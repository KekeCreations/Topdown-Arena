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

public class OptionsUi extends InteractiveCustomUIPage<MenuWithButtonsData> {

    private static final String SPIDER_BUTTON_ID = "SPIDERMODE";
    private static final String EASY_BUTTON_ID = "EASYMODE";
    private static final String BACK_BUTTON_ID = "BACK";
    RoundComponent roundData;

    public OptionsUi(@Nonnull PlayerRef playerRef, RoundComponent roundComponent, @Nonnull CustomPageLifetime lifetime) {
        super(playerRef, lifetime, MenuWithButtonsData.CODEC);
        this.roundData = roundComponent;
    }

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("Pages/options_menu.ui");

        if (roundData.getArachnophobiaMode()) {
            uiCommandBuilder.set("#SPIDERMODE.TextSpans", Message.raw("ARACHNOPHOBIA MODE: ON"));
        } else {
            uiCommandBuilder.set("#SPIDERMODE.TextSpans", Message.raw("ARACHNOPHOBIA MODE: OFF"));
        }

        if (roundData.getEasyMode()) {
            uiCommandBuilder.set("#EASYMODE.TextSpans", Message.raw("EASY MODE: ON"));
        } else {
            uiCommandBuilder.set("#EASYMODE.TextSpans", Message.raw("EASY MODE: OFF"));
        }

        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#SPIDERMODE", EventData.of("OnButtonClicked", SPIDER_BUTTON_ID), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#EASYMODE", EventData.of("OnButtonClicked", EASY_BUTTON_ID), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#BACK", EventData.of("OnButtonClicked", BACK_BUTTON_ID), false);
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull MenuWithButtonsData data) {
        super.handleDataEvent(ref, store, data);

        Player player = Objects.requireNonNull(store.getComponent(ref, Player.getComponentType()));
        RoundComponent roundData = Objects.requireNonNull(store.getComponent(ref, RoundComponent.getComponentType()));
        if (store.getComponent(ref, RoundComponent.getComponentType()) == null) {
            store.addComponent(ref, RoundComponent.getComponentType());
        }
        if (SPIDER_BUTTON_ID.equals(data.buttonClicked)) {
            roundData.arachnophobiaMode(!roundData.getArachnophobiaMode());
            player.getPageManager().openCustomPage(ref, store, new OptionsUi(playerRef, roundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
        }
        if (EASY_BUTTON_ID.equals(data.buttonClicked)) {
            roundData.easyMode(!roundData.getEasyMode());
            player.getPageManager().openCustomPage(ref, store, new OptionsUi(playerRef, roundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
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
