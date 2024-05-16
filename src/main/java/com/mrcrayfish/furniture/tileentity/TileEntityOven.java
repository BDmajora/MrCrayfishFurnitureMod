/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 */
package com.mrcrayfish.furniture.tileentity;

import com.mrcrayfish.furniture.api.RecipeData;
import com.mrcrayfish.furniture.api.Recipes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityOven
extends TileEntity
implements ISidedInventory {
    private static final int[] field_102010_d = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
    private static final int[] field_102011_e = new int[]{9, 10, 11, 12, 13, 14, 15, 16, 17};
    private ItemStack[] ovenItemStacks = new ItemStack[18];
    public int ovenCookTime = 0;
    public int currentItemCookTime = 0;
    public int ovenCookingTime = 0;
    private String field_94130_e;
    private Random rand = new Random();

    public int getSizeInventory() {
        return this.ovenItemStacks.length;
    }

    public ItemStack getStackInSlot(int par1) {
        return this.ovenItemStacks[par1];
    }

    public ItemStack decrStackSize(int par1, int par2) {
        if (this.ovenItemStacks[par1] != null) {
            if (this.ovenItemStacks[par1].stackSize <= par2) {
                ItemStack itemstack = this.ovenItemStacks[par1];
                this.ovenItemStacks[par1] = null;
                return itemstack;
            }
            ItemStack itemstack = this.ovenItemStacks[par1].splitStack(par2);
            if (this.ovenItemStacks[par1].stackSize == 0) {
                this.ovenItemStacks[par1] = null;
            }
            return itemstack;
        }
        return null;
    }

    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.ovenItemStacks[par1] != null) {
            ItemStack itemstack = this.ovenItemStacks[par1];
            this.ovenItemStacks[par1] = null;
            return itemstack;
        }
        return null;
    }

    public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
        this.ovenItemStacks[par1] = par2ItemStack;
        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = (NBTTagList)par1NBTTagCompound.getTag("Items");
        this.ovenItemStacks = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");
            if (b0 < 0 || b0 >= this.ovenItemStacks.length) continue;
            this.ovenItemStacks[b0] = ItemStack.loadItemStackFromNBT((NBTTagCompound)nbttagcompound1);
        }
        this.ovenCookTime = par1NBTTagCompound.getShort("BurnTime");
        this.ovenCookingTime = par1NBTTagCompound.getShort("CookTime");
        this.currentItemCookTime = TileEntityOven.getItemBurnTime(this.ovenItemStacks[1]);
        if (par1NBTTagCompound.hasKey("CustomName")) {
            this.field_94130_e = par1NBTTagCompound.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("BurnTime", (short)this.ovenCookTime);
        par1NBTTagCompound.setShort("CookTime", (short)this.ovenCookingTime);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.ovenItemStacks.length; ++i) {
            if (this.ovenItemStacks[i] == null) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setByte("Slot", (byte)i);
            this.ovenItemStacks[i].writeToNBT(nbttagcompound1);
            nbttaglist.appendTag((NBTBase)nbttagcompound1);
        }
        par1NBTTagCompound.setTag("Items", (NBTBase)nbttaglist);
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    @SideOnly(value=Side.CLIENT)
    public int getCookProgressScaled(int par1) {
        return this.ovenCookingTime * par1 / 600;
    }

    @SideOnly(value=Side.CLIENT)
    public int getBurnTimeRemainingScaled(int par1) {
        if (this.currentItemCookTime == 0) {
            this.currentItemCookTime = 600;
        }
        return this.ovenCookTime * par1 / this.currentItemCookTime;
    }

    public boolean isBurning() {
        return this.ovenCookTime > 0;
    }

    public void updateEntity() {
        boolean flag = this.ovenCookTime > 0;
        boolean flag1 = false;
        if (this.ovenCookTime > 0) {
            --this.ovenCookTime;
        }
        if (!this.worldObj.isRemote) {
            int itemNum = this.canSmelt();
            if (itemNum != -1) {
                ++this.ovenCookingTime;
                if (this.ovenCookingTime == 600) {
                    this.ovenCookingTime = 0;
                    this.cookItems();
                    flag1 = true;
                }
            } else {
                this.ovenCookingTime = 0;
            }
            if (flag != this.ovenCookTime > 0) {
                flag1 = true;
            }
        }
        if (flag1) {
            this.markDirty();
        }
    }

    private int canSmelt() {
        boolean hasItem = false;
        int itemNum = -1;
        for (int x = 0; x < 9; ++x) {
            if (this.ovenItemStacks[x] == null) continue;
            hasItem = true;
            itemNum = x;
            break;
        }
        if (hasItem) {
            RecipeData data = Recipes.getOvenRecipeFromInput(this.ovenItemStacks[itemNum]);
            if (data == null) {
                return -1;
            }
            if (this.ovenItemStacks[itemNum + 9] == null) {
                return itemNum;
            }
            if (this.ovenItemStacks[itemNum + 9].getItem() != data.getOutput().getItem()) {
                return -1;
            }
            if (this.ovenItemStacks[itemNum + 9].stackSize < this.getInventoryStackLimit() && this.ovenItemStacks[itemNum + 9].stackSize < this.ovenItemStacks[itemNum + 9].getMaxStackSize()) {
                return itemNum;
            }
            if (this.ovenItemStacks[itemNum + 9].stackSize < data.getOutput().getMaxStackSize()) {
                return itemNum;
            }
            return -1;
        }
        return -1;
    }

    public void cookItems() {
        int itemNum = this.canSmelt();
        if (itemNum != -1) {
            RecipeData data = Recipes.getOvenRecipeFromInput(this.ovenItemStacks[itemNum]);
            if (data == null) {
                return;
            }
            if (this.ovenItemStacks[itemNum + 9] == null) {
                this.ovenItemStacks[itemNum + 9] = data.getOutput().copy();
            } else if (this.ovenItemStacks[itemNum + 9].getItem() == data.getOutput().getItem() && this.ovenItemStacks[itemNum + 9].getMetadata() == data.getOutput().getMetadata()) {
                this.ovenItemStacks[itemNum + 9].stackSize += data.getOutput().copy().stackSize;
            }
            if (this.ovenItemStacks[itemNum].getItem().hasContainerItem()) {
                this.ovenItemStacks[itemNum] = new ItemStack(this.ovenItemStacks[itemNum].getItem().getContainerItem());
            } else {
                --this.ovenItemStacks[itemNum].stackSize;
            }
            if (this.ovenItemStacks[itemNum].stackSize <= 0) {
                this.ovenItemStacks[itemNum] = null;
            }
        }
    }

    public static int getItemBurnTime(ItemStack par0ItemStack) {
        return 0;
    }

    public static boolean isItemFuel(ItemStack par0ItemStack) {
        return TileEntityOven.getItemBurnTime(par0ItemStack) > 0;
    }

    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
    }

    public boolean isStackValidForSlot(int par1, ItemStack par2ItemStack) {
        return par1 == 2 ? false : (par1 == 1 ? TileEntityOven.isItemFuel(par2ItemStack) : true);
    }

    public int[] getSlotsForFace(int par1) {
        return par1 == 0 ? field_102011_e : field_102010_d;
    }

    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) {
        return this.isStackValidForSlot(par1, par2ItemStack);
    }

    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3) {
        return par1 > 8;
    }

    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return i <= 8;
    }

    public String getInventoryName() {
        return "Oven";
    }

    public boolean isCustomInventoryName() {
        return false;
    }

    public void openChest() {
    }

    public void closeChest() {
    }
}

