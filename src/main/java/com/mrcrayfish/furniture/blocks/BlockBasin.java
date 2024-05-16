/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.blocks;

import com.mrcrayfish.furniture.FurnitureAchievements;
import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.network.PacketHandler;
import com.mrcrayfish.furniture.network.message.MessageFillBasin;
import com.mrcrayfish.furniture.proxy.ClientProxy;
import com.mrcrayfish.furniture.proxy.CommonProxy;
import com.mrcrayfish.furniture.tileentity.TileEntityBasin;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBasin
extends BlockContainer {
    public BlockBasin(Material material) {
        super(material);
    }

    public boolean canRenderInPass(int pass) {
        ClientProxy.renderPass = pass;
        return true;
    }

    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityBasin();
    }

    public Item getItemDropped(int i, Random random, int j) {
        return MrCrayfishFurnitureMod.itemBasin;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack(MrCrayfishFurnitureMod.itemBasin);
    }

    public int getRenderBlockPass() {
        return 1;
    }

    public int getRenderType() {
        return CommonProxy.renderBasin;
    }

    public boolean hasWaterSource(World world, int x, int y, int z) {
        return world.getBlock(x, y - 2, z) == Blocks.water && world.getBlockMetadata(x, y - 2, z) == 0;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        TileEntity tile_entity;
        if (!par1World.isRemote && (tile_entity = par1World.getTileEntity(par2, par3, par4)) instanceof TileEntityBasin) {
            TileEntityBasin tileEntityBasin = (TileEntityBasin)tile_entity;
            ItemStack currentItem = par5EntityPlayer.getHeldItem();
            if (currentItem != null) {
                if (currentItem.getItem() == Items.bucket) {
                    if (tileEntityBasin.hasWater()) {
                        if (!par5EntityPlayer.capabilities.isCreativeMode) {
                            if (currentItem.stackSize > 1) {
                                if (par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.water_bucket))) {
                                    --par5EntityPlayer.getHeldItem().stackSize;
                                }
                            } else {
                                par5EntityPlayer.setCurrentItemOrArmor(0, new ItemStack(Items.water_bucket));
                            }
                        }
                        tileEntityBasin.empty();
                    }
                } else if (currentItem.getItem() == Items.water_bucket) {
                    if (!tileEntityBasin.hasWater()) {
                        if (!par5EntityPlayer.capabilities.isCreativeMode) {
                            par5EntityPlayer.setCurrentItemOrArmor(0, new ItemStack(Items.bucket));
                        }
                        tileEntityBasin.fill();
                    }
                } else if (currentItem.getItem() == Items.glass_bottle) {
                    if (tileEntityBasin.hasWater()) {
                        if (!par5EntityPlayer.capabilities.isCreativeMode) {
                            if (currentItem.stackSize > 1) {
                                if (par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack((Item)Items.potionitem, 1, 0))) {
                                    --par5EntityPlayer.getHeldItem().stackSize;
                                }
                            } else {
                                par5EntityPlayer.setCurrentItemOrArmor(0, new ItemStack((Item)Items.potionitem, 1, 0));
                            }
                        }
                        tileEntityBasin.empty();
                    }
                } else if (currentItem.getItem() == Items.potionitem && currentItem.getMetadata() == 0) {
                    if (!tileEntityBasin.hasWater()) {
                        if (!par5EntityPlayer.capabilities.isCreativeMode) {
                            par5EntityPlayer.setCurrentItemOrArmor(0, new ItemStack(Items.glass_bottle));
                        }
                        tileEntityBasin.fill();
                    }
                } else if (!tileEntityBasin.hasWater()) {
                    if (this.hasWaterSource(par1World, par2, par3, par4)) {
                        tileEntityBasin.fill();
                        par1World.playSoundEffect((double)par2, (double)par3, (double)par4, "cfm:tap", 0.75f, 1.0f);
                        par1World.setBlockToAir(par2, par3 - 2, par4);
                    } else {
                        par5EntityPlayer.addChatComponentMessage((IChatComponent)new ChatComponentText("You need to have a water source under the block the basin is on to fill it. Alternatively you can use a water bucket to fill it."));
                    }
                }
            } else if (!tileEntityBasin.hasWater()) {
                if (this.hasWaterSource(par1World, par2, par3, par4)) {
                    tileEntityBasin.fill();
                    par1World.playSoundEffect((double)par2, (double)par3, (double)par4, "cfm:tap", 0.75f, 1.0f);
                    par1World.setBlockToAir(par2, par3 - 2, par4);
                } else {
                    par5EntityPlayer.addChatComponentMessage((IChatComponent)new ChatComponentText("You need to have a water source under the block the basin is on to fill it. Alternatively you can use a water bucket to fill it."));
                }
            }
            PacketHandler.INSTANCE.sendToAllAround((IMessage)new MessageFillBasin(tileEntityBasin.hasWater(), par2, par3, par4), new NetworkRegistry.TargetPoint(par5EntityPlayer.dimension, (double)par2, (double)par3, (double)par4, 50.0));
            par1World.markBlockForUpdate(par2, par3, par4);
        }
        return true;
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        ((EntityPlayer)par5EntityLivingBase).triggerAchievement((StatBase)FurnitureAchievements.bathroom);
    }

    public void registerIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = Blocks.quartz_block.getBlockTextureFromSide(0);
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return true;
    }
}

