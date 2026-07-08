package net.not_assher.core;

import net.acoyt.acornlib.api.ALib;
import net.fabricmc.api.ModInitializer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.not_assher.core.index.ModDataComponentTypes;
import net.not_assher.core.index.ModEnchantmentEffects;
import net.not_assher.core.index.ModItems;
import net.not_assher.core.utilities.LootTableModifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quartermaster implements ModInitializer {
	public static final String MOD_ID = "quartermaster";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final ALib.ModMenuData DATA = new ALib.ModMenuData(
            Text.literal("Quartermaster").withColor(0xFFe9ac2d),
            Text.literal("piracy").withColor(0xFF844f89),
            Text.literal("Asher's silly lil pirate mod adding some elite tools for plundering much booty")
    );

	public void onInitialize() {
        ModItems.init();
        ModEnchantmentEffects.init();
        ModDataComponentTypes.init();

        LootTableModifiers.init();

		LOGGER.info("Init Completed.");

        ALib.registerModData(MOD_ID, DATA);
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
