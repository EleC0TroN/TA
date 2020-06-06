package com.electron.taigaexpansion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TaigaExpansion.MODID)
public class ModEvents {
    @SubscribeEvent
    public static void onPlayerLiving(LivingEvent event) {
     if (event.getEntityLiving() instanceof PlayerEntity) {
         if (event.getEntityLiving().isPotionActive(ModEffects.CHEERFULNESS.get())) {
             ((PlayerEntity) event.getEntityLiving()).getFoodStats().setFoodSaturationLevel(ModConfigs.COMMON.balance.cheerfulness_level.get());
         }
     }
    }
}
