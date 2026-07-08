package net.not_assher.core.cca;

import net.not_assher.core.cca.entity.RapierComponent;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

public class ModCCA implements EntityComponentInitializer {
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry module) {
        module.registerForPlayers(
                RapierComponent.KEY,
                RapierComponent::new,
                RespawnCopyStrategy.NEVER_COPY
        );
    }
}
