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

public class RenderLamp
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        renderer.setOverrideBlockTexture(Blocks.wool.getIcon(0, 0));
        renderer.setRenderBounds((double)0.3f, (double)0.85f, (double)0.3f, (double)0.7f, (double)0.9f, (double)0.7f);
        renderer.renderStandardBlock(block, x, y, z);
        renderer.setRenderBounds((double)0.2f, (double)0.8f, (double)0.2f, (double)0.8f, (double)0.85f, (double)0.8f);
        renderer.renderStandardBlock(block, x, y, z);
        renderer.setRenderBounds((double)0.15f, (double)0.7f, (double)0.15f, (double)0.85f, (double)0.8f, (double)0.85f);
        renderer.renderStandardBlock(block, x, y, z);
        renderer.setRenderBounds((double)0.1f, 0.5, (double)0.1f, (double)0.9f, (double)0.7f, (double)0.9f);
        renderer.renderStandardBlock(block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.obsidian.getBlockTextureFromSide(0));
        renderer.setRenderBounds((double)0.45f, 0.0, (double)0.45f, (double)0.55f, (double)0.6f, (double)0.55f);
        renderer.renderStandardBlock(block, x, y, z);
        renderer.setRenderBounds(0.25, 0.0, 0.25, 0.75, 0.3125, 0.75);
        renderer.renderStandardBlock(block, x, y, z);
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

