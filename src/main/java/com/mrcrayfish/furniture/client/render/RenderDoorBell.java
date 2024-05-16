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

public class RenderDoorBell
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int metadata;
        int temp = metadata = world.getBlockMetadata(x, y, z);
        if (metadata == 11) {
            metadata = 3;
        }
        if (metadata == 9) {
            metadata = 1;
        }
        if (metadata == 10) {
            metadata = 2;
        }
        if (metadata == 8) {
            metadata = 0;
        }
        double var = 0.85;
        if (temp > 3) {
            var = 0.89;
        }
        renderer.setOverrideBlockTexture(Blocks.log.getBlockTextureFromSide(2));
        RenderHelper.setRenderBounds(renderer, metadata, 0.9, 0.3, 0.4, 1.0, 0.7, 0.6);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.iron_block.getBlockTextureFromSide(0));
        RenderHelper.setRenderBounds(renderer, metadata, var, 0.45, 0.45, 0.9, 0.55, 0.55);
        RenderHelper.renderBlock(renderer, block, x, y, z);
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

