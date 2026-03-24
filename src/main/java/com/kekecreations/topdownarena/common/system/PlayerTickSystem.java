package com.kekecreations.topdownarena.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.DelayedEntitySystem;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.topdownarena.common.component.RoundComponent;
import com.kekecreations.topdownarena.common.ui.ClassMenuUi;
import com.kekecreations.topdownarena.common.ui.LevelMenuUi;
import com.kekecreations.topdownarena.common.ui.RoundStatsHud;
import com.kekecreations.topdownarena.common.ui.StartMenuUi;

import javax.annotation.Nonnull;

public class PlayerTickSystem extends DelayedEntitySystem<EntityStore> {

    ComponentType<EntityStore, RoundComponent> componentType;

    public PlayerTickSystem(ComponentType<EntityStore, RoundComponent> componentType) {
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
        if (player != null) {
            RoundComponent roundData = store.getComponent(ref, RoundComponent.getComponentType());
            if (roundData != null) {
                player.getHudManager().setCustomHud(player.getPlayerRef(), new RoundStatsHud(player.getPlayerRef(), roundData));
                if (roundData.getRoundTimer() > 0) {
                    roundData.setRoundTimer(roundData.getRoundTimer() - 1);
                }
                //OPENING MENU STRAIGHT FROM CUSTOM PAGE CAUSES SERVER LAG
                if (roundData.getRoundType() == "menu_levels") {
                    player.getPageManager().openCustomPage(ref, store, new LevelMenuUi(player.getPlayerRef(), roundData, CustomPageLifetime.CantClose));
                    roundData.setRoundType("null");
                }
                if (roundData.getRoundType() == "menu_start") {
                    player.getPageManager().openCustomPage(ref, store, new StartMenuUi(player.getPlayerRef(), CustomPageLifetime.CantClose));
                    roundData.setRoundType("null");
                }
                if (roundData.getRoundType() == "menu_class") {
                    player.getPageManager().openCustomPage(ref, store, new ClassMenuUi(player.getPlayerRef(), roundData, CustomPageLifetime.CantClose));
                    roundData.setRoundType("null");
                }
            }
        }
    }
}

