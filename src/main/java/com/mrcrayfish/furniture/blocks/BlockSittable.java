/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.blocks;

import com.mrcrayfish.furniture.entity.EntitySittableBlock;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockSittable
extends Block {
    public BlockSittable(Material par2Material) {
        super(par2Material);
    }

    public boolean sitOnBlock(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, double par6) {
        this.checkForExistingEntity(par1World, par2, par3, par4, par5EntityPlayer);
        EntitySittableBlock nemb = new EntitySittableBlock(par1World, par2, par3, par4, par6);
        par1World.spawnEntityInWorld((Entity)nemb);
        par5EntityPlayer.mountEntity((Entity)nemb);
        return true;
    }

    public boolean sitOnBlockWithRotationOffset(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, double par6, int metadata, double offset) {
        if (!this.checkForExistingEntity(par1World, par2, par3, par4, par5EntityPlayer)) {
            EntitySittableBlock nemb = new EntitySittableBlock(par1World, par2, par3, par4, par6, metadata, offset);
            par1World.spawnEntityInWorld((Entity)nemb);
            par5EntityPlayer.mountEntity((Entity)nemb);
        }
        return true;
    }

    public boolean checkForExistingEntity(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {
        List listEMB = par1World.getEntitiesWithinAABB(EntitySittableBlock.class, AxisAlignedBB.getBoundingBox((double)par2, (double)par3, (double)par4, (double)((double)par2 + 1.0), (double)((double)par3 + 1.0), (double)((double)par4 + 1.0)).expand(1.0, 1.0, 1.0));
        for (EntitySittableBlock mount : listEMB) {
            if (mount.blockPosX != par2 || mount.blockPosY != par3 || mount.blockPosZ != par4) continue;
            if (mount.riddenByEntity == null) {
                par5EntityPlayer.mountEntity((Entity)mount);
            }
            return true;
        }
        return false;
    }
}

