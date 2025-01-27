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

import com.mrcrayfish.furniture.gui.containers.ContainerFreezer;
import com.mrcrayfish.furniture.tileentity.TileEntityFreezer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiFreezer
extends GuiContainer {
    private TileEntityFreezer FreezerInventory;
    private static final ResourceLocation gui = new ResourceLocation("cfm:textures/gui/freezer.png");

    public GuiFreezer(InventoryPlayer inventoryplayer, TileEntityFreezer tileentityfreezer) {
        super((Container)new ContainerFreezer(inventoryplayer, tileentityfreezer));
        this.FreezerInventory = tileentityfreezer;
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRendererObj.drawString("Freezer", 8, 6, 0x404040);
        this.fontRendererObj.drawString("Inventory", 8, this.ySize - 96 + 2, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        int var7;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(gui);
        int l = (this.width - this.xSize) / 2;
        int i1 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(l, i1, 0, 0, this.xSize, this.ySize);
        if (this.FreezerInventory.isFreezing()) {
            var7 = this.FreezerInventory.getFreezeTimeRemainingScaled(12);
            this.drawTexturedModalRect(l + 15, i1 + 40 - var7, 176, 12 - var7, 14, var7 + 2);
        }
        var7 = this.FreezerInventory.getCoolProgressScaled(24);
        this.drawTexturedModalRect(l + 86, i1 + 27, 176, 14, var7 + 1, 16);
    }
}

