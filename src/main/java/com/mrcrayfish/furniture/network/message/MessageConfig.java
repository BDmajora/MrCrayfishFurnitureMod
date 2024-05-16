/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 */
package com.mrcrayfish.furniture.network.message;

import com.mrcrayfish.furniture.api.RecipeRegistryRemote;
import com.mrcrayfish.furniture.api.Recipes;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;

public class MessageConfig
implements IMessage,
IMessageHandler<MessageConfig, IMessage> {
    private ArrayList<String> itemData = new ArrayList();

    public void toBytes(ByteBuf buf) {
        ArrayList<String> dataList = Recipes.recipeData;
        buf.writeInt(dataList.size());
        for (String data : dataList) {
            ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)data);
        }
    }

    public void fromBytes(ByteBuf buf) {
        int length = buf.readInt();
        for (int count = 0; count != length; ++count) {
            String data = ByteBufUtils.readUTF8String((ByteBuf)buf);
            this.itemData.add(data);
        }
    }

    public IMessage onMessage(MessageConfig message, MessageContext ctx) {
        Recipes.clearRemoteRecipes();
        RecipeRegistryRemote.registerRemoteRecipes(message.itemData);
        return null;
    }
}

