package com.electron.taigaexpansion.world;

import com.electron.taigaexpansion.ModBlocks;
import com.electron.taigaexpansion.blocks.LichenBlock;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;
public class  LichenFeature extends Feature<NoFeatureConfig> {
    public LichenFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49908_1_) {
        super(p_i49908_1_);
    }
    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        int i = 0;
        BlockState greenstate = ModBlocks.GREEN_LICHEN.get().getDefaultState();
        BlockState greystate = ModBlocks.GREY_LICHEN.get().getDefaultState();

        for(int j = 0; j < 64; ++j) {
            int c = rand.nextInt(4);
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(6), rand.nextInt(6) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(6));
            if (worldIn.isAirBlock(blockpos) && worldIn.getBlockState(blockpos.west()).getBlock() == Blocks.SPRUCE_LOG || worldIn.getBlockState(blockpos.west()).getBlock() == ModBlocks.ROTTEN_LOG.get()) {
                if (c < 3) {
                    worldIn.setBlockState(blockpos, greenstate.with(LichenBlock.FACING, Direction.EAST), 2);
                } else if (c <=4 && c >= 3) {
                    worldIn.setBlockState(blockpos, greystate.with(LichenBlock.FACING, Direction.EAST), 2);
                }
                ++i;

            }
            if (worldIn.isAirBlock(blockpos) && worldIn.getBlockState(blockpos.north()).getBlock() == Blocks.SPRUCE_LOG || worldIn.getBlockState(blockpos.north()).getBlock() == Blocks.MOSSY_COBBLESTONE) {
                if (c < 3) {
                    worldIn.setBlockState(blockpos, greenstate.with(LichenBlock.FACING, Direction.SOUTH), 2);
                } else if (c == 3) {
                    worldIn.setBlockState(blockpos, greystate.with(LichenBlock.FACING, Direction.SOUTH), 2);
                }
                ++i;
            }
            if (worldIn.isAirBlock(blockpos) && worldIn.getBlockState(blockpos.south()).getBlock() == Blocks.SPRUCE_LOG || worldIn.getBlockState(blockpos.south()).getBlock() == ModBlocks.ROTTEN_LOG.get()) {
                if (c < 3) {
                    worldIn.setBlockState(blockpos, greenstate.with(LichenBlock.FACING, Direction.NORTH), 2);
                } else if (c == 3) {
                    worldIn.setBlockState(blockpos, greystate.with(LichenBlock.FACING, Direction.NORTH), 2);
                }
                ++i;
            }
            if (worldIn.isAirBlock(blockpos) && worldIn.getBlockState(blockpos.east()).getBlock() == Blocks.SPRUCE_LOG) {
                if (c < 3) {
                    worldIn.setBlockState(blockpos, greenstate.with(LichenBlock.FACING, Direction.WEST), 2);
                } else if (c == 3) {
                    worldIn.setBlockState(blockpos, greystate.with(LichenBlock.FACING, Direction.WEST), 2);
                }
                ++i;
            }
        }
        return i > 0;
    }
}