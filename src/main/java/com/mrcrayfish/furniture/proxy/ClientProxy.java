/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.player.EntityPlayer
 */
package com.mrcrayfish.furniture.proxy;

import com.mrcrayfish.furniture.client.render.RenderBarStool;
import com.mrcrayfish.furniture.client.render.RenderBasin;
import com.mrcrayfish.furniture.client.render.RenderBath;
import com.mrcrayfish.furniture.client.render.RenderBedsideCabinet;
import com.mrcrayfish.furniture.client.render.RenderBin;
import com.mrcrayfish.furniture.client.render.RenderBirdBath;
import com.mrcrayfish.furniture.client.render.RenderBlender;
import com.mrcrayfish.furniture.client.render.RenderCabinet;
import com.mrcrayfish.furniture.client.render.RenderCabinetKitchen;
import com.mrcrayfish.furniture.client.render.RenderCeilingLight;
import com.mrcrayfish.furniture.client.render.RenderChair;
import com.mrcrayfish.furniture.client.render.RenderChoppingBoard;
import com.mrcrayfish.furniture.client.render.RenderCoffeeTable;
import com.mrcrayfish.furniture.client.render.RenderComputer;
import com.mrcrayfish.furniture.client.render.RenderCookieJar;
import com.mrcrayfish.furniture.client.render.RenderCouch;
import com.mrcrayfish.furniture.client.render.RenderCounter;
import com.mrcrayfish.furniture.client.render.RenderCounterSink;
import com.mrcrayfish.furniture.client.render.RenderCup;
import com.mrcrayfish.furniture.client.render.RenderDishWasher;
import com.mrcrayfish.furniture.client.render.RenderDoorBell;
import com.mrcrayfish.furniture.client.render.RenderElectricFence;
import com.mrcrayfish.furniture.client.render.RenderFireAlarm;
import com.mrcrayfish.furniture.client.render.RenderFridge;
import com.mrcrayfish.furniture.client.render.RenderHedge;
import com.mrcrayfish.furniture.client.render.RenderLamp;
import com.mrcrayfish.furniture.client.render.RenderMailBox;
import com.mrcrayfish.furniture.client.render.RenderMicrowave;
import com.mrcrayfish.furniture.client.render.RenderOven;
import com.mrcrayfish.furniture.client.render.RenderOvenOverhead;
import com.mrcrayfish.furniture.client.render.RenderPlate;
import com.mrcrayfish.furniture.client.render.RenderPresent;
import com.mrcrayfish.furniture.client.render.RenderPrinter;
import com.mrcrayfish.furniture.client.render.RenderShower;
import com.mrcrayfish.furniture.client.render.RenderShowerHead;
import com.mrcrayfish.furniture.client.render.RenderStereo;
import com.mrcrayfish.furniture.client.render.RenderStonePath;
import com.mrcrayfish.furniture.client.render.RenderTV;
import com.mrcrayfish.furniture.client.render.RenderTable;
import com.mrcrayfish.furniture.client.render.RenderTap;
import com.mrcrayfish.furniture.client.render.RenderToaster;
import com.mrcrayfish.furniture.client.render.RenderToilet;
import com.mrcrayfish.furniture.client.render.RenderTree;
import com.mrcrayfish.furniture.client.render.RenderWallCabinet;
import com.mrcrayfish.furniture.client.render.RenderWashingMachine;
import com.mrcrayfish.furniture.client.render.RenderWhiteFence;
import com.mrcrayfish.furniture.client.render.RenderWindowDecoration;
import com.mrcrayfish.furniture.client.render.tileentity.BlenderRenderer;
import com.mrcrayfish.furniture.client.render.tileentity.ChoppingBoardRenderer;
import com.mrcrayfish.furniture.client.render.tileentity.CookieRenderer;
import com.mrcrayfish.furniture.client.render.tileentity.CupRenderer;
import com.mrcrayfish.furniture.client.render.tileentity.MicrowaveRenderer;
import com.mrcrayfish.furniture.client.render.tileentity.PlateRenderer;
import com.mrcrayfish.furniture.client.render.tileentity.ToastRenderer;
import com.mrcrayfish.furniture.client.render.tileentity.WashingMachineRenderer;
import com.mrcrayfish.furniture.proxy.CommonProxy;
import com.mrcrayfish.furniture.tileentity.TileEntityBlender;
import com.mrcrayfish.furniture.tileentity.TileEntityChoppingBoard;
import com.mrcrayfish.furniture.tileentity.TileEntityCookieJar;
import com.mrcrayfish.furniture.tileentity.TileEntityCup;
import com.mrcrayfish.furniture.tileentity.TileEntityMicrowave;
import com.mrcrayfish.furniture.tileentity.TileEntityPlate;
import com.mrcrayfish.furniture.tileentity.TileEntityToaster;
import com.mrcrayfish.furniture.tileentity.TileEntityWashingMachine;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;

