/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.blocks;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.proxy.ClientProxy;
import com.mrcrayfish.furniture.proxy.CommonProxy;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWindowDecorationClosed
extends Block {
    int timer = 0;

    public BlockWindowDecorationClosed(Material material) {
        super(material);
        this.setLightOpacity(255);
        this.setBlockBounds(0.0f, 0.0f, 0.9f, 1.0f, 1.0f, 1.0f);
    }

    public int getRenderType() {
        return CommonProxy.renderWindowDecoration;
    }

    public boolean canRenderInPass(int pass) {
        ClientProxy.renderPass = pass;
        return true;
    }

    public int getRenderBlockPass() {
        return 1;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return true;
    }

    public void addCollisionBoxesToList(World par1World, int i, int j, int k, AxisAlignedBB par5AxisAlignedBB, List arrayList, Entity par7Entity) {
        int l = par1World.getBlockMetadata(i, j, k);
        if (l == 0) {
            this.setBlockBounds(0.0f, 0.0f, 0.9f, 1.0f, 1.0f, 1.0f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        } else if (l == 2) {
            this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.1f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        } else if (l == 1) {
            this.setBlockBounds(0.0f, 0.0f, 0.0f, 0.1f, 1.0f, 1.0f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        } else if (l == 3) {
            this.setBlockBounds(0.9f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int i, int j, int k) {
        int l = world.getBlockMetadata(i, j, k);
        if (l == 0) {
            this.setBlockBounds(0.0f, 0.0f, 0.9f, 1.0f, 1.0f, 1.0f);
            super.getSelectedBoundingBoxFromPool(world, i, j, k);
        } else if (l == 2) {
            this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.1f);
            super.getSelectedBoundingBoxFromPool(world, i, j, k);
        } else if (l == 1) {
            this.setBlockBounds(0.0f, 0.0f, 0.0f, 0.1f, 1.0f, 1.0f);
            super.getSelectedBoundingBoxFromPool(world, i, j, k);
        } else if (l == 3) {
            this.setBlockBounds(0.9f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            super.getSelectedBoundingBoxFromPool(world, i, j, k);
        }
        return super.getSelectedBoundingBoxFromPool(world, i, j, k);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int l = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        if (l == 0) {
            this.setBlockBounds(0.0f, 0.0f, 0.9f, 1.0f, 1.0f, 1.0f);
        } else if (l == 2) {
            this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.1f);
        } else if (l == 1) {
            this.setBlockBounds(0.0f, 0.0f, 0.0f, 0.1f, 1.0f, 1.0f);
        } else if (l == 3) {
            this.setBlockBounds(0.9f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        }
    }

    public Item getItemDropped(int i, Random random, int j) {
        if (this == MrCrayfishFurnitureMod.blindsClosed) {
            return MrCrayfishFurnitureMod.itemBlinds;
        }
        if (this == MrCrayfishFurnitureMod.curtainsClosed) {
            return MrCrayfishFurnitureMod.itemCurtains;
        }
        return null;
    }

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        int l = par1World.getBlockMetadata(par2, par3, par4);
        if (this == MrCrayfishFurnitureMod.blindsClosed) {
            par1World.setBlock(par2, par3, par4, MrCrayfishFurnitureMod.blinds, l, 3);
        }
        if (this == MrCrayfishFurnitureMod.curtainsClosed) {
            par1World.setBlock(par2, par3, par4, MrCrayfishFurnitureMod.curtains, l, 3);
        }
        return true;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        if (this == MrCrayfishFurnitureMod.blindsClosed) {
            return new ItemStack(MrCrayfishFurnitureMod.itemBlinds);
        }
        if (this == MrCrayfishFurnitureMod.curtainsClosed) {
            return new ItemStack(MrCrayfishFurnitureMod.itemCurtains);
        }
        return null;
    }

    public void registerIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = Blocks.planks.getBlockTextureFromSide(1);
    }
}

