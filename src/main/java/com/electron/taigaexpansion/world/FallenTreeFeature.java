package com.electron.taigaexpansion.world;

import com.electron.taigaexpansion.ModBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LogBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class FallenTreeFeature extends Feature<NoFeatureConfig> {
    public FallenTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49908_1_) {
        super(p_i49908_1_);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        int i = 0;
        BlockState logstate = ModBlocks.ROTTEN_LOG.get().getDefaultState();
        int t = rand.nextInt(4);
        int f = rand.nextInt(10);
        int c = rand.nextInt(6);
        int y = 0;
        if (c > 2 && f >= 6) {
            for (int j = 0; j < c; ++j) {
                if (t >= 2) {
                    BlockPos blockpos = pos.add(1 * y, 0, 0);
                    if (worldIn.isAirBlock(blockpos.up()) && worldIn.getBlockState(blockpos.down()).getBlock() == Blocks.PODZOL || worldIn.getBlockState(blockpos.down()).getBlock() == Blocks.COARSE_DIRT || worldIn.getBlockState(blockpos).getBlock() == Blocks.WATER && worldIn.getBlockState(blockpos.down()).getBlock() != Blocks.WATER) {
                        worldIn.setBlockState(blockpos, logstate.with(LogBlock.AXIS, Direction.Axis.X), 2);
                    } else if (worldIn.isAirBlock(blockpos.up()) && worldIn.getBlockState(blockpos.down(1)).getBlock() == Blocks.PODZOL || worldIn.getBlockState(blockpos.down(1)).getBlock() == Blocks.COARSE_DIRT || worldIn.getBlockState(blockpos.down()).getBlock() == Blocks.WATER && worldIn.getBlockState(blockpos.down(1)).getBlock() != Blocks.WATER) {
                        worldIn.setBlockState(blockpos.down(), logstate.with(LogBlock.AXIS, Direction.Axis.X), 2);
                    }
                    ++i;
                } else if (t < 2) {
                    BlockPos blockpos = pos.add(0, 0, 1 * y);
                    BlockPos blockpos_m = pos.add(0, 1, 5);
                    if (worldIn.isAirBlock(blockpos.up()) && worldIn.getBlockState(blockpos.down()).getBlock() == Blocks.PODZOL || worldIn.getBlockState(blockpos.down()).getBlock() == Blocks.COARSE_DIRT || worldIn.getBlockState(blockpos).getBlock() == Blocks.WATER && worldIn.getBlockState(blockpos.down()).getBlock() != Blocks.WATER) {
                        worldIn.setBlockState(blockpos, logstate.with(LogBlock.AXIS, Direction.Axis.Z), 2);
                    } else if (worldIn.isAirBlock(blockpos.up()) && worldIn.getBlockState(blockpos.down(1)).getBlock() == Blocks.PODZOL || worldIn.getBlockState(blockpos.down(1)).getBlock() == Blocks.COARSE_DIRT || worldIn.getBlockState(blockpos.down()).getBlock() == Blocks.WATER && worldIn.getBlockState(blockpos.down(1)).getBlock() != Blocks.WATER) {
                        worldIn.setBlockState(blockpos.down(), logstate.with(LogBlock.AXIS, Direction.Axis.Z), 2);
                    }
                    ++i;
                }
                y = y + 1;
            }
        }
        return i > 0;
    }
}
