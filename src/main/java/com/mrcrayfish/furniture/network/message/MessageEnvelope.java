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

public class MessageEnvelope
implements IMessage,
IMessageHandler<MessageEnvelope, IMessage> {
    private ItemStack envelope;

    public MessageEnvelope() {
    }

    public MessageEnvelope(ItemStack envelope) {
        this.envelope = envelope;
    }

    public void fromBytes(ByteBuf buf) {
        this.envelope = ByteBufUtils.readItemStack((ByteBuf)buf);
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeItemStack((ByteBuf)buf, (ItemStack)this.envelope);
    }

    public IMessage onMessage(MessageEnvelope message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().playerEntity;
        ItemStack mail = message.envelope;
        ItemStack signedMail = new ItemStack(MrCrayfishFurnitureMod.itemEnvelopeSigned);
        signedMail.stackTagCompound = mail.stackTagCompound;
        signedMail.setTagInfo("Author", (NBTBase)new NBTTagString(player.getCommandSenderName()));
        signedMail.setStackDisplayName("Mail");
        player.setCurrentItemOrArmor(0, signedMail);
        return null;
    }
}

