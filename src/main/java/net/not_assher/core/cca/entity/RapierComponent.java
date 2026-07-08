package net.not_assher.core.cca.entity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.not_assher.core.Quartermaster;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

public class RapierComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<RapierComponent> KEY = ComponentRegistry.getOrCreate(
            Quartermaster.id("rapier"),
            RapierComponent.class
    );
    private final PlayerEntity player;

    private static final int MAX_PARRY_TICKS = (3 * 20);

    private int parryTicks = 0;

    public RapierComponent(PlayerEntity player) {
        this.player = player;
    }

    public void tick() {
        if (parryTicks > 0) {
            parryTicks--;
            if (parryTicks == 0) {
                sync();
            }
        }
    }

    public void sync() {
        KEY.sync(player);
    }

    public void readData(ReadView readView) {
        parryTicks = readView.getInt("ParryTicks", 0);
    }

    public void writeData(WriteView writeView) {
        writeView.putInt("ParryTicks", parryTicks);
    }

    public int getParryTicks() {
        return parryTicks;
    }

    public void setParryTicks(int parryTicks) {
        this.parryTicks = parryTicks;
        sync();
    }
}
