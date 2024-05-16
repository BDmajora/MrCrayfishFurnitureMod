/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.ITileEntityProvider
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemDye
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
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
import com.mrcrayfish.furniture.tileentity.TileEntityCouch;
import com.mrcrayfish.furniture.util.CollisionHelper;
import com.mrcrayfish.furniture.util.RenderHelper;
import java.util.List;
import java.util.Random;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCouch
extends BlockSittable
implements ITileEntityProvider {
    public BlockCouch(Material material) {
        super(material);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return CommonProxy.renderCouch;
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
        ItemStack currentItem = par5EntityPlayer.getCurrentEquippedItem();
        TileEntity tileEntity = par1World.getTileEntity(par2, par3, par4);
        if (tileEntity instanceof TileEntityCouch) {
            TileEntityCouch tileEntityCouch = (TileEntityCouch)tileEntity;
            if (currentItem != null && currentItem.getItem() instanceof ItemDye) {
                tileEntityCouch.setColour(currentItem.getItemDamage());
                --currentItem.stackSize;
                par1World.markBlockForUpdate(par2, par3, par4);
                return true;
            }
        }
        return this.sitOnBlock(par1World, par2, par3, par4, par5EntityPlayer, 0.6);
    }

    public void addCollisionBoxesToList(World par1World, int i, int j, int k, AxisAlignedBB par5AxisAlignedBB, List arrayList, Entity par7Entity) {
        float[] data;
        int metadata = par1World.getBlockMetadata(i, j, k);
        if (RenderHelper.getBlock((IBlockAccess)par1World, i, j, k, metadata, 1) instanceof BlockCouch) {
            if (RenderHelper.getRotation((IBlockAccess)par1World, i, j, k, metadata, 1) == 3) {
                data = CollisionHelper.fixRotation(metadata, 0.0f, 0.0f, 0.75f, 0.25f);
                this.setBlockBounds(data[0], 0.6f, data[1], data[2], 1.2f, data[3]);
                super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
            } else if (RenderHelper.getRotation((IBlockAccess)par1World, i, j, k, metadata, 1) == 2) {
                data = CollisionHelper.fixRotation(metadata, 0.0f, 0.75f, 0.75f, 1.0f);
                this.setBlockBounds(data[0], 0.6f, data[1], data[2], 1.2f, data[3]);
                super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
            } else {
                if (RenderHelper.isAirBlock((IBlockAccess)par1World, i, j, k, metadata, 2)) {
                    data = CollisionHelper.fixRotation(metadata, -0.001f, -0.2f, 1.001f, 0.05f);
                    this.setBlockBounds(data[0], 0.5f, data[1], data[2], 0.9f, data[3]);
                    super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
                }
                if (RenderHelper.isAirBlock((IBlockAccess)par1World, i, j, k, metadata, 3)) {
                    data = CollisionHelper.fixRotation(metadata, -0.001f, 0.95f, 1.001f, 1.2f);
                    this.setBlockBounds(data[0], 0.5f, data[1], data[2], 0.9f, data[3]);
                    super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
                }
            }
        } else {
            if (RenderHelper.isAirBlock((IBlockAccess)par1World, i, j, k, metadata, 2)) {
                data = CollisionHelper.fixRotation(metadata, -0.001f, -0.2f, 1.001f, 0.05f);
                this.setBlockBounds(data[0], 0.5f, data[1], data[2], 0.9f, data[3]);
                super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
            }
            if (RenderHelper.isAirBlock((IBlockAccess)par1World, i, j, k, metadata, 3)) {
                data = CollisionHelper.fixRotation(metadata, -0.001f, 0.95f, 1.001f, 1.2f);
                this.setBlockBounds(data[0], 0.5f, data[1], data[2], 0.9f, data[3]);
                super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
            }
        }
        data = CollisionHelper.fixRotation(metadata, 0.8f, 0.0f, 1.0f, 1.0f);
        this.setBlockBounds(data[0], 0.6f, data[1], data[2], 1.2f, data[3]);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        float[] data2 = CollisionHelper.fixRotation(metadata, 0.0f, 0.0f, 1.0f, 1.0f);
        this.setBlockBounds(data2[0], 0.0f, data2[1], data2[2], 0.6f, data2[3]);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityCouch();
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack(MrCrayfishFurnitureMod.itemCouch);
    }

    public Item getItemDropped(int i, Random random, int j) {
        return MrCrayfishFurnitureMod.itemCouch;
    }

    public void registerIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = par1IIconRegister.registerIcon("wool_colored_white");
    }
}

