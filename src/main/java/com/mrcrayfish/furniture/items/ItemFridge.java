/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.items;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemFridge
extends Item {
    public ItemFridge() {
        this.maxStackSize = 8;
    }

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        int rotate = MathHelper.floor_double((double)((double)(par2EntityPlayer.rotationYaw * 4.0f / 360.0f) + 0.5)) & 3;
        if (!par2EntityPlayer.isSneaking() && par3World.isAirBlock(par4, par5 + 1, par6) && par3World.isAirBlock(par4, par5 + 2, par6)) {
            par3World.setBlock(par4, par5 + 1, par6, MrCrayfishFurnitureMod.freezer, rotate, 3);
            par3World.setBlock(par4, par5 + 2, par6, MrCrayfishFurnitureMod.fridge, rotate, 3);
            --par1ItemStack.stackSize;
        }
        return true;
    }
}

