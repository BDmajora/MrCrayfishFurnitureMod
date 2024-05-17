/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.block.Block
 *  net.minecraft.block.ITileEntityProvider
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.blocks;

import com.mrcrayfish.furniture.FurnitureAchievements;
import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.network.PacketHandler;
import com.mrcrayfish.furniture.network.message.MessageTVServer;
import com.mrcrayfish.furniture.proxy.ClientProxy;
import com.mrcrayfish.furniture.proxy.CommonProxy;
import com.mrcrayfish.furniture.tileentity.TileEntityTV;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTV
extends Block
implements ITileEntityProvider {
    public BlockTV(Material material) {
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
        return CommonProxy.renderTV;
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

    public void addCollisionBoxesToList(World par1World, int i, int j, int k, AxisAlignedBB par5AxisAlignedBB, List arrayList, Entity par7Entity) {
        this.setBlockBounds(0.1f, 0.0f, 0.1f, 0.9f, 0.8f, 0.9f);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        this.setBlockBounds(0.1f, 0.0f, 0.1f, 0.9f, 0.8f, 0.9f);
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        ((EntityPlayer)par5EntityLivingBase).triggerAchievement((StatBase)FurnitureAchievements.modernTechnology);
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
        TileEntity tile_entity = world.getTileEntity(i, j, k);
        if (tile_entity instanceof TileEntityTV && !entityplayer.isSneaking()) {
            TileEntityTV tileEntityTV = (TileEntityTV)tile_entity;
            if (world.isRemote) {
                if (tileEntityTV.getChannel() == 3) {
                    tileEntityTV.setChannel(0);
                } else {
                    tileEntityTV.setChannel(tileEntityTV.getChannel() + 1);
                }
            }
            world.playSoundEffect((double)i, (double)j, (double)k, "cfm:static", 0.75f, 1.0f);
            world.markBlockForUpdate(i, j, k);
            world.markBlockRangeForRenderUpdate(i, j, k, 20, 20, 20);
            if (tileEntityTV.getChannel() == 1) {
                entityplayer.triggerAchievement((StatBase)FurnitureAchievements.heyeyey);
            }
            if (world.isRemote) {
                PacketHandler.INSTANCE.sendToServer((IMessage)new MessageTVServer(tileEntityTV.getChannel(), i, j, k));
            }
        }
        return true;
    }

    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityTV();
    }

    public Item getItemDropped(int i, Random random, int j) {
        return MrCrayfishFurnitureMod.itemTV;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack(MrCrayfishFurnitureMod.itemTV);
    }

    public void registerIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = Blocks.planks.getBlockTextureFromSide(1);
    }

    public Block setUnlocalizedName(String tv) {
        return null;
    }
}

