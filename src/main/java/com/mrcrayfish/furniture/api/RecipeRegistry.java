/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.EnumChatFormatting
 */
package com.mrcrayfish.furniture.api;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.api.Parser;
import com.mrcrayfish.furniture.api.RecipeAPI;
import com.mrcrayfish.furniture.api.RecipeData;
import com.mrcrayfish.furniture.api.RecipeUtil;
import com.mrcrayfish.furniture.handler.ConfigurationHandler;
import java.util.ArrayList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;

public class RecipeRegistry
extends RecipeAPI {
    private static RecipeRegistry furnitureRegister = null;

    public static RecipeRegistry getInstance() {
        if (furnitureRegister == null) {
            furnitureRegister = new RecipeRegistry();
        }
        return furnitureRegister;
    }

    public void registerMineBayItem(ItemStack item, ItemStack currency, int price) {
        RecipeRegistry.addMineBayRecipe(new RecipeData().setInput(item).setCurrency(currency).setPrice(price), 0);
    }

    public void registerOvenRecipe(ItemStack input, ItemStack output) {
        RecipeRegistry.addOvenRecipe(new RecipeData().setInput(input).setOutput(output), 0);
    }

    public void registerFreezerRecipe(ItemStack input, ItemStack output) {
        RecipeRegistry.addFreezerRecipe(new RecipeData().setInput(input).setOutput(output), 0);
    }

    public void registerPrinterRecipe(ItemStack input) {
        RecipeRegistry.addPrinterRecipe(new RecipeData().setInput(input), 0);
    }

    public void registerChoppingBoardRecipe(ItemStack input, ItemStack output) {
        RecipeRegistry.addChoppingBoardRecipe(new RecipeData().setInput(input).setOutput(output), 0);
    }

    public void registerToasterRecipe(ItemStack input, ItemStack output) {
        RecipeRegistry.addToasterRecipe(new RecipeData().setInput(input).setOutput(output), 0);
    }

    public void registerMicrowaveRecipe(ItemStack input, ItemStack output) {
        RecipeRegistry.addMicrowaveRecipe(new RecipeData().setInput(input).setOutput(output), 0);
    }

    public void registerWashingMachineRecipe(ItemStack input) {
        RecipeRegistry.addWashingMachineRecipe(new RecipeData().setInput(input), 0);
    }

    public void registerDishwasherRecipe(ItemStack input) {
        RecipeRegistry.addDishwasherRecipe(new RecipeData().setInput(input), 0);
    }

    public void registerBlenderRecipe(String name, int heal, ItemStack[] ingredients, int[] rgb) {
        RecipeData data = new RecipeData();
        data.setName(name);
        data.setHeal(heal);
        for (ItemStack item : ingredients) {
            data.addIngredient(item);
        }
        data.setColour(rgb[0], rgb[1], rgb[2]);
        RecipeRegistry.addBlenderRecipe(data, 0);
    }

    public static void registerPrinterRecipe(Parser parser, int num) {
        String input_item = parser.getValue("input-item", null);
        String input_metadata = parser.getValue("input-metadata", "0");
        if (input_item != null) {
            Item input = (Item)Item.itemRegistry.getObject(input_item);
            if (input != null) {
                int i_metadata = 0;
                try {
                    i_metadata = Integer.parseInt(input_metadata);
                }
                catch (NumberFormatException e) {
                    if (ConfigurationHandler.api_debug) {
                        RecipeUtil.printReport(parser, num, "input-metadata", "Could not parse the value '" + input_metadata + "' to an integer.");
                    }
                    return;
                }
                RecipeRegistry.getInstance().registerPrinterRecipe(new ItemStack(input, 1, i_metadata));
            } else if (ConfigurationHandler.api_debug) {
                RecipeUtil.printReport(parser, num, "input-item", "The input-item '" + input_item + "' does not exist");
            }
        } else if (ConfigurationHandler.api_debug) {
            RecipeUtil.printMissing(parser, num, "input-item", "An input-item is required");
        }
    }

    public static void registerOvenRecipe(Parser parser, int num) {
        String input_item = parser.getValue("input-item", null);
        String input_metadata = parser.getValue("input-metadata", "0");
        String output_item = parser.getValue("output-item", null);
        String output_metadata = parser.getValue("output-metadata", "0");
        String output_amount = parser.getValue("output-amount", "1");
        if (input_item != null) {
            if (output_item != null) {
                Item input = (Item)Item.itemRegistry.getObject(input_item);
                Item output = (Item)Item.itemRegistry.getObject(output_item);
                if (input != null) {
                    if (output != null) {
                        int i_metadata = 0;
                        try {
                            i_metadata = Integer.parseInt(input_metadata);
                        }
                        catch (NumberFormatException e) {
                            if (ConfigurationHandler.api_debug) {
                                RecipeUtil.printReport(parser, num, "input-metadata", "Could not parse the value '" + input_metadata + "' to an integer");
                            }
                            return;
                        }
                        int o_amount = 1;
                        try {
                            o_amount = Integer.parseInt(output_amount);
                        }
                        catch (NumberFormatException e) {
                            if (ConfigurationHandler.api_debug) {
                                RecipeUtil.printReport(parser, num, "output-amount", "Could not parse the value '" + output_amount + "' to an integer");
                            }
                            return;
                        }
                        int o_metadata = 0;
                        try {
                            o_metadata = Integer.parseInt(output_metadata);
                        }
                        catch (NumberFormatException e) {
                            if (ConfigurationHandler.api_debug) {
                                RecipeUtil.printReport(parser, num, "output-metadata", "Could not parse the value '" + output_metadata + "' to an integer");
                            }
                            return;
                        }
                        RecipeRegistry.getInstance().registerOvenRecipe(new ItemStack(input, 1, i_metadata), new ItemStack(output, o_amount, o_metadata));
                    } else if (ConfigurationHandler.api_debug) {
                        RecipeUtil.printReport(parser, num, "output-item", "The output-item '" + output_item + "' does not exist");
                    }
                } else if (ConfigurationHandler.api_debug) {
                    RecipeUtil.printReport(parser, num, "input-item", "The input-item '" + input_item + "' does not exist");
                }
            } else if (ConfigurationHandler.api_debug) {
                RecipeUtil.printMissing(parser, num, "output-item", "An output-item is required");
            }
        } else if (ConfigurationHandler.api_debug) {
            RecipeUtil.printMissing(parser, num, "input-item", "An input-item is required");
        }
    }

    public static void registerFreezerRecipe(Parser parser, int num) {
        String input_item = parser.getValue("input-item", null);
        String input_metadata = parser.getValue("input-metadata", "0");
        String output_item = parser.getValue("output-item", null);
        String output_metadata = parser.getValue("output-metadata", "0");
        String output_amount = parser.getValue("output-amount", "1");
        if (input_item != null) {
            if (output_item != null) {
                Item input = (Item)Item.itemRegistry.getObject(input_item);
                Item output = (Item)Item.itemRegistry.getObject(output_item);
                if (input != null) {
                    if (output != null) {
                        int i_metadata = 0;
                        try {
                            i_metadata = Integer.parseInt(input_metadata);
                        }
                        catch (NumberFormatException e) {
                            if (ConfigurationHandler.api_debug) {
                                RecipeUtil.printReport(parser, num, "input-metdata", "Could not parse the value '" + input_metadata + "' to an integer");
                            }
                            return;
                        }
                        int o_amount = 1;
                        try {
                            o_amount = Integer.parseInt(output_amount);
                        }
                        catch (NumberFormatException e) {
                            if (ConfigurationHandler.api_debug) {
                                RecipeUtil.printReport(parser, num, "output-amount", "Could not parse the value '" + output_amount + "' to an integer");
                            }
                            return;
                        }
                        int o_metadata = 0;
                        try {
                            o_metadata = Integer.parseInt(output_metadata);
                        }
                        catch (NumberFormatException e) {
                            if (ConfigurationHandler.api_debug) {
                                RecipeUtil.printReport(parser, num, "output-metadata", "Could not parse the value '" + output_metadata + "' to an integer");
                            }
                            return;
                        }
                        RecipeRegistry.getInstance().registerFreezerRecipe(new ItemStack(input, 1, i_metadata), new ItemStack(output, o_amount, o_metadata));
                    } else if (ConfigurationHandler.api_debug) {
                        RecipeUtil.printReport(parser, num, "output-item", "The output-item '" + output_item + "' does not exist");
                    }
                } else if (ConfigurationHandler.api_debug) {
                    RecipeUtil.printReport(parser, num, "input-item", "The input-item '" + input_item + "' does not exist");
                }
            } else if (ConfigurationHandler.api_debug) {
                RecipeUtil.printMissing(parser, num, "output-item", "An output-item is required");
            }
        } else if (ConfigurationHandler.api_debug) {
            RecipeUtil.printMissing(parser, num, "input-item", "An input-item is required");
        }
    }

    public static void registerMineBayRecipe(Parser parser, int num) {
        String input_item = parser.getValue("input-item", null);
        String input_metadata = parser.getValue("input-metadata", "0");
        String input_amount = parser.getValue("input-amount", "1");
        String payment_item = parser.getValue("payment-item", "minecraft:emerald");
        String payment_item_metadata = parser.getValue("payment-metadata", "0");
        String price = parser.getValue("payment-price", "1");
        if (input_item != null) {
            Item input = (Item)Item.itemRegistry.getObject(input_item);
            Item payment = (Item)Item.itemRegistry.getObject(payment_item);
            if (input != null) {
                if (payment != null) {
                    int i_metadata = 0;
                    try {
                        i_metadata = Integer.parseInt(input_metadata);
                    }
                    catch (NumberFormatException e) {
                        if (ConfigurationHandler.api_debug) {
                            RecipeUtil.printReport(parser, num, "input-metadata", "Could not parse the value '" + input_metadata + "' to an integer");
                        }
                        return;
                    }
                    int i_amount = 0;
                    try {
                        i_amount = Integer.parseInt(input_amount);
                    }
                    catch (NumberFormatException e) {
                        if (ConfigurationHandler.api_debug) {
                            RecipeUtil.printReport(parser, num, "input-amount", "Could not parse the value '" + input_amount + "' to an integer");
                        }
                        return;
                    }
                    int p_metadata = 0;
                    try {
                        p_metadata = Integer.parseInt(payment_item_metadata);
                    }
                    catch (NumberFormatException e) {
                        if (ConfigurationHandler.api_debug) {
                            RecipeUtil.printReport(parser, num, "payment-metadata", "Could not parse the value '" + p_metadata + "' to an integer");
                        }
                        return;
                    }
                    int p_price = 1;
                    try {
                        p_price = Integer.parseInt(price);
                    }
                    catch (NumberFormatException e) {
                        if (ConfigurationHandler.api_debug) {
                            RecipeUtil.printReport(parser, num, "price", "Could not parse the value '" + p_price + "' to an integer");
                        }
                        return;
                    }
                    RecipeRegistry.getInstance().registerMineBayItem(new ItemStack(input, i_amount, i_metadata), new ItemStack(payment, 1, p_metadata), p_price);
                } else if (ConfigurationHandler.api_debug) {
                    RecipeUtil.printReport(parser, num, "payment-item", "The payment-item '" + payment_item + "' does not exist");
                }
            } else if (ConfigurationHandler.api_debug) {
                RecipeUtil.printReport(parser, num, "input-item", "The input-item '" + input_item + "' does not exist");
            }
        } else if (ConfigurationHandler.api_debug) {
            RecipeUtil.printMissing(parser, num, "input-item", "An input-item is required");
        }
    }

    public static void registerToasterRecipe(Parser parser, int num) {
        String input_item = parser.getValue("input-item", null);
        String input_metadata = parser.getValue("input-metadata", "0");
        String output_item = parser.getValue("output-item", null);
        String output_metadata = parser.getValue("output-metadata", "0");
        String output_amount = parser.getValue("output-amount", "1");
        if (input_item != null) {
            if (output_item != null) {
                Item input = (Item)Item.itemRegistry.getObject(input_item);
                Item output = (Item)Item.itemRegistry.getObject(output_item);
                if (input != null) {
                    if (output != null) {
                        int i_metadata = 0;
                        try {
                            i_metadata = Integer.parseInt(input_metadata);
                        }
                        catch (NumberFormatException e) {
                            if (ConfigurationHandler.api_debug) {
                                RecipeUtil.printReport(parser, num, "input-metadata", "Could not parse the value '" + input_metadata + "' to an integer");
                            }
                            return;
                        }
                        int o_amount = 1;
                        try {
                            o_amount = Integer.parseInt(output_amount);
                        }
                        catch (NumberFormatException e) {
                            if (ConfigurationHandler.api_debug) {
                                RecipeUtil.printReport(parser, num, "output-amount", "Could not parse the value '" + output_amount + "' to an integer");
                            }
                            return;
                        }
                        int o_metadata = 0;
                        try {
                            o_metadata = Integer.parseInt(output_metadata);
                        }
                        catch (NumberFormatException e) {
                            if (ConfigurationHandler.api_debug) {
                                RecipeUtil.printReport(parser, num, "output-metadata", "Could not parse the value '" + output_metadata + "' to an integer");
                            }
                            return;
                        }
                        RecipeRegistry.getInstance().registerToasterRecipe(new ItemStack(input, 1, i_metadata), new ItemStack(output, o_amount, o_metadata));
                    } else if (ConfigurationHandler.api_debug) {
                        RecipeUtil.printReport(parser, num, "output-item", "The output-item '" + output_item + "' does not exist");
                    }
                } else if (ConfigurationHandler.api_debug) {
                    RecipeUtil.printReport(parser, num, "input-item", "The input-item '" + input_item + "' does not exist");
                }
            } else if (ConfigurationHandler.api_debug) {
                RecipeUtil.printMissing(parser, num, "output-item", "An output-item is required");
            }
        } else if (ConfigurationHandler.api_debug) {
            RecipeUtil.printMissing(parser, num, "input-item", "An input-item is required");
        }
    }

    public static void registerChoppingBoardRecipe(Parser parser, int num) {
        String input_item = parser.getValue("input-item", null);
        String input_metadata = parser.getValue("input-metadata", "0");
        String output_item = parser.getValue("output-item", null);
        String output_metadata = parser.getValue("output-metadata", "0");
        String output_amount = parser.getValue("output-amount", "1");
        if (input_item != null) {
            if (output_item != null) {
                Item input = (Item)Item.itemRegistry.getObject(input_item);
                Item output = (Item)Item.itemRegistry.getObject(output_item);
                if (input != null) {
                    if (output != null) {
                        int i_metadata = 0;
                        try {
                            i_metadata = Integer.parseInt(input_metadata);
                        }
                        catch (NumberFormatException e) {
                            if (ConfigurationHandler.api_debug) {
                                RecipeUtil.printReport(parser, num, "input-metadata", "Could not parse the value '" + input_metadata + "' to an integer");
                            }
                            return;
                        }
                        int o_amount = 1;
                        try {
                            o_amount = Integer.parseInt(output_amount);
                        }
                        catch (NumberFormatException e) {
                            if (ConfigurationHandler.api_debug) {
                                RecipeUtil.printReport(parser, num, "output-amount", "Could not parse the value '" + output_amount + "' to an integer");
                            }
                            return;
                        }
                        int o_metadata = 0;
                        try {
                            o_metadata = Integer.parseInt(output_metadata);
                        }
                        catch (NumberFormatException e) {
                            if (ConfigurationHandler.api_debug) {
                                RecipeUtil.printReport(parser, num, "output-metadata", "Could not parse the value '" + output_metadata + "' to an integer");
                            }
                            return;
                        }
                        RecipeRegistry.getInstance().registerChoppingBoardRecipe(new ItemStack(input, 1, i_metadata), new ItemStack(output, o_amount, o_metadata));
                    } else if (ConfigurationHandler.api_debug) {
                        RecipeUtil.printReport(parser, num, "output-item", "The output-item '" + output_item + "' does not exist");
                    }
                } else if (ConfigurationHandler.api_debug) {
                    RecipeUtil.printReport(parser, num, "input-item", "The input-item '" + input_item + "' does not exist");
                }
            } else if (ConfigurationHandler.api_debug) {
                RecipeUtil.printMissing(parser, num, "output-item", "An output-item is required");
            }
        } else if (ConfigurationHandler.api_debug) {
            RecipeUtil.printMissing(parser, num, "input-item", "An input-item is required");
        }
    }

    public static void registerBlenderRecipe(Parser parser, int num) {
        String name = parser.getValue("name", null);
        String heal_amount = parser.getValue("heal", null);
        String ingredients = parser.getValue("ingredients", null);
        String colour = parser.getValue("colour", null);
        if (name != null) {
            if (heal_amount != null) {
                if (ingredients != null) {
                    if (colour != null) {
                        int h_amount = 0;
                        try {
                            h_amount = Integer.parseInt(heal_amount);
                        }
                        catch (NumberFormatException e) {
                            if (ConfigurationHandler.api_debug) {
                                RecipeUtil.printReport(parser, num, "heal", "Could not parse the value '" + heal_amount + "' to an integer");
                            }
                            return;
                        }
                        ItemStack[] in = RecipeRegistry.parseIngredients(ingredients, num, parser);
                        if (in == null) {
                            return;
                        }
                        int[] rgb = RecipeRegistry.parseColour(colour, num, parser);
                        if (rgb == null) {
                            return;
                        }
                        name = RecipeRegistry.parseFormatting(RecipeRegistry.parseSpaces(name));
                        RecipeRegistry.getInstance().registerBlenderRecipe(name, h_amount, in, rgb);
                    } else if (ConfigurationHandler.api_debug) {
                        RecipeUtil.printMissing(parser, num, "colour", "A colour is required");
                    }
                } else if (ConfigurationHandler.api_debug) {
                    RecipeUtil.printMissing(parser, num, "ingredients", "Ingredients are required");
                }
            } else if (ConfigurationHandler.api_debug) {
                RecipeUtil.printMissing(parser, num, "heal", "A heal amount is required");
            }
        } else if (ConfigurationHandler.api_debug) {
            RecipeUtil.printMissing(parser, num, "name", "A name is required");
        }
    }

    public static ItemStack[] parseIngredients(String ingredients, int num, Parser parser) {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        String[] ingredientData = ingredients.split("/");
        if (ingredientData.length == 0) {
            return null;
        }
        int length = ingredientData.length > 4 ? 4 : ingredientData.length;
        for (int i = 0; i < length; ++i) {
            String[] itemData = ingredientData[i].split(":");
            String itemName = itemData[0] + ":" + itemData[1];
            String itemAmount = "1";
            String itemMetadata = "0";
            if (itemData.length > 2) {
                itemAmount = itemData[2];
                if (itemData.length > 3) {
                    itemMetadata = itemData[3];
                }
            }
            Item item = (Item)Item.itemRegistry.getObject(itemName);
            int i_amount = 1;
            try {
                i_amount = Integer.parseInt(itemAmount);
            }
            catch (NumberFormatException e) {
                if (ConfigurationHandler.api_debug) {
                    RecipeUtil.printReport(parser, num, "ingredients", "Could not parse the value '" + itemAmount + "' to an integer for ingredient number " + i);
                }
                return null;
            }
            int i_metadata = 0;
            try {
                i_metadata = Integer.parseInt(itemMetadata);
            }
            catch (NumberFormatException e) {
                if (ConfigurationHandler.api_debug) {
                    RecipeUtil.printReport(parser, num, "ingredients", "Could not parse the value '" + itemMetadata + "' to an integer for ingredient number " + i);
                }
                return null;
            }
            list.add(new ItemStack(item, i_amount, i_metadata));
        }
        return list.toArray(new ItemStack[0]);
    }

    public static int[] parseColour(String colour, int num, Parser parser) {
        String[] rgb = colour.split(":");
        if (rgb.length == 3) {
            String r = rgb[0];
            String g = rgb[1];
            String b = rgb[2];
            int red = 0;
            try {
                red = Integer.parseInt(r);
            }
            catch (NumberFormatException e) {
                if (ConfigurationHandler.api_debug) {
                    RecipeUtil.printReport(parser, num, "colour", "Could not parse the value '" + r + "' to an integer");
                }
                return null;
            }
            int green = 0;
            try {
                green = Integer.parseInt(g);
            }
            catch (NumberFormatException e) {
                if (ConfigurationHandler.api_debug) {
                    RecipeUtil.printReport(parser, num, "colour", "Could not parse the value '" + g + "' to an integer");
                }
                return null;
            }
            int blue = 0;
            try {
                blue = Integer.parseInt(b);
            }
            catch (NumberFormatException e) {
                if (ConfigurationHandler.api_debug) {
                    RecipeUtil.printReport(parser, num, "colour", "Could not parse the value '" + b + "' to an integer");
                }
                return null;
            }
            return new int[]{red, green, blue};
        }
        if (ConfigurationHandler.api_debug) {
            RecipeUtil.printReport(parser, num, "colour", "The colour variable doesn't have all three rgb values set, it needs to be colour=r-g-b");
        }
        return null;
    }

    public static String parseSpaces(String name) {
        return name.replaceAll("_", " ");
    }

    public static String parseFormatting(String name) {
        name = name.replaceAll("&0", "\u00a7" + EnumChatFormatting.BLACK.getFormattingCode());
        name = name.replaceAll("&1", "\u00a7" + EnumChatFormatting.DARK_BLUE.getFormattingCode());
        name = name.replaceAll("&2", "\u00a7" + EnumChatFormatting.DARK_GREEN.getFormattingCode());
        name = name.replaceAll("&3", "\u00a7" + EnumChatFormatting.DARK_AQUA.getFormattingCode());
        name = name.replaceAll("&4", "\u00a7" + EnumChatFormatting.DARK_RED.getFormattingCode());
        name = name.replaceAll("&5", "\u00a7" + EnumChatFormatting.DARK_PURPLE.getFormattingCode());
        name = name.replaceAll("&6", "\u00a7" + EnumChatFormatting.GOLD.getFormattingCode());
        name = name.replaceAll("&7", "\u00a7" + EnumChatFormatting.GRAY.getFormattingCode());
        name = name.replaceAll("&8", "\u00a7" + EnumChatFormatting.DARK_GRAY.getFormattingCode());
        name = name.replaceAll("&9", "\u00a7" + EnumChatFormatting.BLUE.getFormattingCode());
        name = name.replaceAll("&a", "\u00a7" + EnumChatFormatting.GREEN.getFormattingCode());
        name = name.replaceAll("&b", "\u00a7" + EnumChatFormatting.AQUA.getFormattingCode());
        name = name.replaceAll("&c", "\u00a7" + EnumChatFormatting.RED.getFormattingCode());
        name = name.replaceAll("&d", "\u00a7" + EnumChatFormatting.LIGHT_PURPLE.getFormattingCode());
        name = name.replaceAll("&e", "\u00a7" + EnumChatFormatting.YELLOW.getFormattingCode());
        name = name.replaceAll("&f", "\u00a7" + EnumChatFormatting.WHITE.getFormattingCode());
        name = name.replaceAll("&k", "\u00a7" + EnumChatFormatting.OBFUSCATED.getFormattingCode());
        name = name.replaceAll("&l", "\u00a7" + EnumChatFormatting.BOLD.getFormattingCode());
        name = name.replaceAll("&m", "\u00a7" + EnumChatFormatting.STRIKETHROUGH.getFormattingCode());
        name = name.replaceAll("&n", "\u00a7" + EnumChatFormatting.UNDERLINE.getFormattingCode());
        name = name.replaceAll("&o", "\u00a7" + EnumChatFormatting.ITALIC.getFormattingCode());
        name = name.replaceAll("&r", "\u00a7" + EnumChatFormatting.RESET.getFormattingCode());
        return name;
    }

    public static void registerMicrowaveRecipe(Parser parser, int num) {
        String input_item = parser.getValue("input-item", null);
        String input_metadata = parser.getValue("input-metadata", "0");
        String output_item = parser.getValue("output-item", null);
        String output_metadata = parser.getValue("output-metadata", "0");
        if (input_item != null) {
            if (output_item != null) {
                Item input = (Item)Item.itemRegistry.getObject(input_item);
                Item output = (Item)Item.itemRegistry.getObject(output_item);
                if (input != null) {
                    if (output != null) {
                        int i_metadata = 0;
                        try {
                            i_metadata = Integer.parseInt(input_metadata);
                        }
                        catch (NumberFormatException e) {
                            if (ConfigurationHandler.api_debug) {
                                RecipeUtil.printReport(parser, num, "input-metadata", "Could not parse the value '" + input_metadata + "' to an integer");
                            }
                            return;
                        }
                        int o_metadata = 0;
                        try {
                            o_metadata = Integer.parseInt(output_metadata);
                        }
                        catch (NumberFormatException e) {
                            if (ConfigurationHandler.api_debug) {
                                RecipeUtil.printReport(parser, num, "output-metadata", "Could not parse the value '" + output_metadata + "' to an integer");
                            }
                            return;
                        }
                        RecipeRegistry.getInstance().registerMicrowaveRecipe(new ItemStack(input, 1, i_metadata), new ItemStack(output, 1, o_metadata));
                    } else if (ConfigurationHandler.api_debug) {
                        RecipeUtil.printReport(parser, num, "output-item", "The output-item '" + output_item + "' does not exist");
                    }
                } else if (ConfigurationHandler.api_debug) {
                    RecipeUtil.printReport(parser, num, "input-item", "The input-item '" + input_item + "' does not exist");
                }
            } else if (ConfigurationHandler.api_debug) {
                RecipeUtil.printMissing(parser, num, "output-item", "An output-item is required");
            }
        } else if (ConfigurationHandler.api_debug) {
            RecipeUtil.printMissing(parser, num, "input-item", "An input-item is required");
        }
    }

    public static void registerWashingMachineRecipe(Parser parser, int num) {
        String input_item = parser.getValue("input-item", null);
        if (input_item != null) {
            Item input = (Item)Item.itemRegistry.getObject(input_item);
            if (input != null) {
                RecipeRegistry.getInstance().registerWashingMachineRecipe(new ItemStack(input));
            } else if (ConfigurationHandler.api_debug) {
                RecipeUtil.printReport(parser, num, "input-item", "The input-item '" + input_item + "' does not exist");
            }
        } else if (ConfigurationHandler.api_debug) {
            RecipeUtil.printMissing(parser, num, "input-item", "An input-item is required");
        }
    }

    public static void registerDishwasherRecipe(Parser parser, int num) {
        String input_item = parser.getValue("input-item", null);
        if (input_item != null) {
            Item input = (Item)Item.itemRegistry.getObject(input_item);
            if (input != null) {
                RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(input));
            } else if (ConfigurationHandler.api_debug) {
                RecipeUtil.printReport(parser, num, "input-item", "The input-item '" + input_item + "' does not exist");
            }
        } else if (ConfigurationHandler.api_debug) {
            RecipeUtil.printMissing(parser, num, "input-item", "An input-item is required");
        }
    }

    public static void registerConfigRecipes() {
        if (ConfigurationHandler.items.length > 0 && ConfigurationHandler.api_debug) {
            System.out.println("RecipeAPI (Configuration): Registering " + ConfigurationHandler.items.length + " recipes from the config.");
        }
        Parser parser = Parser.getInstance();
        for (int i = 0; i < ConfigurationHandler.items.length; ++i) {
            parser.parseLine(ConfigurationHandler.items[i], true);
            String type = parser.getValue("type", null);
            int realNum = i + 1;
            if (type == null) continue;
            if (type.equalsIgnoreCase("printer")) {
                RecipeRegistry.registerPrinterRecipe(parser, realNum);
                continue;
            }
            if (type.equalsIgnoreCase("oven")) {
                RecipeRegistry.registerOvenRecipe(parser, realNum);
                continue;
            }
            if (type.equalsIgnoreCase("freezer")) {
                RecipeRegistry.registerFreezerRecipe(parser, realNum);
                continue;
            }
            if (type.equalsIgnoreCase("minebay")) {
                RecipeRegistry.registerMineBayRecipe(parser, realNum);
                continue;
            }
            if (type.equalsIgnoreCase("choppingboard")) {
                RecipeRegistry.registerChoppingBoardRecipe(parser, realNum);
                continue;
            }
            if (type.equalsIgnoreCase("toaster")) {
                RecipeRegistry.registerToasterRecipe(parser, realNum);
                continue;
            }
            if (type.equalsIgnoreCase("microwave")) {
                RecipeRegistry.registerMicrowaveRecipe(parser, realNum);
                continue;
            }
            if (type.equalsIgnoreCase("blender")) {
                RecipeRegistry.registerBlenderRecipe(parser, realNum);
                continue;
            }
            if (type.equalsIgnoreCase("washingmachine")) {
                RecipeRegistry.registerWashingMachineRecipe(parser, realNum);
                continue;
            }
            if (type.equalsIgnoreCase("dishwasher")) {
                RecipeRegistry.registerDishwasherRecipe(parser, realNum);
                continue;
            }
            if (!ConfigurationHandler.api_debug) continue;
            RecipeUtil.printReport(parser, realNum, "type", "The recipe type '" + type + "' does not exist");
        }
    }

    public static void registerDefaultRecipes() {
        NBTTagCompound master;
        ItemStack stack;
        if (ConfigurationHandler.printer_1) {
            RecipeRegistry.getInstance().registerPrinterRecipe(new ItemStack((Item)Items.enchanted_book));
        }
        if (ConfigurationHandler.printer_2) {
            RecipeRegistry.getInstance().registerPrinterRecipe(new ItemStack(Items.written_book));
        }
        if (ConfigurationHandler.oven_1) {
            RecipeRegistry.getInstance().registerOvenRecipe(new ItemStack(Items.beef), new ItemStack(Items.cooked_beef));
        }
        if (ConfigurationHandler.oven_2) {
            RecipeRegistry.getInstance().registerOvenRecipe(new ItemStack(Items.porkchop), new ItemStack(Items.cooked_porkchop));
        }
        if (ConfigurationHandler.oven_3) {
            RecipeRegistry.getInstance().registerOvenRecipe(new ItemStack(Items.potato), new ItemStack(Items.baked_potato));
        }
        if (ConfigurationHandler.oven_4) {
            RecipeRegistry.getInstance().registerOvenRecipe(new ItemStack(Items.chicken), new ItemStack(Items.cooked_chicken));
        }
        if (ConfigurationHandler.oven_5) {
            RecipeRegistry.getInstance().registerOvenRecipe(new ItemStack(Items.fish, 1, 0), new ItemStack(Items.cooked_fished, 1, 0));
        }
        if (ConfigurationHandler.oven_6) {
            RecipeRegistry.getInstance().registerOvenRecipe(new ItemStack(Items.fish, 1, 1), new ItemStack(Items.cooked_fished, 1, 1));
        }
        if (ConfigurationHandler.oven_7) {
            RecipeRegistry.getInstance().registerOvenRecipe(new ItemStack(MrCrayfishFurnitureMod.itemFlesh), new ItemStack(MrCrayfishFurnitureMod.itemCookedFlesh));
        }
        if (ConfigurationHandler.frez_1) {
            RecipeRegistry.getInstance().registerFreezerRecipe(new ItemStack(Items.water_bucket), new ItemStack(Blocks.ice));
        }
        if (ConfigurationHandler.frez_2) {
            RecipeRegistry.getInstance().registerFreezerRecipe(new ItemStack(Blocks.ice), new ItemStack(Blocks.packed_ice));
        }
        if (ConfigurationHandler.frez_3) {
            RecipeRegistry.getInstance().registerFreezerRecipe(new ItemStack(Items.lava_bucket), new ItemStack(Blocks.obsidian));
        }
        if (ConfigurationHandler.frez_4) {
            RecipeRegistry.getInstance().registerFreezerRecipe(new ItemStack(Items.slime_ball), new ItemStack(Items.snowball));
        }
        if (ConfigurationHandler.frez_5) {
            RecipeRegistry.getInstance().registerFreezerRecipe(new ItemStack(Items.poisonous_potato), new ItemStack(Items.potato));
        }
        if (ConfigurationHandler.frez_6) {
            RecipeRegistry.getInstance().registerFreezerRecipe(new ItemStack(Items.rotten_flesh), new ItemStack(MrCrayfishFurnitureMod.itemFlesh));
        }
        if (ConfigurationHandler.mine_1) {
            RecipeRegistry.getInstance().registerMineBayItem(new ItemStack(Blocks.hardened_clay, 16), new ItemStack(Items.emerald), 1);
        }
        if (ConfigurationHandler.mine_2) {
            RecipeRegistry.getInstance().registerMineBayItem(new ItemStack(Items.skull), new ItemStack(Items.emerald), 8);
        }
        if (ConfigurationHandler.mine_3) {
            RecipeRegistry.getInstance().registerMineBayItem(new ItemStack(Items.saddle), new ItemStack(Items.emerald), 4);
        }
        if (ConfigurationHandler.mine_4) {
            RecipeRegistry.getInstance().registerMineBayItem(new ItemStack(Items.spawn_egg, 1, 100), new ItemStack(Items.emerald), 8);
        }
        if (ConfigurationHandler.mine_5) {
            RecipeRegistry.getInstance().registerMineBayItem(new ItemStack(Items.diamond_horse_armor), new ItemStack(Items.diamond), 8);
        }
        if (ConfigurationHandler.mine_6) {
            RecipeRegistry.getInstance().registerMineBayItem(new ItemStack(Items.experience_bottle), new ItemStack(Items.iron_ingot), 1);
        }
        if (ConfigurationHandler.mine_7) {
            stack = new ItemStack(Items.fireworks, 4);
            master = new NBTTagCompound();
            NBTTagCompound firework = new NBTTagCompound();
            firework.setByte("Flight", (byte)1);
            NBTTagList list = new NBTTagList();
            NBTTagCompound data = new NBTTagCompound();
            data.setByte("Trail", (byte)1);
            data.setByte("Type", (byte)0);
            data.setIntArray("Colors", new int[]{11743532, 3887386});
            list.appendTag((NBTBase)data);
            firework.setTag("Explosions", (NBTBase)list);
            master.setTag("Fireworks", (NBTBase)firework);
            stack.setTagCompound(master);
            stack.setStackDisplayName(EnumChatFormatting.RED + "Christmas" + EnumChatFormatting.GREEN + " Firework");
            RecipeRegistry.getInstance().registerMineBayItem(stack, new ItemStack(Items.iron_ingot), 1);
        }
        if (ConfigurationHandler.mine_8) {
            stack = new ItemStack((Item)Items.enchanted_book);
            master = new NBTTagCompound();
            NBTTagList list = new NBTTagList();
            NBTTagCompound data = new NBTTagCompound();
            data.setShort("id", (short)33);
            data.setShort("lvl", (short)1);
            list.appendTag((NBTBase)data);
            master.setTag("StoredEnchantments", (NBTBase)list);
            stack.setTagCompound(master);
            RecipeRegistry.getInstance().registerMineBayItem(stack, new ItemStack(Items.emerald), 8);
        }
        if (ConfigurationHandler.mine_9) {
            RecipeRegistry.getInstance().registerMineBayItem(new ItemStack((Item)Items.potionitem, 2, 8270), new ItemStack(Items.emerald), 1);
        }
        if (ConfigurationHandler.mine_10) {
            RecipeRegistry.getInstance().registerMineBayItem(new ItemStack(MrCrayfishFurnitureMod.itemRecipeBook), new ItemStack(Items.emerald), 1);
        }
        if (ConfigurationHandler.blen_1) {
            RecipeRegistry.getInstance().registerBlenderRecipe("Fruit Crush", 4, new ItemStack[]{new ItemStack(Items.apple, 2), new ItemStack(Items.melon, 4)}, new int[]{255, 58, 37});
        }
        if (ConfigurationHandler.blen_2) {
            RecipeRegistry.getInstance().registerBlenderRecipe("Veggie Juice", 6, new ItemStack[]{new ItemStack(Items.carrot, 4), new ItemStack(Items.potato, 1), new ItemStack(Items.pumpkin_pie, 2)}, new int[]{247, 139, 122});
        }
        if (ConfigurationHandler.blen_3) {
            RecipeRegistry.getInstance().registerBlenderRecipe("Cookies and Cream Milkshake", 4, new ItemStack[]{new ItemStack(Items.cookie, 2), new ItemStack(Items.milk_bucket)}, new int[]{255, 214, 164});
        }
        if (ConfigurationHandler.blen_4) {
            RecipeRegistry.getInstance().registerBlenderRecipe("Energy Drink", 8, new ItemStack[]{new ItemStack(Items.redstone, 8), new ItemStack(Items.fire_charge, 1), new ItemStack(Items.sugar, 16)}, new int[]{92, 23, 8});
        }
        if (ConfigurationHandler.chop_1) {
            RecipeRegistry.getInstance().registerChoppingBoardRecipe(new ItemStack(Items.bread), new ItemStack(MrCrayfishFurnitureMod.itemBreadSlice, 6));
        }
        if (ConfigurationHandler.dish_1) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack((Item)Items.bow));
        }
        if (ConfigurationHandler.dish_2) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(Items.wooden_pickaxe));
        }
        if (ConfigurationHandler.dish_3) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(Items.wooden_axe));
        }
        if (ConfigurationHandler.dish_4) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(Items.wooden_shovel));
        }
        if (ConfigurationHandler.dish_5) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(Items.wooden_hoe));
        }
        if (ConfigurationHandler.dish_6) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(Items.wooden_sword));
        }
        if (ConfigurationHandler.dish_7) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(Items.stone_pickaxe));
        }
        if (ConfigurationHandler.dish_8) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(Items.stone_axe));
        }
        if (ConfigurationHandler.dish_9) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(Items.stone_shovel));
        }
        if (ConfigurationHandler.dish_10) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(Items.stone_hoe));
        }
        if (ConfigurationHandler.dish_11) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(Items.stone_sword));
        }
        if (ConfigurationHandler.dish_12) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(Items.iron_pickaxe));
        }
        if (ConfigurationHandler.dish_13) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(Items.iron_axe));
        }
        if (ConfigurationHandler.dish_14) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(Items.iron_shovel));
        }
        if (ConfigurationHandler.dish_15) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(Items.iron_hoe));
        }
        if (ConfigurationHandler.dish_16) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(Items.iron_sword));
        }
        if (ConfigurationHandler.dish_17) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(Items.golden_pickaxe));
        }
        if (ConfigurationHandler.dish_18) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(Items.golden_axe));
        }
        if (ConfigurationHandler.dish_19) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(Items.golden_shovel));
        }
        if (ConfigurationHandler.dish_20) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(Items.golden_hoe));
        }
        if (ConfigurationHandler.dish_21) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(Items.golden_sword));
        }
        if (ConfigurationHandler.dish_22) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(Items.diamond_pickaxe));
        }
        if (ConfigurationHandler.dish_23) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(Items.diamond_axe));
        }
        if (ConfigurationHandler.dish_24) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(Items.diamond_shovel));
        }
        if (ConfigurationHandler.dish_25) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(Items.diamond_hoe));
        }
        if (ConfigurationHandler.dish_26) {
            RecipeRegistry.getInstance().registerDishwasherRecipe(new ItemStack(Items.diamond_sword));
        }
        if (ConfigurationHandler.micr_1) {
            RecipeRegistry.getInstance().registerMicrowaveRecipe(new ItemStack(Items.beef), new ItemStack(Items.cooked_beef));
        }
        if (ConfigurationHandler.micr_2) {
            RecipeRegistry.getInstance().registerMicrowaveRecipe(new ItemStack(Items.potato), new ItemStack(Items.baked_potato));
        }
        if (ConfigurationHandler.toast_1) {
            RecipeRegistry.getInstance().registerToasterRecipe(new ItemStack(MrCrayfishFurnitureMod.itemBreadSlice), new ItemStack(MrCrayfishFurnitureMod.itemToast));
        }
        if (ConfigurationHandler.wash_1) {
            RecipeRegistry.getInstance().registerWashingMachineRecipe(new ItemStack((Item)Items.leather_helmet));
        }
        if (ConfigurationHandler.wash_2) {
            RecipeRegistry.getInstance().registerWashingMachineRecipe(new ItemStack((Item)Items.leather_chestplate));
        }
        if (ConfigurationHandler.wash_3) {
            RecipeRegistry.getInstance().registerWashingMachineRecipe(new ItemStack((Item)Items.leather_leggings));
        }
        if (ConfigurationHandler.wash_4) {
            RecipeRegistry.getInstance().registerWashingMachineRecipe(new ItemStack((Item)Items.leather_boots));
        }
        if (ConfigurationHandler.wash_5) {
            RecipeRegistry.getInstance().registerWashingMachineRecipe(new ItemStack((Item)Items.chainmail_helmet));
        }
        if (ConfigurationHandler.wash_6) {
            RecipeRegistry.getInstance().registerWashingMachineRecipe(new ItemStack((Item)Items.chainmail_chestplate));
        }
        if (ConfigurationHandler.wash_7) {
            RecipeRegistry.getInstance().registerWashingMachineRecipe(new ItemStack((Item)Items.chainmail_leggings));
        }
        if (ConfigurationHandler.wash_8) {
            RecipeRegistry.getInstance().registerWashingMachineRecipe(new ItemStack((Item)Items.chainmail_boots));
        }
        if (ConfigurationHandler.wash_9) {
            RecipeRegistry.getInstance().registerWashingMachineRecipe(new ItemStack((Item)Items.iron_helmet));
        }
        if (ConfigurationHandler.wash_10) {
            RecipeRegistry.getInstance().registerWashingMachineRecipe(new ItemStack((Item)Items.iron_chestplate));
        }
        if (ConfigurationHandler.wash_11) {
            RecipeRegistry.getInstance().registerWashingMachineRecipe(new ItemStack((Item)Items.iron_leggings));
        }
        if (ConfigurationHandler.wash_12) {
            RecipeRegistry.getInstance().registerWashingMachineRecipe(new ItemStack((Item)Items.iron_boots));
        }
        if (ConfigurationHandler.wash_13) {
            RecipeRegistry.getInstance().registerWashingMachineRecipe(new ItemStack((Item)Items.golden_helmet));
        }
        if (ConfigurationHandler.wash_14) {
            RecipeRegistry.getInstance().registerWashingMachineRecipe(new ItemStack((Item)Items.golden_chestplate));
        }
        if (ConfigurationHandler.wash_15) {
            RecipeRegistry.getInstance().registerWashingMachineRecipe(new ItemStack((Item)Items.golden_leggings));
        }
        if (ConfigurationHandler.wash_16) {
            RecipeRegistry.getInstance().registerWashingMachineRecipe(new ItemStack((Item)Items.golden_boots));
        }
        if (ConfigurationHandler.wash_17) {
            RecipeRegistry.getInstance().registerWashingMachineRecipe(new ItemStack((Item)Items.diamond_helmet));
        }
        if (ConfigurationHandler.wash_18) {
            RecipeRegistry.getInstance().registerWashingMachineRecipe(new ItemStack((Item)Items.diamond_chestplate));
        }
        if (ConfigurationHandler.wash_19) {
            RecipeRegistry.getInstance().registerWashingMachineRecipe(new ItemStack((Item)Items.diamond_leggings));
        }
        if (ConfigurationHandler.wash_20) {
            RecipeRegistry.getInstance().registerWashingMachineRecipe(new ItemStack((Item)Items.diamond_boots));
        }
    }
}

