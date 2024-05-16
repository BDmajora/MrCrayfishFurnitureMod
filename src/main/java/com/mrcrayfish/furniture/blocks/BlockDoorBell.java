/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
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
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.mrcrayfish.furniture.blocks;

import com.mrcrayfish.furniture.FurnitureAchievements;
import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.proxy.ClientProxy;
import com.mrcrayfish.furniture.proxy.CommonProxy;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
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
import net.minecraftforge.common.util.ForgeDirection;

public class BlockDoorBell
extends Block {
    public BlockDoorBell(Material material) {
        super(material);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return CommonProxy.renderDoorBell;
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

    public int tickRate(World par1World) {
        return 20;
    }

    public boolean canPlaceBlockOnSide(World p_149707_1_, int p_149707_2_, int p_149707_3_, int p_149707_4_, int p_149707_5_) {
        ForgeDirection dir = ForgeDirection.getOrientation((int)p_149707_5_);
        return dir == ForgeDirection.NORTH && p_149707_1_.isSideSolid(p_149707_2_, p_149707_3_, p_149707_4_ + 1, ForgeDirection.NORTH) || dir == ForgeDirection.SOUTH && p_149707_1_.isSideSolid(p_149707_2_, p_149707_3_, p_149707_4_ - 1, ForgeDirection.SOUTH) || dir == ForgeDirection.WEST && p_149707_1_.isSideSolid(p_149707_2_ + 1, p_149707_3_, p_149707_4_, ForgeDirection.WEST) || dir == ForgeDirection.EAST && p_149707_1_.isSideSolid(p_149707_2_ - 1, p_149707_3_, p_149707_4_, ForgeDirection.EAST);
    }

    public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_) {
        return p_149742_1_.isSideSolid(p_149742_2_ - 1, p_149742_3_, p_149742_4_, ForgeDirection.EAST) || p_149742_1_.isSideSolid(p_149742_2_ + 1, p_149742_3_, p_149742_4_, ForgeDirection.WEST) || p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_, p_149742_4_ - 1, ForgeDirection.SOUTH) || p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_, p_149742_4_ + 1, ForgeDirection.NORTH);
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!this.canPlaceBlockAt(world, x, y, z)) {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        ((EntityPlayer)par5EntityLivingBase).triggerAchievement((StatBase)FurnitureAchievements.modernTechnology);
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
        int i1 = world.getBlockMetadata(i, j, k);
        int j1 = i1 & 7;
        int k1 = 8 - (i1 & 8);
        entityplayer.triggerAchievement((StatBase)FurnitureAchievements.dingDong);
        if (!entityplayer.isSneaking()) {
            if (k1 == 0) {
                return true;
            }
            world.playSoundEffect((double)i, (double)j, (double)k, "cfm:doorbell", 0.75f, 1.0f);
            world.setBlockMetadataWithNotify(i, j, k, j1 + k1, 3);
            world.markBlockRangeForRenderUpdate(i, j, k, i, j, k);
            world.scheduleBlockUpdate(i, j, k, (Block)this, this.tickRate(world));
        }
        return true;
    }

    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        int l;
        if (!par1World.isRemote && ((l = par1World.getBlockMetadata(par2, par3, par4)) & 8) != 0) {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, l & 7, 3);
            par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4);
        }
    }

    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int l = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        this.func_82534_e(l);
    }

    private void func_82534_e(int par1) {
        int j = par1 & 7;
        boolean flag = (par1 & 8) > 0;
        float f = 0.3f;
        float f1 = 0.7f;
        float f2 = 0.1f;
        float f3 = 0.15f;
        if (flag) {
            f3 = 0.11f;
        }
        if (j == 1) {
            this.setBlockBounds(0.0f, f, 0.5f - f2, f3, f1, 0.5f + f2);
        } else if (j == 3) {
            this.setBlockBounds(1.0f - f3, f, 0.5f - f2, 1.0f, f1, 0.5f + f2);
        } else if (j == 2) {
            this.setBlockBounds(0.5f - f2, f, 0.0f, 0.5f + f2, f1, f3);
        } else if (j == 0) {
            this.setBlockBounds(0.5f - f2, f, 1.0f - f3, 0.5f + f2, f1, 1.0f);
        }
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return null;
    }

    public Item getItemDropped(int i, Random random, int j) {
        return MrCrayfishFurnitureMod.itemDoorBell;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack(MrCrayfishFurnitureMod.itemDoorBell);
    }

    public void registerIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = Blocks.log.getBlockTextureFromSide(2);
    }
}

