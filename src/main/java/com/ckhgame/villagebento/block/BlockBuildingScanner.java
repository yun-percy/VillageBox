package com.ckhgame.villagebento.block;

import java.util.List;

import com.ckhgame.villagebento.Main;
import com.ckhgame.villagebento.building.builder.BuildingEntityList;
import com.ckhgame.villagebento.building.scanning.BlockTypePosMetadata;
import com.ckhgame.villagebento.building.scanning.BuildingPrefab;
import com.ckhgame.villagebento.building.scanning.HelperScanning;
import com.ckhgame.villagebento.config.ConfigBuilding;
import com.ckhgame.villagebento.data.DataBuilding;
import com.ckhgame.villagebento.data.DataVillageBento;
import com.ckhgame.villagebento.util.helper.HelperDataVB;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockBuildingScanner extends Block {

    protected BlockBuildingScanner() {
        super(Material.wood);
        this.setBlockName("BlockBuildingScanner");
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setBlockTextureName(Main.MODID + ":BlockBuildingScanner");
        this.setStepSound(soundTypeWood);
    }
    
    private DataVillageBento villageBentoData = null; 
    
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		
		if(world.isRemote || world.provider.dimensionId !=0) return true;
		else{
			villageBentoData = DataVillageBento.get();
			
			scanBuilding(world,x,y,z);		
			outputScanResults();
			outputCodes();
						
			return true;
		}	
	}
	
	private BuildingPrefab prefab;
	
	private void scanBuilding(World world,int x, int y,int z){
		
		prefab = null;		
		
		DataVillageBento dataVB = DataVillageBento.get();
		
		//get building data
		DataBuilding bd = HelperDataVB.findBuildingByPos(dataVB,x,y,z);
		int d = ConfigBuilding.BuildingGroundWorkDepth;
		int h = ConfigBuilding.BuildingMaxHeight;
		Block b;
		int mt;
		
		if(bd != null){
			prefab = new BuildingPrefab();
			
			prefab.bX = bd.x;
			prefab.bY = bd.y;
			prefab.bZ = bd.z;
			
			//scan blocks
			for(int dy = -d;dy < h;dy++){
				for(int dx= -bd.sizeX;dx<= bd.sizeX;dx++){
					for(int dz= -bd.sizeZ;dz<= bd.sizeZ;dz++){
						b = world.getBlock(bd.x + dx, bd.y + dy, bd.z + dz);
						mt = world.getBlockMetadata(bd.x + dx, bd.y + dy, bd.z + dz);
						if(dy >= 0){ //scan blocks above groundwork
							if(b != Blocks.air && b != ModBlocks.blockBuildingScanner)
								prefab.addblock(new BlockTypePosMetadata(b,dx,dy,dz,mt,world.getTileEntity(bd.x + dx, bd.y + dy, bd.z + dz)));
						}					
						else{ //under center y,in other words inside of groundwork
							if(b != ConfigBuilding.GroundWorkBlock)
								if(b == ModBlocks.blockBuildingScanner)
									prefab.addblock(new BlockTypePosMetadata(Blocks.air,dx,dy,dz,mt,world.getTileEntity(bd.x + dx, bd.y + dy, bd.z + dz)));
								else
									prefab.addblock(new BlockTypePosMetadata(b,dx,dy,dz,mt,world.getTileEntity(bd.x + dx, bd.y + dy, bd.z + dz)));
						}
					}
				}
			}
			prefab.sort();
			
			//scan entityes
			Class[] entityClasses = BuildingEntityList.get();
			
			AxisAlignedBB aabb = HelperDataVB.getBuildingAABB(bd);
			for(Class c : entityClasses){
				List<Entity> es = world.getEntitiesWithinAABB(c, aabb);
				for(Entity e : es){
					prefab.addEntity(e);
				}
			}
		}
		else{
			System.out.println("Can not find the building data");
		}
		
		
	}
	
	private void outputScanResults(){
		
		if(prefab != null){
			
			System.out.println("Blocks of the current Building.....");
			System.out.println("===================================");
			for(BlockTypePosMetadata b:prefab.blocks){
				System.out.println("Block:" + b.block + ",X:" + b.x + ",Y:" + b.y + ",Z:" + b.z + ",Metadata:" + b.metadata);
				if(b.tileEntity != null){
					System.out.println("TileEntity:"+b.tileEntity);
				}
			}
			for(Entity e : prefab.entities){
				System.out.println("Entity:"+e);
			}
		}
		else{
			System.out.println("Prefab is null!");
		}
		
	}
	
	private void outputCodes(){
		
		if(prefab != null){
			
			System.out.println("Codes of the current Building.....");
			System.out.println("===================================");
			int ln = 0;
			for(BlockTypePosMetadata b:prefab.blocks){
				
				System.out.format("bb.buildBlock(%d,%d,%d,%s,%d);" + (ln++%3 == 2?"\n":""), b.x,b.y,b.z,HelperScanning.getInstancePath(b.block),b.metadata);
				if(b.tileEntity != null){
					ln = 0;
					//pot
					if(b.tileEntity.getClass() == TileEntityFlowerPot.class){
						TileEntityFlowerPot tePot = (TileEntityFlowerPot)b.tileEntity;
						
						int itemID = Item.getIdFromItem(tePot.getFlowerPotItem());
						int data = tePot.getFlowerPotData();
						System.out.format("bb.addTileEntityPot(%d,%d,%d,%d,%d);\n",
								tePot.xCoord - prefab.bX,tePot.yCoord - prefab.bY,tePot.zCoord - prefab.bZ,itemID,data);
					}
					else if(b.tileEntity.getClass() == TileEntitySign.class){
						TileEntitySign teSign = (TileEntitySign)b.tileEntity;
						String l1 = teSign.signText[0] == null?"":teSign.signText[0];
						String l2 = teSign.signText[1] == null?"":teSign.signText[1];
						String l3 = teSign.signText[2] == null?"":teSign.signText[2];
						String l4 = teSign.signText[3] == null?"":teSign.signText[3];
						System.out.format("bb.addTileEntitySign(%d,%d,%d,\"%s\",\"%s\",\"%s\",\"%s\");\n"
										 ,teSign.xCoord - prefab.bX,teSign.yCoord - prefab.bY,teSign.zCoord - prefab.bZ,l1,l2,l3,l4);
					}
				}
			}
			
			//entities
			System.out.format("");
			for(Entity e:prefab.entities){
				if(e.getClass() == EntityPainting.class){
					EntityPainting ePainting = (EntityPainting)e;
					System.out.format("bb.addEntityPainting(%d,%d,%d,%d,\"%s\");\n",
							ePainting.field_146063_b - prefab.bX,ePainting.field_146064_c - prefab.bY,ePainting.field_146062_d - prefab.bZ,
							ePainting.hangingDirection,ePainting.art.title);
				}
				else if(e.getClass() == EntityItemFrame.class){
					EntityItemFrame eItemFrame = (EntityItemFrame)e;
					ItemStack itemStack = eItemFrame.getDisplayedItem();
					int itemID = itemStack == null?-1:Item.getIdFromItem(itemStack.getItem());
					String path = null;
					Block block = (Block)Block.blockRegistry.getObjectById(itemID);
					if(block != null && block != Blocks.air){
						path = HelperScanning.getInstancePath(block);
					}
					else{
						Item item = Item.getItemById(itemID);
						path = HelperScanning.getInstancePath(item);
					}				
					System.out.format("bb.addEntityItemFrame(%d,%d,%d,%d,%s);\n",	
							eItemFrame.field_146063_b - prefab.bX,eItemFrame.field_146064_c - prefab.bY,eItemFrame.field_146062_d - prefab.bZ,
							eItemFrame.hangingDirection,path);
				}
			}
			
		}
		
	}
}