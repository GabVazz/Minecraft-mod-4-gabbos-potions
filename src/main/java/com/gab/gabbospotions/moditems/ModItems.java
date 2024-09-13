package com.gab.gabbospotions.moditems;

import com.gab.gabbospotions.GabbosPotions;
import com.gab.gabbospotions.modeffects.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.LingeringPotionItem;
import net.minecraft.item.PotionItem;
import net.minecraft.item.SplashPotionItem;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    // Pozioni Bevibili
    public static Potion LUCKY_POTION;
    public static Potion ONE_SHOT_POTION;

    // Pozioni Splash
    public static Potion EXP_POTION_10;
    public static Potion EXP_POTION_20;
    public static Potion EXP_POTION_50;
    public static Potion EXP_POTION_100;

    public static Potion registraPozione(String nome, Potion pozione) {
        return Registry.register(Registry.POTION, new Identifier(GabbosPotions.MOD_ID, nome), pozione);
    }

    public static Item registraItem(String nome, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(GabbosPotions.MOD_ID, nome), item);
    }

    public static void registraItems() {
        LUCKY_POTION = registraPozione("lucky_potion", new Potion(new StatusEffectInstance(ModEffects.LUCKY, 72000)));
        ONE_SHOT_POTION = registraPozione("one_shot_potion", new Potion(new StatusEffectInstance(ModEffects.ONE_SHOT, 72000)));

        EXP_POTION_10 = registraPozione("experience_potion_10", new Potion(new StatusEffectInstance(ModEffects.EXPERIENCE_BOOST, 1, 0)));
        EXP_POTION_20 = registraPozione("experience_potion_20", new Potion(new StatusEffectInstance(ModEffects.EXPERIENCE_BOOST, 1, 1)));
        EXP_POTION_50 = registraPozione("experience_potion_50", new Potion(new StatusEffectInstance(ModEffects.EXPERIENCE_BOOST, 1, 2)));
        EXP_POTION_100 = registraPozione("experience_potion_100", new Potion(new StatusEffectInstance(ModEffects.EXPERIENCE_BOOST, 1, 3)));
    }
}
