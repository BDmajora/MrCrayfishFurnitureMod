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

public class RenderToilet
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
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
        tessellator.setColorOpaque_F(f * f1, f * f2, f * f3);
        IIcon icon2 = BlockLiquid.getLiquidIcon((String)"water_still");
        int metadata = world.getBlockMetadata(x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.4, 0.4, 0.4f, 0.7, 0.401, 0.6);
        renderer.renderFaceYPos(block, (double)x, (double)y, (double)z, icon2);
        renderer.setOverrideBlockTexture(Blocks.quartz_block.getBlockTextureFromSide(0));
        RenderHelper.setRenderBounds(renderer, metadata, 0.2, 0.0, 0.25, 0.9, 0.3, 0.75);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.65, 0.3, 0.15, 1.0, 1.0, 0.85);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.6, 1.0, 0.1, 1.0, 1.1, 0.9);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.1, 0.3, 0.15, 0.65, 0.6, 0.25);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.1, 0.3, 0.25, 0.2, 0.6, 0.75);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.1, 0.3, 0.75, 0.65, 0.6, 0.85);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.4, 0.3, 0.25, 0.7, 0.35, 0.4);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.4, 0.3, 0.6, 0.7, 0.35, 0.75);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.2, 0.3, 0.25, 0.7, 0.4, 0.75);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.stained_hardened_clay.getIcon(0, 9));
        RenderHelper.setRenderBounds(renderer, metadata, 0.7, 0.4, 0.39, 0.8, 1.15, 0.49);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.7, 0.4, 0.51, 0.8, 1.15, 0.61);
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

