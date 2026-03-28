package com.kekecreations.topdownarena.common.component;

import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import java.util.UUID;

public class OtherPlayerRoundComponent implements Component<EntityStore> {

    public static final BuilderCodec<OtherPlayerRoundComponent> CODEC =
            BuilderCodec.builder(OtherPlayerRoundComponent.class, OtherPlayerRoundComponent::new)
                    .build();

    private String roundType;

    private int roundLevel;


    private UUID playerOne;

    private int sandboxClassChoice = 0;
    private int sandboxClassLevelChoice = 0;

    public OtherPlayerRoundComponent() {

    }

    public OtherPlayerRoundComponent(String roundType, int roundLevel, UUID playerOne, int sandboxClassChoice, int sandboxClassLevelChoice) {
        this.roundType = roundType;
        this.roundLevel = roundLevel;
        this.playerOne = playerOne;
        this.sandboxClassChoice = sandboxClassChoice;
        this.sandboxClassLevelChoice = sandboxClassLevelChoice;

    }

    public Component<EntityStore> clone() {
        OtherPlayerRoundComponent copy = new OtherPlayerRoundComponent(roundType, roundLevel, playerOne, sandboxClassChoice, sandboxClassLevelChoice);
        copy.roundType= this.roundType;
        copy.roundLevel = this.roundLevel;
        copy.sandboxClassChoice = this.sandboxClassChoice;
        copy.sandboxClassLevelChoice = this.sandboxClassLevelChoice;
        return copy;
    }

    public String getRoundType() {
        return this.roundType;
    }

    public int getLevel() {
        return this.roundLevel;
    }
    public UUID getPlayerOne() {
        return this.playerOne;
    }

    public void setPlayerOne(UUID player) {
        this.playerOne = player;
    }

    public void setRoundType(String roundType) {
        this.roundType = roundType;
    }

    public void setLevel(int round) {
        this.roundLevel = round;
    }

    //SANDBOX
    public int getSandboxClassChoice() {
        return this.sandboxClassChoice;
    }

    public void setClassChoice(int choice) {
        this.sandboxClassChoice = choice;
    }
    public void setClassLevelChoice(int choice) {
        this.sandboxClassLevelChoice = choice;
    }

    public int getSandboxClassLevelChoice() {
        return this.sandboxClassLevelChoice;
    }

    private static ComponentType<EntityStore, OtherPlayerRoundComponent> type;

    public static ComponentType<EntityStore, OtherPlayerRoundComponent> getComponentType(){
        return type;
    }

    public static void setComponentType(ComponentType<EntityStore, OtherPlayerRoundComponent> type2) {
        type = type2;
    }
}
