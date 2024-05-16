/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 */
package com.mrcrayfish.furniture.gui.containers;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.api.RecipeAPI;
import com.mrcrayfish.furniture.api.RecipeData;
import com.mrcrayfish.furniture.gui.slots.SlotArmour;
import com.mrcrayfish.furniture.tileentity.TileEntityWashingMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ContainerWashingMachine
extends Container {
    private IInventory playerInventory;

    public ContainerWashingMachine(IInventory playerInventory, TileEntityWashingMachine washingMachine) {
        int i;
        this.playerInventory = playerInventory;
        washingMachine.openChest();
        this.addSlotToContainer(new SlotArmour(washingMachine, 0, 80, 43, 0));
        this.addSlotToContainer(new SlotArmour(washingMachine, 1, 64, 60, 1));
        this.addSlotToContainer(new SlotArmour(washingMachine, 2, 96, 60, 2));
        this.addSlotToContainer(new SlotArmour(washingMachine, 3, 80, 76, 3));
        this.addSlotToContainer(new Slot((IInventory)washingMachine, 4, 125, 7));
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
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();
            if (par2 < 5) {
                if (!this.mergeItemStack(var5, 5, this.inventorySlots.size(), false)) {
                    return null;
                }
            } else if (par2 > 4) {
                RecipeData data = RecipeAPI.getWashingMachineRecipeFromInput(var5);
                if (data != null) {
                    if (data.getInput().getItem() instanceof ItemArmor) {
                        ItemArmor armour = (ItemArmor)data.getInput().getItem();
                        if (!this.mergeItemStack(var5, armour.armorType, armour.armorType + 1, true)) {
                            return null;
                        }
                    }
                } else if (var5.getItem() == MrCrayfishFurnitureMod.itemSoapyWater | var5.getItem() == MrCrayfishFurnitureMod.itemSuperSoapyWater ? !this.mergeItemStack(var5, 4, 5, false) : (par2 > 4 && par2 < this.inventorySlots.size() - 9 ? !this.mergeItemStack(var5, this.inventorySlots.size() - 9, this.inventorySlots.size(), false) : par2 >= this.inventorySlots.size() - 9 && par2 < this.inventorySlots.size() && !this.mergeItemStack(var5, 5, this.inventorySlots.size() - 9, false))) {
                    return null;
                }
            } else if (!this.mergeItemStack(var5, 0, 9, false)) {
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
}

