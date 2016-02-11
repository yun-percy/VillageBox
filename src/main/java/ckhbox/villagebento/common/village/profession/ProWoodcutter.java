package ckhbox.villagebento.common.village.profession;

import ckhbox.villagebento.common.util.helper.PathHelper;
import ckhbox.villagebento.common.village.trading.TradingRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ProWoodcutter extends Profession{

	@Override
	protected void initTradingRecipeList() {
		this.tradingRecipeList.add(new TradingRecipe(
				new ItemStack[]{new ItemStack(Items.emerald,2)},
				new ItemStack(Blocks.log,1,0)));
		this.tradingRecipeList.add(new TradingRecipe(
				new ItemStack[]{new ItemStack(Items.emerald,2)},
				new ItemStack(Blocks.log,1,1)));
		this.tradingRecipeList.add(new TradingRecipe(
				new ItemStack[]{new ItemStack(Items.emerald,2)},
				new ItemStack(Blocks.log,1,2)));
		this.tradingRecipeList.add(new TradingRecipe(
				new ItemStack[]{new ItemStack(Items.emerald,2)},
				new ItemStack(Blocks.log,1,3)));
		this.tradingRecipeList.add(new TradingRecipe(
				new ItemStack[]{new ItemStack(Items.emerald,2)},
				new ItemStack(Blocks.log2,1,0)));
		this.tradingRecipeList.add(new TradingRecipe(
				new ItemStack[]{new ItemStack(Items.emerald,2)},
				new ItemStack(Blocks.log2,1,1)));
	}

	@Override
	protected void initAttributes() {
		this.maxEnegy = 100;
		this.maxProficiency = 200;
	}

	@Override
	protected void initTexture() {
		this.texture = new ResourceLocation(PathHelper.full("textures/entity/villager/woodcutter.png"));
	}

	@Override
	protected String getUnlocalized() {
		return PathHelper.full("profession.woodcutter");
	}
	
	@Override
	protected void initUpgradeOptions() {
		this.upgradeToCurentNeeds = new ItemStack[]{
				new ItemStack(Items.emerald,10),
				new ItemStack(Items.iron_ingot,8)
		};
		
		this.upgradeToNextOptionClasses = new Class[]{
				ProCarpenter.class,
				ProFlorist.class
				};
	}
	
}
