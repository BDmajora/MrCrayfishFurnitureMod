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

public class RenderBedsideCabinet
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int metadata = world.getBlockMetadata(x, y, z);
        renderer.setOverrideBlockTexture(Blocks.planks.getIcon(0, 1));
        RenderHelper.setRenderBounds(renderer, metadata, 0.0, 0.125, 0.125, 0.0625, 0.4375, 0.875);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.0, 0.5625, 0.125, 0.0625, 0.875, 0.875);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.planks.getBlockTextureFromSide(0));
        RenderHelper.setRenderBounds(renderer, metadata, -0.03, 0.25, 0.375, 0.0, 0.3125, 0.625);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, -0.03, 0.6875, 0.375, 0.0, 0.75, 0.625);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.planks.getBlockTextureFromSide(0));
        renderer.setRenderBounds(0.0625, (double)0.1f, 0.0625, 0.9375, (double)0.9f, 0.9375);
        renderer.renderStandardBlock(block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.log.getBlockTextureFromSide(2));
        renderer.setRenderBounds(0.0, 0.0, 0.0, 1.0, (double)0.1f, 1.0);
        renderer.renderStandardBlock(block, x, y, z);
        renderer.setRenderBounds(0.0, (double)0.9f, 0.0, 1.0, 1.0, 1.0);
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

