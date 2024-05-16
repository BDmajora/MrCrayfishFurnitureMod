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
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.items;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlockCustom
extends Item {
    private Block spawnBlock;
    private int metadata;
    private int metadata2;

    public ItemBlockCustom(Block block, int metadata) {
        this.spawnBlock = block;
        this.metadata = metadata;
        this.maxStackSize = 64;
    }

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        Block var11;
        if (this == MrCrayfishFurnitureMod.itemStonePath) {
            Random rand = new Random();
            this.metadata2 = rand.nextInt(this.metadata);
        }
        if ((var11 = par3World.getBlock(par4, par5, par6)) == Blocks.snow && (par3World.getBlockMetadata(par4, par5, par6) & 7) < 1) {
            par7 = 1;
        } else if (var11 != Blocks.vine && var11 != Blocks.tallgrass && var11 != Blocks.deadbush) {
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
        if (par3World.canPlaceEntityOnSide(this.spawnBlock, par4, par5, par6, false, par7, (Entity)null, par1ItemStack)) {
            Block var12 = this.spawnBlock;
            if (this == MrCrayfishFurnitureMod.itemStonePath) {
                if (par3World.setBlock(par4, par5, par6, var12, this.metadata2, 3)) {
                    if (par3World.getBlock(par4, par5, par6) == var12) {
                        var12.onBlockPlacedBy(par3World, par4, par5, par6, (EntityLivingBase)par2EntityPlayer, par1ItemStack);
                        var12.onPostBlockPlaced(par3World, par4, par5, par6, this.metadata2);
                    }
                    par3World.playSoundEffect((double)((float)par4 + 0.5f), (double)((float)par5 + 0.5f), (double)((float)par6 + 0.5f), var12.stepSound.getPlaceSound(), (var12.stepSound.getVolume() + 1.0f) / 2.0f, var12.stepSound.getFrequency() * 0.8f);
                    --par1ItemStack.stackSize;
                }
            } else if (par3World.setBlock(par4, par5, par6, var12, this.metadata, 3)) {
                if (par3World.getBlock(par4, par5, par6) == var12) {
                    var12.onBlockPlacedBy(par3World, par4, par5, par6, (EntityLivingBase)par2EntityPlayer, par1ItemStack);
                    var12.onPostBlockPlaced(par3World, par4, par5, par6, this.metadata);
                }
                par3World.playSoundEffect((double)((float)par4 + 0.5f), (double)((float)par5 + 0.5f), (double)((float)par6 + 0.5f), var12.stepSound.getPlaceSound(), (var12.stepSound.getVolume() + 1.0f) / 2.0f, var12.stepSound.getFrequency() * 0.8f);
                --par1ItemStack.stackSize;
            }
        }
        return true;
    }
}

