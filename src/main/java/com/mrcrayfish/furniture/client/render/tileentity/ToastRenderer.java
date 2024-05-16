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
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package com.mrcrayfish.furniture.client.render.tileentity;

import com.mrcrayfish.furniture.tileentity.TileEntityToaster;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class ToastRenderer
extends TileEntitySpecialRenderer {
    private EntityItem[] slots;

    public ToastRenderer() {
        this.slots = new EntityItem[]{new EntityItem((World)Minecraft.getMinecraft().theWorld, 0.0, 0.0, 0.0), new EntityItem((World)Minecraft.getMinecraft().theWorld, 0.0, 0.0, 0.0)};
    }

    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
        TileEntityToaster tileEntityToaster = (TileEntityToaster)tileEntity;
        int metadata = tileEntity.getWorldObj().getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
        for (int i = 0; i < 2; ++i) {
            ItemStack slice = tileEntityToaster.getSlice(i);
            if (slice == null) continue;
            this.slots[i].setEntityItemStack(slice);
            GL11.glPushMatrix();
            this.slots[i].hoverStart = 0.0f;
            RenderItem.renderInFrame = true;
            GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y + 0.05f), (float)((float)z + 0.3f));
            double xOffset = 0.0;
            double zOffset = 0.0;
            switch (metadata) {
                case 0: {
                    if (i == 1) {
                        zOffset += (double)0.27f;
                        break;
                    }
                    zOffset += (double)0.14f;
                    break;
                }
                case 1: {
                    xOffset += (double)0.2f;
                    zOffset = i == 1 ? (zOffset += (double)0.07f) : (zOffset -= (double)0.06f);
                    GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    break;
                }
                case 2: {
                    zOffset = i == 1 ? (zOffset -= (double)0.13f) : (zOffset -= (double)0.26f);
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    break;
                }
                case 3: {
                    xOffset -= (double)0.2f;
                    zOffset = i == 1 ? (zOffset += (double)0.07f) : (zOffset -= (double)0.06f);
                    GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                }
            }
            double yOffset = tileEntityToaster.isToasting() ? -0.075 : 0.0;
            RenderManager.instance.renderEntityWithPosYaw((Entity)this.slots[i], 0.0 + xOffset, 0.1 + yOffset, 0.0 + zOffset, 0.0f, 0.0f);
            RenderItem.renderInFrame = false;
            GL11.glPopMatrix();
        }
    }
}

