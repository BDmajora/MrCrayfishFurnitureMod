/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.items;

import com.mrcrayfish.furniture.tileentity.TileEntityMailBox;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class ItemHammer
extends Item {
    public ItemHammer() {
        this.setMaxStackSize(1);
    }

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        TileEntity tile_entity = par3World.getTileEntity(par4, par5, par6);
        if (tile_entity instanceof TileEntityMailBox) {
            TileEntityMailBox tileEntityMailBox = (TileEntityMailBox)tile_entity;
            if (tileEntityMailBox.locked && !par3World.isRemote) {
                par2EntityPlayer.addChatMessage((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "[FORCED]" + EnumChatFormatting.WHITE + " Mail Box is now unlocked."));
                tileEntityMailBox.locked = false;
            } else if (!tileEntityMailBox.locked && !par3World.isRemote) {
                par2EntityPlayer.addChatMessage((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "[FORCED]" + EnumChatFormatting.WHITE + " Mail Box is now locked."));
                tileEntityMailBox.locked = true;
            }
        }
        return true;
    }
}

