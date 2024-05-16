/*
 * Decompiled with CFR 0.152.
 */
package com.mrcrayfish.furniture.api;

import com.mrcrayfish.furniture.api.IRecipeRegistry;
import com.mrcrayfish.furniture.api.RecipeAPI;
import com.mrcrayfish.furniture.api.RecipeConditions;
import com.mrcrayfish.furniture.api.RecipeData;
import com.mrcrayfish.furniture.api.RecipeUtil;
import com.mrcrayfish.furniture.api.RecipeVariables;
import com.mrcrayfish.furniture.handler.ConfigurationHandler;

public class RecipeRegistryComm
extends RecipeAPI
implements IRecipeRegistry {
    private static RecipeRegistryComm furnitureRegister = null;
    private String modName;

    public static RecipeRegistryComm getInstance(String modName) {
        if (furnitureRegister == null) {
            furnitureRegister = new RecipeRegistryComm();
        }
        RecipeRegistryComm.furnitureRegister.modName = modName;
        return furnitureRegister;
    }

    @Override
    public void registerRecipe(String type, RecipeVariables params) {
        if (type.equalsIgnoreCase("minebay")) {
            if (RecipeConditions.hasMineBayArgs(params)) {
                RecipeRegistryComm.addMineBayRecipe(RecipeData.convertFrom(params), 2);
            } else if (ConfigurationHandler.api_debug) {
                RecipeUtil.printRequired(type, "Missing required variables", this.modName);
            }
        } else if (type.equalsIgnoreCase("oven")) {
            if (RecipeConditions.hasOvenArgs(params)) {
                RecipeRegistryComm.addOvenRecipe(RecipeData.convertFrom(params), 2);
            } else if (ConfigurationHandler.api_debug) {
                RecipeUtil.printRequired(type, "Missing required variables", this.modName);
            }
        } else if (type.equalsIgnoreCase("freezer")) {
            if (RecipeConditions.hasFreezerArgs(params)) {
                RecipeRegistryComm.addFreezerRecipe(RecipeData.convertFrom(params), 2);
            } else if (ConfigurationHandler.api_debug) {
                RecipeUtil.printRequired(type, "Missing required variables", this.modName);
            }
        } else if (type.equalsIgnoreCase("printer")) {
            if (RecipeConditions.hasPrinterArgs(params)) {
                RecipeRegistryComm.addPrinterRecipe(RecipeData.convertFrom(params), 2);
            } else if (ConfigurationHandler.api_debug) {
                RecipeUtil.printRequired(type, "Missing required variables for " + type, this.modName);
            }
        } else if (type.equalsIgnoreCase("toaster")) {
            if (RecipeConditions.hasToasterArgs(params)) {
                RecipeRegistryComm.addToasterRecipe(RecipeData.convertFrom(params), 2);
            } else if (ConfigurationHandler.api_debug) {
                RecipeUtil.printRequired(type, "Missing required variables for " + type, this.modName);
            }
        } else if (type.equalsIgnoreCase("choppingboard")) {
            if (RecipeConditions.hasChoppingBoardArgs(params)) {
                RecipeRegistryComm.addChoppingBoardRecipe(RecipeData.convertFrom(params), 2);
            } else if (ConfigurationHandler.api_debug) {
                RecipeUtil.printRequired(type, "Missing required variables for " + type, this.modName);
            }
        } else if (type.equalsIgnoreCase("blender")) {
            if (RecipeConditions.hasBlenderArgs(params)) {
                RecipeRegistryComm.addBlenderRecipe(RecipeData.convertFrom(params), 2);
            } else if (ConfigurationHandler.api_debug) {
                RecipeUtil.printRequired(type, "Missing required variables for " + type, this.modName);
            }
        } else if (type.equalsIgnoreCase("microwave")) {
            if (RecipeConditions.hasMicrowaveArgs(params)) {
                RecipeRegistryComm.addMicrowaveRecipe(RecipeData.convertFrom(params), 2);
            } else if (ConfigurationHandler.api_debug) {
                RecipeUtil.printRequired(type, "Missing required variables for " + type, this.modName);
            }
        } else if (type.equalsIgnoreCase("washingmachine")) {
            if (RecipeConditions.hasWashingMachineArgs(params)) {
                RecipeRegistryComm.addWashingMachineRecipe(RecipeData.convertFrom(params), 2);
            } else if (ConfigurationHandler.api_debug) {
                RecipeUtil.printRequired(type, "Missing required variables for " + type, this.modName);
            }
        } else if (type.equalsIgnoreCase("dishwasher")) {
            if (RecipeConditions.hasDishwasherArgs(params)) {
                RecipeRegistryComm.addDishwasherRecipe(RecipeData.convertFrom(params), 2);
            } else if (ConfigurationHandler.api_debug) {
                RecipeUtil.printRequired(type, "Missing required variables for " + type, this.modName);
            }
        } else if (ConfigurationHandler.api_debug) {
            System.err.println("## RecipeAPI Error Report ##");
            System.err.println("From Mod: " + this.modName);
            System.err.println("Description:  The mod '" + this.modName + "' is trying to add a non existing recipe type '" + type + "'.");
            System.err.println("Type: " + type + " (Unknown)");
        }
        params.clear();
    }
}

