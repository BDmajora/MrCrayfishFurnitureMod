/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package com.mrcrayfish.furniture.api;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.api.RecipeData;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;

public class Recipes {
    public static ArrayList<String> recipeData = new ArrayList();
    public static ArrayList<RecipeData> localMineBayRecipes = new ArrayList();
    public static ArrayList<RecipeData> localOvenRecipes = new ArrayList();
    public static ArrayList<RecipeData> localFreezerRecipes = new ArrayList();
    public static ArrayList<RecipeData> localPrinterRecipes = new ArrayList();
    public static ArrayList<RecipeData> localToasterRecipes = new ArrayList();
    public static ArrayList<RecipeData> localChoppingBoardRecipes = new ArrayList();
    public static ArrayList<RecipeData> localBlenderRecipes = new ArrayList();
    public static ArrayList<RecipeData> localDishwasherRecipes = new ArrayList();
    public static ArrayList<RecipeData> localWashingMachineRecipes = new ArrayList();
    public static ArrayList<RecipeData> localMicrowaveRecipes = new ArrayList();
    public static ArrayList<RecipeData> commMineBayRecipes = new ArrayList();
    public static ArrayList<RecipeData> commOvenRecipes = new ArrayList();
    public static ArrayList<RecipeData> commFreezerRecipes = new ArrayList();
    public static ArrayList<RecipeData> commPrinterRecipes = new ArrayList();
    public static ArrayList<RecipeData> commToasterRecipes = new ArrayList();
    public static ArrayList<RecipeData> commChoppingBoardRecipes = new ArrayList();
    public static ArrayList<RecipeData> commBlenderRecipes = new ArrayList();
    public static ArrayList<RecipeData> commDishwasherRecipes = new ArrayList();
    public static ArrayList<RecipeData> commWashingMachineRecipes = new ArrayList();
    public static ArrayList<RecipeData> commMicrowaveRecipes = new ArrayList();
    public static ArrayList<RecipeData> remoteMineBayRecipes = new ArrayList();
    public static ArrayList<RecipeData> remoteOvenRecipes = new ArrayList();
    public static ArrayList<RecipeData> remoteFreezerRecipes = new ArrayList();
    public static ArrayList<RecipeData> remotePrinterRecipes = new ArrayList();
    public static ArrayList<RecipeData> remoteToasterRecipes = new ArrayList();
    public static ArrayList<RecipeData> remoteChoppingBoardRecipes = new ArrayList();
    public static ArrayList<RecipeData> remoteBlenderRecipes = new ArrayList();
    public static ArrayList<RecipeData> remoteDishwasherRecipes = new ArrayList();
    public static ArrayList<RecipeData> remoteWashingMachineRecipes = new ArrayList();
    public static ArrayList<RecipeData> remoteMicrowaveRecipes = new ArrayList();

    public static RecipeData[] getMineBayItems() {
        return Recipes.getRecipes("minebay").toArray(new RecipeData[0]);
    }

    public static RecipeData getOvenRecipeFromInput(ItemStack itemStack) {
        ArrayList<RecipeData> recipes = Recipes.getRecipes("oven");
        for (int i = 0; i < recipes.size(); ++i) {
            ItemStack validItemStack = recipes.get(i).getInput();
            if (validItemStack.getItem() != itemStack.getItem() || validItemStack.getMetadata() != itemStack.getMetadata()) continue;
            return recipes.get(i);
        }
        return null;
    }

    public static RecipeData getFreezerRecipeFromInput(ItemStack itemStack) {
        ArrayList<RecipeData> recipes = Recipes.getRecipes("freezer");
        for (int i = 0; i < recipes.size(); ++i) {
            ItemStack validItemStack = recipes.get(i).getInput();
            if (validItemStack.getItem() != itemStack.getItem() || validItemStack.getMetadata() != itemStack.getMetadata()) continue;
            return recipes.get(i);
        }
        return null;
    }

    public static RecipeData getPrinterRecipeFromInput(ItemStack itemStack) {
        ArrayList<RecipeData> recipes = Recipes.getRecipes("printer");
        for (int i = 0; i < recipes.size(); ++i) {
            ItemStack validItemStack = recipes.get(i).getInput();
            if (validItemStack.getItem() != itemStack.getItem() || validItemStack.getMetadata() != itemStack.getMetadata() || itemStack.stackSize != 1) continue;
            return recipes.get(i);
        }
        return null;
    }

