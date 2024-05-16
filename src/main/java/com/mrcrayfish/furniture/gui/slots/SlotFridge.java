/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 */
package com.mrcrayfish.furniture.gui.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class SlotFridge
extends Slot {
    public SlotFridge(IInventory inventory, int slotIndex, int x, int y) {
        super(inventory, slotIndex, x, y);
    }

    public boolean isItemValid(ItemStack par1ItemStack) {
        return par1ItemStack != null && par1ItemStack.getItem() instanceof ItemFood;
    }
}

