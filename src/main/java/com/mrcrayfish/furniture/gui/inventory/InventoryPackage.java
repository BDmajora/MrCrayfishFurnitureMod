/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.InventoryBasic
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package com.mrcrayfish.furniture.gui.inventory;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.items.IMail;
import com.mrcrayfish.furniture.util.NBTHelper;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryPackage
extends InventoryBasic {
    protected EntityPlayer playerEntity;
    protected static ItemStack packag3;
    protected boolean reading = false;
    protected String uniqueID = "";

    public InventoryPackage(EntityPlayer player, ItemStack packag3) {
        super("Package", false, InventoryPackage.getInventorySize());
        this.playerEntity = player;
        InventoryPackage.packag3 = packag3;
        if (!this.hasInventory()) {
            this.uniqueID = UUID.randomUUID().toString();
            this.createInventory();
        }
        this.loadInventory();
    }

    public void markDirty() {
        super.markDirty();
        if (!this.reading) {
            this.saveInventory();
        }
    }

    public static boolean isSigned() {
        boolean isValid = false;
        if (packag3.getItem() == MrCrayfishFurnitureMod.itemPackageSigned) {
            isValid = true;
        }
        return isValid;
    }

    public void openChest() {
        this.loadInventory();
    }

    public void closeChest() {
        this.saveInventory();
    }

    protected static int getInventorySize() {
        return 6;
    }

    protected boolean hasInventory() {
        return NBTHelper.hasTag(packag3, "Package");
    }

    protected void createInventory() {
        this.writeToNBT();
    }

    protected void setNBT() {
        for (ItemStack itemStack : this.playerEntity.inventory.mainInventory) {
            NBTTagCompound nbt;
            if (itemStack == null || !(itemStack.getItem() instanceof IMail) || (nbt = itemStack.getTagCompound()) == null || nbt.getCompoundTag("Package").getString("UniqueID") != this.uniqueID) continue;
            itemStack.setTagCompound(packag3.getTagCompound());
            break;
        }
    }

    public void loadInventory() {
        this.readFromNBT();
    }

    public void saveInventory() {
        this.writeToNBT();
        this.setNBT();
    }

    public String getSender() {
        return NBTHelper.getString(packag3, "Author");
    }

    protected void readFromNBT() {
        this.reading = true;
        NBTTagCompound nbt = NBTHelper.getCompoundTag(packag3, "Package");
        if ("".equals(this.uniqueID)) {
            this.uniqueID = nbt.getString("UniqueID");
            if ("".equals(this.uniqueID)) {
                this.uniqueID = UUID.randomUUID().toString();
            }
        }
        NBTTagList itemList = (NBTTagList)NBTHelper.getCompoundTag(packag3, "Package").getTag("Items");
        for (int i = 0; i < itemList.tagCount(); ++i) {
            NBTTagCompound slotEntry = itemList.getCompoundTagAt(i);
            int j = slotEntry.getByte("Slot") & 0xFF;
            if (j < 0 || j >= this.getSizeInventory()) continue;
            this.setInventorySlotContents(j, ItemStack.loadItemStackFromNBT((NBTTagCompound)slotEntry));
        }
        this.reading = false;
    }

    protected void writeToNBT() {
        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < this.getSizeInventory(); ++i) {
            if (this.getStackInSlot(i) == null) continue;
            NBTTagCompound slotEntry = new NBTTagCompound();
            slotEntry.setByte("Slot", (byte)i);
            this.getStackInSlot(i).writeToNBT(slotEntry);
            itemList.appendTag((NBTBase)slotEntry);
        }
        NBTTagCompound inventory = new NBTTagCompound();
        inventory.setTag("Items", (NBTBase)itemList);
        inventory.setString("UniqueID", this.uniqueID);
        NBTHelper.setCompoundTag(packag3, "Package", inventory);
    }
}

