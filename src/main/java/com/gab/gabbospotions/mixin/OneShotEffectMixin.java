package com.gab.gabbospotions.mixin;

import com.gab.gabbospotions.modeffects.ModEffects;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(PlayerEntity.class)
public abstract class OneShotEffectMixin {

    @Unique
    private static final UUID ONE_SHOT_DAMAGE_BOOST_UUID = UUID.fromString("c607b1d5-df34-4f18-8f68-4a7d5b5e3e28");
    @Unique
    EntityAttributeModifier oneShotAttributeModifier = new EntityAttributeModifier(
            ONE_SHOT_DAMAGE_BOOST_UUID,
            "OneShotDamageBoost",
            10000.0,  // Valore molto alto per "One Shot"
            EntityAttributeModifier.Operation.ADDITION);

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        EntityAttributeInstance attackDamageAttribute = player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        if (attackDamageAttribute != null) {
            boolean hasModifier = attackDamageAttribute.hasModifier(oneShotAttributeModifier);
            StatusEffectInstance oneShotEffect = player.getStatusEffect(ModEffects.ONE_SHOT);

            if (oneShotEffect != null && !hasModifier) {
                attackDamageAttribute.addPersistentModifier(oneShotAttributeModifier);
            } else if (oneShotEffect == null && hasModifier) {
                attackDamageAttribute.removeModifier(oneShotAttributeModifier);
            }
        }
    }
}
