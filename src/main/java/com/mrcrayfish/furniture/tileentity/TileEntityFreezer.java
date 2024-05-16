/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 */
package com.mrcrayfish.furniture.tileentity;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.api.RecipeData;
import com.mrcrayfish.furniture.api.Recipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFreezer
extends TileEntity
implements ISidedInventory {
    private ItemStack[] freezerItemStacks = new ItemStack[3];
    public int freezerFreezeTime = 0;
    public int currentItemCoolTime = 0;
    public int freezerCoolTime = 0;
    private static final int[] slots_top = new int[]{1};
    private static final int[] slots_bottom = new int[]{2, 1};
    private static final int[] slots_sides = new int[]{0};

    public int getSizeInventory() {
        return this.freezerItemStacks.length;
    }

    public ItemStack getStackInSlot(int i) {
        return this.freezerItemStacks[i];
    }

    public ItemStack decrStackSize(int i, int j) {
        if (this.freezerItemStacks[i] != null) {
            if (this.freezerItemStacks[i].stackSize <= j) {
                ItemStack itemstack = this.freezerItemStacks[i];
                this.freezerItemStacks[i] = null;
                return itemstack;
            }
            ItemStack itemstack1 = this.freezerItemStacks[i].splitStack(j);
            if (this.freezerItemStacks[i].stackSize == 0) {
                this.freezerItemStacks[i] = null;
            }
            return itemstack1;
        }
        return null;
    }

    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.freezerItemStacks[par1] != null) {
            ItemStack itemstack = this.freezerItemStacks[par1];
            this.freezerItemStacks[par1] = null;
            return itemstack;
        }
        return null;
    }

    public void setInventorySlotContents(int i, ItemStack itemstack) {
        this.freezerItemStacks[i] = itemstack;
        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = (NBTTagList)par1NBTTagCompound.getTag("Items");
        this.freezerItemStacks = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
            byte byte0 = nbttagcompound.getByte("Slot");
            if (byte0 < 0 || byte0 >= this.freezerItemStacks.length) continue;
            this.freezerItemStacks[byte0] = ItemStack.loadItemStackFromNBT((NBTTagCompound)nbttagcompound);
        }
        this.freezerCoolTime = par1NBTTagCompound.getShort("CoolTime");
        this.freezerFreezeTime = par1NBTTagCompound.getShort("FreezeTime");
        this.currentItemCoolTime = TileEntityFreezer.getItemFreezeTime(this.freezerItemStacks[1]);
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("CoolTime", (short)this.freezerCoolTime);
        par1NBTTagCompound.setShort("FreezeTime", (short)this.freezerFreezeTime);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.freezerItemStacks.length; ++i) {
            if (this.freezerItemStacks[i] == null) continue;
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.setByte("Slot", (byte)i);
            this.freezerItemStacks[i].writeToNBT(nbttagcompound);
            nbttaglist.appendTag((NBTBase)nbttagcompound);
        }
        par1NBTTagCompound.setTag("Items", (NBTBase)nbttaglist);
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public int getCoolProgressScaled(int i) {
        return this.freezerCoolTime * i / 200;
    }

    public int getFreezeTimeRemainingScaled(int i) {
        if (this.currentItemCoolTime == 0) {
            this.currentItemCoolTime = 200;
        }
        return this.freezerFreezeTime * i / this.currentItemCoolTime;
    }

    public boolean isFreezing() {
        return this.freezerFreezeTime > 0;
    }

    public void updateEntity() {
        boolean flag = this.freezerFreezeTime > 0;
        boolean flag1 = false;
        if (this.freezerFreezeTime > 0) {
            --this.freezerFreezeTime;
        }
        if (!this.worldObj.isRemote) {
            if (this.freezerFreezeTime == 0 && this.canSolidify()) {
                this.currentItemCoolTime = this.freezerFreezeTime = TileEntityFreezer.getItemFreezeTime(this.freezerItemStacks[1]);
                if (this.freezerFreezeTime > 0) {
                    flag1 = true;
                    if (this.freezerItemStacks[1] != null) {
                        --this.freezerItemStacks[1].stackSize;
                        if (this.freezerItemStacks[1].stackSize == 0) {
                            this.freezerItemStacks[1] = null;
                        }
                    }
                }
            }
            if (this.isFreezing() && this.canSolidify()) {
                ++this.freezerCoolTime;
                if (this.freezerCoolTime == 200) {
                    this.freezerCoolTime = 0;
                    this.solidifyItem();
                    flag1 = true;
                }
            } else {
                this.freezerCoolTime = 0;
            }
        }
        if (flag != this.freezerFreezeTime > 0) {
            flag1 = true;
        }
        if (flag1) {
            this.markDirty();
        }
    }

    private boolean canSolidify() {
        if (this.freezerItemStacks[0] == null) {
            return false;
        }
        RecipeData data = Recipes.getFreezerRecipeFromInput(this.freezerItemStacks[0]);
        if (data == null) {
            return false;
        }
        if (this.freezerItemStacks[2] == null) {
            return true;
        }
        if (this.freezerItemStacks[2].getItem() != data.getOutput().getItem()) {
            return false;
        }
        if (this.freezerItemStacks[2].stackSize < this.getInventoryStackLimit() && this.freezerItemStacks[2].stackSize < this.freezerItemStacks[2].getMaxStackSize()) {
            return true;
        }
        return this.freezerItemStacks[2].stackSize < data.getOutput().getMaxStackSize();
    }

    public void solidifyItem() {
        if (this.canSolidify()) {
            RecipeData data = Recipes.getFreezerRecipeFromInput(this.freezerItemStacks[0]);
            if (data == null) {
                return;
            }
            if (this.freezerItemStacks[2] == null) {
                this.freezerItemStacks[2] = data.getOutput().copy();
            } else if (this.freezerItemStacks[2].getItem() == data.getOutput().getItem() && this.freezerItemStacks[2].getMetadata() == data.getOutput().getMetadata()) {
                this.freezerItemStacks[2].stackSize += data.getOutput().copy().stackSize;
            }
            if (this.freezerItemStacks[0].getItem().hasContainerItem()) {
                this.freezerItemStacks[0] = new ItemStack(this.freezerItemStacks[0].getItem().getContainerItem());
            } else {
                --this.freezerItemStacks[0].stackSize;
            }
            if (this.freezerItemStacks[0].stackSize <= 0) {
                this.freezerItemStacks[0] = null;
            }
        }
    }

    private static int getItemFreezeTime(ItemStack itemstack) {
        if (itemstack == null) {
            return 0;
        }
        Item i = itemstack.getItem();
        if (i == MrCrayfishFurnitureMod.itemCoolPack) {
            return 2500;
        }
        if (i == new ItemStack(Blocks.ice).getItem()) {
            return 5000;
        }
        return 0;
    }

    public static boolean isItemFuel(ItemStack itemstack) {
        return TileEntityFreezer.getItemFreezeTime(itemstack) > 0;
    }

    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
    }

    public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
        return par1 == 2 ? false : par1 != 1;
    }

    public int[] getSlotsForFace(int par1) {
        return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_sides);
    }

    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) {
        return this.isItemValidForSlot(par1, par2ItemStack);
    }

    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3) {
        return par3 != 0 || par1 != 1 || par2ItemStack.getItem() == Items.bucket;
    }

    public String getInventoryName() {
        return "Freezer";
    }

    public boolean isCustomInventoryName() {
        return false;
    }

    public void openChest() {
    }

    public void closeChest() {
    }
}

