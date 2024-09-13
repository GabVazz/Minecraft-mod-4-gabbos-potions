package com.gab.gabbospotions.modeffects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;

public class ExperienceBoostEffect extends StatusEffect {

    public ExperienceBoostEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0x5A6E21);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            int experienceAmount = getExperienceAmount(amplifier);
            player.addExperience(experienceAmount);
            System.out.println("Experience boost applied: " + experienceAmount);
        }
    }

    // Metodo per determinare l'importo di esperienza basato sul livello dell'effetto
    private int getExperienceAmount(int amplifier) {
        switch (amplifier) {
            case 0: return 10;
            case 1: return 20;
            case 2: return 50;
            case 3: return 100;
            default: return 0;
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // Forza l'applicazione immediata dell'effetto
        return true;
    }

}
