package com.kekecreations.topdownarena.common.ui;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.connection.DisconnectType;
import com.hypixel.hytale.protocol.packets.connection.ServerDisconnect;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.EventData;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.topdownarena.common.component.OtherPlayerRoundComponent;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;

public class InProgressUi extends InteractiveCustomUIPage<MenuWithButtonsData> {

    private static final String QUIT_BUTTON_ID = "QUIT";

    OtherPlayerRoundComponent roundData;

    public InProgressUi(@Nonnull PlayerRef playerRef,  OtherPlayerRoundComponent roundData, @Nonnull CustomPageLifetime lifetime) {
        super(playerRef, lifetime, MenuWithButtonsData.CODEC);
        this.roundData = roundData;
    }

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("Pages/in_progress.ui");
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#QUIT", EventData.of("OnButtonClicked", QUIT_BUTTON_ID), false);
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull MenuWithButtonsData data) {
        super.handleDataEvent(ref, store, data);


        if (QUIT_BUTTON_ID.equals(data.buttonClicked)) {
            playerRef.getPacketHandler().writeNoCache(new ServerDisconnect(null, DisconnectType.Disconnect));
        }
    }

    @Override
    public void onDismiss(@NonNull Ref<EntityStore> ref, @NonNull Store<EntityStore> store) {
        super.onDismiss(ref, store);
        roundData.setRoundType("in_progress");
    }
}
