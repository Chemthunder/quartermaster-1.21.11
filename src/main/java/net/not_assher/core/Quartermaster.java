package net.not_assher.core;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.not_assher.core.index.ModCriterions;
import net.not_assher.core.index.ModDataComponentTypes;
import net.not_assher.core.index.ModEnchantmentEffects;
import net.not_assher.core.index.ModItems;
import net.not_assher.core.utilities.LootTableModifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quartermaster implements ModInitializer {
	public static final String MOD_ID = "quartermaster";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public void onInitialize() {
        ModItems.init();
        ModEnchantmentEffects.init();
        ModDataComponentTypes.init();
        ModCriterions.init();

        LootTableModifiers.init();

		LOGGER.info("Init Completed.");
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
