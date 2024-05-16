/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  org.lwjgl.opengl.GL11
 */
package com.mrcrayfish.furniture.gui;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.api.RecipeData;
import com.mrcrayfish.furniture.gui.GuiRecipeBook;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;

public class RecipePage {
    private ArrayList<RecipeData> recipes = new ArrayList();
    public String type;
    private int spacing = 30;
    private ItemStack knife = new ItemStack(MrCrayfishFurnitureMod.itemKnife);
    private ItemStack drink = new ItemStack(MrCrayfishFurnitureMod.itemDrink);

    public RecipePage(String type) {
        this.type = type;
    }

    public void addRecipe(RecipeData data) {
        this.recipes.add(data);
    }

    public void drawPage(Minecraft mc, GuiRecipeBook gui, int x, int y, int mouseX, int mouseY) {
        int i;
        GL11.glPushMatrix();
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glDisable((int)2896);
        GL11.glEnable((int)32826);
        GL11.glEnable((int)2903);
        GL11.glEnable((int)2896);
        this.drawTitle(gui, x, y);
        this.drawDesc(gui, x, y);
        for (i = 0; i < this.recipes.size(); ++i) {
            if (this.type.equals("oven")) {
                this.drawOven(mc, gui, this.recipes.get(i), x + i / 4 * 150 + 15, y + i % 4 * this.spacing);
                gui.drawProgressArrow(x + i / 4 * 150 + 42, y + i % 4 * this.spacing + 2);
            }
            if (this.type.equals("freezer")) {
                this.drawOven(mc, gui, this.recipes.get(i), x + i / 4 * 150 + 15, y + i % 4 * this.spacing);
                gui.drawProgressArrow(x + i / 4 * 150 + 42, y + i % 4 * this.spacing + 2);
            }
            if (this.type.equals("minebay")) {
                this.drawMineBay(mc, gui, this.recipes.get(i), x + i / 4 * 150, y + i % 4 * this.spacing);
            }
            if (this.type.equals("printer")) {
                this.drawPrinter(mc, gui, this.recipes.get(i), x + i % 5 * 20 + i / 20 * 145 + 7, y + i / 5 * 20 - i / 20 * 80 + 30);
            }
            if (this.type.equals("choppingboard")) {
                this.drawChoppingBoard(mc, gui, this.recipes.get(i), x + i / 4 * 150 + 15, y + i % 4 * this.spacing);
                gui.drawPlainArrow(x + i / 4 * 150 + 42, y + i % 4 * this.spacing + 2);
            }
            if (this.type.equals("toaster")) {
                this.drawOven(mc, gui, this.recipes.get(i), x + i / 4 * 150 + 15, y + i % 4 * this.spacing);
                gui.drawProgressArrow(x + i / 4 * 150 + 42, y + i % 4 * this.spacing + 2);
            }
            if (this.type.equals("blender")) {
                this.drawBlender(mc, gui, this.recipes.get(i), x + i / 2 * 150 + 8, y + i % 2 * 50 + 10);
            }
            if (this.type.equals("washingmachine")) {
                this.drawPrinter(mc, gui, this.recipes.get(i), x + i % 5 * 20 + i / 20 * 145 + 7, y + i / 5 * 20 - i / 20 * 80 + 30);
            }
            if (this.type.equals("dishwasher")) {
                this.drawPrinter(mc, gui, this.recipes.get(i), x + i % 5 * 20 + i / 20 * 145 + 7, y + i / 5 * 20 - i / 20 * 80 + 30);
            }
            if (!this.type.equals("microwave")) continue;
            this.drawOven(mc, gui, this.recipes.get(i), x + i / 4 * 150 + 15, y + i % 4 * this.spacing);
            gui.drawProgressArrow(x + i / 4 * 150 + 42, y + i % 4 * this.spacing + 2);
        }
        GL11.glPopMatrix();
        GL11.glEnable((int)2896);
        GL11.glEnable((int)2929);
        RenderHelper.enableStandardItemLighting();
        for (i = 0; i < this.recipes.size(); ++i) {
            if (this.type.equals("oven")) {
                this.drawToolTip(gui, mc, x + i / 4 * 150 + 15, y + i % 4 * this.spacing, mouseX, mouseY, this.recipes.get(i).getInput());
                this.drawToolTip(gui, mc, x + i / 4 * 150 + 75, y + i % 4 * this.spacing, mouseX, mouseY, this.recipes.get(i).getOutput());
            }
            if (this.type.equals("freezer")) {
                this.drawToolTip(gui, mc, x + i / 4 * 150 + 15, y + i % 4 * this.spacing, mouseX, mouseY, this.recipes.get(i).getInput());
                this.drawToolTip(gui, mc, x + i / 4 * 150 + 75, y + i % 4 * this.spacing, mouseX, mouseY, this.recipes.get(i).getOutput());
            }
            if (this.type.equals("minebay")) {
                this.drawToolTip(gui, mc, x + i / 4 * 150 + 20, y + i % 4 * this.spacing, mouseX, mouseY, this.recipes.get(i).getInput());
                this.drawToolTip(gui, mc, x + i / 4 * 150 + 51, y + i % 4 * this.spacing, mouseX, mouseY, this.recipes.get(i).getCurrency());
            }
            if (this.type.equals("printer")) {
                this.drawToolTip(gui, mc, x + i % 5 * 20 + i / 20 * 145 + 7, y + i / 5 * 20 - i / 20 * 80 + 30, mouseX, mouseY, this.recipes.get(i).getInput());
            }
            if (this.type.equals("choppingboard")) {
                this.drawToolTip(gui, mc, x + i / 4 * 150 + 15, y + i % 4 * this.spacing, mouseX, mouseY, this.recipes.get(i).getInput());
                this.drawToolTip(gui, mc, x + i / 4 * 150 + 75, y + i % 4 * this.spacing, mouseX, mouseY, this.recipes.get(i).getOutput());
            }
            if (this.type.equals("toaster")) {
                this.drawToolTip(gui, mc, x + i / 4 * 150 + 15, y + i % 4 * this.spacing, mouseX, mouseY, this.recipes.get(i).getInput());
                this.drawToolTip(gui, mc, x + i / 4 * 150 + 75, y + i % 4 * this.spacing, mouseX, mouseY, this.recipes.get(i).getOutput());
            }
            if (this.type.equals("blender")) {
                int posX = x + i / 2 * 150 + 8;
                int posY = y + i % 2 * 50 + 10;
                RecipeData data = this.recipes.get(i);
                if (gui.isPointInRegion(posX + 0, posY + 0 + 10, 16, 16, mouseX, mouseY) && data.getIngredients().size() > 0 && data.getIngredients().get(0) != null) {
                    gui.renderToolTip(data.getIngredients().get(0), mouseX, mouseY);
                }
                if (gui.isPointInRegion(posX + 18, posY + 0 + 10, 16, 16, mouseX, mouseY) && data.getIngredients().size() > 1 && data.getIngredients().get(1) != null) {
                    gui.renderToolTip(data.getIngredients().get(1), mouseX, mouseY);
                }
                if (gui.isPointInRegion(posX + 0, posY + 18 + 10, 16, 16, mouseX, mouseY) && data.getIngredients().size() > 2 && data.getIngredients().get(2) != null) {
                    gui.renderToolTip(data.getIngredients().get(2), mouseX, mouseY);
                }
                if (gui.isPointInRegion(posX + 18, posY + 18 + 10, 16, 16, mouseX, mouseY) && data.getIngredients().size() > 3 && data.getIngredients().get(3) != null) {
                    gui.renderToolTip(data.getIngredients().get(3), mouseX, mouseY);
                }
                this.drawToolTip(gui, mc, posX + 80, posY + 20, mouseX, mouseY, this.getDrink(data.getDrinkName(), data.getRed(), data.getGreen(), data.getBlue()));
            }
            if (this.type.equals("washingmachine")) {
                this.drawToolTip(gui, mc, x + i % 5 * 20 + i / 20 * 145 + 7, y + i / 5 * 20 - i / 20 * 80 + 30, mouseX, mouseY, this.recipes.get(i).getInput());
            }
            if (this.type.equals("dishwasher")) {
                this.drawToolTip(gui, mc, x + i % 5 * 20 + i / 20 * 145 + 7, y + i / 5 * 20 - i / 20 * 80 + 30, mouseX, mouseY, this.recipes.get(i).getInput());
            }
            if (!this.type.equals("microwave")) continue;
            this.drawToolTip(gui, mc, x + i / 4 * 150 + 15, y + i % 4 * this.spacing, mouseX, mouseY, this.recipes.get(i).getInput());
            this.drawToolTip(gui, mc, x + i / 4 * 150 + 75, y + i % 4 * this.spacing, mouseX, mouseY, this.recipes.get(i).getOutput());
        }
    }

