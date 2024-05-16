/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerLoggedInEvent
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 */
package com.mrcrayfish.furniture.handler;

import com.mrcrayfish.furniture.FurnitureAchievements;
import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.handler.ConfigurationHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.StatBase;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class PlayerEvents {
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e) {
        EntityPlayer player = e.player;
        player.triggerAchievement((StatBase)FurnitureAchievements.installMod);
        if (ConfigurationHandler.canDisplay && !player.worldObj.isRemote && !MrCrayfishFurnitureMod.hasDisplayedOnce) {
            player.addChatMessage((IChatComponent)new ChatComponentText(EnumChatFormatting.AQUA + "Thank you for downloading MrCrayfish's Furniture Mod."));
            player.addChatMessage((IChatComponent)new ChatComponentText("Make sure you check out the wiki! http://mrcrayfishs-furniture-mod.wikia.com/"));
            MrCrayfishFurnitureMod.hasDisplayedOnce = true;
        }
    }
}

