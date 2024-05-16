/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.mrcrayfish.furniture.items;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.gui.inventory.InventoryPresent;
import com.mrcrayfish.furniture.items.IMail;
import com.mrcrayfish.furniture.network.PacketHandler;
import com.mrcrayfish.furniture.network.message.MessagePresent;
import com.mrcrayfish.furniture.util.NBTHelper;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemPresent
extends Item
implements IMail {
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (par1ItemStack.hasTagCompound()) {
            NBTTagCompound nbttagcompound = par1ItemStack.getTagCompound();
            NBTTagString nbttagstring = (NBTTagString)nbttagcompound.getTag("Author");
            if (nbttagstring != null) {
                par3List.add(EnumChatFormatting.GRAY + "from " + nbttagstring.getString());
            } else {
                par3List.add(EnumChatFormatting.GRAY + "Unsigned");
            }
        }
    }

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        if (par3World.getBlock(par4, par5, par6).isSideSolid((IBlockAccess)par3World, par4, par5, par6, ForgeDirection.UP)) {
            int metadata = 0;
            if (this == MrCrayfishFurnitureMod.itemPresentGreen) {
                metadata = 1;
            }
            if (par1ItemStack.hasTagCompound()) {
                NBTTagCompound nbttagcompound = par1ItemStack.getTagCompound();
                NBTTagString nbttagstring = (NBTTagString)nbttagcompound.getTag("Author");
                if (nbttagstring != null) {
                    NBTTagList itemList = (NBTTagList)NBTHelper.getCompoundTag(par1ItemStack, "Present").getTag("Items");
                    if (itemList.tagCount() > 0) {
                        par3World.setBlock(par4, par5 + 1, par6, MrCrayfishFurnitureMod.present, metadata, 2);
                        par3World.playSoundEffect((double)((float)par4 + 0.5f), (double)((float)par5 + 0.5f), (double)((float)par6 + 0.5f), MrCrayfishFurnitureMod.present.stepSound.getPlaceSound(), (MrCrayfishFurnitureMod.present.stepSound.getVolume() + 1.0f) / 2.0f, MrCrayfishFurnitureMod.present.stepSound.getFrequency() * 0.8f);
                        if (par3World.isRemote) {
                            PacketHandler.INSTANCE.sendToServer((IMessage)new MessagePresent(par1ItemStack, par4, par5 + 1, par6));
                        }
                        --par1ItemStack.stackSize;
                    } else if (par3World.isRemote) {
                        par2EntityPlayer.addChatMessage((IChatComponent)new ChatComponentText("You some how have no items in the present. You cannot use this present."));
                    }
                } else if (par3World.isRemote) {
                    par2EntityPlayer.addChatMessage((IChatComponent)new ChatComponentText("You need to sign it before you can place it"));
                }
            } else if (par3World.isRemote) {
                par2EntityPlayer.addChatMessage((IChatComponent)new ChatComponentText("You need to sign it before you can place it"));
            }
        }
        return true;
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par2World.isRemote) {
            if (par1ItemStack.hasTagCompound()) {
                NBTTagCompound nbttagcompound = par1ItemStack.getTagCompound();
                NBTTagString nbttagstring = (NBTTagString)nbttagcompound.getTag("Author");
                if (nbttagstring == null) {
                    par3EntityPlayer.openGui((Object)MrCrayfishFurnitureMod.instance, 9, par2World, 0, 0, 0);
                } else if (nbttagstring.getString().equals("")) {
                    par3EntityPlayer.addChatMessage((IChatComponent)new ChatComponentText("You cannot unwrap the present once signed"));
                }
            } else {
                par3EntityPlayer.openGui((Object)MrCrayfishFurnitureMod.instance, 9, par2World, 0, 0, 0);
            }
        }
        return par1ItemStack;
    }

    public static IInventory getInv(EntityPlayer par1EntityPlayer) {
        ItemStack present = par1EntityPlayer.getCurrentEquippedItem();
        InventoryPresent invPresent = null;
        if (present != null && present.getItem() instanceof ItemPresent) {
            invPresent = new InventoryPresent(par1EntityPlayer, present);
        }
        return invPresent;
    }
}

