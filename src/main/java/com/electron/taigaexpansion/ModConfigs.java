package com.electron.taigaexpansion;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = TaigaExpansion.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModConfigs {
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final CommonConfig COMMON;
    static {
        final Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        COMMON = specPair.getLeft();
        COMMON_SPEC = specPair.getRight();

    }
    public static class CommonConfig {
        public final Balance balance;
        public CommonConfig(final ForgeConfigSpec.Builder builder) {
            balance = new Balance(builder);
        }
        public static class Balance {
            public static String name = "balance";

            public static ConfigValue<Integer> cheerfulness_level;
            public static ConfigValue<Boolean> only_vanilla_taiga_features;
            public static ConfigValue<Boolean> rotten_logs;
            public static ConfigValue<Boolean> lichen;

            public Balance(ForgeConfigSpec.Builder builder) {
                builder.push(name);
                only_vanilla_taiga_features = builder
                        .comment("Structures and Features appears only in vanilla biomes")
                        .define("only_vanilla_taiga_features", false);
                lichen = builder
                        .comment("Lichen generation")
                        .define("lichen", true);
                rotten_logs = builder
                        .comment("Rotten Logs generation")
                        .define("rotten_logs", true);
                cheerfulness_level = builder
                        .comment("Cheerfulness effect amplifier")
                        .defineInRange("cheerfulness_level", 3, 0, 20);
                builder.pop();
            }
        }
    }
}

