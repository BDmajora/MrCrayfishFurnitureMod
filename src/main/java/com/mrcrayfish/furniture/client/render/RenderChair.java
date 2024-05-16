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

public class RenderChair
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int metadata = world.getBlockMetadata(x, y, z);
        if (block == MrCrayfishFurnitureMod.chairWood) {
            renderer.setOverrideBlockTexture(Blocks.planks.getBlockTextureFromSide(1));
        } else {
            renderer.setOverrideBlockTexture(Blocks.cobblestone.getBlockTextureFromSide(1));
        }
        renderer.setRenderBounds(0.1, 0.0, 0.1, 0.2, 0.5, 0.2);
        renderer.renderStandardBlock(block, x, y, z);
        renderer.setRenderBounds(0.8, 0.0, 0.8, 0.9, 0.5, 0.9);
        renderer.renderStandardBlock(block, x, y, z);
        renderer.setRenderBounds(0.8, 0.0, 0.1, 0.9, 0.5, 0.2);
        renderer.renderStandardBlock(block, x, y, z);
        renderer.setRenderBounds(0.1, 0.0, 0.8, 0.2, 0.5, 0.9);
        renderer.renderStandardBlock(block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.8, 0.6, 0.1, 0.9, 1.2, 0.9);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        if (block == MrCrayfishFurnitureMod.chairWood) {
            renderer.setOverrideBlockTexture(Blocks.log.getBlockTextureFromSide(2));
        } else {
            renderer.setOverrideBlockTexture(Blocks.stone.getBlockTextureFromSide(1));
        }
        renderer.setRenderBounds(0.1, 0.5, 0.1, 0.9, (double)0.6f, 0.9);
        renderer.renderStandardBlock(block, x, y, z);
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

