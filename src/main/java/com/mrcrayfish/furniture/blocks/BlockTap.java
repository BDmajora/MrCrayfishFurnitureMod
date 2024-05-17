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
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
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
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTap
extends Block {
    public BlockTap(Material material) {
        super(material);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return CommonProxy.renderTap;
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

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        ((EntityPlayer)par5EntityLivingBase).triggerAchievement((StatBase)FurnitureAchievements.gardening);
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
        if (!world.isRemote) {
            if (this.hasWaterSource(world, i, j, k)) {
                int l = world.getBlockMetadata(i, j, k);
                switch (l) {
                    case 3: {
                        world.setBlock(i - 1, j, k, Blocks.water);
                        break;
                    }
                    case 1: {
                        world.setBlock(i + 1, j, k, Blocks.water);
                        break;
                    }
                    case 2: {
                        world.setBlock(i, j, k + 1, Blocks.water);
                        break;
                    }
                    case 0: {
                        world.setBlock(i, j, k - 1, Blocks.water);
                    }
                }
                world.setBlockToAir(i, j - 2, k);
                entityplayer.triggerAchievement((StatBase)FurnitureAchievements.tapped);
            } else {
                entityplayer.addChatComponentMessage((IChatComponent)new ChatComponentText("You need to have a water source under the block the tap is on to use it."));
            }
        }
        return true;
    }

    public boolean hasWaterSource(World world, int x, int y, int z) {
        return world.getBlock(x, y - 2, z) == Blocks.water && world.getBlockMetadata(x, y - 2, z) == 0;
    }

    public void addCollisionBoxesToList(World par1World, int i, int j, int k, AxisAlignedBB par5AxisAlignedBB, List arrayList, Entity par7Entity) {
        this.setBlockBounds(0.4375f, 0.0f, 0.4375f, 0.5625f, 0.8f, 0.5625f);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        int l = par1World.getBlockMetadata(i, j, k);
        if (l == 3) {
            this.setBlockBounds(0.125f, 0.8f, 0.4375f, 0.5625f, 0.9f, 0.5625f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
            this.setBlockBounds(0.125f, 0.7f, 0.4375f, 0.25f, 0.8f, 0.5625f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
        if (l == 1) {
            this.setBlockBounds(0.4375f, 0.8f, 0.4375f, 0.875f, 0.9f, 0.5625f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
            this.setBlockBounds(0.75f, 0.7f, 0.4375f, 0.875f, 0.8f, 0.5625f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
        if (l == 2) {
            this.setBlockBounds(0.4375f, 0.8f, 0.4375f, 0.5625f, 0.9f, 0.875f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
            this.setBlockBounds(0.4375f, 0.7f, 0.75f, 0.5625f, 0.8f, 0.875f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
        if (l == 0) {
            this.setBlockBounds(0.4375f, 0.8f, 0.125f, 0.5625f, 0.9f, 0.5625f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
            this.setBlockBounds(0.4375f, 0.7f, 0.125f, 0.5625f, 0.8f, 0.25f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
    }

    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int l = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        if (l == 3) {
            this.setBlockBounds(0.125f, 0.0f, 0.4f, 0.5625f, 1.0f, 0.6f);
        }
        if (l == 1) {
            this.setBlockBounds(0.4375f, 0.0f, 0.4f, 0.875f, 1.0f, 0.6f);
        }
        if (l == 2) {
            this.setBlockBounds(0.4f, 0.0f, 0.4375f, 0.6f, 1.0f, 0.875f);
        }
        if (l == 0) {
            this.setBlockBounds(0.4f, 0.0f, 0.125f, 0.6f, 1.0f, 0.5625f);
        }
    }

    public Item getItemDropped(int i, Random random, int j) {
        return MrCrayfishFurnitureMod.itemTap;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack(MrCrayfishFurnitureMod.itemTap);
    }

    public void registerIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = Blocks.stone.getBlockTextureFromSide(1);
    }

    public Block setUnlocalizedName(String tap) {
        return null;
    }
}

