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

public class TileEntityChoppingBoard
extends TileEntity {
    private ItemStack food = null;

    public void setFood(ItemStack food) {
        this.food = food;
    }

    public ItemStack getFood() {
        return this.food;
    }

    public void chopFood(ItemStack knife) {
        RecipeData data;
        if (this.food != null && (data = RecipeAPI.getChoppingBoardRecipeFromInput(this.food)) != null) {
            if (!this.worldObj.isRemote) {
                EntityItem entityItem = new EntityItem(this.worldObj, (double)this.xCoord + 0.5, (double)this.yCoord + 0.2, (double)this.zCoord + 0.5, data.getOutput().copy());
                this.worldObj.spawnEntityInWorld((Entity)entityItem);
                this.worldObj.playSoundEffect((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, "cfm:knife_chop", 0.75f, 1.0f);
            }
            this.setFood(null);
            knife.setMetadata(knife.getMetadata() + 1);
        }
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

