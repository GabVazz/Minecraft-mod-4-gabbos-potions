package com.gab.gabbospotions.mixin;

import com.gab.gabbospotions.modeffects.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public abstract class OneShotProjectileMixin {

    @Inject(method = "onHit", at = @At("HEAD"))
    private void onHit(LivingEntity target, CallbackInfo ci) {
        PersistentProjectileEntity projectile = (PersistentProjectileEntity) (Object) this;
        if (projectile.getOwner() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) projectile.getOwner();
            if (player.hasStatusEffect(ModEffects.ONE_SHOT)) {
                target.damage(DamageSource.thrownProjectile(projectile, player), Float.MAX_VALUE);
            }
        }
    }
}

