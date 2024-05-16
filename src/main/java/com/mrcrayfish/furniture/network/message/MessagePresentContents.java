/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.util.EnumChatFormatting
 */
package com.mrcrayfish.furniture.network.message;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumChatFormatting;

public class MessagePresentContents
implements IMessage,
IMessageHandler<MessagePresentContents, IMessage> {
    private ItemStack envelope;

    public MessagePresentContents() {
    }

    public MessagePresentContents(ItemStack envelope) {
        this.envelope = envelope;
    }

    public void fromBytes(ByteBuf buf) {
        this.envelope = ByteBufUtils.readItemStack((ByteBuf)buf);
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeItemStack((ByteBuf)buf, (ItemStack)this.envelope);
    }

    public IMessage onMessage(MessagePresentContents message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().playerEntity;
        ItemStack present = message.envelope;
        if (present.getItem() == MrCrayfishFurnitureMod.itemPresentRed) {
            ItemStack signedPresent = new ItemStack(MrCrayfishFurnitureMod.itemPresentRed);
            signedPresent.stackTagCompound = present.stackTagCompound;
            signedPresent.setTagInfo("Author", (NBTBase)new NBTTagString(player.getCommandSenderName()));
            signedPresent.setStackDisplayName(EnumChatFormatting.RED + "Wrapped Present");
            present.stackTagCompound.getTag("Present");
            player.setCurrentItemOrArmor(0, signedPresent);
        } else {
            ItemStack signedPresent = new ItemStack(MrCrayfishFurnitureMod.itemPresentGreen);
            signedPresent.stackTagCompound = present.stackTagCompound;
            signedPresent.setTagInfo("Author", (NBTBase)new NBTTagString(player.getCommandSenderName()));
            signedPresent.setStackDisplayName(EnumChatFormatting.GREEN + "Wrapped Present");
            player.setCurrentItemOrArmor(0, signedPresent);
        }
        return null;
    }
}

