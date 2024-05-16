/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.mrcrayfish.furniture.gui;

import com.mrcrayfish.furniture.gui.containers.ContainerCabinet;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiKitchenCabinet
extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("cfm:textures/gui/kitchen_cabinet.png");

    public GuiKitchenCabinet(IInventory par1IInventory, IInventory par2IInventory) {
        super((Container)new ContainerCabinet(par1IInventory, par2IInventory));
        this.xSize = 176;
        this.ySize = 167;
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRendererObj.drawString("Kitchen Cabinet", this.xSize / 2 - 44, 6, 0x404040);
        this.fontRendererObj.drawString("Inventory", 8, this.ySize - 94, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(gui);
        int l = (this.width - this.xSize) / 2;
        int i1 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(l, i1, 0, 0, this.xSize, this.ySize);
    }
}

