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
import com.mrcrayfish.furniture.tileentity.TileEntityBath;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class MessageFillBath
implements IMessage,
IMessageHandler<MessageFillBath, IMessage> {
    private int waterLevel;
    private int bathOneX;
    private int bathOneY;
    private int bathOneZ;
    private int bathTwoX;
    private int bathTwoY;
    private int bathTwoZ;

    public MessageFillBath() {
    }

    public MessageFillBath(int waterLevel, int x1, int y1, int z1, int x2, int y2, int z2) {
        this.waterLevel = waterLevel;
        this.bathOneX = x1;
        this.bathOneY = y1;
        this.bathOneZ = z1;
        this.bathTwoX = x2;
        this.bathTwoY = y2;
        this.bathTwoZ = z2;
    }

    public void fromBytes(ByteBuf buf) {
        this.waterLevel = buf.readInt();
        this.bathOneX = buf.readInt();
        this.bathOneY = buf.readInt();
        this.bathOneZ = buf.readInt();
        this.bathTwoX = buf.readInt();
        this.bathTwoY = buf.readInt();
        this.bathTwoZ = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.waterLevel);
        buf.writeInt(this.bathOneX);
        buf.writeInt(this.bathOneY);
        buf.writeInt(this.bathOneZ);
        buf.writeInt(this.bathTwoX);
        buf.writeInt(this.bathTwoY);
        buf.writeInt(this.bathTwoZ);
    }

    public IMessage onMessage(MessageFillBath message, MessageContext ctx) {
        if (ctx.side.isClient()) {
            TileEntity tile_entity2;
            EntityPlayer player = MrCrayfishFurnitureMod.proxy.getClientPlayer();
            TileEntity tile_entity = player.worldObj.getTileEntity(message.bathOneX, message.bathOneY, message.bathOneZ);
            if (tile_entity instanceof TileEntityBath) {
                TileEntityBath tileEntityBath = (TileEntityBath)tile_entity;
                tileEntityBath.setWaterLevel(message.waterLevel);
                player.worldObj.markBlockForUpdate(message.bathOneX, message.bathOneY, message.bathOneZ);
            }
            if ((tile_entity2 = player.worldObj.getTileEntity(message.bathTwoX, message.bathTwoY, message.bathTwoZ)) instanceof TileEntityBath) {
                TileEntityBath tileEntityBath = (TileEntityBath)tile_entity2;
                tileEntityBath.setWaterLevel(message.waterLevel);
                player.worldObj.markBlockForUpdate(message.bathTwoX, message.bathTwoY, message.bathTwoZ);
            }
        }
        return null;
    }
}

