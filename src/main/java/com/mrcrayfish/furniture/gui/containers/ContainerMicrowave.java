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

import com.mrcrayfish.furniture.gui.slots.SlotMicrowave;
import com.mrcrayfish.furniture.tileentity.TileEntityMicrowave;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerMicrowave
extends Container {
    private IInventory playerInventory;

    public ContainerMicrowave(IInventory playerInventory, TileEntityMicrowave microwaveInventory) {
        int i;
        this.playerInventory = playerInventory;
        microwaveInventory.openChest();
        this.addSlotToContainer(new SlotMicrowave(microwaveInventory, 0, 65, 43));
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, j * 18 + 8, i * 18 + 92));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(playerInventory, i, i * 18 + 8, 150));
        }
    }

    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(par2);
        if (var4 != null && var4.getHasStack()) {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();
            if (par2 < 1 ? !this.mergeItemStack(var5, 1, this.inventorySlots.size(), false) : (par2 > 0 && par2 < 27 ? !this.mergeItemStack(var5, 27, this.inventorySlots.size(), true) : !this.mergeItemStack(var5, 1, 27, false))) {
                return null;
            }
            if (var5.stackSize == 0) {
                var4.putStack((ItemStack)null);
            } else {
                var4.onSlotChanged();
            }
        }
        return null;
    }
}

