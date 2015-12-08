package com.ckhgame.villagebento.renderer.block;

import org.lwjgl.opengl.GL11;

import com.ckhgame.villagebento.block.BlockCustom;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;

public class RenderBlockCustom extends RenderBlockBase {
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		
		BlockCustom blockCustom = (BlockCustom)block;
		
		GL11.glPushMatrix();
		GL11.glTranslatef(-0.5F, -0.6F, -0.5F);
		TileEntityRendererDispatcher.instance.renderTileEntityAt(blockCustom.getTileEntityBlockCustom(), 0.0D, 0.0D, 0.0D, 0.0F);
		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

}