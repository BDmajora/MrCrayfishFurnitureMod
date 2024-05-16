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

public class RenderComputer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int metadata = world.getBlockMetadata(x, y, z);
        renderer.setOverrideBlockTexture(Blocks.iron_block.getBlockTextureFromSide(0));
        RenderHelper.setRenderBounds(renderer, metadata, 0.3, 0.0, 0.125, 0.9375, 0.3, 0.875);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.gravel.getBlockTextureFromSide(0));
        RenderHelper.setRenderBounds(renderer, metadata, 0.0625, 0.0, 0.0625, 0.2375, 0.0625, 0.9375);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.iron_door.getBlockTextureFromSide(0));
        RenderHelper.setRenderBounds(renderer, metadata, 0.25, 0.4, 0.125, 0.4, 0.4625, 0.875);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.25, 0.9375, 0.125, 0.4, 1.0, 0.875);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.25, 0.46255, 0.125, 0.4, 0.9375, 0.1875);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.25, 0.4625, 0.8125, 0.4, 0.9375, 0.875);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.quartz_block.getBlockTextureFromSide(0));
        RenderHelper.setRenderBounds(renderer, metadata, 0.4, 0.4625, 0.1875, 0.875, 0.9375, 0.8125);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.wool.getIcon(0, 15));
        RenderHelper.setRenderBounds(renderer, metadata, 0.3, 0.4625, 0.1875, 0.4, 0.9375, 0.8125);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.stone.getIcon(0, 15));
        renderer.setRenderBounds((double)0.4f, (double)0.3f, (double)0.4f, (double)0.6f, (double)0.4625f, (double)0.6f);
        renderer.renderStandardBlock(block, x, y, z);
        renderer.setRenderBounds((double)0.35f, (double)0.3f, (double)0.35f, (double)0.65f, (double)0.35f, (double)0.65f);
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

