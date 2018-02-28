package ckhbox.villagebox.common.event;

import ckhbox.villagebox.common.config.VBConfig;
import ckhbox.villagebox.common.entity.villager.EntityVillager;
import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.player.CapExPlayerPropertiesProvider;
import ckhbox.villagebox.common.player.ExtendedPlayerProperties;
import ckhbox.villagebox.common.util.helper.PathHelper;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventEntity {

    @SubscribeEvent
    public void onAttachCapability(AttachCapabilitiesEvent.Entity event){
        if(event.getEntity() instanceof EntityPlayer){
            event.addCapability(ExtendedPlayerProperties.key, new CapExPlayerPropertiesProvider((EntityPlayer)event.getEntity()));
        }
    }

    @SubscribeEvent
    public void livingSpawnEvent(LivingSpawnEvent event) {

        Class ec = event.getEntityLiving().getClass();

        if(ec == EntityZombie.class){
            EntityZombie entity = (EntityZombie)event.getEntityLiving();
            entity.targetTasks.addTask(3, new EntityAINearestAttackableTarget(entity, EntityVillager.class, true));
        }

    }

    @SubscribeEvent
    public void onLivingUpdating(LivingUpdateEvent event)
    {
        if(!event.getEntity().worldObj.isRemote){
            //register extended player properties
            if(event.getEntity() instanceof EntityPlayer){

                ExtendedPlayerProperties playerProperties = ExtendedPlayerProperties.get((EntityPlayer)event.getEntity());

                if(playerProperties.hasSentInvitation && playerProperties.newMailTimer > 0){
                    if(--playerProperties.newMailTimer == 0){
                        ((EntityPlayer)event.getEntity()).addChatMessage(new TextComponentTranslation(PathHelper.full("message.mail.newmail")));
                    }
                }
            }
        }

    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (!event.getWorld().isRemote && event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.getEntity();

            ExtendedPlayerProperties p = ExtendedPlayerProperties.get(player);

            if(!p.receivedVillagebook && !VBConfig.disableBookAtStarting){
                ItemStack villageBook = new ItemStack(ModItems.villageBook);
                ItemStack invitation = new ItemStack(ModItems.invitation);
                if (!player.inventory.addItemStackToInventory(villageBook)) {
                    player.dropItem(villageBook, false);
                }
                if (!player.inventory.addItemStackToInventory(invitation)) {
                    player.dropItem(invitation, false);
                }
                p.receivedVillagebook = true;
            }
        }
    }

    @SubscribeEvent
    void onClone(PlayerEvent.Clone event) {
        NBTTagCompound temp = new NBTTagCompound();
        ExtendedPlayerProperties old = ExtendedPlayerProperties.get(event.getOriginal());
        ExtendedPlayerProperties current = ExtendedPlayerProperties.get(event.getEntityPlayer());
        old.saveNBTData(temp);
        current.loadNBTData(temp);
    }

    @SubscribeEvent
    void onItemUseTick(LivingEntityUseItemEvent.Start event){
        if(event.getItem().getItem() == ModItems.efficientBow){
            event.setDuration(ModItems.efficientBow.getMaxItemUseDuration(event.getItem()) - 9);
        }
    }
}
