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

public class TileEntityBin
extends TileEntity
implements IInventory {
    private ItemStack[] binContents = new ItemStack[12];

    public int getSizeInventory() {
        return this.binContents.length;
    }

    public ItemStack getStackInSlot(int i) {
        return this.binContents[i];
    }

    public ItemStack decrStackSize(int par1, int par2) {
        if (this.binContents[par1] != null) {
            if (this.binContents[par1].stackSize <= par2) {
                ItemStack var3 = this.binContents[par1];
                this.binContents[par1] = null;
                this.markDirty();
                return var3;
            }
            ItemStack var3 = this.binContents[par1].splitStack(par2);
            if (this.binContents[par1].stackSize == 0) {
                this.binContents[par1] = null;
            }
            this.markDirty();
            return var3;
        }
        return null;
    }

    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.binContents[par1] != null) {
            ItemStack var2 = this.binContents[par1];
            this.binContents[par1] = null;
            return var2;
        }
        return null;
    }

    public void setInventorySlotContents(int i, ItemStack itemstack) {
        this.binContents[i] = itemstack;
        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
        this.markDirty();
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList var2 = (NBTTagList)par1NBTTagCompound.getTag("Items");
        this.binContents = new ItemStack[this.getSizeInventory()];
        for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
            NBTTagCompound var4 = var2.getCompoundTagAt(var3);
            int var5 = var4.getByte("Slot") & 0xFF;
            if (var5 < 0 || var5 >= this.binContents.length) continue;
            this.binContents[var5] = ItemStack.loadItemStackFromNBT((NBTTagCompound)var4);
        }
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < this.binContents.length; ++var3) {
            if (this.binContents[var3] == null) continue;
            NBTTagCompound var4 = new NBTTagCompound();
            var4.setByte("Slot", (byte)var3);
            this.binContents[var3].writeToNBT(var4);
            var2.appendTag((NBTBase)var4);
        }
        par1NBTTagCompound.setTag("Items", (NBTBase)var2);
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
    }

    public void openChest() {
        this.worldObj.playSoundEffect((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, "cfm:binopen", 0.75f, 1.0f);
    }

    public void closeChest() {
        this.worldObj.playSoundEffect((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, "cfm:binclose", 0.75f, 1.0f);
    }

    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return true;
    }

    public void empty() {
        for (int i = 0; i < this.binContents.length; ++i) {
            this.binContents[i] = null;
        }
    }

    public String getInventoryName() {
        return "Bin";
    }

    public boolean isCustomInventoryName() {
        return false;
    }

    public boolean canUpdate() {
        return false;
    }
}

