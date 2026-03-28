package com.kekecreations.topdownarena.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.DelayedEntitySystem;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.Page;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.topdownarena.common.component.OtherPlayerRoundComponent;
import com.kekecreations.topdownarena.common.ui.*;

import javax.annotation.Nonnull;

public class OtherPlayerTickSystem extends DelayedEntitySystem<EntityStore> {

    ComponentType<EntityStore, OtherPlayerRoundComponent> componentType;

    public OtherPlayerTickSystem(ComponentType<EntityStore, OtherPlayerRoundComponent> componentType) {
        super(1.0F);
        this.componentType = componentType;
    }

    @Nonnull
    @Override
    public Query<EntityStore> getQuery() {
        return Query.and(this.componentType);
    }

    @Override
    public void tick(float dt, int index, ArchetypeChunk<EntityStore> chunk, Store<EntityStore> store, CommandBuffer<EntityStore> commandBuffer) {
        Ref<EntityStore> ref = chunk.getReferenceTo(index);
        Player player = store.getComponent(ref, Player.getComponentType());
        PlayerRef playerRef = store.getComponent(ref, PlayerRef.getComponentType());
        if (player != null && playerRef != null) {
            OtherPlayerRoundComponent otherRoundData = store.getComponent(ref, OtherPlayerRoundComponent.getComponentType());
            if (otherRoundData != null) {
                if (otherRoundData.getRoundType() != "null") {
                    if (otherRoundData.getRoundType() == "in_progress") {
                        player.getPageManager().openCustomPage(ref, store, new InProgressUi(playerRef, otherRoundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
                        otherRoundData.setRoundType("null");
                    }
                    if (otherRoundData.getRoundType() == "menu_class") {
                        player.getPageManager().openCustomPage(ref, store, new OtherClassMenuUi(playerRef, otherRoundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
                        otherRoundData.setRoundType("null");
                    }
                }
            }
        }
    }
}