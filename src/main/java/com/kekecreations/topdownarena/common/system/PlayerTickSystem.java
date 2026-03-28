package com.kekecreations.topdownarena.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.DelayedEntitySystem;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.Page;
import com.hypixel.hytale.server.core.command.system.CommandManager;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entitystats.EntityStatMap;
import com.hypixel.hytale.server.core.modules.entitystats.asset.DefaultEntityStatTypes;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.topdownarena.common.component.OtherPlayerRoundComponent;
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
                for (PlayerRef otherPlayer : Universe.get().getPlayers()) {
                    player.getHudManager().setCustomHud(otherPlayer, new RoundStatsHud(otherPlayer, roundData));
                }
                if (roundData.getRoundTimer() > 0 && !roundData.isTimerFrozen()) {
                    roundData.setRoundTimer(roundData.getRoundTimer() - 1);
                    EntityStatMap entityStat = store.getComponent(ref, EntityStatMap.getComponentType());
                    if (entityStat != null) {
                        if (roundData.getEasyMode()) {
                            entityStat.addStatValue(DefaultEntityStatTypes.getHealth(), 2F);
                        }
                        entityStat.addStatValue(DefaultEntityStatTypes.getHealth(), 1F);
                    }
                    //WAVE 2
                    if ((roundData.getRoundTimer() == 20 && roundData.getRoundType() !=  "sandbox") || (roundData.getRoundTimer() == 20 && roundData.getSandboxRandomWaves() && roundData.getRoundType() == "sandbox")) {
                        switch(randomWave) {
                            case 0 -> {
                                CommandManager.get().handleCommand(playerRef, "round_npc Skeleton Add 0 0 2");
                                CommandManager.get().handleCommand(playerRef, "round_npc Skeleton Add 2 0 2");
                                CommandManager.get().handleCommand(playerRef, "round_npc Skeleton Add 0 0 2");
                                CommandManager.get().handleCommand(playerRef, "round_npc Skeleton Add 2 0 2");
                                CommandManager.get().handleCommand(playerRef, "round_npc Skeleton Add 2 0 0");
                                CommandManager.get().handleCommand(playerRef, "round_npc Skeleton_Archer Subtract 2 0 0");
                                CommandManager.get().handleCommand(playerRef, "round_npc Skeleton_Archer Subtract 0 0 2");
                            }
                            case 1 -> {
                                CommandManager.get().handleCommand(playerRef, "round_npc Zombie Add 0 0 2");
                                CommandManager.get().handleCommand(playerRef, "round_npc Zombie Add 2 0 2");
                                CommandManager.get().handleCommand(playerRef, "round_npc Zombie Add 2 0 0");
                                CommandManager.get().handleCommand(playerRef, "round_npc Zombie Add 1 0 0");
                                CommandManager.get().handleCommand(playerRef, "round_npc Zombie Add 2 0 1");
                            }
                            case 2 -> {
                                if (roundData.getArachnophobiaMode()) {
                                    CommandManager.get().handleCommand(playerRef, "round_npc Wolf_Black Add 0 0 2");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Wolf_White Add 2 0 2");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Wolf_White Add 2 0 0");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Wolf_White Add 1 0 2");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Wolf_White Add 1 0 0");
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
                        int roundScale;
                        for (roundScale = 0; roundScale < Universe.get().getPlayerCount(); roundScale++) {
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
                                    CommandManager.get().handleCommand(playerRef, "round_npc Wolf_White Add 2 0 2");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Wolf_White Subtract 1 0 0");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Wolf_White Add 2 0 2");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Wolf_White Subtract 1 0 0");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Zombie Add 1 0 0");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Zombie Add 2 0 1");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Zombie Add 1 0 2");
                                }
                                case 4 -> {
                                    CommandManager.get().handleCommand(playerRef, "round_npc Skeleton_Fighter Add 0 0 2");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Skeleton_Fighter Add 1 0 0");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Skeleton_Archmage Add 2 0 2");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Cow_Undead Subtract 1 0 0");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Wolf_White Add 2 0 2");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Wolf_White Subtract 1 0 0");
                                }
                                case 5 -> {
                                    CommandManager.get().handleCommand(playerRef, "round_npc Cow_Undead Add 0 0 2");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Cow_Undead Add 1 0 2");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Cow_Undead Add 2 0 2");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Cow_Undead Add 2 0 0");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Cow_Undead Add 0 0 2");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Skeleton_Archmage Add 2 0 2");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Skeleton_Archmage Add 2 0 2");
                                }
                                case 6 -> {
                                    CommandManager.get().handleCommand(playerRef, "round_npc Werewolf Add 0 0 3");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Werewolf Add 2 0 0");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Werewolf Add 1 0 1");
                                }
                                case 7 -> {
                                    CommandManager.get().handleCommand(playerRef, "round_npc Wraith Add 0 0 3");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Wraith Add 2 0 0");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Wraith  Add 1 0 1");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Skeleton Add 0 0 1");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Skeleton Add 1 0 0");
                                    CommandManager.get().handleCommand(playerRef, "round_npc Skeleton Add 1 0 2");
                                }
                                case 8 -> {
                                    CommandManager.get().handleCommand(playerRef, "round_npc Zombie_Aberrant Add 0 0 3");
                                }
                            }
                        }
                        int count1;
                        int count2;
                        int count3;
                        if (roundData.getRoundType() == "sandbox") {
                            for (count1 = 0; count1 < roundData.getSandboxEnemyCount(); count1++) {
                                switch(roundData.getSandboxEnemyChoice()) {
                                    case 0 -> CommandManager.get().handleCommand(playerRef, "round_npc Skeleton Add 0 0 1");
                                    case 1 -> CommandManager.get().handleCommand(playerRef, "round_npc Skeleton_Archer Add 0 0 1");
                                    case 2 -> CommandManager.get().handleCommand(playerRef, "round_npc Skeleton_Archmage Add 0 0 1");
                                    case 3 -> CommandManager.get().handleCommand(playerRef, "round_npc Skeleton_Burnt_Alchemist Add 0 0 1");
                                    case 4 -> CommandManager.get().handleCommand(playerRef, "round_npc Skeleton_Burnt_Gunner Add 0 0 1");
                                    case 5 -> CommandManager.get().handleCommand(playerRef, "round_npc Zombie Add 0 0 1");
                                    case 6 -> CommandManager.get().handleCommand(playerRef, "round_npc Zombie_Aberrant Add 0 0 1");
                                    case 7 -> CommandManager.get().handleCommand(playerRef, "round_npc Wolf_White Add 0 0 1");
                                    case 8 -> CommandManager.get().handleCommand(playerRef, "round_npc Wolf_Black Add 0 0 1");
                                    case 9 -> CommandManager.get().handleCommand(playerRef, "round_npc Werewolf Add 0 0 1");
                                    case 10 -> CommandManager.get().handleCommand(playerRef, "round_npc Yeti Add 0 0 1");
                                    case 11 -> CommandManager.get().handleCommand(playerRef, "round_npc Spider Add 0 0 1");
                                    case 12 -> CommandManager.get().handleCommand(playerRef, "round_npc Spider_Cave Add 0 0 1");
                                }
                            }
                            for (count2 = 0; count2 < roundData.getSandboxEnemyCount2(); count2++) {
                                switch(roundData.getSandboxEnemyChoice2()) {
                                    case 0 -> CommandManager.get().handleCommand(playerRef, "round_npc Skeleton Add 1 0 0");
                                    case 1 -> CommandManager.get().handleCommand(playerRef, "round_npc Skeleton_Archer Add 1 0 0");
                                    case 2 -> CommandManager.get().handleCommand(playerRef, "round_npc Skeleton_Archmage Add 1 0 0");
                                    case 3 -> CommandManager.get().handleCommand(playerRef, "round_npc Skeleton_Burnt_Alchemist Add 1 0 0");
                                    case 4 -> CommandManager.get().handleCommand(playerRef, "round_npc Skeleton_Burnt_Gunner Add 1 0 0");
                                    case 5 -> CommandManager.get().handleCommand(playerRef, "round_npc Zombie Add 1 0 0");
                                    case 6 -> CommandManager.get().handleCommand(playerRef, "round_npc Zombie_Aberrant Add 1 0 0");
                                    case 7 -> CommandManager.get().handleCommand(playerRef, "round_npc Wolf_White Add 1 0 0");
                                    case 8 -> CommandManager.get().handleCommand(playerRef, "round_npc Wolf_Black Add 1 0 0");
                                    case 9 -> CommandManager.get().handleCommand(playerRef, "round_npc Werewolf Add 1 0 0");
                                    case 10 -> CommandManager.get().handleCommand(playerRef, "round_npc Yeti Add 1 0 0");
                                    case 11 -> CommandManager.get().handleCommand(playerRef, "round_npc Spider Add 1 0 0");
                                    case 12 -> CommandManager.get().handleCommand(playerRef, "round_npc Spider_Cave Add 1 0 0");
                                }
                            }
                            for (count3 = 0; count3 < roundData.getSandboxEnemyCount3(); count3++) {
                                switch(roundData.getSandboxEnemyChoice3()) {
                                    case 0 -> CommandManager.get().handleCommand(playerRef, "round_npc Skeleton Subtract 1 0 0");
                                    case 1 -> CommandManager.get().handleCommand(playerRef, "round_npc Skeleton_Archer Subtract 1 0 0");
                                    case 2 -> CommandManager.get().handleCommand(playerRef, "round_npc Skeleton_Archmage Subtract 1 0 0");
                                    case 3 -> CommandManager.get().handleCommand(playerRef, "round_npc Skeleton_Burnt_Alchemist Subtract 1 0 0");
                                    case 4 -> CommandManager.get().handleCommand(playerRef, "round_npc Skeleton_Burnt_Gunner Subtract 1 0 0");
                                    case 5 -> CommandManager.get().handleCommand(playerRef, "round_npc Zombie Subtract 1 0 0");
                                    case 6 -> CommandManager.get().handleCommand(playerRef, "round_npc Zombie_Aberrant Subtract 1 0 0");
                                    case 7 -> CommandManager.get().handleCommand(playerRef, "round_npc Wolf_White Subtract 1 0 0");
                                    case 8 -> CommandManager.get().handleCommand(playerRef, "round_npc Wolf_Black Subtract 1 0 0");
                                    case 9 -> CommandManager.get().handleCommand(playerRef, "round_npc Werewolf Subtract 1 0 0");
                                    case 10 -> CommandManager.get().handleCommand(playerRef, "round_npc Yeti Subtract 1 0 0");
                                    case 11 -> CommandManager.get().handleCommand(playerRef, "round_npc Spider Subtract 1 0 0");
                                    case 12 -> CommandManager.get().handleCommand(playerRef, "round_npc Spider_Cave Subtract 1 0 0");
                                }
                            }
                        }
                    }
                }
                EntityStatMap entityStat = store.getComponent(ref, EntityStatMap.getComponentType());
                if (roundData.getRoundTimer() <= 0 && !roundData.isTimerFrozen() && player.getPageManager().getCustomPage() == null) {
                    if (roundData.getEnemiesLeftToKill() <= 0) {
                        roundData.setRoundType("menu_winlevel");
                        roundData.setRoundsPlayedStat(roundData.getRoundsPlayedStat() + 1);
                        roundData.setRoundsWonStat(roundData.getRoundsWonStat() + 1);
                        if (entityStat != null) {
                            entityStat.setStatValue(DefaultEntityStatTypes.getHealth(), 150.0F);
                        }
                    } else {
                        roundData.setRoundType("menu_lostlevel");
                        roundData.setRoundsPlayedStat(roundData.getRoundsPlayedStat() + 1);
                        if (entityStat != null) {
                            entityStat.setStatValue(DefaultEntityStatTypes.getHealth(), 150.0F);
                        }
                    }
                }
                //OPENING MENU STRAIGHT FROM CUSTOM PAGE CAUSES SERVER LAG
                if (roundData.getRoundType() != "null" && player.getPageManager().getCustomPage() == null) {
                    if (roundData.getRoundType() == "menu_levels") {
                        player.getPageManager().openCustomPage(ref, store, new LevelMenuUi(playerRef, roundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
                        roundData.setRoundType("null");
                    }
                    if (roundData.getRoundType() == "menu_start") {
                        player.getPageManager().openCustomPage(ref, store, new StartMenuUi(playerRef, roundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
                        roundData.setRoundType("null");
                    }
                    if (roundData.getRoundType() == "menu_class") {
                        for (PlayerRef otherPlayerRef : Universe.get().getPlayers()) {
                            OtherPlayerRoundComponent otherPlayerRoundComponent = store.getComponent(otherPlayerRef.getReference(), OtherPlayerRoundComponent.getComponentType());
                            if (otherPlayerRoundComponent != null) {
                                Player otherPlayer = store.getComponent(otherPlayerRef.getReference(), Player.getComponentType());
                                if (otherPlayer != null) {
                                    otherPlayer.getPageManager().setPage(ref, store, Page.None);
                                    otherPlayerRoundComponent.setRoundType("menu_class");
                                    otherPlayerRoundComponent.setLevel(roundData.getLevel());
                                }
                            }
                        }
                        player.getPageManager().openCustomPage(ref, store, new ClassMenuUi(playerRef, roundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
                        roundData.setRoundType("null");
                    }
                    if (roundData.getRoundType() == "menu_winlevel") {
                        for (PlayerRef otherPlayerRef : Universe.get().getPlayers()) {
                            OtherPlayerRoundComponent otherPlayerRoundComponent = store.getComponent(otherPlayerRef.getReference(), OtherPlayerRoundComponent.getComponentType());
                            if (otherPlayerRoundComponent != null) {
                                Player otherPlayer = store.getComponent(otherPlayerRef.getReference(), Player.getComponentType());
                                if (otherPlayer != null) {
                                    otherPlayer.getPageManager().setPage(ref, store, Page.None);
                                    otherPlayerRoundComponent.setRoundType("in_progress");
                                    otherPlayerRoundComponent.setLevel(roundData.getLevel());
                                }
                            }
                        }
                        player.getPageManager().openCustomPage(ref, store, new WinLevelUi(playerRef, roundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
                        roundData.setRoundType("null");
                    }
                    if (roundData.getRoundType() == "menu_lostlevel") {
                        for (PlayerRef otherPlayerRef : Universe.get().getPlayers()) {
                            OtherPlayerRoundComponent otherPlayerRoundComponent = store.getComponent(otherPlayerRef.getReference(), OtherPlayerRoundComponent.getComponentType());
                            if (otherPlayerRoundComponent != null) {
                                Player otherPlayer = store.getComponent(otherPlayerRef.getReference(), Player.getComponentType());
                                if (otherPlayer != null) {
                                    otherPlayer.getPageManager().setPage(ref, store, Page.None);
                                    otherPlayerRoundComponent.setRoundType("in_progress");
                                    otherPlayerRoundComponent.setLevel(roundData.getLevel());
                                }
                            }
                        }
                        player.getPageManager().openCustomPage(ref, store, new LostLevelUi(playerRef, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
                        roundData.setRoundType("null");
                    }
                    if (roundData.getRoundType() == "menu_options") {
                        player.getPageManager().openCustomPage(ref, store, new OptionsUi(playerRef, roundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
                        roundData.setRoundType("null");
                    }
                    if (roundData.getRoundType() == "how_to_play") {
                        player.getPageManager().openCustomPage(ref, store, new HowToPlayUi(playerRef, roundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
                        roundData.setRoundType("null");
                    }
                    if (roundData.getRoundType() == "sandbox_mode") {
                        for (PlayerRef otherPlayerRef : Universe.get().getPlayers()) {
                            OtherPlayerRoundComponent otherPlayerRoundComponent = store.getComponent(otherPlayerRef.getReference(), OtherPlayerRoundComponent.getComponentType());
                            if (otherPlayerRoundComponent != null) {
                                Player otherPlayer = store.getComponent(otherPlayerRef.getReference(), Player.getComponentType());
                                if (otherPlayer != null) {
                                    otherPlayer.getPageManager().setPage(ref, store, Page.None);
                                    otherPlayerRoundComponent.setRoundType("menu_sandbox");
                                }
                            }
                        }
                        player.getPageManager().openCustomPage(ref, store, new SandboxModeMenuUi(playerRef, roundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
                        roundData.setRoundType("null");
                    }
                    if (roundData.getRoundType() == "stats") {
                        player.getPageManager().openCustomPage(ref, store, new StatsUi(playerRef, roundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
                        roundData.setRoundType("null");
                    }
                }
            }
        }
    }
}