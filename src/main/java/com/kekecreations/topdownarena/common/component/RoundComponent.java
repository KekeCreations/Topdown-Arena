package com.kekecreations.topdownarena.common.component;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

public class RoundComponent implements Component<EntityStore> {

    public static final BuilderCodec<RoundComponent> CODEC =
            BuilderCodec.builder(RoundComponent.class, RoundComponent::new)
                    .append(new KeyedCodec<>("RoundTimer", Codec.INTEGER),
                            (c, f) -> c.roundTimer = f, c -> c.roundTimer)
                    .add()
                    .append(new KeyedCodec<>("EnemiesKilled", Codec.INTEGER),
                            (c, f) -> c.enemiesKilled = f, c -> c.enemiesKilled)
                    .add()
                    .append(new KeyedCodec<>("BonusEnemiesKilled", Codec.INTEGER),
                            (c, f) -> c.bonusEnemiesKilled = f, c -> c.bonusEnemiesKilled)
                    .add()
                    .append(new KeyedCodec<>("UnlockedLevels", Codec.INTEGER),
                            (c, f) -> c.unlockedLevels = f, c -> c.unlockedLevels)
                    .add()
                    .append(new KeyedCodec<>("Level", Codec.INTEGER),
                            (c, f) -> c.level = f, c -> c.level)
                    .add()
                    .append(new KeyedCodec<>("RoundType", Codec.STRING),
                            (c, f) -> c.roundType = f, c -> c.roundType)
                    .add()
                    .build();


    private int roundTimer = 0;

    private int enemiesKilled = 0;

    private int bonusEnemiesKilled = 0;

    private int unlockedLevels = 1;

    private int level = 0;

    private String roundType = "null";

    private boolean freezeTimer = false;

    public RoundComponent() {}

    public RoundComponent(int roundTimer, int enemiesKilled, int bonusEnemiesKilled, int unlockedLevels, int level, String roundType, boolean freezeTimer) {
        this.roundTimer = roundTimer;
        this.enemiesKilled = enemiesKilled;
        this.bonusEnemiesKilled = bonusEnemiesKilled;
        this.unlockedLevels = unlockedLevels;
        this.level = level;
        this.roundType = roundType;
        this.freezeTimer = freezeTimer;
    }

    @Override
    public Component<EntityStore> clone() {
        RoundComponent copy = new RoundComponent(roundTimer, enemiesKilled, bonusEnemiesKilled, unlockedLevels, level, roundType, freezeTimer);
        copy.roundTimer = this.roundTimer;
        copy.enemiesKilled = this.enemiesKilled;
        copy.bonusEnemiesKilled = this.bonusEnemiesKilled;
        copy.unlockedLevels = this.unlockedLevels;
        copy.level = this.level;
        copy.roundType = this.roundType;
        copy.freezeTimer = this.freezeTimer;
        return copy;
    }

    public int getRoundTimer() {
        return this.roundTimer;
    }

    public int getEnemiesKilled() {
        return this.enemiesKilled;
    }

    public int getBonusEnemiesKilled() {
        return this.bonusEnemiesKilled;
    }

    public int getUnlockedLevels() {
        return this.unlockedLevels;
    }

    public int getLevel() {
        return this.level;
    }

    public String getRoundType() {
        return this.roundType;
    }

    public boolean isTimerFrozen() {
        return this.freezeTimer;
    }

    public void setRoundType(String newRoundType) {
        this.roundType = newRoundType;
    }

    public void setRoundTimer(int newRoundTimer) {
        this.roundTimer = newRoundTimer;
    }

    public void setLevel(int levelStarted) {
        this.level = levelStarted;
    }

    public void setEnemiesKilled(int newKillCount) {
        this.enemiesKilled = newKillCount;
    }

    public void setBonusEnemiesKilled(int newBonusKillCount) {
        this.bonusEnemiesKilled = newBonusKillCount;
    }

    public void setUnlockedLevels(int newLevelUnlocked) {
        this.unlockedLevels = newLevelUnlocked;
    }

    public void freezeRoundTimer(boolean freezeTimer) {
        this.freezeTimer = freezeTimer;
    }

    private static ComponentType<EntityStore, RoundComponent> type;

    public static ComponentType<EntityStore, RoundComponent> getComponentType(){
        return type;
    }

    public static void setComponentType(ComponentType<EntityStore, RoundComponent> type2){
        type = type2;
    }
}
