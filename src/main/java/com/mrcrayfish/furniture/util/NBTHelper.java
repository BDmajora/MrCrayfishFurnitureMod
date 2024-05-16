/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package com.mrcrayfish.furniture.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class NBTHelper {
    private static void initNBTTagCompound(ItemStack itemStack) {
        if (itemStack.stackTagCompound == null) {
            itemStack.setTagCompound(new NBTTagCompound());
        }
    }

    public static boolean hasTag(ItemStack itemStack, String keyName) {
        if (itemStack.stackTagCompound != null) {
            return itemStack.stackTagCompound.hasKey(keyName);
        }
        return false;
    }

    public static void removeTag(ItemStack itemStack, String keyName) {
        if (itemStack.stackTagCompound != null) {
            itemStack.stackTagCompound.removeTag(keyName);
        }
    }

    public static String getString(ItemStack itemStack, String keyName) {
        NBTHelper.initNBTTagCompound(itemStack);
        if (!itemStack.stackTagCompound.hasKey(keyName)) {
            NBTHelper.setString(itemStack, keyName, "");
        }
        return itemStack.stackTagCompound.getString(keyName);
    }

    public static void setString(ItemStack itemStack, String keyName, String keyValue) {
        NBTHelper.initNBTTagCompound(itemStack);
        itemStack.stackTagCompound.setString(keyName, keyValue);
    }

    public static boolean getBoolean(ItemStack itemStack, String keyName) {
        NBTHelper.initNBTTagCompound(itemStack);
        if (!itemStack.stackTagCompound.hasKey(keyName)) {
            NBTHelper.setBoolean(itemStack, keyName, false);
        }
        return itemStack.stackTagCompound.getBoolean(keyName);
    }

    public static void setBoolean(ItemStack itemStack, String keyName, boolean keyValue) {
        NBTHelper.initNBTTagCompound(itemStack);
        itemStack.stackTagCompound.setBoolean(keyName, keyValue);
    }

    public static byte getByte(ItemStack itemStack, String keyName) {
        NBTHelper.initNBTTagCompound(itemStack);
        if (!itemStack.stackTagCompound.hasKey(keyName)) {
            NBTHelper.setByte(itemStack, keyName, (byte)0);
        }
        return itemStack.stackTagCompound.getByte(keyName);
    }

    public static void setByte(ItemStack itemStack, String keyName, byte keyValue) {
        NBTHelper.initNBTTagCompound(itemStack);
        itemStack.stackTagCompound.setByte(keyName, keyValue);
    }

    public static short getShort(ItemStack itemStack, String keyName) {
        NBTHelper.initNBTTagCompound(itemStack);
        if (!itemStack.stackTagCompound.hasKey(keyName)) {
            NBTHelper.setShort(itemStack, keyName, (short)0);
        }
        return itemStack.stackTagCompound.getShort(keyName);
    }

    public static void setShort(ItemStack itemStack, String keyName, short keyValue) {
        NBTHelper.initNBTTagCompound(itemStack);
        itemStack.stackTagCompound.setShort(keyName, keyValue);
    }

    public static int getInt(ItemStack itemStack, String keyName) {
        NBTHelper.initNBTTagCompound(itemStack);
        if (!itemStack.stackTagCompound.hasKey(keyName)) {
            NBTHelper.setInteger(itemStack, keyName, 0);
        }
        return itemStack.stackTagCompound.getInteger(keyName);
    }

    public static void setInteger(ItemStack itemStack, String keyName, int keyValue) {
        NBTHelper.initNBTTagCompound(itemStack);
        itemStack.stackTagCompound.setInteger(keyName, keyValue);
    }

    public static long getLong(ItemStack itemStack, String keyName) {
        NBTHelper.initNBTTagCompound(itemStack);
        if (!itemStack.stackTagCompound.hasKey(keyName)) {
            NBTHelper.setLong(itemStack, keyName, 0L);
        }
        return itemStack.stackTagCompound.getLong(keyName);
    }

    public static void setLong(ItemStack itemStack, String keyName, long keyValue) {
        NBTHelper.initNBTTagCompound(itemStack);
        itemStack.stackTagCompound.setLong(keyName, keyValue);
    }

    public static float getFloat(ItemStack itemStack, String keyName) {
        NBTHelper.initNBTTagCompound(itemStack);
        if (!itemStack.stackTagCompound.hasKey(keyName)) {
            NBTHelper.setFloat(itemStack, keyName, 0.0f);
        }
        return itemStack.stackTagCompound.getFloat(keyName);
    }

    public static void setFloat(ItemStack itemStack, String keyName, float keyValue) {
        NBTHelper.initNBTTagCompound(itemStack);
        itemStack.stackTagCompound.setFloat(keyName, keyValue);
    }

    public static double getDouble(ItemStack itemStack, String keyName) {
        NBTHelper.initNBTTagCompound(itemStack);
        if (!itemStack.stackTagCompound.hasKey(keyName)) {
            NBTHelper.setDouble(itemStack, keyName, 0.0);
        }
        return itemStack.stackTagCompound.getDouble(keyName);
    }

    public static void setDouble(ItemStack itemStack, String keyName, double keyValue) {
        NBTHelper.initNBTTagCompound(itemStack);
        itemStack.stackTagCompound.setDouble(keyName, keyValue);
    }

    public static NBTTagCompound getCompoundTag(ItemStack itemStack, String tagName) {
        NBTHelper.initNBTTagCompound(itemStack);
        if (!itemStack.stackTagCompound.hasKey(tagName)) {
            itemStack.stackTagCompound.setTag(tagName, (NBTBase)new NBTTagCompound());
        }
        return itemStack.stackTagCompound.getCompoundTag(tagName);
    }

    public static void setCompoundTag(ItemStack itemStack, String tagName, NBTTagCompound tagValue) {
        NBTHelper.initNBTTagCompound(itemStack);
        itemStack.stackTagCompound.setTag(tagName, (NBTBase)tagValue);
    }
}

