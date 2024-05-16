/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
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
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.blocks;

import com.mrcrayfish.furniture.FurnitureAchievements;
import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.proxy.ClientProxy;
import com.mrcrayfish.furniture.proxy.CommonProxy;
import com.mrcrayfish.furniture.tileentity.TileEntityStereo;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
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
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockStereo
extends BlockContainer {
    public static ArrayList records = new ArrayList();

    public BlockStereo(Material material) {
        super(material);
        records.add(new ItemStack(Items.record_13));
        records.add(new ItemStack(Items.record_blocks));
        records.add(new ItemStack(Items.record_cat));
        records.add(new ItemStack(Items.record_chirp));
        records.add(new ItemStack(Items.record_far));
        records.add(new ItemStack(Items.record_mall));
        records.add(new ItemStack(Items.record_mellohi));
        records.add(new ItemStack(Items.record_stal));
        records.add(new ItemStack(Items.record_strad));
        records.add(new ItemStack(Items.record_wait));
        records.add(new ItemStack(Items.record_ward));
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return CommonProxy.renderStereo;
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

    public Item getItemDropped(int par1, Random par2Random, int par3) {
        return MrCrayfishFurnitureMod.itemStereo;
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        if (par1World.getBlockMetadata(par2, par3, par4) == 3 | par1World.getBlockMetadata(par2, par3, par4) == 1) {
            this.setBlockBounds(0.3f, 0.0f, 0.0f, 0.7f, 0.5f, 1.0f);
        }
        if (par1World.getBlockMetadata(par2, par3, par4) == 2 | par1World.getBlockMetadata(par2, par3, par4) == 0) {
            this.setBlockBounds(0.0f, 0.0f, 0.3f, 1.0f, 0.5f, 0.7f);
        }
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        this.ejectRecord(par1World, par2, par3, par4);
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        ((EntityPlayer)par5EntityLivingBase).triggerAchievement((StatBase)FurnitureAchievements.modernTechnology);
    }

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        TileEntity tile_entity;
        if (!par1World.isRemote && (tile_entity = par1World.getTileEntity(par2, par3, par4)) instanceof TileEntityStereo) {
            TileEntityStereo tileEntityStereo = (TileEntityStereo)tile_entity;
            if (!par5EntityPlayer.isSneaking()) {
                tileEntityStereo.count = tileEntityStereo.count == 13 ? records.size() : ++tileEntityStereo.count;
                if (tileEntityStereo.count == records.size()) {
                    tileEntityStereo.count = 0;
                    tileEntityStereo.setRecord((ItemStack)records.get(0));
                } else {
                    tileEntityStereo.setRecord((ItemStack)records.get(tileEntityStereo.count));
                }
                par5EntityPlayer.triggerAchievement((StatBase)FurnitureAchievements.houseParty);
                ItemStack itemstack = (ItemStack)records.get(tileEntityStereo.count);
                par1World.playAuxSFXAtEntity((EntityPlayer)null, 1005, par2, par3, par4, Item.getIdFromItem((Item)itemstack.getItem()));
            } else if (tileEntityStereo.count != 13) {
                tileEntityStereo.count = 13;
                par5EntityPlayer.addChatComponentMessage((IChatComponent)new ChatComponentText("Stereo is now turned off."));
                par1World.playAuxSFX(1005, par2, par3, par4, 0);
                par1World.playRecord((String)null, par2, par3, par4);
                tileEntityStereo.setRecord(null);
            } else {
                par5EntityPlayer.addChatComponentMessage((IChatComponent)new ChatComponentText("Stereo is already turned off."));
            }
        }
        return true;
    }

    public void ejectRecord(World par1World, int par2, int par3, int par4) {
        TileEntityStereo tileentitystereo;
        if (!par1World.isRemote && (tileentitystereo = (TileEntityStereo)par1World.getTileEntity(par2, par3, par4)) != null) {
            par1World.playAuxSFX(1005, par2, par3, par4, 0);
            par1World.playRecord((String)null, par2, par3, par4);
            tileentitystereo.setRecord(null);
        }
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack(MrCrayfishFurnitureMod.itemStereo);
    }

    public void registerIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = Blocks.planks.getBlockTextureFromSide(1);
    }

    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityStereo();
    }
}

