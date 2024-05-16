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
import com.mrcrayfish.furniture.proxy.ClientProxy;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class RenderCeilingLight
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        renderer.setOverrideBlockTexture(Blocks.obsidian.getBlockTextureFromSide(0));
        renderer.setRenderBounds((double)0.4f, 0.9375, (double)0.4f, (double)0.6f, 1.0, (double)0.6f);
        renderer.renderStandardBlock(block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.stone.getBlockTextureFromSide(0));
        renderer.setRenderBounds((double)0.45f, 0.75, (double)0.45f, (double)0.55f, 0.9375, (double)0.55f);
        renderer.renderStandardBlock(block, x, y, z);
        renderer.setRenderBounds((double)0.4f, 0.75, (double)0.4f, (double)0.6f, 0.8125, (double)0.6f);
        renderer.renderStandardBlock(block, x, y, z);
        if (ClientProxy.renderPass == 1 && world.getBlock(x, y, z) == MrCrayfishFurnitureMod.ceilingLightOn) {
            renderer.setOverrideBlockTexture(MrCrayfishFurnitureMod.yellowGlow.getBlockTextureFromSide(0));
            renderer.setRenderBounds((double)0.35f, (double)0.45f, (double)0.35f, (double)0.65f, (double)0.8f, (double)0.65f);
            renderer.renderStandardBlock(block, x, y, z);
        } else if (world.getBlock(x, y, z) == MrCrayfishFurnitureMod.ceilingLightOn) {
            renderer.setOverrideBlockTexture(MrCrayfishFurnitureMod.whiteGlass.getBlockTextureFromSide(0));
            renderer.setRenderBounds((double)0.4f, 0.5, (double)0.4f, (double)0.6f, 0.75, (double)0.6f);
            renderer.renderStandardBlock(block, x, y, z);
        } else if (ClientProxy.renderPass == 1) {
            renderer.setOverrideBlockTexture(MrCrayfishFurnitureMod.whiteGlass.getBlockTextureFromSide(0));
            renderer.setRenderBounds((double)0.4f, 0.5, (double)0.4f, (double)0.6f, 0.75, (double)0.6f);
            renderer.renderStandardBlock(block, x, y, z);
        }
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

