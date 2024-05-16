/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.opengl.GL11
 */
package com.mrcrayfish.furniture.gui;

import com.mrcrayfish.furniture.gui.containers.ContainerDishwasher;
import com.mrcrayfish.furniture.network.PacketHandler;
import com.mrcrayfish.furniture.network.message.MessageDishwasher;
import com.mrcrayfish.furniture.tileentity.TileEntityDishwasher;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiDishwasher
extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("cfm:textures/gui/dishwasher.png");
    private TileEntityDishwasher tileEntityDishwasher;
    private GuiButton button_start;

    public GuiDishwasher(InventoryPlayer inventoryPlayer, TileEntityDishwasher dishwasher) {
        super((Container)new ContainerDishwasher((IInventory)inventoryPlayer, dishwasher));
        this.tileEntityDishwasher = dishwasher;
        this.xSize = 176;
        this.ySize = 228;
    }

    public void initGui() {
        super.initGui();
        Keyboard.enableRepeatEvents((boolean)false);
        this.buttonList.clear();
        int posX = this.width / 2;
        int posY = this.height / 2;
        this.button_start = new GuiButton(0, posX - 35, posY - 109, 32, 20, "Start");
        this.buttonList.add(this.button_start);
    }

    protected void actionPerformed(GuiButton guibutton) {
        if (!guibutton.enabled) {
            return;
        }
        if (guibutton.id == 0) {
            if (!this.tileEntityDishwasher.isWashing()) {
                PacketHandler.INSTANCE.sendToServer((IMessage)new MessageDishwasher(0, this.tileEntityDishwasher.xCoord, this.tileEntityDishwasher.yCoord, this.tileEntityDishwasher.zCoord));
            } else {
                PacketHandler.INSTANCE.sendToServer((IMessage)new MessageDishwasher(1, this.tileEntityDishwasher.xCoord, this.tileEntityDishwasher.yCoord, this.tileEntityDishwasher.zCoord));
            }
        }
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRendererObj.drawString("Inventory", 8, 135, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(gui);
        int l = (this.width - this.xSize) / 2;
        int i1 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(l, i1, 0, 0, this.xSize, this.ySize);
        if (this.tileEntityDishwasher.isWashing()) {
            int superMode = this.tileEntityDishwasher.superMode ? 20 : 50;
            int percent = this.tileEntityDishwasher.progress % superMode * 55 / superMode;
            this.drawTexturedModalRect(l + 39, i1 + 94 - percent, 176, 55 - percent, 9, percent);
        }
        int percent = this.tileEntityDishwasher.timeRemaining * 55 / 5000;
        int superMode = this.tileEntityDishwasher.superMode ? 14 : 0;
        this.drawTexturedModalRect(l + 129, i1 + 94 - percent, 185 + superMode, 0, 7, percent);
        this.drawTexturedModalRect(l + 129, i1 + 39, 192, 0, 7, 55);
        if (this.tileEntityDishwasher.isWashing()) {
            this.button_start.displayString = "Stop";
            this.drawColour(l + 37, i1 + 9, 11, 11, 49475);
        } else {
            this.button_start.displayString = "Start";
            this.drawColour(l + 37, i1 + 9, 11, 11, 0xFF0000);
        }
    }

    public void drawColour(int x, int y, int width, int height, int par4) {
        Tessellator tessellator = Tessellator.instance;
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        tessellator.startDrawingQuads();
        tessellator.setColorOpaque_I(par4);
        tessellator.addVertex((double)x, (double)y, 0.0);
        tessellator.addVertex((double)x, (double)(y + height), 0.0);
        tessellator.addVertex((double)(x + width), (double)(y + height), 0.0);
        tessellator.addVertex((double)(x + width), (double)y, 0.0);
        tessellator.draw();
        GL11.glEnable((int)3553);
    }
}

