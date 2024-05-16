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
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.blocks;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.proxy.ClientProxy;
import com.mrcrayfish.furniture.proxy.CommonProxy;
import com.mrcrayfish.furniture.tileentity.TileEntityCup;
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
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCup
extends BlockContainer {
    private Random rand = new Random();

    public BlockCup(Material material) {
        super(material);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return CommonProxy.renderCup;
    }

    public boolean canRenderInPass(int pass) {
        ClientProxy.renderPass = pass;
        return true;
    }

    public int getRenderBlockPass() {
        return 1;
    }

    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int meta) {
        return true;
    }

    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        TileEntityCup var7 = (TileEntityCup)par1World.getTileEntity(par2, par3, par4);
        if (var7 != null) {
            ItemStack var9 = var7.getDrink();
            if (var9 == null) {
                var9 = new ItemStack(MrCrayfishFurnitureMod.itemCup);
            }
            float var10 = this.rand.nextFloat() * 0.8f + 0.1f;
            float var11 = this.rand.nextFloat() * 0.8f + 0.1f;
            float var12 = this.rand.nextFloat() * 0.8f + 0.1f;
            while (var9.stackSize > 0) {
                int var13 = this.rand.nextInt(21) + 10;
                if (var13 > var9.stackSize) {
                    var13 = var9.stackSize;
                }
                var9.stackSize -= var13;
                EntityItem var14 = new EntityItem(par1World, (double)((float)par2 + var10), (double)((float)par3 + var11), (double)((float)par4 + var12), new ItemStack(var9.getItem(), var13, var9.getMetadata()));
                float var15 = 0.05f;
                var14.motionX = (float)this.rand.nextGaussian() * var15;
                var14.motionY = (float)this.rand.nextGaussian() * var15 + 0.2f;
                var14.motionZ = (float)this.rand.nextGaussian() * var15;
                if (var9.hasTagCompound()) {
                    var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                }
                par1World.spawnEntityInWorld((Entity)var14);
            }
        }
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
        NBTTagCompound nbt;
        if (item.hasTagCompound() && (nbt = item.getTagCompound()).hasKey("Colour")) {
            int[] rgb = nbt.getIntArray("Colour");
            TileEntityCup tileEntityCup = (TileEntityCup)world.getTileEntity(x, y, z);
            tileEntityCup.setColour(rgb);
            tileEntityCup.setItem(item);
        }
    }

    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int metadata = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        float[] data = CollisionHelper.fixRotation(metadata, 0.3125f, 0.3125f, 0.6875f, 0.6875f);
        this.setBlockBounds(data[0], 0.0f, data[1], data[2], 0.46875f, data[3]);
    }

    public void addCollisionBoxesToList(World par1World, int i, int j, int k, AxisAlignedBB par5AxisAlignedBB, List arrayList, Entity par7Entity) {
        int metadata = par1World.getBlockMetadata(i, j, k);
        float[] data = CollisionHelper.fixRotation(metadata, 0.3125f, 0.3125f, 0.6875f, 0.6875f);
        this.setBlockBounds(data[0], 0.0f, data[1], data[2], 0.46875f, data[3]);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
    }

    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityCup();
    }

    public Item getItemDropped(int i, Random random, int j) {
        return null;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        TileEntityCup tileEntityCup = (TileEntityCup)world.getTileEntity(x, y, z);
        if (tileEntityCup.getDrink() != null) {
            return tileEntityCup.getDrink().copy();
        }
        return new ItemStack(MrCrayfishFurnitureMod.itemCup);
    }

    public void registerIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = Blocks.stained_glass_pane.getBlockTextureFromSide(0);
    }
}

