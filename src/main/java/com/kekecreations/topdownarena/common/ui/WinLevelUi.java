package com.kekecreations.topdownarena.common.ui;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.RemoveReason;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.protocol.packets.interface_.Page;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.EventData;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.npc.entities.NPCEntity;
import com.kekecreations.topdownarena.common.component.RoundComponent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Objects;

public class WinLevelUi extends InteractiveCustomUIPage<MenuWithButtonsData> {

    private static final String CONTINUE_BUTTON_ID = "CONTINUE";

    RoundComponent roundData;

    public WinLevelUi(@Nonnull PlayerRef playerRef, RoundComponent roundData, @Nonnull CustomPageLifetime lifetime) {
        super(playerRef, lifetime, MenuWithButtonsData.CODEC);
        this.roundData = roundData;
    }

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("Pages/win_level.ui");
        if (roundData.getRoundType() != "sandbox") {
            uiCommandBuilder.set("#WINTEXT.TextSpans", Message.raw("YOU'VE COMPLETED LEVEL " + roundData.getLevel()));
        } else {
            uiCommandBuilder.set("#WINTEXT.TextSpans", Message.raw("YOU'VE COMPLETED LEVEL SANDBOX"));
        }

        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#CONTINUE", EventData.of("OnButtonClicked", CONTINUE_BUTTON_ID), false);
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull MenuWithButtonsData data) {
        super.handleDataEvent(ref, store, data);

        Player player = Objects.requireNonNull(store.getComponent(ref, Player.getComponentType()));
        RoundComponent roundData = Objects.requireNonNull(store.getComponent(ref, RoundComponent.getComponentType()));
        if (CONTINUE_BUTTON_ID.equals(data.buttonClicked)) {
            unlockLevels();
            rewardStars();
            player.getPageManager().setPage(ref, store, Page.None);
            roundData.setRoundType("menu_start");
            roundData.freezeRoundTimer(true);
            store.forEachEntityParallel(NPCEntity.getComponentType(), (index, archetypeChunk, commandBuffer) -> commandBuffer.removeEntity(archetypeChunk.getReferenceTo(index), RemoveReason.REMOVE));
        }
    }

    void rewardStars() {
        switch(roundData.getLevel()) {
            case 1 -> {
                if (roundData.getFinalRatingStarsLevel1() < roundData.getRatingStars()) {
                    roundData.setFinalRatingStarsLevel1(roundData.getRatingStars());
                }
            }
            case 2 -> {
                if (roundData.getFinalRatingStarsLevel2() < roundData.getRatingStars()) {
                    roundData.setFinalRatingStarsLevel2(roundData.getRatingStars());
                }
            }
            case 3 -> {
                if (roundData.getFinalRatingStarsLevel3() < roundData.getRatingStars()) {
                    roundData.setFinalRatingStarsLevel3(roundData.getRatingStars());
                }
            }
        }
    }

    void unlockLevels() {
        if (roundData.getLevel() == 1 && roundData.getUnlockedLevels() <= 1) {
            roundData.setUnlockedLevels(2);
        }
        if (roundData.getLevel() == 2 && roundData.getUnlockedLevels() <= 2) {
            roundData.setUnlockedLevels(3);
        }
        if (roundData.getLevel() == 3 && roundData.getUnlockedLevels() <= 3) {
            roundData.setUnlockedLevels(4);
        }
        if (roundData.getLevel() == 4 && roundData.getUnlockedLevels() <= 4) {
            roundData.setUnlockedLevels(5);
        }
        if (roundData.getLevel() == 5 && roundData.getUnlockedLevels() <= 5) {
            roundData.setUnlockedLevels(6);
        }
        if (roundData.getLevel() == 6 && roundData.getUnlockedLevels() <= 6) {
            roundData.setUnlockedLevels(7);
        }
        if (roundData.getLevel() == 7 && roundData.getUnlockedLevels() <= 7) {
            roundData.setUnlockedLevels(8);
        }
    }

    @Override
    public void onDismiss(@NotNull Ref<EntityStore> ref, @NotNull Store<EntityStore> store) {
        super.onDismiss(ref, store);
        unlockLevels();
        rewardStars();
        roundData.setRoundType("menu_start");
    }
}
