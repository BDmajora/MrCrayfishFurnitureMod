/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.IBlockAccess
 */
package com.mrcrayfish.furniture.client.render;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.tileentity.TileEntityTV;
import com.mrcrayfish.furniture.util.RenderHelper;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

public class RenderTV
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int metadata = world.getBlockMetadata(x, y, z);
        renderer.setOverrideBlockTexture(Blocks.log.getBlockTextureFromSide(2));
        RenderHelper.setRenderBounds(renderer, metadata, 0.1, 0.0, 0.1, 0.6, 0.1, 0.9);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.1, 0.7, 0.1, 0.6, 0.8, 0.9);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.1, 0.1, 0.1, 0.6, 0.7, 0.2);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        RenderHelper.setRenderBounds(renderer, metadata, 0.1, 0.1, 0.8, 0.6, 0.7, 0.9);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        TileEntity tile_entity = world.getTileEntity(x, y, z);
        if (tile_entity instanceof TileEntityTV) {
            TileEntityTV tileEntityTV = (TileEntityTV)tile_entity;
            int tvChannel = tileEntityTV.getChannel();
            if (tvChannel == 0) {
                renderer.setOverrideBlockTexture(Blocks.wool.getIcon(0, 15));
            }
            if (tvChannel == 1) {
                renderer.setOverrideBlockTexture(MrCrayfishFurnitureMod.hey.getBlockTextureFromSide(0));
            }
            if (tvChannel == 2) {
                renderer.setOverrideBlockTexture(MrCrayfishFurnitureMod.nyan.getBlockTextureFromSide(0));
            }
            if (tvChannel == 3) {
                renderer.setOverrideBlockTexture(MrCrayfishFurnitureMod.pattern.getBlockTextureFromSide(0));
            }
        }
        RenderHelper.setRenderBounds(renderer, metadata, 0.2, 0.1f, 0.2, 0.5, 0.7f, 0.8);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setOverrideBlockTexture(Blocks.planks.getBlockTextureFromSide(1));
        RenderHelper.setRenderBounds(renderer, metadata, 0.5, 0.0625, 0.2, 0.9, 0.7f, 0.8);
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

