/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ICrafting
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 */
package com.mrcrayfish.furniture.gui.containers;

import com.mrcrayfish.furniture.FurnitureAchievements;
import com.mrcrayfish.furniture.gui.slots.SlotPrinter;
import com.mrcrayfish.furniture.tileentity.TileEntityPrinter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;

public class ContainerPrinter
extends Container {
    private TileEntityPrinter freezer;
    private int lastCookTime = 0;
    private int lastBurnTime = 0;
    private int lastItemBurnTime = 0;

    public ContainerPrinter(InventoryPlayer inventoryplayer, TileEntityPrinter tileentityPrinter) {
        this.freezer = tileentityPrinter;
        this.addSlotToContainer(new Slot((IInventory)tileentityPrinter, 0, 80, -3));
        this.addSlotToContainer(new Slot((IInventory)tileentityPrinter, 1, 55, 22));
        this.addSlotToContainer(new SlotPrinter((IInventory)tileentityPrinter, 2, 80, 53));
        for (int i = 0; i < 3; ++i) {
            for (int k = 0; k < 9; ++k) {
                this.addSlotToContainer(new Slot((IInventory)inventoryplayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18 + 11));
            }
        }
        for (int j = 0; j < 9; ++j) {
            this.addSlotToContainer(new Slot((IInventory)inventoryplayer, j, 8 + j * 18, 153));
        }
    }

    public void onCraftGuiOpened(ICrafting par1ICrafting) {
        super.onCraftMatrixChanged((IInventory) par1ICrafting);
        par1ICrafting.sendProgressBarUpdate((Container)this, 0, this.freezer.printerPrintTime);
        par1ICrafting.sendProgressBarUpdate((Container)this, 1, this.freezer.printerPrintingTime);
        par1ICrafting.sendProgressBarUpdate((Container)this, 2, this.freezer.currentItemPrintTime);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int var1 = 0; var1 < this.crafters.size(); ++var1) {
            ICrafting var2 = (ICrafting)this.crafters.get(var1);
            if (this.lastCookTime != this.freezer.printerPrintTime) {
                var2.sendProgressBarUpdate((Container)this, 0, this.freezer.printerPrintTime);
            }
            if (this.lastBurnTime != this.freezer.printerPrintingTime) {
                var2.sendProgressBarUpdate((Container)this, 1, this.freezer.printerPrintingTime);
            }
            if (this.lastItemBurnTime == this.freezer.currentItemPrintTime) continue;
            var2.sendProgressBarUpdate((Container)this, 2, this.freezer.currentItemPrintTime);
        }
        this.lastCookTime = this.freezer.printerPrintTime;
        this.lastBurnTime = this.freezer.printerPrintingTime;
        this.lastItemBurnTime = this.freezer.currentItemPrintTime;
    }

    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            this.freezer.printerPrintTime = par2;
        }
        if (par1 == 1) {
            this.freezer.printerPrintingTime = par2;
        }
        if (par1 == 2) {
            this.freezer.currentItemPrintTime = par2;
        }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.freezer.isUseableByPlayer(par1EntityPlayer);
    }

    public ItemStack slotClick(int par1, int par2, int par3, EntityPlayer par4EntityPlayer) {
        Slot var4;
        if (par1 == 2 && (var4 = (Slot)this.inventorySlots.get(par1)) != null && var4.getHasStack()) {
            par4EntityPlayer.triggerAchievement((StatBase)FurnitureAchievements.copyItem);
        }
        return super.slotClick(par1, par2, par3, par4EntityPlayer);
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(par2);
        if (var4 != null && var4.getHasStack()) {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();
            if (par2 == 2) {
                if (!this.mergeItemStack(var5, 3, 39, true)) {
                    return null;
                }
                var4.onSlotChange(var5, var3);
            } else if (par2 != 1 && par2 != 0 ? (var3.getItem() == Items.enchanted_book | var3.getItem() == Items.written_book ? !this.mergeItemStack(var5, 0, 1, false) : (TileEntityPrinter.isItemFuel(var5) ? !this.mergeItemStack(var5, 1, 2, false) : (par2 >= 3 && par2 < 30 ? !this.mergeItemStack(var5, 30, 39, false) : par2 >= 30 && par2 < 39 && !this.mergeItemStack(var5, 3, 30, false)))) : !this.mergeItemStack(var5, 3, 39, false)) {
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

