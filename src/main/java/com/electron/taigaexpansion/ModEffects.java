package com.electron.taigaexpansion;

import com.electron.taigaexpansion.effects.CheerfulnessEffect;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEffects
{
    public static final DeferredRegister<Effect> EFFECTS = new DeferredRegister<>(ForgeRegistries.POTIONS, TaigaExpansion.MODID);

    public static final RegistryObject<Effect> CHEERFULNESS = EFFECTS.register("cheerfulness",  () -> new CheerfulnessEffect());

}
