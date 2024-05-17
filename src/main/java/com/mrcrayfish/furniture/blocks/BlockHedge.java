/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLeavesBase
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.ColorizerFoliage
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IShearable
 */
package com.mrcrayfish.furniture.blocks;

import com.mrcrayfish.furniture.FurnitureAchievements;
import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.proxy.CommonProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockHedge
extends BlockLeavesBase
implements IShearable {
    public static final String[] LEAF_TYPES = new String[]{"oak", "spruce", "birch", "jungle"};
    public static final String[][] field_94396_b = new String[][]{{"leaves_oak", "leaves_spruce", "leaves_birch", "leaves_jungle"}, {"leaves_oak_opaque", "leaves_spruce_opaque", "leaves_birch_opaque", "leaves_jungle_opaque"}};
    @SideOnly(value=Side.CLIENT)
    private int field_94394_cP;
    private IIcon[][] iconArray = new IIcon[2][];
    int[] adjacentTreeBlocks;

    public BlockHedge() {
        super(Material.leaves, false);
    }

    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public int getBlockColor() {
        double d0 = 0.5;
        double d1 = 1.0;
        return ColorizerFoliage.getFoliageColor((double)d0, (double)d1);
    }

    public int getRenderColor(int par1) {
        return (par1 & 3) == 1 ? ColorizerFoliage.getFoliageColorPine() : ((par1 & 3) == 2 ? ColorizerFoliage.getFoliageColorBirch() : ColorizerFoliage.getFoliageColorBasic());
    }

    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int l = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        if (l == 1) {
            return ColorizerFoliage.getFoliageColorPine();
        }
        if (l == 2) {
            return ColorizerFoliage.getFoliageColorBirch();
        }
        int i1 = 0;
        int j1 = 0;
        int k1 = 0;
        for (int l1 = -1; l1 <= 1; ++l1) {
            for (int i2 = -1; i2 <= 1; ++i2) {
                int j2 = par1IBlockAccess.getBiomeGenForCoords(par2 + i2, par4 + l1).getBiomeFoliageColor(par2, par3, par4);
                i1 += (j2 & 0xFF0000) >> 16;
                j1 += (j2 & 0xFF00) >> 8;
                k1 += j2 & 0xFF;
            }
        }
        return (i1 / 9 & 0xFF) << 16 | (j1 / 9 & 0xFF) << 8 | k1 / 9 & 0xFF;
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (par1World.isRaining()) {
            if (!World.doesBlockHaveSolidTopSurface((IBlockAccess)par1World, (int)par2, (int)(par3 - 1), (int)par4) && par5Random.nextInt(15) == 1) {
                double d0 = (float)par2 + par5Random.nextFloat();
                double d1 = (double)par3 - 0.05;
                double d2 = (float)par4 + par5Random.nextFloat();
                par1World.spawnParticle("dripWater", d0, d1, d2, 0.0, 0.0, 0.0);
            }
        }
    }

    public Item getItemDropped(int par1, Random par2Random, int par3) {
        if (par1 == 0) {
            return MrCrayfishFurnitureMod.itemHedgeBasic;
        }
        if (par1 == 1) {
            return MrCrayfishFurnitureMod.itemHedgeSpruce;
        }
        if (par1 == 2) {
            return MrCrayfishFurnitureMod.itemHedgeBirch;
        }
        if (par1 == 3) {
            return MrCrayfishFurnitureMod.itemHedgeJungle;
        }
        return null;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        int l = world.getBlockMetadata(x, y, z);
        if (l == 0) {
            return new ItemStack(MrCrayfishFurnitureMod.itemHedgeBasic);
        }
        if (l == 1) {
            return new ItemStack(MrCrayfishFurnitureMod.itemHedgeSpruce);
        }
        if (l == 2) {
            return new ItemStack(MrCrayfishFurnitureMod.itemHedgeBirch);
        }
        if (l == 3) {
            return new ItemStack(MrCrayfishFurnitureMod.itemHedgeJungle);
        }
        return null;
    }

    public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6) {
        super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
    }

    public int damageDropped(int par1) {
        return par1 & 3;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return CommonProxy.renderHedge;
    }

    public boolean canConnectFenceTo(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        Block l = par1IBlockAccess.getBlock(par2, par3, par4);
        if (l != this && l != Blocks.fence_gate) {
            return this != null && this.blockMaterial.isOpaque() && this.renderAsNormalBlock() ? this.blockMaterial != Material.plants : false;
        }
        return true;
    }

    public void addCollisionBoxesToList(World par1World, int i, int j, int k, AxisAlignedBB par5AxisAlignedBB, List arrayList, Entity par7Entity) {
        this.setBlockBounds(0.1875f, 0.0f, 0.1875f, 0.8125f, 1.5f, 0.8125f);
        super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        if (par1World.getBlock(i + 1, j, k) == MrCrayfishFurnitureMod.hedge | par1World.getBlock(i + 1, j, k) == Blocks.fence_gate | par1World.getBlock(i + 1, j, k).isNormalCube()) {
            this.setBlockBounds(0.8125f, 0.0f, 0.1875f, 1.0f, 1.5f, 0.8125f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
        if (par1World.getBlock(i - 1, j, k) == MrCrayfishFurnitureMod.hedge | par1World.getBlock(i - 1, j, k) == Blocks.fence_gate | par1World.getBlock(i - 1, j, k).isNormalCube()) {
            this.setBlockBounds(0.0f, 0.0f, 0.1875f, 0.1875f, 1.5f, 0.8125f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
        if (par1World.getBlock(i, j, k + 1) == MrCrayfishFurnitureMod.hedge | par1World.getBlock(i, j, k + 1) == Blocks.fence_gate | par1World.getBlock(i, j, k + 1).isNormalCube()) {
            this.setBlockBounds(0.1875f, 0.0f, 0.8125f, 0.8125f, 1.5f, 1.0f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
        if (par1World.getBlock(i, j, k - 1) == MrCrayfishFurnitureMod.hedge | par1World.getBlock(i, j, k - 1) == Blocks.fence_gate | par1World.getBlock(i, j, k - 1).isNormalCube()) {
            this.setBlockBounds(0.1875f, 0.0f, 0.0f, 0.8125f, 1.5f, 0.1875f);
            super.addCollisionBoxesToList(par1World, i, j, k, par5AxisAlignedBB, arrayList, par7Entity);
        }
    }

    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        float f = 0.1875f;
        float f1 = 0.8125f;
        float f2 = 0.1875f;
        float f3 = 0.8125f;
        if (par1IBlockAccess.getBlock(par2 + 1, par3, par4) == MrCrayfishFurnitureMod.hedge | par1IBlockAccess.getBlock(par2 + 1, par3, par4) == Blocks.fence_gate | par1IBlockAccess.getBlock(par2 + 1, par3, par4).isNormalCube()) {
            f1 = 1.0f;
        }
        if (par1IBlockAccess.getBlock(par2 - 1, par3, par4) == MrCrayfishFurnitureMod.hedge | par1IBlockAccess.getBlock(par2 - 1, par3, par4) == Blocks.fence_gate | par1IBlockAccess.getBlock(par2 - 1, par3, par4).isNormalCube()) {
            f = 0.0f;
        }
        if (par1IBlockAccess.getBlock(par2, par3, par4 + 1) == MrCrayfishFurnitureMod.hedge | par1IBlockAccess.getBlock(par2, par3, par4 + 1) == Blocks.fence_gate | par1IBlockAccess.getBlock(par2, par3, par4 + 1).isNormalCube()) {
            f3 = 1.0f;
        }
        if (par1IBlockAccess.getBlock(par2, par3, par4 - 1) == MrCrayfishFurnitureMod.hedge | par1IBlockAccess.getBlock(par2, par3, par4 - 1) == Blocks.fence_gate | par1IBlockAccess.getBlock(par2, par3, par4 - 1).isNormalCube()) {
            f2 = 0.0f;
        }
        this.setBlockBounds(f, 0.0f, f2, f1, 1.0f, f3);
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        ((EntityPlayer)par5EntityLivingBase).triggerAchievement((StatBase)FurnitureAchievements.gardening);
    }

    @SideOnly(value=Side.CLIENT)
    protected ItemStack createStackedBlock(int par1) {
        return new ItemStack((Block)this, 1, par1 & 3);
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack((Block)this, 1, world.getBlockMetadata(x, y, z) & 3));
        return ret;
    }

    public IIcon getIcon(int par1, int par2) {
        return (par2 & 3) == 1 ? this.iconArray[this.field_94394_cP][1] : ((par2 & 3) == 3 ? this.iconArray[this.field_94394_cP][3] : ((par2 & 3) == 2 ? this.iconArray[this.field_94394_cP][2] : this.iconArray[this.field_94394_cP][0]));
    }

    public void registerIcons(IIconRegister par1IIconRegister) {
        for (int i = 0; i < field_94396_b.length; ++i) {
            this.iconArray[i] = new IIcon[field_94396_b[i].length];
            for (int j = 0; j < field_94396_b[i].length; ++j) {
                this.iconArray[i][j] = par1IIconRegister.registerIcon(field_94396_b[i][j]);
            }
        }
    }

    public Block setUnlocalizedName(String hedge) {
        return null;
    }
}

