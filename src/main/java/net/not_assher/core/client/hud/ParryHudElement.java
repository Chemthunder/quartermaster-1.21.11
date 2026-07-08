package net.not_assher.core.client.hud;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.not_assher.core.Quartermaster;
import net.not_assher.core.cca.entity.RapierComponent;

public class ParryHudElement implements HudElement {
    public void render(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        if (RapierComponent.KEY.get(player).getParryTicks() > 0) {
            drawContext.drawGuiTexture(
                    RenderPipelines.GUI_TEXTURED,
                    Quartermaster.id("hud/rapier_parry_indicator"),
                    drawContext.getScaledWindowWidth() / 2 - 9,
                    drawContext.getScaledWindowHeight() / 2 - 20,
                    16,
                    16
            );
        }
    }
}
