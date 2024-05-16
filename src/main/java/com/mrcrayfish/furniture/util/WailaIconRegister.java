///*
// * Decompiled with CFR 0.152.
// *
// * Could not load the following classes:
// *  mcp.mobius.waila.api.IWailaConfigHandler
// *  mcp.mobius.waila.api.IWailaDataAccessor
// *  mcp.mobius.waila.api.IWailaDataProvider
// *  mcp.mobius.waila.api.IWailaRegistrar
// *  net.minecraft.item.ItemStack
// */
//package com.mrcrayfish.furniture.util;
//
//import com.mrcrayfish.furniture.blocks.BlockBirdBath;
//import com.mrcrayfish.furniture.blocks.BlockCeilingLight;
//import com.mrcrayfish.furniture.blocks.BlockChair;
//import com.mrcrayfish.furniture.blocks.BlockCoffeeTable;
//import com.mrcrayfish.furniture.blocks.BlockCouch;
//import com.mrcrayfish.furniture.blocks.BlockDoorBell;
//import com.mrcrayfish.furniture.blocks.BlockElectricFence;
//import com.mrcrayfish.furniture.blocks.BlockFireAlarm;
//import com.mrcrayfish.furniture.blocks.BlockHedge;
//import com.mrcrayfish.furniture.blocks.BlockLampOff;
//import com.mrcrayfish.furniture.blocks.BlockLampOn;
//import com.mrcrayfish.furniture.blocks.BlockOvenOverhead;
//import com.mrcrayfish.furniture.blocks.BlockPresent;
//import com.mrcrayfish.furniture.blocks.BlockShower;
//import com.mrcrayfish.furniture.blocks.BlockShowerHead;
//import com.mrcrayfish.furniture.blocks.BlockStonePath;
//import com.mrcrayfish.furniture.blocks.BlockTable;
//import com.mrcrayfish.furniture.blocks.BlockTap;
//import com.mrcrayfish.furniture.blocks.BlockToilet;
//import com.mrcrayfish.furniture.blocks.BlockTree;
//import com.mrcrayfish.furniture.blocks.BlockWhiteFence;
//import com.mrcrayfish.furniture.blocks.BlockWindowDecoration;
//import com.mrcrayfish.furniture.blocks.BlockWindowDecorationClosed;
//import java.util.List;
//import mcp.mobius.waila.api.IWailaConfigHandler;
//import mcp.mobius.waila.api.IWailaDataAccessor;
//import mcp.mobius.waila.api.IWailaDataProvider;
//import mcp.mobius.waila.api.IWailaRegistrar;
//import net.minecraft.item.ItemStack;
//
//public class WailaIconRegister
//implements IWailaDataProvider {
//    public static void callbackRegister(IWailaRegistrar registrar) {
//        registrar.registerStackProvider((IWailaDataProvider)new WailaIconRegister(), BlockLampOn.class);
//        registrar.registerStackProvider((IWailaDataProvider)new WailaIconRegister(), BlockLampOff.class);
//        registrar.registerStackProvider((IWailaDataProvider)new WailaIconRegister(), BlockCoffeeTable.class);
//        registrar.registerStackProvider((IWailaDataProvider)new WailaIconRegister(), BlockTable.class);
//        registrar.registerStackProvider((IWailaDataProvider)new WailaIconRegister(), BlockChair.class);
//        registrar.registerStackProvider((IWailaDataProvider)new WailaIconRegister(), BlockCouch.class);
//        registrar.registerStackProvider((IWailaDataProvider)new WailaIconRegister(), BlockWindowDecoration.class);
//        registrar.registerStackProvider((IWailaDataProvider)new WailaIconRegister(), BlockWindowDecorationClosed.class);
//        registrar.registerStackProvider((IWailaDataProvider)new WailaIconRegister(), BlockOvenOverhead.class);
//        registrar.registerStackProvider((IWailaDataProvider)new WailaIconRegister(), BlockHedge.class);
//        registrar.registerStackProvider((IWailaDataProvider)new WailaIconRegister(), BlockBirdBath.class);
//        registrar.registerStackProvider((IWailaDataProvider)new WailaIconRegister(), BlockStonePath.class);
//        registrar.registerStackProvider((IWailaDataProvider)new WailaIconRegister(), BlockWhiteFence.class);
//        registrar.registerStackProvider((IWailaDataProvider)new WailaIconRegister(), BlockTap.class);
//        registrar.registerStackProvider((IWailaDataProvider)new WailaIconRegister(), BlockElectricFence.class);
//        registrar.registerStackProvider((IWailaDataProvider)new WailaIconRegister(), BlockDoorBell.class);
//        registrar.registerStackProvider((IWailaDataProvider)new WailaIconRegister(), BlockFireAlarm.class);
//        registrar.registerStackProvider((IWailaDataProvider)new WailaIconRegister(), BlockCeilingLight.class);
//        registrar.registerStackProvider((IWailaDataProvider)new WailaIconRegister(), BlockTree.class);
//        registrar.registerStackProvider((IWailaDataProvider)new WailaIconRegister(), BlockPresent.class);
//        registrar.registerStackProvider((IWailaDataProvider)new WailaIconRegister(), BlockPresent.class);
//        registrar.registerStackProvider((IWailaDataProvider)new WailaIconRegister(), BlockPresent.class);
//        registrar.registerStackProvider((IWailaDataProvider)new WailaIconRegister(), BlockToilet.class);
//        registrar.registerStackProvider((IWailaDataProvider)new WailaIconRegister(), BlockShower.class);
//        registrar.registerStackProvider((IWailaDataProvider)new WailaIconRegister(), BlockShowerHead.class);
//    }
//
//    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
//        return new ItemStack(accessor.getBlock().getPickBlock(null, accessor.getWorld(), accessor.getPosition().blockX, accessor.getPosition().blockY, accessor.getPosition().blockZ).getItem());
//    }
//
//    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
//        return currenttip;
//    }
//
//    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
//        return currenttip;
//    }
//
//    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
//        return currenttip;
//    }
//}
//
