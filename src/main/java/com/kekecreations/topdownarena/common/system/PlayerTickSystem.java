package com.kekecreations.topdownarena.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.DelayedEntitySystem;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandManager;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entitystats.EntityStatMap;
import com.hypixel.hytale.server.core.modules.entitystats.asset.DefaultEntityStatTypes;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.topdownarena.common.component.RoundComponent;
import com.kekecreations.topdownarena.common.ui.*;

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
        PlayerRef playerRef = store.getComponent(ref, PlayerRef.getComponentType());

        int randomWave = (int)(Math.random() * 3);

        if (player != null && playerRef != null) {
            RoundComponent roundData = store.getComponent(ref, RoundComponent.getComponentType());
            if (roundData != null) {
                player.getHudManager().setCustomHud(playerRef, new RoundStatsHud(playerRef, roundData));
                if (roundData.getRoundTimer() > 0 && !roundData.isTimerFrozen()) {
                    roundData.setRoundTimer(roundData.getRoundTimer() - 1);
                    if (roundData.getEasyMode()) {
                        EntityStatMap entityStat = store.getComponent(ref, EntityStatMap.getComponentType());
                        if (entityStat != null) {
                            entityStat.addStatValue(DefaultEntityStatTypes.getHealth(), 0.10F);
                        }
                    }
                    //WAVE 2
                    if (roundData.getRoundTimer() == 20) {
                        switch(randomWave) {
                            case 0 -> {
                                CommandManager.get().handleCommand(playerRef, "round_npc Skeleton Add 0 0 2");
                                CommandManager.get().handleCommand(playerRef, "round_npc Skeleton Add 2 0 2");
                                CommandManager.get().handleCommand(playerRef, "round_npc Skeleton Add 2 0 0");
                                CommandManager.get().handleCommand(playerRef, "round_npc Skeleton_Archer Subtract 2 0 0");
                                CommandManager.get().handleCommand(playerRef, "round_npc Skeleton_Archer Subtract 0 0 2");
                            }
                            case 1 -> {
                                CommandManager.get().handleCommand(playerRef, "round_npc Zombie Add 0 0 2");
                                CommandManager.get().handleCommand(playerRef, "round_npc Zombie Add 2 0 2");
                                CommandManager.get().handleCommand(playerRef, "round_npc Zombie_Aberrant Add 2 0 0");
                            }
                            case 2 -> {
                                if (roundData.getArachnophobiaMode()) {
                                    CommandManager.get().handleCommand(playerRef, "round_npc Wolf_Black Add 0 0 2");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Wolf_White Add 2 0 2");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Wolf_White Add 2 0 0");
                                } else {
                                    CommandManager.get().handleCommand(playerRef, "round_npc Spider_Cave Add 0 0 2");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Spider_Cave Add 2 0 2");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Spider_Cave Add 2 0 0");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Spider_Cave Subtract 0 0 2");
                                }
                            }
                        }
                    }
                    //START GAME
                    if (roundData.getRoundTimer() == 60) {
                        switch (roundData.getLevel()) {
                            case 1 -> {
                                CommandManager.get().handleCommand(playerRef, "round_npc Skeleton Add 0 0 2");
                                CommandManager.get().handleCommand(playerRef, "round_npc Skeleton Add 1 0 0");
                                CommandManager.get().handleCommand(playerRef, "round_npc Skeleton Add 2 0 2");
                                CommandManager.get().handleCommand(playerRef, "round_npc Skeleton Add 1 0 0");
                                CommandManager.get().handleCommand(playerRef, "round_npc Skeleton Add 2 0 1");
                                CommandManager.get().handleCommand(playerRef, "round_npc Skeleton Add 1 0 2");
                                CommandManager.get().handleCommand(playerRef, "round_npc Zombie Add 0 0 1");
                                CommandManager.get().handleCommand(playerRef, "round_npc Zombie Add 1 0 1");
                            }
                            case 2 -> {
                                CommandManager.get().handleCommand(playerRef, "round_npc Skeleton_Archer Add 0 0 2");
                                CommandManager.get().handleCommand(playerRef, "round_npc Skeleton_Archer Add 1 0 0");
                                CommandManager.get().handleCommand(playerRef, "round_npc Skeleton_Archer Add 0 0 1");
                                CommandManager.get().handleCommand(playerRef, "round_npc Skeleton_Archer Add 0 0 2");
                                CommandManager.get().handleCommand(playerRef, "round_npc Zombie Add 2 0 2");
                                CommandManager.get().handleCommand(playerRef, "round_npc Zombie Add 1 0 0");
                                CommandManager.get().handleCommand(playerRef, "round_npc Zombie Add 2 0 1");
                                CommandManager.get().handleCommand(playerRef, "round_npc Zombie Add 1 0 2");
                            }
                            case 3 -> {
                                CommandManager.get().handleCommand(playerRef, "round_npc Skeleton Add 0 0 2");
                                CommandManager.get().handleCommand(playerRef, "round_npc Skeleton Add 1 0 0");
                                CommandManager.get().handleCommand(playerRef, "round_npc Zombie_Aberrant Add 2 0 2");
                                CommandManager.get().handleCommand(playerRef, "round_npc Zombie Add 1 0 0");
                                CommandManager.get().handleCommand(playerRef, "round_npc Zombie Add 2 0 1");
                                CommandManager.get().handleCommand(playerRef, "round_npc Zombie Add 1 0 2");
                            }
                        }
                    }
                }
                EntityStatMap entityStat = store.getComponent(ref, EntityStatMap.getComponentType());
                if (roundData.getRoundTimer() <= 0 && !roundData.isTimerFrozen()) {
                    if (roundData.getEnemiesLeftToKill() <= 0) {
                        roundData.setRoundType("menu_winlevel");
                        if (entityStat != null) {
                            entityStat.setStatValue(DefaultEntityStatTypes.getHealth(), 150.0F);
                        }
                    } else {
                        roundData.setRoundType("menu_lostlevel");
                        if (entityStat != null) {
                            entityStat.setStatValue(DefaultEntityStatTypes.getHealth(), 150.0F);
                        }
                    }
                }
                //OPENING MENU STRAIGHT FROM CUSTOM PAGE CAUSES SERVER LAG
                if (roundData.getRoundType() != "null") {
                    if (roundData.getRoundType() == "menu_levels") {
                        player.getPageManager().openCustomPage(ref, store, new LevelMenuUi(playerRef, roundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
                        roundData.setRoundType("null");
                    }
                    if (roundData.getRoundType() == "menu_start") {
                        player.getPageManager().openCustomPage(ref, store, new StartMenuUi(playerRef, roundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
                        roundData.setRoundType("null");
                    }
                    if (roundData.getRoundType() == "menu_class") {
                        player.getPageManager().openCustomPage(ref, store, new ClassMenuUi(playerRef, roundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
                        roundData.setRoundType("null");
                    }
                    if (roundData.getRoundType() == "menu_winlevel") {
                        player.getPageManager().openCustomPage(ref, store, new WinLevelUi(playerRef, roundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
                        roundData.setRoundType("null");
                    }
                    if (roundData.getRoundType() == "menu_lostlevel") {
                        player.getPageManager().openCustomPage(ref, store, new LostLevelUi(playerRef, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
                        roundData.setRoundType("null");
                    }
                    if (roundData.getRoundType() == "menu_options") {
                        player.getPageManager().openCustomPage(ref, store, new OptionsUi(playerRef, roundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
                        roundData.setRoundType("null");
                    }
                }
            }
        }
    }
}