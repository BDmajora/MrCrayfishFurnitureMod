/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package com.mrcrayfish.furniture.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class NullRender
extends Render {
    private final Minecraft mc = Minecraft.getMinecraft();

    public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1) {
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return null;
    }
}

