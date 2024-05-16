/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.stats.StatBase
 *  net.minecraft.tileentity.TileEntity
 */
package com.mrcrayfish.furniture.network.message;

import com.mrcrayfish.furniture.FurnitureAchievements;
import com.mrcrayfish.furniture.api.RecipeData;
import com.mrcrayfish.furniture.api.Recipes;
import com.mrcrayfish.furniture.tileentity.TileEntityComputer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;

public class MessageMineBayBuy
implements IMessage,
IMessageHandler<MessageMineBayBuy, IMessage> {
    private int itemNum;
    private int x;
    private int y;
    private int z;
    private boolean shouldClear;

    public MessageMineBayBuy() {
    }

    public MessageMineBayBuy(int itemNum, int x, int y, int z, boolean shouldClear) {
        this.itemNum = itemNum;
        this.x = x;
        this.y = y;
        this.z = z;
        this.shouldClear = shouldClear;
    }

    public void fromBytes(ByteBuf buf) {
        this.itemNum = buf.readInt();
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.shouldClear = buf.readBoolean();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.itemNum);
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
        buf.writeBoolean(this.shouldClear);
    }

    public IMessage onMessage(MessageMineBayBuy message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().playerEntity;
        TileEntity tile_entity = player.worldObj.getTileEntity(message.x, message.y, message.z);
        if (tile_entity instanceof TileEntityComputer) {
            TileEntityComputer tileEntityComputer = (TileEntityComputer)tile_entity;
            RecipeData[] data = Recipes.getMineBayItems();
            int price = data[message.itemNum].getPrice();
            if (message.shouldClear) {
                tileEntityComputer.clearInventory();
            } else {
                tileEntityComputer.takeEmeraldFromSlot(price);
            }
            EntityItem var14 = new EntityItem(player.worldObj, player.posX, player.posY + 1.0, player.posZ, data[message.itemNum].getInput().copy());
            player.worldObj.spawnEntityInWorld((Entity)var14);
            player.triggerAchievement((StatBase)FurnitureAchievements.buyItem);
        }
        return null;
    }
}

