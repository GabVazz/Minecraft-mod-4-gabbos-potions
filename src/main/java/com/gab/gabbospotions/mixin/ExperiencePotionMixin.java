package com.gab.gabbospotions.mixin;

import com.gab.gabbospotions.moditems.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PotionEntity.class)
public abstract class ExperiencePotionMixin {

    @Inject(method = "onCollision", at = @At("HEAD"))
    private void onPotionCollision(HitResult hitResult, CallbackInfo ci) {
        PotionEntity potionEntity = (PotionEntity) (Object) this;
        World world = potionEntity.world;

        if (!world.isClient) {
            ItemStack stack = potionEntity.getStack();
            Potion potion = PotionUtil.getPotion(stack);

            int experienceLevels = getExperienceLevels(potion);

            if (experienceLevels > 0) {
                if (hitResult instanceof EntityHitResult entityHitResult) {
                    applyEffectToEntity((LivingEntity) entityHitResult.getEntity(), experienceLevels);
                } else {
                    // Get splash effect radius
                    double splashRadius = 3.0; // Adjust as needed
                    Vec3d position = potionEntity.getPos();
                    Box effectArea = new Box(position.subtract(splashRadius, splashRadius, splashRadius), position.add(splashRadius, splashRadius, splashRadius));
                    world.getEntitiesByClass(LivingEntity.class, effectArea, entity -> entity instanceof PlayerEntity).forEach(entity -> applyEffectToEntity(entity, experienceLevels));
                }
            }
        }
    }

    private void applyEffectToEntity(LivingEntity entity, int experienceLevels) {
        if (entity instanceof PlayerEntity player) {
            player.addExperienceLevels(experienceLevels);
            System.out.println("Added " + experienceLevels + " levels to player");
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
