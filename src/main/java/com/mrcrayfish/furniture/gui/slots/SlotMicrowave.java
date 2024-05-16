/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package com.mrcrayfish.furniture.gui.slots;

import com.mrcrayfish.furniture.tileentity.TileEntityMicrowave;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotMicrowave
extends Slot {
    private TileEntityMicrowave microwave;

    public SlotMicrowave(TileEntityMicrowave microwave, int id, int x, int y) {
        super((IInventory)microwave, id, x, y);
        this.microwave = microwave;
    }

    public boolean isItemValid(ItemStack item) {
        return !this.microwave.isCooking();
    }

    public boolean canTakeStack(EntityPlayer player) {
        return !this.microwave.isCooking();
    }
}

