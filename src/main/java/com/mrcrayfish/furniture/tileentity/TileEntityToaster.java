/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 */
package com.mrcrayfish.furniture.tileentity;

import com.mrcrayfish.furniture.api.RecipeAPI;
import com.mrcrayfish.furniture.api.RecipeData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityToaster
extends TileEntity {
    public ItemStack[] slots = new ItemStack[2];
    private int toastingTime = 0;
    private boolean toasting = false;

    public void addSlice(ItemStack item) {
        for (int i = 0; i < this.slots.length; ++i) {
            if (this.slots[i] != null) continue;
            this.slots[i] = item.copy();
            return;
        }
    }

    public void removeSlice() {
        for (int i = 0; i < this.slots.length; ++i) {
            if (this.slots[i] == null) continue;
            if (!this.worldObj.isRemote) {
                EntityItem entityItem = new EntityItem(this.worldObj, (double)this.xCoord + 0.5, (double)this.yCoord + 0.6, (double)this.zCoord + 0.5, this.slots[i]);
                this.worldObj.spawnEntityInWorld((Entity)entityItem);
            }
            this.slots[i] = null;
            return;
        }
    }

    public void startToasting() {
        this.toasting = true;
    }

    public boolean isToasting() {
        return this.toasting;
    }

    public ItemStack getSlice(int slot) {
        return this.slots[slot];
    }

    public void updateEntity() {
        if (this.toasting) {
            if (this.toastingTime == 200) {
                for (int i = 0; i < this.slots.length; ++i) {
                    if (this.slots[i] == null) continue;
                    if (!this.worldObj.isRemote) {
                        RecipeData data = RecipeAPI.getToasterRecipeFromInput(this.slots[i]);
                        EntityItem entityItem = new EntityItem(this.worldObj, (double)this.xCoord + 0.5, (double)this.yCoord + 0.6, (double)this.zCoord + 0.5, data.getOutput().copy());
                        this.worldObj.spawnEntityInWorld((Entity)entityItem);
                    }
                    this.slots[i] = null;
                }
                if (!this.worldObj.isRemote) {
                    this.worldObj.playSoundEffect((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, "cfm:toaster_down", 0.75f, 1.0f);
                }
                this.toastingTime = 0;
                this.toasting = false;
            } else {
                ++this.toastingTime;
            }
        }
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = (NBTTagList)par1NBTTagCompound.getTag("Items");
        this.slots = new ItemStack[2];
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");
            if (b0 < 0 || b0 >= this.slots.length) continue;
            this.slots[b0] = ItemStack.loadItemStackFromNBT((NBTTagCompound)nbttagcompound1);
        }
        this.toastingTime = par1NBTTagCompound.getInteger("ToastTime");
        this.toasting = par1NBTTagCompound.getBoolean("Toasting");
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.slots.length; ++i) {
            if (this.slots[i] == null) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setByte("Slot", (byte)i);
            this.slots[i].writeToNBT(nbttagcompound1);
            nbttaglist.appendTag((NBTBase)nbttagcompound1);
        }
        par1NBTTagCompound.setTag("Items", (NBTBase)nbttaglist);
        par1NBTTagCompound.setInteger("ToastTime", this.toastingTime);
        par1NBTTagCompound.setBoolean("Toasting", this.toasting);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound tagCom = pkt.getNbtCompound();
        this.readFromNBT(tagCom);
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound tagCom = new NBTTagCompound();
        this.writeToNBT(tagCom);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, tagCom);
    }
}

