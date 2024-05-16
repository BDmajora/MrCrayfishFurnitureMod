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
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.opengl.GL11
 */
package com.mrcrayfish.furniture.gui;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.gui.containers.ContainerPresent;
import com.mrcrayfish.furniture.gui.inventory.InventoryPresent;
import com.mrcrayfish.furniture.items.ItemPresent;
import com.mrcrayfish.furniture.network.PacketHandler;
import com.mrcrayfish.furniture.network.message.MessagePresentContents;
import com.mrcrayfish.furniture.util.NBTHelper;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiPresent
extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("cfm:textures/gui/redpresent.png");
    private static final ResourceLocation gui2 = new ResourceLocation("cfm:textures/gui/greenpresent.png");
    private GuiButton button_wrap;
    private EntityPlayer player;
    private InventoryPresent inventory;
    private ItemStack present;

    public GuiPresent(InventoryPlayer inventoryplayer, IInventory inventoryMail, EntityPlayer player, ItemStack present) {
        super((Container)new ContainerPresent((IInventory)inventoryplayer, inventoryMail));
        this.player = player;
        this.inventory = (InventoryPresent)inventoryMail;
        this.present = present;
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        if (this.player.inventory.getCurrentItem() == null | (this.player.inventory.getCurrentItem() != null && !(this.player.inventory.getCurrentItem().getItem() instanceof ItemPresent))) {
            this.mc.thePlayer.closeScreen();
        }
        this.fontRendererObj.drawString("Inventory", 8, this.ySize - 96 + 2, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (this.present.getItem() == MrCrayfishFurnitureMod.itemPresentRed) {
            this.mc.getTextureManager().bindTexture(gui);
        } else {
            this.mc.getTextureManager().bindTexture(gui2);
        }
        int l = (this.width - this.xSize) / 2;
        int i1 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(l, i1, 0, 0, this.xSize, this.ySize);
    }

    public void initGui() {
        super.initGui();
        Keyboard.enableRepeatEvents((boolean)false);
        this.buttonList.clear();
        int posX = this.width / 2 + 40;
        int posY = this.height / 2 - 50;
        this.button_wrap = new GuiButton(0, posX, posY, 40, 20, "Wrap");
        this.button_wrap.enabled = true;
        this.buttonList.add(this.button_wrap);
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
            NBTTagList var2 = (NBTTagList)NBTHelper.getCompoundTag(this.present, "Present").getTag("Items");
            if (var2.tagCount() > 0) {
                this.present.setTagInfo("Author", (NBTBase)new NBTTagString(this.player.getCommandSenderName()));
                this.mc.displayGuiScreen(null);
                PacketHandler.INSTANCE.sendToServer((IMessage)new MessagePresentContents(this.present));
            } else {
                this.player.addChatMessage((IChatComponent)new ChatComponentText("You cannot sign an empty envelope."));
            }
        }
    }

    protected void mouseClicked(int i, int j, int k) {
        super.mouseClicked(i, j, k);
    }
}

