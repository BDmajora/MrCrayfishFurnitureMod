/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.world.IBlockAccess
 */
package com.mrcrayfish.furniture.util;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class RenderHelper {
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    public static void setRenderBounds(RenderBlocks renderer, int blockMetadata, double x1, double y1, double z1, double x2, double y2, double z2) {
        double[] bounds = RenderHelper.fixRotation(blockMetadata, x1, z1, x2, z2);
        renderer.setRenderBounds(bounds[0], y1, bounds[1], bounds[2], y2, bounds[3]);
    }

    public static void renderBlock(RenderBlocks renderer, Block block, int x, int y, int z) {
        renderer.renderStandardBlock(block, x, y, z);
    }

    public static double[] fixRotation(int metadata, double var1, double var2, double var3, double var4) {
        switch (metadata) {
            case 1: {
                double var_temp_1 = var1;
                var1 = 1.0 - var3;
                double var_temp_2 = var2;
                var2 = 1.0 - var4;
                var3 = 1.0 - var_temp_1;
                var4 = 1.0 - var_temp_2;
                break;
            }
            case 2: {
                double var_temp_3 = var1;
                var1 = var2;
                var2 = 1.0 - var3;
                var3 = var4;
                var4 = 1.0 - var_temp_3;
                break;
            }
            case 0: {
                double var_temp_4 = var1;
                var1 = 1.0 - var4;
                double var_temp_5 = var2;
                var2 = var_temp_4;
                double var_temp_6 = var3;
                var3 = 1.0 - var_temp_5;
                var4 = var_temp_6;
            }
        }
        return new double[]{var1, var2, var3, var4};
    }

    public static boolean isAirBlock(IBlockAccess world, int x, int y, int z, int metadata, int rotation) {
        switch (rotation) {
            case 0: {
                if (metadata == 3) {
                    return world.isAirBlock(x + 1, y, z);
                }
                if (metadata == 1) {
                    return world.isAirBlock(x - 1, y, z);
                }
                if (metadata == 2) {
                    return world.isAirBlock(x, y, z - 1);
                }
                if (metadata == 0) {
                    return world.isAirBlock(x, y, z + 1);
                }
            }
            case 1: {
                if (metadata == 3) {
                    return world.isAirBlock(x - 1, y, z);
                }
                if (metadata == 1) {
                    return world.isAirBlock(x + 1, y, z);
                }
                if (metadata == 2) {
                    return world.isAirBlock(x, y, z + 1);
                }
                if (metadata != 0) break;
                return world.isAirBlock(x, y, z - 1);
            }
            case 2: {
                if (metadata == 2) {
                    return world.isAirBlock(x - 1, y, z);
                }
                if (metadata == 0) {
                    return world.isAirBlock(x + 1, y, z);
                }
                if (metadata == 3) {
                    return world.isAirBlock(x, y, z - 1);
                }
                if (metadata != 1) break;
                return world.isAirBlock(x, y, z + 1);
            }
            case 3: {
                if (metadata == 2) {
                    return world.isAirBlock(x + 1, y, z);
                }
                if (metadata == 0) {
                    return world.isAirBlock(x - 1, y, z);
                }
                if (metadata == 3) {
                    return world.isAirBlock(x, y, z + 1);
                }
                if (metadata != 1) break;
                return world.isAirBlock(x, y, z - 1);
            }
        }
        return false;
    }

    public static Block getBlock(IBlockAccess world, int x, int y, int z, int metadata, int rotation) {
        boolean ID = false;
        switch (rotation) {
            case 0: {
                if (metadata == 3) {
                    return world.getBlock(x + 1, y, z);
                }
                if (metadata == 1) {
                    return world.getBlock(x - 1, y, z);
                }
                if (metadata == 2) {
                    return world.getBlock(x, y, z - 1);
                }
                if (metadata != 0) break;
                return world.getBlock(x, y, z + 1);
            }
            case 1: {
                if (metadata == 3) {
                    return world.getBlock(x - 1, y, z);
                }
                if (metadata == 1) {
                    return world.getBlock(x + 1, y, z);
                }
                if (metadata == 2) {
                    return world.getBlock(x, y, z + 1);
                }
                if (metadata != 0) break;
                return world.getBlock(x, y, z - 1);
            }
            case 2: {
                if (metadata == 2) {
                    return world.getBlock(x - 1, y, z);
                }
                if (metadata == 0) {
                    return world.getBlock(x + 1, y, z);
                }
                if (metadata == 3) {
                    return world.getBlock(x, y, z - 1);
                }
                if (metadata != 1) break;
                return world.getBlock(x, y, z + 1);
            }
            case 3: {
                if (metadata == 2) {
                    return world.getBlock(x + 1, y, z);
                }
                if (metadata == 0) {
                    return world.getBlock(x - 1, y, z);
                }
                if (metadata == 3) {
                    return world.getBlock(x, y, z + 1);
                }
                if (metadata != 1) break;
                return world.getBlock(x, y, z - 1);
            }
        }
        return world.getBlock(x, y, z);
    }

    public static int getRotation(IBlockAccess world, int x, int y, int z, int metadata, int rotation) {
        int blockMetadata = RenderHelper.getMetadata(world, x, y, z, metadata, rotation);
        if (metadata == 3) {
            if (blockMetadata == 3) {
                return 1;
            }
            if (blockMetadata == 1) {
                return 0;
            }
            if (blockMetadata == 2) {
                return 3;
            }
            if (blockMetadata == 0) {
                return 2;
            }
        }
        if (metadata == 1) {
            if (blockMetadata == 3) {
                return 0;
            }
            if (blockMetadata == 1) {
                return 1;
            }
            if (blockMetadata == 2) {
                return 2;
            }
            if (blockMetadata == 0) {
                return 3;
            }
        }
        if (metadata == 2) {
            if (blockMetadata == 3) {
                return 2;
            }
            if (blockMetadata == 1) {
                return 3;
            }
            if (blockMetadata == 2) {
                return 1;
            }
            if (blockMetadata == 0) {
                return 0;
            }
        }
        if (metadata == 0) {
            if (blockMetadata == 3) {
                return 3;
            }
            if (blockMetadata == 1) {
                return 2;
            }
            if (blockMetadata == 2) {
                return 0;
            }
            if (blockMetadata == 0) {
                return 1;
            }
        }
        return 0;
    }

    public static int getMetadata(IBlockAccess world, int x, int y, int z, int metadata, int rotation) {
        int METADATA = 0;
        switch (rotation) {
            case 0: {
                if (metadata == 3) {
                    METADATA = world.getBlockMetadata(x + 1, y, z);
                }
                if (metadata == 1) {
                    METADATA = world.getBlockMetadata(x - 1, y, z);
                }
                if (metadata == 2) {
                    METADATA = world.getBlockMetadata(x, y, z - 1);
                }
                if (metadata != 0) break;
                METADATA = world.getBlockMetadata(x, y, z + 1);
                break;
            }
            case 1: {
                if (metadata == 3) {
                    METADATA = world.getBlockMetadata(x - 1, y, z);
                }
                if (metadata == 1) {
                    METADATA = world.getBlockMetadata(x + 1, y, z);
                }
                if (metadata == 2) {
                    METADATA = world.getBlockMetadata(x, y, z + 1);
                }
                if (metadata != 0) break;
                METADATA = world.getBlockMetadata(x, y, z - 1);
                break;
            }
            case 2: {
                if (metadata == 2) {
                    METADATA = world.getBlockMetadata(x - 1, y, z);
                }
                if (metadata == 0) {
                    METADATA = world.getBlockMetadata(x + 1, y, z);
                }
                if (metadata == 3) {
                    METADATA = world.getBlockMetadata(x, y, z - 1);
                }
                if (metadata != 1) break;
                METADATA = world.getBlockMetadata(x, y, z + 1);
                break;
            }
            case 3: {
                if (metadata == 2) {
                    METADATA = world.getBlockMetadata(x + 1, y, z);
                }
                if (metadata == 0) {
                    METADATA = world.getBlockMetadata(x - 1, y, z);
                }
                if (metadata == 3) {
                    METADATA = world.getBlockMetadata(x, y, z + 1);
                }
                if (metadata != 1) break;
                METADATA = world.getBlockMetadata(x, y, z - 1);
            }
        }
        return METADATA;
    }
}

