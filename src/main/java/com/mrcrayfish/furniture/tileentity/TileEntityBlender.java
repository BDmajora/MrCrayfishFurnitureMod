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

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
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

public class TileEntityBlender
extends TileEntity {
    public ItemStack[] ingredients = new ItemStack[4];
    private boolean blending = false;
    public int progress = 0;
    public int drinkCount = 0;
    public String drinkName = "";
    public int healAmount;
    public int currentRed;
    public int currentGreen;
    public int currentBlue;
    int timer = 0;

    public void addIngredient(ItemStack ingredient) {
        for (int i = 0; i < this.ingredients.length; ++i) {
            if (this.ingredients[i] != null) continue;
            this.ingredients[i] = ingredient.copy();
            break;
        }
    }

    public void removeIngredient() {
        for (int i = this.ingredients.length - 1; i >= 0; --i) {
            if (this.ingredients[i] == null) continue;
            if (!this.worldObj.isRemote) {
                EntityItem entityItem = new EntityItem(this.worldObj, (double)this.xCoord + 0.5, (double)this.yCoord + 0.6, (double)this.zCoord + 0.5, this.ingredients[i]);
                this.worldObj.spawnEntityInWorld((Entity)entityItem);
            }
            this.ingredients[i] = null;
            break;
        }
    }

    public boolean isFull() {
        for (int i = 0; i < this.ingredients.length; ++i) {
            if (this.ingredients[i] != null) continue;
            return false;
        }
        return true;
    }

    public ItemStack[] getIngredients() {
        return this.ingredients;
    }

    public boolean hasValidIngredients() {
        RecipeData data = RecipeAPI.getBlenderRecipeDataFromIngredients(this.ingredients);
        if (data == null) {
            return false;
        }
        this.drinkName = data.getDrinkName();
        this.healAmount = data.getHealAmount();
        this.currentRed = data.getRed();
        this.currentGreen = data.getGreen();
        this.currentBlue = data.getBlue();
        return true;
    }

    public void startBlending() {
        this.blending = true;
    }

    public boolean isBlending() {
        return this.blending;
    }

    public boolean hasDrink() {
        return this.drinkCount > 0;
    }

    public ItemStack getDrink() {
        ItemStack cup = new ItemStack(MrCrayfishFurnitureMod.itemDrink);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setIntArray("Colour", new int[]{this.currentRed, this.currentGreen, this.currentBlue});
        nbt.setInteger("HealAmount", this.healAmount);
        cup.setTagCompound(nbt);
        cup.setStackDisplayName(new String(this.drinkName));
        return cup;
    }

    public void updateEntity() {
        if (this.blending) {
            ++this.progress;
            if (this.progress == 200) {
                this.clearIngredients();
                this.drinkCount = 6;
                this.progress = 0;
                this.blending = false;
            }
            if (this.timer == 20) {
                this.timer = 0;
            }
            if (this.timer == 0) {
                this.worldObj.playSoundEffect((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, "cfm:blender", 0.75f, 1.0f);
            }
            ++this.timer;
        }
    }

    public void clearIngredients() {
        for (int i = 0; i < this.ingredients.length; ++i) {
            if (this.ingredients[i] == null) continue;
            if (this.ingredients[i].getItem().hasContainerItem() && !this.worldObj.isRemote) {
                EntityItem entityItem = new EntityItem(this.worldObj, (double)this.xCoord + 0.5, (double)this.yCoord + 0.6, (double)this.zCoord + 0.5, new ItemStack(this.ingredients[i].getItem().getContainerItem()));
                this.worldObj.spawnEntityInWorld((Entity)entityItem);
            }
            this.ingredients[i] = null;
        }
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        if (par1NBTTagCompound.hasKey("Items")) {
            NBTTagList tagList = (NBTTagList)par1NBTTagCompound.getTag("Items");
            this.ingredients = new ItemStack[4];
            for (int i = 0; i < tagList.tagCount(); ++i) {
                NBTTagCompound nbt = tagList.getCompoundTagAt(i);
                byte s = nbt.getByte("Slot");
                if (s < 0 || s >= this.ingredients.length) continue;
                this.ingredients[s] = ItemStack.loadItemStackFromNBT((NBTTagCompound)nbt);
            }
        }
        this.blending = par1NBTTagCompound.getBoolean("Blending");
        this.progress = par1NBTTagCompound.getInteger("Progress");
        this.drinkCount = par1NBTTagCompound.getInteger("DrinkCount");
        this.drinkName = par1NBTTagCompound.getString("DrinkName");
        this.healAmount = par1NBTTagCompound.getInteger("HealAmount");
        this.currentRed = par1NBTTagCompound.getInteger("CurrentRed");
        this.currentGreen = par1NBTTagCompound.getInteger("CurrentGreen");
        this.currentBlue = par1NBTTagCompound.getInteger("CurrentBlue");
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList tagList = new NBTTagList();
        for (int i = 0; i < this.ingredients.length; ++i) {
            if (this.ingredients[i] == null) continue;
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("Slot", (byte)i);
            this.ingredients[i].writeToNBT(nbt);
            tagList.appendTag((NBTBase)nbt);
        }
        par1NBTTagCompound.setTag("Items", (NBTBase)tagList);
        par1NBTTagCompound.setBoolean("Blending", this.blending);
        par1NBTTagCompound.setInteger("Progress", this.progress);
        par1NBTTagCompound.setString("DrinkName", this.drinkName);
        par1NBTTagCompound.setInteger("DrinkCount", this.drinkCount);
        par1NBTTagCompound.setInteger("HealAmount", this.healAmount);
        par1NBTTagCompound.setInteger("CurrentRed", this.currentRed);
        par1NBTTagCompound.setInteger("CurrentGreen", this.currentGreen);
        par1NBTTagCompound.setInteger("CurrentBlue", this.currentBlue);
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

