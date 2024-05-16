/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package com.mrcrayfish.furniture.gui.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerComputer
extends Container {
    public ContainerComputer(IInventory par1IInventory, IInventory par2IInventory) {
        int i;
        par2IInventory.openInventory();
        this.addSlotToContainer(new Slot(par2IInventory, 0, 119, 40));
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(par1IInventory, j + i * 9 + 9, j * 18 + 8, i * 18 + 95));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(par1IInventory, i, i * 18 + 8, 153));
        }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return true;
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (par2 == 0 ? !this.mergeItemStack(itemstack1, 1, this.inventorySlots.size(), true) : !this.mergeItemStack(itemstack1, 0, 1, false)) {
                return null;
            }
            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack)null);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }

    public void onContainerClosed(EntityPlayer par1EntityPlayer) {
        super.onContainerClosed(par1EntityPlayer);
    }
}

