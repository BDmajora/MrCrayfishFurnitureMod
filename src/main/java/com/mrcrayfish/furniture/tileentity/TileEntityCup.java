/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
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

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCup
extends TileEntity {
    private ItemStack item = null;
    public int red;
    public int green;
    public int blue;

    public void setItem(ItemStack item) {
        this.item = item.copy();
    }

    public void setColour(int[] rgb) {
        this.red = rgb[0];
        this.green = rgb[1];
        this.blue = rgb[2];
    }

    public ItemStack getDrink() {
        return this.item;
    }

    public void readFromNBT(NBTTagCompound tagCompound) {
        NBTTagList tagList;
        super.readFromNBT(tagCompound);
        if (tagCompound.hasKey("Item") && (tagList = (NBTTagList)tagCompound.getTag("Item")).tagCount() > 0) {
            this.item = ItemStack.loadItemStackFromNBT((NBTTagCompound)tagList.getCompoundTagAt(0));
        }
        this.red = tagCompound.getInteger("Red");
        this.green = tagCompound.getInteger("Green");
        this.blue = tagCompound.getInteger("Blue");
    }

    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        NBTTagList tagList = new NBTTagList();
        NBTTagCompound nbt = new NBTTagCompound();
        if (this.item != null) {
            this.item.writeToNBT(nbt);
            tagList.appendTag((NBTBase)nbt);
        }
        tagCompound.setTag("Item", (NBTBase)tagList);
        tagCompound.setInteger("Red", this.red);
        tagCompound.setInteger("Green", this.green);
        tagCompound.setInteger("Blue", this.blue);
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
}

