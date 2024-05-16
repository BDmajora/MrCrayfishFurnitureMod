/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.items;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.gui.inventory.InventoryEnvelope;
import com.mrcrayfish.furniture.items.IMail;
import com.mrcrayfish.furniture.tileentity.TileEntityMailBox;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class ItemEnvelope
extends Item
implements IMail {
    public ItemEnvelope() {
        this.maxStackSize = 1;
    }

    public boolean getShareTag() {
        return true;
    }

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        TileEntity tile_entity = par3World.getTileEntity(par4, par5, par6);
        if (!par3World.isRemote) {
            if (par3World.getBlock(par4, par5, par6) != null && tile_entity instanceof TileEntityMailBox) {
                if (par2EntityPlayer.isSneaking()) {
                    par2EntityPlayer.addChatMessage((IChatComponent)new ChatComponentText("You must sign the envelope before depositing it."));
                }
                return false;
            }
            par2EntityPlayer.addChatMessage((IChatComponent)new ChatComponentText("To open the envelope, make sure you are not highlighting any block."));
        }
        return true;
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par2World.isRemote) {
            par3EntityPlayer.openGui((Object)MrCrayfishFurnitureMod.instance, 5, par2World, 0, 0, 0);
        }
        return par1ItemStack;
    }

    public static IInventory getInv(EntityPlayer par1EntityPlayer) {
        ItemStack mail = par1EntityPlayer.getCurrentEquippedItem();
        InventoryEnvelope invMail = null;
        if (mail != null && mail.getItem() instanceof ItemEnvelope) {
            invMail = new InventoryEnvelope(par1EntityPlayer, mail);
        }
        return invMail;
    }
}

