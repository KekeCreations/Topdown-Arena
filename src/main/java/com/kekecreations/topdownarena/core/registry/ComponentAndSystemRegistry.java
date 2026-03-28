package com.kekecreations.topdownarena.core.registry;

import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.topdownarena.common.component.OtherPlayerRoundComponent;
import com.kekecreations.topdownarena.common.component.RoundComponent;
import com.kekecreations.topdownarena.common.system.NPCDeathSystem;
import com.kekecreations.topdownarena.common.system.OtherPlayerTickSystem;
import com.kekecreations.topdownarena.common.system.PlayerDeathSystem;
import com.kekecreations.topdownarena.common.system.PlayerTickSystem;

public class ComponentAndSystemRegistry {

    private static ComponentType<EntityStore, RoundComponent> roundComponent;

    private static ComponentType<EntityStore, OtherPlayerRoundComponent> otherRoundComponent;


    public static void registerComponents(JavaPlugin javaPlugin) {
        var registry = javaPlugin.getEntityStoreRegistry();
        roundComponent = registry.registerComponent(
                RoundComponent.class,
                "RoundData",
                RoundComponent.CODEC
        );
        //Allows to call component from component class
        RoundComponent.setComponentType(roundComponent);

        otherRoundComponent = registry.registerComponent(
                OtherPlayerRoundComponent.class,
                "OtherRoundData",
                OtherPlayerRoundComponent.CODEC
        );
        OtherPlayerRoundComponent.setComponentType(otherRoundComponent);

        registry.registerSystem(new PlayerTickSystem(roundComponent));
        registry.registerSystem(new OtherPlayerTickSystem(otherRoundComponent));
        registry.registerSystem(new PlayerDeathSystem());
        registry.registerSystem(new NPCDeathSystem());
    }

}
