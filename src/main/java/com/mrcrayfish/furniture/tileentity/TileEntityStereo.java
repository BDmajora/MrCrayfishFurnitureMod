/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 */
package com.mrcrayfish.furniture.tileentity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityStereo
extends TileEntity {
    private ItemStack record;
    public int count;

    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        if (par1NBTTagCompound.hasKey("RecordItem")) {
            this.setRecord(ItemStack.loadItemStackFromNBT((NBTTagCompound)par1NBTTagCompound.getCompoundTag("RecordItem")));
        } else if (par1NBTTagCompound.getInteger("Record") > 0) {
            this.setRecord(new ItemStack(Item.getItemById((int)par1NBTTagCompound.getInteger("Record")), 1, 0));
        }
        this.count = par1NBTTagCompound.getInteger("count");
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        if (this.getRecordStack() != null) {
            par1NBTTagCompound.setTag("RecordItem", (NBTBase)this.getRecordStack().writeToNBT(new NBTTagCompound()));
            par1NBTTagCompound.setInteger("Record", Item.getIdFromItem((Item)this.getRecordStack().getItem()));
        }
        par1NBTTagCompound.setInteger("count", this.count);
    }

    public ItemStack getRecordStack() {
        return this.record;
    }

    public void setRecord(ItemStack par1ItemStack) {
        this.record = par1ItemStack;
        this.markDirty();
    }

    public boolean canUpdate() {
        return false;
    }
}

