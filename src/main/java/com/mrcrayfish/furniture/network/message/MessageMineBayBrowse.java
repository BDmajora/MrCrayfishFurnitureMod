/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.tileentity.TileEntity
 */
package com.mrcrayfish.furniture.network.message;

import com.mrcrayfish.furniture.tileentity.TileEntityComputer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;

public class MessageMineBayBrowse
implements IMessage,
IMessageHandler<MessageMineBayBrowse, IMessage> {
    private int itemNum;
    private int x;
    private int y;
    private int z;

    public MessageMineBayBrowse() {
    }

    public MessageMineBayBrowse(int itemNum, int x, int y, int z) {
        this.itemNum = itemNum;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void fromBytes(ByteBuf buf) {
        this.itemNum = buf.readInt();
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.itemNum);
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
    }

    public IMessage onMessage(MessageMineBayBrowse message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().playerEntity;
        TileEntity tile_entity = player.worldObj.getTileEntity(message.x, message.y, message.z);
        if (tile_entity instanceof TileEntityComputer) {
            TileEntityComputer tileEntityComputer = (TileEntityComputer)tile_entity;
            tileEntityComputer.setBrowsingInfo(message.itemNum);
        }
        player.worldObj.markBlockForUpdate(message.x, message.y, message.z);
        return null;
    }
}