    public static RecipeData getToasterRecipeFormInput(ItemStack itemStack) {
        ArrayList<RecipeData> recipes = Recipes.getRecipes("toaster");
        for (int i = 0; i < recipes.size(); ++i) {
            ItemStack validItemStack = recipes.get(i).getInput();
            if (validItemStack.getItem() != itemStack.getItem() || validItemStack.getMetadata() != itemStack.getMetadata()) continue;
            return recipes.get(i);
        }
        return null;
    }

    public static RecipeData getChoppingBoardRecipeFromInput(ItemStack itemStack) {
        ArrayList<RecipeData> recipes = Recipes.getRecipes("choppingboard");
        for (int i = 0; i < recipes.size(); ++i) {
            ItemStack validItemStack = recipes.get(i).getInput();
            if (validItemStack.getItem() != itemStack.getItem() || validItemStack.getMetadata() != itemStack.getMetadata()) continue;
            return recipes.get(i);
        }
        return null;
    }

    public static RecipeData getBlenderRecipeFromIngredients(ItemStack[] itemStack) {
        ArrayList<RecipeData> recipes = Recipes.getRecipes("blender");
        for (int i = 0; i < recipes.size(); ++i) {
            int count = 0;
            ArrayList<ItemStack> ingredients = recipes.get(i).getIngredients();
            for (ItemStack item : itemStack) {
                if (item == null) continue;
                for (ItemStack ingredient : ingredients) {
                    if (ingredient.getItem() == item.getItem() && ingredient.stackSize == item.stackSize && ingredient.getMetadata() == item.getMetadata()) {
                        ++count;
                    }
                    if (count != ingredients.size()) continue;
                    return recipes.get(i);
                }
            }
        }
        return null;
    }

    public static RecipeData getMicrowaveRecipeFromInput(ItemStack itemStack) {
        ArrayList<RecipeData> recipes = Recipes.getRecipes("microwave");
        for (int i = 0; i < recipes.size(); ++i) {
            ItemStack validItemStack = recipes.get(i).getInput();
            if (validItemStack.getItem() != itemStack.getItem() || validItemStack.getMetadata() != itemStack.getMetadata()) continue;
            return recipes.get(i);
        }
        return null;
    }

    public static RecipeData getWashingMachineRecipeFromInput(ItemStack itemStack) {
        ArrayList<RecipeData> recipes = Recipes.getRecipes("washingmachine");
        for (int i = 0; i < recipes.size(); ++i) {
            ItemStack validItemStack = recipes.get(i).getInput();
            if (validItemStack.getItem() != itemStack.getItem()) continue;
            return recipes.get(i);
        }
        return null;
    }

    public static RecipeData getDishwasherRecipeFromInput(ItemStack itemStack) {
        ArrayList<RecipeData> recipes = Recipes.getRecipes("dishwasher");
        for (int i = 0; i < recipes.size(); ++i) {
            ItemStack validItemStack = recipes.get(i).getInput();
            if (validItemStack.getItem() != itemStack.getItem()) continue;
            return recipes.get(i);
        }
        return null;
    }

    public static void updateDataList() {
        recipeData.clear();
        for (RecipeData data : localMineBayRecipes) {
            recipeData.add("type=minebay," + data.toString());
        }
        for (RecipeData data : localOvenRecipes) {
            recipeData.add("type=oven," + data.toString());
        }
        for (RecipeData data : localFreezerRecipes) {
            recipeData.add("type=freezer," + data.toString());
        }
        for (RecipeData data : localPrinterRecipes) {
            recipeData.add("type=printer," + data.toString());
        }
        for (RecipeData data : localToasterRecipes) {
            recipeData.add("type=toaster," + data.toString());
        }
        for (RecipeData data : localChoppingBoardRecipes) {
            recipeData.add("type=choppingboard," + data.toString());
        }
        for (RecipeData data : localBlenderRecipes) {
            recipeData.add("type=blender," + data.toString());
        }
        for (RecipeData data : localMicrowaveRecipes) {
            recipeData.add("type=microwave," + data.toString());
        }
        for (RecipeData data : localWashingMachineRecipes) {
            recipeData.add("type=washingmachine," + data.toString());
        }
        for (RecipeData data : localDishwasherRecipes) {
            recipeData.add("type=dishwasher," + data.toString());
        }
    }

