/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.stats.StatBase
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.items;

import com.mrcrayfish.furniture.FurnitureAchievements;
import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.gui.inventory.InventoryPackage;
import com.mrcrayfish.furniture.items.IMail;
import com.mrcrayfish.furniture.tileentity.TileEntityMailBox;
import com.mrcrayfish.furniture.util.NBTHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class ItemPackageSigned
extends Item
implements IMail {
    public static boolean canUse;

    public ItemPackageSigned() {
        this.maxStackSize = 1;
    }

    public boolean getShareTag() {
        return true;
    }

    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        NBTTagCompound nbttagcompound;
        NBTTagString nbttagstring;
        if (par1ItemStack.hasTagCompound() && (nbttagstring = (NBTTagString)(nbttagcompound = par1ItemStack.getTagCompound()).getTag("Author")) != null) {
            par3List.add(EnumChatFormatting.GRAY + "from " + nbttagstring.toString());
        }
    }

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        TileEntity tile_entity = par3World.getTileEntity(par4, par5, par6);
        if (!par3World.isRemote) {
            NBTTagList var2 = (NBTTagList)NBTHelper.getCompoundTag(par1ItemStack, "Package").getTag("Items");
            if (var2.tagCount() > 0) {
                if (par2EntityPlayer.capabilities.isCreativeMode && par2EntityPlayer.isSneaking() && tile_entity instanceof TileEntityMailBox) {
                    par2EntityPlayer.addChatMessage((IChatComponent)new ChatComponentText("You cannot use this in creative."));
                } else if (tile_entity instanceof TileEntityMailBox) {
                    TileEntityMailBox tileEntityMailBox = (TileEntityMailBox)tile_entity;
                    if (!tileEntityMailBox.isMailBoxFull() && par2EntityPlayer.isSneaking() && !par3World.isRemote) {
                        ItemStack itemStack = par1ItemStack.copy();
                        tileEntityMailBox.addMail(itemStack);
                        par2EntityPlayer.addChatComponentMessage((IChatComponent)new ChatComponentText("Thank you! - " + EnumChatFormatting.YELLOW + tileEntityMailBox.ownerName));
                        --par1ItemStack.stackSize;
                    } else if (tileEntityMailBox.isMailBoxFull() && par2EntityPlayer.isSneaking()) {
                        par2EntityPlayer.addChatMessage((IChatComponent)new ChatComponentText(EnumChatFormatting.YELLOW + tileEntityMailBox.ownerName + "'s" + EnumChatFormatting.WHITE + " mail box seems to be full. Try again later."));
                    }
                }
            } else {
                par2EntityPlayer.addChatMessage((IChatComponent)new ChatComponentText("You cannot insert a used package."));
            }
        }
        return true;
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par2World.isRemote && this == MrCrayfishFurnitureMod.itemPackageSigned) {
            par3EntityPlayer.openGui((Object)MrCrayfishFurnitureMod.instance, 8, par2World, 0, 0, 0);
            par3EntityPlayer.triggerAchievement((StatBase)FurnitureAchievements.firstMail);
        }
        return par1ItemStack;
    }

    public static IInventory getInv(EntityPlayer par1EntityPlayer) {
        ItemStack mail = par1EntityPlayer.getCurrentEquippedItem();
        InventoryPackage invMail = null;
        if (mail != null && mail.getItem() instanceof ItemPackageSigned) {
            invMail = new InventoryPackage(par1EntityPlayer, mail);
        }
        return invMail;
    }

    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("cfm:itempackage");
    }

    @SideOnly(value=Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack) {
        return true;
    }
}

