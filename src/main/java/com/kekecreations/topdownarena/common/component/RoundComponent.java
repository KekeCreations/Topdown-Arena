package com.kekecreations.topdownarena.common.component;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import java.util.UUID;

public class RoundComponent implements Component<EntityStore> {

    public static final BuilderCodec<RoundComponent> CODEC =
            BuilderCodec.builder(RoundComponent.class, RoundComponent::new)
                    .append(new KeyedCodec<>("UnlockedLevels", Codec.INTEGER),
                            (c, f) -> c.unlockedLevels = f, c -> c.unlockedLevels)
                    .add()
                    .append(new KeyedCodec<>("ArachnophobiaMode", Codec.BOOLEAN),
                            (c, f) -> c.arachnophobiaMode = f, c -> c.arachnophobiaMode)
                    .add()
                    .append(new KeyedCodec<>("EasyMode", Codec.BOOLEAN),
                            (c, f) -> c.easyMode = f, c -> c.easyMode)
                    .add()
                    .append(new KeyedCodec<>("RoundsPlayed", Codec.INTEGER),
                            (c, f) -> c.roundsPlayedStat = f, c -> c.roundsPlayedStat)
                    .add()
                    .append(new KeyedCodec<>("SandboxRoundsPlayed", Codec.INTEGER),
                            (c, f) -> c.sandboxRoundsPlayedStat = f, c -> c.sandboxRoundsPlayedStat)
                    .add()
                    .append(new KeyedCodec<>("TotalKills", Codec.INTEGER),
                            (c, f) -> c.totalKillsStat = f, c -> c.totalKillsStat)
                    .add()
                    .append(new KeyedCodec<>("TotalBonusKills", Codec.INTEGER),
                            (c, f) -> c.totalBonusKillsStat = f, c -> c.totalBonusKillsStat)
                    .add()
                    .append(new KeyedCodec<>("RoundsWon", Codec.INTEGER),
                            (c, f) -> c.roundsWonStat = f, c -> c.roundsWonStat)
                    .add()
                    .append(new KeyedCodec<>("StarsLevel1", Codec.INTEGER),
                            (c, f) -> c.finalRatingStarsLevel1 = f, c -> c.finalRatingStarsLevel1)
                    .add()
                    .append(new KeyedCodec<>("StarsLevel2", Codec.INTEGER),
                            (c, f) -> c.finalRatingStarsLevel2 = f, c -> c.finalRatingStarsLevel2)
                    .add()
                    .append(new KeyedCodec<>("StarsLevel3", Codec.INTEGER),
                            (c, f) -> c.finalRatingStarsLevel3 = f, c -> c.finalRatingStarsLevel3)
                    .add()
                    .append(new KeyedCodec<>("StarsLevel4", Codec.INTEGER),
                            (c, f) -> c.finalRatingStarsLevel4 = f, c -> c.finalRatingStarsLevel4)
                    .add()
                    .append(new KeyedCodec<>("StarsLevel5", Codec.INTEGER),
                            (c, f) -> c.finalRatingStarsLevel5 = f, c -> c.finalRatingStarsLevel5)
                    .add()
                    .append(new KeyedCodec<>("StarsLevel6", Codec.INTEGER),
                            (c, f) -> c.finalRatingStarsLevel6 = f, c -> c.finalRatingStarsLevel6)
                    .add()
                    .append(new KeyedCodec<>("StarsLevel7", Codec.INTEGER),
                            (c, f) -> c.finalRatingStarsLevel7 = f, c -> c.finalRatingStarsLevel7)
                    .add()
                    .append(new KeyedCodec<>("StarsLevel8", Codec.INTEGER),
                            (c, f) -> c.finalRatingStarsLevel8 = f, c -> c.finalRatingStarsLevel8)
                    .add()
                    .append(new KeyedCodec<>("StarsLevel9", Codec.INTEGER),
                            (c, f) -> c.finalRatingStarsLevel9 = f, c -> c.finalRatingStarsLevel9)
                    .add()
                    .append(new KeyedCodec<>("StarsLevel10", Codec.INTEGER),
                            (c, f) -> c.finalRatingStarsLevel10 = f, c -> c.finalRatingStarsLevel10)
                    .add()
                    .append(new KeyedCodec<>("StarsLevel11", Codec.INTEGER),
                            (c, f) -> c.finalRatingStarsLevel11 = f, c -> c.finalRatingStarsLevel11)
                    .add()
                    .append(new KeyedCodec<>("StarsLevel12", Codec.INTEGER),
                            (c, f) -> c.finalRatingStarsLevel12 = f, c -> c.finalRatingStarsLevel12)
                    .add()
                    .append(new KeyedCodec<>("StarsLevel13", Codec.INTEGER),
                            (c, f) -> c.finalRatingStarsLevel13 = f, c -> c.finalRatingStarsLevel13)
                    .add()
                    .append(new KeyedCodec<>("StarsLevel14", Codec.INTEGER),
                            (c, f) -> c.finalRatingStarsLevel14 = f, c -> c.finalRatingStarsLevel14)
                    .add()
                    .append(new KeyedCodec<>("StarsLevel15", Codec.INTEGER),
                            (c, f) -> c.finalRatingStarsLevel15 = f, c -> c.finalRatingStarsLevel15)
                    .add()
                    .append(new KeyedCodec<>("StarsLevel16", Codec.INTEGER),
                            (c, f) -> c.finalRatingStarsLevel16 = f, c -> c.finalRatingStarsLevel16)
                    .add()
                    .append(new KeyedCodec<>("StarsLevel17", Codec.INTEGER),
                            (c, f) -> c.finalRatingStarsLevel17 = f, c -> c.finalRatingStarsLevel17)
                    .add()
                    .append(new KeyedCodec<>("StarsLevel18", Codec.INTEGER),
                            (c, f) -> c.finalRatingStarsLevel18 = f, c -> c.finalRatingStarsLevel18)
                    .add()
                    .append(new KeyedCodec<>("StarsLevel19", Codec.INTEGER),
                            (c, f) -> c.finalRatingStarsLevel19 = f, c -> c.finalRatingStarsLevel19)
                    .add()
                    .append(new KeyedCodec<>("StarsLevel20", Codec.INTEGER),
                            (c, f) -> c.finalRatingStarsLevel20 = f, c -> c.finalRatingStarsLevel20)
                    .add()
                    .append(new KeyedCodec<>("StarsLevel21", Codec.INTEGER),
                            (c, f) -> c.finalRatingStarsLevel21 = f, c -> c.finalRatingStarsLevel21)
                    .add()
                    .append(new KeyedCodec<>("StarsLevel22", Codec.INTEGER),
                            (c, f) -> c.finalRatingStarsLevel22 = f, c -> c.finalRatingStarsLevel22)
                    .add()
                    .append(new KeyedCodec<>("StarsLevel23", Codec.INTEGER),
                            (c, f) -> c.finalRatingStarsLevel23 = f, c -> c.finalRatingStarsLevel23)
                    .add()
                    .append(new KeyedCodec<>("StarsLevel24", Codec.INTEGER),
                            (c, f) -> c.finalRatingStarsLevel24 = f, c -> c.finalRatingStarsLevel24)
                    .add()
                    .append(new KeyedCodec<>("StarsLevel25", Codec.INTEGER),
                            (c, f) -> c.finalRatingStarsLevel25 = f, c -> c.finalRatingStarsLevel25)
                    .add()
                    .build();

