package ckhbox.villagebox.common.entity.villager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ckhbox.villagebox.VillageBoxMod;
import ckhbox.villagebox.common.config.VBConfig;
import ckhbox.villagebox.common.entity.ai.VillagerAIFollowing;
import ckhbox.villagebox.common.entity.ai.VillagerAILookAtInteractPlayer;
import ckhbox.villagebox.common.entity.ai.VillagerAIWander;
import ckhbox.villagebox.common.gui.GuiIDs;
import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.player.ExtendedPlayerProperties;
import ckhbox.villagebox.common.util.helper.BitHelper;
import ckhbox.villagebox.common.util.helper.PathHelper;
import ckhbox.villagebox.common.util.math.IntBoundary;
import ckhbox.villagebox.common.util.math.IntVec3;
import ckhbox.villagebox.common.util.math.Rand;
import ckhbox.villagebox.common.util.tool.HouseDetector;
import ckhbox.villagebox.common.util.tool.NameGenerator;
import ckhbox.villagebox.common.village.data.DataVillage;
import ckhbox.villagebox.common.village.profession.Profession;
import ckhbox.villagebox.common.village.quest.IQuestProvider;
import ckhbox.villagebox.common.village.quest.Quest;
import ckhbox.villagebox.common.village.trading.ITrading;
import ckhbox.villagebox.common.village.trading.TradingRecipeList;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityVillager extends EntityCreature implements ITrading, IQuestProvider{

    private static final DataParameter<Integer> PROFESSIONID = EntityDataManager.<Integer>createKey(EntityVillager.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> FLAGS = EntityDataManager.<Integer>createKey(EntityVillager.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> QUEST = EntityDataManager.<Integer>createKey(EntityVillager.class, DataSerializers.VARINT);

    private Profession profession;
    //the player this villager is currently interacting with
    private EntityPlayer interacting;
    private EntityPlayer following;

    private IntBoundary home;

    //the canter of wandering when no home has been set to this villager
    private Vec3d wanderCenter;

    //the upgrading history
    private List<Integer> upgradingHistory = new ArrayList<Integer>(Arrays.asList(new Integer[]{99999}));//99999:caveman

    public EntityVillager(World worldIn){
        this(worldIn, Rand.get().nextBoolean());
    }

    public EntityVillager(World worldIn, boolean male){
        this(worldIn, male?NameGenerator.getRandomMaleName():NameGenerator.getRandomFemaleName(), male);
    }

    public EntityVillager(World worldIn, String name, boolean male) {
        super(worldIn);

        this.setSize(0.6F, 1.8F);

        if(!this.worldObj.isRemote){
            this.setProfession(Rand.get().nextInt(3));
        }

        this.setGender(male);

        if(!this.hasCustomName()){
            this.setCustomNameTag(name);
        }

        this.initAI();
    }



    @Override
    public EntityJumpHelper getJumpHelper() {
        // TODO Auto-generated method stub
        return super.getJumpHelper();
    }



    @Override
    protected float getJumpUpwardsMotion() {
        // TODO Auto-generated method stub
        return super.getJumpUpwardsMotion();
    }

    protected void initAI(){
        ((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityZombie.class, 8.0F, 0.6D, 0.6D));
        this.tasks.addTask(1, new VillagerAILookAtInteractPlayer(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new VillagerAIFollowing(this,0.6F));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.3D));
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(9, new VillagerAIWander(this, 0.4D));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        //profession id
        this.getDataManager().register(PROFESSIONID, Integer.valueOf(0));
        //data flags(interacting.following,etc.)
        this.getDataManager().register(FLAGS, Integer.valueOf(0));
        //quest
        this.getDataManager().register(QUEST, Integer.valueOf(-1));
    }


    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand, ItemStack stack) {

        if(!player.worldObj.isRemote){
            if(	(this.isInteracting() && this.interacting.isEntityAlive() && this.interacting != player) ||
                    (this.isFollowing() && this.following.isEntityAlive() && this.following != player)){
                player.addChatMessage(new TextComponentTranslation(PathHelper.full("message.villager.isbusy")));
                    }
            else{
                ItemStack itemstack = player.inventory.getCurrentItem();
                if(itemstack != null && (itemstack.getItem() == ModItems.resetScroll || itemstack.getItem() == ModItems.dismissalScroll) && itemstack.stackSize > 0){
                    //if the player is using a reset or a dismissal scroll
                    if((itemstack.getItem() == ModItems.resetScroll && this.downgrade()) ||
                            (itemstack.getItem() == ModItems.dismissalScroll && this.dismiss(player))){
                        this.consumeItemFromStack(player,itemstack);
                            }
                }
                else{
                    player.openGui(VillageBoxMod.instance, GuiIDs.VillagerMain, player.worldObj, player.dimension, this.getEntityId(), 0);
                    //collections
                    this.addProIDToCollections(player);
                }
            }
        }

        return true;
    }

    protected void consumeItemFromStack(EntityPlayer player, ItemStack stack)
    {
        if (!player.capabilities.isCreativeMode)
        {
            --stack.stackSize;

            if (stack.stackSize <= 0)
            {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
            }
        }
    }

    @Override
    public TradingRecipeList getTradingRecipeList() {
        return this.profession.getTradingRecipeList();
    }

    @Override
    public void onTrade() {
    }

    public Vec3d getWanderCenter(){
        if(this.wanderCenter == null){
            this.wanderCenter = new Vec3d(this.posX, this.posY, this.posZ);
        }
        return this.wanderCenter;
    }

    //data flags

    /**
     * POS: 0=Interacting, 1=Following, 2=Has Home
     */
    protected void setDataFlag(int pos, boolean flag){
        int data = ((Integer)this.getDataManager().get(FLAGS)).intValue();
        data = BitHelper.writeBit(data, pos, flag);
        this.getDataManager().set(FLAGS, Integer.valueOf(data));
    }

    /**
     * POS: 0=Interacting, 1=Following, 2=Has Home, 3 gender
     */
    protected boolean getDataFlag(int pos){
        int data = ((Integer)this.getDataManager().get(FLAGS)).intValue();
        return BitHelper.readBit(data, pos);
    }

    //gender
    public void setGender(boolean male){
        this.setDataFlag(3, male);
    }

    public boolean isMale(){
        return this.getDataFlag(3);
    }

    public int[] getUpgradingHistory(){
        if(this.upgradingHistory == null || this.upgradingHistory.size() < 1)
            return null;
        int[] upgrades = new int[this.upgradingHistory.size()];
        for(int i =0;i<upgrades.length;i++){
            upgrades[i] = this.upgradingHistory.get(i);
        }
        return upgrades;
    }

    public void setUpgradingHistory(int[] arr){
        this.upgradingHistory.clear();
        if(arr != null && arr.length > 0){
            for(int i =0;i<arr.length;i++){
                this.upgradingHistory.add(arr[i]);
            }
        }
    }

    //interacting and following
    public void setInteracting(EntityPlayer player){
        if(!this.worldObj.isRemote){
            this.interacting = player;
            this.setDataFlag(0, (this.interacting != null));
        }
    }

    public boolean isInteracting(){
        return this.getDataFlag(0);
    }

    public EntityPlayer getInteracting(){
        return this.interacting;
    }

    public void setFollowing(EntityPlayer player){
        if(!this.worldObj.isRemote){
            this.following = player;
            this.setDataFlag(1, (this.following != null));
            this.wanderCenter = null;
        }
    }

    public boolean isFollowing(){
        return this.getDataFlag(1);
    }

    public EntityPlayer getFollowing(){
        return this.following;
    }

    //set home
    public void setCurrentPosAsHome(EntityPlayer player){
        //server side only
        if(this.worldObj.isRemote)
            return;

        //scan home boundary
        IntBoundary bound = HouseDetector.getClosedField(this.worldObj, new IntVec3(this.posX,this.posY,this.posZ));
        if(bound == null){
            player.addChatMessage(new TextComponentTranslation(PathHelper.full("message.villager.home.openspace")));
        }
        else{
            //remove outlines
            bound = bound.extend(-1,0,-1);
            bound.maxy -= 1;
            //System.out.println(bound.toString());
            //add home bounday
            String oldOwner = DataVillage.get(this.worldObj).addHome(this.getName(),bound);
            if(oldOwner != null){
                player.addChatMessage(new TextComponentTranslation(PathHelper.full("message.villager.home.existed"),oldOwner));
            }
            else{
                //remove old home
                if(this.home != null){
                    DataVillage.get(this.worldObj).removeHome(this.getName(),home);
                }
                this.home = bound;
                //stop following
                this.setFollowing(null);
                player.addChatMessage(new TextComponentTranslation(PathHelper.full("message.villager.home.movein"),this.getName()));
                //update data flag
                this.setDataFlag(2, true);
            }
        }
    }

    public void setHome(IntBoundary home){
        this.home = home;
        this.setDataFlag(2, true);
    }

    public void moveOutHome(EntityPlayer player){
        if(this.home != null){
            DataVillage.get(this.worldObj).removeHome(this.getName(),home);
            this.home = null;
            this.setDataFlag(2, false);
            player.addChatMessage(new TextComponentTranslation(PathHelper.full("message.villager.home.moveout"),this.getName()));
        }
    }

    public IntBoundary getHome(){
        return this.home;
    }

    public boolean hasHome(){
        return this.getDataFlag(2);
    }

    //profession
    public Profession getProfession(){
        if(this.worldObj.isRemote && (this.profession == null || ((Integer)this.getDataManager().get(PROFESSIONID)).intValue() != this.profession.getRegID())){
            this.profession = Profession.registry.get(((Integer)this.getDataManager().get(PROFESSIONID)).intValue());
            this.refreshProfession();
        }
        return this.profession;
    }

    public void setProfession(int proid){
        this.getDataManager().set(PROFESSIONID, Integer.valueOf(proid));
        this.refreshProfession();
    }

    public void upgrade(int pid){
        if(!this.worldObj.isRemote){
            TextComponentTranslation oldProName = new TextComponentTranslation(this.getProfession().getUnloalizedDisplayName());
            this.upgradingHistory.add(this.getProfession().getRegID());
            this.setProfession(pid);
            TextComponentTranslation newProName = new TextComponentTranslation(this.getProfession().getUnloalizedDisplayName());
            this.getServer().getPlayerList().sendChatMsg(new TextComponentTranslation(PathHelper.full("message.villager.upgrade"),this.getName(),oldProName,newProName));
            //quest
            this.removeCurrentQuest();
        }
    }

    public boolean downgrade(){
        if(!this.worldObj.isRemote && this.upgradingHistory.size() > 0){
            TextComponentTranslation oldProName = new TextComponentTranslation(this.getProfession().getUnloalizedDisplayName());
            int last = this.upgradingHistory.remove(this.upgradingHistory.size() - 1);
            this.setProfession(last);
            TextComponentTranslation newProName = new TextComponentTranslation(this.getProfession().getUnloalizedDisplayName());
            this.getServer().getPlayerList().sendChatMsg(new TextComponentTranslation(PathHelper.full("message.villager.downgrade"),this.getName(),oldProName,newProName));
            //quest
            this.removeCurrentQuest();
            return true;
        }
        else{
            return false;
        }
    }

    public boolean dismiss(EntityPlayer player){
        if(!this.worldObj.isRemote){
            this.moveOutHome(player);
            this.worldObj.removeEntity(this);
            this.getServer().getPlayerList().sendChatMsg(new TextComponentTranslation(PathHelper.full("message.villager.dismiss"), player.getName(),this.getName(),
                        new TextComponentTranslation(this.getProfession().getUnloalizedDisplayName())));
            return true;
        }
        else{
            return false;
        }
    }

    public String getName(){
        return I18n.format(this.getProfession().getUnloalizedDisplayName());
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        //update profession
        if(this.worldObj.isRemote && (this.profession == null || ((Integer)this.getDataManager().get(PROFESSIONID)).intValue() != this.profession.getRegID())){
            this.setProfession(((Integer)this.getDataManager().get(PROFESSIONID)).intValue());
        }
        //update quest
        if(!this.worldObj.isRemote){
            this.updateQuest();
        }
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        if(!this.worldObj.isRemote){
            DataVillage.get(this.worldObj).addDeadVillager(this);
            this.getServer().getPlayerList().sendChatMsg(new TextComponentTranslation(PathHelper.full("message.villager.died"),this.getName()));
        }
    }

    @Override
    protected void dropEquipment(boolean p_82160_1_, int p_82160_2_){
        //don't drop any equipment
    }

    public void refreshProfession(){
        int proid = ((Integer)this.getDataManager().get(PROFESSIONID)).intValue();
        this.profession = Profession.registry.get(proid);
        if(!this.worldObj.isRemote){
            //clear both hands
            this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, null);
            this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, null);
            //get the item on either left hand or right hand
            this.setItemStackToSlot(Rand.get().nextBoolean()?EntityEquipmentSlot.MAINHAND:EntityEquipmentSlot.OFFHAND, this.profession.getRandomHoldItem());
        }
    }

    //quests

    //the quest idx and the left ticks are stored in the same integer
    //the last one byte are used to store the index
    //the rest bytes are used to store the time left
    //e.g.
    //the integer = 0xFE02
    //ticks left = 0xFE = 254;
    //quest index = 0x02 - 1 = 1
    //* quest index is start from -1, end with 254, -1 means no quest

    public void setCurrentQuestIdx(int idx){
        int data = ((Integer)this.getDataManager().get(QUEST)).intValue();
        data &= 0xFFFFFF00;
        data |= Math.max(0, idx + 1);
        this.getDataManager().set(QUEST, Integer.valueOf(data));
    }

    public int getCurrentQuestIdx(){
        int data = ((Integer)this.getDataManager().get(QUEST)).intValue();
        int idx = (data & 0x000000FF) - 1;
        return idx;
    }

    public int getCurrentQuestTicks(){
        int data = ((Integer)this.getDataManager().get(QUEST)).intValue();
        data >>= 8;
        return data;
    }

    public void setCurrentQuestTicks(int ticks){
        int data = ((Integer)this.getDataManager().get(QUEST)).intValue();
        data &= 0x000000FF;
        data |= (ticks << 8);
        this.getDataManager().set(QUEST, Integer.valueOf(data));
    }

    public int getCurrentQuestTicksLeft(){
        return VBConfig.questLifetime - this.getCurrentQuestTicks();
    }

    @Override
    public void removeCurrentQuest(){
        this.setCurrentQuestIdx(-1);
        this.setCurrentQuestTicks(0);
    }

    @Override
    public void createNewQuest(){
        if(this.getProfession() == null)
            return;
        List<Quest> quests= this.getProfession().getQuests();
        //generate a random quest idx
        int idx = (quests != null && quests.size() > 0)?Rand.get().nextInt(quests.size()):-1;
        this.setCurrentQuestIdx(idx);
        this.setCurrentQuestTicks(0);
    }

    @Override
    public Quest getCurrentQuest(){
        int idx = this.getCurrentQuestIdx();
        if(idx < 0)
            return null;
        if(this.getProfession() == null)
            return null;
        List<Quest> quests = this.getProfession().getQuests();
        if(quests == null || idx >= quests.size())
            return null;
        return quests.get(idx);
    }

    @Override
    public void completeCurrentQuest(EntityPlayer player){
        Quest q = this.getCurrentQuest();
        if(q != null && q.complete(player)){
            this.removeCurrentQuest();
        }
    }

    private void updateQuest(){
        if(VBConfig.questFrequency <= 0) return;
        if(this.getCurrentQuest() == null){
            if(this.hasHome() && this.rand.nextInt(VBConfig.questFrequency) == 0){
                this.createNewQuest();
            }
        }
        else{
            int left = this.getCurrentQuestTicks() + 1;
            if(left > VBConfig.questLifetime) left = VBConfig.questLifetime;
            this.setCurrentQuestTicks(left);
            if(!this.isInteracting() && left == VBConfig.questLifetime){
                this.removeCurrentQuest();
            }
        }
    }

    //collections
    private void addProIDToCollections(EntityPlayer player){
        ExtendedPlayerProperties.get(player).collections.addProfession(this.getProfession());
    }

    //-----

    @Override
    public boolean canBeLeashedTo(EntityPlayer player) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean getAlwaysRenderNameTagForRender()
    {
        return true;
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setInteger("proid", ((Integer)this.getDataManager().get(PROFESSIONID)).intValue());
        tagCompound.setBoolean("gender", this.isMale());
        //home
        if(this.home != null){
            tagCompound.setIntArray("homebd", new int[]{
                this.home.minx,
                    this.home.miny,
                    this.home.minz,
                    this.home.maxx,
                    this.home.maxy,
                    this.home.maxz
            });
        }
        //upgrading history
        int[] upgrades = this.getUpgradingHistory();
        if(upgrades != null){
            tagCompound.setIntArray("upgrades", upgrades);
        }
        //quest
        tagCompound.setInteger("questinfo", ((Integer)this.getDataManager().get(QUEST)).intValue());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund) {
        super.readEntityFromNBT(tagCompund);
        int proid = tagCompund.getInteger("proid");
        this.setProfession(proid);
        this.setGender(tagCompund.getBoolean("gender"));
        //home
        int[] arr = tagCompund.getIntArray("homebd");
        if(arr == null || arr.length == 0){
            this.home = null;
            this.setDataFlag(2,false);
        }
        else{
            this.home = new IntBoundary(arr[0],arr[1],arr[2],arr[3],arr[4],arr[5]);
            this.setDataFlag(2,true);
        }
        //upgrading history
        arr = tagCompund.getIntArray("upgrades");
        this.setUpgradingHistory(arr);
        //quest
        if(tagCompund.hasKey("questinfo")){
            this.getDataManager().set(QUEST, Integer.valueOf(tagCompund.getInteger("questinfo")));
        }
    }


    //----------------------------------
    //upgrading preview
    @SideOnly(Side.CLIENT)
    public Profession previewProfession;
}
