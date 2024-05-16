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

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.util.RenderHelper;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class RenderShower
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int metadata = world.getBlockMetadata(x, y, z);
        renderer.setOverrideBlockTexture(Blocks.quartz_block.getBlockTextureFromSide(1));
        if (block == MrCrayfishFurnitureMod.showerBottom) {
            RenderHelper.setRenderBounds(renderer, metadata, 0.0, 0.0, 0.0, 1.0, 0.0625, 1.0);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            RenderHelper.setRenderBounds(renderer, metadata, 0.0, 0.0625, 0.0, 0.0625, 1.0, 0.0625);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            RenderHelper.setRenderBounds(renderer, metadata, 0.9375, 0.0625, 0.0, 1.0, 1.0, 0.0625);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            RenderHelper.setRenderBounds(renderer, metadata, 0.9375, 0.0625, 0.9375, 1.0, 1.0, 1.0);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            RenderHelper.setRenderBounds(renderer, metadata, 0.0, 0.0625, 0.9375, 0.0625, 1.0, 1.0);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            renderer.clearOverrideBlockTexture();
        } else {
            RenderHelper.setRenderBounds(renderer, metadata, 0.0, 0.9375, 0.0, 1.0, 1.0, 0.0625);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            RenderHelper.setRenderBounds(renderer, metadata, 0.0, 0.9375, 0.9375, 1.0, 1.0, 1.0);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            RenderHelper.setRenderBounds(renderer, metadata, 0.0, 0.9375, 0.0625, 0.0625, 1.0, 0.9375);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            RenderHelper.setRenderBounds(renderer, metadata, 0.9375, 0.9375, 0.0625, 1.0, 1.0, 0.9375);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            RenderHelper.setRenderBounds(renderer, metadata, 0.0, 0.0, 0.0, 0.0625, 0.9375, 0.0625);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            RenderHelper.setRenderBounds(renderer, metadata, 0.9375, 0.0, 0.0, 1.0, 0.9375, 0.0625);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            RenderHelper.setRenderBounds(renderer, metadata, 0.9375, 0.0, 0.9375, 1.0, 0.9375, 1.0);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            RenderHelper.setRenderBounds(renderer, metadata, 0.0, 0.0, 0.9375, 0.0625, 0.9375, 1.0);
            RenderHelper.renderBlock(renderer, block, x, y, z);
        }
        renderer.setOverrideBlockTexture(Blocks.glass.getBlockTextureFromSide(0));
        RenderHelper.setRenderBounds(renderer, metadata, 0.03125, 0.0625, 0.03125, 0.96875, 1.0, 0.03125);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.03125, 0.0625, 0.96875, 0.96875, 1.0, 0.96875);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.96875, 0.0625, 0.03125, 0.96875, 1.0, 0.96875);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.clearOverrideBlockTexture();
        return true;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return -1;
    }
}

