package com.kekecreations.topdownarena.core.registry;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.protocol.*;
import com.hypixel.hytale.protocol.packets.camera.SetServerCamera;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.topdownarena.common.component.RoundComponent;
import com.kekecreations.topdownarena.common.ui.StartMenuUi;

public class EventRegistry {

    private static final ServerCameraSettings cameraSettings = new ServerCameraSettings();

    public static void registerEvents(JavaPlugin javaPlugin) {
        cameraSettings.positionLerpSpeed = 0.2F;
        cameraSettings.rotationLerpSpeed = 0.2F;
        cameraSettings.distance = 8.0F;
        cameraSettings.displayCursor = true;
        cameraSettings.isFirstPerson = false;
        cameraSettings.movementForceRotationType = MovementForceRotationType.Custom;
        cameraSettings.eyeOffset = true;
        cameraSettings.positionDistanceOffsetType = PositionDistanceOffsetType.DistanceOffset;
        cameraSettings.rotationType = RotationType.Custom;
        cameraSettings.rotation = new Direction(0.0F, (-(float)Math.PI / 2F), 0.0F);


        javaPlugin.getEventRegistry().registerGlobal(PlayerReadyEvent.class, event -> {
            Player player = event.getPlayer();
            Ref<EntityStore> playerRef = event.getPlayerRef();
            player.getPageManager().openCustomPage(playerRef, playerRef.getStore(), new StartMenuUi(player.getPlayerRef(), CustomPageLifetime.CantClose));
            RoundComponent roundComponent = playerRef.getStore().ensureAndGetComponent(playerRef, RoundComponent.getComponentType());
            roundComponent.freezeRoundTimer(true);
            player.getPlayerRef().getPacketHandler().writeNoCache(new SetServerCamera(ClientCameraView.Custom, true, cameraSettings));
        });
    }
}
