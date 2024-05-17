/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.mrcrayfish.furniture.gui;

import com.mrcrayfish.furniture.gui.containers.ContainerMailBox;
import com.mrcrayfish.furniture.tileentity.TileEntityMailBox;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiMailBox
extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("cfm:textures/gui/mailbox.png");

    public GuiMailBox(InventoryPlayer inventoryplayer, TileEntityMailBox tileEntityMailBox) {
        super((Container)new ContainerMailBox((IInventory)inventoryplayer, (IInventory) tileEntityMailBox));
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRendererObj.drawString("Mail Box", this.xSize / 2 - 19, 5, 9999999);
        this.fontRendererObj.drawString("Inventory", 8, this.ySize - 96 + 2, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(gui);
        int l = (this.width - this.xSize) / 2;
        int i1 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(l, i1, 0, 0, this.xSize, this.ySize);
    }
}

