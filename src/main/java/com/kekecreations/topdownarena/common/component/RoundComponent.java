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

    private boolean arachnophobiaMode = false;

    private boolean easyMode = false;

    private int sandboxEnemyChoice = 0;
    private int sandboxEnemyChoice2 = 0;
    private int sandboxEnemyChoice3 = 0;
    private int sandboxClassChoice = 0;
    private int sandboxClassLevelChoice = 0;
    private int sandboxEnemyCount = 0;
    private int sandboxEnemyCount2 = 0;
    private int sandboxEnemyCount3 = 0;
    private int sandboxRequiredKills = 1;
    private boolean sandboxRandomWaves = true;

    public RoundComponent() {}

    public RoundComponent(int roundTimer, int enemiesKilled, int bonusEnemiesKilled, int unlockedLevels, int level, String roundType, boolean freezeTimer, boolean arachnophobiaMode, boolean easyMode,
                          int sandboxEnemyChoice, int sandboxEnemyChoice2, int sandboxEnemyChoice3, int sandboxClassChoice, int sandboxClassLevelChoice,
                          int sandboxEnemyCount, int sandboxEnemyCount2, int sandboxEnemyCount3, int sandboxRequiredKills, boolean sandboxRandomWaves) {
        this.roundTimer = roundTimer;
        this.enemiesKilled = enemiesKilled;
        this.bonusEnemiesKilled = bonusEnemiesKilled;
        this.unlockedLevels = unlockedLevels;
        this.level = level;
        this.roundType = roundType;
        this.freezeTimer = freezeTimer;
        this.arachnophobiaMode = arachnophobiaMode;
        this.easyMode = easyMode;
        this.sandboxEnemyChoice = sandboxEnemyChoice;
        this.sandboxEnemyChoice2 = sandboxEnemyChoice2;
        this.sandboxEnemyChoice3 = sandboxEnemyChoice3;
        this.sandboxClassChoice = sandboxClassChoice;
        this.sandboxClassLevelChoice = sandboxClassLevelChoice;
        this.sandboxEnemyCount = sandboxEnemyCount;
        this.sandboxEnemyCount2 = sandboxEnemyCount2;
        this.sandboxEnemyCount3 = sandboxEnemyCount3;
        this.sandboxRequiredKills = sandboxRequiredKills;
        this.sandboxRandomWaves = sandboxRandomWaves;
    }

    @Override
    public Component<EntityStore> clone() {
        RoundComponent copy = new RoundComponent(roundTimer, enemiesKilled, bonusEnemiesKilled, unlockedLevels, level, roundType, freezeTimer, arachnophobiaMode, easyMode, sandboxEnemyChoice, sandboxEnemyChoice2, sandboxEnemyChoice3,
                sandboxClassChoice, sandboxClassLevelChoice, sandboxEnemyCount, sandboxEnemyCount2, sandboxEnemyCount3, sandboxRequiredKills, sandboxRandomWaves);
        copy.roundTimer = this.roundTimer;
        copy.enemiesKilled = this.enemiesKilled;
        copy.bonusEnemiesKilled = this.bonusEnemiesKilled;
        copy.unlockedLevels = this.unlockedLevels;
        copy.level = this.level;
        copy.roundType = this.roundType;
        copy.freezeTimer = this.freezeTimer;
        copy.arachnophobiaMode = this.arachnophobiaMode;
        copy.easyMode = this.easyMode;
        copy.sandboxEnemyChoice = this.sandboxEnemyChoice;
        copy.sandboxEnemyChoice2 = this.sandboxEnemyChoice2;
        copy.sandboxEnemyChoice3 = this.sandboxEnemyChoice3;
        copy.sandboxClassChoice = this.sandboxClassChoice;
        copy.sandboxClassLevelChoice = this.sandboxClassLevelChoice;
        copy.sandboxEnemyCount = this.sandboxEnemyCount;
        copy.sandboxEnemyCount2 = this.sandboxEnemyCount2;
        copy.sandboxEnemyCount3 = this.sandboxEnemyCount3;
        copy.sandboxRequiredKills = this.sandboxRequiredKills;
        copy.sandboxRandomWaves = this.sandboxRandomWaves;
        return copy;
    }

    //SANDBOX
    public int getSandboxEnemyChoice() {
        return this.sandboxEnemyChoice;
    }
    public int getSandboxEnemyChoice2() {
        return this.sandboxEnemyChoice2;
    }
    public int getSandboxEnemyChoice3() {
        return this.sandboxEnemyChoice3;
    }
    public int getSandboxClassChoice() {
        return this.sandboxClassChoice;
    }
    public int getSandboxClassLevelChoice() {
        return this.sandboxClassLevelChoice;
    }
    public int getSandboxEnemyCount() {
        return this.sandboxEnemyCount;
    }
    public int getSandboxEnemyCount2() {
        return this.sandboxEnemyCount2;
    }
    public int getSandboxEnemyCount3() {
        return this.sandboxEnemyCount3;
    }
    public void setEnemyChoice(int enemyChoice) {
        this.sandboxEnemyChoice = enemyChoice;
    }
    public void setEnemyChoice2(int enemyChoice) {
        this.sandboxEnemyChoice2 = enemyChoice;
    }
    public void setEnemyChoice3(int enemyChoice) {
        this.sandboxEnemyChoice3 = enemyChoice;
    }
    public void setClassChoice(int classChoice) {
        this.sandboxClassChoice= classChoice;
    }
    public void setClassLevelChoice(int classLevelChoice) {
        this.sandboxClassLevelChoice= classLevelChoice;
    }
    public void setEnemyCount(int enemyCount) {
        this.sandboxEnemyCount = enemyCount;
    }
    public void setEnemyCount2(int enemyCount) {
        this.sandboxEnemyCount2 = enemyCount;
    }
    public void setEnemyCount3(int enemyCount) {
        this.sandboxEnemyCount3 = enemyCount;
    }
    public int getSandboxRequiredKills() {
        return this.sandboxRequiredKills;
    }
    public void setRequiredKills(int killCount) {
        this.sandboxRequiredKills = killCount;
    }
    public boolean getSandboxRandomWaves() {
        return this.sandboxRandomWaves;
    }
    public boolean setSandboxRandomWaves(boolean wave) {
        return this.sandboxRandomWaves = wave;
    }



    //NORMAL + SANDBOX
    public int getRoundTimer() {
        return this.roundTimer;
    }

    public boolean getArachnophobiaMode() {
        return this.arachnophobiaMode;
    }

    public boolean getEasyMode() {
        return this.easyMode;
    }

    public int getEnemiesLeftToKill() {
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

    public void setEnemiesToKill(int newKillCount) {
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

    public void arachnophobiaMode(boolean arachnophobiaMode) {
        this.arachnophobiaMode = arachnophobiaMode;
    }

    public void easyMode(boolean easyMode) {
        this.easyMode = easyMode;
    }

    private static ComponentType<EntityStore, RoundComponent> type;

    public static ComponentType<EntityStore, RoundComponent> getComponentType(){
        return type;
    }

    public static void setComponentType(ComponentType<EntityStore, RoundComponent> type2){
        type = type2;
    }
}
