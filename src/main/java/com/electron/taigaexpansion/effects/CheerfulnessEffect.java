package com.electron.taigaexpansion.effects;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;

import javax.annotation.Nullable;

public class CheerfulnessEffect  extends Effect {
    public CheerfulnessEffect() {
        super(EffectType.NEUTRAL, 3381504);

    }

    public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity entityLivingBaseIn, int amplifier, double health) {
        for (EffectInstance effect : entityLivingBaseIn.getActivePotionEffects()) {
            ((PlayerEntity) entityLivingBaseIn).getFoodStats().setFoodLevel(((PlayerEntity) entityLivingBaseIn).getFoodStats().getFoodLevel() + 8);
            entityLivingBaseIn.addPotionEffect(new EffectInstance(effect.getPotion(), effect.getDuration() * (amplifier), effect.getAmplifier(), effect.isAmbient(), effect.doesShowParticles(), effect.isShowIcon()));
        }
    }
}