package ckhbox.villagebox.common.event;

import ckhbox.villagebox.common.config.VBConfig;
import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.player.ExtendedPlayerProperties;
import ckhbox.villagebox.common.util.math.Rand;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockOre;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventCoinsFound {

	private void dropCoins(int num,int treasureHuntLevel, World world, double x, double y, double z){

		if(num > 0){
			int count = num > 64? 64:num;//the max num is 64
			treasureHuntLevel=treasureHuntLevel==0?1:treasureHuntLevel;
			int coinTypeChance=Rand.get().nextInt(1*2*3*10/treasureHuntLevel);
			ItemStack coinType = new ItemStack(ModItems.bronzeCoin,count);
			System.out.println("treasureHuntLevel:"+  treasureHuntLevel);
			System.out.println("coinTypeChance:"+  coinTypeChance);
			System.out.println("count:"+  count);
			//10% gold coin,20% silver coin 70%  bronzecoin
			if (coinTypeChance>54/treasureHuntLevel){
				coinType = new ItemStack(ModItems.goldCoin,1);
			}else if (coinTypeChance>42/treasureHuntLevel){
				coinType = new ItemStack(ModItems.silverCoin,1);
			}
			EntityItem entityitem = new EntityItem(world, x, y + 0.5F, z, coinType);
			entityitem.setDefaultPickupDelay();
			world.spawnEntityInWorld(entityitem);
		}
	}

	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event)
	{
		if(!event.getEntityLiving().worldObj.isRemote &&
			event.getEntityLiving() instanceof EntityMob &&
			event.getSource().getSourceOfDamage() instanceof EntityPlayer){
			if(VBConfig.killMobsDropCoins){
				int l =  ExtendedPlayerProperties.get((EntityPlayer)event.getSource().getSourceOfDamage()).treasureHuntLevel;
				int base = l * 3 + Rand.get().nextInt(3);
				int add = l * 5;
				int coinNum= Rand.get().nextInt(add) + base;
				dropCoins(coinNum,l,event.getEntityLiving().worldObj, event.getEntityLiving().posX, event.getEntityLiving().posY, event.getEntityLiving().posZ);
			}
		}
	}

	@SubscribeEvent
	public void onBlockHarvest(BlockEvent.HarvestDropsEvent event)
	{
		if(!event.getWorld().isRemote && event.getHarvester() != null){
			if(Rand.get().nextInt(5) == 0 && VBConfig.destroyBlocksDropCoins){
				int l =  ExtendedPlayerProperties.get(event.getHarvester()).treasureHuntLevel;
				int base = l * 2 + Rand.get().nextInt(3);
				int add = l * Rand.get().nextInt(4);
				int coinNum= add + base;
				dropCoins(coinNum,l,event.getWorld(), event.getPos().getX() + 0.5D, event.getPos().getY() + 0.5D, event.getPos().getZ() + 0.5D);
			}
		}
	}
}