    private void drawTitle(GuiRecipeBook gui, int x, int y) {
        if (this.type.equals("oven")) {
            gui.getFontRenderer().drawString(EnumChatFormatting.UNDERLINE + "Oven Recipes", x + 21, 25, 1986677);
            gui.getFontRenderer().drawString(EnumChatFormatting.UNDERLINE + "Oven Recipes", x + 170, 25, 1986677);
        }
        if (this.type.equals("freezer")) {
            gui.getFontRenderer().drawString(EnumChatFormatting.UNDERLINE + "Freezer Recipes", x + 13, 25, 1986677);
            gui.getFontRenderer().drawString(EnumChatFormatting.UNDERLINE + "Freezer Recipes", x + 163, 25, 1986677);
        }
        if (this.type.equals("minebay")) {
            gui.getFontRenderer().drawString(EnumChatFormatting.UNDERLINE + "MineBay Items", x + 20, 25, 1986677);
            gui.getFontRenderer().drawString(EnumChatFormatting.UNDERLINE + "MineBay Items", x + 169, 25, 1986677);
        }
        if (this.type.equals("printer")) {
            gui.getFontRenderer().drawString(EnumChatFormatting.UNDERLINE + "Printer Recipes", x + 13, 25, 1986677);
            gui.getFontRenderer().drawString(EnumChatFormatting.UNDERLINE + "Printer Recipes", x + 163, 25, 1986677);
        }
        if (this.type.equals("choppingboard")) {
            gui.getFontRenderer().drawString(EnumChatFormatting.UNDERLINE + "Chopping Board Recipes", x - 3, 25, 1986677);
            gui.getFontRenderer().drawString(EnumChatFormatting.UNDERLINE + "Chopping Board Recipes", x + 144, 25, 1986677);
        }
        if (this.type.equals("blender")) {
            gui.getFontRenderer().drawString(EnumChatFormatting.UNDERLINE + "Blender Recipes", x + 15, 25, 1986677);
            gui.getFontRenderer().drawString(EnumChatFormatting.UNDERLINE + "Blender Recipes", x + 162, 25, 1986677);
        }
        if (this.type.equals("toaster")) {
            gui.getFontRenderer().drawString(EnumChatFormatting.UNDERLINE + "Toaster Recipes", x + 15, 25, 1986677);
            gui.getFontRenderer().drawString(EnumChatFormatting.UNDERLINE + "Toaster Recipes", x + 162, 25, 1986677);
        }
        if (this.type.equals("washingmachine")) {
            gui.getFontRenderer().drawString(EnumChatFormatting.UNDERLINE + "Washing Mac. Recipes", x + 5, 25, 1986677);
            gui.getFontRenderer().drawString(EnumChatFormatting.UNDERLINE + "Washing Mac. Recipes", x + 152, 25, 1986677);
        }
        if (this.type.equals("microwave")) {
            gui.getFontRenderer().drawString(EnumChatFormatting.UNDERLINE + "Microwave Recipes", x + 10, 25, 1986677);
            gui.getFontRenderer().drawString(EnumChatFormatting.UNDERLINE + "Microwave Recipes", x + 157, 25, 1986677);
        }
        if (this.type.equals("dishwasher")) {
            gui.getFontRenderer().drawString(EnumChatFormatting.UNDERLINE + "Dishwasher Recipes", x + 5, 25, 1986677);
            gui.getFontRenderer().drawString(EnumChatFormatting.UNDERLINE + "Dishwasher Recipes", x + 152, 25, 1986677);
        }
    }

