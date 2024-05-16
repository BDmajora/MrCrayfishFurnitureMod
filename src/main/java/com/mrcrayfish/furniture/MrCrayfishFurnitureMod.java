/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.Mod
 *  cpw.mods.fml.common.Mod$EventHandler
 *  cpw.mods.fml.common.Mod$Instance
 *  cpw.mods.fml.common.ModContainer
 *  cpw.mods.fml.common.SidedProxy
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLInterModComms
 *  cpw.mods.fml.common.event.FMLInterModComms$IMCEvent
 *  cpw.mods.fml.common.event.FMLInterModComms$IMCMessage
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.network.IGuiHandler
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.registry.EntityRegistry
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 */
package com.mrcrayfish.furniture;

import com.mrcrayfish.furniture.FurnitureAchievements;
import com.mrcrayfish.furniture.FurnitureTab;
import com.mrcrayfish.furniture.api.IRecipeRegistry;
import com.mrcrayfish.furniture.api.RecipeRegistry;
import com.mrcrayfish.furniture.api.RecipeRegistryComm;
import com.mrcrayfish.furniture.api.Recipes;
import com.mrcrayfish.furniture.blocks.BlockBarStool;
import com.mrcrayfish.furniture.blocks.BlockBasin;
import com.mrcrayfish.furniture.blocks.BlockBath;
import com.mrcrayfish.furniture.blocks.BlockBedsideCabinet;
import com.mrcrayfish.furniture.blocks.BlockBin;
import com.mrcrayfish.furniture.blocks.BlockBirdBath;
import com.mrcrayfish.furniture.blocks.BlockBlender;
import com.mrcrayfish.furniture.blocks.BlockCabinet;
import com.mrcrayfish.furniture.blocks.BlockCabinetKitchen;
import com.mrcrayfish.furniture.blocks.BlockCeilingLight;
import com.mrcrayfish.furniture.blocks.BlockChair;
import com.mrcrayfish.furniture.blocks.BlockChoppingBoard;
import com.mrcrayfish.furniture.blocks.BlockCoffeeTable;
import com.mrcrayfish.furniture.blocks.BlockComputer;
import com.mrcrayfish.furniture.blocks.BlockCookieJar;
import com.mrcrayfish.furniture.blocks.BlockCouch;
import com.mrcrayfish.furniture.blocks.BlockCounter;
import com.mrcrayfish.furniture.blocks.BlockCounterSink;
import com.mrcrayfish.furniture.blocks.BlockCup;
import com.mrcrayfish.furniture.blocks.BlockDishwasher;
import com.mrcrayfish.furniture.blocks.BlockDoorBell;
import com.mrcrayfish.furniture.blocks.BlockElectricFence;
import com.mrcrayfish.furniture.blocks.BlockFireAlarm;
import com.mrcrayfish.furniture.blocks.BlockFreezer;
import com.mrcrayfish.furniture.blocks.BlockFridge;
import com.mrcrayfish.furniture.blocks.BlockHedge;
import com.mrcrayfish.furniture.blocks.BlockLampOff;
import com.mrcrayfish.furniture.blocks.BlockLampOn;
import com.mrcrayfish.furniture.blocks.BlockMailBox;
import com.mrcrayfish.furniture.blocks.BlockMicrowave;
import com.mrcrayfish.furniture.blocks.BlockOven;
import com.mrcrayfish.furniture.blocks.BlockOvenOverhead;
import com.mrcrayfish.furniture.blocks.BlockPlate;
import com.mrcrayfish.furniture.blocks.BlockPresent;
import com.mrcrayfish.furniture.blocks.BlockPrinter;
import com.mrcrayfish.furniture.blocks.BlockShower;
import com.mrcrayfish.furniture.blocks.BlockShowerHead;
import com.mrcrayfish.furniture.blocks.BlockStereo;
import com.mrcrayfish.furniture.blocks.BlockStonePath;
import com.mrcrayfish.furniture.blocks.BlockTV;
import com.mrcrayfish.furniture.blocks.BlockTVAnimation;
import com.mrcrayfish.furniture.blocks.BlockTable;
import com.mrcrayfish.furniture.blocks.BlockTap;
import com.mrcrayfish.furniture.blocks.BlockToaster;
import com.mrcrayfish.furniture.blocks.BlockToilet;
import com.mrcrayfish.furniture.blocks.BlockTree;
import com.mrcrayfish.furniture.blocks.BlockWallCabinet;
import com.mrcrayfish.furniture.blocks.BlockWashingMachine;
import com.mrcrayfish.furniture.blocks.BlockWhiteFence;
import com.mrcrayfish.furniture.blocks.BlockWindowDecoration;
import com.mrcrayfish.furniture.blocks.BlockWindowDecorationClosed;
import com.mrcrayfish.furniture.entity.EntitySittableBlock;
import com.mrcrayfish.furniture.gui.GuiHandler;
import com.mrcrayfish.furniture.handler.ConfigurationHandler;
import com.mrcrayfish.furniture.handler.CraftingHandler;
import com.mrcrayfish.furniture.handler.InputHandler;
import com.mrcrayfish.furniture.handler.PlayerEvents;
import com.mrcrayfish.furniture.handler.SyncEvent;
import com.mrcrayfish.furniture.items.ItemBath;
import com.mrcrayfish.furniture.items.ItemBlockCustom;
import com.mrcrayfish.furniture.items.ItemCup;
import com.mrcrayfish.furniture.items.ItemEnvelope;
import com.mrcrayfish.furniture.items.ItemEnvelopeSigned;
import com.mrcrayfish.furniture.items.ItemFireAlarm;
import com.mrcrayfish.furniture.items.ItemFridge;
import com.mrcrayfish.furniture.items.ItemFurniture;
import com.mrcrayfish.furniture.items.ItemFurnitureFood;
import com.mrcrayfish.furniture.items.ItemFurniturePlacer;
import com.mrcrayfish.furniture.items.ItemHammer;
import com.mrcrayfish.furniture.items.ItemPackage;
import com.mrcrayfish.furniture.items.ItemPackageSigned;
import com.mrcrayfish.furniture.items.ItemPresent;
import com.mrcrayfish.furniture.items.ItemRecipeBook;
import com.mrcrayfish.furniture.network.PacketHandler;
import com.mrcrayfish.furniture.proxy.CommonProxy;
import com.mrcrayfish.furniture.tileentity.TileEntityBasin;
import com.mrcrayfish.furniture.tileentity.TileEntityBath;
import com.mrcrayfish.furniture.tileentity.TileEntityBedsideCabinet;
import com.mrcrayfish.furniture.tileentity.TileEntityBin;
import com.mrcrayfish.furniture.tileentity.TileEntityBlender;
import com.mrcrayfish.furniture.tileentity.TileEntityCabinet;
import com.mrcrayfish.furniture.tileentity.TileEntityCabinetKitchen;
import com.mrcrayfish.furniture.tileentity.TileEntityChoppingBoard;
import com.mrcrayfish.furniture.tileentity.TileEntityComputer;
import com.mrcrayfish.furniture.tileentity.TileEntityCookieJar;
import com.mrcrayfish.furniture.tileentity.TileEntityCouch;
import com.mrcrayfish.furniture.tileentity.TileEntityCounterSink;
import com.mrcrayfish.furniture.tileentity.TileEntityCup;
import com.mrcrayfish.furniture.tileentity.TileEntityDishwasher;
import com.mrcrayfish.furniture.tileentity.TileEntityFreezer;
import com.mrcrayfish.furniture.tileentity.TileEntityFridge;
import com.mrcrayfish.furniture.tileentity.TileEntityMailBox;
import com.mrcrayfish.furniture.tileentity.TileEntityMicrowave;
import com.mrcrayfish.furniture.tileentity.TileEntityOven;
import com.mrcrayfish.furniture.tileentity.TileEntityPlate;
import com.mrcrayfish.furniture.tileentity.TileEntityPresent;
import com.mrcrayfish.furniture.tileentity.TileEntityPrinter;
import com.mrcrayfish.furniture.tileentity.TileEntityShowerHead;
import com.mrcrayfish.furniture.tileentity.TileEntityStereo;
import com.mrcrayfish.furniture.tileentity.TileEntityTV;
import com.mrcrayfish.furniture.tileentity.TileEntityToaster;
import com.mrcrayfish.furniture.tileentity.TileEntityWallCabinet;
import com.mrcrayfish.furniture.tileentity.TileEntityWashingMachine;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import java.lang.reflect.Method;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

