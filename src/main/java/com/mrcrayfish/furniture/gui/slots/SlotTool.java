/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemAxe
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemHoe
 *  net.minecraft.item.ItemPickaxe
 *  net.minecraft.item.ItemSpade
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 */
package com.mrcrayfish.furniture.gui.slots;

import com.mrcrayfish.furniture.tileentity.TileEntityDishwasher;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class SlotTool
extends Slot {
    private int toolType;

    public SlotTool(TileEntityDishwasher dishwasher, int id, int x, int y, int toolType) {
        super((IInventory)dishwasher, id, x, y);
        this.toolType = toolType;
    }

    public boolean isItemValid(ItemStack par1ItemStack) {
        if (par1ItemStack == null) {
            return false;
        }
        Item item = par1ItemStack.getItem();
        switch (this.toolType) {
            case 0: {
                if (!(item instanceof ItemPickaxe)) break;
                return true;
            }
            case 1: {
                if (!(item instanceof ItemSpade)) break;
                return true;
            }
            case 2: {
                if (!(item instanceof ItemSword)) break;
                return true;
            }
            case 3: {
                if (!(item instanceof ItemAxe)) break;
                return true;
            }
            case 4: {
                if (!(item instanceof ItemHoe)) break;
                return true;
            }
            case 5: {
                if (!(item instanceof ItemBow)) break;
                return true;
            }
        }
        return false;
    }
}