    private void drawDesc(GuiRecipeBook gui, int x, int y) {
        if (this.type.equals("printer")) {
            gui.getFontRenderer().drawString("All items below can be", x + 150, 40, -16731470);
            gui.getFontRenderer().drawString("used in the printer.", x + 150, 50, -16731470);
            gui.getFontRenderer().drawString("All items below can be", x + 4, 40, -16731470);
            gui.getFontRenderer().drawString("used in the printer.", x + 4, 50, -16731470);
        }
        if (this.type.equals("washingmachine")) {
            gui.getFontRenderer().drawString("All items below can be", x + 150, 40, -16731470);
            gui.getFontRenderer().drawString("repaired in the wash-", x + 150, 50, -16731470);
            gui.getFontRenderer().drawString("ing machine.", x + 150, 60, -16731470);
            gui.getFontRenderer().drawString("All items below can be", x + 4, 40, -16731470);
            gui.getFontRenderer().drawString("repaired in the wash-", x + 4, 50, -16731470);
            gui.getFontRenderer().drawString("ing machine.", x + 4, 60, -16731470);
        }
        if (this.type.equals("dishwasher")) {
            gui.getFontRenderer().drawString("All items below can be", x + 150, 40, -16731470);
            gui.getFontRenderer().drawString("repaired in the dish-", x + 150, 50, -16731470);
            gui.getFontRenderer().drawString("washer.", x + 150, 60, -16731470);
            gui.getFontRenderer().drawString("All items below can be", x + 4, 40, -16731470);
            gui.getFontRenderer().drawString("repaired in the dish-", x + 4, 50, -16731470);
            gui.getFontRenderer().drawString("washer.", x + 4, 60, -16731470);
        }
    }

