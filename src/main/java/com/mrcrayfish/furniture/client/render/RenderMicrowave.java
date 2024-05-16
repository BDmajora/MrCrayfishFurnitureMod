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

public class RenderMicrowave
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int metadata = world.getBlockMetadata(x, y, z);
        renderer.setOverrideBlockTexture(Blocks.iron_block.getBlockTextureFromSide(0));
        RenderHelper.setRenderBounds(renderer, metadata, 0.25, 0.0, 0.0625, 0.8125, 0.5, 0.125);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.25, 0.0, 0.125, 0.8125, 0.0625, 0.75);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.25, 0.4375, 0.125, 0.8125, 0.5, 0.75);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.25, 0.0, 0.75, 0.8125, 0.5, 0.9375);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.75, 0.0, 0.125, 0.8125, 0.5, 0.75);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.1875, 0.0625, 0.125, 0.25, 0.125, 0.6875);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.1875, 0.375, 0.125, 0.25, 0.4375, 0.6875);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.1875, 0.125, 0.125, 0.25, 0.375, 0.1875);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.1875, 0.125, 0.625, 0.25, 0.375, 0.6875);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.wool.getIcon(0, 15));
        RenderHelper.setRenderBounds(renderer, metadata, 0.1875, 0.0, 0.0625, 0.25, 0.0625, 0.75);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.1875, 0.4375, 0.0625, 0.25, 0.5, 0.75);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.1875, 0.0625, 0.0625, 0.25, 0.4375, 0.125);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.1875, 0.0625, 0.6875, 0.25, 0.4375, 0.75);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.stone.getBlockTextureFromSide(0));
        RenderHelper.setRenderBounds(renderer, metadata, 0.1875, 0.0, 0.78125, 0.25, 0.5, 0.9375);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        if (ClientProxy.renderPass == 1) {
            renderer.setOverrideBlockTexture(Blocks.stained_glass_pane.getBlockTextureFromSide(0));
            RenderHelper.setRenderBounds(renderer, metadata, 0.21875, 0.125, 0.1875, 0.21875, 0.375, 0.625);
            RenderHelper.renderBlock(renderer, block, x, y, z);
        }
        renderer.setOverrideBlockTexture(Blocks.stone.getBlockTextureFromSide(0));
        RenderHelper.setRenderBounds(renderer, metadata, 0.265625, 0.0625, 0.1875, 0.625, 0.125, 0.640625);
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

