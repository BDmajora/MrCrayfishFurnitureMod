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
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.blocks;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.api.Recipes;
import com.mrcrayfish.furniture.proxy.ClientProxy;
import com.mrcrayfish.furniture.proxy.CommonProxy;
import com.mrcrayfish.furniture.tileentity.TileEntityChoppingBoard;
import com.mrcrayfish.furniture.util.CollisionHelper;
import java.util.List;
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
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockChoppingBoard
extends BlockContainer {
    private Random random = new Random();

    public BlockChoppingBoard(Material material) {
        super(material);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return CommonProxy.renderChoppingBoard;
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

    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        ItemStack var9;
        TileEntityChoppingBoard var7 = (TileEntityChoppingBoard)par1World.getTileEntity(par2, par3, par4);
        if (var7 != null && (var9 = var7.getFood()) != null) {
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
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        ItemStack currentItem = par5EntityPlayer.getCurrentEquippedItem();
        TileEntity tileEntity = par1World.getTileEntity(par2, par3, par4);
        if (tileEntity instanceof TileEntityChoppingBoard) {
            TileEntityChoppingBoard tileEntityChoppingBoard = (TileEntityChoppingBoard)tileEntity;
            if (currentItem != null) {
                if (Recipes.getChoppingBoardRecipeFromInput(currentItem) != null && tileEntityChoppingBoard.getFood() == null) {
                    tileEntityChoppingBoard.setFood(new ItemStack(currentItem.getItem(), 1));
                    par1World.markBlockForUpdate(par2, par3, par4);
                    --currentItem.stackSize;
                    return true;
                }
                if (currentItem.getItem() == MrCrayfishFurnitureMod.itemKnife && tileEntityChoppingBoard.getFood() != null) {
                    tileEntityChoppingBoard.chopFood(currentItem);
                    return true;
                }
            }
            if (tileEntityChoppingBoard.getFood() != null) {
                if (!par1World.isRemote) {
                    EntityItem entityFood = new EntityItem(par1World, (double)par2 + 0.5, (double)par3 + 0.4, (double)par4 + 0.5, tileEntityChoppingBoard.getFood());
                    par1World.spawnEntityInWorld((Entity)entityFood);
                }
                tileEntityChoppingBoard.setFood(null);
            }
        }
        return true;
    }

    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int metadata = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        float[] data = CollisionHelper.fixRotation(metadata, 0.1875f, 0.0f, 0.875f, 1.0f);
        this.setBlockBounds(data[0], 0.0f, data[1], data[2], 0.09375f, data[3]);
    }

    public void addCollisionBoxesToList(World par1World, int i, int j, int k, AxisAlignedBB par5AxisAlignedBB, List arrayList, Entity par7Entity) {
        int metadata = par1World.getBlockMetadata(i, j, k);
        float[] data = CollisionHelper.fixRotation(metadata, 0.25f, 0.0625f, 0.8125f, 0.9375f);
        this.setBlockBounds(data[0], 0.0f, data[1], data[2], 0.0625f, data[3]);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
    }

    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityChoppingBoard();
    }

    public Item getItemDropped(int i, Random random, int j) {
        return MrCrayfishFurnitureMod.itemChoppingBoard;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack(MrCrayfishFurnitureMod.itemChoppingBoard);
    }

    public void registerIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = Blocks.planks.getIcon(0, 0);
    }

    public Block setUnlocalizedName(String choppingboard) {
        return null;
    }
}

