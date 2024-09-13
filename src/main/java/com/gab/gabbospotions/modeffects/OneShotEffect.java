package com.gab.gabbospotions.modeffects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;


public class OneShotEffect extends StatusEffect {

    public OneShotEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xDC143C); // Colore dell'effetto (esadecimale)
    }
}