    public static ArrayList<RecipeData> getRecipes(String type) {
        if (MrCrayfishFurnitureMod.proxy.isSinglePlayer() | MrCrayfishFurnitureMod.proxy.isDedicatedServer()) {
            if (type.equalsIgnoreCase("minebay")) {
                return localMineBayRecipes;
            }
            if (type.equalsIgnoreCase("freezer")) {
                return localFreezerRecipes;
            }
            if (type.equalsIgnoreCase("oven")) {
                return localOvenRecipes;
            }
            if (type.equalsIgnoreCase("printer")) {
                return localPrinterRecipes;
            }
            if (type.equalsIgnoreCase("toaster")) {
                return localToasterRecipes;
            }
            if (type.equalsIgnoreCase("choppingboard")) {
                return localChoppingBoardRecipes;
            }
            if (type.equalsIgnoreCase("blender")) {
                return localBlenderRecipes;
            }
            if (type.equalsIgnoreCase("microwave")) {
                return localMicrowaveRecipes;
            }
            if (type.equalsIgnoreCase("washingmachine")) {
                return localWashingMachineRecipes;
            }
            if (type.equalsIgnoreCase("dishwasher")) {
                return localDishwasherRecipes;
            }
        } else {
            if (type.equalsIgnoreCase("minebay")) {
                return remoteMineBayRecipes;
            }
            if (type.equalsIgnoreCase("freezer")) {
                return remoteFreezerRecipes;
            }
            if (type.equalsIgnoreCase("oven")) {
                return remoteOvenRecipes;
            }
            if (type.equalsIgnoreCase("printer")) {
                return remotePrinterRecipes;
            }
            if (type.equalsIgnoreCase("toaster")) {
                return remoteToasterRecipes;
            }
            if (type.equalsIgnoreCase("choppingboard")) {
                return remoteChoppingBoardRecipes;
            }
            if (type.equalsIgnoreCase("blender")) {
                return remoteBlenderRecipes;
            }
            if (type.equalsIgnoreCase("microwave")) {
                return remoteMicrowaveRecipes;
            }
            if (type.equalsIgnoreCase("washingmachine")) {
                return remoteWashingMachineRecipes;
            }
            if (type.equalsIgnoreCase("dishwasher")) {
                return remoteDishwasherRecipes;
            }
        }
        return new ArrayList<RecipeData>();
    }

    public static void addCommRecipesToLocal() {
        for (RecipeData data : commMineBayRecipes) {
            localMineBayRecipes.add(data);
        }
        for (RecipeData data : commOvenRecipes) {
            localOvenRecipes.add(data);
        }
        for (RecipeData data : commFreezerRecipes) {
            localFreezerRecipes.add(data);
        }
        for (RecipeData data : commPrinterRecipes) {
            localPrinterRecipes.add(data);
        }
        for (RecipeData data : commToasterRecipes) {
            localToasterRecipes.add(data);
        }
        for (RecipeData data : commChoppingBoardRecipes) {
            localChoppingBoardRecipes.add(data);
        }
        for (RecipeData data : commBlenderRecipes) {
            localBlenderRecipes.add(data);
        }
        for (RecipeData data : commMicrowaveRecipes) {
            localMicrowaveRecipes.add(data);
        }
        for (RecipeData data : commWashingMachineRecipes) {
            localWashingMachineRecipes.add(data);
        }
        for (RecipeData data : commDishwasherRecipes) {
            localDishwasherRecipes.add(data);
        }
    }

    public static void clearLocalRecipes() {
        localMineBayRecipes.clear();
        localOvenRecipes.clear();
        localFreezerRecipes.clear();
        localPrinterRecipes.clear();
        localToasterRecipes.clear();
        localChoppingBoardRecipes.clear();
        localBlenderRecipes.clear();
        localMicrowaveRecipes.clear();
        localWashingMachineRecipes.clear();
        localDishwasherRecipes.clear();
    }

    public static void clearRemoteRecipes() {
        remoteMineBayRecipes.clear();
        remoteOvenRecipes.clear();
        remoteFreezerRecipes.clear();
        remotePrinterRecipes.clear();
        remoteToasterRecipes.clear();
        remoteChoppingBoardRecipes.clear();
        remoteBlenderRecipes.clear();
        remoteMicrowaveRecipes.clear();
        remoteWashingMachineRecipes.clear();
        remoteDishwasherRecipes.clear();
    }

    public static void clearCommRecipes() {
        commMineBayRecipes.clear();
        commOvenRecipes.clear();
        commFreezerRecipes.clear();
        commPrinterRecipes.clear();
        commToasterRecipes.clear();
        commChoppingBoardRecipes.clear();
        commBlenderRecipes.clear();
        commMicrowaveRecipes.clear();
        commWashingMachineRecipes.clear();
        commDishwasherRecipes.clear();
    }

    public static void clearAll() {
        Recipes.clearLocalRecipes();
        Recipes.clearRemoteRecipes();
        Recipes.clearCommRecipes();
    }
}

