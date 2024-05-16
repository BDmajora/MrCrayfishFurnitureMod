/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 */
package com.mrcrayfish.furniture.tileentity;

import com.mrcrayfish.furniture.FurnitureAchievements;
import com.mrcrayfish.furniture.util.ParticleSpawner;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityShowerHead
extends TileEntity {
    private Random rand = new Random();
    private int timer = 20;

    public void updateEntity() {
        if (this.worldObj.isRemote) {
            double posX = (double)this.xCoord + 0.35 + this.rand.nextDouble() / 3.0;
            double posZ = (double)this.zCoord + 0.35 + this.rand.nextDouble() / 3.0;
            ParticleSpawner.spawnParticle("shower", posX, (double)this.yCoord + 0.065, posZ);
        }
        List listPlayers = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox((double)this.xCoord, (double)(this.yCoord - 1), (double)this.zCoord, (double)((double)this.xCoord + 1.0), (double)((double)(this.yCoord - 1) + 1.0), (double)((double)this.zCoord + 1.0)));
        for (EntityPlayer player : listPlayers) {
            if (player == null) continue;
            player.curePotionEffects(new ItemStack(Items.milk_bucket));
            player.triggerAchievement((StatBase)FurnitureAchievements.allClean);
            for (int i = 0; i < 4; ++i) {
                ItemArmor armour;
                ItemStack itemstack = player.getCurrentArmor(i);
                if (itemstack == null || !(itemstack.getItem() instanceof ItemArmor) || (armour = (ItemArmor)itemstack.getItem()).getArmorMaterial() != ItemArmor.ArmorMaterial.CLOTH) continue;
                player.setCurrentItemOrArmor(i + 1, new ItemStack(itemstack.getItem(), 1, itemstack.getItemDamage()));
            }
            if (!player.isBurning()) continue;
            player.extinguish();
        }
        if (this.timer >= 20) {
            this.worldObj.playSoundEffect((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, "cfm:shower", 0.75f, 1.0f);
            this.timer = 0;
        }
        ++this.timer;
    }
}

