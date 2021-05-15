package com.mrcrayfish.furniture.datagen;

import com.mrcrayfish.furniture.Reference;
import com.mrcrayfish.furniture.common.ModTags;
import com.mrcrayfish.furniture.core.ModBlocks;
import com.mrcrayfish.furniture.core.ModItems;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

/**
 * @author Ocelot
 */
public class ItemTagGen extends ItemTagsProvider
{
    public ItemTagGen(DataGenerator generator, BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper)
    {
        super(generator, blockTagsProvider, Reference.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerTags()
    {
        this.getOrCreateBuilder(ModTags.Items.BEDROOM)
                // Cabinets
                .add(ModBlocks.BEDSIDE_CABINET_OAK.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_SPRUCE.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_BIRCH.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_JUNGLE.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_ACACIA.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_DARK_OAK.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_STONE.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_GRANITE.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_DIORITE.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_ANDESITE.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_STRIPPED_OAK.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_STRIPPED_SPRUCE.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_STRIPPED_BIRCH.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_STRIPPED_JUNGLE.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_STRIPPED_ACACIA.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_STRIPPED_DARK_OAK.asItem())
                // Desks
                .add(ModBlocks.DESK_OAK.asItem())
                .add(ModBlocks.DESK_SPRUCE.asItem())
                .add(ModBlocks.DESK_BIRCH.asItem())
                .add(ModBlocks.DESK_JUNGLE.asItem())
                .add(ModBlocks.DESK_ACACIA.asItem())
                .add(ModBlocks.DESK_DARK_OAK.asItem())
                .add(ModBlocks.DESK_STONE.asItem())
                .add(ModBlocks.DESK_GRANITE.asItem())
                .add(ModBlocks.DESK_DIORITE.asItem())
                .add(ModBlocks.DESK_ANDESITE.asItem())
                .add(ModBlocks.DESK_STRIPPED_OAK.asItem())
                .add(ModBlocks.DESK_STRIPPED_SPRUCE.asItem())
                .add(ModBlocks.DESK_STRIPPED_BIRCH.asItem())
                .add(ModBlocks.DESK_STRIPPED_JUNGLE.asItem())
                .add(ModBlocks.DESK_STRIPPED_ACACIA.asItem())
                .add(ModBlocks.DESK_STRIPPED_DARK_OAK.asItem())
                // Desk Cabinets
                .add(ModBlocks.DESK_CABINET_OAK.asItem())
                .add(ModBlocks.DESK_CABINET_SPRUCE.asItem())
                .add(ModBlocks.DESK_CABINET_BIRCH.asItem())
                .add(ModBlocks.DESK_CABINET_JUNGLE.asItem())
                .add(ModBlocks.DESK_CABINET_ACACIA.asItem())
                .add(ModBlocks.DESK_CABINET_DARK_OAK.asItem())
                .add(ModBlocks.DESK_CABINET_STONE.asItem())
                .add(ModBlocks.DESK_CABINET_GRANITE.asItem())
                .add(ModBlocks.DESK_CABINET_DIORITE.asItem())
                .add(ModBlocks.DESK_CABINET_ANDESITE.asItem())
                .add(ModBlocks.DESK_CABINET_STRIPPED_OAK.asItem())
                .add(ModBlocks.DESK_CABINET_STRIPPED_SPRUCE.asItem())
                .add(ModBlocks.DESK_CABINET_STRIPPED_BIRCH.asItem())
                .add(ModBlocks.DESK_CABINET_STRIPPED_JUNGLE.asItem())
                .add(ModBlocks.DESK_CABINET_STRIPPED_ACACIA.asItem())
                .add(ModBlocks.DESK_CABINET_STRIPPED_DARK_OAK.asItem())
                // Blinds
                .add(ModBlocks.BLINDS_OAK.asItem())
                .add(ModBlocks.BLINDS_SPRUCE.asItem())
                .add(ModBlocks.BLINDS_BIRCH.asItem())
                .add(ModBlocks.BLINDS_JUNGLE.asItem())
                .add(ModBlocks.BLINDS_ACACIA.asItem())
                .add(ModBlocks.BLINDS_DARK_OAK.asItem())
                .add(ModBlocks.BLINDS_STRIPPED_OAK.asItem())
                .add(ModBlocks.BLINDS_STRIPPED_SPRUCE.asItem())
                .add(ModBlocks.BLINDS_STRIPPED_BIRCH.asItem())
                .add(ModBlocks.BLINDS_STRIPPED_JUNGLE.asItem())
                .add(ModBlocks.BLINDS_STRIPPED_ACACIA.asItem())
                .add(ModBlocks.BLINDS_STRIPPED_DARK_OAK.asItem());

        this.getOrCreateBuilder(ModTags.Items.GENERAL)
                // Tables
                .add(ModBlocks.TABLE_OAK.asItem())
                .add(ModBlocks.TABLE_SPRUCE.asItem())
                .add(ModBlocks.TABLE_BIRCH.asItem())
                .add(ModBlocks.TABLE_JUNGLE.asItem())
                .add(ModBlocks.TABLE_ACACIA.asItem())
                .add(ModBlocks.TABLE_DARK_OAK.asItem())
                .add(ModBlocks.TABLE_STONE.asItem())
                .add(ModBlocks.TABLE_GRANITE.asItem())
                .add(ModBlocks.TABLE_DIORITE.asItem())
                .add(ModBlocks.TABLE_ANDESITE.asItem())
                .add(ModBlocks.TABLE_STRIPPED_OAK.asItem())
                .add(ModBlocks.TABLE_STRIPPED_SPRUCE.asItem())
                .add(ModBlocks.TABLE_STRIPPED_BIRCH.asItem())
                .add(ModBlocks.TABLE_STRIPPED_JUNGLE.asItem())
                .add(ModBlocks.TABLE_STRIPPED_ACACIA.asItem())
                .add(ModBlocks.TABLE_STRIPPED_DARK_OAK.asItem())
                // Chairs
                .add(ModBlocks.CHAIR_OAK.asItem())
                .add(ModBlocks.CHAIR_SPRUCE.asItem())
                .add(ModBlocks.CHAIR_BIRCH.asItem())
                .add(ModBlocks.CHAIR_JUNGLE.asItem())
                .add(ModBlocks.CHAIR_ACACIA.asItem())
                .add(ModBlocks.CHAIR_DARK_OAK.asItem())
                .add(ModBlocks.CHAIR_STONE.asItem())
                .add(ModBlocks.CHAIR_GRANITE.asItem())
                .add(ModBlocks.CHAIR_DIORITE.asItem())
                .add(ModBlocks.CHAIR_ANDESITE.asItem())
                .add(ModBlocks.CHAIR_STRIPPED_OAK.asItem())
                .add(ModBlocks.CHAIR_STRIPPED_SPRUCE.asItem())
                .add(ModBlocks.CHAIR_STRIPPED_BIRCH.asItem())
                .add(ModBlocks.CHAIR_STRIPPED_JUNGLE.asItem())
                .add(ModBlocks.CHAIR_STRIPPED_ACACIA.asItem())
                .add(ModBlocks.CHAIR_STRIPPED_DARK_OAK.asItem())
                // Coffee Table
                .add(ModBlocks.COFFEE_TABLE_OAK.asItem())
                .add(ModBlocks.COFFEE_TABLE_SPRUCE.asItem())
                .add(ModBlocks.COFFEE_TABLE_BIRCH.asItem())
                .add(ModBlocks.COFFEE_TABLE_JUNGLE.asItem())
                .add(ModBlocks.COFFEE_TABLE_ACACIA.asItem())
                .add(ModBlocks.COFFEE_TABLE_DARK_OAK.asItem())
                .add(ModBlocks.COFFEE_TABLE_STONE.asItem())
                .add(ModBlocks.COFFEE_TABLE_GRANITE.asItem())
                .add(ModBlocks.COFFEE_TABLE_DIORITE.asItem())
                .add(ModBlocks.COFFEE_TABLE_ANDESITE.asItem())
                .add(ModBlocks.COFFEE_TABLE_STRIPPED_OAK.asItem())
                .add(ModBlocks.COFFEE_TABLE_STRIPPED_SPRUCE.asItem())
                .add(ModBlocks.COFFEE_TABLE_STRIPPED_BIRCH.asItem())
                .add(ModBlocks.COFFEE_TABLE_STRIPPED_JUNGLE.asItem())
                .add(ModBlocks.COFFEE_TABLE_STRIPPED_ACACIA.asItem())
                .add(ModBlocks.COFFEE_TABLE_STRIPPED_DARK_OAK.asItem())
                // Sofa
                .add(ModBlocks.SOFA_WHITE.asItem())
                .add(ModBlocks.SOFA_ORANGE.asItem())
                .add(ModBlocks.SOFA_MAGENTA.asItem())
                .add(ModBlocks.SOFA_LIGHT_BLUE.asItem())
                .add(ModBlocks.SOFA_YELLOW.asItem())
                .add(ModBlocks.SOFA_LIME.asItem())
                .add(ModBlocks.SOFA_PINK.asItem())
                .add(ModBlocks.SOFA_GRAY.asItem())
                .add(ModBlocks.SOFA_LIGHT_GRAY.asItem())
                .add(ModBlocks.SOFA_CYAN.asItem())
                .add(ModBlocks.SOFA_PURPLE.asItem())
                .add(ModBlocks.SOFA_BLUE.asItem())
                .add(ModBlocks.SOFA_BROWN.asItem())
                .add(ModBlocks.SOFA_GREEN.asItem())
                .add(ModBlocks.SOFA_RED.asItem())
                .add(ModBlocks.SOFA_BLACK.asItem());

        this.getOrCreateBuilder(ModTags.Items.KITCHEN)
                // Kitchen Counters
                .add(ModBlocks.KITCHEN_COUNTER_OAK.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_SPRUCE.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_BIRCH.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_JUNGLE.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_ACACIA.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_DARK_OAK.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_STRIPPED_OAK.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_STRIPPED_SPRUCE.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_STRIPPED_BIRCH.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_STRIPPED_JUNGLE.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_STRIPPED_ACACIA.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_STRIPPED_DARK_OAK.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_WHITE.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_ORANGE.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_MAGENTA.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_LIGHT_BLUE.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_YELLOW.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_LIME.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_PINK.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_GRAY.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_LIGHT_GRAY.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_CYAN.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_PURPLE.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_BLUE.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_BROWN.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_GREEN.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_RED.asItem())
                .add(ModBlocks.KITCHEN_COUNTER_BLACK.asItem())
                // Kitchen Drawers
                .add(ModBlocks.KITCHEN_DRAWER_OAK.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_SPRUCE.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_BIRCH.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_JUNGLE.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_ACACIA.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_DARK_OAK.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_STRIPPED_OAK.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_STRIPPED_SPRUCE.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_STRIPPED_BIRCH.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_STRIPPED_JUNGLE.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_STRIPPED_ACACIA.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_STRIPPED_DARK_OAK.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_WHITE.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_ORANGE.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_MAGENTA.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_LIGHT_BLUE.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_YELLOW.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_LIME.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_PINK.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_GRAY.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_LIGHT_GRAY.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_CYAN.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_PURPLE.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_BLUE.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_BROWN.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_GREEN.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_RED.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_BLACK.asItem())
                // Spatula
                .add(ModItems.SPATULA)
                // Kitchen Sinks
                .add(ModBlocks.KITCHEN_SINK_LIGHT_OAK.asItem())
                .add(ModBlocks.KITCHEN_SINK_LIGHT_SPRUCE.asItem())
                .add(ModBlocks.KITCHEN_SINK_LIGHT_BIRCH.asItem())
                .add(ModBlocks.KITCHEN_SINK_LIGHT_JUNGLE.asItem())
                .add(ModBlocks.KITCHEN_SINK_LIGHT_ACACIA.asItem())
                .add(ModBlocks.KITCHEN_SINK_LIGHT_DARK_OAK.asItem())
                .add(ModBlocks.KITCHEN_SINK_LIGHT_STRIPPED_OAK.asItem())
                .add(ModBlocks.KITCHEN_SINK_LIGHT_STRIPPED_SPRUCE.asItem())
                .add(ModBlocks.KITCHEN_SINK_LIGHT_STRIPPED_BIRCH.asItem())
                .add(ModBlocks.KITCHEN_SINK_LIGHT_STRIPPED_JUNGLE.asItem())
                .add(ModBlocks.KITCHEN_SINK_LIGHT_STRIPPED_ACACIA.asItem())
                .add(ModBlocks.KITCHEN_SINK_LIGHT_STRIPPED_DARK_OAK.asItem())
                .add(ModBlocks.KITCHEN_SINK_DARK_OAK.asItem())
                .add(ModBlocks.KITCHEN_SINK_DARK_SPRUCE.asItem())
                .add(ModBlocks.KITCHEN_SINK_DARK_BIRCH.asItem())
                .add(ModBlocks.KITCHEN_SINK_DARK_JUNGLE.asItem())
                .add(ModBlocks.KITCHEN_SINK_DARK_ACACIA.asItem())
                .add(ModBlocks.KITCHEN_SINK_DARK_DARK_OAK.asItem())
                .add(ModBlocks.KITCHEN_SINK_DARK_STRIPPED_OAK.asItem())
                .add(ModBlocks.KITCHEN_SINK_DARK_STRIPPED_SPRUCE.asItem())
                .add(ModBlocks.KITCHEN_SINK_DARK_STRIPPED_BIRCH.asItem())
                .add(ModBlocks.KITCHEN_SINK_DARK_STRIPPED_JUNGLE.asItem())
                .add(ModBlocks.KITCHEN_SINK_DARK_STRIPPED_ACACIA.asItem())
                .add(ModBlocks.KITCHEN_SINK_DARK_STRIPPED_DARK_OAK.asItem())
                .add(ModBlocks.KITCHEN_SINK_WHITE.asItem())
                .add(ModBlocks.KITCHEN_SINK_ORANGE.asItem())
                .add(ModBlocks.KITCHEN_SINK_MAGENTA.asItem())
                .add(ModBlocks.KITCHEN_SINK_LIGHT_BLUE.asItem())
                .add(ModBlocks.KITCHEN_SINK_YELLOW.asItem())
                .add(ModBlocks.KITCHEN_SINK_LIME.asItem())
                .add(ModBlocks.KITCHEN_SINK_PINK.asItem())
                .add(ModBlocks.KITCHEN_SINK_GRAY.asItem())
                .add(ModBlocks.KITCHEN_SINK_LIGHT_GRAY.asItem())
                .add(ModBlocks.KITCHEN_SINK_CYAN.asItem())
                .add(ModBlocks.KITCHEN_SINK_PURPLE.asItem())
                .add(ModBlocks.KITCHEN_SINK_BLUE.asItem())
                .add(ModBlocks.KITCHEN_SINK_BROWN.asItem())
                .add(ModBlocks.KITCHEN_SINK_GREEN.asItem())
                .add(ModBlocks.KITCHEN_SINK_RED.asItem())
                .add(ModBlocks.KITCHEN_SINK_BLACK.asItem());

        this.getOrCreateBuilder(ModTags.Items.OUTDOORS)
                // Fences and Fence Gates
                .addTag(ModTags.Items.UPGRADED_FENCE_GATES)
                .addTag(ModTags.Items.UPGRADED_FENCES)
                // Park Benches
                .add(ModBlocks.PARK_BENCH_OAK.asItem())
                .add(ModBlocks.PARK_BENCH_SPRUCE.asItem())
                .add(ModBlocks.PARK_BENCH_BIRCH.asItem())
                .add(ModBlocks.PARK_BENCH_JUNGLE.asItem())
                .add(ModBlocks.PARK_BENCH_ACACIA.asItem())
                .add(ModBlocks.PARK_BENCH_DARK_OAK.asItem())
                .add(ModBlocks.PARK_BENCH_STRIPPED_OAK.asItem())
                .add(ModBlocks.PARK_BENCH_STRIPPED_SPRUCE.asItem())
                .add(ModBlocks.PARK_BENCH_STRIPPED_BIRCH.asItem())
                .add(ModBlocks.PARK_BENCH_STRIPPED_JUNGLE.asItem())
                .add(ModBlocks.PARK_BENCH_STRIPPED_ACACIA.asItem())
                .add(ModBlocks.PARK_BENCH_STRIPPED_DARK_OAK.asItem())
                // Post Box
                .add(ModBlocks.POST_BOX.asItem())
                // Mail Boxes
                .add(ModBlocks.MAIL_BOX_OAK.asItem())
                .add(ModBlocks.MAIL_BOX_SPRUCE.asItem())
                .add(ModBlocks.MAIL_BOX_BIRCH.asItem())
                .add(ModBlocks.MAIL_BOX_JUNGLE.asItem())
                .add(ModBlocks.MAIL_BOX_ACACIA.asItem())
                .add(ModBlocks.MAIL_BOX_DARK_OAK.asItem())
                .add(ModBlocks.MAIL_BOX_STRIPPED_OAK.asItem())
                .add(ModBlocks.MAIL_BOX_STRIPPED_SPRUCE.asItem())
                .add(ModBlocks.MAIL_BOX_STRIPPED_BIRCH.asItem())
                .add(ModBlocks.MAIL_BOX_STRIPPED_JUNGLE.asItem())
                .add(ModBlocks.MAIL_BOX_STRIPPED_ACACIA.asItem())
                .add(ModBlocks.MAIL_BOX_STRIPPED_DARK_OAK.asItem())
                // Hedge
                .addTag(ModTags.Items.HEDGES)
                // Rock Path
                .add(ModBlocks.ROCK_PATH.asItem())
                // Trampolines
                .add(ModBlocks.TRAMPOLINE_WHITE.asItem())
                .add(ModBlocks.TRAMPOLINE_ORANGE.asItem())
                .add(ModBlocks.TRAMPOLINE_MAGENTA.asItem())
                .add(ModBlocks.TRAMPOLINE_LIGHT_BLUE.asItem())
                .add(ModBlocks.TRAMPOLINE_YELLOW.asItem())
                .add(ModBlocks.TRAMPOLINE_LIME.asItem())
                .add(ModBlocks.TRAMPOLINE_PINK.asItem())
                .add(ModBlocks.TRAMPOLINE_GRAY.asItem())
                .add(ModBlocks.TRAMPOLINE_LIGHT_GRAY.asItem())
                .add(ModBlocks.TRAMPOLINE_CYAN.asItem())
                .add(ModBlocks.TRAMPOLINE_PURPLE.asItem())
                .add(ModBlocks.TRAMPOLINE_BLUE.asItem())
                .add(ModBlocks.TRAMPOLINE_BROWN.asItem())
                .add(ModBlocks.TRAMPOLINE_GREEN.asItem())
                .add(ModBlocks.TRAMPOLINE_RED.asItem())
                .add(ModBlocks.TRAMPOLINE_BLACK.asItem())
                // Coolers
                .add(ModBlocks.COOLER_WHITE.asItem())
                .add(ModBlocks.COOLER_ORANGE.asItem())
                .add(ModBlocks.COOLER_MAGENTA.asItem())
                .add(ModBlocks.COOLER_LIGHT_BLUE.asItem())
                .add(ModBlocks.COOLER_YELLOW.asItem())
                .add(ModBlocks.COOLER_LIME.asItem())
                .add(ModBlocks.COOLER_PINK.asItem())
                .add(ModBlocks.COOLER_GRAY.asItem())
                .add(ModBlocks.COOLER_LIGHT_GRAY.asItem())
                .add(ModBlocks.COOLER_CYAN.asItem())
                .add(ModBlocks.COOLER_PURPLE.asItem())
                .add(ModBlocks.COOLER_BLUE.asItem())
                .add(ModBlocks.COOLER_BROWN.asItem())
                .add(ModBlocks.COOLER_GREEN.asItem())
                .add(ModBlocks.COOLER_RED.asItem())
                .add(ModBlocks.COOLER_BLACK.asItem())
                // Grill
                .add(ModBlocks.GRILL_WHITE.asItem())
                .add(ModBlocks.GRILL_ORANGE.asItem())
                .add(ModBlocks.GRILL_MAGENTA.asItem())
                .add(ModBlocks.GRILL_LIGHT_BLUE.asItem())
                .add(ModBlocks.GRILL_YELLOW.asItem())
                .add(ModBlocks.GRILL_LIME.asItem())
                .add(ModBlocks.GRILL_PINK.asItem())
                .add(ModBlocks.GRILL_GRAY.asItem())
                .add(ModBlocks.GRILL_LIGHT_GRAY.asItem())
                .add(ModBlocks.GRILL_CYAN.asItem())
                .add(ModBlocks.GRILL_PURPLE.asItem())
                .add(ModBlocks.GRILL_BLUE.asItem())
                .add(ModBlocks.GRILL_BROWN.asItem())
                .add(ModBlocks.GRILL_GREEN.asItem())
                .add(ModBlocks.GRILL_RED.asItem())
                .add(ModBlocks.GRILL_BLACK.asItem())
                // Spatula
                .add(ModItems.SPATULA);

        this.getOrCreateBuilder(ModTags.Items.STORAGE)
                // Cabinets
                .add(ModBlocks.CABINET_OAK.asItem())
                .add(ModBlocks.CABINET_SPRUCE.asItem())
                .add(ModBlocks.CABINET_BIRCH.asItem())
                .add(ModBlocks.CABINET_JUNGLE.asItem())
                .add(ModBlocks.CABINET_ACACIA.asItem())
                .add(ModBlocks.CABINET_DARK_OAK.asItem())
                .add(ModBlocks.CABINET_STRIPPED_OAK.asItem())
                .add(ModBlocks.CABINET_STRIPPED_SPRUCE.asItem())
                .add(ModBlocks.CABINET_STRIPPED_BIRCH.asItem())
                .add(ModBlocks.CABINET_STRIPPED_JUNGLE.asItem())
                .add(ModBlocks.CABINET_STRIPPED_ACACIA.asItem())
                .add(ModBlocks.CABINET_STRIPPED_DARK_OAK.asItem())
                // Bedside Cabinets
                .add(ModBlocks.BEDSIDE_CABINET_OAK.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_SPRUCE.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_BIRCH.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_JUNGLE.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_ACACIA.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_DARK_OAK.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_STRIPPED_OAK.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_STRIPPED_SPRUCE.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_STRIPPED_BIRCH.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_STRIPPED_JUNGLE.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_STRIPPED_ACACIA.asItem())
                .add(ModBlocks.BEDSIDE_CABINET_STRIPPED_DARK_OAK.asItem())
                // Desk Cabinets
                .add(ModBlocks.DESK_CABINET_OAK.asItem())
                .add(ModBlocks.DESK_CABINET_SPRUCE.asItem())
                .add(ModBlocks.DESK_CABINET_BIRCH.asItem())
                .add(ModBlocks.DESK_CABINET_JUNGLE.asItem())
                .add(ModBlocks.DESK_CABINET_ACACIA.asItem())
                .add(ModBlocks.DESK_CABINET_DARK_OAK.asItem())
                .add(ModBlocks.DESK_CABINET_STONE.asItem())
                .add(ModBlocks.DESK_CABINET_GRANITE.asItem())
                .add(ModBlocks.DESK_CABINET_DIORITE.asItem())
                .add(ModBlocks.DESK_CABINET_ANDESITE.asItem())
                .add(ModBlocks.DESK_CABINET_STRIPPED_OAK.asItem())
                .add(ModBlocks.DESK_CABINET_STRIPPED_SPRUCE.asItem())
                .add(ModBlocks.DESK_CABINET_STRIPPED_BIRCH.asItem())
                .add(ModBlocks.DESK_CABINET_STRIPPED_JUNGLE.asItem())
                .add(ModBlocks.DESK_CABINET_STRIPPED_ACACIA.asItem())
                .add(ModBlocks.DESK_CABINET_STRIPPED_DARK_OAK.asItem())
                // Crate
                .add(ModBlocks.CRATE_OAK.asItem())
                .add(ModBlocks.CRATE_SPRUCE.asItem())
                .add(ModBlocks.CRATE_BIRCH.asItem())
                .add(ModBlocks.CRATE_JUNGLE.asItem())
                .add(ModBlocks.CRATE_ACACIA.asItem())
                .add(ModBlocks.CRATE_DARK_OAK.asItem())
                .add(ModBlocks.CRATE_STRIPPED_OAK.asItem())
                .add(ModBlocks.CRATE_STRIPPED_SPRUCE.asItem())
                .add(ModBlocks.CRATE_STRIPPED_BIRCH.asItem())
                .add(ModBlocks.CRATE_STRIPPED_JUNGLE.asItem())
                .add(ModBlocks.CRATE_STRIPPED_ACACIA.asItem())
                .add(ModBlocks.CRATE_STRIPPED_DARK_OAK.asItem())
                // Mailbox
                .add(ModBlocks.MAIL_BOX_OAK.asItem())
                .add(ModBlocks.MAIL_BOX_SPRUCE.asItem())
                .add(ModBlocks.MAIL_BOX_BIRCH.asItem())
                .add(ModBlocks.MAIL_BOX_JUNGLE.asItem())
                .add(ModBlocks.MAIL_BOX_ACACIA.asItem())
                .add(ModBlocks.MAIL_BOX_DARK_OAK.asItem())
                .add(ModBlocks.MAIL_BOX_STRIPPED_OAK.asItem())
                .add(ModBlocks.MAIL_BOX_STRIPPED_SPRUCE.asItem())
                .add(ModBlocks.MAIL_BOX_STRIPPED_BIRCH.asItem())
                .add(ModBlocks.MAIL_BOX_STRIPPED_JUNGLE.asItem())
                .add(ModBlocks.MAIL_BOX_STRIPPED_ACACIA.asItem())
                .add(ModBlocks.MAIL_BOX_STRIPPED_DARK_OAK.asItem())
                // Coolers
                .add(ModBlocks.COOLER_WHITE.asItem())
                .add(ModBlocks.COOLER_ORANGE.asItem())
                .add(ModBlocks.COOLER_MAGENTA.asItem())
                .add(ModBlocks.COOLER_LIGHT_BLUE.asItem())
                .add(ModBlocks.COOLER_YELLOW.asItem())
                .add(ModBlocks.COOLER_LIME.asItem())
                .add(ModBlocks.COOLER_PINK.asItem())
                .add(ModBlocks.COOLER_GRAY.asItem())
                .add(ModBlocks.COOLER_LIGHT_GRAY.asItem())
                .add(ModBlocks.COOLER_CYAN.asItem())
                .add(ModBlocks.COOLER_PURPLE.asItem())
                .add(ModBlocks.COOLER_BLUE.asItem())
                .add(ModBlocks.COOLER_BROWN.asItem())
                .add(ModBlocks.COOLER_GREEN.asItem())
                .add(ModBlocks.COOLER_RED.asItem())
                .add(ModBlocks.COOLER_BLACK.asItem())
                // Park Benches
                .add(ModBlocks.PARK_BENCH_OAK.asItem())
                .add(ModBlocks.PARK_BENCH_SPRUCE.asItem())
                .add(ModBlocks.PARK_BENCH_BIRCH.asItem())
                .add(ModBlocks.PARK_BENCH_JUNGLE.asItem())
                .add(ModBlocks.PARK_BENCH_ACACIA.asItem())
                .add(ModBlocks.PARK_BENCH_DARK_OAK.asItem())
                .add(ModBlocks.PARK_BENCH_STRIPPED_OAK.asItem())
                .add(ModBlocks.PARK_BENCH_STRIPPED_SPRUCE.asItem())
                .add(ModBlocks.PARK_BENCH_STRIPPED_BIRCH.asItem())
                .add(ModBlocks.PARK_BENCH_STRIPPED_JUNGLE.asItem())
                .add(ModBlocks.PARK_BENCH_STRIPPED_ACACIA.asItem())
                .add(ModBlocks.PARK_BENCH_STRIPPED_DARK_OAK.asItem())
                // Post Box
                .add(ModBlocks.POST_BOX.asItem())
                // Mail Boxes
                .add(ModBlocks.MAIL_BOX_OAK.asItem())
                .add(ModBlocks.MAIL_BOX_SPRUCE.asItem())
                .add(ModBlocks.MAIL_BOX_BIRCH.asItem())
                .add(ModBlocks.MAIL_BOX_JUNGLE.asItem())
                .add(ModBlocks.MAIL_BOX_ACACIA.asItem())
                .add(ModBlocks.MAIL_BOX_DARK_OAK.asItem())
                .add(ModBlocks.MAIL_BOX_STRIPPED_OAK.asItem())
                .add(ModBlocks.MAIL_BOX_STRIPPED_SPRUCE.asItem())
                .add(ModBlocks.MAIL_BOX_STRIPPED_BIRCH.asItem())
                .add(ModBlocks.MAIL_BOX_STRIPPED_JUNGLE.asItem())
                .add(ModBlocks.MAIL_BOX_STRIPPED_ACACIA.asItem())
                .add(ModBlocks.MAIL_BOX_STRIPPED_DARK_OAK.asItem())
                // Coolers
                .add(ModBlocks.COOLER_WHITE.asItem())
                .add(ModBlocks.COOLER_ORANGE.asItem())
                .add(ModBlocks.COOLER_MAGENTA.asItem())
                .add(ModBlocks.COOLER_LIGHT_BLUE.asItem())
                .add(ModBlocks.COOLER_YELLOW.asItem())
                .add(ModBlocks.COOLER_LIME.asItem())
                .add(ModBlocks.COOLER_PINK.asItem())
                .add(ModBlocks.COOLER_GRAY.asItem())
                .add(ModBlocks.COOLER_LIGHT_GRAY.asItem())
                .add(ModBlocks.COOLER_CYAN.asItem())
                .add(ModBlocks.COOLER_PURPLE.asItem())
                .add(ModBlocks.COOLER_BLUE.asItem())
                .add(ModBlocks.COOLER_BROWN.asItem())
                .add(ModBlocks.COOLER_GREEN.asItem())
                .add(ModBlocks.COOLER_RED.asItem())
                .add(ModBlocks.COOLER_BLACK.asItem())
                // Kitchen Drawers
                .add(ModBlocks.KITCHEN_DRAWER_OAK.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_SPRUCE.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_BIRCH.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_JUNGLE.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_ACACIA.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_DARK_OAK.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_STRIPPED_OAK.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_STRIPPED_SPRUCE.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_STRIPPED_BIRCH.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_STRIPPED_JUNGLE.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_STRIPPED_ACACIA.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_STRIPPED_DARK_OAK.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_WHITE.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_ORANGE.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_MAGENTA.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_LIGHT_BLUE.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_YELLOW.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_LIME.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_PINK.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_GRAY.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_LIGHT_GRAY.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_CYAN.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_PURPLE.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_BLUE.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_BROWN.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_GREEN.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_RED.asItem())
                .add(ModBlocks.KITCHEN_DRAWER_BLACK.asItem());

        this.getOrCreateBuilder(ModTags.Items.TRAMPOLINE)
                // Trampolines
                .add(ModBlocks.TRAMPOLINE_WHITE.asItem())
                .add(ModBlocks.TRAMPOLINE_ORANGE.asItem())
                .add(ModBlocks.TRAMPOLINE_MAGENTA.asItem())
                .add(ModBlocks.TRAMPOLINE_LIGHT_BLUE.asItem())
                .add(ModBlocks.TRAMPOLINE_YELLOW.asItem())
                .add(ModBlocks.TRAMPOLINE_LIME.asItem())
                .add(ModBlocks.TRAMPOLINE_PINK.asItem())
                .add(ModBlocks.TRAMPOLINE_GRAY.asItem())
                .add(ModBlocks.TRAMPOLINE_LIGHT_GRAY.asItem())
                .add(ModBlocks.TRAMPOLINE_CYAN.asItem())
                .add(ModBlocks.TRAMPOLINE_PURPLE.asItem())
                .add(ModBlocks.TRAMPOLINE_BLUE.asItem())
                .add(ModBlocks.TRAMPOLINE_BROWN.asItem())
                .add(ModBlocks.TRAMPOLINE_GREEN.asItem())
                .add(ModBlocks.TRAMPOLINE_RED.asItem())
                .add(ModBlocks.TRAMPOLINE_BLACK.asItem());

        this.getOrCreateBuilder(ModTags.Items.ITEMS).add(ModItems.SPATULA);

        this.copy(ModTags.Blocks.UPGRADED_FENCE_GATES, ModTags.Items.UPGRADED_FENCE_GATES);
        this.copy(ModTags.Blocks.PICKET_FENCE_GATES, ModTags.Items.PICKET_FENCE_GATES);
        this.copy(ModTags.Blocks.UPGRADED_FENCES, ModTags.Items.UPGRADED_FENCES);
        this.copy(ModTags.Blocks.PICKET_FENCES, ModTags.Items.PICKET_FENCES);
        this.copy(ModTags.Blocks.HEDGES, ModTags.Items.HEDGES);
    }
}