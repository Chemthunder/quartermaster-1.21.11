package net.not_assher.mixin.client;

import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = InGameHud.class)
public abstract class InGameHudMixin {
}
