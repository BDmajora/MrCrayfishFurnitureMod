/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.IBlockAccess
 */
package com.mrcrayfish.furniture.client.render;

import com.mrcrayfish.furniture.util.RenderHelper;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class RenderTap
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int metadata = world.getBlockMetadata(x, y, z);
        renderer.setOverrideBlockTexture(Blocks.stone_slab.getBlockTextureFromSide(1));
        renderer.setRenderBounds(0.4375, 0.0, 0.4375, 0.5625, (double)0.8f, 0.5625);
        renderer.renderStandardBlock(block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.iron_block.getBlockTextureFromSide(1));
        renderer.setRenderBounds(0.46875, (double)0.9f, 0.46875, 0.53125, (double)0.95f, 0.53125);
        renderer.renderStandardBlock(block, x, y, z);
        renderer.setRenderBounds((double)0.38875f, (double)0.95f, 0.46875, (double)0.61125f, 1.0, 0.53125);
        renderer.renderStandardBlock(block, x, y, z);
        renderer.setRenderBounds(0.46875, (double)0.95f, (double)0.38875f, 0.53125, 1.0, (double)0.61125f);
        renderer.renderStandardBlock(block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.stone_slab.getBlockTextureFromSide(1));
        RenderHelper.setRenderBounds(renderer, metadata, 0.125, 0.8, 0.4375, 0.5625, 0.9, 0.5625);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.125, 0.7, 0.4375, 0.25, 0.8, 0.5625);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.clearOverrideBlockTexture();
        return true;
    }

    public int getRenderId() {
        return 0;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }
}

