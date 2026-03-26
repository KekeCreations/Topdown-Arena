package com.kekecreations.topdownarena.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.modules.entity.DespawnComponent;
import com.hypixel.hytale.server.core.modules.entity.damage.DeathComponent;
import com.hypixel.hytale.server.core.modules.entity.damage.DeathSystems;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.npc.entities.NPCEntity;
import com.kekecreations.topdownarena.common.component.RoundComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NPCDeathSystem extends DeathSystems.OnDeathSystem {


    public NPCDeathSystem() {
        super();
    }

    @Override
    public void onComponentAdded(@NotNull Ref<EntityStore> ref, @NotNull DeathComponent deathComponent, @NotNull Store<EntityStore> store, @NotNull CommandBuffer<EntityStore> commandBuffer) {
        NPCEntity npc = store.getComponent(ref, NPCEntity.getComponentType());
        if (npc != null) {
            DespawnComponent despawnComponent = store.getComponent(ref, DespawnComponent.getComponentType());
            if (despawnComponent != null) {
                npc.setDespawning(false);
                Universe.get().sendMessage(Message.raw("DESPAWN"));
            }
            for (PlayerRef playerRef : Universe.get().getPlayers()) {
                if (playerRef.getReference() != null) {
                    RoundComponent roundData = store.getComponent(playerRef.getReference(), RoundComponent.getComponentType());
                    if (roundData != null) {
                        if (roundData.getEnemiesLeftToKill() == 0) {
                            roundData.setBonusEnemiesKilled(roundData.getBonusEnemiesKilled() + 1);
                            npc.remove();
                        } else {
                            roundData.setEnemiesToKill(roundData.getEnemiesLeftToKill() - 1);
                            npc.remove();
                        }
                    }
                }
            }
        }
    }

    @Override
    public @Nullable Query<EntityStore> getQuery() {
        return Query.any();
    }
}
