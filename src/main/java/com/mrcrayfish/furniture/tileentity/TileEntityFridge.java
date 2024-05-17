/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 */
package com.mrcrayfish.furniture.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFridge
extends TileEntity
 {
    private ItemStack[] fridgeContents = new ItemStack[16];

    public int getSizeInventory() {
        return 15;
    }

    public ItemStack getStackInSlot(int par1) {
        return this.fridgeContents[par1];
    }

    public ItemStack decrStackSize(int par1, int par2) {
        if (this.fridgeContents[par1] != null) {
            if (this.fridgeContents[par1].stackSize <= par2) {
                ItemStack var3 = this.fridgeContents[par1];
                this.fridgeContents[par1] = null;
                this.markDirty();
                return var3;
            }
            ItemStack var3 = this.fridgeContents[par1].splitStack(par2);
            if (this.fridgeContents[par1].stackSize == 0) {
                this.fridgeContents[par1] = null;
            }
            this.markDirty();
            return var3;
        }
        return null;
    }

    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.fridgeContents[par1] != null) {
            ItemStack var2 = this.fridgeContents[par1];
            this.fridgeContents[par1] = null;
            return var2;
        }
        return null;
    }

    public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
        this.fridgeContents[par1] = par2ItemStack;
        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
        this.markDirty();
    }

    public String getInventoryName() {
        return "Fridge";
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList var2 = (NBTTagList)par1NBTTagCompound.getTag("fridgeItems");
        this.fridgeContents = new ItemStack[this.getSizeInventory()];
        for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
            NBTTagCompound var4 = var2.getCompoundTagAt(var3);
            int var5 = var4.getByte("fridgeSlot") & 0xFF;
            if (var5 < 0 || var5 >= this.fridgeContents.length) continue;
            this.fridgeContents[var5] = ItemStack.loadItemStackFromNBT((NBTTagCompound)var4);
        }
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < this.fridgeContents.length; ++var3) {
            if (this.fridgeContents[var3] == null) continue;
            NBTTagCompound var4 = new NBTTagCompound();
            var4.setByte("fridgeSlot", (byte)var3);
            this.fridgeContents[var3].writeToNBT(var4);
            var2.appendTag((NBTBase)var4);
        }
        par1NBTTagCompound.setTag("fridgeItems", (NBTBase)var2);
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
    }

    public void invalidate() {
        this.updateContainingBlockInfo();
        super.invalidate();
    }

    public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
        return true;
    }

    public boolean isCustomInventoryName() {
        return false;
    }
}

