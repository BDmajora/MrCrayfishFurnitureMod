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
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package com.mrcrayfish.furniture.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class CookieRenderer
extends TileEntitySpecialRenderer {
    private ItemStack cookie = new ItemStack(Items.cookie);
    private EntityItem entityItem;

    public CookieRenderer() {
        this.entityItem = new EntityItem((World)Minecraft.getMinecraft().theWorld, 0.0, 0.0, 0.0, this.cookie);
    }

    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        this.entityItem.hoverStart = 0.0f;
        RenderItem.renderInFrame = true;
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y + 0.05f), (float)((float)z + 0.3f));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)1.0f);
        GL11.glScalef((float)0.9f, (float)0.9f, (float)0.9f);
        int metadata = tileEntity.getWorld().getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
        for (int i = 0; i < metadata; ++i) {
            RenderManager.instance.renderEntityWithPosYaw((Entity)this.entityItem, 0.0, 0.0, 0.1 * (double)i, 0.0f, 0.0f);
        }
        RenderItem.renderInFrame = false;
        GL11.glPopMatrix();
    }
}

