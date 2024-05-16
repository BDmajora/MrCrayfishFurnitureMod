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
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class RenderHedge
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        renderer.setRenderBounds(0.1875, 0.0, 0.1875, 0.8125, 1.0, 0.8125);
        renderer.renderStandardBlock(block, x, y, z);
        if (world.getBlock(x + 1, y, z) == MrCrayfishFurnitureMod.hedge | world.getBlock(x + 1, y, z) == Blocks.fence_gate | world.getBlock(x + 1, y, z).isNormalCube()) {
            renderer.setRenderBounds(0.8125, 0.0, 0.1875, 1.0, 1.0, 0.8125);
            renderer.renderStandardBlock(block, x, y, z);
        }
        if (world.getBlock(x - 1, y, z) == MrCrayfishFurnitureMod.hedge | world.getBlock(x - 1, y, z) == Blocks.fence_gate | world.getBlock(x - 1, y, z).isNormalCube()) {
            renderer.setRenderBounds(0.0, 0.0, 0.1875, 0.1875, 1.0, 0.8125);
            renderer.renderStandardBlock(block, x, y, z);
        }
        if (world.getBlock(x, y, z + 1) == MrCrayfishFurnitureMod.hedge | world.getBlock(x, y, z + 1) == Blocks.fence_gate | world.getBlock(x, y, z + 1).isNormalCube()) {
            renderer.setRenderBounds(0.1875, 0.0, 0.8125, 0.8125, 1.0, 1.0);
            renderer.renderStandardBlock(block, x, y, z);
        }
        if (world.getBlock(x, y, z - 1) == MrCrayfishFurnitureMod.hedge | world.getBlock(x, y, z - 1) == Blocks.fence_gate | world.getBlock(x, y, z - 1).isNormalCube()) {
            renderer.setRenderBounds(0.1875, 0.0, 0.0, 0.8125, 1.0, 0.1875);
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

