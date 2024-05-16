/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.items;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemCup
extends Item {
    @SideOnly(value=Side.CLIENT)
    private IIcon cup_icon;
    @SideOnly(value=Side.CLIENT)
    private IIcon cup_overlay;
    private boolean hasLiquid = false;
    private Block cupBlock = MrCrayfishFurnitureMod.cup;

    public ItemCup(boolean hasLiquid) {
        this.hasLiquid = hasLiquid;
        if (hasLiquid) {
            this.setMaxStackSize(1);
        }
    }

    public ItemStack onItemUseFinish(ItemStack cup, World world, EntityPlayer player) {
        if (this.hasLiquid) {
            int heal = cup.getTagCompound().getInteger("HealAmount");
            player.getFoodStats().addStats(heal, 0.5f);
            player.setCurrentItemOrArmor(0, new ItemStack(MrCrayfishFurnitureMod.itemCup));
        }
        return cup;
    }

    public int getMaxItemUseDuration(ItemStack cup) {
        return 32;
    }

    public EnumAction getItemUseAction(ItemStack cup) {
        return EnumAction.drink;
    }

    public ItemStack onItemRightClick(ItemStack cup, World world, EntityPlayer player) {
        if (this.hasLiquid && player.getFoodStats().needFood()) {
            player.setItemInUse(cup, this.getMaxItemUseDuration(cup));
        }
        return cup;
    }

    public boolean onItemUse(ItemStack cup, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float var8, float var9, float var10) {
        if (player.isSneaking()) {
            Block block = world.getBlock(x, y, z);
            int metadata = MathHelper.floor_double((double)((double)(player.rotationYaw * 4.0f / 360.0f) + 0.5)) & 3;
            if (block == Blocks.snow_layer && (world.getBlockMetadata(x, y, z) & 7) < 1) {
                p_77648_7_ = 1;
            } else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush) {
                if (p_77648_7_ == 0) {
                    --y;
                }
                if (p_77648_7_ == 1) {
                    ++y;
                }
                if (p_77648_7_ == 2) {
                    --z;
                }
                if (p_77648_7_ == 3) {
                    ++z;
                }
                if (p_77648_7_ == 4) {
                    --x;
                }
                if (p_77648_7_ == 5) {
                    ++x;
                }
            }
            if (!player.canPlayerEdit(x, y, z, p_77648_7_, cup)) {
                return false;
            }
            if (cup.stackSize == 0) {
                return false;
            }
            if (world.canPlaceEntityOnSide(this.cupBlock, x, y, z, false, p_77648_7_, (Entity)null, cup) && this.cupBlock.canPlaceBlockAt(world, x, y, z)) {
                int i1 = this.cupBlock.onBlockPlaced(world, x, y, z, p_77648_7_, var8, var9, var10, 0);
                if (world.setBlock(x, y, z, this.cupBlock, metadata, 3)) {
                    if (world.getBlock(x, y, z) == this.cupBlock) {
                        this.cupBlock.onBlockPlacedBy(world, x, y, z, (EntityLivingBase)player, cup);
                        this.cupBlock.onPostBlockPlaced(world, x, y, z, metadata);
                    }
                    world.playSoundEffect((double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), this.cupBlock.stepSound.getPlaceSound(), (this.cupBlock.stepSound.getVolume() + 1.0f) / 2.0f, this.cupBlock.stepSound.getFrequency() * 0.8f);
                    --cup.stackSize;
                }
            }
            return true;
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIconFromDamage(int p_77617_1_) {
        return this.cup_icon;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIconFromDamageForRenderPass(int damage, int pass) {
        return pass == 0 && this.hasLiquid ? this.cup_overlay : this.cup_icon;
    }

    @SideOnly(value=Side.CLIENT)
    public int getColorFromCompound(ItemStack cup) {
        if (cup.hasTagCompound() && cup.getTagCompound().hasKey("Colour")) {
            int[] colour = cup.getTagCompound().getIntArray("Colour");
            Color color = new Color(colour[0], colour[1], colour[2]);
            return color.getRGB();
        }
        return 0xFFFFFF;
    }

    @SideOnly(value=Side.CLIENT)
    public int getColorFromItemStack(ItemStack cup, int p_82790_2_) {
        return p_82790_2_ > 0 ? 0xFFFFFF : this.getColorFromCompound(cup);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        this.cup_icon = register.registerIcon("cfm:itemcup");
        this.cup_overlay = register.registerIcon("cfm:cup_overlay");
    }
}

