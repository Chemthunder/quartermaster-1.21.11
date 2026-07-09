package net.not_assher.datagen.providers;

import net.acoyt.acornlib.api.util.DataUtils;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.client.render.item.property.select.DisplayContextProperty;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.util.Identifier;
import net.not_assher.core.client.item.CoralRapierBooleanProperty;
import net.not_assher.core.index.ModItems;

import java.util.Arrays;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {}

    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        createRapier(itemModelGenerator);

        itemModelGenerator.register(ModItems.RAPIER_HANDLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RUINED_HANDLE, Models.GENERATED);

        DataUtils.createSimpleGuiVarying(itemModelGenerator, ModItems.CUTLASS);
        DataUtils.createSimpleGuiVarying(itemModelGenerator, ModItems.MORNINGSTAR);
    }

    private static void createRapier(ItemModelGenerator generator) {
        Identifier baseId = ModelIds.getItemModelId(ModItems.RAPIER);

        generator.upload(ModItems.RAPIER, Models.GENERATED);
        generator.registerSubModel(ModItems.RAPIER, "_coral", Models.GENERATED);

        generator.output.accept(ModItems.RAPIER,
                ItemModels.condition(
                        new CoralRapierBooleanProperty(),
                        ItemModels.select(
                                new DisplayContextProperty(),
                                ItemModels.basic(baseId.withPath(s -> s + "_coral_in_hand")),
                                ItemModels.switchCase(
                                        Arrays.asList(
                                                ItemDisplayContext.GUI,
                                                ItemDisplayContext.GROUND,
                                                ItemDisplayContext.FIXED,
                                                ItemDisplayContext.ON_SHELF
                                        ),
                                        ItemModels.basic(baseId.withPath(s -> s + "_coral"))
                                )
                        ),
                        ItemModels.select(
                                new DisplayContextProperty(),
                                ItemModels.basic(baseId.withPath(s -> s + "_in_hand")),
                                ItemModels.switchCase(
                                        Arrays.asList(
                                                ItemDisplayContext.GUI,
                                                ItemDisplayContext.GROUND,
                                                ItemDisplayContext.FIXED,
                                                ItemDisplayContext.ON_SHELF
                                        ),
                                        ItemModels.basic(baseId.withPath(s -> s))
                                )
                        )
                )
        );
    }
}
