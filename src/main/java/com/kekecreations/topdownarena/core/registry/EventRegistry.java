package com.kekecreations.topdownarena.core.registry;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.*;
import com.hypixel.hytale.protocol.packets.camera.SetServerCamera;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.command.system.CommandManager;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.topdownarena.common.component.OtherPlayerRoundComponent;
import com.kekecreations.topdownarena.common.component.RoundComponent;
import com.kekecreations.topdownarena.common.ui.InProgressUi;
import com.kekecreations.topdownarena.common.ui.StartMenuUi;
import org.joml.Vector3f;

public class EventRegistry {

    private static final ServerCameraSettings cameraSettings = new ServerCameraSettings();

    public static void registerEvents(JavaPlugin javaPlugin) {
        cameraSettings.positionLerpSpeed = 0.2F;
        cameraSettings.rotationLerpSpeed = 0.2F;
        cameraSettings.distance = 7.0F;
        cameraSettings.displayCursor = true;
        cameraSettings.isFirstPerson = false;
        cameraSettings.movementForceRotationType = MovementForceRotationType.Custom;
        cameraSettings.eyeOffset = true;
        cameraSettings.positionDistanceOffsetType = PositionDistanceOffsetType.DistanceOffset;
        cameraSettings.rotationType = RotationType.Custom;
        cameraSettings.rotation = new Direction(0F, (-(float) Math.PI / 2F - 5.5F), 0F);
        cameraSettings.mouseInputType = MouseInputType.LookAtPlane;
        cameraSettings.planeNormal = new Vector3f(0.0F, 1.0F, 0.0F);


        javaPlugin.getEventRegistry().registerGlobal(PlayerReadyEvent.class, event -> {
            Player player = event.getPlayer();
            Ref<EntityStore> playerRef = event.getPlayerRef();
            Store<EntityStore> store = playerRef.getStore();
            PlayerRef playerRefClass = store.getComponent(playerRef, PlayerRef.getComponentType());
            if (playerRefClass != null) {
                if (Universe.get().getPlayerCount() == 1) {
                    if (store.getComponent(playerRef, OtherPlayerRoundComponent.getComponentType()) != null) {
                        store.removeComponent(playerRef, OtherPlayerRoundComponent.getComponentType());
                    }
                    RoundComponent roundComponent = store.ensureAndGetComponent(playerRef, RoundComponent.getComponentType());
                    roundComponent.setPlayerOne(playerRefClass.getUuid());
                    roundComponent.freezeRoundTimer(true);
                    player.getPageManager().openCustomPage(playerRef, store, new StartMenuUi(playerRefClass, roundComponent, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
                } else {
                    OtherPlayerRoundComponent roundComponent = playerRef.getStore().ensureAndGetComponent(playerRef, OtherPlayerRoundComponent.getComponentType());
                    if (store.getComponent(playerRef, RoundComponent.getComponentType()) != null) {
                        store.removeComponent(playerRef, RoundComponent.getComponentType());
                    }
                    player.getPageManager().openCustomPage(playerRef, store, new InProgressUi(playerRefClass, roundComponent, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
                    roundComponent.setRoundType("null");
                }
                playerRefClass.getPacketHandler().writeNoCache(new SetServerCamera(ClientCameraView.Custom, true, cameraSettings));

                int playerCount;
                for (playerCount = 0; playerCount < Universe.get().getPlayerCount(); playerCount++) {
                    CommandManager.get().handleCommand(playerRefClass, "start_tp");
                }
            }
        });
    }
}
