package com.kekecreations.topdownarena.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entity.damage.Damage;
import com.hypixel.hytale.server.core.modules.entity.damage.DeathComponent;
import com.hypixel.hytale.server.core.modules.entity.damage.DeathSystems;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.npc.entities.NPCEntity;
import com.kekecreations.topdownarena.common.component.OtherPlayerRoundComponent;
import com.kekecreations.topdownarena.common.component.RoundComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NPCDeathSystem extends DeathSystems.OnDeathSystem {


    public NPCDeathSystem() {
        super();
    }

    @Override
    public void onComponentAdded(@NotNull Ref<EntityStore> ref, @NotNull DeathComponent deathComponent, @NotNull Store<EntityStore> store, @NotNull CommandBuffer<EntityStore> commandBuffer) {
        NPCEntity npc = store.getComponent(ref, NPCEntity.getComponentType());
        if (npc != null && !npc.wasRemoved()) {
            Damage damage = deathComponent.getDeathInfo();
            if (damage != null) {
                if (damage.getSource() instanceof Damage.EntitySource entitySource) {
                    if (store.getArchetype(entitySource.getRef()).contains(Player.getComponentType())) {
                        OtherPlayerRoundComponent otherRoundData = store.getComponent(entitySource.getRef(), OtherPlayerRoundComponent.getComponentType());
                        RoundComponent roundData = store.getComponent(entitySource.getRef(), RoundComponent.getComponentType());

                        if (otherRoundData != null) {
                            PlayerRef playerRef = Universe.get().getPlayer(otherRoundData.getPlayerOne());
                            if (playerRef != null) {
                                RoundComponent playerOneData = store.getComponent(playerRef.getReference(), RoundComponent.getComponentType());
                                if (playerOneData != null) {
                                    if (playerOneData.getEnemiesLeftToKill() == 0) {
                                        playerOneData.setBonusEnemiesKilled(playerOneData.getBonusEnemiesKilled() + 1);
                                        if (playerOneData.getRoundType() != "sandbox") {
                                            playerOneData.setTotalBonusKillsStat(playerOneData.getTotalBonusKillsStat() + 1);
                                            playerOneData.setTotalKillsStat(playerOneData.getTotalKillsStat() + 1);
                                        }
                                        npc.remove();
                                    } else {
                                        playerOneData.setEnemiesToKill(playerOneData.getEnemiesLeftToKill() - 1);
                                        if (playerOneData.getRoundType() != "sandbox") {
                                            playerOneData.setTotalKillsStat(playerOneData.getTotalKillsStat() + 1);
                                        }
                                        npc.remove();
                                    }
                                }
                            }
                        } else if (roundData != null) {
                            if (roundData.getEnemiesLeftToKill() == 0) {
                                roundData.setBonusEnemiesKilled(roundData.getBonusEnemiesKilled() + 1);
                                if (roundData.getRoundType() != "sandbox") {
                                    roundData.setTotalBonusKillsStat(roundData.getTotalBonusKillsStat() + 1);
                                    roundData.setTotalKillsStat(roundData.getTotalKillsStat() + 1);
                                }
                                npc.remove();
                            } else {
                                roundData.setEnemiesToKill(roundData.getEnemiesLeftToKill() - 1);
                                if (roundData.getRoundType() != "sandbox") {
                                    roundData.setTotalKillsStat(roundData.getTotalKillsStat() + 1);
                                }
                                npc.remove();
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public @Nullable Query<EntityStore> getQuery() {
        return Query.any();
    }
}
