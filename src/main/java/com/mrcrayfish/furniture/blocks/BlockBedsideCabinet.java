/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.blocks;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.proxy.ClientProxy;
import com.mrcrayfish.furniture.proxy.CommonProxy;
import com.mrcrayfish.furniture.tileentity.TileEntityBedsideCabinet;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBedsideCabinet
extends BlockContainer {
    private Random random = new Random();

    public BlockBedsideCabinet(Material material) {
        super(material);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return CommonProxy.renderBedsideCabinet;
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

    public boolean canProvidePower() {
        return true;
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (world.getBlock(x, y + 1, z) == MrCrayfishFurnitureMod.lampOn) {
            world.notifyBlocksOfNeighborChange(x, y, z, block);
        }
    }

    public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        if (par1IBlockAccess.getBlock(par2, par3 + 1, par4) == MrCrayfishFurnitureMod.lampOn) {
            return 15;
        }
        return 0;
    }

    public Item getItemDropped(int i, Random random, int j) {
        return MrCrayfishFurnitureMod.itemBedsideCabinet;
    }

    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        TileEntityBedsideCabinet var7 = (TileEntityBedsideCabinet)par1World.getTileEntity(par2, par3, par4);
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

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        TileEntity tile_entity = par1World.getTileEntity(par2, par3, par4);
        if (tile_entity == null || par5EntityPlayer.isSneaking()) {
            return false;
        }
        par5EntityPlayer.openGui((Object)MrCrayfishFurnitureMod.instance, 0, par1World, par2, par3, par4);
        return true;
    }

    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityBedsideCabinet();
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack(MrCrayfishFurnitureMod.itemBedsideCabinet);
    }

    public void registerIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = Blocks.planks.getBlockTextureFromSide(1);
    }

    public Block setUnlocalizedName(String bedsidecabinet) {
        return null;
    }
}

