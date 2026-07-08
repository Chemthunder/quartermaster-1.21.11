package net.not_assher.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EnchantmentTags;
import net.not_assher.core.index.ModItems;
import net.not_assher.core.index.data.ModEnchantments;
import net.not_assher.core.index.tag.ModEnchantmentTags;
import net.not_assher.core.index.tag.ModItemTags;

import java.util.concurrent.CompletableFuture;

public class ModTagProviders {
    public static final class ItemTags extends FabricTagProvider.ItemTagProvider {
        public ItemTags(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        protected void configure(RegistryWrapper.WrapperLookup registries) {
            this.valueLookupBuilder(ModItemTags.RAPIERS)
                    .add(ModItems.RAPIER)
                    .setReplace(false);
        }
    }

    public static final class EnchantTags extends FabricTagProvider<Enchantment> {
        public EnchantTags(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, RegistryKeys.ENCHANTMENT, registriesFuture);
        }

        protected void configure(RegistryWrapper.WrapperLookup registries) {
            this.getTagBuilder(EnchantmentTags.NON_TREASURE)
                    .add(ModEnchantments.JOUST.getValue())
                    .build();

            this.getTagBuilder(ModEnchantmentTags.RAPIER_ENCHANTABLE)
                    .add(Enchantments.KNOCKBACK.getValue())
                    .add(Enchantments.FIRE_ASPECT.getValue())
                    .add(Enchantments.SHARPNESS.getValue())
                    .add(Enchantments.BANE_OF_ARTHROPODS.getValue())
                    .add(Enchantments.LOOTING.getValue())
                    .add(Enchantments.SMITE.getValue())
                    .add(Enchantments.FEATHER_FALLING.getValue())
                    .add(ModEnchantments.JOUST.getValue())
                    .build();
        }
    }
}
