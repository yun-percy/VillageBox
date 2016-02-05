package ckhbox.villagebento.common.entity.villager;

import ckhbox.villagebento.VillageBentoMod;
import ckhbox.villagebento.common.gui.GuiIDs;
import ckhbox.villagebento.common.network.ModNetwork;
import ckhbox.villagebento.common.network.message.villager.MessageGuiSetFollowing;
import ckhbox.villagebento.common.util.helper.PathHelper;
import ckhbox.villagebento.common.util.tool.NameGenerator;
import ckhbox.villagebento.common.village.attribute.AttributeList;
import ckhbox.villagebento.common.village.attribute.VillagerAttribute;
import ckhbox.villagebento.common.village.profession.Profession;
import ckhbox.villagebento.common.village.trading.ITrading;
import ckhbox.villagebento.common.village.trading.TradingRecipeList;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityVillager extends EntityCreature implements ITrading{

	private Profession profession;
	private AttributeList attributeList;
	//the player this villager is currently interacting with
	private EntityPlayer interacting;
	private EntityPlayer following;
	
	public EntityVillager(World worldIn) {
		super(worldIn);		
		
		this.setSize(0.6F, 1.8F);
		
		this.attributeList = new AttributeList();
		this.attributeList.add(new VillagerAttribute(PathHelper.full("villager.attribute.energy"),0,this,17,0));
		this.attributeList.add(new VillagerAttribute(PathHelper.full("villager.attribute.happiness"),1,this,18,1));
		this.attributeList.add(new VillagerAttribute(PathHelper.full("villager.attribute.proficiency"),2,this,19,2));
		
		//set max happiness to 100 
		this.attributeList.get(1).setMaxValue(100);
		
		//set attribute growing
		this.attributeList.get(0).setValueGrowing(10);
		this.attributeList.get(1).setValueGrowing(3);
		this.attributeList.get(2).setValueGrowing(1);
		
		this.refreshProfession();
		
		//temp
		if(!this.hasCustomName()){
			this.setCustomNameTag(NameGenerator.getRandomMaleName());
		}
		
		this.initAI();
	}
	
	protected void initAI(){
		((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
        ((PathNavigateGround)this.getNavigator()).setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityZombie.class, 8.0F, 0.6D, 0.6D));
        //this.tasks.addTask(1, new EntityAITradePlayer(this));
        //this.tasks.addTask(1, new EntityAILookAtTradePlayer(this));
        //this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        //this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.3D));
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        //this.tasks.addTask(9, new EntityAIVillagerInteract(this));
        this.tasks.addTask(9, new EntityAIWander(this, 0.3D));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		//profession id
		this.getDataWatcher().addObject(16, 0);
		//attributes
		this.getDataWatcher().addObject(17, 0);
		this.getDataWatcher().addObject(18, 0);
		this.getDataWatcher().addObject(19, 0);
	}

	@Override
	protected boolean interact(EntityPlayer player) {
		
		if(!player.worldObj.isRemote){
			if(	(this.isInteracting() && this.interacting != player) ||
				(this.isFollowing() && this.following != player)){
				player.addChatMessage(new ChatComponentTranslation(PathHelper.full("message.villager.isbusy")));
			}
			else{
				player.openGui(VillageBentoMod.instance, GuiIDs.VillagerMain, player.worldObj, player.dimension, this.getEntityId(), 0);
			}
		}
		
		return super.interact(player);
	}

	@Override
	public TradingRecipeList getTradingRecipeList() {
		return this.profession.getTradingRecipeList();
	}
	
	public void setInteracting(EntityPlayer player){
		if(!this.worldObj.isRemote){
			this.interacting = player;
		}
	}
	
	public boolean isInteracting(){
		return (this.interacting != null);
	}
	
	public void setFollowing(EntityPlayer player){
		this.following = player;
		if(this.worldObj.isRemote){
			ModNetwork.getInstance().sendToServer(new MessageGuiSetFollowing(this.getEntityId(), this.dimension, (player != null)));
		}
	}
	
	public boolean isFollowing(){
		return (this.following != null);
	}	
	
	public Profession getProfession(){
		if(this.worldObj.isRemote && (this.profession == null || this.getDataWatcher().getWatchableObjectInt(16) != this.profession.getRegID())){
			this.profession = Profession.registry.get(this.getDataWatcher().getWatchableObjectInt(16));
			this.refreshProfession();
		}
		return this.profession;
	}
	
	public void setProfession(int proid){
		this.getDataWatcher().updateObject(16, proid);
		this.refreshProfession();
	}
	
	public AttributeList getAttributes(){
		return this.attributeList;
	}
	
	public String getName(){
		return this.getCustomNameTag();
	}
	
	public boolean isProficiencyFull(){
		VillagerAttribute attribute = (VillagerAttribute)this.attributeList.get(2);
		return (attribute.getValue() >= attribute.getMaxValue());
	}
	
	public void clearProficiency(){
		this.attributeList.get(2).setValue(0);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		//update profession
		if(this.worldObj.isRemote && (this.profession == null || this.getDataWatcher().getWatchableObjectInt(16) != this.profession.getRegID())){
			this.setProfession(this.getDataWatcher().getWatchableObjectInt(16));
		}
		//update attributes
		this.attributeList.update();
		
		if(!this.worldObj.isRemote){
			this.setCustomNameTag("info:"+ this.isInteracting() + "," + this.isFollowing());
		}
	}

	public void refreshProfession(){
		int proid = this.getDataWatcher().getWatchableObjectInt(16);
		this.profession = Profession.registry.get(proid);
		this.attributeList.get(0).setMaxValue(this.profession.getMaxEnegy());
		this.attributeList.get(2).setMaxValue(this.profession.getMaxProficiency());
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound) {
		super.writeEntityToNBT(tagCompound);
		tagCompound.setInteger("proid", this.getDataWatcher().getWatchableObjectInt(16));
		tagCompound.setInteger("attr_eng", (Integer)this.attributeList.get(0).getValue());
		tagCompound.setInteger("attr_hap", (Integer)this.attributeList.get(1).getValue());
		tagCompound.setInteger("attr_pro", (Integer)this.attributeList.get(2).getValue());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompund) {
		super.readEntityFromNBT(tagCompund);
		int proid = tagCompund.getInteger("proid");
		this.setProfession(proid);
		this.attributeList.get(0).setValue(tagCompund.getInteger("attr_eng"));
		this.attributeList.get(1).setValue(tagCompund.getInteger("attr_hap"));
		this.attributeList.get(2).setValue(tagCompund.getInteger("attr_pro"));
	}
	
	

	
	//----------------------------------
	//upgrading preview
	@SideOnly(Side.CLIENT)
	public Profession previewProfession;

	
}
