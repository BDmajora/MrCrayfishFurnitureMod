/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.InputEvent$KeyInputEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.settings.KeyBinding
 */
package com.mrcrayfish.furniture.handler;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.network.PacketHandler;
import com.mrcrayfish.furniture.network.message.MessageFart;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.settings.KeyBinding;

public class InputHandler {
    public static KeyBinding key_fart;
    public static boolean keyPressed;
    public static boolean keyHasBeenPressed;
    private Random rand = new Random();

    public InputHandler() {
        key_fart = new KeyBinding("key.fart.desc", 33, "keys.cfm.category");
        ClientRegistry.registerKeyBinding((KeyBinding)key_fart);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (key_fart.isPressed()) {
            keyPressed = true;
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.player.worldObj.getBlock((int)Math.floor(event.player.posX), (int)Math.floor(event.player.posY - 1.0), (int)Math.floor(event.player.posZ)) == MrCrayfishFurnitureMod.toilet && keyPressed) {
            keyPressed = false;
            PacketHandler.INSTANCE.sendToServer((IMessage)new MessageFart(event.player.posX, event.player.posY, event.player.posZ));
        }
    }

    public KeyBinding getFartKey() {
        return key_fart;
    }

    static {
        keyPressed = false;
        keyHasBeenPressed = false;
    }
}

