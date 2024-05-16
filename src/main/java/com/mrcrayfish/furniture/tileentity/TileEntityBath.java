/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 */
package com.mrcrayfish.furniture.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBath
extends TileEntity {
    private int waterLevel = 0;

    public boolean hasWater() {
        return this.waterLevel > 0;
    }

    public boolean isFull() {
        return this.waterLevel == 16;
    }

    public int getWaterLevel() {
        return this.waterLevel;
    }

    public void addWaterLevel() {
        if (this.waterLevel < 16) {
            ++this.waterLevel;
        }
    }

    public void removeWaterLevel() {
        if (this.waterLevel > 0) {
            --this.waterLevel;
        }
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        this.waterLevel = par1NBTTagCompound.getInteger("waterLevel");
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("waterLevel", this.waterLevel);
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tag);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        this.readFromNBT(packet.func_148857_g());
    }

    public boolean canUpdate() {
        return false;
    }
}

