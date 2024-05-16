/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 */
package com.mrcrayfish.furniture.client.render.tileentity;

import com.mrcrayfish.furniture.tileentity.TileEntityCup;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class CupRenderer
extends TileEntitySpecialRenderer {
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float p_147500_8_) {
        TileEntityCup tileEntityCup = (TileEntityCup)tileEntity;
        if (tileEntityCup.getDrink() != null) {
            Tessellator tessellator = Tessellator.instance;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y), (float)((float)z + 0.5f));
            GL11.glAlphaFunc((int)516, (float)0.1f);
            GL11.glDisable((int)2884);
            GL11.glDisable((int)3553);
            GL11.glDisable((int)2896);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glEnable((int)3042);
            GL11.glColor4f((float)((float)tileEntityCup.red / 255.0f), (float)((float)tileEntityCup.green / 255.0f), (float)((float)tileEntityCup.blue / 255.0f), (float)1.0f);
            GL11.glBegin((int)7);
            GL11.glVertex3d((double)-0.125, (double)0.03125, (double)-0.125);
            GL11.glVertex3d((double)0.125, (double)0.03125, (double)-0.125);
            GL11.glVertex3d((double)0.125, (double)0.4, (double)-0.125);
            GL11.glVertex3d((double)-0.125, (double)0.4, (double)-0.125);
            GL11.glVertex3d((double)-0.125, (double)0.03125, (double)0.125);
            GL11.glVertex3d((double)0.125, (double)0.03125, (double)0.125);
            GL11.glVertex3d((double)0.125, (double)0.4, (double)0.125);
            GL11.glVertex3d((double)-0.125, (double)0.4, (double)0.125);
            GL11.glVertex3d((double)-0.125, (double)0.03125, (double)-0.125);
            GL11.glVertex3d((double)-0.125, (double)0.03125, (double)0.125);
            GL11.glVertex3d((double)-0.125, (double)0.4, (double)0.125);
            GL11.glVertex3d((double)-0.125, (double)0.4, (double)-0.125);
            GL11.glVertex3d((double)0.125, (double)0.03125, (double)-0.125);
            GL11.glVertex3d((double)0.125, (double)0.03125, (double)0.125);
            GL11.glVertex3d((double)0.125, (double)0.4, (double)0.125);
            GL11.glVertex3d((double)0.125, (double)0.4, (double)-0.125);
            GL11.glVertex3d((double)-0.125, (double)0.4, (double)-0.125);
            GL11.glVertex3d((double)0.125, (double)0.4, (double)-0.125);
            GL11.glVertex3d((double)0.125, (double)0.4, (double)0.125);
            GL11.glVertex3d((double)-0.125, (double)0.4, (double)0.125);
            GL11.glVertex3d((double)-0.125, (double)0.03125, (double)-0.125);
            GL11.glVertex3d((double)0.125, (double)0.03125, (double)-0.125);
            GL11.glVertex3d((double)0.125, (double)0.03125, (double)0.125);
            GL11.glVertex3d((double)-0.125, (double)0.03125, (double)0.125);
            GL11.glEnd();
            GL11.glDisable((int)3042);
            GL11.glEnable((int)2896);
            GL11.glEnable((int)3553);
            GL11.glAlphaFunc((int)516, (float)0.5f);
            GL11.glPopMatrix();
        }
    }
}

