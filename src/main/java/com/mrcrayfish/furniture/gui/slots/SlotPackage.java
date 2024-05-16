/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package com.mrcrayfish.furniture.gui.slots;

import com.mrcrayfish.furniture.gui.inventory.InventoryPackage;
import com.mrcrayfish.furniture.items.IMail;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotPackage
extends Slot {
    InventoryPackage inventoryPac;

    public SlotPackage(IInventory inventory, int slotIndex, int x, int y) {
        super(inventory, slotIndex, x, y);
        this.inventoryPac = (InventoryPackage)inventory;
    }

    public boolean isItemValid(ItemStack par1ItemStack) {
        SlotPackage slotPackage = this;
        if (slotPackage.inventoryPac.isSigned()) {
            return false;
        }
        return par1ItemStack == null || !(par1ItemStack.getItem() instanceof IMail);
    }
}

