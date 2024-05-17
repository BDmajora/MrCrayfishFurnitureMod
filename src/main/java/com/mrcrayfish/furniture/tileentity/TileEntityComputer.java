/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 */
package com.mrcrayfish.furniture.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityComputer
extends TileEntity {
    private ItemStack paySlot = null;
    public int stockNum = 0;
    private boolean isTrading = false;

    public int getSizeInventory() {
        return 1;
    }

    public ItemStack getStackInSlot(int par1) {
        return this.paySlot;
    }

    public ItemStack decrStackSize(int par1, int par2) {
        if (this.paySlot != null) {
            if (this.paySlot.stackSize <= par2) {
                ItemStack var3 = this.paySlot;
                this.paySlot = null;
                this.markDirty();
                return var3;
            }
            ItemStack var3 = this.paySlot.splitStack(par2);
            if (this.paySlot.stackSize == 0) {
                this.paySlot = null;
            }
            this.markDirty();
            return var3;
        }
        return null;
    }

    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.paySlot != null) {
            ItemStack var2 = this.paySlot;
            this.paySlot = null;
            return var2;
        }
        return null;
    }

    public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
        this.paySlot = par2ItemStack;
        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
        this.markDirty();
    }

    public String getInvName() {
        return "Computer";
    }

    public void takeEmeraldFromSlot(int price) {
        if (this.paySlot != null) {
            this.paySlot.stackSize -= price;
        }
        this.markDirty();
    }

    public void clearInventory() {
        this.paySlot = null;
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        this.stockNum = par1NBTTagCompound.getInteger("StockNum");
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("StockNum", this.stockNum);
    }

    public void setBrowsingInfo(int stockNum) {
        this.stockNum = stockNum;
    }

    public int getBrowsingInfo() {
        return this.stockNum;
    }

    public void setTrading(boolean isUsing) {
        this.isTrading = isUsing;
    }

    public boolean isTrading() {
        return this.isTrading;
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
    }

    public void updateContainingBlockInfo() {
        super.updateContainingBlockInfo();
    }

    public void updateEntity() {
        super.updateEntity();
    }

    public void openChest() {
    }

    public void closeChest() {
        this.setTrading(false);
    }

    public void invalidate() {
        this.updateContainingBlockInfo();
        super.invalidate();
    }

    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return false;
    }

    public String getInventoryName() {
        return "Computer";
    }

    public boolean isCustomInventoryName() {
        return false;
    }
}

