package ckhbox.villagebento.client.gui.villager;

import java.io.IOException;
import java.util.ArrayList;

import ckhbox.villagebento.client.gui.GuiHelper;
import ckhbox.villagebento.common.entity.villager.EntityVillager;
import ckhbox.villagebento.common.gui.villager.ContainerUpgrading;
import ckhbox.villagebento.common.network.ModNetwork;
import ckhbox.villagebento.common.network.message.MessageGuiSelectTradeRecipeIndex;
import ckhbox.villagebento.common.util.helper.PathHelper;
import ckhbox.villagebento.common.village.profession.Profession;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiVillagerUpgrading extends GuiContainer{
	
	private static final ResourceLocation UpgradeGuiTexture = new ResourceLocation(PathHelper.full("textures/gui/villager/upgrade.png"));

	private EntityVillager villager;
    private ArrowButton nextButton;
    private ArrowButton previousButton;
    private UpgradeButton upgradeButton;
    private int selectedUpgradeOptionIdx;
    private boolean noUpgradeOption;
    
	public GuiVillagerUpgrading(InventoryPlayer playerInventory, EntityVillager villager, World worldIn)
    {
        super(new ContainerUpgrading(playerInventory, villager, worldIn));
        this.villager = villager;
    }
	
	@Override
    public void initGui()
    {
        super.initGui();
        
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;  
        
        Profession[] nextUpgradeOptions = this.villager.getProfession().getUpgradeToNextOptions();

        this.noUpgradeOption = (nextUpgradeOptions == null || nextUpgradeOptions.length <= 0);
        
        if(!this.noUpgradeOption){
            this.buttonList.add(this.nextButton = new ArrowButton(1, x + 125 + 35, y + 30 - 1, true));
            this.buttonList.add(this.previousButton = new ArrowButton(2, x + 31 - 27, y + 30 - 1, false));
            this.buttonList.add(this.upgradeButton = new UpgradeButton(3, x + 82, y + 41));
            this.nextButton.enabled = false;
            this.previousButton.enabled = false;
        }
        
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(UpgradeGuiTexture);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);		
               
        //upgrading preview
        if(!this.noUpgradeOption){
        	Profession[] nextUpgradeOptions = this.villager.getProfession().getUpgradeToNextOptions();
        	Profession currentOption = nextUpgradeOptions[this.selectedUpgradeOptionIdx];
        	this.villager.previewProfession = currentOption;
        	this.drawEntityOnScreen(x + 139, y + 73, 20, this.villager);
        	this.villager.previewProfession = null;
        }
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawScreen(mouseX, mouseY, partialTicks);
        if(this.noUpgradeOption){
        	this.drawCenteredString(this.fontRendererObj, "�eNo upgrading for next...", this.width / 2, this.height / 2, 0);
        }
        else{
        	Profession[] nextUpgradeOptions = this.villager.getProfession().getUpgradeToNextOptions();

            int i = (this.width - this.xSize) / 2;
            int j = (this.height - this.ySize) / 2;
            Profession currentOption = nextUpgradeOptions[this.selectedUpgradeOptionIdx];
            ItemStack itemstack = null;
            GlStateManager.pushMatrix();
            RenderHelper.enableGUIStandardItemLighting();
            GlStateManager.disableLighting();
            GlStateManager.enableRescaleNormal();
            GlStateManager.enableColorMaterial();
            GlStateManager.enableLighting();
            this.itemRender.zLevel = 100.0F;
            
            int x;
            ItemStack[] upgradeNeeds = currentOption.getUpgradeToCurentNeeds();
            if(upgradeNeeds != null && upgradeNeeds.length > 0){
                for(int itemIdx = 0;itemIdx<currentOption.getUpgradeToCurentNeeds().length;itemIdx++){
                	itemstack = upgradeNeeds[itemIdx];
                	x = i + 19 + (3 - upgradeNeeds.length + itemIdx) * 18;
                	this.itemRender.renderItemAndEffectIntoGUI(itemstack, x, j + 28);
                    this.itemRender.renderItemOverlays(this.fontRendererObj, itemstack, x, j + 28);
                }
                
                //tooltips
                for(int itemIdx = 0;itemIdx<currentOption.getUpgradeToCurentNeeds().length;itemIdx++){
                	itemstack = upgradeNeeds[itemIdx];
                	x = 19 + (3 - upgradeNeeds.length + itemIdx) * 18;
                	if (this.isPointInRegion(x, 28, 16, 16, mouseX, mouseY) && itemstack != null)
    		        {
    		            this.renderToolTip(itemstack, mouseX, mouseY);
    		        }
                }
            }

            GlStateManager.popMatrix();
            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
            RenderHelper.enableStandardItemLighting();
            
            //profession hover text
            this.drawFieldHoverText(i + 122, j + 27, 35, 49, mouseX, mouseY, currentOption.getDisplayName(), currentOption.getDescription());
            
            //upgrade button hover text
            this.drawButtonHoverText(this.upgradeButton, mouseX, mouseY, "Upgrade", "Upgrade the villager");            
        }
    }
	
	private void drawFieldHoverText(int x, int y, int w, int h, int mouseX, int mouseY, String title, String desc){	
		if(GuiHelper.isPointInRegion(x, y, w, h, mouseX, mouseY)){
			ArrayList<String> list = new ArrayList<String>();
			list.add("�e" + title);
			list.add("�f" + desc);
			this.drawHoveringText(list, mouseX, mouseY, this.fontRendererObj);
		}
	}
	
	private void drawButtonHoverText(GuiButton button, int mouseX, int mouseY, String title, String desc){	
		this.drawFieldHoverText(button.xPosition, button.yPosition, button.width, button.height, mouseX, mouseY, title, desc);
	}
	
	public void drawEntityOnScreen(int posX, int posY, int scale, EntityLivingBase ent)
    {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)posX, (float)posY, 50.0F);
        GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        float f = ent.renderYawOffset;
        float f1 = ent.rotationYaw;
        float f2 = ent.rotationPitch;
        float f3 = ent.prevRotationYawHead;
        float f4 = ent.rotationYawHead;
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
       // GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        ent.rotationYawHead = ent.rotationYaw;
        ent.prevRotationYawHead = ent.rotationYaw;
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntityWithPosYaw(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        rendermanager.setRenderShadow(true);
        ent.renderYawOffset = f;
        ent.rotationYaw = f1;
        ent.rotationPitch = f2;
        ent.prevRotationYawHead = f3;
        ent.rotationYawHead = f4;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
	
	@Override
	public void updateScreen()
    {
        super.updateScreen();
        
        if(!this.noUpgradeOption){    
	        Profession[] nextUpgradeOptions = this.villager.getProfession().getUpgradeToNextOptions();
	        if (nextUpgradeOptions != null)
	        {
	            this.nextButton.enabled = this.selectedUpgradeOptionIdx < nextUpgradeOptions.length - 1;
	            this.previousButton.enabled = this.selectedUpgradeOptionIdx > 0;
	        }
        }
    }

	@Override
	protected void actionPerformed(GuiButton button) throws IOException
    {
        boolean flag = false;

        if (button == this.nextButton)
        {
            ++this.selectedUpgradeOptionIdx;
            Profession[] nextUpgradeOptions = this.villager.getProfession().getUpgradeToNextOptions();

            if (nextUpgradeOptions != null && this.selectedUpgradeOptionIdx >= nextUpgradeOptions.length)
            {
                this.selectedUpgradeOptionIdx = nextUpgradeOptions.length - 1;
            }

            flag = true;
        }
        else if (button == this.previousButton)
        {
            --this.selectedUpgradeOptionIdx;

            if (this.selectedUpgradeOptionIdx < 0)
            {
                this.selectedUpgradeOptionIdx = 0;
            }

            flag = true;
        }

        if (flag)
        {
            ((ContainerUpgrading)this.inventorySlots).setCurrentUpgradeOptionIndex(this.selectedUpgradeOptionIdx);
            ModNetwork.getInstance().sendToServer(new MessageGuiSelectTradeRecipeIndex(this.selectedUpgradeOptionIdx));
        }
        
        if(button == this.upgradeButton){
        	System.out.println("upgrade...");//test
        }
    }
	
	@SideOnly(Side.CLIENT)
    static class ArrowButton extends GuiButton
    {
        private final boolean left;

        public ArrowButton(int buttonID, int x, int y, boolean left)
        {
            super(buttonID, x, y, 12, 19, "");
            this.left = left;
        }

        /**
         * Draws this button to the screen.
         */
        public void drawButton(Minecraft mc, int mouseX, int mouseY)
        {
            if (this.visible)
            {
                mc.getTextureManager().bindTexture(UpgradeGuiTexture);
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                boolean flag = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
                int i = 0;
                int j = 176;

                if (!this.enabled)
                {
                    j += this.width * 2;
                }
                else if (flag)
                {
                    j += this.width;
                }

                if (!this.left)
                {
                    i += this.height;
                }

                this.drawTexturedModalRect(this.xPosition, this.yPosition, j, i, this.width, this.height);
            }
        }
    }
	
	@SideOnly(Side.CLIENT)
    static class UpgradeButton extends GuiButton
    {

        public UpgradeButton(int buttonID, int x, int y)
        {
            super(buttonID, x, y, 29, 21, "");
        }

        /**
         * Draws this button to the screen.
         */
        public void drawButton(Minecraft mc, int mouseX, int mouseY)
        {
            if (this.visible)
            {
                mc.getTextureManager().bindTexture(UpgradeGuiTexture);
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                boolean flag = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
                int i = 38;
                int j = 177;

                if (!this.enabled)
                {
                    i += 23 * 2;
                }
                else if (flag)
                {
                    i += 23;
                }

                this.drawTexturedModalRect(this.xPosition, this.yPosition, j, i, this.width, this.height);
            }
        }
    }
	
}