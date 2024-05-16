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

import com.mrcrayfish.furniture.blocks.BlockCounter;
import com.mrcrayfish.furniture.util.RenderHelper;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class RenderCounter
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int metadata = world.getBlockMetadata(x, y, z);
        renderer.setOverrideBlockTexture(Blocks.quartz_block.getBlockTextureFromSide(0));
        if (RenderHelper.getBlock(world, x, y, z, metadata, 1) instanceof BlockCounter) {
            if (RenderHelper.getRotation(world, x, y, z, metadata, 1) == 2) {
                RenderHelper.setRenderBounds(renderer, metadata, 0.0, 0.0, 0.125, 1.0, 0.875, 1.0);
                RenderHelper.renderBlock(renderer, block, x, y, z);
            } else if (RenderHelper.getRotation(world, x, y, z, metadata, 1) == 3) {
                RenderHelper.setRenderBounds(renderer, metadata, 0.0, 0.0, 0.0, 1.0, 0.875, 0.875);
                RenderHelper.renderBlock(renderer, block, x, y, z);
            }
        }
        if (RenderHelper.getBlock(world, x, y, z, metadata, 0) instanceof BlockCounter) {
            if (RenderHelper.getRotation(world, x, y, z, metadata, 0) == 2 && !(RenderHelper.getBlock(world, x, y, z, metadata, 2) instanceof BlockCounter)) {
                RenderHelper.setRenderBounds(renderer, metadata, 0.125, 0.0, 0.125, 1.0, 0.875, 1.0);
                RenderHelper.renderBlock(renderer, block, x, y, z);
            } else if (RenderHelper.getRotation(world, x, y, z, metadata, 0) == 3 && !(RenderHelper.getBlock(world, x, y, z, metadata, 3) instanceof BlockCounter)) {
                RenderHelper.setRenderBounds(renderer, metadata, 0.125, 0.0, 0.0, 1.0, 0.875, 0.875);
                RenderHelper.renderBlock(renderer, block, x, y, z);
            } else {
                RenderHelper.setRenderBounds(renderer, metadata, 0.125, 0.0, 0.0, 1.0, 0.875, 1.0);
                RenderHelper.renderBlock(renderer, block, x, y, z);
            }
        } else {
            RenderHelper.setRenderBounds(renderer, metadata, 0.125, 0.0, 0.0, 1.0, 0.875, 1.0);
            RenderHelper.renderBlock(renderer, block, x, y, z);
        }
        renderer.setOverrideBlockTexture(Blocks.stained_hardened_clay.getIcon(0, 9));
        renderer.setRenderBounds(0.0, 0.875, 0.0, 1.0, 1.0, 1.0);
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

