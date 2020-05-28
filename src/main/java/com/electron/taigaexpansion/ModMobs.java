package com.electron.taigaexpansion;

import com.electron.taigaexpansion.mobs.BrownBearMob;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = TaigaExpansion.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModMobs {
    public static EntityType<BrownBearMob> BROWN_BEAR;

    @SubscribeEvent
    public static void initEntities(RegistryEvent.Register<EntityType<?>> event) {
        registerEntity(BROWN_BEAR);
    }

    public static void preInitEntityTypes() {
        BROWN_BEAR = setupEntity("brown_bear", BROWN_BEAR, BrownBearMob::new, EntityClassification.CREATURE, 64, 1.4F, 1.4F);
    }

    public static <T extends Entity> EntityType<T> setupEntity(String name, EntityType<T> entityType, EntityType.IFactory<T> entityTypeFactory,
                                                               EntityClassification classification, int range, float width, float height) {
        entityType = EntityType.Builder.create(entityTypeFactory, classification)
                .setTrackingRange(range)
                .size(width, height)
                .build(name);
        entityType.setRegistryName(new ResourceLocation(TaigaExpansion.MODID, name));

        return entityType;
    }

    public static <T extends Entity> void registerEntity(EntityType<T> entityType) {
        ForgeRegistries.ENTITIES.register(entityType);
    }
}
