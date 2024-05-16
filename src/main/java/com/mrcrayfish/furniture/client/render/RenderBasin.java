/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 */
package com.mrcrayfish.furniture.client.render;

import com.mrcrayfish.furniture.tileentity.TileEntityBasin;
import com.mrcrayfish.furniture.util.RenderHelper;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class RenderBasin
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int metadata = world.getBlockMetadata(x, y, z);
        renderer.setOverrideBlockTexture(Blocks.quartz_block.getBlockTextureFromSide(1));
        RenderHelper.setRenderBounds(renderer, metadata, 0.5, 0.0, 0.2, 1.0, 0.6875, 0.8);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.0, 0.6875, 0.0, 1.0, 0.75, 1.0);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.75, 0.75, 0.0, 1.0, 1.0, 1.0);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.0, 0.75, 0.0, 0.75, 1.0, 0.125);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.0, 0.75, 0.875, 0.75, 1.0, 1.0);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.0, 0.75, 0.125, 0.125, 1.0, 0.875);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.stained_hardened_clay.getIcon(0, 9));
        RenderHelper.setRenderBounds(renderer, metadata, 0.8125, 1.0, 0.4375, 0.9375, 1.25, 0.5625);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.5625, 1.25, 0.4375, 0.9375, 1.375, 0.5625);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.8125, 1.0, 0.125, 0.9375, 1.125, 0.25);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.8125, 1.0, 0.75, 0.9375, 1.125, 0.875);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.stone.getBlockTextureFromSide(0));
        RenderHelper.setRenderBounds(renderer, metadata, 0.5625, 1.23, 0.4375, 0.6875, 1.25, 0.5625);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.clearOverrideBlockTexture();
        TileEntityBasin tileEntityBasin = (TileEntityBasin)world.getTileEntity(x, y, z);
        if (tileEntityBasin.hasWater()) {
            Tessellator tessellator = Tessellator.instance;
            tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
            float f = 1.0f;
            int l1 = block.colorMultiplier(world, x, y, z);
            float f1 = (float)(l1 >> 16 & 0xFF) / 255.0f;
            float f2 = (float)(l1 >> 8 & 0xFF) / 255.0f;
            float f3 = (float)(l1 & 0xFF) / 255.0f;
            if (EntityRenderer.anaglyphEnable) {
                float f5 = (f1 * 30.0f + f2 * 59.0f + f3 * 11.0f) / 100.0f;
                float f4 = (f1 * 30.0f + f2 * 70.0f) / 100.0f;
                float f6 = (f1 * 30.0f + f3 * 70.0f) / 100.0f;
                f1 = f5;
                f2 = f4;
                f3 = f6;
            }
            tessellator.setColorRGBA_F(f * f1, f * f2, f * f3, 0.8f);
            IIcon icon2 = BlockLiquid.getLiquidIcon((String)"water_still");
            RenderHelper.setRenderBounds(renderer, metadata, 0.125, 0.9, 0.125, 0.875, 0.91, 0.875);
            renderer.renderFaceYPos(block, (double)x, (double)y, (double)z, icon2);
            renderer.clearOverrideBlockTexture();
        }
        return true;
    }

    public int getRenderId() {
        return 0;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }
}

