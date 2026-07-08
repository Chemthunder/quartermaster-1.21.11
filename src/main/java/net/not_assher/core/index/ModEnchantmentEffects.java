package net.not_assher.core.index;

import net.acoyt.acornlib.api.registrants.EnchantmentEffectRegistrant;
import net.minecraft.component.ComponentType;
import net.minecraft.util.Unit;
import net.not_assher.core.Quartermaster;

public interface ModEnchantmentEffects {
    EnchantmentEffectRegistrant rant = new EnchantmentEffectRegistrant(Quartermaster.MOD_ID);

    ComponentType<Unit> JOUST = rant.register("joust", Unit.CODEC, Unit.PACKET_CODEC);
    ComponentType<Unit> DISARM = rant.register("disarm", Unit.CODEC, Unit.PACKET_CODEC);

    static void init() {}
}
