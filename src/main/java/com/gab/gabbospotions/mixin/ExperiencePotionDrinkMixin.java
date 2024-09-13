package com.gab.gabbospotions.mixin;

import com.gab.gabbospotions.moditems.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PotionItem.class)
public abstract class ExperiencePotionDrinkMixin {

    @Inject(method = "finishUsing", at = @At("HEAD"))
    private void onPotionDrink(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (!world.isClient && user instanceof PlayerEntity player) {
            Potion potion = PotionUtil.getPotion(stack);

            int experienceLevels = getExperienceLevels(potion);
            if (experienceLevels > 0) {
                player.addExperienceLevels(experienceLevels);
                System.out.println("Added " + experienceLevels + " levels to player");
            }
        }
    }

    private int getExperienceLevels(Potion potion) {
        if (potion == ModItems.EXP_POTION_10) return 10;
        if (potion == ModItems.EXP_POTION_20) return 20;
        if (potion == ModItems.EXP_POTION_50) return 50;
        if (potion == ModItems.EXP_POTION_100) return 100;
        return 0;
    }
}
