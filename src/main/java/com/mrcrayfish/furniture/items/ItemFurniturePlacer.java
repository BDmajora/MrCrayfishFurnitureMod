/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.items;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemFurniturePlacer
extends Item {
    private Block spawnBlock;

    public ItemFurniturePlacer(Block block) {
        this.spawnBlock = block;
        this.maxStackSize = 64;
    }

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        Block block = par3World.getBlock(par4, par5, par6);
        int metadata = MathHelper.floor_double((double)((double)(par2EntityPlayer.rotationYaw * 4.0f / 360.0f) + 0.5)) & 3;
        if (block == Blocks.snow_layer && (par3World.getBlockMetadata(par4, par5, par6) & 7) < 1) {
            par7 = 1;
        } else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush) {
            if (par7 == 0) {
                --par5;
            }
            if (par7 == 1) {
                ++par5;
            }
            if (par7 == 2) {
                --par6;
            }
            if (par7 == 3) {
                ++par6;
            }
            if (par7 == 4) {
                --par4;
            }
            if (par7 == 5) {
                ++par4;
            }
        }
        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack)) {
            return false;
        }
        if (par1ItemStack.stackSize == 0) {
            return false;
        }
        if (par3World.canPlaceEntityOnSide(this.spawnBlock, par4, par5, par6, false, par7, (Entity)null, par1ItemStack) && this.spawnBlock.canPlaceBlockAt(par3World, par4, par5, par6)) {
            int i1 = this.spawnBlock.onBlockPlaced(par3World, par4, par5, par6, par7, par8, par9, par10, 0);
            if (par3World.setBlock(par4, par5, par6, this.spawnBlock, metadata, 3)) {
                if (par3World.getBlock(par4, par5, par6) == this.spawnBlock) {
                    this.spawnBlock.onBlockPlacedBy(par3World, par4, par5, par6, (EntityLivingBase)par2EntityPlayer, par1ItemStack);
                    this.spawnBlock.onPostBlockPlaced(par3World, par4, par5, par6, metadata);
                }
                par3World.playSoundEffect((double)((float)par4 + 0.5f), (double)((float)par5 + 0.5f), (double)((float)par6 + 0.5f), this.spawnBlock.stepSound.getBreakSound(), (this.spawnBlock.stepSound.getVolume() + 1.0f) / 2.0f, this.spawnBlock.stepSound.getPitch() * 0.8f);
                --par1ItemStack.stackSize;
            }
        }
        return true;
    }
}

