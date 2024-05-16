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
import com.mrcrayfish.furniture.blocks.BlockCoffeeTable;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class RenderCoffeeTable
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        if (world.getBlock(x, y, z) == MrCrayfishFurnitureMod.coffeeTableWood) {
            renderer.setOverrideBlockTexture(Blocks.log.getBlockTextureFromSide(2));
        } else {
            renderer.setOverrideBlockTexture(Blocks.stone.getBlockTextureFromSide(0));
        }
        renderer.setRenderBounds(0.0, (double)0.4f, 0.0, 1.0, 0.5, 1.0);
        renderer.renderStandardBlock(block, x, y, z);
        boolean forward = world.getBlock(x + 1, y, z) instanceof BlockCoffeeTable;
        boolean back = world.getBlock(x - 1, y, z) instanceof BlockCoffeeTable;
        boolean left = world.getBlock(x, y, z - 1) instanceof BlockCoffeeTable;
        boolean right = world.getBlock(x, y, z + 1) instanceof BlockCoffeeTable;
        if (world.getBlock(x, y, z) == MrCrayfishFurnitureMod.coffeeTableWood) {
            renderer.setOverrideBlockTexture(Blocks.planks.getBlockTextureFromSide(0));
        } else {
            renderer.setOverrideBlockTexture(Blocks.cobblestone.getBlockTextureFromSide(0));
        }
        if (!back) {
            if (!left) {
                renderer.setRenderBounds(0.0, 0.0, 0.0, (double)0.1f, (double)0.4f, (double)0.1f);
                renderer.renderStandardBlock(block, x, y, z);
            }
            if (!right) {
                renderer.setRenderBounds(0.0, 0.0, (double)0.9f, (double)0.1f, (double)0.4f, 1.0);
                renderer.renderStandardBlock(block, x, y, z);
            }
        }
        if (!forward) {
            if (!left) {
                renderer.setRenderBounds((double)0.9f, 0.0, 0.0, 1.0, (double)0.4f, (double)0.1f);
                renderer.renderStandardBlock(block, x, y, z);
            }
            if (!right) {
                renderer.setRenderBounds((double)0.9f, 0.0, (double)0.9f, 1.0, (double)0.4f, 1.0);
                renderer.renderStandardBlock(block, x, y, z);
            }
        }
        if (!left) {
            if (!forward) {
                renderer.setRenderBounds((double)0.9f, 0.0, 0.0, 1.0, (double)0.4f, (double)0.1f);
                renderer.renderStandardBlock(block, x, y, z);
            }
            if (!back) {
                renderer.setRenderBounds(0.0, 0.0, 0.0, (double)0.1f, (double)0.4f, (double)0.1f);
                renderer.renderStandardBlock(block, x, y, z);
            }
        }
        if (!right) {
            if (!forward) {
                renderer.setRenderBounds((double)0.9f, 0.0, (double)0.9f, 1.0, (double)0.4f, 1.0);
                renderer.renderStandardBlock(block, x, y, z);
            }
            if (!back) {
                renderer.setRenderBounds(0.0, 0.0, (double)0.9f, (double)0.1f, (double)0.4f, 1.0);
                renderer.renderStandardBlock(block, x, y, z);
            }
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

