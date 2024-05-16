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

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.util.RenderHelper;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class RenderWindowDecoration
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int metadata = world.getBlockMetadata(x, y, z);
        if (block == MrCrayfishFurnitureMod.blinds | block == MrCrayfishFurnitureMod.blindsClosed) {
            renderer.setOverrideBlockTexture(Blocks.log2.getIcon(2, 1));
            RenderHelper.setRenderBounds(renderer, metadata, 0.875, 0.875, 0.0, 1.0, 1.0, 1.0);
            RenderHelper.renderBlock(renderer, block, x, y, z);
        } else {
            renderer.setOverrideBlockTexture(Blocks.planks.getIcon(0, 0));
            RenderHelper.setRenderBounds(renderer, metadata, 0.875, 0.875, 0.0, 1.0, 1.0, 1.0);
            RenderHelper.renderBlock(renderer, block, x, y, z);
        }
        if (block == MrCrayfishFurnitureMod.blinds) {
            renderer.setOverrideBlockTexture(Blocks.planks.getBlockTextureFromSide(0));
            RenderHelper.setRenderBounds(renderer, metadata, 0.9375, 0.0, 0.0, 1.0, 0.0625, 1.0);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            RenderHelper.setRenderBounds(renderer, metadata, 0.9375, 0.125, 0.0, 1.0, 0.1875, 1.0);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            RenderHelper.setRenderBounds(renderer, metadata, 0.9375, 0.25, 0.0, 1.0, 0.3125, 1.0);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            RenderHelper.setRenderBounds(renderer, metadata, 0.9375, 0.375, 0.0, 1.0, 0.4375, 1.0);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            RenderHelper.setRenderBounds(renderer, metadata, 0.9375, 0.5, 0.0, 1.0, 0.5625, 1.0);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            RenderHelper.setRenderBounds(renderer, metadata, 0.9375, 0.625, 0.0, 1.0, 0.6875, 1.0);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            RenderHelper.setRenderBounds(renderer, metadata, 0.9375, 0.75, 0.0, 1.0, 0.8125, 1.0);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            if ((RenderHelper.getBlock(world, x, y, z, metadata, 2) != MrCrayfishFurnitureMod.blinds && RenderHelper.getBlock(world, x, y, z, metadata, 2) != MrCrayfishFurnitureMod.blindsClosed) | (RenderHelper.getBlock(world, x, y, z, metadata, 2) == MrCrayfishFurnitureMod.blinds | RenderHelper.getBlock(world, x, y, z, metadata, 2) == MrCrayfishFurnitureMod.blindsClosed && RenderHelper.getRotation(world, x, y, z, metadata, 2) != 1)) {
                renderer.setOverrideBlockTexture(Blocks.wool.getIcon(0, 0));
                RenderHelper.setRenderBounds(renderer, metadata, 0.90625, 0.124, 0.04687, 0.9375, 0.875, 0.078125);
                RenderHelper.renderBlock(renderer, block, x, y, z);
            }
        } else if (block == MrCrayfishFurnitureMod.blindsClosed) {
            renderer.setOverrideBlockTexture(Blocks.planks.getBlockTextureFromSide(0));
            RenderHelper.setRenderBounds(renderer, metadata, 0.9375, 0.0, 0.0, 1.0, 0.875, 1.0);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            if ((RenderHelper.getBlock(world, x, y, z, metadata, 2) != MrCrayfishFurnitureMod.blinds && RenderHelper.getBlock(world, x, y, z, metadata, 2) != MrCrayfishFurnitureMod.blindsClosed) | (RenderHelper.getBlock(world, x, y, z, metadata, 2) == MrCrayfishFurnitureMod.blinds | RenderHelper.getBlock(world, x, y, z, metadata, 2) == MrCrayfishFurnitureMod.blindsClosed && RenderHelper.getRotation(world, x, y, z, metadata, 2) != 1)) {
                renderer.setOverrideBlockTexture(Blocks.wool.getIcon(0, 0));
                RenderHelper.setRenderBounds(renderer, metadata, 0.90625, 0.5, 0.046875, 0.9375, 0.875, 0.078125);
                RenderHelper.renderBlock(renderer, block, x, y, z);
            }
        }
        if (block == MrCrayfishFurnitureMod.curtains) {
            double heightFix;
            double widthFix;
            boolean isClosedCurtain;
            renderer.setOverrideBlockTexture(Blocks.wool.getIcon(0, 14));
            if (RenderHelper.getBlock(world, x, y, z, metadata, 2) != MrCrayfishFurnitureMod.curtains | (RenderHelper.getBlock(world, x, y, z, metadata, 2) == MrCrayfishFurnitureMod.curtains && RenderHelper.getRotation(world, x, y, z, metadata, 2) != 1)) {
                isClosedCurtain = RenderHelper.getBlock(world, x, y, z, metadata, 2) == MrCrayfishFurnitureMod.curtainsClosed && RenderHelper.getRotation(world, x, y, z, metadata, 2) == 1;
                widthFix = isClosedCurtain ? 0.125 : 0.0;
                RenderHelper.setRenderBounds(renderer, metadata, 0.9375, 0.125, 0.0, 1.0, 0.25, 0.1875 - widthFix);
                RenderHelper.renderBlock(renderer, block, x, y, z);
                heightFix = isClosedCurtain ? 0.0625 : 0.0;
                RenderHelper.setRenderBounds(renderer, metadata, 0.9375, 0.3125 - heightFix, 0.0, 1.0, 0.375, 0.125);
                RenderHelper.renderBlock(renderer, block, x, y, z);
                RenderHelper.setRenderBounds(renderer, metadata, 0.9375, 0.375, 0.0, 1.0, 0.5, 0.1875);
                RenderHelper.renderBlock(renderer, block, x, y, z);
                RenderHelper.setRenderBounds(renderer, metadata, 0.9375, 0.5, 0.0, 1.0, 0.6875, 0.25);
                RenderHelper.renderBlock(renderer, block, x, y, z);
                RenderHelper.setRenderBounds(renderer, metadata, 0.9375, 0.6875, 0.0, 1.0, 0.875, 0.3125);
                RenderHelper.renderBlock(renderer, block, x, y, z);
            }
            if (RenderHelper.getBlock(world, x, y, z, metadata, 3) != MrCrayfishFurnitureMod.curtains | (RenderHelper.getBlock(world, x, y, z, metadata, 3) == MrCrayfishFurnitureMod.curtains && RenderHelper.getRotation(world, x, y, z, metadata, 3) != 1)) {
                isClosedCurtain = RenderHelper.getBlock(world, x, y, z, metadata, 3) == MrCrayfishFurnitureMod.curtainsClosed && RenderHelper.getRotation(world, x, y, z, metadata, 3) == 1;
                widthFix = isClosedCurtain ? 0.125 : 0.0;
                RenderHelper.setRenderBounds(renderer, metadata, 0.9375, 0.125, 0.8125 + widthFix, 1.0, 0.25, 1.0);
                RenderHelper.renderBlock(renderer, block, x, y, z);
                heightFix = isClosedCurtain ? 0.0625 : 0.0;
                RenderHelper.setRenderBounds(renderer, metadata, 0.9375, 0.3125 - heightFix, 0.875, 1.0, 0.375, 1.0);
                RenderHelper.renderBlock(renderer, block, x, y, z);
                RenderHelper.setRenderBounds(renderer, metadata, 0.9375, 0.375, 0.8125, 1.0, 0.5, 1.0);
                RenderHelper.renderBlock(renderer, block, x, y, z);
                RenderHelper.setRenderBounds(renderer, metadata, 0.9375, 0.5, 0.75, 1.0, 0.6875, 1.0);
                RenderHelper.renderBlock(renderer, block, x, y, z);
                RenderHelper.setRenderBounds(renderer, metadata, 0.9375, 0.6875, 0.6875, 1.0, 0.875, 1.0);
                RenderHelper.renderBlock(renderer, block, x, y, z);
            }
            renderer.setOverrideBlockTexture(Blocks.gold_block.getBlockTextureFromSide(0));
            if ((RenderHelper.getBlock(world, x, y, z, metadata, 2) != MrCrayfishFurnitureMod.curtains && RenderHelper.getBlock(world, x, y, z, metadata, 2) != MrCrayfishFurnitureMod.curtains) | (RenderHelper.getBlock(world, x, y, z, metadata, 2) == MrCrayfishFurnitureMod.curtains && RenderHelper.getRotation(world, x, y, z, metadata, 2) != 1)) {
                boolean bl = isClosedCurtain = RenderHelper.getBlock(world, x, y, z, metadata, 2) == MrCrayfishFurnitureMod.curtainsClosed && RenderHelper.getRotation(world, x, y, z, metadata, 2) == 1;
                if (!isClosedCurtain) {
                    RenderHelper.setRenderBounds(renderer, metadata, 0.9374, 0.25, 0.0, 0.9999, 0.3125, 0.125);
                    RenderHelper.renderBlock(renderer, block, x, y, z);
                }
            }
            if (RenderHelper.getBlock(world, x, y, z, metadata, 3) != MrCrayfishFurnitureMod.curtains | (RenderHelper.getBlock(world, x, y, z, metadata, 3) == MrCrayfishFurnitureMod.curtains && RenderHelper.getRotation(world, x, y, z, metadata, 3) != 1)) {
                boolean bl = isClosedCurtain = RenderHelper.getBlock(world, x, y, z, metadata, 3) == MrCrayfishFurnitureMod.curtainsClosed && RenderHelper.getRotation(world, x, y, z, metadata, 3) == 1;
                if (!isClosedCurtain) {
                    RenderHelper.setRenderBounds(renderer, metadata, 0.9374, 0.25, 0.875, 0.9999, 0.3125, 1.0);
                    RenderHelper.renderBlock(renderer, block, x, y, z);
                }
            }
        } else if (block == MrCrayfishFurnitureMod.curtainsClosed) {
            renderer.setOverrideBlockTexture(Blocks.wool.getIcon(0, 14));
            RenderHelper.setRenderBounds(renderer, metadata, 0.9375, 0.0625, 0.0, 1.0, 0.875, 1.0001);
            RenderHelper.renderBlock(renderer, block, x, y, z);
            renderer.setOverrideBlockTexture(Blocks.gold_block.getBlockTextureFromSide(0));
            if ((RenderHelper.getBlock(world, x, y, z, metadata, 2) != MrCrayfishFurnitureMod.curtains && RenderHelper.getBlock(world, x, y, z, metadata, 2) != MrCrayfishFurnitureMod.curtainsClosed) | (RenderHelper.getBlock(world, x, y, z, metadata, 2) == MrCrayfishFurnitureMod.curtains | RenderHelper.getBlock(world, x, y, z, metadata, 2) == MrCrayfishFurnitureMod.curtainsClosed && RenderHelper.getRotation(world, x, y, z, metadata, 2) != 1)) {
                RenderHelper.setRenderBounds(renderer, metadata, 0.9374, 0.1875, -1.0E-4, 0.9999, 0.3125, 0.0625);
                RenderHelper.renderBlock(renderer, block, x, y, z);
            }
            if ((RenderHelper.getBlock(world, x, y, z, metadata, 3) != MrCrayfishFurnitureMod.curtains && RenderHelper.getBlock(world, x, y, z, metadata, 3) != MrCrayfishFurnitureMod.curtainsClosed) | (RenderHelper.getBlock(world, x, y, z, metadata, 3) == MrCrayfishFurnitureMod.curtains | RenderHelper.getBlock(world, x, y, z, metadata, 3) == MrCrayfishFurnitureMod.curtainsClosed && RenderHelper.getRotation(world, x, y, z, metadata, 3) != 1)) {
                RenderHelper.setRenderBounds(renderer, metadata, 0.9374, 0.1875, 0.9375, 0.9999, 0.3125, 1.0002);
                RenderHelper.renderBlock(renderer, block, x, y, z);
            }
        }
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

