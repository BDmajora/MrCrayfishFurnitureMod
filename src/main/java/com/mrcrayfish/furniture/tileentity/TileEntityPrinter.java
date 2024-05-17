/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
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
import com.mrcrayfish.furniture.api.RecipeAPI;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPrinter
extends TileEntity {
    private static final int[] slots_top = new int[]{0};
    private static final int[] slots_bottom = new int[]{2, 1};
    private static final int[] slots_sides = new int[]{1};
    private ItemStack[] printerItemStacks = new ItemStack[3];
    public int printerPrintTime;
    public int currentItemPrintTime;
    public int printerPrintingTime;

    public int getSizeInventory() {
        return this.printerItemStacks.length;
    }

    public ItemStack getStackInSlot(int par1) {
        return this.printerItemStacks[par1];
    }

    public ItemStack decrStackSize(int par1, int par2) {
        if (this.printerItemStacks[par1] != null) {
            if (this.printerItemStacks[par1].stackSize <= par2) {
                ItemStack itemstack = this.printerItemStacks[par1];
                this.printerItemStacks[par1] = null;
                return itemstack;
            }
            ItemStack itemstack = this.printerItemStacks[par1].splitStack(par2);
            if (this.printerItemStacks[par1].stackSize == 0) {
                this.printerItemStacks[par1] = null;
            }
            return itemstack;
        }
        return null;
    }

    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.printerItemStacks[par1] != null) {
            ItemStack itemstack = this.printerItemStacks[par1];
            this.printerItemStacks[par1] = null;
            return itemstack;
        }
        return null;
    }

    public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
        this.printerItemStacks[par1] = par2ItemStack;
        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = (NBTTagList)par1NBTTagCompound.getTag("Items");
        this.printerItemStacks = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");
            if (b0 < 0 || b0 >= this.printerItemStacks.length) continue;
            this.printerItemStacks[b0] = ItemStack.loadItemStackFromNBT((NBTTagCompound)nbttagcompound1);
        }
        this.printerPrintTime = par1NBTTagCompound.getShort("PrintTime");
        this.printerPrintingTime = par1NBTTagCompound.getShort("PrintingTime");
        this.currentItemPrintTime = TileEntityPrinter.getItemPrintTime(this.printerItemStacks[1]);
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("PrintTime", (short)this.printerPrintTime);
        par1NBTTagCompound.setShort("PrintingTime", (short)this.printerPrintingTime);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.printerItemStacks.length; ++i) {
            if (this.printerItemStacks[i] == null) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setByte("Slot", (byte)i);
            this.printerItemStacks[i].writeToNBT(nbttagcompound1);
            nbttaglist.appendTag((NBTBase)nbttagcompound1);
        }
        par1NBTTagCompound.setTag("Items", (NBTBase)nbttaglist);
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    @SideOnly(value=Side.CLIENT)
    public int getPrintingProgressScaled(int par1) {
        if (this.printerItemStacks[0] != null) {
            if (this.printerItemStacks[0].getItem() == Items.enchanted_book) {
                return this.printerPrintingTime * par1 / 10000;
            }
            return this.printerPrintingTime * par1 / 1000;
        }
        return 0;
    }

    @SideOnly(value=Side.CLIENT)
    public int getPrintTimeRemainingScaled(int par1) {
        if (this.printerItemStacks[0] != null) {
            if (this.printerItemStacks[0].getItem() == Items.enchanted_book) {
                if (this.currentItemPrintTime == 0) {
                    this.currentItemPrintTime = 10000;
                }
            } else if (this.currentItemPrintTime == 0) {
                this.currentItemPrintTime = 1000;
            }
        } else if (this.currentItemPrintTime == 0) {
            this.currentItemPrintTime = 1000;
        }
        return this.printerPrintTime * par1 / this.currentItemPrintTime;
    }

    public boolean isPrinting() {
        return this.printerPrintTime > 0;
    }

    public void updateEntity() {
        boolean flag1 = false;
        if (this.printerPrintTime > 0) {
            --this.printerPrintTime;
        }
        if (!this.worldObj.isRemote) {
            if (this.printerPrintTime == 0 && this.canPrint()) {
                this.currentItemPrintTime = this.printerPrintTime = TileEntityPrinter.getItemPrintTime(this.printerItemStacks[1]);
                if (this.printerPrintTime > 0) {
                    flag1 = true;
                    if (this.printerItemStacks[1] != null) {
                        --this.printerItemStacks[1].stackSize;
                        if (this.printerItemStacks[1].stackSize == 0) {
                            this.printerItemStacks[1] = this.printerItemStacks[1].getItem().getContainerItem(this.printerItemStacks[1]);
                        }
                    }
                }
            }
            if (this.isPrinting() && this.canPrint()) {
                ++this.printerPrintingTime;
                if (this.printerItemStacks[0] != null && this.printerItemStacks[0].getItem() == Items.enchanted_book) {
                    if (this.printerPrintingTime == 10000) {
                        this.printerPrintingTime = 0;
                        this.smeltItem();
                        flag1 = true;
                    }
                } else if (this.printerPrintingTime == 1000) {
                    this.printerPrintingTime = 0;
                    this.smeltItem();
                    flag1 = true;
                }
            } else {
                this.printerPrintingTime = 0;
            }
        }
        if (flag1) {
            this.markDirty();
        }
    }

    private boolean canPrint() {
        if (this.printerItemStacks[0] == null) {
            return false;
        }
        return RecipeAPI.getPrinterRecipeFromInput(this.printerItemStacks[0]) != null && this.printerItemStacks[2] == null;
    }

    public void smeltItem() {
        if (this.canPrint()) {
            ItemStack itemstack = this.printerItemStacks[0];
            if (this.printerItemStacks[2] == null) {
                this.printerItemStacks[2] = itemstack.copy();
            }
        }
    }

    public static int getItemPrintTime(ItemStack par0ItemStack) {
        if (par0ItemStack == null) {
            return 0;
        }
        Item i = par0ItemStack.getItem();
        if (par0ItemStack.getItemDamage() == 0) {
            if (i == Items.dye) {
                return 1000;
            }
            if (i == MrCrayfishFurnitureMod.itemInkCartridge) {
                return 5000;
            }
        }
        return 0;
    }

    public static boolean isItemFuel(ItemStack par0ItemStack) {
        return TileEntityPrinter.getItemPrintTime(par0ItemStack) > 0;
    }

    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
    }

    public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
        return par1 == 2 ? false : (par1 == 1 ? TileEntityPrinter.isItemFuel(par2ItemStack) : true);
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
        return "Printer";
    }

    public boolean isCustomInventoryName() {
        return false;
    }

    public void openChest() {
    }

    public void closeChest() {
    }
}

