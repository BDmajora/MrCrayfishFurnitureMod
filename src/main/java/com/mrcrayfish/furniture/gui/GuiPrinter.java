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

import com.mrcrayfish.furniture.gui.containers.ContainerPrinter;
import com.mrcrayfish.furniture.tileentity.TileEntityPrinter;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiPrinter
extends GuiContainer {
    private TileEntityPrinter printerInventory;
    private static final ResourceLocation gui = new ResourceLocation("cfm:textures/gui/printer.png");

    public GuiPrinter(InventoryPlayer inventoryplayer, TileEntityPrinter tileentityprinter) {
        super((Container)new ContainerPrinter(inventoryplayer, tileentityprinter));
        this.printerInventory = tileentityprinter;
        this.xSize = 176;
        this.ySize = 187;
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRendererObj.drawString("Inventory", 8, this.ySize - 116 + 13, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        int var7;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(gui);
        int l = (this.width - this.xSize) / 2;
        int i1 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(l, i1 - 10, 0, 0, this.xSize, this.ySize + 21);
        if (this.printerInventory.isPrinting()) {
            var7 = this.printerInventory.getPrintTimeRemainingScaled(16);
            this.drawTexturedModalRect(l + 74, i1 + 38 - var7, 179, 16 - var7, 3, var7);
        }
        if (this.printerInventory.getStackInSlot(0) != null && this.printerInventory.isPrinting()) {
            var7 = this.printerInventory.getPrintingProgressScaled(16);
            this.drawTexturedModalRect(l + 80, i1 + 37 - var7, 176, 15 - var7, 3, var7 + 1);
        }
    }
}

