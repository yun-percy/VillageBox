package ckhbox.villagebox.common.block.tool;

import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.util.helper.PathHelper;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.BlockStairs;

public class BlockBuildBox extends Block{

    public static enum BuildSize{

        ExLarge("buildboxExLarge",5, 7),
        Large("buildboxLarge",4, 7),
        Medium("buildboxMedium",3, 6),
        Small("buildboxSmall",2, 6);

        public final String name;
        public final int radius;
        public final int height;
        private BuildSize(String name, int radius, int height){
            this.name = name;
            this.radius = radius;
            this.height = height;
        }
    }

    private BuildSize size;

    public BlockBuildBox(BuildSize size) {
        super(Material.WOOD);
        this.size = size;
        this.setUnlocalizedName(PathHelper.full(size.name));
        this.setHardness(1.0F);
        this.setCreativeTab(ModItems.tabVB);
        this.setSoundType(SoundType.WOOD);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
            EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {

        if(!worldIn.isRemote){
            //remove buildbox
            worldIn.setBlockToAir(pos);

            build(worldIn, pos, false);
        }

        return true;
    }

    private void build(World world, BlockPos pos, boolean removeOld){

        int xmin = pos.getX() - size.radius - 1;
        int xmax = pos.getX() + size.radius + 1;
        int zmin = pos.getZ() - size.radius - 1;
        int zmax = pos.getZ() + size.radius + 1;
        int ymin = pos.getY() - 1;
        int ymax = pos.getY() + size.height+size.radius;
        int yroof = pos.getY() + size.height -1;

        IBlockState bsp = Blocks.QUARTZ_BLOCK.getDefaultState();
        IBlockState bsf = Blocks.WOODEN_SLAB.getDefaultState();
        IBlockState quartzSlab = Blocks.STONE_SLAB.getStateFromMeta(7);
        IBlockState bss = Blocks.STONEBRICK.getDefaultState();
        IBlockState bsw = Blocks.GLASS_PANE.getDefaultState();
        IBlockState bsr = Blocks.BRICK_BLOCK.getDefaultState();
        IBlockState bs_light = Blocks.GLOWSTONE.getDefaultState();
        IBlockState bs_door = Blocks.ACACIA_DOOR.getDefaultState();
        IBlockState brickStairs = Blocks.BRICK_STAIRS.getDefaultState();
        //IBlockState brickStairs = Blocks.BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST);

        //		BlockPlanks.EnumType ptRoof = BlockPlanks.EnumType.DARK_OAK;
        //		BlockPlanks.EnumType ptWall = BlockPlanks.EnumType.BIRCH;
        //		BlockPlanks.EnumType ptFloor = BlockPlanks.EnumType.OAK;

        int flags = 1|2;

        for(int x = xmin;x <= xmax; x++){
            for(int z = zmin; z <= zmax; z++){
                for(int y = ymin; y <= ymax; y++){
                    if(y == ymin){//floor
                        if(x==xmin ||x==xmax||z==zmin||z==zmax){
                            world.setBlockState(new BlockPos(x,y,z),bss);
                        }else{
                            world.setBlockState(new BlockPos(x,y,z),quartzSlab);
                        }
                        //if(world.isAirBlock(new BlockPos(x,y,z)) || removeOld) world.setBlockState(new BlockPos(x,y,z), bsf);
                    }
                    else if(y >= yroof){//roof
                        int roof_layer=y-yroof;
                        if((  (x==xmin+roof_layer || x==xmax-roof_layer) && (z>=zmin+roof_layer && z<=zmax-roof_layer) ) || ((z==zmin+roof_layer||z==zmax-roof_layer) && (x>=xmin+roof_layer && x<=xmax-roof_layer))){
                            if(x==xmin+roof_layer){
                                world.setBlockState(new BlockPos(x,y,z),brickStairs.withProperty(BlockStairs.FACING, EnumFacing.EAST));
                            }else if (x==xmax-roof_layer){
                                world.setBlockState(new BlockPos(x,y,z),brickStairs.withProperty(BlockStairs.FACING, EnumFacing.WEST));
                            }else if (z==zmax-roof_layer){
                                world.setBlockState(new BlockPos(x,y,z),brickStairs.withProperty(BlockStairs.FACING, EnumFacing.NORTH));
                            }else if (z==zmin+roof_layer){
                                world.setBlockState(new BlockPos(x,y,z),brickStairs.withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
                            }else{
                                world.setBlockState(new BlockPos(x,y,z),bsr);
                            }
                            if(x==pos.getX() && z==pos.getZ() && y==ymax){
                                world.setBlockState(new BlockPos(x,y,z),bs_light);
                            }
                        }else if(x==pos.getX() && z==pos.getZ() && y==ymax-1){
                            world.setBlockState(new BlockPos(x,y,z),bs_light);
                        }else{
                            world.setBlockToAir(new BlockPos(x,y,z));
                        }
                    }
                    else if(x != xmin && z != zmin && x != xmax && z != zmax && y<=yroof){//empty space
                        if(world.isAirBlock(new BlockPos(x,y,z)) || removeOld) world.setBlockToAir(new BlockPos(x,y,z));
                    }
                    else{//wall
                        if((x==xmin && z==zmin) || (x==xmin && z==zmax) || (x==xmax && z==zmin) || (x==xmax && z==zmax)){
                            System.out.println("x:" + x+",y:"+y+",z:"+z);
                            world.setBlockState(new BlockPos(x,y,z),bsp);
                        }else if(x==xmax && (z>=zmin || z<=zmax)){
                            if(z==pos.getZ() && (y==ymin+1||y==ymin+2)){
                                if(y==ymin+1){
                                    world.setBlockState(new BlockPos(x,y,z),bs_door);
                                }
                            }else{
                                world.setBlockState(new BlockPos(x,y,z),bsp);
                            }
                        }else{
                            world.setBlockState(new BlockPos(x,y,z),bsw);
                        }
                    }
                }
            }
        }

    }
}
