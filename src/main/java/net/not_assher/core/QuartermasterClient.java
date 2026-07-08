package net.not_assher.core;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.render.item.property.bool.BooleanProperties;
import net.not_assher.core.client.hud.ParryHudElement;
import net.not_assher.core.client.item.CoralRapierBooleanProperty;

public class QuartermasterClient implements ClientModInitializer {
    public void onInitializeClient() {
        BooleanProperties.ID_MAPPER.put(CoralRapierBooleanProperty.ID, CoralRapierBooleanProperty.CODEC);

        HudElementRegistry.attachElementAfter(
                VanillaHudElements.CROSSHAIR,
                Quartermaster.id("parry_hud"),
                new ParryHudElement()
        );
    }
}
