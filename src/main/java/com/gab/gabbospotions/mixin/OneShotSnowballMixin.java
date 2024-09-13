package com.gab.gabbospotions.mixin;

import com.gab.gabbospotions.modeffects.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SnowballEntity.class)
public abstract class OneShotSnowballMixin {

    @Inject(method = "onCollision", at = @At("HEAD"))
    private void onCollision(HitResult hitResult, CallbackInfo ci) {
        SnowballEntity snowballEntity = (SnowballEntity) (Object) this;
        if (snowballEntity.getOwner() instanceof PlayerEntity player) {
            if (player.hasStatusEffect(ModEffects.ONE_SHOT)) {
                if (hitResult instanceof EntityHitResult entityHitResult) {
                    // Gestione dell'entità colpita
                    LivingEntity target;
                    if (entityHitResult.getEntity() instanceof LivingEntity livingEntity) {
                        target = livingEntity;
                        // Applica il danno a qualsiasi entità di tipo LivingEntity
                        target.damage(DamageSource.thrownProjectile(snowballEntity, player), 10000.0F); // Usa un valore alto ma non massimo
                    }
                }
            }
        }
    }
}
