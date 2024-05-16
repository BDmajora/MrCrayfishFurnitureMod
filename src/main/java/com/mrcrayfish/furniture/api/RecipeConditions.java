/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package com.mrcrayfish.furniture.api;

import com.mrcrayfish.furniture.api.RecipeVariables;
import net.minecraft.item.ItemStack;

public class RecipeConditions {
    public static boolean hasMineBayArgs(RecipeVariables params) {
        if (params.get("input") == null) {
            return false;
        }
        return params.get("input") instanceof ItemStack;
    }

    public static boolean hasOvenArgs(RecipeVariables params) {
        if (params.get("input") == null) {
            return false;
        }
        if (!(params.get("input") instanceof ItemStack)) {
            return false;
        }
        if (params.get("output") == null) {
            return false;
        }
        return params.get("output") instanceof ItemStack;
    }

    public static boolean hasFreezerArgs(RecipeVariables params) {
        if (params.get("input") == null) {
            return false;
        }
        if (!(params.get("input") instanceof ItemStack)) {
            return false;
        }
        if (params.get("output") == null) {
            return false;
        }
        return params.get("output") instanceof ItemStack;
    }

    public static boolean hasPrinterArgs(RecipeVariables params) {
        if (params.get("input") == null) {
            return false;
        }
        return params.get("input") instanceof ItemStack;
    }

    public static boolean hasChoppingBoardArgs(RecipeVariables params) {
        if (params.get("input") == null) {
            return false;
        }
        if (!(params.get("input") instanceof ItemStack)) {
            return false;
        }
        if (params.get("output") == null) {
            return false;
        }
        return params.get("output") instanceof ItemStack;
    }

    public static boolean hasBlenderArgs(RecipeVariables params) {
        if (params.get("name") == null) {
            return false;
        }
        if (!(params.get("name") instanceof String)) {
            return false;
        }
        if (params.get("heal") == null) {
            return false;
        }
        if (!(params.get("heal") instanceof Integer)) {
            return false;
        }
        if (params.get("ingredients") == null) {
            return false;
        }
        if (!(params.get("ingredients") instanceof ItemStack[])) {
            return false;
        }
        if (((ItemStack[])params.get("ingredients")).length == 0 | ((ItemStack[])params.get("ingredients")).length > 4) {
            return false;
        }
        if (params.get("colour") == null) {
            return false;
        }
        if (!(params.get("colour") instanceof int[])) {
            return false;
        }
        return ((int[])params.get("colour")).length == 3;
    }

    public static boolean hasMicrowaveArgs(RecipeVariables params) {
        if (params.get("input") == null) {
            return false;
        }
        if (!(params.get("input") instanceof ItemStack)) {
            return false;
        }
        if (params.get("output") == null) {
            return false;
        }
        return params.get("output") instanceof ItemStack;
    }

    public static boolean hasWashingMachineArgs(RecipeVariables params) {
        if (params.get("input") == null) {
            return false;
        }
        return params.get("input") instanceof ItemStack;
    }

    public static boolean hasToasterArgs(RecipeVariables params) {
        if (params.get("input") == null) {
            return false;
        }
        if (!(params.get("input") instanceof ItemStack)) {
            return false;
        }
        if (params.get("output") == null) {
            return false;
        }
        return params.get("output") instanceof ItemStack;
    }

    public static boolean hasDishwasherArgs(RecipeVariables params) {
        if (params.get("input") == null) {
            return false;
        }
        return params.get("input") instanceof ItemStack;
    }
}

