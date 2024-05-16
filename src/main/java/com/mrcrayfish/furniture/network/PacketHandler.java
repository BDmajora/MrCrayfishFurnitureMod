/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  cpw.mods.fml.relauncher.Side
 */
package com.mrcrayfish.furniture.network;

import com.mrcrayfish.furniture.network.message.MessageAchievement;
import com.mrcrayfish.furniture.network.message.MessageConfig;
import com.mrcrayfish.furniture.network.message.MessageDishwasher;
import com.mrcrayfish.furniture.network.message.MessageEmptyBin;
import com.mrcrayfish.furniture.network.message.MessageEnvelope;
import com.mrcrayfish.furniture.network.message.MessageFart;
import com.mrcrayfish.furniture.network.message.MessageFillBasin;
import com.mrcrayfish.furniture.network.message.MessageFillBath;
import com.mrcrayfish.furniture.network.message.MessageFillSink;
import com.mrcrayfish.furniture.network.message.MessageMicrowave;
import com.mrcrayfish.furniture.network.message.MessageMineBayBrowse;
import com.mrcrayfish.furniture.network.message.MessageMineBayBuy;
import com.mrcrayfish.furniture.network.message.MessageMineBayClosed;
import com.mrcrayfish.furniture.network.message.MessagePackage;
import com.mrcrayfish.furniture.network.message.MessagePresent;
import com.mrcrayfish.furniture.network.message.MessagePresentContents;
import com.mrcrayfish.furniture.network.message.MessageTVClient;
import com.mrcrayfish.furniture.network.message.MessageTVServer;
import com.mrcrayfish.furniture.network.message.MessageTakeWater;
import com.mrcrayfish.furniture.network.message.MessageWashingMachine;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("cfm");

    public static void init() {
        INSTANCE.registerMessage(MessageAchievement.class, MessageAchievement.class, 0, Side.SERVER);
        INSTANCE.registerMessage(MessageConfig.class, MessageConfig.class, 1, Side.CLIENT);
        INSTANCE.registerMessage(MessageEmptyBin.class, MessageEmptyBin.class, 2, Side.SERVER);
        INSTANCE.registerMessage(MessageEnvelope.class, MessageEnvelope.class, 3, Side.SERVER);
        INSTANCE.registerMessage(MessageFart.class, MessageFart.class, 4, Side.SERVER);
        INSTANCE.registerMessage(MessageFillBasin.class, MessageFillBasin.class, 5, Side.CLIENT);
        INSTANCE.registerMessage(MessageFillBath.class, MessageFillBath.class, 6, Side.CLIENT);
        INSTANCE.registerMessage(MessageMineBayBrowse.class, MessageMineBayBrowse.class, 7, Side.SERVER);
        INSTANCE.registerMessage(MessageMineBayBuy.class, MessageMineBayBuy.class, 8, Side.SERVER);
        INSTANCE.registerMessage(MessageMineBayClosed.class, MessageMineBayClosed.class, 9, Side.SERVER);
        INSTANCE.registerMessage(MessagePackage.class, MessagePackage.class, 10, Side.SERVER);
        INSTANCE.registerMessage(MessagePresent.class, MessagePresent.class, 11, Side.SERVER);
        INSTANCE.registerMessage(MessagePresentContents.class, MessagePresentContents.class, 12, Side.SERVER);
        INSTANCE.registerMessage(MessageTakeWater.class, MessageTakeWater.class, 14, Side.SERVER);
        INSTANCE.registerMessage(MessageTVClient.class, MessageTVClient.class, 15, Side.CLIENT);
        INSTANCE.registerMessage(MessageTVServer.class, MessageTVServer.class, 16, Side.SERVER);
        INSTANCE.registerMessage(MessageMicrowave.class, MessageMicrowave.class, 17, Side.SERVER);
        INSTANCE.registerMessage(MessageWashingMachine.class, MessageWashingMachine.class, 18, Side.SERVER);
        INSTANCE.registerMessage(MessageDishwasher.class, MessageDishwasher.class, 19, Side.SERVER);
        INSTANCE.registerMessage(MessageFillSink.class, MessageFillSink.class, 20, Side.CLIENT);
    }
}

