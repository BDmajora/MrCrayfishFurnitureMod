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
import com.mrcrayfish.furniture.blocks.BlockTable;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class RenderTable
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        boolean forward = world.getBlock(x + 1, y, z) instanceof BlockTable;
        boolean back = world.getBlock(x - 1, y, z) instanceof BlockTable;
        boolean left = world.getBlock(x, y, z - 1) instanceof BlockTable;
        boolean right = world.getBlock(x, y, z + 1) instanceof BlockTable;
        if (forward && right && !left && !back) {
            renderer.setRenderBounds(0.175, 0.0, 0.175, 0.425, 0.9, 0.425);
            renderer.renderStandardBlock(block, x, y, z);
        }
        if (forward && !right && left && !back) {
            renderer.setRenderBounds(0.175, 0.0, 0.575, 0.425, 0.9, 0.825);
            renderer.renderStandardBlock(block, x, y, z);
        }
        if (!forward && right && !left && back) {
            renderer.setRenderBounds(0.575, 0.0, 0.175, 0.825, 0.9, 0.425);
            renderer.renderStandardBlock(block, x, y, z);
        }
        if (!forward && !right && left && back) {
            renderer.setRenderBounds(0.575, 0.0, 0.575, 0.825, 0.9, 0.825);
            renderer.renderStandardBlock(block, x, y, z);
        }
        if (forward && !right && !left && !back) {
            renderer.setRenderBounds(0.175, 0.0, 0.375, 0.425, 0.9, 0.625);
            renderer.renderStandardBlock(block, x, y, z);
        }
        if (!forward && right && !left && !back) {
            renderer.setRenderBounds(0.375, 0.0, 0.175, 0.625, 0.9, 0.425);
            renderer.renderStandardBlock(block, x, y, z);
        }
        if (!forward && !right && left && !back) {
            renderer.setRenderBounds(0.375, 0.0, 0.575, 0.625, 0.9, 0.825);
            renderer.renderStandardBlock(block, x, y, z);
        }
        if (!forward && !right && !left && back) {
            renderer.setRenderBounds(0.575, 0.0, 0.375, 0.825, 0.9, 0.625);
            renderer.renderStandardBlock(block, x, y, z);
        }
        if (!(forward || right || left || back)) {
            renderer.setRenderBounds(0.375, 0.0, 0.375, 0.625, 0.9, 0.625);
            renderer.renderStandardBlock(block, x, y, z);
        }
        if (block == MrCrayfishFurnitureMod.tableWood) {
            renderer.setOverrideBlockTexture(Blocks.log.getBlockTextureFromSide(2));
        } else {
            renderer.setOverrideBlockTexture(Blocks.stone.getBlockTextureFromSide(1));
        }
        renderer.setRenderBounds(0.0, 0.9, 0.0, 1.0, 1.0, 1.0);
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

