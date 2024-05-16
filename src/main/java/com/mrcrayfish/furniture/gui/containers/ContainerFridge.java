/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 */
package com.mrcrayfish.furniture.gui.containers;

import com.mrcrayfish.furniture.gui.slots.SlotFridge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ContainerFridge
extends Container {
    private IInventory lowerChestInventory;
    private int numRows;

    public ContainerFridge(IInventory par1IInventory, IInventory par2IInventory) {
        int var4;
        this.lowerChestInventory = par2IInventory;
        par2IInventory.openInventory();
        int count = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 5; ++j) {
                this.addSlotToContainer(new SlotFridge(par2IInventory, count, j * 18 + 44, i * 18 + 18));
                ++count;
            }
        }
        for (var4 = 0; var4 < 3; ++var4) {
            for (int var5 = 0; var5 < 9; ++var5) {
                this.addSlotToContainer(new Slot(par1IInventory, var5 + var4 * 9 + 9, 8 + var5 * 18, 85 + var4 * 18));
            }
        }
        for (var4 = 0; var4 < 9; ++var4) {
            this.addSlotToContainer(new Slot(par1IInventory, var4, 8 + var4 * 18, 143));
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
            if (!(var5.getItem() instanceof ItemFood)) {
                return null;
            }
            if (par2 < 16 ? !this.mergeItemStack(var5, 16, this.inventorySlots.size(), true) : !this.mergeItemStack(var5, 0, 16, false)) {
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

