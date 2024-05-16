/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
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
import com.mrcrayfish.furniture.blocks.BlockSittable;
import com.mrcrayfish.furniture.proxy.ClientProxy;
import com.mrcrayfish.furniture.proxy.CommonProxy;
import com.mrcrayfish.furniture.util.CollisionHelper;
import java.util.List;
import java.util.Random;
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

public class BlockChair
extends BlockSittable {
    public BlockChair(Material material) {
        super(material);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return CommonProxy.renderChair;
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

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        return this.sitOnBlock(par1World, par2, par3, par4, par5EntityPlayer, 0.6);
    }

    public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {
        int l = par1World.getBlockMetadata(par2, par3, par4);
        float[] data = CollisionHelper.fixRotation(l, 0.825f, 0.1f, 0.9f, 0.9f);
        this.setBlockBounds(data[0], 0.6f, data[1], data[2], 1.2f, data[3]);
        super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        this.setBlockBounds(0.1f, 0.0f, 0.1f, 0.9f, 0.6f, 0.9f);
        super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        this.setBlockBounds(0.1f, 0.0f, 0.1f, 0.9f, 1.2f, 0.9f);
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    public Item getItemDropped(int i, Random random, int j) {
        if (this == MrCrayfishFurnitureMod.chairWood) {
            return MrCrayfishFurnitureMod.itemChairWood;
        }
        if (this == MrCrayfishFurnitureMod.chairStone) {
            return MrCrayfishFurnitureMod.itemChairStone;
        }
        return null;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        if (this == MrCrayfishFurnitureMod.chairWood) {
            return new ItemStack(MrCrayfishFurnitureMod.itemChairWood);
        }
        if (this == MrCrayfishFurnitureMod.chairStone) {
            return new ItemStack(MrCrayfishFurnitureMod.itemChairStone);
        }
        return null;
    }

    public void registerIcons(IIconRegister par1IIconRegister) {
        if (this == MrCrayfishFurnitureMod.chairWood) {
            this.blockIcon = Blocks.planks.getBlockTextureFromSide(1);
        }
        if (this == MrCrayfishFurnitureMod.chairStone) {
            this.blockIcon = Blocks.cobblestone.getBlockTextureFromSide(1);
        }
    }
}

