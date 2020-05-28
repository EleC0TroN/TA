package com.electron.taigaexpansion.world;

import com.electron.taigaexpansion.ModBlocks;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;

public class NatureFeatures {
    public static final BlockClusterFeatureConfig BERGENIA_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.BERGENIA.get().getDefaultState()), new SimpleBlockPlacer())).tries(2).build();

}
