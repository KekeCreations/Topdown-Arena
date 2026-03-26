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

public class LevelMenuUi extends InteractiveCustomUIPage<MenuWithButtonsData> {

    private static final String PLAY_LEVEL_ONE_BUTTON_ID = "PLAYLEVEL1";
    private static final String PLAY_LEVEL_TWO_BUTTON_ID = "PLAYLEVEL2";
    private static final String PLAY_LEVEL_THREE_BUTTON_ID = "PLAYLEVEL3";
    private static final String BACK_BUTTON_ID = "BACK";
    RoundComponent roundData;

    public LevelMenuUi(@Nonnull PlayerRef playerRef, RoundComponent roundData, @Nonnull CustomPageLifetime lifetime) {
        super(playerRef, lifetime, MenuWithButtonsData.CODEC);
        this.roundData = roundData;
    }

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("Pages/level_menu.ui");
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#BACK", EventData.of("OnButtonClicked", BACK_BUTTON_ID), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL1", EventData.of("OnButtonClicked", PLAY_LEVEL_ONE_BUTTON_ID), false);
        if (roundData.getUnlockedLevels() >= 2) {
            uiCommandBuilder.set("#PLAYLEVEL2.TextSpans", Message.raw("LEVEL 2"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL2", EventData.of("OnButtonClicked", PLAY_LEVEL_TWO_BUTTON_ID), false);
        }
        if (roundData.getUnlockedLevels() >= 3) {
            uiCommandBuilder.set("#PLAYLEVEL3.TextSpans", Message.raw("LEVEL 3"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL3", EventData.of("OnButtonClicked", PLAY_LEVEL_THREE_BUTTON_ID), false);
        }
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull MenuWithButtonsData data) {
        super.handleDataEvent(ref, store, data);

        Player player = Objects.requireNonNull(store.getComponent(ref, Player.getComponentType()));
        RoundComponent roundData = Objects.requireNonNull(store.getComponent(ref, RoundComponent.getComponentType()));

        if (PLAY_LEVEL_ONE_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundData.setRoundType("menu_class");
            roundData.setRoundTimer(70);
            roundData.setLevel(1);
            roundData.setEnemiesToKill(5);
            roundData.freezeRoundTimer(true);
        }
        else if (PLAY_LEVEL_TWO_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundData.setRoundType("menu_class");
            roundData.setRoundTimer(70);
            roundData.setLevel(2);
            roundData.setEnemiesToKill(6);
            roundData.freezeRoundTimer(true);
        }
        else if (PLAY_LEVEL_THREE_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundData.setRoundType("menu_class");
            roundData.setRoundTimer(70);
            roundData.setLevel(3);
            roundData.setEnemiesToKill(8);
            roundData.freezeRoundTimer(true);
        }
        else if (BACK_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundData.setRoundType("menu_start");
        }
    }

    @Override
    public void onDismiss(@NotNull Ref<EntityStore> ref, @NotNull Store<EntityStore> store) {
        super.onDismiss(ref, store);
        roundData.setRoundType("menu_start");
    }
}
