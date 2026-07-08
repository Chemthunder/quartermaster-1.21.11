package net.not_assher.core.index;

import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.minecraft.item.Item;
import net.not_assher.core.Quartermaster;
import net.not_assher.core.item.RapierHandleItem;
import net.not_assher.core.item.RapierItem;

public interface ModItems {
    ItemRegistrant rant = new ItemRegistrant(Quartermaster.MOD_ID);

    Item RAPIER = rant.register("rapier", RapierItem::new, new Item.Settings()
            .maxCount(1)
            .attributeModifiers(RapierItem.createAttributes())
            .component(ModDataComponentTypes.IS_CORAL, false)
            .enchantable(4)
            .fireproof()
    );

    Item RAPIER_HANDLE = rant.register("rapier_handle", RapierHandleItem::new, new Item.Settings()
            .maxCount(1)
    );

    Item RUINED_HANDLE = rant.register("ruined_handle", RapierHandleItem::new, new Item.Settings()
            .maxCount(1)
    );

    static void init() {}
}
