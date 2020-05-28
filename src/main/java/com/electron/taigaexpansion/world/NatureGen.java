package com.electron.taigaexpansion.world;

import com.electron.taigaexpansion.ModMobs;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class NatureGen {
    public static void initGen() {

        for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
            if (biome.getCategory().equals(Biome.Category.TAIGA)) {
                biome.addFeature(GenerationStage.Decoration.RAW_GENERATION, new FallenTreeFeature(NoFeatureConfig::deserialize).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP.configure(new FrequencyConfig(1))));
                biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(NatureFeatures.BERGENIA_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(1))));
                biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, new LichenFeature(NoFeatureConfig::deserialize).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(4))));

                biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(ModMobs.BROWN_BEAR, 6, 1, 2));
            }
        }
    }
}
