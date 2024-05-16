/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.opengl.GL11
 */
package com.mrcrayfish.furniture.gui;

import com.mrcrayfish.furniture.gui.containers.ContainerMicrowave;
import com.mrcrayfish.furniture.network.PacketHandler;
import com.mrcrayfish.furniture.network.message.MessageMicrowave;
import com.mrcrayfish.furniture.tileentity.TileEntityMicrowave;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiMicrowave
extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("cfm:textures/gui/microwave.png");
    private TileEntityMicrowave tileEntityMicrowave;
    private GuiButton button_start;

    public GuiMicrowave(InventoryPlayer inventoryPlayer, TileEntityMicrowave tileEntityMicrowave) {
        super((Container)new ContainerMicrowave((IInventory)inventoryPlayer, tileEntityMicrowave));
        this.tileEntityMicrowave = tileEntityMicrowave;
        this.xSize = 176;
        this.ySize = 174;
    }

    public void initGui() {
        super.initGui();
        Keyboard.enableRepeatEvents((boolean)false);
        this.buttonList.clear();
        int posX = this.width / 2;
        int posY = this.height / 2;
        this.button_start = new GuiButton(0, posX + 30, posY - 48, 32, 20, "Start");
        this.buttonList.add(this.button_start);
    }

    protected void actionPerformed(GuiButton guibutton) {
        if (!guibutton.enabled) {
            return;
        }
        if (guibutton.id == 0) {
            if (!this.tileEntityMicrowave.isCooking()) {
                PacketHandler.INSTANCE.sendToServer((IMessage)new MessageMicrowave(0, this.tileEntityMicrowave.xCoord, this.tileEntityMicrowave.yCoord, this.tileEntityMicrowave.zCoord));
            } else {
                PacketHandler.INSTANCE.sendToServer((IMessage)new MessageMicrowave(1, this.tileEntityMicrowave.xCoord, this.tileEntityMicrowave.yCoord, this.tileEntityMicrowave.zCoord));
            }
        }
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRendererObj.drawString("Inventory", 8, 80, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(gui);
        int l = (this.width - this.xSize) / 2;
        int i1 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(l, i1, 0, 0, this.xSize, this.ySize);
        int percent = this.tileEntityMicrowave.progress * 27 / 40;
        this.drawTexturedModalRect(l + 120, i1 + 26, 176, 0, percent, 5);
        this.button_start.displayString = this.tileEntityMicrowave.isCooking() ? "Stop" : "Start";
    }
}

