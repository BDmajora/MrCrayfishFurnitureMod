/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.tileentity.TileEntity
 */
package com.mrcrayfish.furniture.network.message;

import com.mrcrayfish.furniture.network.PacketHandler;
import com.mrcrayfish.furniture.network.message.MessageTVClient;
import com.mrcrayfish.furniture.tileentity.TileEntityTV;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;

public class MessageTVServer
implements IMessage,
IMessageHandler<MessageTVServer, IMessage> {
    private int channel;
    private int x;
    private int y;
    private int z;

    public MessageTVServer() {
    }

    public MessageTVServer(int channel, int x, int y, int z) {
        this.channel = channel;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void fromBytes(ByteBuf buf) {
        this.channel = buf.readInt();
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.channel);
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
    }

    public IMessage onMessage(MessageTVServer message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().playerEntity;
        TileEntity tile_entity = player.worldObj.getTileEntity(message.x, message.y, message.z);
        if (tile_entity instanceof TileEntityTV) {
            TileEntityTV tileEntityTV = (TileEntityTV)tile_entity;
            tileEntityTV.setChannel(message.channel);
        }
        player.worldObj.markBlockForUpdate(message.x, message.y, message.z);
        PacketHandler.INSTANCE.sendToAllAround((IMessage)new MessageTVClient(message.channel, message.x, message.y, message.z), new NetworkRegistry.TargetPoint(player.dimension, (double)this.x, (double)this.y, (double)this.z, 50.0));
        return null;
    }
}

