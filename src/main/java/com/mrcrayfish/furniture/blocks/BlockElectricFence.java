/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.effect.EntityLightningBolt
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.blocks;

import com.mrcrayfish.furniture.FurnitureAchievements;
import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.proxy.ClientProxy;
import com.mrcrayfish.furniture.proxy.CommonProxy;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockElectricFence
extends Block {
    public DamageSource electricFence = new DamageSource("electricFence");

    public BlockElectricFence() {
        super(Material.rock);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.6f, 1.0f);
        this.setLightLevel(0.2f);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return CommonProxy.renderElectricFence;
    }

    public boolean canRenderInPass(int pass) {
        ClientProxy.renderPass = pass;
        return true;
    }

    public int getRenderBlockPass() {
        return 1;
    }

    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return true;
    }

    public void addCollisionBoxesToList(World par1World, int i, int j, int k, AxisAlignedBB par5AxisAlignedBB, List arrayList, Entity par7Entity) {
        this.setBlockBounds(0.4375f, 0.0f, 0.4375f, 0.5625f, 1.0f, 0.5625f);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        if (par1World.getBlock(i + 1, j, k) == MrCrayfishFurnitureMod.electricFence | par1World.getBlock(i + 1, j, k) == Blocks.fence_gate) {
            this.setBlockBounds(0.4375f, 0.0f, 0.4375f, 1.0f, 1.0f, 0.5625f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
        if (par1World.getBlock(i - 1, j, k) == MrCrayfishFurnitureMod.electricFence | par1World.getBlock(i - 1, j, k) == Blocks.fence_gate) {
            this.setBlockBounds(0.0f, 0.0f, 0.4375f, 0.5625f, 1.0f, 0.5625f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
        if (par1World.getBlock(i, j, k + 1) == MrCrayfishFurnitureMod.electricFence | par1World.getBlock(i, j, k + 1) == Blocks.fence_gate) {
            this.setBlockBounds(0.4375f, 0.0f, 0.4375f, 0.5625f, 1.0f, 1.0f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
        if (par1World.getBlock(i, j, k - 1) == MrCrayfishFurnitureMod.electricFence | par1World.getBlock(i, j, k - 1) == Blocks.fence_gate) {
            this.setBlockBounds(0.4375f, 0.0f, 0.0f, 0.5625f, 1.0f, 0.5625f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        ((EntityPlayer)par5EntityLivingBase).triggerAchievement((StatBase)FurnitureAchievements.modernTechnology);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        float f = 0.4375f;
        float f1 = 0.5625f;
        float f2 = 0.4375f;
        float f3 = 0.5625f;
        if (par1IBlockAccess.getBlock(par2 + 1, par3, par4) == MrCrayfishFurnitureMod.electricFence | par1IBlockAccess.getBlock(par2 + 1, par3, par4) == Blocks.fence_gate) {
            f1 = 1.0f;
        }
        if (par1IBlockAccess.getBlock(par2 - 1, par3, par4) == MrCrayfishFurnitureMod.electricFence | par1IBlockAccess.getBlock(par2 - 1, par3, par4) == Blocks.fence_gate) {
            f = 0.0f;
        }
        if (par1IBlockAccess.getBlock(par2, par3, par4 + 1) == MrCrayfishFurnitureMod.electricFence | par1IBlockAccess.getBlock(par2, par3, par4 + 1) == Blocks.fence_gate) {
            f3 = 1.0f;
        }
        if (par1IBlockAccess.getBlock(par2, par3, par4 - 1) == MrCrayfishFurnitureMod.electricFence | par1IBlockAccess.getBlock(par2, par3, par4 - 1) == Blocks.fence_gate) {
            f2 = 0.0f;
        }
        this.setBlockBounds(f, 0.0f, f2, f1, 1.1f, f3);
    }

    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
        if (!(par5Entity instanceof EntityItem) && !par5Entity.getCommandSenderName().equals("unknown")) {
            if (par5Entity instanceof EntityCreeper) {
                EntityCreeper creeper = (EntityCreeper)par5Entity;
                EntityLightningBolt lightning = new EntityLightningBolt(par1World, (double)par2, (double)par3, (double)par4);
                creeper.onStruckByLightning(lightning);
            } else if (par5Entity instanceof EntityPlayer) {
                if (!((EntityPlayer)par5Entity).capabilities.isCreativeMode) {
                    par5Entity.attackEntityFrom(this.electricFence, 6.0f);
                    par1World.playSoundEffect((double)par2, (double)par3, (double)par4, "cfm:zap", 0.2f, 1.0f);
                    this.sparkle(par1World, par2, par3, par4);
                    ((EntityPlayer)par5Entity).triggerAchievement((StatBase)FurnitureAchievements.careful);
                }
            } else {
                par5Entity.attackEntityFrom(this.electricFence, 6.0f);
                par1World.playSoundEffect((double)par2, (double)par3, (double)par4, "cfm:zap", 0.2f, 1.0f);
                this.sparkle(par1World, par2, par3, par4);
            }
        }
    }

    public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity) {
        if (!(par5Entity instanceof EntityItem) && !par5Entity.getCommandSenderName().equals("unknown")) {
            if (par5Entity instanceof EntityCreeper) {
                EntityCreeper creeper = (EntityCreeper)par5Entity;
                EntityLightningBolt lightning = new EntityLightningBolt(par1World, (double)par2, (double)par3, (double)par4);
                creeper.onStruckByLightning(lightning);
            } else if (par5Entity instanceof EntityPlayer) {
                if (!((EntityPlayer)par5Entity).capabilities.isCreativeMode) {
                    par5Entity.attackEntityFrom(this.electricFence, 6.0f);
                    par1World.playSoundEffect((double)par2, (double)par3, (double)par4, "cfm:zap", 0.2f, 1.0f);
                    this.sparkle(par1World, par2, par3, par4);
                }
            } else {
                par5Entity.attackEntityFrom(this.electricFence, 6.0f);
                par1World.playSoundEffect((double)par2, (double)par3, (double)par4, "cfm:zap", 0.2f, 1.0f);
                this.sparkle(par1World, par2, par3, par4);
            }
        }
    }

    private void sparkle(World par1World, int par2, int par3, int par4) {
        Random random = par1World.rand;
        double d0 = 0.0625;
        for (int l = 0; l < 6; ++l) {
            double d1 = (float)par2 + random.nextFloat();
            double d2 = (float)par3 + random.nextFloat();
            double d3 = (float)par4 + random.nextFloat();
            if (l == 0 && !par1World.getBlock(par2, par3 + 1, par4).isOpaqueCube()) {
                d2 = (double)(par3 + 1) + d0;
            }
            if (l == 1 && !par1World.getBlock(par2, par3 - 1, par4).isOpaqueCube()) {
                d2 = (double)(par3 + 0) - d0;
            }
            if (l == 2 && !par1World.getBlock(par2, par3, par4 + 1).isOpaqueCube()) {
                d3 = (double)(par4 + 1) + d0;
            }
            if (l == 3 && !par1World.getBlock(par2, par3, par4 - 1).isOpaqueCube()) {
                d3 = (double)(par4 + 0) - d0;
            }
            if (l == 4 && !par1World.getBlock(par2 + 1, par3, par4).isOpaqueCube()) {
                d1 = (double)(par2 + 1) + d0;
            }
            if (l == 5 && !par1World.getBlock(par2 - 1, par3, par4).isOpaqueCube()) {
                d1 = (double)(par2 + 0) - d0;
            }
            if (!(d1 < (double)par2 || d1 > (double)(par2 + 1) || d2 < 0.0 || d2 > (double)(par3 + 1) || d3 < (double)par4) && !(d3 > (double)(par4 + 1))) continue;
            par1World.spawnParticle("reddust", d1, d2, d3, 0.0, 0.0, 0.0);
        }
    }

    public Item getItemDropped(int i, Random random, int j) {
        return MrCrayfishFurnitureMod.itemElectricFence;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack(MrCrayfishFurnitureMod.itemElectricFence);
    }

    public void registerIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = Blocks.anvil.getBlockTextureFromSide(1);
    }
}

