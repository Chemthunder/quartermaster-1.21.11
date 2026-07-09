package net.not_assher.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.not_assher.core.cca.entity.RapierComponent;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Chemthunder
 */
@Mixin(value = LivingEntity.class)
public abstract class LivingEntityMixin {

    @WrapMethod(method = "damage")
    private boolean parry(ServerWorld world, DamageSource source, float amount, Operation<Boolean> original) {
        if (source.getAttacker() instanceof LivingEntity target) {
            RapierComponent rapier = RapierComponent.KEY.get(this);

            if (RapierComponent.KEY.get(target).getParryTicks() <= 0) {
                if (rapier.getParryTicks() > 0) {
                    target.damage(world, source, amount);

                    return original.call(world, source, amount / 2);
                }
            }
        }
        return original.call(world, source, amount);
    }
}
