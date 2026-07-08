package net.not_assher.core.index.tag;

import net.acoyt.acornlib.api.builder.TagBuilder;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.not_assher.core.Quartermaster;

public interface ModItemTags {
    TagBuilder<Item> tag = new TagBuilder<>(Quartermaster.MOD_ID, RegistryKeys.ITEM);

    TagKey<Item> RAPIERS = tag.register("rapiers");
}
