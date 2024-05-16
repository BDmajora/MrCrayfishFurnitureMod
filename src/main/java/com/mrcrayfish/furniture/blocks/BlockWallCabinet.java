/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
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
import com.mrcrayfish.furniture.tileentity.TileEntityWallCabinet;
import com.mrcrayfish.furniture.util.CollisionHelper;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockWallCabinet
extends BlockContainer {
    private Random random = new Random();

    public BlockWallCabinet(Material par2Material) {
        super(par2Material);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return CommonProxy.renderWallCabinet;
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
        ((EntityPlayer)par5EntityLivingBase).triggerAchievement((StatBase)FurnitureAchievements.bathroom);
    }

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        TileEntity tile_entity = par1World.getTileEntity(par2, par3, par4);
        if (tile_entity == null || par5EntityPlayer.isSneaking()) {
            return false;
        }
        par5EntityPlayer.openGui((Object)MrCrayfishFurnitureMod.instance, 0, par1World, par2, par3, par4);
        return true;
    }

    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityWallCabinet();
    }

    public void addCollisionBoxesToList(World par1World, int i, int j, int k, AxisAlignedBB par5AxisAlignedBB, List arrayList, Entity par7Entity) {
        int l = par1World.getBlockMetadata(i, j, k);
        float[] data = CollisionHelper.fixRotation(l, 0.75f, 0.125f, 1.0f, 0.875f);
        this.setBlockBounds(data[0], 0.1f, data[1], data[2], 0.9f, data[3]);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int l = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        float[] data = CollisionHelper.fixRotation(l, 0.75f, 0.125f, 1.0f, 0.875f);
        this.setBlockBounds(data[0], 0.1f, data[1], data[2], 0.9f, data[3]);
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

    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        TileEntityWallCabinet var7 = (TileEntityWallCabinet)par1World.getTileEntity(par2, par3, par4);
        if (var7 != null) {
            for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8) {
                ItemStack var9 = var7.getStackInSlot(var8);
                if (var9 == null) continue;
                float var10 = this.random.nextFloat() * 0.8f + 0.1f;
                float var11 = this.random.nextFloat() * 0.8f + 0.1f;
                float var12 = this.random.nextFloat() * 0.8f + 0.1f;
                while (var9.stackSize > 0) {
                    int var13 = this.random.nextInt(21) + 10;
                    if (var13 > var9.stackSize) {
                        var13 = var9.stackSize;
                    }
                    var9.stackSize -= var13;
                    EntityItem var14 = new EntityItem(par1World, (double)((float)par2 + var10), (double)((float)par3 + var11), (double)((float)par4 + var12), new ItemStack(var9.getItem(), var13, var9.getItemDamage()));
                    float var15 = 0.05f;
                    var14.motionX = (float)this.random.nextGaussian() * var15;
                    var14.motionY = (float)this.random.nextGaussian() * var15 + 0.2f;
                    var14.motionZ = (float)this.random.nextGaussian() * var15;
                    if (var9.hasTagCompound()) {
                        var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                    }
                    par1World.spawnEntityInWorld((Entity)var14);
                }
            }
        }
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

    public Item getItemDropped(int i, Random random, int j) {
        return MrCrayfishFurnitureMod.itemWallCabinet;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack(MrCrayfishFurnitureMod.itemWallCabinet);
    }

    public void registerIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = Blocks.quartz_block.getBlockTextureFromSide(0);
    }
}

