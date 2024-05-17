/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.blocks;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.proxy.ClientProxy;
import com.mrcrayfish.furniture.proxy.CommonProxy;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCounter
extends Block {
    public BlockCounter(Material material) {
        super(material);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return CommonProxy.renderCounter;
    }

    public boolean canRenderInPass(int pass) {
        ClientProxy.renderPass = pass;
        return true;
    }

    public int getRenderBlockPass() {
        return 1;
    }

    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return true;
    }

    public boolean isBlockSolid(IBlockAccess world, int x, int y, int z, int side) {
        if (side == 1) {
            return true;
        }
        return super.isBlockSolid(world, x, y, z, side);
    }

    public Item getItemDropped(int i, Random random, int j) {
        return MrCrayfishFurnitureMod.itemCounterDoored;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack(MrCrayfishFurnitureMod.itemCounterDoored);
    }

    public void registerIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = Blocks.iron_block.getBlockTextureFromSide(0);
    }

    public Block setUnlocalizedName(String counterdoored) {
        return null;
    }
}

