/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.blocks;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.proxy.ClientProxy;
import com.mrcrayfish.furniture.proxy.CommonProxy;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFireAlarm
extends Block {
    private boolean isOn;

    public BlockFireAlarm(Material par2Material, boolean isOn) {
        super(par2Material);
        this.setBlockBounds(0.3f, 0.9f, 0.3f, 0.7f, 1.0f, 0.7f);
        this.isOn = isOn;
        if (isOn) {
            this.setLightLevel(1.0f);
        } else {
            this.setLightLevel(0.2f);
        }
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return CommonProxy.renderFireAlarm;
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

    public boolean canProvidePower() {
        return this == MrCrayfishFurnitureMod.fireAlarmOn;
    }

    public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return this == MrCrayfishFurnitureMod.fireAlarmOn && par5 == 0 ? 15 : 0;
    }

    public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return par5 == 0 && this == MrCrayfishFurnitureMod.fireAlarmOn ? this.isProvidingWeakPower(par1IBlockAccess, par2, par3, par4, par5) : 0;
    }

    private boolean isIndirectlyPowered(World par1World, int par2, int par3, int par4) {
        return par1World.getIndirectPowerOutput(par2, par3 + 1, par4, 0) && this == MrCrayfishFurnitureMod.fireAlarmOn;
    }

    public Item getItemDropped(int i, Random random, int j) {
        return MrCrayfishFurnitureMod.itemFireAlarm;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack(MrCrayfishFurnitureMod.itemFireAlarm);
    }

    public int tickRate(World par1World) {
        return 40;
    }

    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (!this.isOn) {
            int radius = 9;
            block0: for (int x = 0; x < radius; ++x) {
                for (int y = 0; y < radius; ++y) {
                    for (int z = 0; z < radius; ++z) {
                        if (par1World.getBlock(par2 - 4 + x, par3 - 4 + y, par4 - 4 + z) == Blocks.fire) {
                            par1World.playSoundEffect((double)par2, (double)par3, (double)par4, "cfm:firealarm", 5.0f, 1.0f);
                            par1World.setBlock(par2, par3, par4, MrCrayfishFurnitureMod.fireAlarmOn, 0, 3);
                            break block0;
                        }
                        if (x != 8 || y != 8 || z != 8) continue;
                        par1World.scheduleBlockUpdate(par2, par3, par4, (Block)this, this.tickRate(par1World));
                        break block0;
                    }
                }
            }
        }
        if (this.isOn) {
            par1World.playSoundEffect((double)par2, (double)par3, (double)par4, "cfm:firealarm", 5.0f, 1.0f);
            par1World.scheduleBlockUpdate(par2, par3, par4, (Block)this, 34);
        }
    }

    public void onBlockAdded(World par1World, int par2, int par3, int par4) {
        if (this.isOn) {
            par1World.scheduleBlockUpdate(par2, par3, par4, (Block)this, 34);
        }
        if (!this.isOn) {
            par1World.scheduleBlockUpdate(par2, par3, par4, (Block)this, 1);
        }
        par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, (Block)this);
        par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, (Block)this);
        par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, (Block)this);
        par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, (Block)this);
        par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, (Block)this);
        par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, (Block)this);
    }

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        if (this.isOn) {
            par1World.setBlock(par2, par3, par4, MrCrayfishFurnitureMod.fireAlarmOff, 0, 3);
        }
        return true;
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (this.canBlockStay(world, x, y, z)) {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }

    public boolean canBlockStay(World par1World, int par2, int par3, int par4) {
        return par1World.isAirBlock(par2, par3 + 1, par4);
    }

    public void registerIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = Blocks.quartz_block.getBlockTextureFromSide(1);
    }
}

