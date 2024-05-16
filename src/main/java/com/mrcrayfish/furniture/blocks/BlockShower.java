/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.blocks;

import com.mrcrayfish.furniture.FurnitureAchievements;
import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.proxy.ClientProxy;
import com.mrcrayfish.furniture.proxy.CommonProxy;
import com.mrcrayfish.furniture.util.CollisionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockShower
extends Block {
    public BlockShower(Material material) {
        super(material);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return CommonProxy.renderShower;
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
        par1World.setBlock(par2, par3 + 1, par4, MrCrayfishFurnitureMod.showerTop, par1World.getBlockMetadata(par2, par3, par4), 2);
        ((EntityPlayer)par5EntityLivingBase).triggerAchievement((StatBase)FurnitureAchievements.bathroom);
    }

    public void addCollisionBoxesToList(World par1World, int i, int j, int k, AxisAlignedBB par5AxisAlignedBB, List arrayList, Entity par7Entity) {
        int metadata = par1World.getBlockMetadata(i, j, k);
        if (metadata == 3 | metadata == 2 | metadata == 0) {
            this.setBlockBounds(0.9f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
        if (metadata == 3 | metadata == 2 | metadata == 1) {
            this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.1f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
        if (metadata == 3 | metadata == 1 | metadata == 0) {
            this.setBlockBounds(0.0f, 0.0f, 0.9f, 1.0f, 1.0f, 1.0f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
        if (metadata == 2 | metadata == 1 | metadata == 0) {
            this.setBlockBounds(0.0f, 0.0f, 0.0f, 0.1f, 1.0f, 1.0f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int camera = Minecraft.getMinecraft().gameSettings.thirdPersonView;
        int direction = MathHelper.floor_double((double)((double)(Minecraft.getMinecraft().thePlayer.rotationYaw * 4.0f / 360.0f) + 0.5)) & 3;
        int metadata = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        switch (metadata) {
            case 1: {
                metadata = 3;
                break;
            }
            case 3: {
                metadata = 1;
                break;
            }
            case 2: {
                metadata = 0;
                break;
            }
            case 0: {
                metadata = 2;
            }
        }
        if (camera == 1 | camera == 2) {
            float[] data = CollisionHelper.fixRotation(direction, 0.9f, 0.0f, 1.0f, 1.0f);
            if (direction != metadata) {
                this.setBlockBounds(data[0], 0.0f, data[1], data[2], 1.0f, data[3]);
            } else {
                this.setBlockBounds(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
            }
        }
    }

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        Block block = par1World.getBlock(par2, par3 + 1, par4);
        int metadata = par1World.getBlockMetadata(par2, par3 + 1, par4);
        if (block == MrCrayfishFurnitureMod.showerHeadOff) {
            par1World.setBlock(par2, par3 + 1, par4, MrCrayfishFurnitureMod.showerHeadOn, metadata, 2);
            par1World.playSoundEffect((double)par2 + 0.5, (double)par3 + 0.5, (double)par4 + 0.5, "random.click", 0.3f, 0.6f);
        } else if (block == MrCrayfishFurnitureMod.showerHeadOn) {
            par1World.setBlock(par2, par3 + 1, par4, MrCrayfishFurnitureMod.showerHeadOff, metadata, 2);
            par1World.playSoundEffect((double)par2 + 0.5, (double)par3 + 0.5, (double)par4 + 0.5, "random.click", 0.3f, 0.5f);
        }
        return true;
    }

    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5Block) {
        if (this == MrCrayfishFurnitureMod.showerBottom) {
            if (par1World.getBlock(par2, par3 + 1, par4) != MrCrayfishFurnitureMod.showerTop) {
                par1World.setBlockToAir(par2, par3, par4);
            }
        } else if (par1World.getBlock(par2, par3 - 1, par4) != MrCrayfishFurnitureMod.showerBottom) {
            par1World.setBlockToAir(par2, par3, par4);
        }
    }

    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
        return par1World.isAirBlock(par2, par3 + 1, par4);
    }

    public Item getItemDropped(int i, Random random, int j) {
        return MrCrayfishFurnitureMod.itemShower;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack(MrCrayfishFurnitureMod.itemShower);
    }

    public void registerIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = Blocks.quartz_block.getBlockTextureFromSide(0);
    }
}

