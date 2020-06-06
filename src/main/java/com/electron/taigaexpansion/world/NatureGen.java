package com.electron.taigaexpansion.world;

import com.electron.taigaexpansion.ModConfigs;
import com.electron.taigaexpansion.ModMobs;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class NatureGen {
    public static void initGen() {
    if (!ModConfigs.COMMON.balance.only_vanilla_taiga_features.get()) {
        for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
            if (biome.getCategory().equals(Biome.Category.TAIGA)) {
                if (ModConfigs.COMMON.balance.rotten_logs.get()) {
                    biome.addFeature(GenerationStage.Decoration.RAW_GENERATION, new FallenTreeFeature(NoFeatureConfig::deserialize).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP.configure(new FrequencyConfig(1))));
                }
                biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(NatureFeatures.BERGENIA_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(1))));
                if (ModConfigs.COMMON.balance.lichen.get()) { biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, new LichenFeature(NoFeatureConfig::deserialize).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(4)))); }

                biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(ModMobs.BROWN_BEAR, 6, 1, 2));
                }
            }
        } else if (ModConfigs.COMMON.balance.only_vanilla_taiga_features.get()){
            if (ModConfigs.COMMON.balance.rotten_logs.get()) { Biomes.TAIGA.addFeature(GenerationStage.Decoration.RAW_GENERATION, new FallenTreeFeature(NoFeatureConfig::deserialize).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP.configure(new FrequencyConfig(1)))); }
            Biomes.TAIGA.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(NatureFeatures.BERGENIA_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(1))));
            if (ModConfigs.COMMON.balance.lichen.get()) { Biomes.TAIGA.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, new LichenFeature(NoFeatureConfig::deserialize).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(4)))); }

            Biomes.TAIGA.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(ModMobs.BROWN_BEAR, 6, 1, 2));
        }
    }
}
