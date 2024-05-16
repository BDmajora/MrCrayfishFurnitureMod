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
import com.mrcrayfish.furniture.tileentity.TileEntityBasin;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class MessageFillBasin
implements IMessage,
IMessageHandler<MessageFillBasin, IMessage> {
    private boolean hasWater;
    private int x;
    private int y;
    private int z;

    public MessageFillBasin() {
    }

    public MessageFillBasin(boolean hasWater, int x, int y, int z) {
        this.hasWater = hasWater;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void fromBytes(ByteBuf buf) {
        this.hasWater = buf.readBoolean();
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.hasWater);
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
    }

    public IMessage onMessage(MessageFillBasin message, MessageContext ctx) {
        if (ctx.side.isClient()) {
            EntityPlayer player = MrCrayfishFurnitureMod.proxy.getClientPlayer();
            TileEntity tile_entity = player.worldObj.getTileEntity(message.x, message.y, message.z);
            if (tile_entity instanceof TileEntityBasin) {
                TileEntityBasin tileEntityBasin = (TileEntityBasin)tile_entity;
                tileEntityBasin.setHasWater(message.hasWater);
            }
            player.worldObj.markBlockForUpdate(message.x, message.y, message.z);
        }
        return null;
    }
}

