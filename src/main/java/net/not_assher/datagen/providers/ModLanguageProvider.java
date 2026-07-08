package net.not_assher.datagen.providers;

import net.acoyt.acornlib.api.util.DataUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import net.not_assher.core.index.ModItems;
import net.not_assher.core.index.data.ModEnchantments;

import java.util.concurrent.CompletableFuture;

public class ModLanguageProvider extends FabricLanguageProvider {
    public ModLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        ModItems.rant.registerLang(wrapperLookup, translationBuilder);

        DataUtils.registerEnchantment(translationBuilder,
                ModEnchantments.JOUST,
                "Joust",
                "Swaps the parry for a tremendous boost of speed into the air."
        );
    }
}
