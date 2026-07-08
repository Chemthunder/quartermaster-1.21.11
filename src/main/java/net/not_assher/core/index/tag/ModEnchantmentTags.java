package net.not_assher.core.index.tag;

import net.acoyt.acornlib.api.builder.TagBuilder;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.not_assher.core.Quartermaster;

public interface ModEnchantmentTags {
    TagBuilder<Enchantment> tag = new TagBuilder<>(Quartermaster.MOD_ID, RegistryKeys.ENCHANTMENT);

    TagKey<Enchantment> RAPIER_ENCHANTABLE = tag.register("rapier_enchantable");
}
