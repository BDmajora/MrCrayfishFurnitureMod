/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.opengl.GL11
 */
package com.mrcrayfish.furniture.gui;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.gui.containers.ContainerPackage;
import com.mrcrayfish.furniture.gui.inventory.InventoryPackage;
import com.mrcrayfish.furniture.network.PacketHandler;
import com.mrcrayfish.furniture.network.message.MessagePackage;
import com.mrcrayfish.furniture.util.NBTHelper;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiPackage
extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("cfm:textures/gui/package.png");
    private GuiButton buttonOk;
    private EntityPlayer player;
    private InventoryPackage inventory;
    private ItemStack mail;

    public GuiPackage(InventoryPlayer inventoryplayer, IInventory inventoryMail, EntityPlayer player, ItemStack mail) {
        super((Container)new ContainerPackage((IInventory)inventoryplayer, inventoryMail));
        this.player = player;
        this.inventory = (InventoryPackage)inventoryMail;
        this.mail = mail;
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRendererObj.drawString("Package", this.xSize / 2 - 19, 5, 9999999);
        this.fontRendererObj.drawString("Inventory", 8, this.ySize - 96 + 2, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(gui);
        int l = (this.width - this.xSize) / 2;
        int i1 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(l, i1, 0, 0, this.xSize, this.ySize);
    }

    public void initGui() {
        ItemStack mail;
        super.initGui();
        Keyboard.enableRepeatEvents((boolean)false);
        this.buttonList.clear();
        int posX = this.width / 2 + 40;
        int posY = this.height / 2 - 50;
        if (this.player.getCurrentEquippedItem() != null && (mail = this.player.getCurrentEquippedItem()).getItem() == MrCrayfishFurnitureMod.itemPackage) {
            this.buttonOk = new GuiButton(0, posX, posY, 40, 20, "Sign");
            this.buttonOk.enabled = true;
            this.buttonList.add(this.buttonOk);
        }
    }

    public void onGuiClosed() {
        super.onGuiClosed();
        this.inventory.saveInventory();
    }

    protected void actionPerformed(GuiButton guibutton) {
        if (!guibutton.enabled) {
            return;
        }
        if (guibutton.id == 0 && this.player.getCurrentEquippedItem() != null) {
            NBTTagList list = (NBTTagList)NBTHelper.getCompoundTag(this.mail, "Package").getTag("Items");
            if (list.tagCount() > 0) {
                PacketHandler.INSTANCE.sendToServer((IMessage)new MessagePackage(this.mail));
                this.mc.displayGuiScreen(null);
            } else {
                this.player.addChatMessage((IChatComponent)new ChatComponentText("You cannot sign an empty package."));
            }
        }
    }

    protected void mouseClicked(int i, int j, int k) {
        super.mouseClicked(i, j, k);
    }
}

