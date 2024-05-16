/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package com.mrcrayfish.furniture.gui;

import com.mrcrayfish.furniture.gui.containers.ContainerBedsideCabinet;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiBedsideCabinet
extends GuiContainer {
    private IInventory upperChestInventory;
    private IInventory lowerChestInventory;
    private static final ResourceLocation gui = new ResourceLocation("cfm:textures/gui/bedsidecabinet.png");

    public GuiBedsideCabinet(IInventory par1IInventory, IInventory par2IInventory) {
        super((Container)new ContainerBedsideCabinet(par1IInventory, par2IInventory));
        this.upperChestInventory = par1IInventory;
        this.lowerChestInventory = par2IInventory;
        this.allowUserInput = false;
        int var3 = 222;
        int var4 = var3 - 108;
        this.ySize = var4 + 54;
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRendererObj.drawString(StatCollector.translateToLocal((String)this.lowerChestInventory.getInventoryName()), this.xSize / 2 - 24, 6, 0x404040);
        this.fontRendererObj.drawString(StatCollector.translateToLocal((String)this.upperChestInventory.getInventoryName()), 8, this.ySize - 96 + 2, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(gui);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, 71);
        this.drawTexturedModalRect(var5, var6 + 54 + 17, 0, 126, this.xSize, 96);
    }
}

