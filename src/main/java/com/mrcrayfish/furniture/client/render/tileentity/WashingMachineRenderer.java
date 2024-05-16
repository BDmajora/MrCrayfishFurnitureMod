/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package com.mrcrayfish.furniture.client.render.tileentity;

import com.mrcrayfish.furniture.tileentity.TileEntityWashingMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class WashingMachineRenderer
extends TileEntitySpecialRenderer {
    private EntityItem armour;

    public WashingMachineRenderer() {
        this.armour = new EntityItem((World)Minecraft.getMinecraft().theWorld, 0.0, 0.0, 0.0);
    }

    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float p_147500_8_) {
        TileEntityWashingMachine tileEntityWashingMachine = (TileEntityWashingMachine)tileEntity;
        int metadata = tileEntity.getWorldObj().getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y + 0.5f), (float)((float)z + 0.5f));
        GL11.glRotatef((float)((float)metadata * -90.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        RenderItem.renderInFrame = true;
        this.armour.hoverStart = 0.0f;
        for (int i = 0; i < 4; ++i) {
            if (tileEntityWashingMachine.getStackInSlot(i) == null) continue;
            double zOffset = this.getOffsetZ(metadata, i);
            this.armour.setEntityItemStack(tileEntityWashingMachine.getStackInSlot(i));
            GL11.glRotated((double)(tileEntityWashingMachine.progress * 5), (double)0.0, (double)0.0, (double)1.0);
            RenderManager.instance.renderEntityWithPosYaw((Entity)this.armour, 0.0, -0.2, zOffset, 0.0f, 0.0f);
        }
        RenderItem.renderInFrame = false;
        GL11.glPopMatrix();
    }

    public double getOffsetZ(int metadata, int slot) {
        switch (slot) {
            case 0: {
                return -0.2;
            }
            case 1: {
                return -0.1;
            }
            case 2: {
                return 0.0;
            }
            case 3: {
                return 0.1;
            }
        }
        return 0.0;
    }
}

