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
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 */
package com.mrcrayfish.furniture.tileentity;

import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMailBox
extends TileEntity {
    public ItemStack[] mailBoxContents = new ItemStack[6];
    public UUID ownerUUID = null;
    public String ownerName = "";
    public boolean locked = true;

    public int getSizeInventory() {
        return 6;
    }

    public void setOwner(EntityPlayer player) {
        this.ownerUUID = player.getUniqueID();
        this.ownerName = player.getCommandSenderName();
    }

    public void tryAndUpdateName(EntityPlayer player) {
        if (this.ownerUUID.toString().equalsIgnoreCase(player.getUniqueID().toString()) && !this.ownerName.equalsIgnoreCase(player.getCommandSenderName())) {
            this.ownerName = player.getCommandSenderName();
        }
    }

    public ItemStack getStackInSlot(int par1) {
        return this.mailBoxContents[par1];
    }

    public ItemStack decrStackSize(int par1, int par2) {
        if (this.mailBoxContents[par1] != null) {
            if (this.mailBoxContents[par1].stackSize <= par2) {
                ItemStack var3 = this.mailBoxContents[par1];
                this.mailBoxContents[par1] = null;
                this.markDirty();
                return var3;
            }
            ItemStack var3 = this.mailBoxContents[par1].splitStack(par2);
            if (this.mailBoxContents[par1].stackSize == 0) {
                this.mailBoxContents[par1] = null;
            }
            this.markDirty();
            return var3;
        }
        return null;
    }

    public int getMailCount() {
        int count = 0;
        for (int i = 0; i < this.getSizeInventory(); ++i) {
            if (this.mailBoxContents[i] == null) continue;
            ++count;
        }
        return count;
    }

    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.mailBoxContents[par1] != null) {
            ItemStack var2 = this.mailBoxContents[par1];
            this.mailBoxContents[par1] = null;
            return var2;
        }
        return null;
    }

    public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
        this.mailBoxContents[par1] = par2ItemStack;
        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
        this.markDirty();
    }

    public String getInventoryName() {
        return "Mail Box";
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList var2 = (NBTTagList)par1NBTTagCompound.getTag("mailBoxItems");
        if (par1NBTTagCompound.hasKey("OwnerUUID") && par1NBTTagCompound.hasKey("OwnerName")) {
            this.ownerUUID = UUID.fromString(par1NBTTagCompound.getString("OwnerUUID"));
            this.ownerName = par1NBTTagCompound.getString("OwnerName");
        }
        this.locked = par1NBTTagCompound.getBoolean("Locked");
        this.mailBoxContents = new ItemStack[this.getSizeInventory()];
        for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
            NBTTagCompound var4 = var2.getCompoundTagAt(var3);
            int var5 = var4.getByte("mailBoxSlot") & 0xFF;
            if (var5 < 0 || var5 >= this.mailBoxContents.length) continue;
            this.mailBoxContents[var5] = ItemStack.loadItemStackFromNBT((NBTTagCompound)var4);
        }
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList var2 = new NBTTagList();
        if (this.ownerUUID != null && this.ownerName != null) {
            par1NBTTagCompound.setString("OwnerUUID", this.ownerUUID.toString());
            par1NBTTagCompound.setString("OwnerName", this.ownerName);
        }
        par1NBTTagCompound.setBoolean("Locked", this.locked);
        for (int var3 = 0; var3 < this.mailBoxContents.length; ++var3) {
            if (this.mailBoxContents[var3] == null) continue;
            NBTTagCompound var4 = new NBTTagCompound();
            var4.setByte("mailBoxSlot", (byte)var3);
            this.mailBoxContents[var3].writeToNBT(var4);
            var2.appendTag((NBTBase)var4);
        }
        par1NBTTagCompound.setTag("mailBoxItems", (NBTBase)var2);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound tagCom = pkt.func_148857_g();
        this.readFromNBT(tagCom);
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound tagCom = new NBTTagCompound();
        this.writeToNBT(tagCom);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, tagCom);
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

    public boolean canOpen(TileEntityMailBox mailBox, EntityPlayer player) {
        return mailBox.ownerUUID.toString().equalsIgnoreCase(player.getUniqueID().toString());
    }

    public void addMail(ItemStack itemStack) {
        for (int i = 0; i < 6; ++i) {
            if (this.mailBoxContents[i] != null) continue;
            this.mailBoxContents[i] = itemStack;
            break;
        }
        this.markDirty();
    }

    public boolean isMailBoxFull() {
        for (int i = 0; i < 6; ++i) {
            if (this.mailBoxContents[i] != null) continue;
            return false;
        }
        return true;
    }

    public void setContents(int slotNumber, ItemStack itemStack) {
        this.mailBoxContents[slotNumber] = itemStack;
        this.markDirty();
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

    public String getOwner() {
        if (this.ownerName == null) {
            return "null";
        }
        return this.ownerName;
    }

    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return false;
    }

    public boolean isCustomInventoryName() {
        return false;
    }
}

