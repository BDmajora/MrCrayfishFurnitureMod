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
import com.mrcrayfish.furniture.gui.slots.SlotFreezer;
import com.mrcrayfish.furniture.tileentity.TileEntityFreezer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;

public class ContainerFreezer
extends Container {
    private TileEntityFreezer freezer;
    private int lastCookTime = 0;
    private int lastBurnTime = 0;
    private int lastItemBurnTime = 0;

    public ContainerFreezer(InventoryPlayer inventoryplayer, TileEntityFreezer tileentityFreezer) {
        this.freezer = tileentityFreezer;
        this.addSlotToContainer(new Slot((IInventory)tileentityFreezer, 0, 63, 27));
        this.addSlotToContainer(new Slot((IInventory)tileentityFreezer, 1, 32, 27));
        this.addSlotToContainer(new SlotFreezer((IInventory)tileentityFreezer, 2, 123, 27));
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
        super.onCraftMatrixChanged((IInventory) par1ICrafting);
        par1ICrafting.sendProgressBarUpdate((Container)this, 0, this.freezer.freezerCoolTime);
        par1ICrafting.sendProgressBarUpdate((Container)this, 1, this.freezer.freezerFreezeTime);
        par1ICrafting.sendProgressBarUpdate((Container)this, 2, this.freezer.currentItemCoolTime);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int var1 = 0; var1 < this.crafters.size(); ++var1) {
            ICrafting var2 = (ICrafting)this.crafters.get(var1);
            if (this.lastCookTime != this.freezer.freezerCoolTime) {
                var2.sendProgressBarUpdate((Container)this, 0, this.freezer.freezerCoolTime);
            }
            if (this.lastBurnTime != this.freezer.freezerFreezeTime) {
                var2.sendProgressBarUpdate((Container)this, 1, this.freezer.freezerFreezeTime);
            }
            if (this.lastItemBurnTime == this.freezer.currentItemCoolTime) continue;
            var2.sendProgressBarUpdate((Container)this, 2, this.freezer.currentItemCoolTime);
        }
        this.lastCookTime = this.freezer.freezerCoolTime;
        this.lastBurnTime = this.freezer.freezerFreezeTime;
        this.lastItemBurnTime = this.freezer.currentItemCoolTime;
    }

    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            this.freezer.freezerCoolTime = par2;
        }
        if (par1 == 1) {
            this.freezer.freezerFreezeTime = par2;
        }
        if (par1 == 2) {
            this.freezer.currentItemCoolTime = par2;
        }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.freezer.isUseableByPlayer(par1EntityPlayer);
    }

    public ItemStack slotClick(int par1, int par2, int par3, EntityPlayer par4EntityPlayer) {
        Slot var4;
        if (par1 == 2 && (var4 = (Slot)this.inventorySlots.get(par1)) != null && var4.getHasStack()) {
            par4EntityPlayer.triggerAchievement((StatBase)FurnitureAchievements.freezeItem);
        }
        return super.slotClick(par1, par2, par3, par4EntityPlayer);
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(par2);
        if (var4 != null && var4.getHasStack()) {
            RecipeData data;
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();
            if (par2 == 2) {
                if (!this.mergeItemStack(var5, 3, 39, true)) {
                    return null;
                }
                var4.onSlotChange(var5, var3);
            } else if (par2 != 1 && par2 != 0 ? ((data = RecipeAPI.getFreezerRecipeFromInput(var5)) != null ? !this.mergeItemStack(var5, 0, 1, false) : (TileEntityFreezer.isItemFuel(var5) ? !this.mergeItemStack(var5, 1, 2, false) : (par2 >= 3 && par2 < 30 ? !this.mergeItemStack(var5, 30, 39, false) : par2 >= 30 && par2 < 39 && !this.mergeItemStack(var5, 3, 30, false)))) : !this.mergeItemStack(var5, 3, 39, false)) {
                return null;
            }
            if (var5.stackSize == 0) {
                var4.putStack((ItemStack)null);
            } else {
                var4.onSlotChanged();
            }
            if (var5.stackSize == var3.stackSize) {
                return null;
            }
            var4.onPickupFromSlot(par1EntityPlayer, var5);
        }
        return var3;
    }
}

