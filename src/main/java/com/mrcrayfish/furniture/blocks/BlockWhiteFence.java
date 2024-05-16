/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.blocks;

import com.mrcrayfish.furniture.FurnitureAchievements;
import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.proxy.ClientProxy;
import com.mrcrayfish.furniture.proxy.CommonProxy;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWhiteFence
extends Block {
    public BlockWhiteFence(Material material) {
        super(material);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.6f, 1.0f);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return CommonProxy.renderWhiteFence;
    }

    public boolean canRenderInPass(int pass) {
        ClientProxy.renderPass = pass;
        return true;
    }

    public int getRenderBlockPass() {
        return 1;
    }

    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return true;
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        ((EntityPlayer)par5EntityLivingBase).triggerAchievement((StatBase)FurnitureAchievements.gardening);
    }

    public void addCollisionBoxesToList(World par1World, int i, int j, int k, AxisAlignedBB par5AxisAlignedBB, List arrayList, Entity par7Entity) {
        this.setBlockBounds(0.4375f, 0.0f, 0.4375f, 0.5625f, 1.5f, 0.5625f);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        if (par1World.getBlock(i + 1, j, k) == MrCrayfishFurnitureMod.whiteFence | par1World.getBlock(i + 1, j, k) == Blocks.fence_gate | par1World.getBlock(i + 1, j, k).isNormalCube()) {
            this.setBlockBounds(0.4375f, 0.0f, 0.4375f, 1.0f, 1.5f, 0.5625f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
        if (par1World.getBlock(i - 1, j, k) == MrCrayfishFurnitureMod.whiteFence | par1World.getBlock(i - 1, j, k) == Blocks.fence_gate | par1World.getBlock(i - 1, j, k).isNormalCube()) {
            this.setBlockBounds(0.0f, 0.0f, 0.4375f, 0.5625f, 1.5f, 0.5625f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
        if (par1World.getBlock(i, j, k + 1) == MrCrayfishFurnitureMod.whiteFence | par1World.getBlock(i, j, k + 1) == Blocks.fence_gate | par1World.getBlock(i, j, k + 1).isNormalCube()) {
            this.setBlockBounds(0.4375f, 0.0f, 0.4375f, 0.5625f, 1.5f, 1.0f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
        if (par1World.getBlock(i, j, k - 1) == MrCrayfishFurnitureMod.whiteFence | par1World.getBlock(i, j, k - 1) == Blocks.fence_gate | par1World.getBlock(i, j, k - 1).isNormalCube()) {
            this.setBlockBounds(0.4375f, 0.0f, 0.0f, 0.5625f, 1.5f, 0.5625f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
    }

    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        float f = 0.4375f;
        float f1 = 0.5625f;
        float f2 = 0.4375f;
        float f3 = 0.5625f;
        if (par1IBlockAccess.getBlock(par2 + 1, par3, par4) == MrCrayfishFurnitureMod.whiteFence | par1IBlockAccess.getBlock(par2 + 1, par3, par4) == Blocks.fence_gate | par1IBlockAccess.getBlock(par2 + 1, par3, par4).isNormalCube()) {
            f1 = 1.0f;
        }
        if (par1IBlockAccess.getBlock(par2 - 1, par3, par4) == MrCrayfishFurnitureMod.whiteFence | par1IBlockAccess.getBlock(par2 - 1, par3, par4) == Blocks.fence_gate | par1IBlockAccess.getBlock(par2 - 1, par3, par4).isNormalCube()) {
            f = 0.0f;
        }
        if (par1IBlockAccess.getBlock(par2, par3, par4 + 1) == MrCrayfishFurnitureMod.whiteFence | par1IBlockAccess.getBlock(par2, par3, par4 + 1) == Blocks.fence_gate | par1IBlockAccess.getBlock(par2, par3, par4 + 1).isNormalCube()) {
            f3 = 1.0f;
        }
        if (par1IBlockAccess.getBlock(par2, par3, par4 - 1) == MrCrayfishFurnitureMod.whiteFence | par1IBlockAccess.getBlock(par2, par3, par4 - 1) == Blocks.fence_gate | par1IBlockAccess.getBlock(par2, par3, par4 - 1).isNormalCube()) {
            f2 = 0.0f;
        }
        this.setBlockBounds(f, 0.0f, f2, f1, 1.1f, f3);
    }

    public Item getItemDropped(int i, Random random, int j) {
        return MrCrayfishFurnitureMod.itemWhiteFence;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack(MrCrayfishFurnitureMod.itemWhiteFence);
    }

    public void registerIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = Blocks.wool.getBlockTextureFromSide(1);
    }
}

