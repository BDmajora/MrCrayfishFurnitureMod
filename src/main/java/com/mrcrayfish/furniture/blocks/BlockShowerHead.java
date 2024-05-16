/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.ITileEntityProvider
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.tileentity.TileEntity
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
import com.mrcrayfish.furniture.tileentity.TileEntityShowerHead;
import com.mrcrayfish.furniture.util.CollisionHelper;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockShowerHead
extends Block
implements ITileEntityProvider {
    public BlockShowerHead(Material material) {
        super(material);
        this.setTickRandomly(true);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return CommonProxy.renderShowerHead;
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

    public TileEntity createNewTileEntity(World var1, int var2) {
        if (this == MrCrayfishFurnitureMod.showerHeadOn) {
            return new TileEntityShowerHead();
        }
        return null;
    }

    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int metadata = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        float[] data = CollisionHelper.fixRotation(metadata, 0.35f, 0.35f, 1.0f, 0.65f);
        this.setBlockBounds(data[0], 0.15f, data[1], data[2], 0.45f, data[3]);
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        ((EntityPlayer)par5EntityLivingBase).triggerAchievement((StatBase)FurnitureAchievements.bathroom);
    }

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        int metadata = par1World.getBlockMetadata(par2, par3, par4);
        if (this == MrCrayfishFurnitureMod.showerHeadOff) {
            par1World.setBlock(par2, par3, par4, MrCrayfishFurnitureMod.showerHeadOn, metadata, 2);
            par1World.playSoundEffect((double)par2 + 0.5, (double)par3 + 0.5, (double)par4 + 0.5, "random.click", 0.3f, 0.6f);
        } else {
            par1World.setBlock(par2, par3, par4, MrCrayfishFurnitureMod.showerHeadOff, metadata, 2);
            par1World.playSoundEffect((double)par2 + 0.5, (double)par3 + 0.5, (double)par4 + 0.5, "random.click", 0.3f, 0.5f);
        }
        return true;
    }

    public void addCollisionBoxesToList(World par1World, int i, int j, int k, AxisAlignedBB par5AxisAlignedBB, List arrayList, Entity par7Entity) {
        int metadata = par1World.getBlockMetadata(i, j, k);
        float[] data = CollisionHelper.fixRotation(metadata, 0.35f, 0.35f, 1.0f, 0.65f);
        this.setBlockBounds(data[0], 0.15f, data[1], data[2], 0.45f, data[3]);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
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

    public Item getItemDropped(int i, Random random, int j) {
        return MrCrayfishFurnitureMod.itemShowerHead;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack(MrCrayfishFurnitureMod.itemShowerHead);
    }

    public void registerIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = Blocks.stone.getBlockTextureFromSide(0);
    }
}

