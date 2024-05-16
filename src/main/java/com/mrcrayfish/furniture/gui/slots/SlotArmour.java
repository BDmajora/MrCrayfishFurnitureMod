/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 */
package com.mrcrayfish.furniture.gui.slots;

import com.mrcrayfish.furniture.tileentity.TileEntityWashingMachine;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class SlotArmour
extends Slot {
    private int armourType;

    public SlotArmour(TileEntityWashingMachine machine, int id, int x, int y, int armourType) {
        super((IInventory)machine, id, x, y);
        this.armourType = armourType;
    }

    public boolean isItemValid(ItemStack par1ItemStack) {
        if (par1ItemStack == null) {
            return false;
        }
        if (!(par1ItemStack.getItem() instanceof ItemArmor)) {
            return false;
        }
        return ((ItemArmor)par1ItemStack.getItem()).armorType == this.armourType;
    }
}

