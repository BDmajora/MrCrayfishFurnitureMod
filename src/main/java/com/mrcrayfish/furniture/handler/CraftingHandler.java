/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$ItemCraftedEvent
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 */
package com.mrcrayfish.furniture.handler;

import com.mrcrayfish.furniture.FurnitureAchievements;
import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;

public class CraftingHandler {
    @SubscribeEvent
    public void onCrafted(PlayerEvent.ItemCraftedEvent event) {
        ItemStack item;
        int i;
        if (event.crafting.getItem() == MrCrayfishFurnitureMod.itemChairWood | event.crafting.getItem() == MrCrayfishFurnitureMod.itemChairStone) {
            event.player.triggerAchievement((StatBase)FurnitureAchievements.mineKea);
        }
        if (event.crafting.getItem() == MrCrayfishFurnitureMod.itemSoapyWater) {
            for (i = 0; i < 9; ++i) {
                item = event.craftMatrix.getStackInSlot(i);
                if (item == null || item.getItem() != Items.water_bucket) continue;
                event.craftMatrix.setInventorySlotContents(i, null);
                break;
            }
        }
        if (event.crafting.getItem() == MrCrayfishFurnitureMod.itemSuperSoapyWater) {
            for (i = 0; i < 9; ++i) {
                item = event.craftMatrix.getStackInSlot(i);
                if (item == null || item.getItem() != MrCrayfishFurnitureMod.itemSoapyWater) continue;
                event.craftMatrix.setInventorySlotContents(i, null);
                break;
            }
        }
    }
}

