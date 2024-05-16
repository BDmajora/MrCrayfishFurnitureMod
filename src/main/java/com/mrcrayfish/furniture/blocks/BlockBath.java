/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.blocks;

import com.mrcrayfish.furniture.FurnitureAchievements;
import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.network.PacketHandler;
import com.mrcrayfish.furniture.network.message.MessageFillBath;
import com.mrcrayfish.furniture.proxy.ClientProxy;
import com.mrcrayfish.furniture.proxy.CommonProxy;
import com.mrcrayfish.furniture.tileentity.TileEntityBath;
import com.mrcrayfish.furniture.util.CollisionHelper;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBath
extends BlockContainer {
    public BlockBath(Material material) {
        super(material);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return CommonProxy.renderBath;
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

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        if (!par1World.isRemote) {
            int[] coords = this.getOtherBathCoords(par1World, par2, par3, par4);
            TileEntity tile_entity = par1World.getTileEntity(par2, par3, par4);
            TileEntity tile_entity2 = par1World.getTileEntity(coords[0], coords[1], coords[2]);
            if (tile_entity instanceof TileEntityBath && tile_entity2 instanceof TileEntityBath) {
                TileEntityBath tileEntityBath = (TileEntityBath)tile_entity;
                TileEntityBath tileEntityBath2 = (TileEntityBath)tile_entity2;
                ItemStack currentItem = par5EntityPlayer.getHeldItem();
                if (currentItem != null) {
                    if (currentItem.getItem() == Items.bucket) {
                        if (tileEntityBath.hasWater()) {
                            if (!par5EntityPlayer.capabilities.isCreativeMode) {
                                if (currentItem.stackSize > 1) {
                                    if (par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.water_bucket))) {
                                        --par5EntityPlayer.getHeldItem().stackSize;
                                    }
                                } else {
                                    par5EntityPlayer.setCurrentItemOrArmor(0, new ItemStack(Items.water_bucket));
                                }
                            }
                            tileEntityBath.removeWaterLevel();
                            tileEntityBath2.removeWaterLevel();
                        }
                    } else if (currentItem.getItem() == Items.water_bucket) {
                        if (!tileEntityBath.isFull()) {
                            tileEntityBath.addWaterLevel();
                            tileEntityBath2.addWaterLevel();
                            if (!par5EntityPlayer.capabilities.isCreativeMode) {
                                par5EntityPlayer.setCurrentItemOrArmor(0, new ItemStack(Items.bucket));
                            }
                        }
                    } else if (currentItem.getItem() == Items.glass_bottle) {
                        if (tileEntityBath.hasWater()) {
                            if (!par5EntityPlayer.capabilities.isCreativeMode) {
                                if (currentItem.stackSize > 1) {
                                    if (par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack((Item)Items.potionitem, 1, 0))) {
                                        --par5EntityPlayer.getHeldItem().stackSize;
                                    }
                                } else {
                                    par5EntityPlayer.setCurrentItemOrArmor(0, new ItemStack((Item)Items.potionitem, 1, 0));
                                }
                            }
                            tileEntityBath.removeWaterLevel();
                            tileEntityBath2.removeWaterLevel();
                        }
                    } else if (currentItem.getItem() == Items.potionitem && currentItem.getMetadata() == 0) {
                        if (!tileEntityBath.isFull()) {
                            tileEntityBath.addWaterLevel();
                            tileEntityBath2.addWaterLevel();
                            if (!par5EntityPlayer.capabilities.isCreativeMode) {
                                par5EntityPlayer.setCurrentItemOrArmor(0, new ItemStack(Items.glass_bottle));
                            }
                        }
                    } else if (this == MrCrayfishFurnitureMod.bath2) {
                        if (this.hasWaterSource(par1World, par2, par3, par4)) {
                            if (!tileEntityBath.isFull()) {
                                tileEntityBath.addWaterLevel();
                                tileEntityBath2.addWaterLevel();
                                par1World.playSoundEffect((double)par2, (double)par3, (double)par4, "cfm:tap", 0.75f, 0.8f);
                                par1World.setBlockToAir(par2, par3 - 2, par4);
                            }
                        } else {
                            par5EntityPlayer.addChatComponentMessage((IChatComponent)new ChatComponentText("You need to have a water source under the block the bath head is on to fill it. Alternatively you can use a water bucket to fill it."));
                        }
                    }
                } else if (this == MrCrayfishFurnitureMod.bath2) {
                    if (this.hasWaterSource(par1World, par2, par3, par4)) {
                        if (!tileEntityBath.isFull()) {
                            tileEntityBath.addWaterLevel();
                            tileEntityBath2.addWaterLevel();
                            par1World.playSoundEffect((double)par2, (double)par3, (double)par4, "cfm:tap", 0.75f, 0.6f);
                            par1World.setBlockToAir(par2, par3 - 2, par4);
                        }
                    } else {
                        par5EntityPlayer.addChatComponentMessage((IChatComponent)new ChatComponentText("You need to have a water source under the block the bath head is on to fill it. Alternatively you can use a water bucket to fill it."));
                    }
                }
                PacketHandler.INSTANCE.sendToAllAround((IMessage)new MessageFillBath(tileEntityBath.getWaterLevel(), par2, par3, par4, coords[0], coords[1], coords[2]), new NetworkRegistry.TargetPoint(par5EntityPlayer.dimension, (double)par2, (double)par3, (double)par4, 128.0));
                par1World.markBlockForUpdate(par2, par3, par4);
                par1World.markBlockForUpdate(coords[0], coords[1], coords[2]);
            }
        }
        return true;
    }

    public boolean hasWaterSource(World world, int x, int y, int z) {
        return world.getBlock(x, y - 2, z) == Blocks.water && world.getBlockMetadata(x, y - 2, z) == 0;
    }

    public int[] getOtherBathCoords(World world, int x, int y, int z) {
        int metadata = world.getBlockMetadata(x, y, z);
        if (this == MrCrayfishFurnitureMod.bath1) {
            switch (metadata) {
                case 3: {
                    return new int[]{x + 1, y, z};
                }
                case 1: {
                    return new int[]{x - 1, y, z};
                }
                case 2: {
                    return new int[]{x, y, z - 1};
                }
                case 0: {
                    return new int[]{x, y, z + 1};
                }
            }
        } else {
            switch (metadata) {
                case 3: {
                    return new int[]{x - 1, y, z};
                }
                case 1: {
                    return new int[]{x + 1, y, z};
                }
                case 2: {
                    return new int[]{x, y, z + 1};
                }
                case 0: {
                    return new int[]{x, y, z - 1};
                }
            }
        }
        return null;
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        int metadata = par1World.getBlockMetadata(par2, par3, par4);
        if (metadata == 3) {
            par1World.setBlock(par2 + 1, par3, par4, MrCrayfishFurnitureMod.bath2, metadata, 3);
        }
        if (metadata == 1) {
            par1World.setBlock(par2 - 1, par3, par4, MrCrayfishFurnitureMod.bath2, metadata, 3);
        }
        if (metadata == 2) {
            par1World.setBlock(par2, par3, par4 - 1, MrCrayfishFurnitureMod.bath2, metadata, 3);
        }
        if (metadata == 0) {
            par1World.setBlock(par2, par3, par4 + 1, MrCrayfishFurnitureMod.bath2, metadata, 3);
        }
        ((EntityPlayer)par5EntityLivingBase).triggerAchievement((StatBase)FurnitureAchievements.bathroom);
    }

    public boolean canPlaceBath(World par1World, int par2, int par3, int par4, int par5Metadata) {
        switch (par5Metadata) {
            case 3: {
                return par1World.isAirBlock(par2, par3, par4) && par1World.isAirBlock(par2 + 1, par3, par4);
            }
            case 1: {
                return par1World.isAirBlock(par2, par3, par4) && par1World.isAirBlock(par2 - 1, par3, par4);
            }
            case 2: {
                return par1World.isAirBlock(par2, par3, par4) && par1World.isAirBlock(par2, par3, par4 - 1);
            }
            case 0: {
                return par1World.isAirBlock(par2, par3, par4) && par1World.isAirBlock(par2, par3, par4 + 1);
            }
        }
        return false;
    }

    public boolean removeBlockByPlayer(World par1World, EntityPlayer player, int par3, int par4, int par5) {
        Block block = par1World.getBlock(par3, par4, par5);
        int metadata = par1World.getBlockMetadata(par3, par4, par5);
        if (this == MrCrayfishFurnitureMod.bath1) {
            if (metadata == 3) {
                par1World.setBlockToAir(par3 + 1, par4, par5);
            }
            if (metadata == 1) {
                par1World.setBlockToAir(par3 - 1, par4, par5);
            }
            if (metadata == 2) {
                par1World.setBlockToAir(par3, par4, par5 - 1);
            }
            if (metadata == 0) {
                par1World.setBlockToAir(par3, par4, par5 + 1);
            }
        } else {
            if (metadata == 3) {
                par1World.setBlockToAir(par3 - 1, par4, par5);
            }
            if (metadata == 1) {
                par1World.setBlockToAir(par3 + 1, par4, par5);
            }
            if (metadata == 2) {
                par1World.setBlockToAir(par3, par4, par5 + 1);
            }
            if (metadata == 0) {
                par1World.setBlockToAir(par3, par4, par5 - 1);
            }
        }
        return par1World.setBlockToAir(par3, par4, par5);
    }

    public void addCollisionBoxesToList(World par1World, int i, int j, int k, AxisAlignedBB par5AxisAlignedBB, List arrayList, Entity par7Entity) {
        int metadata = par1World.getBlockMetadata(i, j, k);
        float[] boundst = CollisionHelper.fixRotation(metadata, 0.0f, 0.0f, 1.0f, 1.0f);
        super.setBlockBounds(boundst[0], 0.0f, boundst[1], boundst[2], 0.125f, boundst[3]);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        float[] bounds = CollisionHelper.fixRotation(metadata, 0.0f, 0.0f, 1.0f, 0.125f);
        super.setBlockBounds(bounds[0], 0.0f, bounds[1], bounds[2], 0.9f, bounds[3]);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        float[] bounds2 = CollisionHelper.fixRotation(metadata, 0.0f, 0.875f, 1.0f, 1.0f);
        super.setBlockBounds(bounds2[0], 0.0f, bounds2[1], bounds2[2], 0.9f, bounds2[3]);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        if (this == MrCrayfishFurnitureMod.bath1) {
            float[] bounds3 = CollisionHelper.fixRotation(metadata, 0.0f, 0.125f, 0.125f, 0.875f);
            super.setBlockBounds(bounds3[0], 0.0f, bounds3[1], bounds3[2], 0.9f, bounds3[3]);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        } else {
            float[] bounds4 = CollisionHelper.fixRotation(metadata, 0.75f, 0.0f, 1.0f, 0.875f);
            super.setBlockBounds(bounds4[0], 0.0f, bounds4[1], bounds4[2], 0.9f, bounds4[3]);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
    }

    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.875f, 1.0f);
    }

    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5Block) {
        int[] coords = this.getOtherBathCoords(par1World, par2, par3, par4);
        if (this == MrCrayfishFurnitureMod.bath1) {
            if (par1World.getBlock(coords[0], coords[1], coords[2]) != MrCrayfishFurnitureMod.bath2) {
                par1World.setBlockToAir(par2, par3, par4);
            }
        } else if (par1World.getBlock(coords[0], coords[1], coords[2]) != MrCrayfishFurnitureMod.bath1) {
            par1World.setBlockToAir(par2, par3, par4);
        }
    }

    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityBath();
    }

    public Item getItemDropped(int i, Random random, int j) {
        return MrCrayfishFurnitureMod.itemBath;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack(MrCrayfishFurnitureMod.itemBath);
    }

    public void registerIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = Blocks.quartz_block.getBlockTextureFromSide(0);
    }
}

