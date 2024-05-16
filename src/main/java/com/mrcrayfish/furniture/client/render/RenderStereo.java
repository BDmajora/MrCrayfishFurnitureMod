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

public class RenderStereo
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int metadata = world.getBlockMetadata(x, y, z);
        double yOffset = -0.3625f;
        renderer.setOverrideBlockTexture(Blocks.wool.getIcon(0, 15));
        RenderHelper.setRenderBounds(renderer, metadata, 0.35, 0.0, 0.0625, 0.65, 0.45, 0.3);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.35, 0.0, 0.7, 0.65, 0.45, 0.9375);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.log.getBlockTextureFromSide(2));
        RenderHelper.setRenderBounds(renderer, metadata, 0.35, 0.0, 0.3, 0.65, 0.45, 0.7);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.planks.getBlockTextureFromSide(0));
        RenderHelper.setRenderBounds(renderer, metadata, 0.3, 0.1, 0.35, 0.35, 0.35, 0.65);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.stone.getBlockTextureFromSide(0));
        RenderHelper.setRenderBounds(renderer, metadata, 0.46875, (double)0.9875f + yOffset, 0.65625, 0.53125, (double)1.05f + yOffset, 0.71875);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.46875, (double)0.925f + yOffset, 0.59375, 0.53125, (double)0.9875f + yOffset, 0.65625);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.46875, (double)0.8625f + yOffset, 0.53125, 0.53125, (double)0.925f + yOffset, 0.59375);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.46875, (double)0.8f + yOffset, 0.46875, 0.53125, (double)0.8625f + yOffset, 0.53125);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.46875, (double)0.8625f + yOffset, 0.40625, 0.53125, (double)0.925f + yOffset, 0.46875);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.46875, (double)0.925f + yOffset, 0.34375, 0.53125, (double)0.9875f + yOffset, 0.40625);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.46875, (double)0.9875f + yOffset, 0.28125, 0.53125, (double)1.05f + yOffset, 0.34375);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.46875, (double)0.9875f + yOffset, 0.28125, 0.53125, (double)1.05f + yOffset, 0.34375);
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

