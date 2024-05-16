/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.util.ResourceLocation
 */
package com.mrcrayfish.furniture;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class FurnitureTab
extends CreativeTabs {
    private static final ResourceLocation gui = new ResourceLocation("cfm:textures/gui/cabinet.png");

    public FurnitureTab(int id, String unlocalizedName) {
        super(id, unlocalizedName);
        this.setBackgroundImageName("furniture.png");
    }

    public Item getTabIconItem() {
        return MrCrayfishFurnitureMod.itemChairWood;
    }
}

