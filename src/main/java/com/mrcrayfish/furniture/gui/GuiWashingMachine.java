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

import com.mrcrayfish.furniture.gui.containers.ContainerWashingMachine;
import com.mrcrayfish.furniture.network.PacketHandler;
import com.mrcrayfish.furniture.network.message.MessageWashingMachine;
import com.mrcrayfish.furniture.tileentity.TileEntityWashingMachine;
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

public class GuiWashingMachine
extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("cfm:textures/gui/washingmachine.png");
    private TileEntityWashingMachine tileEntityWashingMachine;
    private GuiButton button_start;

    public GuiWashingMachine(InventoryPlayer inventoryPlayer, TileEntityWashingMachine tileEntityWashingMachine) {
        super((Container)new ContainerWashingMachine((IInventory)inventoryPlayer, tileEntityWashingMachine));
        this.tileEntityWashingMachine = tileEntityWashingMachine;
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
            if (!this.tileEntityWashingMachine.isWashing()) {
                PacketHandler.INSTANCE.sendToServer((IMessage)new MessageWashingMachine(0, this.tileEntityWashingMachine.xCoord, this.tileEntityWashingMachine.yCoord, this.tileEntityWashingMachine.zCoord));
            } else {
                PacketHandler.INSTANCE.sendToServer((IMessage)new MessageWashingMachine(1, this.tileEntityWashingMachine.xCoord, this.tileEntityWashingMachine.yCoord, this.tileEntityWashingMachine.zCoord));
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
        if (this.tileEntityWashingMachine.isWashing()) {
            int superMode = this.tileEntityWashingMachine.superMode ? 20 : 50;
            int percent = this.tileEntityWashingMachine.progress % superMode * 73 / superMode;
            this.drawTexturedModalRect(l + 34, i1 + 104 - percent, 176, 73 - percent, 16, percent);
        }
        int percent = this.tileEntityWashingMachine.timeRemaining * 73 / 5000;
        int superMode = this.tileEntityWashingMachine.superMode ? 20 : 0;
        this.drawTexturedModalRect(l + 129, i1 + 103 - percent, 192 + superMode, 0, 10, percent);
        this.drawTexturedModalRect(l + 129, i1 + 30, 202, 0, 10, 73);
        if (this.tileEntityWashingMachine.isWashing()) {
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

