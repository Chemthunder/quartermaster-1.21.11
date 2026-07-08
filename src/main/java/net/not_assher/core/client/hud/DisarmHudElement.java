package net.not_assher.core.client.hud;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.not_assher.core.cca.entity.RapierComponent;
import net.not_assher.core.index.ModEnchantmentEffects;
import net.not_assher.core.item.RapierItem;

public class DisarmHudElement implements HudElement {
    public void render(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        RapierComponent rapierComponent = RapierComponent.KEY.get(player);

        if (player.getActiveOrMainHandStack().getItem() instanceof RapierItem) {
            if (EnchantmentHelper.hasAnyEnchantmentsWith(player.getActiveOrMainHandStack(), ModEnchantmentEffects.DISARM)) {
                drawContext.drawTextWithShadow(
                        MinecraftClient.getInstance().textRenderer,
                        Text.literal(rapierComponent.getCrits() + ""),
                        30,
                        10,
                        0xFFff0000
                );
            }
        }
    }
}
