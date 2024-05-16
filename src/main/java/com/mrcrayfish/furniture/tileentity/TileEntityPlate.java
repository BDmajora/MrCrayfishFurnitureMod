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

public class TileEntityPlate
extends TileEntity {
    private ItemStack food = null;
    private int rotation = 0;

    public void setFood(ItemStack food) {
        this.food = food;
    }

    public ItemStack getFood() {
        return this.food;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public int getRotation() {
        return this.rotation;
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        if (par1NBTTagCompound.hasKey("Items")) {
            NBTTagList tagList = (NBTTagList)par1NBTTagCompound.getTag("Items");
            for (int i = 0; i < tagList.tagCount(); ++i) {
                NBTTagCompound nbttagcompound1 = tagList.getCompoundTagAt(i);
                ItemStack stack = ItemStack.loadItemStackFromNBT((NBTTagCompound)nbttagcompound1);
                this.setFood(stack);
            }
        }
        this.rotation = par1NBTTagCompound.getInteger("Rotation");
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList tagList = new NBTTagList();
        ItemStack itemStack = this.food;
        if (itemStack != null) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            itemStack.writeToNBT(nbttagcompound1);
            tagList.appendTag((NBTBase)nbttagcompound1);
        }
        par1NBTTagCompound.setTag("Items", (NBTBase)tagList);
        par1NBTTagCompound.setInteger("Rotation", this.rotation);
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

