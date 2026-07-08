package net.not_assher.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.not_assher.core.index.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        return new RecipeGenerator(registries, exporter) {
            public void generate() {
                createShaped(RecipeCategory.COMBAT, ModItems.RAPIER)
                        .pattern("  N")
                        .pattern(" N ")
                        .pattern("H  ")
                        .input('N', Items.NETHERITE_SCRAP)
                        .input('H', ModItems.RAPIER_HANDLE)
                        .criterion("has_ingredient", conditionsFromItem(ModItems.RUINED_HANDLE))
                        .offerTo(exporter);
            }
        };
    }

    public String getName() {
        return "Quartermaster Recipes";
    }
}