    private void drawOven(Minecraft mc, GuiRecipeBook gui, RecipeData data, int x, int y) {
        GL11.glEnable((int)2896);
        gui.getItemRenderer().zLevel = 100.0f;
        gui.getItemRenderer().renderItemAndEffectIntoGUI(gui.getFontRenderer(), mc.getTextureManager(), data.getInput(), x, y);
        gui.getItemRenderer().renderItemOverlayIntoGUI(gui.getFontRenderer(), mc.getTextureManager(), data.getInput(), x, y);
        gui.getItemRenderer().renderItemAndEffectIntoGUI(gui.getFontRenderer(), mc.getTextureManager(), data.getOutput(), x + 60, y);
        gui.getItemRenderer().renderItemOverlayIntoGUI(gui.getFontRenderer(), mc.getTextureManager(), data.getOutput(), x + 60, y);
        gui.getItemRenderer().zLevel = 0.0f;
        GL11.glDisable((int)2896);
    }

    private void drawMineBay(Minecraft mc, GuiRecipeBook gui, RecipeData data, int x, int y) {
        GL11.glDisable((int)2896);
        gui.drawTag(x + 42, y - 1);
        GL11.glEnable((int)2896);
        gui.getItemRenderer().zLevel = 100.0f;
        gui.getItemRenderer().renderItemAndEffectIntoGUI(gui.getFontRenderer(), mc.getTextureManager(), data.getInput(), x + 20, y);
        gui.getItemRenderer().renderItemOverlayIntoGUI(gui.getFontRenderer(), mc.getTextureManager(), data.getInput(), x + 20, y);
        gui.getItemRenderer().renderItemAndEffectIntoGUI(gui.getFontRenderer(), mc.getTextureManager(), data.getCurrency(), x + 51, y);
        gui.getItemRenderer().renderItemOverlayIntoGUI(gui.getFontRenderer(), mc.getTextureManager(), data.getCurrency(), x + 51, y);
        gui.getFontRenderer().drawString("x" + Integer.toString(data.getPrice()), x + 68, y + 4, 0);
        gui.getItemRenderer().zLevel = 0.0f;
        GL11.glDisable((int)2896);
    }

