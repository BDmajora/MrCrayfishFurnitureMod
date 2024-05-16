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

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class RenderStonePath
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        if (world.getBlockMetadata(x, y, z) == 0) {
            renderer.setOverrideBlockTexture(Blocks.stone.getBlockTextureFromSide(0));
            renderer.setRenderBounds(0.0625, 0.0, 0.0625, 0.375, 0.03125, 0.375);
            renderer.renderStandardBlock(block, x, y, z);
            renderer.setRenderBounds(0.125, 0.0, 0.5, 0.375, 0.03125, 0.75);
            renderer.renderStandardBlock(block, x, y, z);
            renderer.setRenderBounds(0.0625, 0.0, 0.8125, 0.1875, 0.03125, (double)0.9275f);
            renderer.renderStandardBlock(block, x, y, z);
            renderer.setRenderBounds(0.4375, 0.0, 0.25, 0.75, 0.03125, 0.5625);
            renderer.renderStandardBlock(block, x, y, z);
            renderer.setRenderBounds(0.4375, 0.0, 0.75, 0.625, 0.03125, 0.9375);
            renderer.renderStandardBlock(block, x, y, z);
            renderer.setRenderBounds(0.625, 0.0, 0.0625, 0.75, 0.03125, 0.1875);
            renderer.renderStandardBlock(block, x, y, z);
            renderer.setRenderBounds(0.6875, 0.0, 0.625, 0.875, 0.03125, 0.8125);
            renderer.renderStandardBlock(block, x, y, z);
            renderer.setRenderBounds(0.8125, 0.0, 0.25, 0.9375, 0.03125, 0.375);
            renderer.renderStandardBlock(block, x, y, z);
        }
        if (world.getBlockMetadata(x, y, z) == 1) {
            renderer.setOverrideBlockTexture(Blocks.stone.getBlockTextureFromSide(0));
            renderer.setRenderBounds(0.1875, 0.0, 0.0625, 0.375, 0.03125, 0.25);
            renderer.renderStandardBlock(block, x, y, z);
            renderer.setRenderBounds(0.0625, 0.0, 0.375, 0.3125, 0.03125, 0.625);
            renderer.renderStandardBlock(block, x, y, z);
            renderer.setRenderBounds(0.125, 0.0, 0.6875, 0.3125, 0.03125, 0.875);
            renderer.renderStandardBlock(block, x, y, z);
            renderer.setRenderBounds(0.4375, 0.0, 0.5625, 0.5625, 0.03125, 0.75);
            renderer.renderStandardBlock(block, x, y, z);
            renderer.setRenderBounds(0.5, 0.0, 0.8125, 0.625, 0.03125, 0.9375);
            renderer.renderStandardBlock(block, x, y, z);
            renderer.setRenderBounds(0.625, 0.0, 0.125, 0.875, 0.03125, 0.375);
            renderer.renderStandardBlock(block, x, y, z);
            renderer.setRenderBounds(0.6875, 0.0, 0.4375, 0.875, 0.03125, 0.625);
            renderer.renderStandardBlock(block, x, y, z);
            renderer.setRenderBounds(0.6875, 0.0, 0.6875, 0.9375, 0.03125, 0.9375);
            renderer.renderStandardBlock(block, x, y, z);
        }
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

