/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

@SideOnly(value=Side.CLIENT)
public class EntityShowerFX
extends EntityFX {
    private Material materialType;

    public EntityShowerFX(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6, 0.0, 0.0, 0.0);
        this.motionY = -0.2;
        this.motionX *= (double)0.3f;
        this.motionZ *= (double)0.3f;
        this.particleRed = 0.0f;
        this.particleGreen = 0.0f;
        this.particleBlue = 1.0f;
        this.setParticleTextureIndex(113);
        this.setSize(0.1f, 0.1f);
        this.particleMaxAge = 15;
        this.particleScale = 1.5f;
    }

    public int getBrightnessForRender(float par1) {
        return super.getBrightnessForRender(par1);
    }

    public float getBrightness(float par1) {
        return 1.0f;
    }

    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.particleRed = 0.2f;
        this.particleGreen = 0.3f;
        this.particleBlue = 1.0f;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionY = -0.2;
        if (this.particleMaxAge-- <= 0) {
            this.setDead();
        }
        if (this.onGround) {
            this.particleScale -= 0.1f;
            this.motionX *= (double)0.7f;
            this.motionZ *= (double)0.7f;
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
        par1Tessellator.draw();
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setBrightness(200);
        super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
    }
}

