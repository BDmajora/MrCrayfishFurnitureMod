/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ICrafting
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 */
package com.mrcrayfish.furniture.gui.containers;

import com.mrcrayfish.furniture.FurnitureAchievements;
import com.mrcrayfish.furniture.api.RecipeAPI;
import com.mrcrayfish.furniture.api.RecipeData;
import com.mrcrayfish.furniture.gui.slots.SlotOven;
import com.mrcrayfish.furniture.tileentity.TileEntityOven;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;

public class ContainerOven
extends Container {
    private TileEntityOven oven;
    private int lastCookTime = 0;
    private int lastBurnTime = 0;
    private int lastItemBurnTime = 0;

    public ContainerOven(InventoryPlayer inventoryplayer, TileEntityOven tileEntityOven) {
        int y;
        int x;
        this.oven = tileEntityOven;
        int count = -1;
        for (x = 0; x < 3; ++x) {
            for (y = 0; y < 3; ++y) {
                this.addSlotToContainer(new Slot((IInventory)tileEntityOven, ++count, x * 18 + 7, y * 18 + 7));
            }
        }
        for (x = 0; x < 3; ++x) {
            for (y = 0; y < 3; ++y) {
                this.addSlotToContainer(new SlotOven((IInventory)tileEntityOven, ++count, x * 18 + 117, y * 18 + 7));
            }
        }
        for (int i = 0; i < 3; ++i) {
            for (int k = 0; k < 9; ++k) {
                this.addSlotToContainer(new Slot((IInventory)inventoryplayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }
        }
        for (int j = 0; j < 9; ++j) {
            this.addSlotToContainer(new Slot((IInventory)inventoryplayer, j, 8 + j * 18, 142));
        }
    }

    public void onCraftGuiOpened(ICrafting par1ICrafting) {
        super.onCraftGuiOpened(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate((Container)this, 0, this.oven.ovenCookTime);
        par1ICrafting.sendProgressBarUpdate((Container)this, 1, this.oven.ovenCookingTime);
        par1ICrafting.sendProgressBarUpdate((Container)this, 2, this.oven.currentItemCookTime);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int var1 = 0; var1 < this.crafters.size(); ++var1) {
            ICrafting var2 = (ICrafting)this.crafters.get(var1);
            if (this.lastCookTime != this.oven.ovenCookTime) {
                var2.sendProgressBarUpdate((Container)this, 0, this.oven.ovenCookTime);
            }
            if (this.lastBurnTime != this.oven.ovenCookingTime) {
                var2.sendProgressBarUpdate((Container)this, 1, this.oven.ovenCookingTime);
            }
            if (this.lastItemBurnTime == this.oven.currentItemCookTime) continue;
            var2.sendProgressBarUpdate((Container)this, 2, this.oven.currentItemCookTime);
        }
        this.lastCookTime = this.oven.ovenCookTime;
        this.lastBurnTime = this.oven.ovenCookingTime;
        this.lastItemBurnTime = this.oven.currentItemCookTime;
    }

    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            this.oven.ovenCookTime = par2;
        }
        if (par1 == 1) {
            this.oven.ovenCookingTime = par2;
        }
        if (par1 == 2) {
            this.oven.currentItemCookTime = par2;
        }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.oven.isUseableByPlayer(par1EntityPlayer);
    }

    public ItemStack slotClick(int par1, int par2, int par3, EntityPlayer par4EntityPlayer) {
        Slot var4;
        if (par1 > 8 && par1 < 18 && (var4 = (Slot)this.inventorySlots.get(par1)) != null && var4.getHasStack()) {
            par4EntityPlayer.triggerAchievement((StatBase)FurnitureAchievements.cookItem);
        }
        return super.slotClick(par1, par2, par3, par4EntityPlayer);
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(par2);
        if (var4 != null && var4.getHasStack()) {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();
            RecipeData data = RecipeAPI.getOvenRecipeFromInput(var5);
            if (par2 < 18 ? !this.mergeItemStack(var5, 18, this.inventorySlots.size(), true) : (data != null ? !this.mergeItemStack(var5, 0, 9, false) : (par2 >= 18 && par2 < this.inventorySlots.size() - 9 ? !this.mergeItemStack(var5, this.inventorySlots.size() - 9, this.inventorySlots.size(), false) : par2 >= this.inventorySlots.size() - 9 && par2 < this.inventorySlots.size() && !this.mergeItemStack(var5, 18, this.inventorySlots.size() - 9, false)))) {
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

