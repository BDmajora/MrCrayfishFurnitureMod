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

import com.mrcrayfish.furniture.proxy.ClientProxy;
import com.mrcrayfish.furniture.util.RenderHelper;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class RenderBlender
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int metadata = world.getBlockMetadata(x, y, z);
        renderer.setOverrideBlockTexture(Blocks.wool.getIcon(0, 15));
        RenderHelper.setRenderBounds(renderer, metadata, 0.3125, 0.0, 0.3125, 0.6875, 0.3125, 0.6875);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.28125, 0.875, 0.28125, 0.71875, 0.9375, 0.71875);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.stone.getBlockTextureFromSide(0));
        RenderHelper.setRenderBounds(renderer, metadata, 0.46875, 0.375, 0.15625, 0.53125, 0.4375, 0.28125);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.46875, 0.75, 0.15625, 0.53125, 0.8125, 0.28125);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.46875, 0.4375, 0.15625, 0.53125, 0.75, 0.21875);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        if (ClientProxy.renderPass == 1) {
            renderer.setOverrideBlockTexture(Blocks.stained_glass_pane.getBlockTextureFromSide(0));
            RenderHelper.setRenderBounds(renderer, metadata, 0.28125, 0.3125, 0.28125, 0.71875, 0.875, 0.71875);
            RenderHelper.renderBlock(renderer, block, x, y, z);
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

