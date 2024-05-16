/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package com.mrcrayfish.furniture.api;

import com.mrcrayfish.furniture.api.RecipeData;
import com.mrcrayfish.furniture.api.Recipes;
import net.minecraft.item.ItemStack;

public class RecipeAPI {
    public static final int LOCAL = 0;
    public static final int REMOTE = 1;
    public static final int COMM = 2;

    public static void addMineBayRecipe(RecipeData itemData, int type) {
        switch (type) {
            case 0: {
                Recipes.localMineBayRecipes.add(itemData);
                break;
            }
            case 1: {
                Recipes.remoteMineBayRecipes.add(itemData);
                break;
            }
            case 2: {
                Recipes.commMineBayRecipes.add(itemData);
            }
        }
    }

    public static void addOvenRecipe(RecipeData itemData, int type) {
        switch (type) {
            case 0: {
                Recipes.localOvenRecipes.add(itemData);
                break;
            }
            case 1: {
                Recipes.remoteOvenRecipes.add(itemData);
                break;
            }
            case 2: {
                Recipes.commOvenRecipes.add(itemData);
            }
        }
    }

    public static void addFreezerRecipe(RecipeData itemData, int type) {
        switch (type) {
            case 0: {
                Recipes.localFreezerRecipes.add(itemData);
                break;
            }
            case 1: {
                Recipes.remoteFreezerRecipes.add(itemData);
                break;
            }
            case 2: {
                Recipes.commFreezerRecipes.add(itemData);
            }
        }
    }

    public static void addPrinterRecipe(RecipeData itemData, int type) {
        switch (type) {
            case 0: {
                Recipes.localPrinterRecipes.add(itemData);
                break;
            }
            case 1: {
                Recipes.remotePrinterRecipes.add(itemData);
                break;
            }
            case 2: {
                Recipes.commPrinterRecipes.add(itemData);
            }
        }
    }

    public static void addToasterRecipe(RecipeData itemData, int type) {
        switch (type) {
            case 0: {
                Recipes.localToasterRecipes.add(itemData);
                break;
            }
            case 1: {
                Recipes.remoteToasterRecipes.add(itemData);
                break;
            }
            case 2: {
                Recipes.commToasterRecipes.add(itemData);
            }
        }
    }

    public static void addChoppingBoardRecipe(RecipeData itemData, int type) {
        switch (type) {
            case 0: {
                Recipes.localChoppingBoardRecipes.add(itemData);
                break;
            }
            case 1: {
                Recipes.remoteChoppingBoardRecipes.add(itemData);
                break;
            }
            case 2: {
                Recipes.commChoppingBoardRecipes.add(itemData);
            }
        }
    }

    public static void addBlenderRecipe(RecipeData itemData, int type) {
        switch (type) {
            case 0: {
                Recipes.localBlenderRecipes.add(itemData);
                break;
            }
            case 1: {
                Recipes.remoteBlenderRecipes.add(itemData);
                break;
            }
            case 2: {
                Recipes.commBlenderRecipes.add(itemData);
            }
        }
    }

    public static void addMicrowaveRecipe(RecipeData itemData, int type) {
        switch (type) {
            case 0: {
                Recipes.localMicrowaveRecipes.add(itemData);
                break;
            }
            case 1: {
                Recipes.remoteMicrowaveRecipes.add(itemData);
                break;
            }
            case 2: {
                Recipes.commMicrowaveRecipes.add(itemData);
            }
        }
    }

    public static void addWashingMachineRecipe(RecipeData itemData, int type) {
        switch (type) {
            case 0: {
                Recipes.localWashingMachineRecipes.add(itemData);
                break;
            }
            case 1: {
                Recipes.remoteWashingMachineRecipes.add(itemData);
                break;
            }
            case 2: {
                Recipes.commWashingMachineRecipes.add(itemData);
            }
        }
    }

    public static void addDishwasherRecipe(RecipeData itemData, int type) {
        switch (type) {
            case 0: {
                Recipes.localDishwasherRecipes.add(itemData);
                break;
            }
            case 1: {
                Recipes.remoteDishwasherRecipes.add(itemData);
                break;
            }
            case 2: {
                Recipes.commDishwasherRecipes.add(itemData);
            }
        }
    }

    public static RecipeData[] getMineBayItems() {
        return Recipes.getMineBayItems();
    }

    public static RecipeData getOvenRecipeFromInput(ItemStack itemStack) {
        return Recipes.getOvenRecipeFromInput(itemStack);
    }

    public static RecipeData getFreezerRecipeFromInput(ItemStack itemStack) {
        return Recipes.getFreezerRecipeFromInput(itemStack);
    }

    public static RecipeData getPrinterRecipeFromInput(ItemStack itemStack) {
        return Recipes.getPrinterRecipeFromInput(itemStack);
    }

    public static RecipeData getToasterRecipeFromInput(ItemStack itemStack) {
        return Recipes.getToasterRecipeFormInput(itemStack);
    }

    public static RecipeData getChoppingBoardRecipeFromInput(ItemStack itemStack) {
        return Recipes.getChoppingBoardRecipeFromInput(itemStack);
    }

    public static RecipeData getBlenderRecipeDataFromIngredients(ItemStack[] itemStack) {
        return Recipes.getBlenderRecipeFromIngredients(itemStack);
    }

    public static RecipeData getMicrowaveRecipeFromIngredients(ItemStack itemStack) {
        return Recipes.getMicrowaveRecipeFromInput(itemStack);
    }

    public static RecipeData getWashingMachineRecipeFromInput(ItemStack itemStack) {
        return Recipes.getWashingMachineRecipeFromInput(itemStack);
    }

    public static RecipeData getDishwasherRecipeFromInput(ItemStack itemStack) {
        return Recipes.getDishwasherRecipeFromInput(itemStack);
    }
}

