/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.mrcrayfish.furniture.gui;

import com.mrcrayfish.furniture.gui.containers.ContainerOven;
import com.mrcrayfish.furniture.tileentity.TileEntityOven;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiOven
extends GuiContainer {
    private TileEntityOven OvenInventory;
    private static final ResourceLocation gui = new ResourceLocation("cfm:textures/gui/oven.png");

    public GuiOven(InventoryPlayer inventoryplayer, TileEntityOven tileEntityFreezer) {
        super((Container)new ContainerOven(inventoryplayer, tileEntityFreezer));
        this.OvenInventory = tileEntityFreezer;
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRendererObj.drawString("Oven", 75, 6, 0x404040);
        this.fontRendererObj.drawString("Inventory", 8, this.ySize - 96 + 2, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(gui);
        int l = (this.width - this.xSize) / 2;
        int i1 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(l, i1, 0, 0, this.xSize, this.ySize);
        int var7 = this.OvenInventory.getCookProgressScaled(24);
        this.drawTexturedModalRect(l + 75, i1 + 20, 176, 14, var7 + 1, 16);
        this.drawTexturedModalRect(l + 66, i1 + 40, 176, 0, 14, 14);
        this.drawTexturedModalRect(l + 81, i1 + 40, 176, 0, 14, 14);
        this.drawTexturedModalRect(l + 96, i1 + 40, 176, 0, 14, 14);
    }
}

