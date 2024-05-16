/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 */
package com.mrcrayfish.furniture.network.message;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.tileentity.TileEntityTV;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class MessageTVClient
implements IMessage,
IMessageHandler<MessageTVClient, IMessage> {
    private int channel;
    private int x;
    private int y;
    private int z;

    public MessageTVClient() {
    }

    public MessageTVClient(int channel, int x, int y, int z) {
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

    public IMessage onMessage(MessageTVClient message, MessageContext ctx) {
        if (ctx.side.isClient()) {
            EntityPlayer player = MrCrayfishFurnitureMod.proxy.getClientPlayer();
            TileEntity tile_entity = player.worldObj.getTileEntity(message.x, message.y, message.z);
            if (tile_entity instanceof TileEntityTV) {
                TileEntityTV tileEntityTV = (TileEntityTV)tile_entity;
                tileEntityTV.setChannel(message.channel);
            }
            player.worldObj.markBlockForUpdate(message.x, message.y, message.z);
        }
        return null;
    }
}

