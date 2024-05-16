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
 */
package com.mrcrayfish.furniture.network.message;

import com.mrcrayfish.furniture.FurnitureAchievements;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class MessageAchievement
implements IMessage,
IMessageHandler<MessageAchievement, IMessage> {
    private String achievementName;

    public MessageAchievement() {
    }

    public MessageAchievement(String achievementName) {
        this.achievementName = achievementName;
    }

    public void fromBytes(ByteBuf buf) {
        this.achievementName = ByteBufUtils.readUTF8String((ByteBuf)buf);
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.achievementName);
    }

    public IMessage onMessage(MessageAchievement message, MessageContext ctx) {
        FurnitureAchievements.triggerAchievement((EntityPlayer)ctx.getServerHandler().playerEntity, message.achievementName);
        return null;
    }
}

