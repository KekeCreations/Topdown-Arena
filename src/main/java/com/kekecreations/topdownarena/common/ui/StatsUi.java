package com.kekecreations.topdownarena.common.ui;

import com.hypixel.hytale.component.Ref;
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
import com.kekecreations.topdownarena.common.component.RoundComponent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Objects;

public class StatsUi extends InteractiveCustomUIPage<MenuWithButtonsData> {

    private static final String BACK_BUTTON_ID = "BACK";
    private static final String RESET_STATS_BUTTON_ID = "RESETSTATS";
    RoundComponent roundData;

    public StatsUi(@Nonnull PlayerRef playerRef, RoundComponent roundData, @Nonnull CustomPageLifetime lifetime) {
        super(playerRef, lifetime, MenuWithButtonsData.CODEC);
        this.roundData = roundData;
    }

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("Pages/stat_menu.ui");
        uiCommandBuilder.set("#ROUNDSPLAYED.TextSpans", Message.raw("ROUNDS PLAYED: " + roundData.getRoundsPlayedStat()));
        uiCommandBuilder.set("#SANDBOXROUNDSPLAYED.TextSpans", Message.raw("SANDBOX ROUNDS PLAYED: " + roundData.getSandboxRoundsPlayedStat()));
        uiCommandBuilder.set("#ROUNDSWON.TextSpans", Message.raw("ROUNDS WON: " + roundData.getRoundsWonStat()));
        uiCommandBuilder.set("#TOTALKILLS.TextSpans", Message.raw("TOTAL KILLS: " + roundData.getTotalKillsStat()));
        uiCommandBuilder.set("#TOTALBONUSKILLS.TextSpans", Message.raw("TOTAL BONUS KILLS: " + roundData.getTotalBonusKillsStat()));

        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#BACK", EventData.of("OnButtonClicked", BACK_BUTTON_ID), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#RESETSTATS", EventData.of("OnButtonClicked", RESET_STATS_BUTTON_ID), false);
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull MenuWithButtonsData data) {
        super.handleDataEvent(ref, store, data);

        Player player = Objects.requireNonNull(store.getComponent(ref, Player.getComponentType()));
        if (BACK_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundData.setRoundType("menu_start");
        }
        if (RESET_STATS_BUTTON_ID.equals(data.buttonClicked)) {
            roundData.setRoundsWonStat(0);
            roundData.setRoundsPlayedStat(0);
            roundData.setSandboxRoundsPlayedStat(0);
            roundData.setTotalKillsStat(0);
            roundData.setTotalBonusKillsStat(0);
            player.getPageManager().openCustomPage(ref, store, new StatsUi(playerRef, roundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
        }
    }

    @Override
    public void onDismiss(@NotNull Ref<EntityStore> ref, @NotNull Store<EntityStore> store) {
        super.onDismiss(ref, store);
        if (roundData.getRoundType() == "null") {
            roundData.setRoundType("menu_start");
            roundData.setLevel(0);
        }
    }
}
