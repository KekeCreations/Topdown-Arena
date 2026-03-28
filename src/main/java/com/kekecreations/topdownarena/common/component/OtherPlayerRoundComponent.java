package com.kekecreations.topdownarena.common.component;

import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

public class OtherPlayerRoundComponent implements Component<EntityStore> {

    public static final BuilderCodec<OtherPlayerRoundComponent> CODEC =
            BuilderCodec.builder(OtherPlayerRoundComponent.class, OtherPlayerRoundComponent::new)
                    .build();

    private String roundType;

    private int roundLevel;

    public OtherPlayerRoundComponent() {

    }

    public OtherPlayerRoundComponent(String roundType, int roundLevel) {
        this.roundType = roundType;
        this.roundLevel = roundLevel;

    }

    public Component<EntityStore> clone() {
        OtherPlayerRoundComponent copy = new OtherPlayerRoundComponent(roundType, roundLevel);
        copy.roundType= this.roundType;
        copy.roundLevel = this.roundLevel;
        return copy;
    }

    public String getRoundType() {
        return roundType;
    }

    public int getLevel() {
        return roundLevel;
    }

    public void setRoundType(String roundType) {
        this.roundType = roundType;
    }

    public void setLevel(int round) {
        this.roundLevel = round;
    }

    private static ComponentType<EntityStore, OtherPlayerRoundComponent> type;

    public static ComponentType<EntityStore, OtherPlayerRoundComponent> getComponentType(){
        return type;
    }

    public static void setComponentType(ComponentType<EntityStore, OtherPlayerRoundComponent> type2) {
        type = type2;
    }
}
