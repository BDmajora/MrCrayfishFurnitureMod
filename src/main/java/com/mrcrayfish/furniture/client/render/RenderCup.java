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

public class RenderCup
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        if (ClientProxy.renderPass == 1) {
            renderer.setOverrideBlockTexture(Blocks.stained_glass_pane.getBlockTextureFromSide(0));
            renderer.setRenderBounds(0.34375, 0.0, 0.34375, 0.375, 0.4375, 0.65625);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            renderer.setRenderBounds(0.625, 0.0, 0.34375, 0.65625, 0.4375, 0.65625);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            renderer.setRenderBounds(0.375, 0.0, 0.34375, 0.625, 0.4375, 0.375);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            renderer.setRenderBounds(0.375, 0.0, 0.625, 0.625, 0.4375, 0.65625);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            renderer.setRenderBounds(0.375, 0.0, 0.375, 0.625, 0.03125, 0.625);
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