@Mod(modid="cfm", name="MrCrayfish's Furniture Mod", version="3.4.7", guiFactory="com.mrcrayfish.furniture.gui.GuiFactory")
public class MrCrayfishFurnitureMod {
    @Mod.Instance(value="cfm")
    public static MrCrayfishFurnitureMod instance = new MrCrayfishFurnitureMod();
    @SidedProxy(clientSide="com.mrcrayfish.furniture.proxy.ClientProxy", serverSide="com.mrcrayfish.furniture.proxy.CommonProxy")
    public static CommonProxy proxy;
    private InputHandler keyHandler;
    private GuiHandler guiHandler = new GuiHandler();
    public static boolean hasDisplayedOnce;
    public static Item itemTableWood;
    public static Item itemTableStone;
    public static Item itemChairWood;
    public static Item itemChairStone;
    public static Item itemCabinet;
    public static Item itemBedsideCabinet;
    public static Item itemCoffeeTableWood;
    public static Item itemCoffeeTableStone;
    public static Item itemFridge;
    public static Item itemCoolPack;
    public static Item itemCouch;
    public static Item itemBlinds;
    public static Item itemCurtains;
    public static Item itemOven;
    public static Item itemOvenRangehood;
    public static Item itemFlesh;
    public static Item itemCookedFlesh;
    public static Item itemHedgeBasic;
    public static Item itemHedgeSpruce;
    public static Item itemHedgeBirch;
    public static Item itemHedgeJungle;
    public static Item itemBirdBath;
    public static Item itemStonePath;
    public static Item itemWhiteFence;
    public static Item itemTap;
    public static Item itemMailBox;
    public static Item itemHammer;
    public static Item itemEnvelope;
    public static Item itemEnvelopeSigned;
    public static Item itemPackage;
    public static Item itemPackageSigned;
    public static Item itemTV;
    public static Item itemComputer;
    public static Item itemPrinter;
    public static Item itemInkCartridge;
    public static Item itemStereo;
    public static Item itemElectricFence;
    public static Item itemCeilingLight;
    public static Item itemDoorBell;
    public static Item itemFireAlarm;
    public static Item itemLamp;
    public static Item itemToilet;
    public static Item itemBasin;
    public static Item itemWallCabinet;
    public static Item itemBath;
    public static Item itemShower;
    public static Item itemShowerHead;
    public static Item itemBin;
    public static Item itemToaster;
    public static Item itemMicrowave;
    public static Item itemBlender;
    public static Item itemWashingMachine;
    public static Item itemDishWasher;
    public static Item itemCounterDoored;
    public static Item itemCounterSink;
    public static Item itemKitchenCabinet;
    public static Item itemPlate;
    public static Item itemCookieJar;
    public static Item itemBarStool;
    public static Item itemChoppingBoard;
    public static Item itemKnife;
    public static Item itemCup;
    public static Item itemDrink;
    public static Item itemSoap;
    public static Item itemSoapyWater;
    public static Item itemSuperSoapyWater;
    public static Item itemBreadSlice;
    public static Item itemToast;
    public static Item itemRecipeBook;
    public static Item itemPresentRed;
    public static Item itemPresentGreen;
    public static Item itemTree;
    public static Item itemCrayfish;
    public static Item itemDollar;
    public static Block coffeeTableWood;
    public static Block coffeeTableStone;
    public static Block tableWood;
    public static Block tableStone;
    public static Block chairWood;
    public static Block chairStone;
    public static Block freezer;
    public static Block fridge;
    public static Block cabinet;
    public static Block bedsideCabinet;
    public static Block couch;
    public static Block blinds;
    public static Block blindsClosed;
    public static Block curtains;
    public static Block curtainsClosed;
    public static Block oven;
    public static Block ovenOverhead;
    public static Block hedge;
    public static Block birdBath;
    public static Block stonePath;
    public static Block whiteFence;
    public static Block tap;
    public static Block mailBox;
    public static Block TV;
    public static Block computer;
    public static Block printer;
    public static Block electricFence;
    public static Block doorBell;
    public static Block stereo;
    public static Block fireAlarmOff;
    public static Block fireAlarmOn;
    public static Block ceilingLightOff;
    public static Block ceilingLightOn;
    public static Block lampOn;
    public static Block lampOff;
    public static Block toilet;
    public static Block basin;
    public static Block bath1;
    public static Block bath2;
    public static Block showerBottom;
    public static Block showerTop;
    public static Block showerHeadOff;
    public static Block showerHeadOn;
    public static Block wallCabinet;
    public static Block bin;
    public static Block toaster;
    public static Block microwave;
    public static Block blender;
    public static Block washingMachine;
    public static Block dishWasher;
    public static Block counterDoored;
    public static Block counterSink;
    public static Block kitchenCabinet;
    public static Block cup;
    public static Block plate;
    public static Block cookieJar;
    public static Block barStool;
    public static Block choppingBoard;
    public static Block present;
    public static Block tree;
    public static Block string;
    public static Block hey;
    public static Block nyan;
    public static Block pattern;
    public static Block yellowGlow;
    public static Block whiteGlass;
    public static CreativeTabs tabFurniture;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        FMLCommonHandler.instance().bus().register((Object)new ConfigurationHandler());
        lampOn = new BlockLampOn(Material.glass).setUnlocalizedName("lampon").setLightLevel(1.0f).setHardness(0.75f).setStepSound(Block.soundTypeCloth);
        lampOff = new BlockLampOff(Material.glass).setUnlocalizedName("lampoff").setHardness(0.75f).setStepSound(Block.soundTypeCloth);
        coffeeTableWood = new BlockCoffeeTable(Material.wood).setUnlocalizedName("coffetablewood").setHardness(1.0f).setStepSound(Block.soundTypeWood);
        coffeeTableStone = new BlockCoffeeTable(Material.rock).setUnlocalizedName("coffetablestone").setHardness(1.5f).setStepSound(Block.soundTypeStone);
        tableWood = new BlockTable(Material.wood).setUnlocalizedName("tablewood").setHardness(1.0f).setStepSound(Block.soundTypeWood);
        tableStone = new BlockTable(Material.rock).setUnlocalizedName("tablestone").setHardness(1.5f).setStepSound(Block.soundTypeStone);
        chairWood = new BlockChair(Material.wood).setUnlocalizedName("chairwood").setHardness(1.0f).setStepSound(Block.soundTypeWood);
        chairStone = new BlockChair(Material.rock).setUnlocalizedName("chairstone").setHardness(1.5f).setStepSound(Block.soundTypeStone);
        freezer = new BlockFreezer().setUnlocalizedName("freezer").setHardness(2.0f).setStepSound(Block.soundTypeMetal);
        fridge = new BlockFridge().setUnlocalizedName("fridge").setHardness(2.0f).setStepSound(Block.soundTypeStone);
        cabinet = new BlockCabinet(Material.wood).setUnlocalizedName("cabinet").setHardness(1.0f).setStepSound(Block.soundTypeWood);
        couch = new BlockCouch(Material.cloth).setUnlocalizedName("couch").setHardness(0.5f).setStepSound(Block.soundTypeCloth);
        blinds = new BlockWindowDecoration(Material.wood).setUnlocalizedName("blindon").setHardness(0.5f).setStepSound(Block.soundTypeWood);
        blindsClosed = new BlockWindowDecorationClosed(Material.glass).setUnlocalizedName("blindoff").setHardness(0.5f).setStepSound(Block.soundTypeWood);
        curtains = new BlockWindowDecoration(Material.cloth).setUnlocalizedName("curtainon").setHardness(0.5f).setStepSound(Block.soundTypeCloth);
        curtainsClosed = new BlockWindowDecorationClosed(Material.cloth).setUnlocalizedName("curtainoff").setHardness(0.5f).setStepSound(Block.soundTypeCloth);
        bedsideCabinet = new BlockBedsideCabinet(Material.wood).setUnlocalizedName("bedsidecabinet").setHardness(1.0f).setStepSound(Block.soundTypeWood);
        oven = new BlockOven().setUnlocalizedName("oven").setHardness(1.0f).setStepSound(Block.soundTypeMetal);
        ovenOverhead = new BlockOvenOverhead(Material.rock).setUnlocalizedName("ovenoverhead").setHardness(0.5f).setStepSound(Block.soundTypeMetal).setLightLevel(0.5f);
        hedge = new BlockHedge().setUnlocalizedName("hedge").setHardness(0.2f).setStepSound(Block.soundTypeGrass);
        birdBath = new BlockBirdBath(Material.rock).setUnlocalizedName("birdbath").setHardness(1.0f).setStepSound(Block.soundTypeStone);
        stonePath = new BlockStonePath(Material.rock).setUnlocalizedName("stonepath").setHardness(0.75f).setStepSound(Block.soundTypeStone);
        whiteFence = new BlockWhiteFence(Material.wood).setUnlocalizedName("whitefence").setHardness(1.0f).setStepSound(Block.soundTypeWood);
        tap = new BlockTap(Material.rock).setUnlocalizedName("tap").setHardness(0.5f).setStepSound(Block.soundTypeStone);
        mailBox = new BlockMailBox(Material.wood).setUnlocalizedName("mailbox").setStepSound(Block.soundTypeWood);
        TV = new BlockTV(Material.wood).setUnlocalizedName("tv").setStepSound(Block.soundTypeWood);
        computer = new BlockComputer(Material.iron).setHardness(1.0f).setUnlocalizedName("computer").setStepSound(Block.soundTypeAnvil);
        printer = new BlockPrinter(Material.iron).setHardness(1.0f).setUnlocalizedName("printer").setStepSound(Block.soundTypeAnvil);
        electricFence = new BlockElectricFence().setHardness(1.0f).setUnlocalizedName("electricfence").setStepSound(Block.soundTypeAnvil);
        doorBell = new BlockDoorBell(Material.wood).setUnlocalizedName("doorbell").setStepSound(Block.soundTypeWood);
        fireAlarmOff = new BlockFireAlarm(Material.rock, false).setHardness(0.5f).setUnlocalizedName("firealarmoff").setStepSound(Block.soundTypeStone);
        fireAlarmOn = new BlockFireAlarm(Material.rock, true).setHardness(0.5f).setUnlocalizedName("firealarmon").setStepSound(Block.soundTypeStone);
        ceilingLightOff = new BlockCeilingLight(Material.glass, false).setHardness(0.5f).setUnlocalizedName("ceilinglightoff").setStepSound(Block.soundTypeGlass);
        ceilingLightOn = new BlockCeilingLight(Material.glass, true).setHardness(0.5f).setUnlocalizedName("ceilinglighton").setStepSound(Block.soundTypeGlass);
        stereo = new BlockStereo(Material.wood).setUnlocalizedName("stereo").setHardness(1.0f).setStepSound(Block.soundTypeWood);
        toilet = new BlockToilet(Material.rock).setHardness(1.0f).setUnlocalizedName("toilet").setStepSound(Block.soundTypeStone);
        basin = new BlockBasin(Material.rock).setHardness(1.0f).setUnlocalizedName("basin").setStepSound(Block.soundTypeStone);
        wallCabinet = new BlockWallCabinet(Material.rock).setHardness(1.0f).setUnlocalizedName("wallcabinet").setStepSound(Block.soundTypeStone);
        bath1 = new BlockBath(Material.rock).setHardness(1.0f).setUnlocalizedName("bath1").setStepSound(Block.soundTypeStone);
        bath2 = new BlockBath(Material.rock).setHardness(1.0f).setUnlocalizedName("bath2").setStepSound(Block.soundTypeStone);
        showerBottom = new BlockShower(Material.rock).setHardness(1.0f).setUnlocalizedName("showerbottom").setStepSound(Block.soundTypeStone);
        showerTop = new BlockShower(Material.rock).setHardness(1.0f).setUnlocalizedName("showertop").setStepSound(Block.soundTypeStone);
        showerHeadOff = new BlockShowerHead(Material.rock).setHardness(1.0f).setUnlocalizedName("showerheadoff").setStepSound(Block.soundTypeStone);
        showerHeadOn = new BlockShowerHead(Material.rock).setHardness(1.0f).setUnlocalizedName("showerheadon").setStepSound(Block.soundTypeStone);
        bin = new BlockBin(Material.rock).setUnlocalizedName("bin").setStepSound(Block.soundTypeAnvil).setHardness(0.5f);
        tree = new BlockTree(Material.wood).setUnlocalizedName("tree").setStepSound(Block.soundTypeWood).setLightLevel(0.3f).setHardness(0.5f);
        present = new BlockPresent(Material.cloth).setUnlocalizedName("present").setStepSound(Block.soundTypeCloth).setHardness(0.5f);
        toaster = new BlockToaster(Material.wood).setUnlocalizedName("toaster").setStepSound(Block.soundTypeAnvil).setHardness(0.5f);
        microwave = new BlockMicrowave(Material.wood).setUnlocalizedName("microwave").setStepSound(Block.soundTypeAnvil).setHardness(0.5f);
        washingMachine = new BlockWashingMachine(Material.rock).setUnlocalizedName("washingmachine").setStepSound(Block.soundTypeAnvil).setHardness(0.5f);
        cookieJar = new BlockCookieJar(Material.glass).setUnlocalizedName("cookiejar").setStepSound(Block.soundTypeGlass).setHardness(0.5f);
        blender = new BlockBlender(Material.glass).setUnlocalizedName("blender").setStepSound(Block.soundTypeGlass).setHardness(0.5f);
        cup = new BlockCup(Material.glass).setUnlocalizedName("cup").setStepSound(Block.soundTypeGlass).setHardness(0.1f);
        plate = new BlockPlate(Material.glass).setUnlocalizedName("plate").setStepSound(Block.soundTypeGlass).setHardness(0.5f);
        counterDoored = new BlockCounter(Material.wood).setUnlocalizedName("counterdoored").setStepSound(Block.soundTypeWood).setHardness(0.5f);
        counterSink = new BlockCounterSink(Material.wood).setUnlocalizedName("countersink").setStepSound(Block.soundTypeWood).setHardness(0.5f);
        dishWasher = new BlockDishwasher(Material.rock).setUnlocalizedName("dishwasher").setStepSound(Block.soundTypeAnvil).setHardness(0.5f);
        kitchenCabinet = new BlockCabinetKitchen(Material.wood).setUnlocalizedName("kitchencabinet").setStepSound(Block.soundTypeWood).setHardness(0.5f);
        choppingBoard = new BlockChoppingBoard(Material.wood).setUnlocalizedName("choppingboard").setStepSound(Block.soundTypeWood).setHardness(0.5f);
        barStool = new BlockBarStool(Material.wood).setUnlocalizedName("barstool").setStepSound(Block.soundTypeWood).setHardness(0.5f);
        hey = new BlockTVAnimation().setUnlocalizedName("hey").setTextureName("cfm:hey");
        nyan = new BlockTVAnimation().setUnlocalizedName("nyan").setTextureName("cfm:nyan");
        pattern = new BlockTVAnimation().setUnlocalizedName("pattern").setTextureName("cfm:pattern");
        yellowGlow = new BlockTVAnimation().setUnlocalizedName("yellowGlow").setTextureName("yellowGlow").setTextureName("cfm:yellowglow");
        whiteGlass = new BlockTVAnimation().setUnlocalizedName("whiteGlass").setTextureName("cfm:whiteglass");
        itemTableWood = new ItemBlockCustom(tableWood, 1).setUnlocalizedName("itemtablewood").setTextureName("cfm:itemtablewood").setCreativeTab(tabFurniture);
        itemTableStone = new ItemBlockCustom(tableStone, 2).setUnlocalizedName("itemtablestone").setTextureName("cfm:itemtablestone").setCreativeTab(tabFurniture);
        itemChairWood = new ItemFurniturePlacer(chairWood).setUnlocalizedName("itemchairwood").setTextureName("cfm:itemchairwood").setCreativeTab(tabFurniture);
        itemChairStone = new ItemFurniturePlacer(chairStone).setUnlocalizedName("itemchairstone").setTextureName("cfm:itemchairstone").setCreativeTab(tabFurniture);
        itemCabinet = new ItemFurniturePlacer(cabinet).setUnlocalizedName("itemcabinet").setTextureName("cfm:itemcabinet").setCreativeTab(tabFurniture);
        itemCoffeeTableWood = new ItemBlockCustom(coffeeTableWood, 1).setUnlocalizedName("itemcoffeetablewood").setTextureName("cfm:itemcoffeetablewood").setCreativeTab(tabFurniture);
        itemCoffeeTableStone = new ItemBlockCustom(coffeeTableStone, 2).setUnlocalizedName("itemcoffeetablestone").setTextureName("cfm:itemcoffeetablestone").setCreativeTab(tabFurniture);
        itemFridge = new ItemFridge().setUnlocalizedName("itemfridge").setTextureName("cfm:itemfridge").setCreativeTab(tabFurniture);
        itemCouch = new ItemFurniturePlacer(couch).setUnlocalizedName("itemcouch").setTextureName("cfm:itemcouchwhite").setCreativeTab(tabFurniture);
        itemBlinds = new ItemFurniturePlacer(blinds).setUnlocalizedName("itemblinds").setTextureName("cfm:itemblinds").setCreativeTab(tabFurniture);
        itemCurtains = new ItemFurniturePlacer(curtains).setUnlocalizedName("itemcurtains").setTextureName("cfm:itemcurtains").setCreativeTab(tabFurniture);
        itemLamp = new ItemBlockCustom(lampOff, 0).setUnlocalizedName("itemlamp").setTextureName("cfm:itemlamp").setCreativeTab(tabFurniture);
        itemBedsideCabinet = new ItemFurniturePlacer(bedsideCabinet).setUnlocalizedName("itembedsidecabinet").setTextureName("cfm:itembedsidecabinet").setCreativeTab(tabFurniture);
        itemCoolPack = new ItemFurniture("itemcoolpack").setUnlocalizedName("itemcoolpack").setTextureName("cfm:itemcoolpack").setCreativeTab(tabFurniture);
        itemOven = new ItemFurniturePlacer(oven).setUnlocalizedName("itemoven").setTextureName("cfm:itemoven").setCreativeTab(tabFurniture);
        itemOvenRangehood = new ItemFurniturePlacer(ovenOverhead).setUnlocalizedName("itemovenoverhead").setTextureName("cfm:itemovenoverhead").setCreativeTab(tabFurniture);
        itemFlesh = new ItemFurnitureFood(1, 2, false).setUnlocalizedName("itemflesh").setTextureName("cfm:itemflesh").setCreativeTab(tabFurniture);
        itemCookedFlesh = new ItemFurnitureFood(4, 4, false).setUnlocalizedName("itemfleshcooked").setTextureName("cfm:itemfleshcooked").setCreativeTab(tabFurniture);
        itemHedgeBasic = new ItemBlockCustom(hedge, 0).setUnlocalizedName("itemhedgebasic").setTextureName("cfm:itemhedgebasic").setCreativeTab(tabFurniture);
        itemHedgeSpruce = new ItemBlockCustom(hedge, 1).setUnlocalizedName("itemhedgespruce").setTextureName("cfm:itemhedgespruce").setCreativeTab(tabFurniture);
        itemHedgeBirch = new ItemBlockCustom(hedge, 2).setUnlocalizedName("itemhedgebirch").setTextureName("cfm:itemhedgebirch").setCreativeTab(tabFurniture);
        itemHedgeJungle = new ItemBlockCustom(hedge, 3).setUnlocalizedName("itemhedgejungle").setTextureName("cfm:itemhedgejungle").setCreativeTab(tabFurniture);
        itemBirdBath = new ItemBlockCustom(birdBath, 0).setUnlocalizedName("itembirdbath").setTextureName("cfm:itembirdbath").setCreativeTab(tabFurniture);
        itemStonePath = new ItemBlockCustom(stonePath, 2).setUnlocalizedName("itemstonepath").setTextureName("cfm:itemstonepath").setCreativeTab(tabFurniture);
        itemWhiteFence = new ItemBlockCustom(whiteFence, 0).setUnlocalizedName("itemwhitefence").setTextureName("cfm:itemwhitefence").setCreativeTab(tabFurniture);
        itemTap = new ItemFurniturePlacer(tap).setUnlocalizedName("itemtap").setTextureName("cfm:itemtap").setCreativeTab(tabFurniture);
        itemMailBox = new ItemFurniturePlacer(mailBox).setUnlocalizedName("itemmailbox").setTextureName("cfm:itemmailbox").setCreativeTab(tabFurniture);
        itemEnvelope = new ItemEnvelope().setUnlocalizedName("itemenvelope").setTextureName("cfm:itemenvelope").setCreativeTab(tabFurniture).setMaxStackSize(1);
        itemEnvelopeSigned = new ItemEnvelopeSigned().setUnlocalizedName("itemenvelopesigned").setTextureName("cfm:itemenvelope").setMaxStackSize(1);
        itemPackage = new ItemPackage().setUnlocalizedName("itempackage").setTextureName("cfm:itempackage").setCreativeTab(tabFurniture).setMaxStackSize(1);
        itemPackageSigned = new ItemPackageSigned().setUnlocalizedName("itempackagesigned").setTextureName("cfm:itempackage").setMaxStackSize(1);
        itemHammer = new ItemHammer().setUnlocalizedName("itemhammer").setTextureName("cfm:itemhammer").setCreativeTab(tabFurniture);
        itemTV = new ItemFurniturePlacer(TV).setUnlocalizedName("itemtv").setTextureName("cfm:itemtv").setCreativeTab(tabFurniture);
        itemComputer = new ItemFurniturePlacer(computer).setUnlocalizedName("itemcomputer").setTextureName("cfm:itemcomputer").setCreativeTab(tabFurniture);
        itemPrinter = new ItemFurniturePlacer(printer).setUnlocalizedName("itemprinter").setTextureName("cfm:itemprinter").setCreativeTab(tabFurniture);
        itemInkCartridge = new ItemFurniture("iteminkcartridge").setUnlocalizedName("iteminkcartridge").setTextureName("cfm:iteminkcartridge").setCreativeTab(tabFurniture);
        itemElectricFence = new ItemBlockCustom(electricFence, 0).setUnlocalizedName("itemelectricfence").setTextureName("cfm:itemelectricfence").setCreativeTab(tabFurniture);
        itemFireAlarm = new ItemFireAlarm(fireAlarmOff).setUnlocalizedName("itemfirealarm").setTextureName("cfm:itemfirealarm").setCreativeTab(tabFurniture);
        itemCeilingLight = new ItemFireAlarm(ceilingLightOff).setUnlocalizedName("itemceilinglight").setTextureName("cfm:itemceilinglight").setCreativeTab(tabFurniture);
        itemDoorBell = new ItemFurniturePlacer(doorBell).setUnlocalizedName("itemdoorbell").setTextureName("cfm:itemdoorbell").setCreativeTab(tabFurniture);
        itemStereo = new ItemFurniturePlacer(stereo).setUnlocalizedName("itemstereo").setTextureName("cfm:itemstereo").setCreativeTab(tabFurniture);
        itemToilet = new ItemFurniturePlacer(toilet).setUnlocalizedName("itemtoilet").setTextureName("cfm:itemtoilet").setCreativeTab(tabFurniture);
        itemBasin = new ItemFurniturePlacer(basin).setUnlocalizedName("itembasin").setTextureName("cfm:itembasin").setCreativeTab(tabFurniture);
        itemWallCabinet = new ItemFurniturePlacer(wallCabinet).setUnlocalizedName("itemwallcabinet").setTextureName("cfm:itemwallcabinet").setCreativeTab(tabFurniture);
        itemBath = new ItemBath().setUnlocalizedName("itembath").setTextureName("cfm:itembath").setCreativeTab(tabFurniture);
        itemShower = new ItemFurniturePlacer(showerBottom).setUnlocalizedName("itemshower").setTextureName("cfm:itemshower").setCreativeTab(tabFurniture);
        itemShowerHead = new ItemFurniturePlacer(showerHeadOff).setUnlocalizedName("itemshowerhead").setTextureName("cfm:itemshowerhead").setCreativeTab(tabFurniture);
        itemBin = new ItemFurniturePlacer(bin).setUnlocalizedName("itembin").setTextureName("cfm:itembin").setCreativeTab(tabFurniture);
        itemPresentRed = new ItemPresent().setUnlocalizedName("itempresentred").setTextureName("cfm:itempresentred").setCreativeTab(tabFurniture).setMaxStackSize(1);
        itemPresentGreen = new ItemPresent().setUnlocalizedName("itempresentgreen").setTextureName("cfm:itempresentgreen").setCreativeTab(tabFurniture).setMaxStackSize(1);
        itemTree = new ItemFurniturePlacer(tree).setUnlocalizedName("itemtree").setTextureName("cfm:itemtree").setCreativeTab(tabFurniture);
        itemToaster = new ItemFurniturePlacer(toaster).setUnlocalizedName("itemtoaster").setTextureName("cfm:itemtoaster").setCreativeTab(tabFurniture);
        itemMicrowave = new ItemFurniturePlacer(microwave).setUnlocalizedName("itemmicrowave").setTextureName("cfm:itemmicrowave").setCreativeTab(tabFurniture);
        itemWashingMachine = new ItemFurniturePlacer(washingMachine).setUnlocalizedName("itemwashingmachine").setTextureName("cfm:itemwashingmachine").setCreativeTab(tabFurniture);
        itemCookieJar = new ItemBlockCustom(cookieJar, 0).setUnlocalizedName("itemcookiejar").setTextureName("cfm:itemcookiejar").setCreativeTab(tabFurniture);
        itemBlender = new ItemFurniturePlacer(blender).setUnlocalizedName("itemblender").setTextureName("cfm:itemblender").setCreativeTab(tabFurniture);
        itemPlate = new ItemFurniturePlacer(plate).setUnlocalizedName("itemplate").setTextureName("cfm:itemplate").setCreativeTab(tabFurniture);
        itemCounterDoored = new ItemFurniturePlacer(counterDoored).setUnlocalizedName("itemkitchencounter").setTextureName("cfm:itemkitchencounter").setCreativeTab(tabFurniture);
        itemCounterSink = new ItemFurniturePlacer(counterSink).setUnlocalizedName("itemkitchencountersink").setTextureName("cfm:itemkitchencountersink").setCreativeTab(tabFurniture);
        itemDishWasher = new ItemFurniturePlacer(dishWasher).setUnlocalizedName("itemdishwasher").setTextureName("cfm:itemdishwasher").setCreativeTab(tabFurniture);
        itemKitchenCabinet = new ItemFurniturePlacer(kitchenCabinet).setUnlocalizedName("itemkitchencabinet").setTextureName("cfm:itemkitchencabinet").setCreativeTab(tabFurniture);
        itemChoppingBoard = new ItemFurniturePlacer(choppingBoard).setUnlocalizedName("itemchoppingboard").setTextureName("cfm:itemchoppingboard").setCreativeTab(tabFurniture);
        itemBarStool = new ItemBlockCustom(barStool, 15).setUnlocalizedName("itembarstool").setTextureName("cfm:itembarstool").setCreativeTab(tabFurniture);
        itemBreadSlice = new ItemFood(2, false).setUnlocalizedName("itembreadslice").setTextureName("cfm:itembreadslice").setCreativeTab(tabFurniture);
        itemToast = new ItemFood(4, false).setUnlocalizedName("itemtoast").setTextureName("cfm:itemtoast").setCreativeTab(tabFurniture);
        itemKnife = new ItemSword(Item.ToolMaterial.STONE).setUnlocalizedName("itemknife").setTextureName("cfm:itemknife").setMaxStackSize(1).setCreativeTab(tabFurniture);
        itemCup = new ItemCup(false).setUnlocalizedName("itemcup").setCreativeTab(tabFurniture);
        itemDrink = new ItemCup(true).setUnlocalizedName("itemdrink");
        itemSoap = new Item().setUnlocalizedName("itemsoap").setTextureName("cfm:itemsoap").setCreativeTab(tabFurniture);
        itemSoapyWater = new Item().setUnlocalizedName("itemsoapwater").setTextureName("cfm:itemsoapwater").setMaxStackSize(1).setContainerItem(Items.bucket).setCreativeTab(tabFurniture);
        itemSuperSoapyWater = new Item().setUnlocalizedName("itemsupersoapwater").setTextureName("cfm:itemsupersoapwater").setMaxStackSize(1).setContainerItem(Items.bucket).setCreativeTab(tabFurniture);
        itemRecipeBook = new ItemRecipeBook().setUnlocalizedName("itemrecipebook").setTextureName("cfm:itemrecipebook").setMaxStackSize(1).setCreativeTab(tabFurniture);
        itemCrayfish = new Item().setUnlocalizedName("itemcrayfish").setMaxStackSize(1).setTextureName("cfm:crayfish");
        itemDollar = new Item().setUnlocalizedName("itemmoney").setMaxStackSize(1).setTextureName("cfm:money");
        GameRegistry.registerBlock((Block)lampOn, (String)lampOn.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)lampOff, (String)lampOff.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)coffeeTableWood, (String)coffeeTableWood.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)coffeeTableStone, (String)coffeeTableStone.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)tableWood, (String)tableWood.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)tableStone, (String)tableStone.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)chairWood, (String)chairWood.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)chairStone, (String)chairStone.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)freezer, (String)freezer.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)fridge, (String)fridge.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)cabinet, (String)cabinet.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)couch, (String)couch.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)blinds, (String)blinds.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)blindsClosed, (String)blindsClosed.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)curtains, (String)curtains.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)curtainsClosed, (String)curtainsClosed.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)bedsideCabinet, (String)bedsideCabinet.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)oven, (String)oven.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)ovenOverhead, (String)ovenOverhead.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)hedge, (String)hedge.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)birdBath, (String)birdBath.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)stonePath, (String)stonePath.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)whiteFence, (String)whiteFence.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)tap, (String)tap.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)mailBox, (String)mailBox.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)TV, (String)TV.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)computer, (String)computer.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)printer, (String)printer.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)electricFence, (String)electricFence.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)doorBell, (String)doorBell.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)fireAlarmOff, (String)fireAlarmOff.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)fireAlarmOn, (String)fireAlarmOn.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)ceilingLightOff, (String)ceilingLightOff.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)ceilingLightOn, (String)ceilingLightOn.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)stereo, (String)stereo.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)toilet, (String)toilet.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)basin, (String)basin.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)wallCabinet, (String)wallCabinet.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)bath1, (String)bath1.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)bath2, (String)bath2.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)showerBottom, (String)showerBottom.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)showerTop, (String)showerTop.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)showerHeadOff, (String)showerHeadOff.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)showerHeadOn, (String)showerHeadOn.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)bin, (String)bin.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)present, (String)present.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)tree, (String)tree.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)toaster, (String)toaster.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)microwave, (String)microwave.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)washingMachine, (String)washingMachine.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)cookieJar, (String)cookieJar.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)blender, (String)blender.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)plate, (String)plate.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)cup, (String)cup.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)counterDoored, (String)counterDoored.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)counterSink, (String)counterSink.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)dishWasher, (String)dishWasher.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)kitchenCabinet, (String)kitchenCabinet.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)choppingBoard, (String)choppingBoard.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)barStool, (String)barStool.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)hey, (String)hey.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)nyan, (String)nyan.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)pattern, (String)pattern.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)yellowGlow, (String)yellowGlow.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock((Block)whiteGlass, (String)whiteGlass.getUnlocalizedName().substring(5));
        GameRegistry.registerItem((Item)itemTableWood, (String)"ItemTableWood");
        GameRegistry.registerItem((Item)itemTableStone, (String)"ItemTableStone");
        GameRegistry.registerItem((Item)itemChairWood, (String)"ItemChairWood");
        GameRegistry.registerItem((Item)itemChairStone, (String)"ItemChairStone");
        GameRegistry.registerItem((Item)itemCabinet, (String)"ItemCabinet");
        GameRegistry.registerItem((Item)itemBedsideCabinet, (String)"ItemBesideCabinet");
        GameRegistry.registerItem((Item)itemCoffeeTableWood, (String)"ItemCoffeeTableWood");
        GameRegistry.registerItem((Item)itemCoffeeTableStone, (String)"ItemCoffeeTableStone");
        GameRegistry.registerItem((Item)itemFridge, (String)"ItemFridge");
        GameRegistry.registerItem((Item)itemCoolPack, (String)"ItemCoolPack");
        GameRegistry.registerItem((Item)itemCouch, (String)"ItemCouch");
        GameRegistry.registerItem((Item)itemBlinds, (String)"ItemBlinds");
        GameRegistry.registerItem((Item)itemCurtains, (String)"ItemCurtains");
        GameRegistry.registerItem((Item)itemOven, (String)"ItemOven");
        GameRegistry.registerItem((Item)itemOvenRangehood, (String)"ItemOvenRangehood");
        GameRegistry.registerItem((Item)itemFlesh, (String)"ItemFlesh");
        GameRegistry.registerItem((Item)itemCookedFlesh, (String)"ItemCookedFlesh");
        GameRegistry.registerItem((Item)itemHedgeBasic, (String)"ItemHedgeBasic");
        GameRegistry.registerItem((Item)itemHedgeSpruce, (String)"ItemHedgeSpruce");
        GameRegistry.registerItem((Item)itemHedgeBirch, (String)"ItemHedgeBirch");
        GameRegistry.registerItem((Item)itemHedgeJungle, (String)"ItemHedgeJungle");
        GameRegistry.registerItem((Item)itemBirdBath, (String)"ItemBirdBath");
        GameRegistry.registerItem((Item)itemStonePath, (String)"ItemStonePath");
        GameRegistry.registerItem((Item)itemWhiteFence, (String)"ItemWhiteFence");
        GameRegistry.registerItem((Item)itemTap, (String)"ItemTap");
        GameRegistry.registerItem((Item)itemMailBox, (String)"ItemMailBox");
        GameRegistry.registerItem((Item)itemHammer, (String)"ItemHammer");
        GameRegistry.registerItem((Item)itemEnvelope, (String)"ItemEnvelope");
        GameRegistry.registerItem((Item)itemEnvelopeSigned, (String)"ItemEnvelopeSigned");
        GameRegistry.registerItem((Item)itemPackage, (String)"ItemPackage");
        GameRegistry.registerItem((Item)itemPackageSigned, (String)"ItemPackageSigned");
        GameRegistry.registerItem((Item)itemTV, (String)"ItemTV");
        GameRegistry.registerItem((Item)itemComputer, (String)"ItemComputer");
        GameRegistry.registerItem((Item)itemPrinter, (String)"ItemPrinter");
        GameRegistry.registerItem((Item)itemInkCartridge, (String)"ItemInkCartridge");
        GameRegistry.registerItem((Item)itemStereo, (String)"ItemStereo");
        GameRegistry.registerItem((Item)itemElectricFence, (String)"ItemElectricFence");
        GameRegistry.registerItem((Item)itemCeilingLight, (String)"ItemCeilingLight");
        GameRegistry.registerItem((Item)itemDoorBell, (String)"ItemDoorBell");
        GameRegistry.registerItem((Item)itemFireAlarm, (String)"ItemFireAlarm");
        GameRegistry.registerItem((Item)itemLamp, (String)"ItemLamp");
        GameRegistry.registerItem((Item)itemToilet, (String)"ItemToilet");
        GameRegistry.registerItem((Item)itemBasin, (String)"ItemBasin");
        GameRegistry.registerItem((Item)itemWallCabinet, (String)"ItemWallCabinet");
        GameRegistry.registerItem((Item)itemBath, (String)"ItemBath");
        GameRegistry.registerItem((Item)itemShower, (String)"ItemShower");
        GameRegistry.registerItem((Item)itemShowerHead, (String)"ItemShowerHead");
        GameRegistry.registerItem((Item)itemBin, (String)"ItemBin");
        GameRegistry.registerItem((Item)itemPresentRed, (String)"ItemPresentRed");
        GameRegistry.registerItem((Item)itemPresentGreen, (String)"ItemPresentGreen");
        GameRegistry.registerItem((Item)itemTree, (String)"itemTree");
        GameRegistry.registerItem((Item)itemToaster, (String)"ItemToaster");
        GameRegistry.registerItem((Item)itemMicrowave, (String)"ItemMicrowave");
        GameRegistry.registerItem((Item)itemWashingMachine, (String)"ItemWashingMachine");
        GameRegistry.registerItem((Item)itemCookieJar, (String)"ItemCookieJar");
        GameRegistry.registerItem((Item)itemBlender, (String)"ItemBlender");
        GameRegistry.registerItem((Item)itemPlate, (String)"ItemPlate");
        GameRegistry.registerItem((Item)itemCounterDoored, (String)"ItemCounterDoored");
        GameRegistry.registerItem((Item)itemCounterSink, (String)"ItemCounterSink");
        GameRegistry.registerItem((Item)itemDishWasher, (String)"ItemDishWasher");
        GameRegistry.registerItem((Item)itemKitchenCabinet, (String)"ItemKitchenCabinet");
        GameRegistry.registerItem((Item)itemChoppingBoard, (String)"ItemChoppingBoard");
        GameRegistry.registerItem((Item)itemBarStool, (String)"ItemBarStall");
        GameRegistry.registerItem((Item)itemBreadSlice, (String)"ItemBreadSlice");
        GameRegistry.registerItem((Item)itemToast, (String)"ItemToast");
        GameRegistry.registerItem((Item)itemKnife, (String)"ItemKnife");
        GameRegistry.registerItem((Item)itemCup, (String)"ItemCup");
        GameRegistry.registerItem((Item)itemDrink, (String)"ItemDrink");
        GameRegistry.registerItem((Item)itemSoap, (String)"ItemSoap");
        GameRegistry.registerItem((Item)itemSoapyWater, (String)"ItemSoapyWater");
        GameRegistry.registerItem((Item)itemSuperSoapyWater, (String)"ItemSuperSoapyWater");
        GameRegistry.registerItem((Item)itemRecipeBook, (String)"ItemRecipeBook");
        GameRegistry.registerItem((Item)itemCrayfish, (String)"ItemCrayfish");
        GameRegistry.registerItem((Item)itemDollar, (String)"ItemDollar");
        FurnitureAchievements.loadAchievements();
        FurnitureAchievements.registerPage();
        PacketHandler.init();
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        proxy.registerRenders();
        GameRegistry.registerTileEntity(TileEntityOven.class, (String)"cfmOven");
        GameRegistry.registerTileEntity(TileEntityFridge.class, (String)"cfmFridge");
        GameRegistry.registerTileEntity(TileEntityCabinet.class, (String)"cfmCabinet");
        GameRegistry.registerTileEntity(TileEntityFreezer.class, (String)"cfmFreezer");
        GameRegistry.registerTileEntity(TileEntityBedsideCabinet.class, (String)"cfmBedsideCabinet");
        GameRegistry.registerTileEntity(TileEntityMailBox.class, (String)"cfmMailBox");
        GameRegistry.registerTileEntity(TileEntityComputer.class, (String)"cfmComputer");
        GameRegistry.registerTileEntity(TileEntityPrinter.class, (String)"cfmPrinter");
        GameRegistry.registerTileEntity(TileEntityTV.class, (String)"cfmTV");
        GameRegistry.registerTileEntity(TileEntityStereo.class, (String)"cfmStereo");
        GameRegistry.registerTileEntity(TileEntityPresent.class, (String)"cfmPresent");
        GameRegistry.registerTileEntity(TileEntityBin.class, (String)"cfmBin");
        GameRegistry.registerTileEntity(TileEntityWallCabinet.class, (String)"cfmWallCabinet");
        GameRegistry.registerTileEntity(TileEntityBath.class, (String)"cfmBath");
        GameRegistry.registerTileEntity(TileEntityBasin.class, (String)"cfmBasin");
        GameRegistry.registerTileEntity(TileEntityShowerHead.class, (String)"cfmShowerHead");
        GameRegistry.registerTileEntity(TileEntityCookieJar.class, (String)"cfmCookieJar");
        GameRegistry.registerTileEntity(TileEntityPlate.class, (String)"cfmPlate");
        GameRegistry.registerTileEntity(TileEntityCouch.class, (String)"cfmCouch");
        GameRegistry.registerTileEntity(TileEntityToaster.class, (String)"cfmToaster");
        GameRegistry.registerTileEntity(TileEntityChoppingBoard.class, (String)"cfmChoppingBoard");
        GameRegistry.registerTileEntity(TileEntityBlender.class, (String)"cfmBlender");
        GameRegistry.registerTileEntity(TileEntityMicrowave.class, (String)"cfmMicrowave");
        GameRegistry.registerTileEntity(TileEntityWashingMachine.class, (String)"cfmWashingMachine");
        GameRegistry.registerTileEntity(TileEntityDishwasher.class, (String)"cfmDishwasher");
        GameRegistry.registerTileEntity(TileEntityCabinetKitchen.class, (String)"cfmCabinetKitchen");
        GameRegistry.registerTileEntity(TileEntityCounterSink.class, (String)"cfmCounterSink");
        GameRegistry.registerTileEntity(TileEntityCup.class, (String)"cfmCup");
        EntityRegistry.registerModEntity(EntitySittableBlock.class, (String)"MountableBlock", (int)0, (Object)this, (int)80, (int)1, (boolean)false);
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)this, (IGuiHandler)this.guiHandler);
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemTableWood, 1), (Object[])new Object[]{"***", " * ", " * ", Character.valueOf('*'), Blocks.planks});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemTableStone, 1), (Object[])new Object[]{"***", " * ", " * ", Character.valueOf('*'), Blocks.cobblestone});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemChairWood, 1), (Object[])new Object[]{"*  ", "***", "* *", Character.valueOf('*'), Blocks.planks});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemChairStone, 1), (Object[])new Object[]{"*  ", "***", "* *", Character.valueOf('*'), Blocks.cobblestone});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemCouch, 1), (Object[])new Object[]{"***", "***", Character.valueOf('*'), new ItemStack(Blocks.wool, 1, 0)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemFridge, 1), (Object[])new Object[]{"***", "*#*", "*@*", Character.valueOf('*'), Blocks.iron_block, Character.valueOf('#'), Blocks.chest, Character.valueOf('@'), Blocks.furnace});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemCabinet, 1), (Object[])new Object[]{"***", "*@*", "***", Character.valueOf('*'), Blocks.planks, Character.valueOf('@'), Blocks.chest});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemCurtains, 2), (Object[])new Object[]{"@@@", "* *", "@ @", Character.valueOf('*'), Items.gold_ingot, Character.valueOf('@'), new ItemStack(Blocks.wool, 1, 14)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemBlinds, 2), (Object[])new Object[]{"***", "***", "***", Character.valueOf('*'), Items.stick});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemCoolPack, 2), (Object[])new Object[]{"***", "*@*", "***", Character.valueOf('*'), Blocks.glass, Character.valueOf('@'), Items.water_bucket});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemCoffeeTableWood, 1), (Object[])new Object[]{"***", "* *", Character.valueOf('*'), Blocks.planks});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemCoffeeTableStone, 1), (Object[])new Object[]{"***", "* *", Character.valueOf('*'), Blocks.cobblestone});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemLamp, 2), (Object[])new Object[]{"***", "*@*", " & ", Character.valueOf('*'), Blocks.wool, Character.valueOf('@'), Blocks.glowstone, Character.valueOf('&'), Blocks.obsidian});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemBedsideCabinet, 1), (Object[])new Object[]{"***", "*@*", "*@*", Character.valueOf('*'), Blocks.planks, Character.valueOf('@'), Blocks.chest});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemOven, 1), (Object[])new Object[]{"***", "*@*", "***", Character.valueOf('*'), Blocks.iron_block, Character.valueOf('@'), Blocks.furnace});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemOvenRangehood, 1), (Object[])new Object[]{" * ", " * ", "*@*", Character.valueOf('*'), Items.iron_ingot, Character.valueOf('@'), Blocks.glowstone});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemHedgeBasic, 4), (Object[])new Object[]{"***", "***", Character.valueOf('*'), new ItemStack((Block)Blocks.leaves, 1, 0)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemHedgeSpruce, 4), (Object[])new Object[]{"***", "***", Character.valueOf('*'), new ItemStack((Block)Blocks.leaves, 1, 1)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemHedgeBirch, 4), (Object[])new Object[]{"***", "***", Character.valueOf('*'), new ItemStack((Block)Blocks.leaves, 1, 2)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemHedgeJungle, 4), (Object[])new Object[]{"***", "***", Character.valueOf('*'), new ItemStack((Block)Blocks.leaves, 1, 3)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemBirdBath, 1), (Object[])new Object[]{"***", " * ", " * ", Character.valueOf('*'), Blocks.stone});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemStonePath, 8), (Object[])new Object[]{"**", Character.valueOf('*'), Blocks.cobblestone});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemTap, 1), (Object[])new Object[]{" @ ", "***", "  *", Character.valueOf('*'), Blocks.stone, Character.valueOf('@'), Items.iron_ingot});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemMailBox, 1), (Object[])new Object[]{"*@*", "***", " * ", Character.valueOf('*'), Blocks.planks, Character.valueOf('@'), Items.book});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemEnvelope, 1), (Object[])new Object[]{"**", Character.valueOf('*'), Items.paper});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemPackage, 1), (Object[])new Object[]{"***", "***", Character.valueOf('*'), Items.paper});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(itemDoorBell, 1), (Object[])new Object[]{Blocks.noteblock, Blocks.stone_button});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(itemWhiteFence, 2), (Object[])new Object[]{Blocks.fence, new ItemStack(Items.dye, 1, 15)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemComputer, 1), (Object[])new Object[]{"***", "*@*", "*&*", Character.valueOf('*'), Blocks.iron_block, Character.valueOf('@'), Blocks.glass_pane, Character.valueOf('&'), Items.redstone});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemPrinter, 1), (Object[])new Object[]{"*@*", "&R&", "***", Character.valueOf('*'), Blocks.stone, Character.valueOf('@'), Items.paper, Character.valueOf('&'), Blocks.iron_block, Character.valueOf('R'), Items.redstone});
        Object[] objectArray = new Object[9];
        objectArray[0] = "***";
        objectArray[1] = "*@*";
        objectArray[2] = "*#*";
        objectArray[3] = Character.valueOf('*');
        objectArray[4] = Items.iron_ingot;
        objectArray[5] = Character.valueOf('@');
        objectArray[6] = itemWhiteFence;
        objectArray[7] = Character.valueOf('#');
        objectArray[8] = Blocks.redstone_torch;
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemElectricFence, 8), (Object[])objectArray);
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemFireAlarm, 1), (Object[])new Object[]{"*#*", "*@*", Character.valueOf('*'), Items.iron_ingot, Character.valueOf('@'), Blocks.noteblock, Character.valueOf('#'), Items.redstone});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemTV, 1), (Object[])new Object[]{"***", "*@*", "*&*", Character.valueOf('*'), Blocks.log, Character.valueOf('@'), Blocks.glass_pane, Character.valueOf('&'), Items.redstone});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemStereo, 1), (Object[])new Object[]{" * ", "NJN", Character.valueOf('*'), Items.iron_ingot, Character.valueOf('N'), Blocks.noteblock, Character.valueOf('J'), Blocks.jukebox});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemCeilingLight, 4), (Object[])new Object[]{"O", "S", "G", Character.valueOf('O'), Blocks.obsidian, Character.valueOf('S'), Blocks.stone, Character.valueOf('G'), Blocks.glowstone});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemInkCartridge, 2), (Object[])new Object[]{"SSS", "SIS", "SSS", Character.valueOf('I'), new ItemStack(Items.dye, 1, 0), Character.valueOf('S'), Blocks.stone});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemTree, 1), (Object[])new Object[]{" L ", "LLL", " P ", Character.valueOf('L'), Blocks.leaves, Character.valueOf('P'), Items.flower_pot});
        Object[] objectArray2 = new Object[7];
        objectArray2[0] = "RRR";
        objectArray2[1] = "RPR";
        objectArray2[2] = "RRR";
        objectArray2[3] = Character.valueOf('R');
        objectArray2[4] = new ItemStack(Blocks.wool, 1, 14);
        objectArray2[5] = Character.valueOf('P');
        objectArray2[6] = itemPackage;
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemPresentRed, 1), (Object[])objectArray2);
        Object[] objectArray3 = new Object[7];
        objectArray3[0] = "GGG";
        objectArray3[1] = "GPG";
        objectArray3[2] = "GGG";
        objectArray3[3] = Character.valueOf('G');
        objectArray3[4] = new ItemStack(Blocks.wool, 1, 13);
        objectArray3[5] = Character.valueOf('P');
        objectArray3[6] = itemPackage;
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemPresentGreen, 1), (Object[])objectArray3);
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemToilet, 1), (Object[])new Object[]{"QB ", "QQQ", " Q ", Character.valueOf('Q'), Blocks.quartz_block, Character.valueOf('B'), Blocks.stone_button});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemBasin, 1), (Object[])new Object[]{"BIB", "QQQ", " Q ", Character.valueOf('Q'), Blocks.quartz_block, Character.valueOf('B'), Blocks.stone_button, Character.valueOf('I'), Items.iron_ingot});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemWallCabinet, 1), (Object[])new Object[]{"QQQ", "QCQ", "QQQ", Character.valueOf('Q'), Blocks.quartz_block, Character.valueOf('C'), Blocks.chest});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemShower, 1), (Object[])new Object[]{"QGQ", "QGQ", "QGQ", Character.valueOf('Q'), Blocks.quartz_block, Character.valueOf('G'), Blocks.glass});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemBin, 1), (Object[])new Object[]{"BSB", "I I", "III", Character.valueOf('B'), Blocks.heavy_weighted_pressure_plate, Character.valueOf('S'), Blocks.stone, Character.valueOf('I'), Items.iron_ingot});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemBath, 1), (Object[])new Object[]{"B  ", "Q Q", "QQQ", Character.valueOf('Q'), Blocks.quartz_block, Character.valueOf('B'), Blocks.stone_button});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemShowerHead, 1), (Object[])new Object[]{"II ", " I ", "SSS", Character.valueOf('S'), Blocks.stone, Character.valueOf('I'), Items.iron_ingot});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemShowerHead, 1), (Object[])new Object[]{"II ", " I ", "SSS", Character.valueOf('S'), Blocks.stone, Character.valueOf('I'), Items.iron_ingot});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(itemSoap), (Object[])new Object[]{Items.clay_ball, new ItemStack(Items.dye, 1, 15), new ItemStack(Items.dye, 1, 12)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(itemSoapyWater), (Object[])new Object[]{Items.water_bucket, itemSoap});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemSuperSoapyWater), (Object[])new Object[]{"GGG", "GSG", "GGG", Character.valueOf('G'), Items.gold_ingot, Character.valueOf('S'), itemSoapyWater});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemToaster), (Object[])new Object[]{"QBQ", "QPS", "WWW", Character.valueOf('Q'), Blocks.quartz_block, Character.valueOf('B'), Blocks.iron_bars, Character.valueOf('S'), Blocks.stone, Character.valueOf('P'), Blocks.piston, Character.valueOf('W'), new ItemStack(Blocks.wool, 1, 15)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemMicrowave), (Object[])new Object[]{"IIQ", "GGB", "IIQ", Character.valueOf('I'), Items.iron_ingot, Character.valueOf('Q'), Blocks.quartz_block, Character.valueOf('G'), Blocks.glass_pane, Character.valueOf('B'), Blocks.stone_button});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemBlender), (Object[])new Object[]{"GBG", "GIG", "BBB", Character.valueOf('G'), Blocks.glass_pane, Character.valueOf('B'), new ItemStack(Blocks.wool, 1, 15), Character.valueOf('I'), Items.iron_ingot});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemWashingMachine), (Object[])new Object[]{"QQQ", "QGQ", "QFQ", Character.valueOf('Q'), Blocks.quartz_block, Character.valueOf('G'), Blocks.glass_pane, Character.valueOf('F'), Blocks.furnace});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemDishWasher), (Object[])new Object[]{"QQQ", "CBC", "CFC", Character.valueOf('Q'), Blocks.quartz_block, Character.valueOf('G'), Blocks.glass_pane, Character.valueOf('F'), Blocks.furnace, Character.valueOf('C'), new ItemStack(Blocks.stained_hardened_clay, 1, 9), Character.valueOf('B'), Blocks.iron_bars});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemCounterDoored), (Object[])new Object[]{"CCC", "QQQ", "QQQ", Character.valueOf('C'), new ItemStack(Blocks.stained_hardened_clay, 1, 9), Character.valueOf('Q'), Blocks.quartz_block});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemCounterSink), (Object[])new Object[]{"CSC", "QQQ", "QQQ", Character.valueOf('C'), new ItemStack(Blocks.stained_hardened_clay, 1, 9), Character.valueOf('S'), itemBasin, Character.valueOf('Q'), Blocks.quartz_block});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemKitchenCabinet), (Object[])new Object[]{"QQQ", "HCH", "QQQ", Character.valueOf('Q'), Blocks.quartz_block, Character.valueOf('C'), Blocks.chest, Character.valueOf('H'), new ItemStack(Blocks.stained_hardened_clay, 1, 9)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemPlate), (Object[])new Object[]{"Q Q", " Q ", Character.valueOf('Q'), Blocks.quartz_block});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemCookieJar), (Object[])new Object[]{" W ", "G G", "GGG", Character.valueOf('W'), new ItemStack(Blocks.wool, 1, 15), Character.valueOf('G'), Blocks.glass_pane});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemBarStool), (Object[])new Object[]{"WWW", "CCC", "Q Q", Character.valueOf('W'), new ItemStack(Blocks.wool, 1, 0), Character.valueOf('C'), new ItemStack(Blocks.stained_hardened_clay, 1, 9), Character.valueOf('Q'), Blocks.quartz_block});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemChoppingBoard), (Object[])new Object[]{"LLL", "SSS", "LLL", Character.valueOf('L'), new ItemStack(Blocks.log, 1, 0), Character.valueOf('S'), new ItemStack((Block)Blocks.wooden_slab, 1, 0)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemKnife), (Object[])new Object[]{"I ", " S", Character.valueOf('I'), Blocks.stone, Character.valueOf('S'), Items.stick});
        GameRegistry.addRecipe((ItemStack)new ItemStack(itemCup), (Object[])new Object[]{"G G", "G G", "GGG", Character.valueOf('G'), Blocks.glass_pane});
        GameRegistry.addSmelting((Item)itemFlesh, (ItemStack)new ItemStack(itemCookedFlesh), (float)0.05f);
        FMLCommonHandler.instance().bus().register((Object)new CraftingHandler());
        FMLCommonHandler.instance().bus().register((Object)new PlayerEvents());
        if (event.getSide() == Side.CLIENT) {
            this.keyHandler = new InputHandler();
            FMLCommonHandler.instance().bus().register((Object)this.keyHandler);
        } else {
            FMLCommonHandler.instance().bus().register((Object)new SyncEvent());
        }
        FMLInterModComms.sendMessage((String)"Waila", (String)"register", (String)"com.mrcrayfish.furniture.util.WailaIconRegister.callbackRegister");
    }

    @Mod.EventHandler
    public void loadComplete(FMLPostInitializationEvent event) {
        RecipeRegistry.registerDefaultRecipes();
        RecipeRegistry.registerConfigRecipes();
        Recipes.addCommRecipesToLocal();
        Recipes.updateDataList();
    }

    @Mod.EventHandler
    public void processIMC(FMLInterModComms.IMCEvent event) {
        if (event.getMessages().size() > 0 && ConfigurationHandler.api_debug) {
            System.out.println("RecipeAPI (InterModComm): Registering recipes from " + event.getMessages().size() + " mod(s).");
        }
        for (FMLInterModComms.IMCMessage imcMessage : event.getMessages()) {
            if (!imcMessage.isStringMessage() || !imcMessage.key.equalsIgnoreCase("register")) continue;
            this.register(imcMessage.getStringValue(), imcMessage.getSender());
        }
    }

    public void register(String method, String modid) {
        String[] data = method.split("\\.");
        String methodName = data[data.length - 1];
        String className = method.substring(0, method.length() - methodName.length() - 1);
        String modName = ((ModContainer)Loader.instance().getIndexedModList().get(modid)).getName();
        try {
            Class<?> clazz = Class.forName(className);
            Method registerMethod = clazz.getDeclaredMethod(methodName, IRecipeRegistry.class);
            registerMethod.invoke(null, RecipeRegistryComm.getInstance(modName));
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static {
        hasDisplayedOnce = false;
        tabFurniture = new FurnitureTab(CreativeTabs.getNextID(), "tabFurniture");
    }
}

