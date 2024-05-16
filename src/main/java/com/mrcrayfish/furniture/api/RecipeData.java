/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package com.mrcrayfish.furniture.api;

import com.mrcrayfish.furniture.api.RecipeVariables;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipeData {
    private ItemStack input;
    private ItemStack output;
    private ItemStack currency;
    private int price;
    private String name;
    private int heal;
    private int red;
    private int green;
    private int blue;
    private ArrayList<ItemStack> ingredients;

    public RecipeData setInput(ItemStack input) {
        this.input = input;
        return this;
    }

    public RecipeData setOutput(ItemStack output) {
        this.output = output;
        return this;
    }

    public RecipeData setCurrency(ItemStack currency) {
        this.currency = currency;
        return this;
    }

    public RecipeData setPrice(int price) {
        this.price = price;
        return this;
    }

    public RecipeData setColour(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        return this;
    }

    public RecipeData setName(String name) {
        this.name = name;
        return this;
    }

    public RecipeData setHeal(int heal) {
        this.heal = heal;
        return this;
    }

    public String getDrinkName() {
        return this.name;
    }

    public int getHealAmount() {
        return this.heal;
    }

    public int getRed() {
        return this.red;
    }

    public int getGreen() {
        return this.green;
    }

    public int getBlue() {
        return this.blue;
    }

    public RecipeData addIngredient(ItemStack ingredient) {
        if (this.ingredients == null) {
            this.ingredients = new ArrayList();
        }
        this.ingredients.add(ingredient);
        return this;
    }

    public ArrayList<ItemStack> getIngredients() {
        return this.ingredients;
    }

    public ItemStack getInput() {
        return this.input;
    }

    public ItemStack getOutput() {
        return this.output;
    }

    public ItemStack getCurrency() {
        return this.currency;
    }

    public int getPrice() {
        return this.price;
    }

    public String toString() {
        String name;
        String result = "";
        if (this.input != null) {
            name = GameRegistry.findUniqueIdentifierFor((Item)this.input.getItem()).toString();
            result = result + "input-item=" + name + ",input-amount=" + this.input.stackSize + ",input-metadata=" + this.input.getMetadata() + ",";
        }
        if (this.output != null) {
            name = GameRegistry.findUniqueIdentifierFor((Item)this.output.getItem()).toString();
            result = result + "output-item=" + name + ",output-amount=" + this.output.stackSize + ",output-metadata=" + this.output.getMetadata() + ",";
        }
        if (this.currency != null) {
            name = GameRegistry.findUniqueIdentifierFor((Item)this.currency.getItem()).toString();
            result = result + "payment-item=" + name + ",output-item-metadata=" + this.currency.getMetadata() + ",";
            result = result + "payment-price=" + this.price + ",";
        }
        if (this.name != null) {
            result = result + "name=" + this.name + ",";
            result = result + "heal=" + this.heal + ",";
            result = result + "colour=" + this.red + "-" + this.green + "-" + this.blue + ",";
        }
        if (this.ingredients != null) {
            String pre = "ingredients=";
            for (int i = 0; i < this.ingredients.size(); ++i) {
                String name2 = GameRegistry.findUniqueIdentifierFor((Item)this.ingredients.get(i).getItem()).toString();
                pre = pre + name2;
                pre = pre + ":";
                pre = pre + this.ingredients.get((int)i).stackSize;
                pre = pre + ":";
                pre = pre + this.ingredients.get(i).getMetadata();
                if (i == this.ingredients.size() - 1) continue;
                pre = pre + "/";
            }
            result = result + pre + ",";
        }
        return result.substring(0, result.length() - 1);
    }

    public static RecipeData convertFrom(RecipeVariables params) {
        Object heal;
        Object name;
        Object colour;
        Object ingredients;
        Object price;
        Object currency;
        Object output;
        Object input;
        RecipeData data = new RecipeData();
        if (params.containsKey("input") && (input = params.get("input")) instanceof ItemStack) {
            data.setInput((ItemStack)input);
        }
        if (params.containsKey("output") && (output = params.get("output")) instanceof ItemStack) {
            data.setOutput((ItemStack)output);
        }
        if (params.containsKey("currency") && (currency = params.get("currency")) instanceof ItemStack) {
            data.setCurrency((ItemStack)currency);
        }
        if (params.containsKey("price") && (price = params.get("price")) instanceof Integer) {
            data.setPrice((Integer)price);
        }
        if (params.containsKey("ingredients") && (ingredients = params.get("ingredients")) instanceof ItemStack[]) {
            for (ItemStack ingredient : (ItemStack[])ingredients) {
                data.addIngredient(new ItemStack(ingredient.getItem(), ingredient.stackSize, ingredient.getMetadata()));
            }
        }
        if (params.containsKey("colour") && (colour = params.get("colour")) instanceof int[]) {
            int[] rgb = (int[])colour;
            data.setColour(rgb[0], rgb[1], rgb[2]);
        }
        if (params.containsKey("name") && (name = params.get("name")) instanceof String) {
            data.setName((String)name);
        }
        if (params.containsKey("heal") && (heal = params.get("heal")) instanceof Integer) {
            data.setHeal((Integer)heal);
        }
        return data;
    }
}

