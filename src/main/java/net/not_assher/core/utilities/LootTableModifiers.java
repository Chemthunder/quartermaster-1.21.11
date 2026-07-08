package net.not_assher.core.utilities;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.not_assher.core.index.ModItems;

public class LootTableModifiers implements LootTableEvents.Modify {
    private static final Identifier TREASURE_ID = Identifier.ofVanilla("chests/buried_treasure");

    public static void init() {
        LootTableEvents.MODIFY.register(new LootTableModifiers());
    }

    public void modifyLootTable(RegistryKey<LootTable> key, LootTable.Builder tableBuilder, LootTableSource source, RegistryWrapper.WrapperLookup registries) {
        if (TREASURE_ID.equals(key.getValue())) {
            LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1.0F))
                    .conditionally(RandomChanceLootCondition.builder(0.75F))
                    .with(ItemEntry.builder(ModItems.RUINED_HANDLE)
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 1.0F)))
                            .apply(EnchantedCountIncreaseLootFunction.builder(registries, UniformLootNumberProvider.create(0.0F, 1.0F))));

            tableBuilder.pool(poolBuilder);
        }
    }
}
