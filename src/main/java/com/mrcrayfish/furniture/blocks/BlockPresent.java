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
 *  net.minecraft.stats.StatBase
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.blocks;

import com.mrcrayfish.furniture.FurnitureAchievements;
import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.proxy.ClientProxy;
import com.mrcrayfish.furniture.proxy.CommonProxy;
import com.mrcrayfish.furniture.tileentity.TileEntityPresent;
import java.util.Date;
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
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPresent
extends BlockContainer {
    public static IIcon stringIcon;

    public BlockPresent(Material material) {
        super(material);
        this.setBlockBounds(0.25f, 0.0f, 0.25f, 0.75f, 0.35f, 0.75f);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return CommonProxy.renderPresent;
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
        return null;
    }

    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        TileEntityPresent var7 = (TileEntityPresent)par1World.getTileEntity(par2, par3, par4);
        Random rand = new Random();
        if (var7 != null) {
            for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8) {
                ItemStack var9 = var7.getStackInSlot(var8);
                if (var9 == null) continue;
                float var10 = rand.nextFloat() * 0.8f + 0.1f;
                float var11 = rand.nextFloat() * 0.8f + 0.1f;
                float var12 = rand.nextFloat() * 0.8f + 0.1f;
                while (var9.stackSize > 0) {
                    int var13 = rand.nextInt(21) + 10;
                    if (var13 > var9.stackSize) {
                        var13 = var9.stackSize;
                    }
                    var9.stackSize -= var13;
                    EntityItem var14 = new EntityItem(par1World, (double)((float)par2 + var10), (double)((float)par3 + var11), (double)((float)par4 + var12), new ItemStack(var9.getItem(), var13, var9.getItemDamage()));
                    if (var9.hasTagCompound()) {
                        var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                    }
                    float var15 = 0.05f;
                    var14.motionX = (float)rand.nextGaussian() * var15;
                    var14.motionY = (float)rand.nextGaussian() * var15 + 0.2f;
                    var14.motionZ = (float)rand.nextGaussian() * var15;
                    par1World.spawnEntityInWorld((Entity)var14);
                    par1World.playSoundEffect((double)par2, (double)par3, (double)par4, "random.levelup", 0.75f, 1.0f);
                }
            }
            EntityPlayer player = par1World.getClosestPlayer((double)par2, (double)par3, (double)par4, 10.0);
            player.addChatComponentMessage((IChatComponent)new ChatComponentText(EnumChatFormatting.GREEN + "Merry Christmas" + EnumChatFormatting.RESET + " from " + EnumChatFormatting.RED + var7.ownerName));
            player.triggerAchievement((StatBase)FurnitureAchievements.unwrapPresent);
        }
    }

    public int getDaysInbetween(Date date, Date date2) {
        return (int)(date2.getTime() - date.getTime()) / 86400000;
    }

    public void addCollisionBoxesToList(World par1World, int i, int j, int k, AxisAlignedBB par5AxisAlignedBB, List arrayList, Entity par7Entity) {
        this.setBlockBounds(0.25f, 0.0f, 0.25f, 0.75f, 0.35f, 0.75f);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        this.setBlockBounds(0.25f, 0.0f, 0.25f, 0.75f, 0.35f, 0.75f);
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        if (world.getBlockMetadata(x, y, z) == 0) {
            return new ItemStack(MrCrayfishFurnitureMod.itemPresentRed);
        }
        return new ItemStack(MrCrayfishFurnitureMod.itemPresentGreen);
    }

    public void registerIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = Blocks.gold_block.getBlockTextureFromSide(1);
        stringIcon = par1IIconRegister.registerIcon("cfm:presentstring");
    }

    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityPresent();
    }
}

