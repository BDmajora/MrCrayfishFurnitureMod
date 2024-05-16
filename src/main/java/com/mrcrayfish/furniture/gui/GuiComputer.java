/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.opengl.GL11
 */
package com.mrcrayfish.furniture.gui;

import com.mrcrayfish.furniture.api.RecipeData;
import com.mrcrayfish.furniture.api.Recipes;
import com.mrcrayfish.furniture.gui.containers.ContainerComputer;
import com.mrcrayfish.furniture.network.PacketHandler;
import com.mrcrayfish.furniture.network.message.MessageMineBayBuy;
import com.mrcrayfish.furniture.network.message.MessageMineBayClosed;
import com.mrcrayfish.furniture.tileentity.TileEntityComputer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiComputer
extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("cfm:textures/gui/computer.png");
    private GuiButton left;
    private GuiButton right;
    private GuiButton button_buy;
    private int itemNum;
    private ItemStack buySlot;
    private TileEntityComputer tileEntityComputer;
    private EntityPlayer player;
    private RecipeData[] itemdata;

    public GuiComputer(InventoryPlayer inventoryplayer, TileEntityComputer tileEntityComputer) {
        super((Container)new ContainerComputer((IInventory)inventoryplayer, tileEntityComputer));
        this.tileEntityComputer = tileEntityComputer;
    }

    public void initGui() {
        super.initGui();
        Keyboard.enableRepeatEvents((boolean)false);
        this.buttonList.clear();
        int posX = this.width / 2 - 48;
        int posY = this.height / 2 - 48;
        this.left = new GuiButton(0, posX, posY - 21, 15, 20, "<");
        this.right = new GuiButton(1, posX + 82, posY - 21, 15, 20, ">");
        this.button_buy = new GuiButton(2, posX, posY + 3, 29, 20, "Buy");
        this.buttonList.add(this.left);
        this.buttonList.add(this.right);
        this.buttonList.add(this.button_buy);
        this.itemNum = this.tileEntityComputer.getBrowsingInfo();
        this.itemdata = Recipes.getMineBayItems();
    }

    protected void actionPerformed(GuiButton guibutton) {
        if (!guibutton.enabled) {
            return;
        }
        if (guibutton.id == 0) {
            --this.itemNum;
            if (this.itemNum < 0) {
                this.itemNum = 0;
            }
            this.tileEntityComputer.setBrowsingInfo(this.itemNum);
        }
        if (guibutton.id == 1) {
            ++this.itemNum;
            if (this.itemNum > this.itemdata.length - 1) {
                this.itemNum = this.itemdata.length - 1;
            }
            this.tileEntityComputer.setBrowsingInfo(this.itemNum);
        }
        if (guibutton.id == 2) {
            this.buySlot = this.tileEntityComputer.getStackInSlot(0);
            if (this.buySlot != null) {
                ItemStack money = this.itemdata[this.itemNum].getCurrency();
                if (this.buySlot.getItem() == money.getItem() && this.buySlot.getMetadata() == money.getMetadata()) {
                    int price = this.itemdata[this.itemNum].getPrice();
                    if (this.buySlot.stackSize == price) {
                        PacketHandler.INSTANCE.sendToServer((IMessage)new MessageMineBayBuy(this.itemNum, this.tileEntityComputer.xCoord, this.tileEntityComputer.yCoord, this.tileEntityComputer.zCoord, true));
                    } else if (this.buySlot.stackSize > price && this.buySlot.stackSize > 1) {
                        PacketHandler.INSTANCE.sendToServer((IMessage)new MessageMineBayBuy(this.itemNum, this.tileEntityComputer.xCoord, this.tileEntityComputer.yCoord, this.tileEntityComputer.zCoord, false));
                    }
                    if (this.buySlot.stackSize == 0 && price == 1) {
                        PacketHandler.INSTANCE.sendToServer((IMessage)new MessageMineBayBuy(this.itemNum, this.tileEntityComputer.xCoord, this.tileEntityComputer.yCoord, this.tileEntityComputer.zCoord, true));
                    }
                }
            }
        }
    }

    public void onGuiClosed() {
        PacketHandler.INSTANCE.sendToServer((IMessage)new MessageMineBayClosed(this.tileEntityComputer.xCoord, this.tileEntityComputer.yCoord, this.tileEntityComputer.zCoord));
        super.onGuiClosed();
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRendererObj.drawString("Inventory", 8, this.ySize - 96 + 13, 0x404040);
        GL11.glPushMatrix();
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glDisable((int)2896);
        GL11.glEnable((int)32826);
        GL11.glEnable((int)2903);
        GL11.glEnable((int)2896);
        GL11.glEnable((int)3042);
        GuiComputer.itemRender.zLevel = 100.0f;
        if (this.itemNum - 1 >= 0) {
            ItemStack pre = this.itemdata[this.itemNum - 1].getInput();
            itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), pre, 57, 16);
            itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), pre, 57, 16);
        }
        ItemStack stock = this.itemdata[this.itemNum].getInput();
        itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), stock, 80, 16);
        itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), stock, 80, 16);
        if (this.itemNum + 1 < this.itemdata.length) {
            ItemStack post = this.itemdata[this.itemNum + 1].getInput();
            itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), post, 103, 16);
            itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), post, 103, 16);
        }
        ItemStack currency = this.itemdata[this.itemNum].getCurrency();
        itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), currency, 73, 40);
        itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), currency, 73, 40);
        GuiComputer.itemRender.zLevel = 0.0f;
        GL11.glDisable((int)2896);
        int price = this.itemdata[this.itemNum].getPrice();
        this.fontRendererObj.drawString("x" + Integer.toString(price), 90, 44, 0);
        GL11.glPopMatrix();
        GL11.glEnable((int)2896);
        GL11.glEnable((int)2929);
        RenderHelper.enableStandardItemLighting();
    }

    public void drawScreen(int par1, int par2, float par3) {
        super.drawScreen(par1, par2, par3);
        ItemStack stock = this.itemdata[this.itemNum].getInput();
        if (this.isPointInRegion(80, 16, 16, 16, par1, par2)) {
            this.renderToolTip(stock, par1, par2);
        }
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(gui);
        int l = (this.width - this.xSize) / 2;
        int i1 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(l, i1 - 10, 0, 0, this.xSize, this.ySize + 21);
    }
}

