/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.particle.EntitySpellParticleFX
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.util;

import com.mrcrayfish.furniture.entity.EntityShowerFX;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.world.World;

public class ParticleSpawner {
    private static Minecraft mc = Minecraft.getMinecraft();

    public static EntityFX spawnParticle(String particleName, double par2, double par4, double par6) {
        if (mc != null && ParticleSpawner.mc.renderViewEntity != null && ParticleSpawner.mc.effectRenderer != null) {
            int var14 = ParticleSpawner.mc.gameSettings.particleSetting;
            if (var14 == 1 && ParticleSpawner.mc.theWorld.rand.nextInt(3) == 0) {
                var14 = 2;
            }
            double var15 = ParticleSpawner.mc.renderViewEntity.posX - par2;
            double var17 = ParticleSpawner.mc.renderViewEntity.posY - par4;
            double var19 = ParticleSpawner.mc.renderViewEntity.posZ - par6;
            EntityShowerFX var21 = null;
            double var22 = 16.0;
            if (var15 * var15 + var17 * var17 + var19 * var19 > var22 * var22) {
                return null;
            }
            if (var14 > 1) {
                return null;
            }
            if (particleName.equals("shower")) {
                var21 = new EntityShowerFX((World)ParticleSpawner.mc.theWorld, par2, par4, par6);
            } else if (particleName.equals("smoke")) {
                var21 = new EntityShowerFX((World)ParticleSpawner.mc.theWorld, par2, par4, par6);
            }
            ParticleSpawner.mc.effectRenderer.addEffect(var21);
            return var21;
        }
        return null;
    }
}

