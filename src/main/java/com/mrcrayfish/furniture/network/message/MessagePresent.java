/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 */
package com.mrcrayfish.furniture.network.message;

import com.mrcrayfish.furniture.gui.inventory.InventoryPresent;
import com.mrcrayfish.furniture.tileentity.TileEntityPresent;
import com.mrcrayfish.furniture.util.NBTHelper;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class MessagePresent
implements IMessage,
IMessageHandler<MessagePresent, IMessage> {
    private ItemStack present;
    private int x;
    private int y;
    private int z;

    public MessagePresent() {
    }

    public MessagePresent(ItemStack present, int x, int y, int z) {
        this.present = present;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void fromBytes(ByteBuf buf) {
        this.present = ByteBufUtils.readItemStack((ByteBuf)buf);
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeItemStack((ByteBuf)buf, (ItemStack)this.present);
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
    }

    public IMessage onMessage(MessagePresent message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().playerEntity;
        InventoryPresent presentInventory = new InventoryPresent((EntityPlayer)player, message.present);
        String author = NBTHelper.getString(message.present, "Author");
        TileEntity tile_entity = player.worldObj.getTileEntity(message.x, message.y, message.z);
        if (tile_entity instanceof TileEntityPresent) {
            TileEntityPresent tileEntityPresent = (TileEntityPresent)tile_entity;
            for (int i = 0; i < presentInventory.getSizeInventory(); ++i) {
                tileEntityPresent.setContents(i, presentInventory.getStackInSlot(i));
            }
            tileEntityPresent.setOwner(author);
        }
        return null;
    }
}

