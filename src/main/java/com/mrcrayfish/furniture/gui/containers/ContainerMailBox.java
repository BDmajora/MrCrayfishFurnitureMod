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

public class ContainerMailBox
extends Container {
    private IInventory lowerChestInventory;
    private int numRows;

    public ContainerMailBox(IInventory par1IInventory, IInventory par2IInventory) {
        int j;
        int i;
        this.lowerChestInventory = par2IInventory;
        this.numRows = par2IInventory.getSizeInventory() / 9;
        par2IInventory.openInventory();
        int var3 = (this.numRows - 4) * 18;
        int count = 0;
        for (i = 0; i < 2; ++i) {
            for (j = 0; j < 3; ++j) {
                this.addSlotToContainer(new Slot(par2IInventory, count, 62 + j * 18, 18 + i * 18));
                ++count;
            }
        }
        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(par1IInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(par1IInventory, i, 8 + i * 18, 142));
        }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.lowerChestInventory.isUseableByPlayer(par1EntityPlayer);
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(par2);
        if (var4 != null && var4.getHasStack()) {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();
            if (par2 < 6 ? !this.mergeItemStack(var5, 6, this.inventorySlots.size(), true) : !this.mergeItemStack(var5, 0, 6, false)) {
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

    public void onContainerClosed(EntityPlayer par1EntityPlayer) {
        super.onContainerClosed(par1EntityPlayer);
        this.lowerChestInventory.closeInventory();
    }

    public IInventory getLowerChestInventory() {
        return this.lowerChestInventory;
    }
}

