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

public class BlockBirdBath
extends Block {
    public BlockBirdBath(Material material) {
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
        return CommonProxy.renderBirdBath;
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
        return MrCrayfishFurnitureMod.itemBirdBath;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack(MrCrayfishFurnitureMod.itemBirdBath);
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        ((EntityPlayer)par5EntityLivingBase).triggerAchievement((StatBase)FurnitureAchievements.gardening);
    }

    public void addCollisionBoxesToList(World par1World, int i, int j, int k, AxisAlignedBB par5AxisAlignedBB, List arrayList, Entity par7Entity) {
        this.setBlockBounds(0.25f, 0.0f, 0.25f, 0.75f, 0.1f, 0.75f);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        this.setBlockBounds(0.3125f, 0.1f, 0.3125f, 0.6875f, 0.7f, 0.6875f);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        this.setBlockBounds(0.0f, 0.7f, 0.0f, 1.0f, 0.8f, 1.0f);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        this.setBlockBounds(0.0f, 0.8f, 0.0f, 0.1f, 0.9f, 1.0f);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        this.setBlockBounds(0.1f, 0.7f, 0.0f, 0.9f, 0.9f, 0.1f);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        this.setBlockBounds(0.1f, 0.7f, 0.9f, 0.9f, 0.9f, 1.0f);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        this.setBlockBounds(0.9f, 0.8f, 0.0f, 1.0f, 0.9f, 1.0f);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.9f, 1.0f);
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.9f, 1.0f);
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    public void registerIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = Blocks.stone.getBlockTextureFromSide(1);
    }

    public Block setUnlocalizedName(String birdbath) {
        return null;
    }
}

