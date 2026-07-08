package net.not_assher.core.index.data;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.not_assher.core.Quartermaster;
import net.not_assher.core.index.ModEnchantmentEffects;
import net.not_assher.core.index.tag.ModItemTags;

public interface ModEnchantments {
    RegistryKey<Enchantment> JOUST = create("joust");
    RegistryKey<Enchantment> DISARM = create("disarm");

    private static RegistryKey<Enchantment> create(String id) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, Quartermaster.id(id));
    }

    static void bootstrap(Registerable<Enchantment> registerable) {
        RegistryEntryLookup<Item> itemLookup = registerable.getRegistryLookup(RegistryKeys.ITEM);

        registerable.register(JOUST, Enchantment.builder(Enchantment.definition(
                                itemLookup.getOrThrow(ModItemTags.RAPIERS),
                                6,
                                1,
                                Enchantment.leveledCost(5, 0),
                                Enchantment.leveledCost(17, 0),
                                7,
                                AttributeModifierSlot.MAINHAND
                        ))
                        .addEffect(ModEnchantmentEffects.JOUST)
                        .build(JOUST.getValue())
        );

        registerable.register(DISARM, Enchantment.builder(Enchantment.definition(
                                itemLookup.getOrThrow(ModItemTags.RAPIERS),
                                6,
                                1,
                                Enchantment.leveledCost(5, 0),
                                Enchantment.leveledCost(17, 0),
                                7,
                                AttributeModifierSlot.MAINHAND
                        ))
                        .addEffect(ModEnchantmentEffects.DISARM)
                        .build(DISARM.getValue())
        );
    }
}

// RegistryEntryList<Item> supportedItems,
// RegistryEntryList<Item> primaryItems, int weight, int maxLevel, Cost minCost, Cost maxCost, int anvilCost, AttributeModifierSlot... slots