/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.Tessellator
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

import com.mrcrayfish.furniture.tileentity.TileEntityBlender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class BlenderRenderer
extends TileEntitySpecialRenderer {
    private EntityItem entityFood;

    public BlenderRenderer() {
        this.entityFood = new EntityItem((World)Minecraft.getMinecraft().theWorld, 0.0, 0.0, 0.0){};
    }

    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float p_147500_8_) {
        TileEntityBlender blender = (TileEntityBlender)tileEntity;
        ItemStack[] ingredients = blender.getIngredients();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y + 0.2f), (float)((float)z + 0.5f));
        GL11.glScalef((float)0.65f, (float)0.65f, (float)0.65f);
        this.entityFood.hoverStart = 0.0f;
        RenderItem.renderInFrame = true;
        for (int i = 0; i < ingredients.length; ++i) {
            if (ingredients[i] == null) continue;
            this.entityFood.setEntityItemStack(ingredients[i]);
            GL11.glRotatef((float)((float)i * -90.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)((float)blender.progress * 18.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            RenderManager.instance.renderEntityWithPosYaw((Entity)this.entityFood, 0.0, 0.2, 0.0, 0.0f, 0.0f);
        }
        RenderItem.renderInFrame = false;
        GL11.glPopMatrix();
        if (blender.isBlending() | blender.drinkCount > 0) {
            Tessellator tessellator = Tessellator.instance;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y + 0.05f), (float)((float)z + 0.5f));
            GL11.glAlphaFunc((int)516, (float)0.1f);
            GL11.glDisable((int)2884);
            GL11.glDisable((int)3553);
            GL11.glDisable((int)2896);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glEnable((int)3042);
            float alpha = blender.isBlending() ? (float)blender.progress / 200.0f : (blender.drinkCount > 0 ? 1.0f : 0.0f);
            GL11.glColor4f((float)((float)blender.currentRed / 255.0f), (float)((float)blender.currentGreen / 255.0f), (float)((float)blender.currentBlue / 255.0f), (float)alpha);
            float height = blender.isBlending() ? 0.8f : 0.275f + 0.525f * ((float)blender.drinkCount / 6.0f);
            GL11.glBegin((int)7);
            GL11.glVertex3d((double)-0.2, (double)0.275, (double)-0.2);
            GL11.glVertex3d((double)0.2, (double)0.275, (double)-0.2);
            GL11.glVertex3d((double)0.2, (double)height, (double)-0.2);
            GL11.glVertex3d((double)-0.2, (double)height, (double)-0.2);
            GL11.glVertex3d((double)-0.2, (double)0.275, (double)0.2);
            GL11.glVertex3d((double)0.2, (double)0.275, (double)0.2);
            GL11.glVertex3d((double)0.2, (double)height, (double)0.2);
            GL11.glVertex3d((double)-0.2, (double)height, (double)0.2);
            GL11.glVertex3d((double)-0.2, (double)0.275, (double)-0.2);
            GL11.glVertex3d((double)-0.2, (double)0.275, (double)0.2);
            GL11.glVertex3d((double)-0.2, (double)height, (double)0.2);
            GL11.glVertex3d((double)-0.2, (double)height, (double)-0.2);
            GL11.glVertex3d((double)0.2, (double)0.275, (double)-0.2);
            GL11.glVertex3d((double)0.2, (double)0.275, (double)0.2);
            GL11.glVertex3d((double)0.2, (double)height, (double)0.2);
            GL11.glVertex3d((double)0.2, (double)height, (double)-0.2);
            GL11.glVertex3d((double)-0.2, (double)height, (double)-0.2);
            GL11.glVertex3d((double)0.2, (double)height, (double)-0.2);
            GL11.glVertex3d((double)0.2, (double)height, (double)0.2);
            GL11.glVertex3d((double)-0.2, (double)height, (double)0.2);
            GL11.glVertex3d((double)-0.2, (double)0.275, (double)-0.2);
            GL11.glVertex3d((double)0.2, (double)0.275, (double)-0.2);
            GL11.glVertex3d((double)0.2, (double)0.275, (double)0.2);
            GL11.glVertex3d((double)-0.2, (double)0.275, (double)0.2);
            GL11.glEnd();
            GL11.glDisable((int)3042);
            GL11.glEnable((int)2896);
            GL11.glEnable((int)3553);
            GL11.glAlphaFunc((int)516, (float)0.5f);
            GL11.glPopMatrix();
        }
    }
}

