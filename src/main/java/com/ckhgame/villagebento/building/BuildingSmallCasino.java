package com.ckhgame.villagebento.building;

import com.ckhgame.villagebento.block.ModBlocks;
import com.ckhgame.villagebento.building.builder.BuildingBuilder;
import com.ckhgame.villagebento.config.ConfigBuilding;
import com.ckhgame.villagebento.item.ModItems;
import com.ckhgame.villagebento.profession.ProfessionCasinoManager;

import net.minecraft.init.Blocks;

public class BuildingSmallCasino extends Building {

	@Override
	public String getName() {
		return "Small Casino";
	}

	@Override
	public String getDescription() {
		return "Gambling";
	}

	@Override
	protected boolean canBuild() {
		return true;
	}
	
	@Override
	protected void generateVillagers(BuildingBuilder bb) {
		// TODO Auto-generated method stub
		//add villager
		bb.addVillager(ProfessionCasinoManager.class,0,-2);
	}

	@Override
	public int getSizeType() {
		return ConfigBuilding.SizeType_Medium;
	}

	@Override
	protected void buildBlocks(BuildingBuilder bb) {
		bb.buildBlock(-3,-1,-3,Blocks.planks,0);bb.buildBlock(-3,-1,-2,Blocks.planks,0);bb.buildBlock(-3,-1,-1,Blocks.planks,0);
		bb.buildBlock(-3,-1,0,Blocks.planks,0);bb.buildBlock(-3,-1,1,Blocks.planks,0);bb.buildBlock(-3,-1,2,Blocks.planks,0);
		bb.buildBlock(-3,-1,3,Blocks.planks,0);bb.buildBlock(-2,-1,-3,Blocks.planks,0);bb.buildBlock(-2,-1,-2,Blocks.planks,0);
		bb.buildBlock(-2,-1,-1,Blocks.planks,0);bb.buildBlock(-2,-1,0,Blocks.planks,0);bb.buildBlock(-2,-1,1,Blocks.planks,0);
		bb.buildBlock(-2,-1,2,Blocks.planks,0);bb.buildBlock(-2,-1,3,Blocks.planks,0);bb.buildBlock(-1,-1,-3,Blocks.planks,0);
		bb.buildBlock(-1,-1,-2,Blocks.planks,0);bb.buildBlock(-1,-1,0,Blocks.planks,0);bb.buildBlock(-1,-1,1,Blocks.planks,0);
		bb.buildBlock(-1,-1,2,Blocks.planks,0);bb.buildBlock(-1,-1,3,Blocks.planks,0);bb.buildBlock(0,-1,-3,Blocks.planks,0);
		bb.buildBlock(0,-1,-2,Blocks.planks,0);bb.buildBlock(0,-1,-1,Blocks.planks,0);bb.buildBlock(0,-1,0,Blocks.planks,0);
		bb.buildBlock(0,-1,1,Blocks.planks,0);bb.buildBlock(0,-1,2,Blocks.planks,0);bb.buildBlock(0,-1,3,Blocks.planks,0);
		bb.buildBlock(1,-1,-3,Blocks.planks,0);bb.buildBlock(1,-1,-2,Blocks.planks,0);bb.buildBlock(1,-1,-1,Blocks.planks,0);
		bb.buildBlock(1,-1,0,Blocks.planks,0);bb.buildBlock(1,-1,1,Blocks.planks,0);bb.buildBlock(2,-1,-3,Blocks.planks,0);
		bb.buildBlock(2,-1,-2,Blocks.planks,0);bb.buildBlock(2,-1,-1,Blocks.planks,0);bb.buildBlock(2,-1,0,Blocks.planks,0);
		bb.buildBlock(2,-1,1,Blocks.planks,0);bb.buildBlock(2,-1,2,Blocks.planks,0);bb.buildBlock(2,-1,3,Blocks.planks,0);
		bb.buildBlock(3,-1,-3,Blocks.planks,0);bb.buildBlock(3,-1,-2,Blocks.planks,0);bb.buildBlock(3,-1,-1,Blocks.planks,0);
		bb.buildBlock(3,-1,0,Blocks.planks,0);bb.buildBlock(3,-1,1,Blocks.planks,0);bb.buildBlock(3,-1,2,Blocks.planks,0);
		bb.buildBlock(3,-1,3,Blocks.planks,0);bb.buildBlock(-4,0,-4,Blocks.planks,1);bb.buildBlock(-4,0,-3,Blocks.planks,1);
		bb.buildBlock(-4,0,-2,Blocks.planks,1);bb.buildBlock(-4,0,-1,Blocks.planks,1);bb.buildBlock(-4,0,0,Blocks.planks,1);
		bb.buildBlock(-4,0,1,Blocks.planks,1);bb.buildBlock(-4,0,2,Blocks.planks,1);bb.buildBlock(-4,0,3,Blocks.planks,1);
		bb.buildBlock(-4,0,4,Blocks.planks,1);bb.buildBlock(-3,0,-4,Blocks.planks,1);bb.buildBlock(-3,0,-3,Blocks.carpet,12);
		bb.buildBlock(-3,0,-2,Blocks.carpet,14);bb.buildBlock(-3,0,-1,Blocks.carpet,12);bb.buildBlock(-3,0,0,Blocks.carpet,14);
		bb.buildBlock(-3,0,1,Blocks.carpet,12);bb.buildBlock(-3,0,2,Blocks.carpet,14);bb.buildBlock(-3,0,3,Blocks.carpet,12);
		bb.buildBlock(-3,0,4,Blocks.planks,1);bb.buildBlock(-2,0,-4,Blocks.planks,1);bb.buildBlock(-2,0,-3,Blocks.carpet,14);
		bb.buildBlock(-2,0,-2,Blocks.carpet,12);bb.buildBlock(-2,0,-1,Blocks.carpet,14);bb.buildBlock(-2,0,0,Blocks.carpet,12);
		bb.buildBlock(-2,0,1,Blocks.carpet,14);bb.buildBlock(-2,0,2,Blocks.carpet,12);bb.buildBlock(-2,0,3,Blocks.carpet,14);
		bb.buildBlock(-2,0,4,Blocks.planks,1);bb.buildBlock(-1,0,-4,Blocks.planks,1);bb.buildBlock(-1,0,-3,Blocks.planks,1);
		bb.buildBlock(-1,0,-2,Blocks.wooden_door,2);bb.buildBlock(-1,0,-1,Blocks.planks,1);bb.buildBlock(-1,0,0,Blocks.planks,1);
		bb.buildBlock(-1,0,1,Blocks.carpet,12);bb.buildBlock(-1,0,2,Blocks.carpet,14);bb.buildBlock(-1,0,3,Blocks.bed,3);
		bb.buildBlock(-1,0,4,Blocks.planks,1);bb.buildBlock(0,0,-4,Blocks.planks,1);bb.buildBlock(0,0,1,Blocks.planks,1);
		bb.buildBlock(0,0,2,Blocks.chest,4);bb.buildBlock(0,0,3,Blocks.bed,11);bb.buildBlock(0,0,4,Blocks.planks,1);bb.buildBlock(1,0,-4,Blocks.planks,1);
		bb.buildBlock(1,0,-3,Blocks.planks,1);bb.buildBlock(1,0,-2,Blocks.planks,1);bb.buildBlock(1,0,-1,Blocks.planks,1);
		bb.buildBlock(1,0,1,Blocks.planks,1);bb.buildBlock(1,0,2,Blocks.planks,1);bb.buildBlock(1,0,3,Blocks.planks,1);
		bb.buildBlock(1,0,4,Blocks.planks,1);bb.buildBlock(2,0,-4,Blocks.planks,1);bb.buildBlock(2,0,-2,ModBlocks.blockOakChair,0);
		bb.buildBlock(2,0,4,Blocks.planks,1);bb.buildBlock(3,0,-4,Blocks.planks,1);bb.buildBlock(3,0,3,ModBlocks.blockOakChair,0);
		bb.buildBlock(3,0,4,Blocks.planks,1);bb.buildBlock(4,0,-4,Blocks.planks,1);bb.buildBlock(4,0,-3,Blocks.planks,1);
		bb.buildBlock(4,0,-2,Blocks.planks,1);bb.buildBlock(4,0,-1,Blocks.wooden_door,2);bb.buildBlock(4,0,0,Blocks.planks,1);
		bb.buildBlock(4,0,1,Blocks.planks,1);bb.buildBlock(4,0,2,Blocks.planks,1);bb.buildBlock(4,0,3,Blocks.planks,1);
		bb.buildBlock(4,0,4,Blocks.planks,1);bb.buildBlock(-4,1,-4,Blocks.planks,1);bb.buildBlock(-4,1,-3,Blocks.planks,1);
		bb.buildBlock(-4,1,-2,Blocks.planks,1);bb.buildBlock(-4,1,-1,Blocks.planks,1);bb.buildBlock(-4,1,0,Blocks.planks,1);
		bb.buildBlock(-4,1,1,Blocks.planks,1);bb.buildBlock(-4,1,2,Blocks.planks,1);bb.buildBlock(-4,1,3,Blocks.planks,1);
		bb.buildBlock(-4,1,4,Blocks.planks,1);bb.buildBlock(-3,1,-4,Blocks.planks,1);bb.buildBlock(-3,1,4,Blocks.planks,1);
		bb.buildBlock(-2,1,-4,Blocks.planks,1);bb.buildBlock(-2,1,4,Blocks.planks,1);bb.buildBlock(-1,1,-4,Blocks.planks,1);
		bb.buildBlock(-1,1,-3,Blocks.planks,1);bb.buildBlock(-1,1,-2,Blocks.wooden_door,8);bb.buildBlock(-1,1,-1,Blocks.planks,1);
		bb.buildBlock(-1,1,0,Blocks.planks,1);bb.buildBlock(-1,1,4,Blocks.planks,1);bb.buildBlock(0,1,-4,Blocks.planks,1);
		bb.buildBlock(0,1,1,Blocks.planks,1);bb.buildBlock(0,1,4,Blocks.planks,1);bb.buildBlock(1,1,-4,Blocks.planks,1);
		bb.buildBlock(1,1,2,Blocks.planks,1);bb.buildBlock(1,1,3,Blocks.planks,1);bb.buildBlock(1,1,4,Blocks.planks,1);
		bb.buildBlock(2,1,-4,Blocks.planks,1);bb.buildBlock(2,1,4,Blocks.planks,1);bb.buildBlock(3,1,-4,Blocks.planks,1);
		bb.buildBlock(3,1,4,Blocks.planks,1);bb.buildBlock(4,1,-4,Blocks.planks,1);bb.buildBlock(4,1,-3,Blocks.planks,1);
		bb.buildBlock(4,1,-2,Blocks.planks,1);bb.buildBlock(4,1,-1,Blocks.wooden_door,8);bb.buildBlock(4,1,0,Blocks.planks,1);
		bb.buildBlock(4,1,1,Blocks.glass_pane,0);bb.buildBlock(4,1,2,Blocks.glass_pane,0);bb.buildBlock(4,1,3,Blocks.planks,1);
		bb.buildBlock(4,1,4,Blocks.planks,1);bb.buildBlock(-4,2,-4,Blocks.planks,1);bb.buildBlock(-4,2,-3,Blocks.planks,1);
		bb.buildBlock(-4,2,-2,Blocks.planks,1);bb.buildBlock(-4,2,-1,Blocks.planks,1);bb.buildBlock(-4,2,0,Blocks.planks,1);
		bb.buildBlock(-4,2,1,Blocks.planks,1);bb.buildBlock(-4,2,2,Blocks.planks,1);bb.buildBlock(-4,2,3,Blocks.planks,1);
		bb.buildBlock(-4,2,4,Blocks.planks,1);bb.buildBlock(-3,2,-4,Blocks.planks,1);bb.buildBlock(-3,2,4,Blocks.planks,1);
		bb.buildBlock(-2,2,-4,Blocks.planks,1);bb.buildBlock(-2,2,4,Blocks.planks,1);bb.buildBlock(-1,2,-4,Blocks.planks,1);
		bb.buildBlock(-1,2,-3,Blocks.planks,1);bb.buildBlock(-1,2,-2,Blocks.planks,1);bb.buildBlock(-1,2,-1,Blocks.planks,1);
		bb.buildBlock(-1,2,0,Blocks.planks,1);bb.buildBlock(-1,2,4,Blocks.planks,1);bb.buildBlock(0,2,-4,Blocks.planks,1);
		bb.buildBlock(0,2,1,Blocks.planks,1);bb.buildBlock(0,2,4,Blocks.planks,1);bb.buildBlock(1,2,-4,Blocks.planks,1);
		bb.buildBlock(1,2,2,Blocks.planks,1);bb.buildBlock(1,2,3,Blocks.planks,1);bb.buildBlock(1,2,4,Blocks.planks,1);
		bb.buildBlock(2,2,-4,Blocks.planks,1);bb.buildBlock(2,2,4,Blocks.planks,1);bb.buildBlock(3,2,-4,Blocks.planks,1);
		bb.buildBlock(3,2,4,Blocks.planks,1);bb.buildBlock(4,2,-4,Blocks.planks,1);bb.buildBlock(4,2,-3,Blocks.planks,1);
		bb.buildBlock(4,2,-2,Blocks.planks,1);bb.buildBlock(4,2,-1,Blocks.planks,1);bb.buildBlock(4,2,0,Blocks.planks,1);
		bb.buildBlock(4,2,1,Blocks.glass_pane,0);bb.buildBlock(4,2,2,Blocks.glass_pane,0);bb.buildBlock(4,2,3,Blocks.planks,1);
		bb.buildBlock(4,2,4,Blocks.planks,1);bb.buildBlock(-5,3,-5,Blocks.acacia_stairs,0);bb.buildBlock(-5,3,-4,Blocks.acacia_stairs,0);
		bb.buildBlock(-5,3,-3,Blocks.acacia_stairs,0);bb.buildBlock(-5,3,-2,Blocks.acacia_stairs,0);bb.buildBlock(-5,3,-1,Blocks.acacia_stairs,0);
		bb.buildBlock(-5,3,0,Blocks.acacia_stairs,0);bb.buildBlock(-5,3,1,Blocks.acacia_stairs,0);bb.buildBlock(-5,3,2,Blocks.acacia_stairs,0);
		bb.buildBlock(-5,3,3,Blocks.acacia_stairs,0);bb.buildBlock(-5,3,4,Blocks.acacia_stairs,0);bb.buildBlock(-5,3,5,Blocks.acacia_stairs,3);
		bb.buildBlock(-4,3,-5,Blocks.acacia_stairs,2);bb.buildBlock(-4,3,-4,Blocks.planks,1);bb.buildBlock(-4,3,-3,Blocks.planks,1);
		bb.buildBlock(-4,3,-2,Blocks.planks,1);bb.buildBlock(-4,3,-1,Blocks.planks,1);bb.buildBlock(-4,3,0,Blocks.planks,1);
		bb.buildBlock(-4,3,1,Blocks.planks,1);bb.buildBlock(-4,3,2,Blocks.planks,1);bb.buildBlock(-4,3,3,Blocks.planks,1);
		bb.buildBlock(-4,3,4,Blocks.planks,1);bb.buildBlock(-4,3,5,Blocks.acacia_stairs,3);bb.buildBlock(-3,3,-5,Blocks.acacia_stairs,2);
		bb.buildBlock(-3,3,-4,Blocks.planks,1);bb.buildBlock(-3,3,4,Blocks.planks,1);bb.buildBlock(-3,3,5,Blocks.acacia_stairs,3);
		bb.buildBlock(-2,3,-5,Blocks.acacia_stairs,2);bb.buildBlock(-2,3,-4,Blocks.planks,1);bb.buildBlock(-2,3,4,Blocks.planks,1);
		bb.buildBlock(-2,3,5,Blocks.acacia_stairs,3);bb.buildBlock(-1,3,-5,Blocks.acacia_stairs,2);bb.buildBlock(-1,3,-4,Blocks.planks,1);
		bb.buildBlock(-1,3,-3,Blocks.planks,1);bb.buildBlock(-1,3,-2,Blocks.planks,1);bb.buildBlock(-1,3,-1,Blocks.planks,1);
		bb.buildBlock(-1,3,0,Blocks.planks,1);bb.buildBlock(-1,3,4,Blocks.planks,1);bb.buildBlock(-1,3,5,Blocks.acacia_stairs,3);
		bb.buildBlock(0,3,-5,Blocks.acacia_stairs,2);bb.buildBlock(0,3,-4,Blocks.planks,1);bb.buildBlock(0,3,1,Blocks.planks,1);
		bb.buildBlock(0,3,4,Blocks.planks,1);bb.buildBlock(0,3,5,Blocks.acacia_stairs,3);bb.buildBlock(1,3,-5,Blocks.acacia_stairs,2);
		bb.buildBlock(1,3,-4,Blocks.planks,1);bb.buildBlock(1,3,1,Blocks.planks,1);bb.buildBlock(1,3,2,Blocks.planks,1);
		bb.buildBlock(1,3,3,Blocks.planks,1);bb.buildBlock(1,3,4,Blocks.planks,1);bb.buildBlock(1,3,5,Blocks.acacia_stairs,3);
		bb.buildBlock(2,3,-5,Blocks.acacia_stairs,2);bb.buildBlock(2,3,-4,Blocks.planks,1);bb.buildBlock(2,3,4,Blocks.planks,1);
		bb.buildBlock(2,3,5,Blocks.acacia_stairs,3);bb.buildBlock(3,3,-5,Blocks.acacia_stairs,2);bb.buildBlock(3,3,-4,Blocks.planks,1);
		bb.buildBlock(3,3,4,Blocks.planks,1);bb.buildBlock(3,3,5,Blocks.acacia_stairs,3);bb.buildBlock(4,3,-5,Blocks.acacia_stairs,2);
		bb.buildBlock(4,3,-4,Blocks.planks,1);bb.buildBlock(4,3,-3,Blocks.planks,1);bb.buildBlock(4,3,-2,Blocks.planks,1);
		bb.buildBlock(4,3,-1,Blocks.planks,1);bb.buildBlock(4,3,0,Blocks.planks,1);bb.buildBlock(4,3,1,Blocks.planks,1);
		bb.buildBlock(4,3,2,Blocks.planks,1);bb.buildBlock(4,3,3,Blocks.planks,1);bb.buildBlock(4,3,4,Blocks.planks,1);
		bb.buildBlock(4,3,5,Blocks.acacia_stairs,3);bb.buildBlock(5,3,-5,Blocks.acacia_stairs,2);bb.buildBlock(5,3,-4,Blocks.acacia_stairs,1);
		bb.buildBlock(5,3,-3,Blocks.acacia_stairs,1);bb.buildBlock(5,3,-2,Blocks.acacia_stairs,1);bb.buildBlock(5,3,-1,Blocks.acacia_stairs,1);
		bb.buildBlock(5,3,0,Blocks.acacia_stairs,1);bb.buildBlock(5,3,1,Blocks.acacia_stairs,1);bb.buildBlock(5,3,2,Blocks.acacia_stairs,5);
		bb.buildBlock(5,3,3,Blocks.acacia_stairs,5);bb.buildBlock(5,3,4,Blocks.acacia_stairs,1);bb.buildBlock(5,3,5,Blocks.acacia_stairs,3);
		bb.buildBlock(-4,4,-4,Blocks.acacia_stairs,2);bb.buildBlock(-4,4,-3,Blocks.acacia_stairs,0);bb.buildBlock(-4,4,-2,Blocks.acacia_stairs,0);
		bb.buildBlock(-4,4,-1,Blocks.acacia_stairs,0);bb.buildBlock(-4,4,0,Blocks.acacia_stairs,0);bb.buildBlock(-4,4,1,Blocks.acacia_stairs,0);
		bb.buildBlock(-4,4,2,Blocks.acacia_stairs,0);bb.buildBlock(-4,4,3,Blocks.acacia_stairs,0);bb.buildBlock(-4,4,4,Blocks.acacia_stairs,0);
		bb.buildBlock(-3,4,-4,Blocks.acacia_stairs,2);bb.buildBlock(-3,4,-3,Blocks.planks,4);bb.buildBlock(-3,4,-2,Blocks.planks,4);
		bb.buildBlock(-3,4,-1,Blocks.planks,4);bb.buildBlock(-3,4,0,Blocks.planks,4);bb.buildBlock(-3,4,1,Blocks.planks,4);
		bb.buildBlock(-3,4,2,Blocks.planks,4);bb.buildBlock(-3,4,3,Blocks.planks,4);bb.buildBlock(-3,4,4,Blocks.acacia_stairs,3);
		bb.buildBlock(-2,4,-4,Blocks.acacia_stairs,2);bb.buildBlock(-2,4,-3,Blocks.planks,4);bb.buildBlock(-2,4,-2,Blocks.planks,0);
		bb.buildBlock(-2,4,-1,Blocks.planks,0);bb.buildBlock(-2,4,0,Blocks.planks,0);bb.buildBlock(-2,4,1,Blocks.planks,0);
		bb.buildBlock(-2,4,2,Blocks.planks,0);bb.buildBlock(-2,4,3,Blocks.planks,4);bb.buildBlock(-2,4,4,Blocks.acacia_stairs,3);
		bb.buildBlock(-1,4,-4,Blocks.acacia_stairs,2);bb.buildBlock(-1,4,-3,Blocks.planks,4);bb.buildBlock(-1,4,-2,Blocks.planks,0);
		bb.buildBlock(-1,4,-1,Blocks.planks,0);bb.buildBlock(-1,4,0,Blocks.planks,0);bb.buildBlock(-1,4,1,Blocks.planks,0);
		bb.buildBlock(-1,4,2,Blocks.planks,0);bb.buildBlock(-1,4,3,Blocks.planks,4);bb.buildBlock(-1,4,4,Blocks.acacia_stairs,3);
		bb.buildBlock(0,4,-4,Blocks.acacia_stairs,2);bb.buildBlock(0,4,-3,Blocks.planks,4);bb.buildBlock(0,4,-2,Blocks.planks,0);
		bb.buildBlock(0,4,-1,Blocks.planks,0);bb.buildBlock(0,4,0,Blocks.planks,0);bb.buildBlock(0,4,1,Blocks.planks,0);
		bb.buildBlock(0,4,2,Blocks.planks,0);bb.buildBlock(0,4,3,Blocks.planks,4);bb.buildBlock(0,4,4,Blocks.acacia_stairs,3);
		bb.buildBlock(1,4,-4,Blocks.acacia_stairs,2);bb.buildBlock(1,4,-3,Blocks.planks,4);bb.buildBlock(1,4,-2,Blocks.planks,0);
		bb.buildBlock(1,4,-1,Blocks.planks,0);bb.buildBlock(1,4,0,Blocks.planks,0);bb.buildBlock(1,4,1,Blocks.planks,0);
		bb.buildBlock(1,4,2,Blocks.planks,0);bb.buildBlock(1,4,3,Blocks.planks,4);bb.buildBlock(1,4,4,Blocks.acacia_stairs,3);
		bb.buildBlock(2,4,-4,Blocks.acacia_stairs,2);bb.buildBlock(2,4,-3,Blocks.planks,4);bb.buildBlock(2,4,-2,Blocks.planks,0);
		bb.buildBlock(2,4,-1,Blocks.planks,0);bb.buildBlock(2,4,0,Blocks.planks,0);bb.buildBlock(2,4,1,Blocks.planks,0);
		bb.buildBlock(2,4,2,Blocks.planks,0);bb.buildBlock(2,4,3,Blocks.planks,4);bb.buildBlock(2,4,4,Blocks.acacia_stairs,3);
		bb.buildBlock(3,4,-4,Blocks.acacia_stairs,2);bb.buildBlock(3,4,-3,Blocks.planks,4);bb.buildBlock(3,4,-2,Blocks.planks,4);
		bb.buildBlock(3,4,-1,Blocks.planks,4);bb.buildBlock(3,4,0,Blocks.planks,4);bb.buildBlock(3,4,1,Blocks.planks,4);
		bb.buildBlock(3,4,2,Blocks.planks,4);bb.buildBlock(3,4,3,Blocks.planks,4);bb.buildBlock(3,4,4,Blocks.acacia_stairs,3);
		bb.buildBlock(4,4,-4,Blocks.acacia_stairs,1);bb.buildBlock(4,4,-3,Blocks.acacia_stairs,1);bb.buildBlock(4,4,-2,Blocks.acacia_stairs,1);
		bb.buildBlock(4,4,-1,Blocks.acacia_stairs,1);bb.buildBlock(4,4,0,Blocks.acacia_stairs,1);bb.buildBlock(4,4,1,Blocks.acacia_stairs,1);
		bb.buildBlock(4,4,2,Blocks.acacia_stairs,1);bb.buildBlock(4,4,3,Blocks.acacia_stairs,1);bb.buildBlock(4,4,4,Blocks.acacia_stairs,1);
		bb.buildBlock(-3,3,-3,Blocks.torch,3);bb.buildBlock(-3,3,-1,Blocks.torch,1);bb.buildBlock(-3,3,0,Blocks.torch,1);
		bb.buildBlock(-3,3,3,Blocks.torch,1);bb.buildBlock(-2,3,-3,Blocks.torch,3);bb.buildBlock(-2,3,0,Blocks.torch,2);
		bb.buildBlock(-1,3,1,Blocks.torch,2);bb.buildBlock(0,3,-3,Blocks.torch,1);bb.buildBlock(0,3,0,Blocks.torch,1);
		bb.buildBlock(0,3,2,Blocks.torch,2);bb.buildBlock(0,3,3,Blocks.torch,2);bb.buildBlock(2,3,1,Blocks.torch,1);
		bb.buildBlock(2,3,3,Blocks.torch,1);bb.buildBlock(3,3,-3,Blocks.torch,2);bb.buildBlock(3,3,0,Blocks.torch,2);
		bb.addEntityPainting(-1,2,4,2,"Pool");
		bb.addEntityItemFrame(4,1,0,3,ModBlocks.blockSmallCasino,0);
		bb.addEntityItemFrame(1,1,2,3,ModItems.itemVillageCurrency,0);
		bb.addEntityItemFrame(1,2,2,3,ModItems.itemVillageCurrency,0);
	}

}
