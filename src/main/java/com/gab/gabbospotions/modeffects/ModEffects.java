package com.gab.gabbospotions.modeffects;

import com.gab.gabbospotions.GabbosPotions;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.SplashPotionItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEffects {
    public static final StatusEffect LUCKY = new LuckyEffect();
    public static final StatusEffect ONE_SHOT = new OneShotEffect();
    public static final StatusEffect EXPERIENCE_BOOST = new ExperienceBoostEffect();

    public static void registraEffetti() {
        Registry.register(Registry.STATUS_EFFECT, new Identifier(GabbosPotions.MOD_ID, "lucky"), LUCKY);
        Registry.register(Registry.STATUS_EFFECT, new Identifier(GabbosPotions.MOD_ID, "one_shot"), ONE_SHOT);
        Registry.register(Registry.STATUS_EFFECT, new Identifier(GabbosPotions.MOD_ID, "experience_boost"), EXPERIENCE_BOOST);
    }
}