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

public class ContainerBin
extends Container {
    private IInventory binInventory;

    public ContainerBin(IInventory playerInventory, IInventory binInventory) {
        int j;
        int i;
        this.binInventory = binInventory;
        this.binInventory.openChest();
        int count = 0;
        for (i = 0; i < 4; ++i) {
            for (j = 0; j < 3; ++j) {
                this.addSlotToContainer(new Slot(binInventory, count, j * 18 + 62, i * 18 + 18));
                ++count;
            }
        }
        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, j * 18 + 8, i * 18 + 115));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(playerInventory, i, i * 18 + 8, 173));
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(par2);
        if (var4 != null && var4.getHasStack()) {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();
            if (par2 < 12 ? !this.mergeItemStack(var5, 12, this.inventorySlots.size(), true) : !this.mergeItemStack(var5, 0, 12, false)) {
                return null;
            }
            if (var5.stackSize == 0) {
                var4.putStack((ItemStack)null);
            } else {
                var4.onSlotChanged();
            }
        }
        return var3;
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.binInventory.isUseableByPlayer(par1EntityPlayer);
    }

    public void onContainerClosed(EntityPlayer par1EntityPlayer) {
        super.onContainerClosed(par1EntityPlayer);
        this.binInventory.closeChest();
    }
}

