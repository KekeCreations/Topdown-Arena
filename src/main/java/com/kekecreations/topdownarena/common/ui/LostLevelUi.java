package com.kekecreations.topdownarena.common.ui;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.RemoveReason;
import com.hypixel.hytale.component.Store;
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
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Objects;

public class LostLevelUi extends InteractiveCustomUIPage<MenuWithButtonsData> {

    private static final String CONTINUE_BUTTON_ID = "CONTINUE";


    public LostLevelUi(@Nonnull PlayerRef playerRef, @Nonnull CustomPageLifetime lifetime) {
        super(playerRef, lifetime, MenuWithButtonsData.CODEC);
    }

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("Pages/lost_level.ui");

        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#CONTINUE", EventData.of("OnButtonClicked", CONTINUE_BUTTON_ID), false);
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull MenuWithButtonsData data) {
        super.handleDataEvent(ref, store, data);

        Player player = Objects.requireNonNull(store.getComponent(ref, Player.getComponentType()));
        RoundComponent roundData = Objects.requireNonNull(store.getComponent(ref, RoundComponent.getComponentType()));
        if (CONTINUE_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundData.setRoundType("menu_start");
            roundData.freezeRoundTimer(true);
            store.forEachEntityParallel(NPCEntity.getComponentType(), (index, archetypeChunk, commandBuffer) -> commandBuffer.removeEntity(archetypeChunk.getReferenceTo(index), RemoveReason.REMOVE));
        }
    }
}
