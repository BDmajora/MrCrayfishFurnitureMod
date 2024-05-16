/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ISidedInventory
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

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.api.RecipeAPI;
import com.mrcrayfish.furniture.api.RecipeData;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDishwasher
extends TileEntity
implements ISidedInventory {
    private ItemStack[] inventory = new ItemStack[7];
    private boolean washing = false;
    public boolean superMode = false;
    public int progress = 0;
    public int timeRemaining = 0;
    private Random rand = new Random();
    private int timer = 0;

    public void startWashing() {
        if (this.canWash()) {
            if (this.timeRemaining == 0) {
                this.superMode = this.inventory[6].getItem() == MrCrayfishFurnitureMod.itemSuperSoapyWater;
                this.inventory[6] = new ItemStack(this.inventory[6].getItem().getContainerItem());
                this.timeRemaining = 5000;
            }
            this.washing = true;
        }
    }

    public void stopWashing() {
        this.progress = 0;
        this.washing = false;
    }

    public boolean canWash() {
        if (this.inventory[6] == null && this.timeRemaining == 0) {
            return false;
        }
        if (this.inventory[6] != null && this.timeRemaining == 0 && this.inventory[6].getItem() != MrCrayfishFurnitureMod.itemSoapyWater && this.inventory[6].getItem() != MrCrayfishFurnitureMod.itemSuperSoapyWater) {
            return false;
        }
        boolean flag = false;
        for (int i = 0; i < 6; ++i) {
            if (this.inventory[i] == null) continue;
            RecipeData data = RecipeAPI.getDishwasherRecipeFromInput(this.inventory[i]);
            if (data == null) {
                return false;
            }
            flag = true;
        }
        return flag;
    }

    public boolean isWashing() {
        return this.washing;
    }

    public void updateEntity() {
        if (this.washing) {
            if (this.canRepair()) {
                for (int i = 0; i < 6; ++i) {
                    if (this.inventory[i] == null || this.inventory[i].getMaxDurability() - this.inventory[i].getMetadata() == this.inventory[i].getMaxDurability()) continue;
                    this.inventory[i].setMetadata(this.inventory[i].getMetadata() - 1);
                }
            }
            --this.timeRemaining;
            if (this.timeRemaining <= 0) {
                if (this.inventory[4] != null) {
                    if (this.inventory[4].getItem() == MrCrayfishFurnitureMod.itemSoapyWater) {
                        this.superMode = false;
                        this.inventory[4] = new ItemStack(MrCrayfishFurnitureMod.itemSoapyWater.getContainerItem());
                        this.timeRemaining = 5000;
                    } else if (this.inventory[4].getItem() == MrCrayfishFurnitureMod.itemSuperSoapyWater) {
                        this.superMode = true;
                        this.inventory[4] = new ItemStack(MrCrayfishFurnitureMod.itemSuperSoapyWater.getContainerItem());
                        this.timeRemaining = 5000;
                    }
                } else {
                    this.timeRemaining = 0;
                    this.progress = 0;
                    this.washing = false;
                }
            }
            ++this.progress;
            if (this.timer == 20) {
                this.timer = 0;
            }
            if (this.timer == 0) {
                this.worldObj.playSoundEffect((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, "cfm:dishwasher", 0.75f, 1.0f);
            }
            ++this.timer;
        }
    }

    public boolean canRepair() {
        return this.progress % (this.superMode ? 20 : 50) == 0;
    }

    public int getSizeInventory() {
        return 7;
    }

    public ItemStack getStackInSlot(int slot) {
        return this.inventory[slot];
    }

    public ItemStack decrStackSize(int slot, int number) {
        if (this.inventory[slot] != null) {
            if (this.inventory[slot].stackSize <= number) {
                ItemStack itemstack = this.inventory[slot];
                this.inventory[slot] = null;
                return itemstack;
            }
            ItemStack itemstack = this.inventory[slot].splitStack(number);
            if (this.inventory[slot].stackSize == 0) {
                this.inventory[slot] = null;
            }
            return itemstack;
        }
        return null;
    }

    public ItemStack getStackInSlotOnClosing(int slot) {
        return this.inventory[slot];
    }

    public void setInventorySlotContents(int slot, ItemStack par2ItemStack) {
        this.inventory[slot] = par2ItemStack;
        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    public String getInventoryName() {
        return "Dishwasher";
    }

    public boolean isCustomInventoryName() {
        return false;
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
    }

    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        if (tagCompound.hasKey("Items")) {
            NBTTagList tagList = (NBTTagList)tagCompound.getTag("Items");
            this.inventory = new ItemStack[7];
            for (int i = 0; i < tagList.tagCount(); ++i) {
                NBTTagCompound nbt = tagList.getCompoundTagAt(i);
                byte slot = nbt.getByte("Slot");
                if (slot < 0 || slot >= this.inventory.length) continue;
                this.inventory[slot] = ItemStack.loadItemStackFromNBT((NBTTagCompound)nbt);
            }
        }
        this.washing = tagCompound.getBoolean("Washing");
        this.superMode = tagCompound.getBoolean("SuperMode");
        this.progress = tagCompound.getInteger("Progress");
        this.timeRemaining = tagCompound.getInteger("Remaining");
    }

    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        NBTTagList tagList = new NBTTagList();
        for (int slot = 0; slot < this.inventory.length; ++slot) {
            if (this.inventory[slot] == null) continue;
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("Slot", (byte)slot);
            this.inventory[slot].writeToNBT(nbt);
            tagList.appendTag((NBTBase)nbt);
        }
        tagCompound.setTag("Items", (NBTBase)tagList);
        tagCompound.setBoolean("Washing", this.washing);
        tagCompound.setBoolean("SuperMode", this.superMode);
        tagCompound.setInteger("Progress", this.progress);
        tagCompound.setInteger("Remaining", this.timeRemaining);
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

    public void openChest() {
    }

    public void closeChest() {
    }

    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }

    public int[] getSlotsForFace(int p_94128_1_) {
        return null;
    }

    public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
        return false;
    }

    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return false;
    }
}

