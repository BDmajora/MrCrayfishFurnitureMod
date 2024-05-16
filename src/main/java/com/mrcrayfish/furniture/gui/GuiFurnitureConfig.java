/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiConfig
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraftforge.common.config.ConfigElement
 */
package com.mrcrayfish.furniture.gui;

import com.mrcrayfish.furniture.handler.ConfigurationHandler;
import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;

public class GuiFurnitureConfig
extends GuiConfig {
    public GuiFurnitureConfig(GuiScreen parent) {
        super(parent, new ConfigElement(ConfigurationHandler.config.getCategory("settings")).getChildElements(), "cfm", false, false, GuiConfig.getAbridgedConfigPath((String)ConfigurationHandler.config.toString()));
    }
}

