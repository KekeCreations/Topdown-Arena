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
        uiCommandBuilder.set("#WINTEXT.TextSpans", Message.raw("YOU'VE COMPLETED LEVEL " + roundData.getLevel()));

        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#CONTINUE", EventData.of("OnButtonClicked", CONTINUE_BUTTON_ID), false);
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull MenuWithButtonsData data) {
        super.handleDataEvent(ref, store, data);

        Player player = Objects.requireNonNull(store.getComponent(ref, Player.getComponentType()));
        RoundComponent roundData = Objects.requireNonNull(store.getComponent(ref, RoundComponent.getComponentType()));
        if (CONTINUE_BUTTON_ID.equals(data.buttonClicked)) {
            if (roundData.getLevel() == 1 && roundData.getUnlockedLevels() <= 1) {
                roundData.setUnlockedLevels(2);
            }
            if (roundData.getLevel() == 2 && roundData.getUnlockedLevels() <= 2) {
                roundData.setUnlockedLevels(3);
            }
            player.getPageManager().setPage(ref, store, Page.None);
            roundData.setRoundType("menu_start");
            roundData.freezeRoundTimer(true);
            roundData.setRoundsPlayedStat(roundData.getRoundsPlayedStat() + 1);
            roundData.setRoundsWonStat(roundData.getRoundsWonStat() + 1);
            store.forEachEntityParallel(NPCEntity.getComponentType(), (index, archetypeChunk, commandBuffer) -> commandBuffer.removeEntity(archetypeChunk.getReferenceTo(index), RemoveReason.REMOVE));
        }
    }

    @Override
    public void onDismiss(@NotNull Ref<EntityStore> ref, @NotNull Store<EntityStore> store) {
        super.onDismiss(ref, store);
        roundData.setRoundsPlayedStat(roundData.getRoundsPlayedStat() + 1);
        roundData.setRoundsWonStat(roundData.getRoundsWonStat() + 1);
        roundData.setRoundType("menu_start");
    }
}
