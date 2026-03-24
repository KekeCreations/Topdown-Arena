package com.kekecreations.topdownarena.core.registry;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.topdownarena.common.component.RoundComponent;
import com.kekecreations.topdownarena.common.ui.StartMenuUi;

public class EventRegistry {

    public static void registerEvents(JavaPlugin javaPlugin) {

        javaPlugin.getEventRegistry().registerGlobal(PlayerReadyEvent.class, event -> {
            Player player = event.getPlayer();
            Ref<EntityStore> playerRef = event.getPlayerRef();
            player.getPageManager().openCustomPage(playerRef, playerRef.getStore(), new StartMenuUi(player.getPlayerRef(), CustomPageLifetime.CantClose));
            RoundComponent roundComponent = playerRef.getStore().ensureAndGetComponent(playerRef, RoundComponent.getComponentType());
            roundComponent.freezeRoundTimer(true);
        });
    }
}