public class ClientProxy
extends CommonProxy {
    public static int renderPass;

    @Override
    public void registerRenders() {
        renderTable = RenderingRegistry.getNextAvailableRenderId();
        renderChair = RenderingRegistry.getNextAvailableRenderId();
        renderCouch = RenderingRegistry.getNextAvailableRenderId();
        renderWindowDecoration = RenderingRegistry.getNextAvailableRenderId();
        renderCoffeeTable = RenderingRegistry.getNextAvailableRenderId();
        renderCarpet = RenderingRegistry.getNextAvailableRenderId();
        renderFridge = RenderingRegistry.getNextAvailableRenderId();
        renderCabinet = RenderingRegistry.getNextAvailableRenderId();
        renderLamp = RenderingRegistry.getNextAvailableRenderId();
        renderBedsideCabinet = RenderingRegistry.getNextAvailableRenderId();
        renderOven = RenderingRegistry.getNextAvailableRenderId();
        renderOvenOverhead = RenderingRegistry.getNextAvailableRenderId();
        renderHedge = RenderingRegistry.getNextAvailableRenderId();
        renderBirdBath = RenderingRegistry.getNextAvailableRenderId();
        renderStonePath = RenderingRegistry.getNextAvailableRenderId();
        renderWhiteFence = RenderingRegistry.getNextAvailableRenderId();
        renderTap = RenderingRegistry.getNextAvailableRenderId();
        renderMailBox = RenderingRegistry.getNextAvailableRenderId();
        renderTV = RenderingRegistry.getNextAvailableRenderId();
        renderComputer = RenderingRegistry.getNextAvailableRenderId();
        renderPrinter = RenderingRegistry.getNextAvailableRenderId();
        renderStereo = RenderingRegistry.getNextAvailableRenderId();
        renderCeilingLight = RenderingRegistry.getNextAvailableRenderId();
        renderDoorBell = RenderingRegistry.getNextAvailableRenderId();
        renderFireAlarm = RenderingRegistry.getNextAvailableRenderId();
        renderElectricFence = RenderingRegistry.getNextAvailableRenderId();
        renderToilet = RenderingRegistry.getNextAvailableRenderId();
        renderPresent = RenderingRegistry.getNextAvailableRenderId();
        renderTree = RenderingRegistry.getNextAvailableRenderId();
        renderBasin = RenderingRegistry.getNextAvailableRenderId();
        renderShower = RenderingRegistry.getNextAvailableRenderId();
        renderShowerHead = RenderingRegistry.getNextAvailableRenderId();
        renderBath = RenderingRegistry.getNextAvailableRenderId();
        renderWallCabinet = RenderingRegistry.getNextAvailableRenderId();
        renderBin = RenderingRegistry.getNextAvailableRenderId();
        renderToaster = RenderingRegistry.getNextAvailableRenderId();
        renderMicrowave = RenderingRegistry.getNextAvailableRenderId();
        renderWashingMachine = RenderingRegistry.getNextAvailableRenderId();
        renderCookieJar = RenderingRegistry.getNextAvailableRenderId();
        renderBlender = RenderingRegistry.getNextAvailableRenderId();
        renderPlate = RenderingRegistry.getNextAvailableRenderId();
        renderCup = RenderingRegistry.getNextAvailableRenderId();
        renderCounter = RenderingRegistry.getNextAvailableRenderId();
        renderCounterSink = RenderingRegistry.getNextAvailableRenderId();
        renderDishWasher = RenderingRegistry.getNextAvailableRenderId();
        renderKitchenCabinet = RenderingRegistry.getNextAvailableRenderId();
        renderChoppingBoard = RenderingRegistry.getNextAvailableRenderId();
        renderBarStool = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((int)renderTable, (ISimpleBlockRenderingHandler)new RenderTable());
        RenderingRegistry.registerBlockHandler((int)renderChair, (ISimpleBlockRenderingHandler)new RenderChair());
        RenderingRegistry.registerBlockHandler((int)renderCouch, (ISimpleBlockRenderingHandler)new RenderCouch());
        RenderingRegistry.registerBlockHandler((int)renderWindowDecoration, (ISimpleBlockRenderingHandler)new RenderWindowDecoration());
        RenderingRegistry.registerBlockHandler((int)renderCoffeeTable, (ISimpleBlockRenderingHandler)new RenderCoffeeTable());
        RenderingRegistry.registerBlockHandler((int)renderFridge, (ISimpleBlockRenderingHandler)new RenderFridge());
        RenderingRegistry.registerBlockHandler((int)renderCabinet, (ISimpleBlockRenderingHandler)new RenderCabinet());
        RenderingRegistry.registerBlockHandler((int)renderLamp, (ISimpleBlockRenderingHandler)new RenderLamp());
        RenderingRegistry.registerBlockHandler((int)renderBedsideCabinet, (ISimpleBlockRenderingHandler)new RenderBedsideCabinet());
        RenderingRegistry.registerBlockHandler((int)renderOven, (ISimpleBlockRenderingHandler)new RenderOven());
        RenderingRegistry.registerBlockHandler((int)renderOvenOverhead, (ISimpleBlockRenderingHandler)new RenderOvenOverhead());
        RenderingRegistry.registerBlockHandler((int)renderHedge, (ISimpleBlockRenderingHandler)new RenderHedge());
        RenderingRegistry.registerBlockHandler((int)renderBirdBath, (ISimpleBlockRenderingHandler)new RenderBirdBath());
        RenderingRegistry.registerBlockHandler((int)renderStonePath, (ISimpleBlockRenderingHandler)new RenderStonePath());
        RenderingRegistry.registerBlockHandler((int)renderWhiteFence, (ISimpleBlockRenderingHandler)new RenderWhiteFence());
        RenderingRegistry.registerBlockHandler((int)renderTap, (ISimpleBlockRenderingHandler)new RenderTap());
        RenderingRegistry.registerBlockHandler((int)renderMailBox, (ISimpleBlockRenderingHandler)new RenderMailBox());
        RenderingRegistry.registerBlockHandler((int)renderTV, (ISimpleBlockRenderingHandler)new RenderTV());
        RenderingRegistry.registerBlockHandler((int)renderComputer, (ISimpleBlockRenderingHandler)new RenderComputer());
        RenderingRegistry.registerBlockHandler((int)renderPrinter, (ISimpleBlockRenderingHandler)new RenderPrinter());
        RenderingRegistry.registerBlockHandler((int)renderStereo, (ISimpleBlockRenderingHandler)new RenderStereo());
        RenderingRegistry.registerBlockHandler((int)renderCeilingLight, (ISimpleBlockRenderingHandler)new RenderCeilingLight());
        RenderingRegistry.registerBlockHandler((int)renderDoorBell, (ISimpleBlockRenderingHandler)new RenderDoorBell());
        RenderingRegistry.registerBlockHandler((int)renderFireAlarm, (ISimpleBlockRenderingHandler)new RenderFireAlarm());
        RenderingRegistry.registerBlockHandler((int)renderElectricFence, (ISimpleBlockRenderingHandler)new RenderElectricFence());
        RenderingRegistry.registerBlockHandler((int)renderToilet, (ISimpleBlockRenderingHandler)new RenderToilet());
        RenderingRegistry.registerBlockHandler((int)renderPresent, (ISimpleBlockRenderingHandler)new RenderPresent());
        RenderingRegistry.registerBlockHandler((int)renderTree, (ISimpleBlockRenderingHandler)new RenderTree());
        RenderingRegistry.registerBlockHandler((int)renderBasin, (ISimpleBlockRenderingHandler)new RenderBasin());
        RenderingRegistry.registerBlockHandler((int)renderShower, (ISimpleBlockRenderingHandler)new RenderShower());
        RenderingRegistry.registerBlockHandler((int)renderShowerHead, (ISimpleBlockRenderingHandler)new RenderShowerHead());
        RenderingRegistry.registerBlockHandler((int)renderBath, (ISimpleBlockRenderingHandler)new RenderBath());
        RenderingRegistry.registerBlockHandler((int)renderWallCabinet, (ISimpleBlockRenderingHandler)new RenderWallCabinet());
        RenderingRegistry.registerBlockHandler((int)renderBin, (ISimpleBlockRenderingHandler)new RenderBin());
        RenderingRegistry.registerBlockHandler((int)renderToaster, (ISimpleBlockRenderingHandler)new RenderToaster());
        RenderingRegistry.registerBlockHandler((int)renderMicrowave, (ISimpleBlockRenderingHandler)new RenderMicrowave());
        RenderingRegistry.registerBlockHandler((int)renderWashingMachine, (ISimpleBlockRenderingHandler)new RenderWashingMachine());
        RenderingRegistry.registerBlockHandler((int)renderCookieJar, (ISimpleBlockRenderingHandler)new RenderCookieJar());
        RenderingRegistry.registerBlockHandler((int)renderBlender, (ISimpleBlockRenderingHandler)new RenderBlender());
        RenderingRegistry.registerBlockHandler((int)renderPlate, (ISimpleBlockRenderingHandler)new RenderPlate());
        RenderingRegistry.registerBlockHandler((int)renderCup, (ISimpleBlockRenderingHandler)new RenderCup());
        RenderingRegistry.registerBlockHandler((int)renderCounter, (ISimpleBlockRenderingHandler)new RenderCounter());
        RenderingRegistry.registerBlockHandler((int)renderCounterSink, (ISimpleBlockRenderingHandler)new RenderCounterSink());
        RenderingRegistry.registerBlockHandler((int)renderDishWasher, (ISimpleBlockRenderingHandler)new RenderDishWasher());
        RenderingRegistry.registerBlockHandler((int)renderKitchenCabinet, (ISimpleBlockRenderingHandler)new RenderCabinetKitchen());
        RenderingRegistry.registerBlockHandler((int)renderChoppingBoard, (ISimpleBlockRenderingHandler)new RenderChoppingBoard());
        RenderingRegistry.registerBlockHandler((int)renderBarStool, (ISimpleBlockRenderingHandler)new RenderBarStool());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCookieJar.class, (TileEntitySpecialRenderer)new CookieRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlate.class, (TileEntitySpecialRenderer)new PlateRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityToaster.class, (TileEntitySpecialRenderer)new ToastRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChoppingBoard.class, (TileEntitySpecialRenderer)new ChoppingBoardRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlender.class, (TileEntitySpecialRenderer)new BlenderRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMicrowave.class, (TileEntitySpecialRenderer)new MicrowaveRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWashingMachine.class, (TileEntitySpecialRenderer)new WashingMachineRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCup.class, (TileEntitySpecialRenderer)new CupRenderer());
    }

    @Override
    public EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    @Override
    public boolean isSinglePlayer() {
        return Minecraft.getMinecraft().isSingleplayer();
    }

    @Override
    public boolean isDedicatedServer() {
        return false;
    }
}

