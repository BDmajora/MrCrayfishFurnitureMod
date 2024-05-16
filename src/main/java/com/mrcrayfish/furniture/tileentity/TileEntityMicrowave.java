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

import com.mrcrayfish.furniture.api.RecipeAPI;
import com.mrcrayfish.furniture.api.RecipeData;
import com.mrcrayfish.furniture.util.ParticleSpawner;
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

public class TileEntityMicrowave
extends TileEntity
implements ISidedInventory {
    private ItemStack item = null;
    private boolean cooking = false;
    public int progress = 0;
    private Random rand = new Random();
    private int timer = 0;

    public ItemStack getItem() {
        return this.item;
    }

    public void startCooking() {
        RecipeData data;
        if (this.item != null && (data = RecipeAPI.getMicrowaveRecipeFromIngredients(this.item)) != null) {
            this.cooking = true;
        }
    }

    public void stopCooking() {
        this.cooking = false;
        this.progress = 0;
    }

    public boolean isCooking() {
        return this.cooking;
    }

    public void updateEntity() {
        if (this.cooking) {
            if (this.worldObj.isRemote) {
                double posX = (double)this.xCoord + 0.35 + this.rand.nextDouble() / 3.0;
                double posZ = (double)this.zCoord + 0.35 + this.rand.nextDouble() / 3.0;
                ParticleSpawner.spawnParticle("smoke", posX, (double)this.yCoord + 0.065, posZ);
            }
            ++this.progress;
            if (this.progress >= 40) {
                RecipeData data;
                if (this.item != null && (data = RecipeAPI.getMicrowaveRecipeFromIngredients(this.item)) != null) {
                    this.item = data.getOutput().copy();
                }
                if (!this.worldObj.isRemote) {
                    this.worldObj.playSoundEffect((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, "cfm:microwave_finish", 0.75f, 1.0f);
                }
                this.timer = 0;
                this.progress = 0;
                this.cooking = false;
            } else {
                if (this.timer == 20) {
                    this.timer = 0;
                }
                if (this.timer == 0) {
                    this.worldObj.playSoundEffect((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, "cfm:microwave_running", 0.75f, 1.0f);
                }
                ++this.timer;
            }
        }
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        NBTTagList tagList;
        super.readFromNBT(par1NBTTagCompound);
        if (par1NBTTagCompound.hasKey("Item") && (tagList = (NBTTagList)par1NBTTagCompound.getTag("Item")).tagCount() > 0) {
            this.item = ItemStack.loadItemStackFromNBT((NBTTagCompound)tagList.getCompoundTagAt(0));
        }
        this.cooking = par1NBTTagCompound.getBoolean("Coooking");
        this.progress = par1NBTTagCompound.getInteger("Progress");
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList tagList = new NBTTagList();
        NBTTagCompound nbt = new NBTTagCompound();
        if (this.item != null) {
            this.item.writeToNBT(nbt);
            tagList.appendTag((NBTBase)nbt);
        }
        par1NBTTagCompound.setTag("Item", (NBTBase)tagList);
        par1NBTTagCompound.setBoolean("Coooking", this.cooking);
        par1NBTTagCompound.setInteger("Progress", this.progress);
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

    public int getSizeInventory() {
        return 1;
    }

    public ItemStack getStackInSlot(int slot) {
        return this.item;
    }

    public ItemStack decrStackSize(int slot, int number) {
        if (this.item != null) {
            if (this.item.stackSize <= number) {
                ItemStack itemstack = this.item;
                this.item = null;
                return itemstack;
            }
            ItemStack itemstack = this.item.splitStack(number);
            if (this.item.stackSize == 0) {
                this.item = null;
            }
            return itemstack;
        }
        return null;
    }

    public ItemStack getStackInSlotOnClosing(int slot) {
        return this.item;
    }

    public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
        this.item = par2ItemStack;
        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    public String getInventoryName() {
        return "Microwave";
    }

    public boolean isCustomInventoryName() {
        return false;
    }

    public int getInventoryStackLimit() {
        return 1;
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
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

