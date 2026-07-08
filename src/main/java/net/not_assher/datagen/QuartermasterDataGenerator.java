package net.not_assher.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.not_assher.core.index.data.ModEnchantments;
import net.not_assher.datagen.providers.*;

public class QuartermasterDataGenerator implements DataGeneratorEntrypoint {
	public void onInitializeDataGenerator(FabricDataGenerator fdg) {
        var pack = fdg.createPack();

        pack.addProvider(ModDynamicRegistryProvider::new);
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModAdvancementProvider::new);

        pack.addProvider(ModTagProviders.ItemTags::new);
        pack.addProvider(ModTagProviders.EnchantTags::new);

        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModLanguageProvider::new);
	}

    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.ENCHANTMENT, ModEnchantments::bootstrap);    }
}
