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

public class RenderFireAlarm
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        if (world.getBlock(x, y, z) == MrCrayfishFurnitureMod.fireAlarmOn) {
            renderer.setOverrideBlockTexture(Blocks.wool.getIcon(1, 14));
            renderer.setRenderBounds((double)0.4f, 0.875, (double)0.4f, (double)0.4625f, (double)0.9f, (double)0.4625f);
            renderer.renderStandardBlock(block, x, y, z);
        } else {
            renderer.setOverrideBlockTexture(Blocks.wool.getIcon(1, 6));
            renderer.setRenderBounds((double)0.4f, 0.875, (double)0.4f, (double)0.4625f, (double)0.9f, (double)0.4625f);
            renderer.renderStandardBlock(block, x, y, z);
        }
        renderer.setOverrideBlockTexture(Blocks.quartz_block.getBlockTextureFromSide(0));
        renderer.setRenderBounds((double)0.35f, 0.9375, (double)0.35f, (double)0.65f, 1.0, (double)0.65f);
        renderer.renderStandardBlock(block, x, y, z);
        renderer.setRenderBounds((double)0.38f, (double)0.9f, (double)0.38f, (double)0.62f, 0.9375, (double)0.62f);
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

