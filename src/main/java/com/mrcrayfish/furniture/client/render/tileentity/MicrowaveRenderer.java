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

import com.mrcrayfish.furniture.tileentity.TileEntityMicrowave;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class MicrowaveRenderer
extends TileEntitySpecialRenderer {
    private EntityItem entityFood;

    public MicrowaveRenderer() {
        this.entityFood = new EntityItem((World)Minecraft.getMinecraft().theWorld, 0.0, 0.0, 0.0);
    }

    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
        TileEntityMicrowave microwave = (TileEntityMicrowave)tileEntity;
        int metadata = tileEntity.getWorld().getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
        if (microwave.getItem() != null) {
            this.entityFood.setEntityItemStack(microwave.getItem());
            GL11.glPushMatrix();
            this.entityFood.hoverStart = 0.0f;
            RenderItem.renderInFrame = true;
            float xOffset = 0.0f;
            float zOffset = 0.0f;
            switch (metadata) {
                case 0: {
                    xOffset += 0.1f;
                    break;
                }
                case 1: {
                    xOffset += 0.2f;
                    zOffset += 0.3f;
                    break;
                }
                case 2: {
                    xOffset -= 0.1f;
                    zOffset += 0.4f;
                    break;
                }
                case 3: {
                    xOffset -= 0.2f;
                    zOffset += 0.1f;
                }
            }
            GL11.glTranslatef((float)((float)x + 0.5f + xOffset), (float)((float)y + 0.075f), (float)((float)z + 0.3f + zOffset));
            GL11.glRotatef((float)((float)metadata * -90.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)1.0f);
            RenderManager.instance.renderEntityWithPosYaw((Entity)this.entityFood, 0.0, 0.0, 0.075, 0.0f, 0.0f);
            RenderItem.renderInFrame = false;
            GL11.glPopMatrix();
        }
    }
}

