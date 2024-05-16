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

public class MessagePackage
implements IMessage,
IMessageHandler<MessagePackage, IMessage> {
    private ItemStack package_;

    public MessagePackage() {
    }

    public MessagePackage(ItemStack package_) {
        this.package_ = package_;
    }

    public void fromBytes(ByteBuf buf) {
        this.package_ = ByteBufUtils.readItemStack((ByteBuf)buf);
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeItemStack((ByteBuf)buf, (ItemStack)this.package_);
    }

    public IMessage onMessage(MessagePackage message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().playerEntity;
        ItemStack mail = message.package_;
        ItemStack signedMail = new ItemStack(MrCrayfishFurnitureMod.itemPackageSigned);
        signedMail.stackTagCompound = mail.stackTagCompound;
        signedMail.setTagInfo("Author", (NBTBase)new NBTTagString(player.getCommandSenderName()));
        signedMail.setStackDisplayName("Mail");
        player.setCurrentItemOrArmor(0, signedMail);
        return null;
    }
}

