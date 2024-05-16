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
import com.mrcrayfish.furniture.gui.inventory.InventoryPackage;
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

public class ItemPackage
extends Item
implements IMail {
    public ItemPackage() {
        this.maxStackSize = 1;
    }

    public boolean getShareTag() {
        return true;
    }

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        TileEntity tile_entity = par3World.getTileEntity(par4, par5, par6);
        if (!par3World.isRemote && tile_entity instanceof TileEntityMailBox && par2EntityPlayer.isSneaking() && !par3World.isRemote) {
            par2EntityPlayer.addChatMessage((IChatComponent)new ChatComponentText("You must sign the package before depositing it."));
        }
        return true;
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par2World.isRemote) {
            par3EntityPlayer.openGui((Object)MrCrayfishFurnitureMod.instance, 7, par2World, 0, 0, 0);
        }
        return par1ItemStack;
    }

    public static IInventory getInv(EntityPlayer par1EntityPlayer) {
        ItemStack mail = par1EntityPlayer.getCurrentEquippedItem();
        InventoryPackage invMail = null;
        if (mail != null && mail.getItem() instanceof ItemPackage) {
            invMail = new InventoryPackage(par1EntityPlayer, mail);
        }
        return invMail;
    }
}

