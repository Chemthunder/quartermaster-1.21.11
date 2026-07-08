package net.not_assher.core.client.item;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.render.item.property.bool.BooleanProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.not_assher.core.Quartermaster;
import net.not_assher.core.index.ModDataComponentTypes;
import org.jspecify.annotations.Nullable;

public record CoralRapierBooleanProperty() implements BooleanProperty {
    public static final Identifier ID = Quartermaster.id("coral_rapier");
    public static final MapCodec<CoralRapierBooleanProperty> CODEC = MapCodec.unit(CoralRapierBooleanProperty::new);

    public MapCodec<? extends BooleanProperty> getCodec() {
        return CODEC;
    }

    public boolean test(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity entity, int seed, ItemDisplayContext displayContext) {
        return stack.getOrDefault(ModDataComponentTypes.IS_CORAL, false);
    }
}
