/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
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
package com.mrcrayfish.furniture.gui.containers;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.api.RecipeAPI;
import com.mrcrayfish.furniture.api.RecipeData;
import com.mrcrayfish.furniture.gui.slots.SlotTool;
import com.mrcrayfish.furniture.tileentity.TileEntityDishwasher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
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

public class ContainerDishwasher
extends Container {
    private IInventory playerInventory;

    public ContainerDishwasher(IInventory playerInventory, TileEntityDishwasher dishwasher) {
        int i;
        this.playerInventory = playerInventory;
        dishwasher.openChest();
        this.addSlotToContainer(new SlotTool(dishwasher, 0, 56, 43, 0));
        this.addSlotToContainer(new SlotTool(dishwasher, 1, 80, 43, 1));
        this.addSlotToContainer(new SlotTool(dishwasher, 2, 104, 43, 2));
        this.addSlotToContainer(new SlotTool(dishwasher, 3, 56, 74, 3));
        this.addSlotToContainer(new SlotTool(dishwasher, 4, 80, 74, 4));
        this.addSlotToContainer(new SlotTool(dishwasher, 5, 104, 74, 5));
        this.addSlotToContainer(new Slot((IInventory)dishwasher, 6, 125, 7));
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, j * 18 + 8, i * 18 + 146));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(playerInventory, i, i * 18 + 8, 204));
        }
    }

    public boolean canInteractWith(EntityPlayer player) {
        return this.playerInventory.isUseableByPlayer(player);
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(par2);
        if (var4 != null && var4.getHasStack()) {
            int slotNum;
            RecipeData data;
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();
            if (par2 <= 6 ? !this.mergeItemStack(var5, 7, this.inventorySlots.size(), false) : par2 > 6 && ((data = RecipeAPI.getDishwasherRecipeFromInput(var5)) != null ? (slotNum = this.armourToSlot(var5)) != -1 && !this.mergeItemStack(var5, slotNum, slotNum + 1, true) : (var5.getItem() == MrCrayfishFurnitureMod.itemSoapyWater | var5.getItem() == MrCrayfishFurnitureMod.itemSuperSoapyWater ? !this.mergeItemStack(var5, 6, 7, false) : (par2 > 6 && par2 < this.inventorySlots.size() - 9 ? !this.mergeItemStack(var5, this.inventorySlots.size() - 9, this.inventorySlots.size(), false) : par2 >= this.inventorySlots.size() - 9 && par2 < this.inventorySlots.size() && !this.mergeItemStack(var5, 7, this.inventorySlots.size() - 9, false))))) {
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

    public int armourToSlot(ItemStack itemstack) {
        Item item = itemstack.getItem();
        if (item instanceof ItemPickaxe) {
            return 0;
        }
        if (item instanceof ItemSpade) {
            return 1;
        }
        if (item instanceof ItemSword) {
            return 2;
        }
        if (item instanceof ItemAxe) {
            return 3;
        }
        if (item instanceof ItemHoe) {
            return 4;
        }
        if (item instanceof ItemBow) {
            return 5;
        }
        return -1;
    }
}

