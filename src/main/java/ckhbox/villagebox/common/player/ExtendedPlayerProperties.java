package ckhbox.villagebox.common.player;

import java.util.ArrayList;
import java.util.List;

import ckhbox.villagebox.VillageBoxMod;
import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.util.helper.PathHelper;
import ckhbox.villagebox.common.util.math.Rand;
import ckhbox.villagebox.common.village.profession.Profession;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.items.IItemHandler;

public class ExtendedPlayerProperties{

    public static final ResourceLocation key = new ResourceLocation(VillageBoxMod.MODID + ".expp");

    public static final int NewMailTimerTotal = 1000;

    public static ExtendedPlayerProperties get(EntityPlayer player){
        return player.getCapability(CapExPlayerProperties.EXTENDED_PLAYER_PROPERTIES_CAPABILITY, null);
    }

    //-------------------------------------------------

    public EntityPlayer player;

    public boolean hasSentInvitation;//if the player has sent the invivation
    public int newMailTimer;//how much time left to receive a new mail

    public int treasureHuntLevel;//high level will bring more gems

    public boolean receivedVillagebook = false; // if the player has recived the village book

    public Collections collections;

    public int playerBronzeCoinNum; //player bronze coin number,max 99999
    public int playerSilverCoinNum;
    public int playerGoldCoinNum;

    public void sendNewVillagerInvitation(){
        if(!this.hasSentInvitation){
            this.hasSentInvitation = true;
            this.resetMailTimer();
        }
    }

    public boolean hasNewVillagerMail(){
        return this.hasSentInvitation && this.newMailTimer <= 0;
    }

    public void receiveNewVillagerMail(){
        this.hasSentInvitation = false;
    }

    public void resetMailTimer(){
        this.newMailTimer = (int)(ExtendedPlayerProperties.NewMailTimerTotal * (Rand.get().nextFloat() * 0.5F + 0.5F));
    }

    public boolean upgradeTreasureHuntLevelTo(int level){
        if(level > treasureHuntLevel){
            treasureHuntLevel = level;
            return true;
        }
        else{
            return false;
        }
    }
    public void addBronzeCoinNum(int num){
        if(playerBronzeCoinNum + num <=99999){
            playerBronzeCoinNum += num;
        }else{
            playerBronzeCoinNum = 99999;
        }
    }
    public void addSilverCoinNum(int num){
        if(playerSilverCoinNum + num <=99999){
            playerSilverCoinNum += num;
        }else{
            playerSilverCoinNum = 99999;
        }
    }
    public void addGoldCoinNum(int num){
        if(playerGoldCoinNum + num <=99999){
            playerGoldCoinNum += num;
        }else{
            playerGoldCoinNum = 99999;
        }
    }

    public ExtendedPlayerProperties(EntityPlayer player){
        this.player = player;
        this.collections = new Collections(player);
    }

    public void saveNBTData(NBTTagCompound compound) {
        compound.setBoolean("invited", this.hasSentInvitation);
        compound.setInteger("nmtimer", this.newMailTimer);
        compound.setInteger("treasurelvl", this.treasureHuntLevel);
        compound.setBoolean("receiveddvb", this.receivedVillagebook);
        compound.setInteger("playerBronzeCoinNum",this.playerBronzeCoinNum);
        compound.setInteger("playerSilverCoinNum",this.playerSilverCoinNum);
        compound.setInteger("playerGoldCoinNum",this.playerGoldCoinNum);
        //collections
        NBTTagCompound collections = new NBTTagCompound();
        this.collections.saveNBTData(collections);
        compound.setTag("collections", collections);
    }

    public void loadNBTData(NBTTagCompound compound) {
        this.hasSentInvitation = compound.getBoolean("invited");
        this.newMailTimer = compound.getInteger("nmtimer");
        this.treasureHuntLevel = compound.getInteger("treasurelvl");
        this.receivedVillagebook = compound.getBoolean("receiveddvb");
        this.playerBronzeCoinNum = compound.getInteger("playerBronzeCoinNum");
        this.playerSilverCoinNum = compound.getInteger("playerSilverCoinNum");
        this.playerGoldCoinNum = compound.getInteger("playerGoldCoinNum");
        //collections
        this.collections.loadNBTData(compound.getCompoundTag("collections"));
    }

    public void init(Entity entity, World world) {
        this.resetMailTimer();
        this.hasSentInvitation = false;
        this.treasureHuntLevel = 0;
        this.playerBronzeCoinNum = 0;
        this.playerSilverCoinNum = 0;
        this.playerGoldCoinNum = 0;
    }

    public static class Collections{
        private EntityPlayer player;
        private List<Integer> proIDs = new ArrayList<Integer>(); //unlocked villager professions

        public Collections(EntityPlayer player){
            this.player = player;
        }

        public void addProfession(Profession profession){
            if(profession != null && !this.proIDs.contains(profession.getRegID())){
                this.proIDs.add(profession.getRegID());
                ItemStack villageBook = new ItemStack(ModItems.villageBook);
                this.player.addChatMessage(new TextComponentTranslation(PathHelper.full("message.player.collections.addproid"),
                            new TextComponentTranslation(profession.getUnloalizedDisplayName()),
                            new TextComponentTranslation(villageBook.getUnlocalizedName() + ".name")));
            }
        }

        public boolean hasProfession(int id){
            return this.proIDs.contains(id);
        }

        public void loadNBTData(NBTTagCompound compound){
            int[] proids = compound.getIntArray("proids");
            if(proids != null && proids.length > 0){
                this.proIDs.clear();
                for(int i =0;i<proids.length;i++)
                    this.proIDs.add(proids[i]);
            }
        }

        public void saveNBTData(NBTTagCompound compound){
            if(this.proIDs.size() > 0){
                int[] temp = new int[this.proIDs.size()];
                for(int i =0;i<this.proIDs.size();i++){
                    temp[i] = this.proIDs.get(i);
                }
                compound.setIntArray("proids", temp);
            }
            else{
                compound.removeTag("proids");
            }
        }
    }

}
