/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.IBlockAccess
 */
package com.mrcrayfish.furniture.client.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class RenderTree
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        float height = 0.5f;
        float width = 0.99f;
        renderer.setOverrideBlockTexture(Blocks.hardened_clay.getBlockTextureFromSide(0));
        renderer.setRenderBounds(0.25, 0.0, 0.25, 0.75, (double)0.35f, 0.75);
        renderer.renderStandardBlock(block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.log.getBlockTextureFromSide(2));
        renderer.setRenderBounds((double)0.46f, 0.0, (double)0.46f, (double)0.54f, (double)1.9f, (double)0.54f);
        renderer.renderStandardBlock(block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.leaves.getIcon(0, 1));
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        float f = 1.0f;
        int l1 = 23040;
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
        tessellator.setColorOpaque_F(f * f1, f * f2, f * f3);
        for (int i = 0; i < 5; ++i) {
            renderer.setRenderBounds((double)(1.0f - width), (double)height, (double)(1.0f - width), (double)width, (double)(height + 0.375f), (double)width);
            renderer.renderFaceYPos(block, (double)x, (double)y, (double)z, Blocks.leaves.getIcon(1, 1));
            renderer.renderFaceYNeg(block, (double)x, (double)y, (double)z, Blocks.leaves.getIcon(1, 1));
            renderer.renderFaceXPos(block, (double)x, (double)y, (double)z, Blocks.leaves.getIcon(1, 1));
            renderer.renderFaceXNeg(block, (double)x, (double)y, (double)z, Blocks.leaves.getIcon(1, 1));
            renderer.renderFaceZPos(block, (double)x, (double)y, (double)z, Blocks.leaves.getIcon(1, 1));
            renderer.renderFaceZNeg(block, (double)x, (double)y, (double)z, Blocks.leaves.getIcon(1, 1));
            width -= 0.1f;
            height += 0.375f;
        }
        renderer.setOverrideBlockTexture(Blocks.gold_block.getBlockTextureFromSide(0));
        int metadata = world.getBlockMetadata(x, y, z);
        if (metadata == 3 | metadata == 1) {
            renderer.setRenderBounds((double)0.46f, (double)height, (double)0.35f, (double)0.54f, (double)height + 0.25, (double)0.65f);
        } else {
            renderer.setRenderBounds((double)0.35f, (double)height, (double)0.46f, (double)0.65f, (double)height + 0.25, (double)0.54f);
        }
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

