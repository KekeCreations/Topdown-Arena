package com.kekecreations.topdownarena.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.EntityTickingSystem;
import com.hypixel.hytale.server.core.command.system.CommandManager;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.topdownarena.common.component.RoundComponent;

import javax.annotation.Nonnull;
import java.util.Objects;

public class DeleteItemsSystem extends EntityTickingSystem<EntityStore> {

    private final ComponentType<EntityStore, RoundComponent> roundStats;


    public DeleteItemsSystem(ComponentType<EntityStore, RoundComponent> roundStats) {
        super();
        this.roundStats = roundStats;
    }

    @Nonnull
    @Override
    public Query<EntityStore> getQuery() {
        return Query.and(roundStats);
    }

    @Override
    public void tick(float dt, int index, ArchetypeChunk<EntityStore> chunk, Store<EntityStore> store, CommandBuffer<EntityStore> commandBuffer) {
        Ref<EntityStore> ref = chunk.getReferenceTo(index);
        PlayerRef playerRef = Objects.requireNonNull(store.getComponent(ref, PlayerRef.getComponentType()));


        if (store.getComponent(ref, roundStats) != null) {
            CommandManager.get().handleCommand(playerRef, "delete_items");
        }
    }
}
