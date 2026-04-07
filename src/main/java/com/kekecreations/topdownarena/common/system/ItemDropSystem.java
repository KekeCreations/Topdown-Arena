package com.kekecreations.topdownarena.common.system;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.EntityEventSystem;
import com.hypixel.hytale.server.core.event.events.ecs.DropItemEvent;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class ItemDropSystem extends EntityEventSystem<EntityStore, DropItemEvent.Drop> {


    public ItemDropSystem() {
        super(DropItemEvent.Drop.class);
    }

    @Override
    public @Nullable Query<EntityStore> getQuery() {
        return Query.any();
    }

    @Override
    public void handle(int i, @NonNull ArchetypeChunk<EntityStore> archetypeChunk, @NonNull Store<EntityStore> store, @NonNull CommandBuffer<EntityStore> commandBuffer,  DropItemEvent.Drop drop) {
        drop.setCancelled(true);
    }
}