    private UUID playerOne;
    //Stats
    private int roundsPlayedStat = 0;
    private int sandboxRoundsPlayedStat = 0;
    private int totalKillsStat = 0;
    private int totalBonusKillsStat = 0;
    private int roundsWonStat = 0;

    private int roundTimer = 0;

    private int enemiesKilled = 0;

    private int bonusEnemiesKilled = 0;

    private int unlockedLevels = 1;

    private int level = 0;

    private String roundType = "null";

    private boolean freezeTimer = false;

    private boolean arachnophobiaMode = false;

    private boolean easyMode = false;

    private int ratingStars = 0;
    private int finalRatingStarsLevel1 = 0;
    private int finalRatingStarsLevel2 = 0;
    private int finalRatingStarsLevel3 = 0;
    private int finalRatingStarsLevel4 = 0;
    private int finalRatingStarsLevel5 = 0;
    private int finalRatingStarsLevel6 = 0;
    private int finalRatingStarsLevel7 = 0;
    private int finalRatingStarsLevel8 = 0;
    private int finalRatingStarsLevel9 = 0;
    private int finalRatingStarsLevel10 = 0;
    private int finalRatingStarsLevel11 = 0;
    private int finalRatingStarsLevel12 = 0;
    private int finalRatingStarsLevel13 = 0;
    private int finalRatingStarsLevel14 = 0;
    private int finalRatingStarsLevel15 = 0;
    private int finalRatingStarsLevel16 = 0;
    private int finalRatingStarsLevel17 = 0;
    private int finalRatingStarsLevel18 = 0;
    private int finalRatingStarsLevel19 = 0;
    private int finalRatingStarsLevel20 = 0;
    private int finalRatingStarsLevel21 = 0;
    private int finalRatingStarsLevel22 = 0;
    private int finalRatingStarsLevel23 = 0;
    private int finalRatingStarsLevel24 = 0;
    private int finalRatingStarsLevel25 = 0;


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
                          int sandboxEnemyCount, int sandboxEnemyCount2, int sandboxEnemyCount3, int sandboxRequiredKills, boolean sandboxRandomWaves, int roundsPlayedStat,
                          int sandboxRoundsPlayedStat, int totalKillsStat, int totalBonusKillsStat, int roundsWonStat, UUID playerOne, int ratingStars,
                          int finalRatingStarsLevel1, int finalRatingStarsLevel2, int finalRatingStarsLevel3, int finalRatingStarsLevel4, int finalRatingStarsLevel5,
                          int finalRatingStarsLevel6, int finalRatingStarsLevel7, int finalRatingStarsLevel8, int finalRatingStarsLevel9, int finalRatingStarsLevel10,
                          int finalRatingStarsLevel11, int finalRatingStarsLevel12, int finalRatingStarsLevel13, int finalRatingStarsLevel14, int finalRatingStarsLevel15,
                          int finalRatingStarsLevel16, int finalRatingStarsLevel17, int finalRatingStarsLevel18, int finalRatingStarsLevel19, int finalRatingStarsLevel20,
                          int finalRatingStarsLevel21, int finalRatingStarsLevel22, int finalRatingStarsLevel23, int finalRatingStarsLevel24, int finalRatingStarsLevel25) {
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
        this.roundsPlayedStat = roundsPlayedStat;
        this.sandboxRoundsPlayedStat = sandboxRoundsPlayedStat;
        this.totalKillsStat = totalKillsStat;
        this.totalBonusKillsStat = totalBonusKillsStat;
        this.roundsWonStat = roundsWonStat;
        this.playerOne = playerOne;
        this.ratingStars = ratingStars;
        this.finalRatingStarsLevel1 = finalRatingStarsLevel1;
        this.finalRatingStarsLevel2 = finalRatingStarsLevel2;
        this.finalRatingStarsLevel3 = finalRatingStarsLevel3;
        this.finalRatingStarsLevel4 = finalRatingStarsLevel4;
        this.finalRatingStarsLevel5 = finalRatingStarsLevel5;
        this.finalRatingStarsLevel6 = finalRatingStarsLevel6;
        this.finalRatingStarsLevel7 = finalRatingStarsLevel7;
        this.finalRatingStarsLevel8 = finalRatingStarsLevel8;
        this.finalRatingStarsLevel9 = finalRatingStarsLevel9;
        this.finalRatingStarsLevel10 = finalRatingStarsLevel10;
        this.finalRatingStarsLevel11 = finalRatingStarsLevel11;
        this.finalRatingStarsLevel12 = finalRatingStarsLevel12;
        this.finalRatingStarsLevel13 = finalRatingStarsLevel13;
        this.finalRatingStarsLevel14 = finalRatingStarsLevel14;
        this.finalRatingStarsLevel15 = finalRatingStarsLevel15;
        this.finalRatingStarsLevel16 = finalRatingStarsLevel16;
        this.finalRatingStarsLevel17 = finalRatingStarsLevel17;
        this.finalRatingStarsLevel18 = finalRatingStarsLevel18;
        this.finalRatingStarsLevel19 = finalRatingStarsLevel19;
        this.finalRatingStarsLevel20 = finalRatingStarsLevel20;
        this.finalRatingStarsLevel21 = finalRatingStarsLevel21;
        this.finalRatingStarsLevel22 = finalRatingStarsLevel22;
        this.finalRatingStarsLevel23 = finalRatingStarsLevel23;
        this.finalRatingStarsLevel24 = finalRatingStarsLevel24;
        this.finalRatingStarsLevel25 = finalRatingStarsLevel25;
    }

    @Override
    public Component<EntityStore> clone() {
        RoundComponent copy = new RoundComponent(roundTimer, enemiesKilled, bonusEnemiesKilled, unlockedLevels, level, roundType, freezeTimer, arachnophobiaMode, easyMode, sandboxEnemyChoice, sandboxEnemyChoice2, sandboxEnemyChoice3,
                sandboxClassChoice, sandboxClassLevelChoice, sandboxEnemyCount, sandboxEnemyCount2, sandboxEnemyCount3, sandboxRequiredKills, sandboxRandomWaves, roundsPlayedStat,
                sandboxRoundsPlayedStat, totalKillsStat, totalBonusKillsStat, roundsWonStat, playerOne, ratingStars,
                finalRatingStarsLevel1, finalRatingStarsLevel2, finalRatingStarsLevel3, finalRatingStarsLevel4, finalRatingStarsLevel5,
                finalRatingStarsLevel6, finalRatingStarsLevel7, finalRatingStarsLevel8, finalRatingStarsLevel9, finalRatingStarsLevel10,
                finalRatingStarsLevel11, finalRatingStarsLevel12, finalRatingStarsLevel13, finalRatingStarsLevel14, finalRatingStarsLevel15,
                finalRatingStarsLevel16, finalRatingStarsLevel17, finalRatingStarsLevel18, finalRatingStarsLevel19, finalRatingStarsLevel20,
                finalRatingStarsLevel21, finalRatingStarsLevel22, finalRatingStarsLevel23, finalRatingStarsLevel24, finalRatingStarsLevel25);
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
    //MULTIPLAYER
    public UUID getPlayerOne() {
        return this.playerOne;
    }
    public void setPlayerOne(UUID playerOne) {
        this.playerOne = playerOne;
    }
    //STATS
    public int getRoundsPlayedStat() {
        return this.roundsPlayedStat;
    }
    public int getRoundsWonStat() {
        return this.roundsWonStat;
    }
    public int getSandboxRoundsPlayedStat() {
        return this.sandboxRoundsPlayedStat;
    }
    public int getTotalKillsStat() {
        return this.totalKillsStat;
    }
    public int getTotalBonusKillsStat() {
        return this.totalBonusKillsStat;
    }
    public void setRoundsPlayedStat(int stat) {
        this.roundsPlayedStat = stat;
    }
    public void setSandboxRoundsPlayedStat(int stat) {
        this.sandboxRoundsPlayedStat = stat;
    }
    public void setTotalKillsStat(int stat) {
        this.totalKillsStat = stat;
    }
    public void setTotalBonusKillsStat(int stat) {
        this.totalBonusKillsStat = stat;
    }
    public void setRoundsWonStat(int stat) {
        this.roundsWonStat = stat;
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

    public int getRatingStars() {
        return this.ratingStars;
    }

    public int getFinalRatingStarsLevel1() { return this.finalRatingStarsLevel1; }
    public int getFinalRatingStarsLevel2() { return this.finalRatingStarsLevel2; }
    public int getFinalRatingStarsLevel3() { return this.finalRatingStarsLevel3; }
    public int getFinalRatingStarsLevel4() { return this.finalRatingStarsLevel4; }
    public int getFinalRatingStarsLevel5() { return this.finalRatingStarsLevel5; }
    public int getFinalRatingStarsLevel6() { return this.finalRatingStarsLevel6; }
    public int getFinalRatingStarsLevel7() { return this.finalRatingStarsLevel7; }
    public int getFinalRatingStarsLevel8() { return this.finalRatingStarsLevel8; }
    public int getFinalRatingStarsLevel9() { return this.finalRatingStarsLevel9; }
    public int getFinalRatingStarsLevel10() { return this.finalRatingStarsLevel10; }
    public int getFinalRatingStarsLevel11() { return this.finalRatingStarsLevel11; }
    public int getFinalRatingStarsLevel12() { return this.finalRatingStarsLevel12; }
    public int getFinalRatingStarsLevel13() { return this.finalRatingStarsLevel13; }
    public int getFinalRatingStarsLevel14() { return this.finalRatingStarsLevel14; }
    public int getFinalRatingStarsLevel15() { return this.finalRatingStarsLevel15; }
    public int getFinalRatingStarsLevel16() { return this.finalRatingStarsLevel16; }
    public int getFinalRatingStarsLevel17() { return this.finalRatingStarsLevel17; }
    public int getFinalRatingStarsLevel18() { return this.finalRatingStarsLevel18; }
    public int getFinalRatingStarsLevel19() { return this.finalRatingStarsLevel19; }
    public int getFinalRatingStarsLevel20() { return this.finalRatingStarsLevel20; }
    public int getFinalRatingStarsLevel21() { return this.finalRatingStarsLevel21; }
    public int getFinalRatingStarsLevel22() { return this.finalRatingStarsLevel22; }
    public int getFinalRatingStarsLevel23() { return this.finalRatingStarsLevel23; }
    public int getFinalRatingStarsLevel24() { return this.finalRatingStarsLevel24; }
    public int getFinalRatingStarsLevel25() { return this.finalRatingStarsLevel25; }

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

    public void setRatingStars(int stars) {
        this.ratingStars = stars;
    }

    public void setFinalRatingStarsLevel1(int stars) { this.finalRatingStarsLevel1 = stars; }
    public void setFinalRatingStarsLevel2(int stars) { this.finalRatingStarsLevel2 = stars; }
    public void setFinalRatingStarsLevel3(int stars) { this.finalRatingStarsLevel3 = stars; }
    public void setFinalRatingStarsLevel4(int stars) { this.finalRatingStarsLevel4 = stars; }
    public void setFinalRatingStarsLevel5(int stars) { this.finalRatingStarsLevel5 = stars; }
    public void setFinalRatingStarsLevel6(int stars) { this.finalRatingStarsLevel6 = stars; }
    public void setFinalRatingStarsLevel7(int stars) { this.finalRatingStarsLevel7 = stars; }
    public void setFinalRatingStarsLevel8(int stars) { this.finalRatingStarsLevel8 = stars; }
    public void setFinalRatingStarsLevel9(int stars) { this.finalRatingStarsLevel9 = stars; }
    public void setFinalRatingStarsLevel10(int stars) { this.finalRatingStarsLevel10 = stars; }
    public void setFinalRatingStarsLevel11(int stars) { this.finalRatingStarsLevel11 = stars; }
    public void setFinalRatingStarsLevel12(int stars) { this.finalRatingStarsLevel12 = stars; }
    public void setFinalRatingStarsLevel13(int stars) { this.finalRatingStarsLevel13 = stars; }
    public void setFinalRatingStarsLevel14(int stars) { this.finalRatingStarsLevel14 = stars; }
    public void setFinalRatingStarsLevel15(int stars) { this.finalRatingStarsLevel15 = stars; }
    public void setFinalRatingStarsLevel16(int stars) { this.finalRatingStarsLevel16 = stars; }
    public void setFinalRatingStarsLevel17(int stars) { this.finalRatingStarsLevel17 = stars; }
    public void setFinalRatingStarsLevel18(int stars) { this.finalRatingStarsLevel18 = stars; }
    public void setFinalRatingStarsLevel19(int stars) { this.finalRatingStarsLevel19 = stars; }
    public void setFinalRatingStarsLevel20(int stars) { this.finalRatingStarsLevel20 = stars; }
    public void setFinalRatingStarsLevel21(int stars) { this.finalRatingStarsLevel21 = stars; }
    public void setFinalRatingStarsLevel22(int stars) { this.finalRatingStarsLevel22 = stars; }
    public void setFinalRatingStarsLevel23(int stars) { this.finalRatingStarsLevel23 = stars; }
    public void setFinalRatingStarsLevel24(int stars) { this.finalRatingStarsLevel24 = stars; }
    public void setFinalRatingStarsLevel25(int stars) { this.finalRatingStarsLevel25 = stars; }

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