    private void drawPrinter(Minecraft mc, GuiRecipeBook gui, RecipeData data, int x, int y) {
        GL11.glEnable((int)2896);
        gui.getItemRenderer().zLevel = 100.0f;
        gui.getItemRenderer().renderItemAndEffectIntoGUI(gui.getFontRenderer(), mc.getTextureManager(), data.getInput(), x, y);
        gui.getItemRenderer().renderItemOverlayIntoGUI(gui.getFontRenderer(), mc.getTextureManager(), data.getInput(), x, y);
        gui.getItemRenderer().zLevel = 0.0f;
        GL11.glDisable((int)2896);
    }

    private void drawChoppingBoard(Minecraft mc, GuiRecipeBook gui, RecipeData data, int x, int y) {
        GL11.glEnable((int)2896);
        gui.getItemRenderer().zLevel = 100.0f;
        gui.getItemRenderer().renderItemAndEffectIntoGUI(gui.getFontRenderer(), mc.getTextureManager(), data.getInput(), x, y);
        gui.getItemRenderer().renderItemOverlayIntoGUI(gui.getFontRenderer(), mc.getTextureManager(), data.getInput(), x, y);
        gui.drawKnife(x + 4, y - 6);
        gui.getItemRenderer().renderItemAndEffectIntoGUI(gui.getFontRenderer(), mc.getTextureManager(), data.getOutput(), x + 60, y);
        gui.getItemRenderer().renderItemOverlayIntoGUI(gui.getFontRenderer(), mc.getTextureManager(), data.getOutput(), x + 60, y);
        gui.getItemRenderer().zLevel = 0.0f;
        GL11.glDisable((int)2896);
    }

    private void drawBlender(Minecraft mc, GuiRecipeBook gui, RecipeData data, int x, int y) {
        gui.getFontRenderer().drawString(this.fixName(data.getDrinkName()), x, y, 0);
        GL11.glEnable((int)2896);
        for (int i = 0; i < data.getIngredients().size(); ++i) {
            if (data.getIngredients().get(i) == null) continue;
            gui.getItemRenderer().renderItemAndEffectIntoGUI(gui.getFontRenderer(), mc.getTextureManager(), data.getIngredients().get(i), x + i % 2 * 18, y + i / 2 * 18 + 10);
            gui.getItemRenderer().renderItemOverlayIntoGUI(gui.getFontRenderer(), mc.getTextureManager(), data.getIngredients().get(i), x + i % 2 * 18, y + i / 2 * 18 + 10);
        }
        gui.drawProgressArrow(x + 45, y + 20);
        GL11.glDisable((int)2896);
        gui.getItemRenderer().renderItemAndEffectIntoGUI(gui.getFontRenderer(), mc.getTextureManager(), this.getDrink(data.getDrinkName(), data.getRed(), data.getGreen(), data.getBlue()), x + 80, y + 20);
        gui.getItemRenderer().renderItemOverlayIntoGUI(gui.getFontRenderer(), mc.getTextureManager(), this.getDrink(data.getDrinkName(), data.getRed(), data.getGreen(), data.getBlue()), x + 80, y + 20);
    }

    public String fixName(String name) {
        if (name.length() > 18) {
            name = name.substring(0, 18) + "...";
        }
        return name;
    }

    public ItemStack getDrink(String name, int red, int green, int blue) {
        this.drink.setStackDisplayName(name);
        this.drink.getTagCompound().setIntArray("Colour", new int[]{red, green, blue});
        return this.drink;
    }

    public void drawToolTip(GuiRecipeBook gui, Minecraft mc, int x, int y, int mouseX, int mouseY, ItemStack stack) {
        if (gui.isPointInRegion(x, y, 16, 16, mouseX, mouseY)) {
            gui.renderToolTip(stack, mouseX, mouseY);
        }
    }
}

