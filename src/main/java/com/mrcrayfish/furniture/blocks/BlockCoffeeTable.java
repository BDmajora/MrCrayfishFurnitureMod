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

public class BlockCoffeeTable
extends Block {
    public BlockCoffeeTable(Material material) {
        super(material);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.5f, 1.0f);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return CommonProxy.renderCoffeeTable;
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

    public Item getItemDropped(int i, Random random, int j) {
        if (i == 1) {
            return MrCrayfishFurnitureMod.itemCoffeeTableWood;
        }
        if (i == 2) {
            return MrCrayfishFurnitureMod.itemCoffeeTableStone;
        }
        return null;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        if (this == MrCrayfishFurnitureMod.coffeeTableWood) {
            return new ItemStack(MrCrayfishFurnitureMod.itemCoffeeTableWood);
        }
        if (this == MrCrayfishFurnitureMod.coffeeTableStone) {
            return new ItemStack(MrCrayfishFurnitureMod.itemCoffeeTableStone);
        }
        return null;
    }

    public void registerIcons(IIconRegister par1IIconRegister) {
        if (this == MrCrayfishFurnitureMod.coffeeTableWood) {
            this.blockIcon = Blocks.planks.getBlockTextureFromSide(1);
        }
        if (this == MrCrayfishFurnitureMod.coffeeTableStone) {
            this.blockIcon = Blocks.cobblestone.getBlockTextureFromSide(1);
        }
    }
}

