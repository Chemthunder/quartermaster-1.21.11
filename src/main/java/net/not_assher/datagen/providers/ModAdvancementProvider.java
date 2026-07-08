package net.not_assher.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.not_assher.core.Quartermaster;
import net.not_assher.core.index.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@SuppressWarnings("removal")
public class ModAdvancementProvider extends FabricAdvancementProvider {
    public ModAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer) {
        AdvancementEntry ahoy = Advancement.Builder.createUntelemetered()
                .parent(Identifier.ofVanilla("adventure/root"))
                .display(
                        ModItems.RAPIER,
                        Text.literal("Ahoy me Hearties!"),
                        Text.literal("Collect a fanciful weapon, only for a traveler of the Seven Seas."),
                        null,
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        true
                ).requirements(AdvancementRequirements.allOf(List.of("e")))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
                .criterion("e", InventoryChangedCriterion.Conditions.items(ModItems.RAPIER))
                .build(Quartermaster.id("ahoy_me_hearties"));

        consumer.accept(ahoy);
    }
}
