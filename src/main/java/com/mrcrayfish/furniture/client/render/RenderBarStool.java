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

public class RenderBarStool
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int metadata = world.getBlockMetadata(x, y, z);
        renderer.setOverrideBlockTexture(Blocks.wool.getIcon(0, 15 - metadata));
        renderer.setRenderBounds(0.21875, 0.75, 0.21875, 0.78125, 0.78125, 0.78125);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.stained_hardened_clay.getIcon(0, 9));
        renderer.setRenderBounds(0.1875, 0.5625, 0.1875, 0.8125, 0.75, 0.8125);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.quartz_block.getBlockTextureFromSide(0));
        renderer.setRenderBounds(0.25, 0.0, 0.25, 0.375, 0.5625, 0.375);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setRenderBounds(0.625, 0.0, 0.25, 0.75, 0.5625, 0.375);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setRenderBounds(0.25, 0.0, 0.625, 0.375, 0.5625, 0.75);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setRenderBounds(0.625, 0.0, 0.625, 0.75, 0.5625, 0.75);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.stone.getBlockTextureFromSide(0));
        renderer.setRenderBounds(0.28125, 0.25, 0.375, 0.34375, 0.3125, 0.625);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setRenderBounds(0.65625, 0.25, 0.375, 0.71875, 0.3125, 0.625);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setRenderBounds(0.375, 0.25, 0.65625, 0.625, 0.3125, 0.71875);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setRenderBounds(0.375, 0.25, 0.28125, 0.625, 0.3125, 0.34375);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.clearOverrideBlockTexture();
        return true;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return 0;
    }
}

