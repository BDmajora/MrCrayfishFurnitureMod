/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.proxy;

import com.mrcrayfish.furniture.proxy.ProxyInterface;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class CommonProxy
implements ProxyInterface {
    public static int renderTable;
    public static int renderChair;
    public static int renderCouch;
    public static int renderWindowDecoration;
    public static int renderCoffeeTable;
    public static int renderCarpet;
    public static int renderFridge;
    public static int renderCabinet;
    public static int renderLamp;
    public static int renderBedsideCabinet;
    public static int renderOven;
    public static int renderOvenOverhead;
    public static int renderHedge;
    public static int renderBirdBath;
    public static int renderStonePath;
    public static int renderWhiteFence;
    public static int renderTap;
    public static int renderMailBox;
    public static int renderTV;
    public static int renderComputer;
    public static int renderPrinter;
    public static int renderStereo;
    public static int renderFireAlarm;
    public static int renderCeilingLight;
    public static int renderDoorBell;
    public static int renderElectricFence;
    public static int renderToilet;
    public static int renderPresent;
    public static int renderTree;
    public static int renderBasin;
    public static int renderShower;
    public static int renderShowerHead;
    public static int renderBath;
    public static int renderWallCabinet;
    public static int renderBin;
    public static int renderToaster;
    public static int renderMicrowave;
    public static int renderWashingMachine;
    public static int renderCookieJar;
    public static int renderBlender;
    public static int renderPlate;
    public static int renderCup;
    public static int renderCounter;
    public static int renderCounterSink;
    public static int renderDishWasher;
    public static int renderKitchenCabinet;
    public static int renderChoppingBoard;
    public static int renderBarStool;

    public void registerRenders() {
    }

    public World getClientWorld() {
        return null;
    }

    public EntityPlayer getClientPlayer() {
        return null;
    }

    @Override
    public boolean isSinglePlayer() {
        return false;
    }

    @Override
    public boolean isDedicatedServer() {
        return MinecraftServer.getServer().isDedicatedServer();
    }
}

