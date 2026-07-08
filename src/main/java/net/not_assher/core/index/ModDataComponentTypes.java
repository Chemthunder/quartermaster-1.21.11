package net.not_assher.core.index;

import com.mojang.serialization.Codec;
import net.acoyt.acornlib.api.registrants.DataComponentTypeRegistrant;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.not_assher.core.Quartermaster;

public interface ModDataComponentTypes {
    DataComponentTypeRegistrant rant = new DataComponentTypeRegistrant(Quartermaster.MOD_ID);

    ComponentType<Boolean> IS_CORAL = rant.register("is_coral", Codec.BOOL, PacketCodecs.BOOLEAN);

    static void init() {}
}
