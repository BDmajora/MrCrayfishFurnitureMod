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

import com.mrcrayfish.furniture.blocks.BlockPresent;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class RenderPresent
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        renderer.setOverrideBlockTexture(BlockPresent.stringIcon);
        renderer.renderCrossedSquares(block, x, y, z);
        int metadata = world.getBlockMetadata(x, y, z);
        if (metadata == 0) {
            renderer.overrideBlockTexture = Blocks.wool.getIcon(1, 14);
        }
        if (metadata == 1) {
            renderer.overrideBlockTexture = Blocks.wool.getIcon(1, 13);
        }
        renderer.setRenderBounds(0.25, 0.0, 0.25, 0.75, (double)0.35f, 0.75);
        renderer.renderStandardBlock(block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.gold_block.getBlockTextureFromSide(0));
        renderer.setRenderBounds((double)0.45f, 0.0, (double)0.24f, (double)0.55f, (double)0.36f, (double)0.76f);
        renderer.renderStandardBlock(block, x, y, z);
        renderer.setRenderBounds((double)0.24f, 0.0, (double)0.45f, (double)0.76f, (double)0.36f, (double)0.55f);
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

