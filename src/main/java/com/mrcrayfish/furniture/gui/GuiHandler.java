/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.IGuiHandler
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.gui;

import com.mrcrayfish.furniture.gui.GuiBedsideCabinet;
import com.mrcrayfish.furniture.gui.GuiBin;
import com.mrcrayfish.furniture.gui.GuiCabinet;
import com.mrcrayfish.furniture.gui.GuiComputer;
import com.mrcrayfish.furniture.gui.GuiDishwasher;
import com.mrcrayfish.furniture.gui.GuiEnvelope;
import com.mrcrayfish.furniture.gui.GuiFreezer;
import com.mrcrayfish.furniture.gui.GuiFridge;
import com.mrcrayfish.furniture.gui.GuiKitchenCabinet;
import com.mrcrayfish.furniture.gui.GuiMailBox;
import com.mrcrayfish.furniture.gui.GuiMicrowave;
import com.mrcrayfish.furniture.gui.GuiOven;
import com.mrcrayfish.furniture.gui.GuiPackage;
import com.mrcrayfish.furniture.gui.GuiPresent;
import com.mrcrayfish.furniture.gui.GuiPrinter;
import com.mrcrayfish.furniture.gui.GuiRecipeBook;
import com.mrcrayfish.furniture.gui.GuiWallCabinet;
import com.mrcrayfish.furniture.gui.GuiWashingMachine;
import com.mrcrayfish.furniture.gui.containers.ContainerBedsideCabinet;
import com.mrcrayfish.furniture.gui.containers.ContainerBin;
import com.mrcrayfish.furniture.gui.containers.ContainerCabinet;
import com.mrcrayfish.furniture.gui.containers.ContainerComputer;
import com.mrcrayfish.furniture.gui.containers.ContainerDishwasher;
import com.mrcrayfish.furniture.gui.containers.ContainerEnvelope;
import com.mrcrayfish.furniture.gui.containers.ContainerFreezer;
import com.mrcrayfish.furniture.gui.containers.ContainerFridge;
import com.mrcrayfish.furniture.gui.containers.ContainerMailBox;
import com.mrcrayfish.furniture.gui.containers.ContainerMicrowave;
import com.mrcrayfish.furniture.gui.containers.ContainerOven;
import com.mrcrayfish.furniture.gui.containers.ContainerPackage;
import com.mrcrayfish.furniture.gui.containers.ContainerPresent;
import com.mrcrayfish.furniture.gui.containers.ContainerPrinter;
import com.mrcrayfish.furniture.gui.containers.ContainerWallCabinet;
import com.mrcrayfish.furniture.gui.containers.ContainerWashingMachine;
import com.mrcrayfish.furniture.items.ItemEnvelope;
import com.mrcrayfish.furniture.items.ItemEnvelopeSigned;
import com.mrcrayfish.furniture.items.ItemPackage;
import com.mrcrayfish.furniture.items.ItemPackageSigned;
import com.mrcrayfish.furniture.items.ItemPresent;
import com.mrcrayfish.furniture.tileentity.TileEntityBedsideCabinet;
import com.mrcrayfish.furniture.tileentity.TileEntityBin;
import com.mrcrayfish.furniture.tileentity.TileEntityCabinet;
import com.mrcrayfish.furniture.tileentity.TileEntityCabinetKitchen;
import com.mrcrayfish.furniture.tileentity.TileEntityComputer;
import com.mrcrayfish.furniture.tileentity.TileEntityDishwasher;
import com.mrcrayfish.furniture.tileentity.TileEntityFreezer;
import com.mrcrayfish.furniture.tileentity.TileEntityFridge;
import com.mrcrayfish.furniture.tileentity.TileEntityMailBox;
import com.mrcrayfish.furniture.tileentity.TileEntityMicrowave;
import com.mrcrayfish.furniture.tileentity.TileEntityOven;
import com.mrcrayfish.furniture.tileentity.TileEntityPrinter;
import com.mrcrayfish.furniture.tileentity.TileEntityWallCabinet;
import com.mrcrayfish.furniture.tileentity.TileEntityWashingMachine;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler
implements IGuiHandler {
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile_entity = world.getTileEntity(x, y, z);
        if (tile_entity instanceof TileEntityFridge) {
            return new ContainerFridge((IInventory)player.inventory, (IInventory) tile_entity);
        }
        if (tile_entity instanceof TileEntityFreezer) {
            return new ContainerFreezer(player.inventory, (TileEntityFreezer)tile_entity);
        }
        if (tile_entity instanceof TileEntityCabinet) {
            return new ContainerCabinet((IInventory)player.inventory, (IInventory) tile_entity);
        }
        if (tile_entity instanceof TileEntityBedsideCabinet) {
            return new ContainerBedsideCabinet((IInventory)player.inventory, (IInventory) tile_entity);
        }
        if (tile_entity instanceof TileEntityOven) {
            return new ContainerOven(player.inventory, (TileEntityOven)tile_entity);
        }
        if (tile_entity instanceof TileEntityMailBox) {
            return new ContainerMailBox((IInventory)player.inventory, (IInventory) tile_entity);
        }
        if (tile_entity instanceof TileEntityComputer) {
            return new ContainerComputer((IInventory)player.inventory, (IInventory) tile_entity);
        }
        if (tile_entity instanceof TileEntityPrinter) {
            return new ContainerPrinter(player.inventory, (TileEntityPrinter)tile_entity);
        }
        if (tile_entity instanceof TileEntityBin) {
            return new ContainerBin((IInventory)player.inventory, (IInventory) tile_entity);
        }
        if (tile_entity instanceof TileEntityWallCabinet) {
            return new ContainerWallCabinet((IInventory)player.inventory, (IInventory) tile_entity);
        }
        if (tile_entity instanceof TileEntityMicrowave) {
            return new ContainerMicrowave((IInventory)player.inventory, (TileEntityMicrowave)tile_entity);
        }
        if (tile_entity instanceof TileEntityWashingMachine) {
            return new ContainerWashingMachine((IInventory)player.inventory, (TileEntityWashingMachine)tile_entity);
        }
        if (tile_entity instanceof TileEntityDishwasher) {
            return new ContainerDishwasher((IInventory)player.inventory, (TileEntityDishwasher)tile_entity);
        }
        if (tile_entity instanceof TileEntityCabinetKitchen) {
            return new ContainerCabinet((IInventory)player.inventory, (IInventory) tile_entity);
        }
        if (id == 5) {
            return new ContainerEnvelope((IInventory)player.inventory, ItemEnvelope.getInv(player));
        }
        if (id == 6) {
            return new ContainerEnvelope((IInventory)player.inventory, ItemEnvelopeSigned.getInv(player));
        }
        if (id == 7) {
            return new ContainerPackage((IInventory)player.inventory, ItemPackage.getInv(player));
        }
        if (id == 8) {
            return new ContainerPackage((IInventory)player.inventory, ItemPackageSigned.getInv(player));
        }
        if (id == 9) {
            return new ContainerPresent((IInventory)player.inventory, ItemPresent.getInv(player));
        }
        return null;
    }

    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile_entity = world.getTileEntity(x, y, z);
        if (tile_entity instanceof TileEntityFridge) {
            return new GuiFridge((IInventory)player.inventory, (IInventory) tile_entity);
        }
        if (tile_entity instanceof TileEntityFreezer) {
            return new GuiFreezer(player.inventory, (TileEntityFreezer)tile_entity);
        }
        if (tile_entity instanceof TileEntityCabinet) {
            return new GuiCabinet((IInventory)player.inventory, (IInventory) tile_entity);
        }
        if (tile_entity instanceof TileEntityBedsideCabinet) {
            return new GuiBedsideCabinet((IInventory)player.inventory, (IInventory) tile_entity);
        }
        if (tile_entity instanceof TileEntityOven) {
            return new GuiOven(player.inventory, (TileEntityOven)tile_entity);
        }
        if (tile_entity instanceof TileEntityMailBox && id == 0) {
            return new GuiMailBox(player.inventory, (TileEntityMailBox)tile_entity);
        }
        if (tile_entity instanceof TileEntityComputer) {
            return new GuiComputer(player.inventory, (TileEntityComputer)tile_entity);
        }
        if (tile_entity instanceof TileEntityPrinter) {
            return new GuiPrinter(player.inventory, (TileEntityPrinter)tile_entity);
        }
        if (tile_entity instanceof TileEntityBin) {
            return new GuiBin((IInventory)player.inventory, (IInventory) tile_entity, x, y, z);
        }
        if (tile_entity instanceof TileEntityWallCabinet) {
            return new GuiWallCabinet((IInventory)player.inventory, (IInventory) tile_entity);
        }
        if (tile_entity instanceof TileEntityMicrowave) {
            return new GuiMicrowave(player.inventory, (TileEntityMicrowave)tile_entity);
        }
        if (tile_entity instanceof TileEntityWashingMachine) {
            return new GuiWashingMachine(player.inventory, (TileEntityWashingMachine)tile_entity);
        }
        if (tile_entity instanceof TileEntityDishwasher) {
            return new GuiDishwasher(player.inventory, (TileEntityDishwasher)tile_entity);
        }
        if (tile_entity instanceof TileEntityCabinetKitchen) {
            return new GuiKitchenCabinet((IInventory)player.inventory, (IInventory) tile_entity);
        }
        ItemStack mail = null;
        if (player.getCurrentEquippedItem() != null) {
            mail = player.getCurrentEquippedItem();
        }
        if (id == 5) {
            return new GuiEnvelope(player.inventory, ItemEnvelope.getInv(player), player, mail);
        }
        if (id == 6) {
            return new GuiEnvelope(player.inventory, ItemEnvelopeSigned.getInv(player), player, mail);
        }
        if (id == 7) {
            return new GuiPackage(player.inventory, ItemPackage.getInv(player), player, mail);
        }
        if (id == 8) {
            return new GuiPackage(player.inventory, ItemPackageSigned.getInv(player), player, mail);
        }
        if (id == 9) {
            return new GuiPresent(player.inventory, ItemPresent.getInv(player), player, mail);
        }
        if (id == 10) {
            return new GuiRecipeBook();
        }
        return null;
    }
}

