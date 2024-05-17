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
import com.mrcrayfish.furniture.proxy.ClientProxy;
import com.mrcrayfish.furniture.proxy.CommonProxy;
import com.mrcrayfish.furniture.tileentity.TileEntityBlender;
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

public class BlockBlender
extends BlockContainer {
    private Random random = new Random();

    public BlockBlender(Material material) {
        super(material);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return CommonProxy.renderBlender;
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
        TileEntityBlender var7 = (TileEntityBlender)par1World.getTileEntity(par2, par3, par4);
        if (var7 != null) {
            for (int var8 = 0; var8 < 4; ++var8) {
                ItemStack var9 = var7.ingredients[var8];
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
                    if (var9.hasTagCompound()) {
                        var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                    }
                    float var15 = 0.05f;
                    var14.motionX = (float)this.random.nextGaussian() * var15;
                    var14.motionY = (float)this.random.nextGaussian() * var15 + 0.2f;
                    var14.motionZ = (float)this.random.nextGaussian() * var15;
                    par1World.spawnEntityInWorld((Entity)var14);
                }
            }
        }
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        ItemStack currentItem = par5EntityPlayer.getCurrentEquippedItem();
        TileEntity tileEntity = par1World.getTileEntity(par2, par3, par4);
        if (tileEntity instanceof TileEntityBlender) {
            TileEntityBlender tileEntityBlender = (TileEntityBlender)tileEntity;
            if (tileEntityBlender.drinkCount == 0) {
                if (currentItem != null && !tileEntityBlender.isFull() && !tileEntityBlender.isBlending()) {
                    tileEntityBlender.addIngredient(currentItem.copy());
                    par1World.markBlockForUpdate(par2, par3, par4);
                    currentItem.stackSize = 0;
                    return true;
                }
                if (par5EntityPlayer.isSneaking()) {
                    if (!tileEntityBlender.isBlending() && tileEntityBlender.hasValidIngredients()) {
                        tileEntityBlender.startBlending();
                    }
                } else if (!tileEntityBlender.isBlending()) {
                    tileEntityBlender.removeIngredient();
                }
            } else if (currentItem != null && tileEntityBlender.hasDrink()) {
                if (currentItem.getItem() == MrCrayfishFurnitureMod.itemCup) {
                    if (currentItem.stackSize == 0 | currentItem.stackSize == 1) {
                        par5EntityPlayer.setCurrentItemOrArmor(0, tileEntityBlender.getDrink());
                    } else {
                        par5EntityPlayer.inventory.addItemStackToInventory(tileEntityBlender.getDrink());
                        --currentItem.stackSize;
                    }
                    --tileEntityBlender.drinkCount;
                }
                return true;
            }
        }
        return true;
    }

    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int metadata = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        float[] data = CollisionHelper.fixRotation(metadata, 0.25f, 0.25f, 0.75f, 0.75f);
        this.setBlockBounds(data[0], 0.0f, data[1], data[2], 1.0f, data[3]);
    }

    public void addCollisionBoxesToList(World par1World, int i, int j, int k, AxisAlignedBB par5AxisAlignedBB, List arrayList, Entity par7Entity) {
        int metadata = par1World.getBlockMetadata(i, j, k);
        float[] data = CollisionHelper.fixRotation(metadata, 0.25f, 0.25f, 0.75f, 0.75f);
        this.setBlockBounds(data[0], 0.0f, data[1], data[2], 1.0f, data[3]);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
    }

    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityBlender();
    }

    public Item getItemDropped(int i, Random random, int j) {
        return MrCrayfishFurnitureMod.itemBlender;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack(MrCrayfishFurnitureMod.itemBlender);
    }

    public void registerIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = Blocks.stained_glass_pane.getBlockTextureFromSide(0);
    }

    public Block setUnlocalizedName(String blender) {
        return null;
    }
}

