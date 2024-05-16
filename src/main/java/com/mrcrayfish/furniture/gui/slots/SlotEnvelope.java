/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package com.mrcrayfish.furniture.gui.slots;

import com.mrcrayfish.furniture.gui.inventory.InventoryEnvelope;
import com.mrcrayfish.furniture.items.IMail;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotEnvelope
extends Slot {
    InventoryEnvelope inventoryEnv;

    public SlotEnvelope(IInventory inventory, int slotIndex, int x, int y) {
        super(inventory, slotIndex, x, y);
        this.inventoryEnv = (InventoryEnvelope)inventory;
    }

    public boolean isItemValid(ItemStack par1ItemStack) {
        SlotEnvelope slotEnvelope = this;
        if (slotEnvelope.inventoryEnv.isSigned()) {
            return false;
        }
        return par1ItemStack == null || !(par1ItemStack.getItem() instanceof IMail);
    }
}

