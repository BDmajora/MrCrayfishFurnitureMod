/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.stats.StatBase
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.mrcrayfish.furniture.blocks;

import com.mrcrayfish.furniture.FurnitureAchievements;
import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.proxy.ClientProxy;
import com.mrcrayfish.furniture.proxy.CommonProxy;
import com.mrcrayfish.furniture.tileentity.TileEntityMailBox;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMailBox
extends BlockContainer {
    private Random random = new Random();

    public BlockMailBox(Material material) {
        super(material);
        this.setHardness(0.5f);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return CommonProxy.renderMailBox;
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
        this.setBlockBounds(0.4375f, 0.0f, 0.4375f, 0.5625f, 0.8f, 0.5625f);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        int l = par1World.getBlockMetadata(i, j, k);
        if (l == 3) {
            this.setBlockBounds(0.25f, 0.8625f, 0.25f, 0.75f, 1.1624f, 0.3125f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
            this.setBlockBounds(0.25f, 0.8625f, 0.6875f, 0.75f, 1.1624f, 0.75f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
            this.setBlockBounds(0.6875f, 0.8625f, 0.3125f, 0.75f, 1.1624f, 0.6875f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
            this.setBlockBounds(0.25f, 0.8625f, 0.3125f, 0.3125f, 1.0f, 0.6875f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
        if (l == 1) {
            this.setBlockBounds(0.25f, 0.8625f, 0.25f, 0.75f, 1.1624f, 0.3125f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
            this.setBlockBounds(0.25f, 0.8625f, 0.6875f, 0.75f, 1.1624f, 0.75f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
            this.setBlockBounds(0.6875f, 0.8625f, 0.3125f, 0.75f, 1.0f, 0.6875f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
            this.setBlockBounds(0.25f, 0.8625f, 0.3125f, 0.3125f, 1.1624f, 0.6875f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
        if (l == 2) {
            this.setBlockBounds(0.3125f, 0.8625f, 0.25f, 0.6875f, 1.1624f, 0.3125f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
            this.setBlockBounds(0.3125f, 0.8625f, 0.6875f, 0.6875f, 1.0f, 0.75f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
            this.setBlockBounds(0.6875f, 0.8625f, 0.25f, 0.75f, 1.1624f, 0.75f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
            this.setBlockBounds(0.25f, 0.8625f, 0.25f, 0.3125f, 1.1624f, 0.75f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
        if (l == 0) {
            this.setBlockBounds(0.3125f, 0.8625f, 0.25f, 0.6875f, 1.0f, 0.3125f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
            this.setBlockBounds(0.3125f, 0.8625f, 0.6875f, 0.6875f, 1.1624f, 0.75f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
            this.setBlockBounds(0.6875f, 0.8625f, 0.25f, 0.75f, 1.1624f, 0.75f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
            this.setBlockBounds(0.25f, 0.8625f, 0.25f, 0.3125f, 1.1624f, 0.75f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
        this.setBlockBounds(0.25f, 0.8f, 0.25f, 0.75f, 0.8625f, 0.75f);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        this.setBlockBounds(0.1875f, 1.1f, 0.1875f, 0.8125f, 1.1625f, 0.8125f);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        this.setBlockBounds(0.1875f, 0.0f, 0.1875f, 0.8125f, 1.1625f, 0.8125f);
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        this.setBlockBounds(0.1875f, 0.0f, 0.1875f, 0.8125f, 1.1625f, 0.8125f);
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        if (par5EntityLivingBase instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)par5EntityLivingBase;
            player.triggerAchievement((StatBase)FurnitureAchievements.mailBox);
            if (!par1World.isRemote) {
                player.addChatComponentMessage((IChatComponent)new ChatComponentText("Now right click the mailbox to claim ownership"));
            }
        }
    }

    public Item getItemDropped(int i, Random random, int j) {
        return MrCrayfishFurnitureMod.itemMailBox;
    }

    public float getExplosionResistance(Entity entity) {
        if (entity instanceof EntityCreeper) {
            return 1000.0f;
        }
        return this.blockResistance / 5.0f;
    }

    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        TileEntityMailBox var7 = (TileEntityMailBox)par1World.getTileEntity(par2, par3, par4);
        if (var7 != null && !var7.locked) {
            for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8) {
                ItemStack var9 = var7.getStackInSlot(var8);
                if (var9 == null) continue;
                float var10 = this.random.nextFloat() * 0.8f + 0.1f;
                float var11 = this.random.nextFloat() * 0.8f + 0.1f;
                float var12 = this.random.nextFloat() * 0.8f + 0.1f;
                while (var9.stackSize > 0) {
                    int var13 = this.random.nextInt(21) + 10;
                    if (var13 > var9.stackSize) {
                        var13 = var9.stackSize;
                    }
                    var9.stackSize -= var13;
                    EntityItem var14 = new EntityItem(par1World, (double)((float)par2 + var10), (double)((float)par3 + var11), (double)((float)par4 + var12), new ItemStack(var9.getItem(), var13, var9.getItemDamage()));
                    float var15 = 0.05f;
                    var14.motionX = (float)this.random.nextGaussian() * var15;
                    var14.motionY = (float)this.random.nextGaussian() * var15 + 0.2f;
                    var14.motionZ = (float)this.random.nextGaussian() * var15;
                    if (var9.hasTagCompound()) {
                        var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                    }
                    par1World.spawnEntityInWorld((Entity)var14);
                }
            }
            super.breakBlock(par1World, par2, par3, par4, par5, par6);
        }
    }

    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
        TileEntityMailBox tileEntityMailBox = (TileEntityMailBox)world.getTileEntity(x, y, z);
        System.out.println("Calling on block desotroyed");
        if (tileEntityMailBox == null) {
            System.out.println("Tile Entity is Null");
            return false;
        }
        int meta = world.getBlockMetadata(x, y, z);
        if (tileEntityMailBox.locked) {
            world.setBlock(x, y, z, (Block)this, meta, 2);
            if (!world.isRemote) {
                player.addChatComponentMessage((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "You need to unlock the mail box to destory it. Crouch and " + EnumChatFormatting.RED + "right click with your bare hand to unlock."));
                return false;
            }
        } else {
            world.setBlockToAir(x, y, z);
            this.breakBlock(world, x, y, z, (Block)this, meta);
        }
        return true;
    }

    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityMailBox();
    }

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        TileEntity tile_entity = par1World.getTileEntity(par2, par3, par4);
        if (!par1World.isRemote && tile_entity instanceof TileEntityMailBox) {
            TileEntityMailBox tileEntityMailBox = (TileEntityMailBox)tile_entity;
            if (tileEntityMailBox.ownerName.isEmpty()) {
                tileEntityMailBox.setOwner(par5EntityPlayer);
                par5EntityPlayer.addChatComponentMessage((IChatComponent)new ChatComponentText("Successfully set the owner of the mail box to " + EnumChatFormatting.YELLOW + par5EntityPlayer.getDisplayName()));
                par1World.markBlockForUpdate(par2, par3, par4);
                return true;
            }
            tileEntityMailBox.tryAndUpdateName(par5EntityPlayer);
            if (!par5EntityPlayer.isSneaking()) {
                if (tileEntityMailBox.canOpen(tileEntityMailBox, par5EntityPlayer) && tileEntityMailBox.locked) {
                    par5EntityPlayer.openGui((Object)MrCrayfishFurnitureMod.instance, 0, par1World, par2, par3, par4);
                } else if (!par1World.isRemote && tileEntityMailBox.locked) {
                    par5EntityPlayer.addChatComponentMessage((IChatComponent)new ChatComponentText("This mail box belongs to " + EnumChatFormatting.YELLOW + tileEntityMailBox.ownerName));
                } else if (!tileEntityMailBox.locked) {
                    par5EntityPlayer.openGui((Object)MrCrayfishFurnitureMod.instance, 0, par1World, par2, par3, par4);
                }
            } else if (tileEntityMailBox.canOpen(tileEntityMailBox, par5EntityPlayer)) {
                if (tileEntityMailBox.locked) {
                    tileEntityMailBox.locked = false;
                    par5EntityPlayer.addChatComponentMessage((IChatComponent)new ChatComponentText(EnumChatFormatting.GREEN + "Mailbox unlocked."));
                } else if (!tileEntityMailBox.locked) {
                    tileEntityMailBox.locked = true;
                    par5EntityPlayer.addChatComponentMessage((IChatComponent)new ChatComponentText(EnumChatFormatting.GREEN + "Mailbox locked."));
                }
            } else {
                par5EntityPlayer.addChatComponentMessage((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "You don't have permission to unlock this mail box."));
            }
        }
        return true;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack(MrCrayfishFurnitureMod.itemMailBox);
    }

    public void registerIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = Blocks.log.getBlockTextureFromSide(2);
    }
}

