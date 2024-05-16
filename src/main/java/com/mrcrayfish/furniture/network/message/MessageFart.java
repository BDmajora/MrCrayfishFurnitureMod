/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.stats.StatBase
 */
package com.mrcrayfish.furniture.network.message;

import com.mrcrayfish.furniture.FurnitureAchievements;
import com.mrcrayfish.furniture.entity.EntitySittableBlock;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.StatBase;

public class MessageFart
implements IMessage,
IMessageHandler<MessageFart, IMessage> {
    private Random rand = new Random();
    private double x;
    private double y;
    private double z;

    public MessageFart() {
    }

    public MessageFart(double posX, double posY, double posZ) {
        this.x = posX;
        this.y = posY;
        this.z = posZ;
    }

    public void fromBytes(ByteBuf buf) {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
    }

    public IMessage onMessage(MessageFart message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().playerEntity;
        if (player.ridingEntity instanceof EntitySittableBlock) {
            player.worldObj.playSoundEffect(message.x, message.y, message.z, "cfm:fart" + (this.rand.nextInt(3) + 1), 0.75f, 1.0f);
            player.triggerAchievement((StatBase)FurnitureAchievements.whatDidYouEat);
        }
        return null;
    }
}

