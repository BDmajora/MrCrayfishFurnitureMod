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

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.tileentity.TileEntityBath;
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

public class RenderBath
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int metadata = world.getBlockMetadata(x, y, z);
        TileEntityBath tileEntityBath = (TileEntityBath)world.getTileEntity(x, y, z);
        if (tileEntityBath.hasWater()) {
            double range = 0.006999999999999999;
            double waterPercentage = (double)((TileEntityBath)world.getTileEntity(x, y, z)).getWaterLevel() / 16.0 * 100.0;
            double height = range * waterPercentage + 0.125;
            Tessellator tessellator = Tessellator.instance;
            tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
            float f = 1.0f;
            int l = block.colorMultiplier(world, x, y, z);
            float f1 = (float)(l >> 16 & 0xFF) / 255.0f;
            float f2 = (float)(l >> 8 & 0xFF) / 255.0f;
            float f3 = (float)(l & 0xFF) / 255.0f;
            if (EntityRenderer.anaglyphEnable) {
                float f5 = (f1 * 30.0f + f2 * 59.0f + f3 * 11.0f) / 100.0f;
                float f4 = (f1 * 30.0f + f2 * 70.0f) / 100.0f;
                float f6 = (f1 * 30.0f + f3 * 70.0f) / 100.0f;
                f1 = f5;
                f2 = f4;
                f3 = f6;
            }
            tessellator.setColorRGBA_F(f * f1, f * f2, f * f3, 0.8f);
            renderer.clearOverrideBlockTexture();
            IIcon icon2 = BlockLiquid.getLiquidIcon((String)"water_still");
            if (block == MrCrayfishFurnitureMod.bath1) {
                RenderHelper.setRenderBounds(renderer, metadata, 0.125, 0.125, 0.125, 1.0, height, 0.875);
            } else {
                RenderHelper.setRenderBounds(renderer, metadata, 0.0, 0.125, 0.125, 0.875, height, 0.875);
            }
            renderer.renderFaceYPos(block, (double)x, (double)y, (double)z, icon2);
        }
        renderer.setOverrideBlockTexture(Blocks.quartz_block.getBlockTextureFromSide(0));
        renderer.setRenderBounds(0.0, 0.0, 0.0, 1.0, 0.125, 1.0);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.0, 0.125, 0.0, 1.0, 0.875, 0.125);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.0, 0.125, 0.875, 1.0, 0.875, 1.0);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        if (block == MrCrayfishFurnitureMod.bath1) {
            RenderHelper.setRenderBounds(renderer, metadata, 0.0, 0.125, 0.125, 0.125, 0.875, 0.875);
            RenderHelper.renderBlock(renderer, block, x, y, z);
        } else {
            RenderHelper.setRenderBounds(renderer, metadata, 0.75, 0.125, 0.125, 1.0, 0.875, 0.875);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            renderer.setOverrideBlockTexture(Blocks.stained_hardened_clay.getIcon(0, 9));
            RenderHelper.setRenderBounds(renderer, metadata, 0.8125, 0.875, 0.4375, 0.9375, 1.125, 0.5625);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            RenderHelper.setRenderBounds(renderer, metadata, 0.5625, 1.125, 0.4375, 0.9375, 1.25, 0.5625);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            RenderHelper.setRenderBounds(renderer, metadata, 0.8125, 0.875, 0.125, 0.9375, 1.0, 0.25);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            RenderHelper.setRenderBounds(renderer, metadata, 0.8125, 0.875, 0.75, 0.9375, 1.0, 0.875);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            renderer.setOverrideBlockTexture(Blocks.stone.getBlockTextureFromSide(0));
            RenderHelper.setRenderBounds(renderer, metadata, 0.5625, 1.105, 0.4375, 0.6875, 1.125, 0.5625);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            renderer.clearOverrideBlockTexture();
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
