/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.network.message;

import com.mrcrayfish.furniture.tileentity.TileEntityWashingMachine;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class MessageWashingMachine
implements IMessage,
IMessageHandler<MessageWashingMachine, IMessage> {
    private int type;
    private int x;
    private int y;
    private int z;

    public MessageWashingMachine() {
    }

    public MessageWashingMachine(int type, int x, int y, int z) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void fromBytes(ByteBuf buf) {
        this.type = buf.readInt();
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.type);
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
    }

    public IMessage onMessage(MessageWashingMachine message, MessageContext ctx) {
        World world = ctx.getServerHandler().playerEntity.worldObj;
        TileEntity tileEntity = world.getTileEntity(message.x, message.y, message.z);
        if (tileEntity instanceof TileEntityWashingMachine) {
            TileEntityWashingMachine tileEntityWashineMachine = (TileEntityWashingMachine)tileEntity;
            if (message.type == 0) {
                tileEntityWashineMachine.startWashing();
            }
            if (message.type == 1) {
                tileEntityWashineMachine.stopWashing();
            }
            world.markBlockForUpdate(message.x, message.y, message.z);
        }
        return null;
    }
}

