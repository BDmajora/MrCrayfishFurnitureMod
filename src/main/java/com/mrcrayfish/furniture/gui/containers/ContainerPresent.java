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

import com.mrcrayfish.furniture.items.IMail;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerPresent
extends Container {
    private int numRows;
    private IInventory present;

    public ContainerPresent(IInventory par1IInventory, IInventory par2IInventory) {
        int var4;
        this.present = par2IInventory;
        par2IInventory.openInventory();
        int var3 = (this.numRows - 4) * 18;
        this.addSlotToContainer(new Slot(par2IInventory, 0, 71, 16));
        this.addSlotToContainer(new Slot(par2IInventory, 1, 89, 16));
        this.addSlotToContainer(new Slot(par2IInventory, 2, 71, 34));
        this.addSlotToContainer(new Slot(par2IInventory, 3, 89, 34));
        for (var4 = 0; var4 < 3; ++var4) {
            for (int var5 = 0; var5 < 9; ++var5) {
                this.addSlotToContainer(new Slot(par1IInventory, var5 + var4 * 9 + 9, 8 + var5 * 18, 103 + var4 * 18 + var3 + 53));
            }
        }
        for (var4 = 0; var4 < 9; ++var4) {
            this.addSlotToContainer(new Slot(par1IInventory, var4, 8 + var4 * 18, 161 + var3 + 53));
        }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return true;
    }

    public ItemStack slotClick(int par1, int par2, int par3, EntityPlayer par4EntityPlayer) {
        this.present.closeInventory();
        return super.slotClick(par1, par2, par3, par4EntityPlayer);
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(par2);
        if (var4 != null && var4.getHasStack()) {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();
            if (var5.getItem() instanceof IMail) {
                return null;
            }
            if (par2 < 4 ? !this.mergeItemStack(var5, 4, this.inventorySlots.size(), true) : !this.mergeItemStack(var5, 0, 4, false)) {
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
        this.present.closeInventory();
    }
}

