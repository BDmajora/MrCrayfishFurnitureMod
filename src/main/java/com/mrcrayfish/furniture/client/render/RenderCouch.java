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

import com.mrcrayfish.furniture.blocks.BlockCouch;
import com.mrcrayfish.furniture.tileentity.TileEntityCouch;
import com.mrcrayfish.furniture.util.RenderHelper;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class RenderCouch
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int metadata = world.getBlockMetadata(x, y, z);
        TileEntityCouch tileEntityCouch = (TileEntityCouch)world.getTileEntity(x, y, z);
        renderer.setOverrideBlockTexture(Blocks.wool.getIcon(0, 15 - tileEntityCouch.getColour()));
        if (RenderHelper.getBlock(world, x, y, z, metadata, 1) instanceof BlockCouch) {
            if (RenderHelper.getRotation(world, x, y, z, metadata, 1) == 3) {
                RenderHelper.setRenderBounds(renderer, metadata, 0.0, 0.6, 0.0, 0.75, 1.2f, 0.25);
                RenderHelper.renderBlock(renderer, block, x, y, z);
            } else if (RenderHelper.getRotation(world, x, y, z, metadata, 1) == 2) {
                RenderHelper.setRenderBounds(renderer, metadata, 0.0, 0.6, 0.75, 0.75, 1.2f, 1.0);
                RenderHelper.renderBlock(renderer, block, x, y, z);
            } else {
                if (RenderHelper.isAirBlock(world, x, y, z, metadata, 2)) {
                    RenderHelper.setRenderBounds(renderer, metadata, -0.001f, 0.5, -0.2f, 1.001f, 0.9f, 0.05f);
                    RenderHelper.renderBlock(renderer, block, x, y, z);
                }
                if (RenderHelper.isAirBlock(world, x, y, z, metadata, 3)) {
                    RenderHelper.setRenderBounds(renderer, metadata, -0.001f, 0.5, 0.95f, 1.001f, 0.9f, 1.2f);
                    RenderHelper.renderBlock(renderer, block, x, y, z);
                }
            }
        } else {
            if (RenderHelper.isAirBlock(world, x, y, z, metadata, 2)) {
                RenderHelper.setRenderBounds(renderer, metadata, -0.001f, 0.5, -0.2f, 1.001f, 0.9f, 0.05f);
                RenderHelper.renderBlock(renderer, block, x, y, z);
            }
            if (RenderHelper.isAirBlock(world, x, y, z, metadata, 3)) {
                RenderHelper.setRenderBounds(renderer, metadata, -0.001f, 0.5, 0.95f, 1.001f, 0.9f, 1.2f);
                RenderHelper.renderBlock(renderer, block, x, y, z);
            }
        }
        RenderHelper.setRenderBounds(renderer, metadata, 0.75, 0.6f, 0.0, 1.0, 1.2f, 1.0);
        RenderHelper.renderBlock(renderer, block, x, y, z);
        renderer.setRenderBounds(0.0, 0.0, 0.0, 1.0, (double)0.6f, 1.0);
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

