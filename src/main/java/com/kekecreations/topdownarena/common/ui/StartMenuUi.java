package com.kekecreations.topdownarena.common.ui;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.RemoveReason;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.*;
import com.hypixel.hytale.protocol.packets.interface_.CustomPage;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.protocol.packets.interface_.Page;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.EventData;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.npc.entities.NPCEntity;
import com.kekecreations.topdownarena.common.component.RoundComponent;

import javax.annotation.Nonnull;
import java.util.Objects;

public class StartMenuUi extends InteractiveCustomUIPage<MenuWithButtonsData> {

    private static final String PLAY_LEVELS_BUTTON_ID = "PLAYLEVELS";
    private static final String PLAY_SANDBOX_BUTTON_ID = "PLAYSANDBOX";
    private static final String QUIT_BUTTON_ID = "QUIT";

    public StartMenuUi(@Nonnull PlayerRef playerRef, @Nonnull CustomPageLifetime lifetime) {
        super(playerRef, lifetime, MenuWithButtonsData.CODEC);
    }

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("Pages/start_menu.ui");

        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVELS", EventData.of("OnButtonClicked", PLAY_LEVELS_BUTTON_ID), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYSANDBOX", EventData.of("OnButtonClicked", PLAY_SANDBOX_BUTTON_ID), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#QUIT", EventData.of("OnButtonClicked", QUIT_BUTTON_ID), false);
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull MenuWithButtonsData data) {
        super.handleDataEvent(ref, store, data);

        Player player = Objects.requireNonNull(store.getComponent(ref, Player.getComponentType()));
        RoundComponent roundData = Objects.requireNonNull(store.getComponent(ref, RoundComponent.getComponentType()));
        if (store.getComponent(ref, RoundComponent.getComponentType()) == null) {
            store.addComponent(ref, RoundComponent.getComponentType());
        }
        if (PLAY_LEVELS_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundData.setRoundType("menu_levels");
            roundData.setBonusEnemiesKilled(0);
            roundData.setEnemiesToKill(0);
            store.forEachEntityParallel(NPCEntity.getComponentType(), (index, archetypeChunk, commandBuffer) -> commandBuffer.removeEntity(archetypeChunk.getReferenceTo(index), RemoveReason.REMOVE));
        }
        else if (PLAY_SANDBOX_BUTTON_ID.equals(data.buttonClicked)) {
        }
        else if (QUIT_BUTTON_ID.equals(data.buttonClicked)) {
            System.exit(0);
        }
    }
}
