package com.gab.gabbospotions.mixin;

import com.gab.gabbospotions.modeffects.ModEffects;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LuckyDropMixin {

    @Inject(method = "dropLoot", at = @At("TAIL"))
    private void onDropLoot(DamageSource source, boolean causedByPlayer, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        World world = entity.getEntityWorld();

        // Verifica se l'entità è stata uccisa da un player
        if (source.getAttacker() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) source.getAttacker();

            // Verifica se il player ha lo status effect LUCKY
            if (!world.isClient && player.hasStatusEffect(ModEffects.LUCKY)) {
                int multiplier = 5; // Moltiplicatore di drop

                // Log per debug
                System.out.println("LuckyDropMixin: Moltiplicatore impostato a " + multiplier + " per i drop uccisi dal giocatore " + player.getName().getString());

                // Cerca gli ItemEntity generati attorno all'entità dopo il drop
                for (ItemEntity itemEntity : world.getEntitiesByClass(ItemEntity.class, entity.getBoundingBox().expand(5.0), i -> true)) {
                    ItemStack stack = itemEntity.getStack();

                    // Log per debug
                    System.out.println("LuckyDropMixin: Trovato drop " + stack.getItem().getName().getString() + " con quantità " + stack.getCount());

                    // Duplica l'oggetto il numero di volte specificato dal moltiplicatore
                    for (int i = 1; i < multiplier; i++) {
                        ItemEntity duplicate = new ItemEntity(world, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), stack.copy());
                        world.spawnEntity(duplicate);
                    }
                }
            }
        }
    }
}
