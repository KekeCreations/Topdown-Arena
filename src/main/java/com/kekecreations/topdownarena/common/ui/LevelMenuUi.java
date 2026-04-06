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
    private static final String PLAY_LEVEL_FOUR_BUTTON_ID = "PLAYLEVEL4";
    private static final String PLAY_LEVEL_FIVE_BUTTON_ID = "PLAYLEVEL5";
    private static final String PLAY_LEVEL_SIX_BUTTON_ID = "PLAYLEVEL6";
    private static final String PLAY_LEVEL_SEVEN_BUTTON_ID = "PLAYLEVEL7";
    private static final String PLAY_LEVEL_EIGHT_BUTTON_ID = "PLAYLEVEL8";
    private static final String PLAY_LEVEL_NINE_BUTTON_ID = "PLAYLEVEL9";
    private static final String PLAY_LEVEL_TEN_BUTTON_ID = "PLAYLEVEL10";
    private static final String PLAY_LEVEL_ELEVEN_BUTTON_ID = "PLAYLEVEL11";
    private static final String PLAY_LEVEL_TWELVE_BUTTON_ID = "PLAYLEVEL12";
    private static final String PLAY_LEVEL_THIRTEEN_BUTTON_ID = "PLAYLEVEL13";
    private static final String PLAY_LEVEL_FOURTEEN_BUTTON_ID = "PLAYLEVEL14";
    private static final String PLAY_LEVEL_FIFTEEN_BUTTON_ID = "PLAYLEVEL15";
    private static final String PLAY_LEVEL_16_BUTTON_ID = "PLAYLEVEL16";
    private static final String PLAY_LEVEL_17_BUTTON_ID = "PLAYLEVEL17";
    private static final String PLAY_LEVEL_18_BUTTON_ID = "PLAYLEVEL18";
    private static final String PLAY_LEVEL_19_BUTTON_ID = "PLAYLEVEL19";
    private static final String PLAY_LEVEL_20_BUTTON_ID = "PLAYLEVEL20";
    private static final String PLAY_LEVEL_21_BUTTON_ID = "PLAYLEVEL21";
    private static final String PLAY_LEVEL_22_BUTTON_ID = "PLAYLEVEL22";
    private static final String PLAY_LEVEL_23_BUTTON_ID = "PLAYLEVEL23";
    private static final String PLAY_LEVEL_24_BUTTON_ID = "PLAYLEVEL24";
    private static final String PLAY_LEVEL_25_BUTTON_ID = "PLAYLEVEL25";

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

        uiCommandBuilder.set("#RATING1.TextSpans", Message.raw(roundData.getFinalRatingStarsLevel1() + "/3"));
        uiCommandBuilder.set("#RATING2.TextSpans", Message.raw(roundData.getFinalRatingStarsLevel2() + "/3"));
        uiCommandBuilder.set("#RATING3.TextSpans", Message.raw(roundData.getFinalRatingStarsLevel3() + "/3"));
        uiCommandBuilder.set("#RATING4.TextSpans", Message.raw(roundData.getFinalRatingStarsLevel4() + "/3"));
        uiCommandBuilder.set("#RATING5.TextSpans", Message.raw(roundData.getFinalRatingStarsLevel5() + "/3"));
        uiCommandBuilder.set("#RATING6.TextSpans", Message.raw(roundData.getFinalRatingStarsLevel6() + "/3"));
        uiCommandBuilder.set("#RATING7.TextSpans", Message.raw(roundData.getFinalRatingStarsLevel7() + "/3"));
        uiCommandBuilder.set("#RATING8.TextSpans", Message.raw(roundData.getFinalRatingStarsLevel8() + "/3"));
        uiCommandBuilder.set("#RATING9.TextSpans", Message.raw(roundData.getFinalRatingStarsLevel9() + "/3"));
        uiCommandBuilder.set("#RATING10.TextSpans", Message.raw(roundData.getFinalRatingStarsLevel10() + "/3"));
        uiCommandBuilder.set("#RATING11.TextSpans", Message.raw(roundData.getFinalRatingStarsLevel11() + "/3"));
        uiCommandBuilder.set("#RATING12.TextSpans", Message.raw(roundData.getFinalRatingStarsLevel12() + "/3"));
        uiCommandBuilder.set("#RATING13.TextSpans", Message.raw(roundData.getFinalRatingStarsLevel13() + "/3"));
        uiCommandBuilder.set("#RATING14.TextSpans", Message.raw(roundData.getFinalRatingStarsLevel14() + "/3"));
        uiCommandBuilder.set("#RATING15.TextSpans", Message.raw(roundData.getFinalRatingStarsLevel15() + "/3"));
        uiCommandBuilder.set("#RATING16.TextSpans", Message.raw(roundData.getFinalRatingStarsLevel16() + "/3"));
        uiCommandBuilder.set("#RATING17.TextSpans", Message.raw(roundData.getFinalRatingStarsLevel17() + "/3"));
        uiCommandBuilder.set("#RATING18.TextSpans", Message.raw(roundData.getFinalRatingStarsLevel18() + "/3"));
        uiCommandBuilder.set("#RATING19.TextSpans", Message.raw(roundData.getFinalRatingStarsLevel19() + "/3"));
        uiCommandBuilder.set("#RATING20.TextSpans", Message.raw(roundData.getFinalRatingStarsLevel20() + "/3"));
        uiCommandBuilder.set("#RATING21.TextSpans", Message.raw(roundData.getFinalRatingStarsLevel21() + "/3"));
        uiCommandBuilder.set("#RATING22.TextSpans", Message.raw(roundData.getFinalRatingStarsLevel22() + "/3"));
        uiCommandBuilder.set("#RATING23.TextSpans", Message.raw(roundData.getFinalRatingStarsLevel23() + "/3"));
        uiCommandBuilder.set("#RATING24.TextSpans", Message.raw(roundData.getFinalRatingStarsLevel24() + "/3"));
        uiCommandBuilder.set("#RATING25.TextSpans", Message.raw(roundData.getFinalRatingStarsLevel25() + "/3"));

        if (roundData.getUnlockedLevels() >= 2) {
            uiCommandBuilder.set("#PLAYLEVEL2.TextSpans", Message.raw("LEVEL 2"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL2", EventData.of("OnButtonClicked", PLAY_LEVEL_TWO_BUTTON_ID), false);
        }
        if (roundData.getUnlockedLevels() >= 3) {
            uiCommandBuilder.set("#PLAYLEVEL3.TextSpans", Message.raw("LEVEL 3"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL3", EventData.of("OnButtonClicked", PLAY_LEVEL_THREE_BUTTON_ID), false);
        }
        if (roundData.getUnlockedLevels() >= 4) {
            uiCommandBuilder.set("#PLAYLEVEL4.TextSpans", Message.raw("LEVEL 4"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL4", EventData.of("OnButtonClicked", PLAY_LEVEL_FOUR_BUTTON_ID), false);
        }
        if (roundData.getUnlockedLevels() >= 5) {
            uiCommandBuilder.set("#PLAYLEVEL5.TextSpans", Message.raw("LEVEL 5"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL5", EventData.of("OnButtonClicked", PLAY_LEVEL_FIVE_BUTTON_ID), false);
        }
        if (roundData.getUnlockedLevels() >= 6) {
            uiCommandBuilder.set("#PLAYLEVEL6.TextSpans", Message.raw("LEVEL 6"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL6", EventData.of("OnButtonClicked", PLAY_LEVEL_SIX_BUTTON_ID), false);
        }
        if (roundData.getUnlockedLevels() >= 7) {
            uiCommandBuilder.set("#PLAYLEVEL7.TextSpans", Message.raw("LEVEL 7"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL7", EventData.of("OnButtonClicked", PLAY_LEVEL_SEVEN_BUTTON_ID), false);
        }
        if (roundData.getUnlockedLevels() >= 8) {
            uiCommandBuilder.set("#PLAYLEVEL8.TextSpans", Message.raw("LEVEL 8"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL8", EventData.of("OnButtonClicked", PLAY_LEVEL_EIGHT_BUTTON_ID), false);
        }
        if (roundData.getUnlockedLevels() >= 9) {
            uiCommandBuilder.set("#PLAYLEVEL9.TextSpans", Message.raw("LEVEL 9"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL9", EventData.of("OnButtonClicked", PLAY_LEVEL_NINE_BUTTON_ID), false);
        }
        if (roundData.getUnlockedLevels() >= 10) {
            uiCommandBuilder.set("#PLAYLEVEL10.TextSpans", Message.raw("LEVEL 10"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL10", EventData.of("OnButtonClicked", PLAY_LEVEL_TEN_BUTTON_ID), false);
        }
        if (roundData.getUnlockedLevels() >= 11) {
            uiCommandBuilder.set("#PLAYLEVEL11.TextSpans", Message.raw("LEVEL 11"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL11", EventData.of("OnButtonClicked", PLAY_LEVEL_ELEVEN_BUTTON_ID), false);
        }
        if (roundData.getUnlockedLevels() >= 12) {
            uiCommandBuilder.set("#PLAYLEVEL12.TextSpans", Message.raw("LEVEL 12"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL12", EventData.of("OnButtonClicked", PLAY_LEVEL_TWELVE_BUTTON_ID), false);
        }
        if (roundData.getUnlockedLevels() >= 13) {
            uiCommandBuilder.set("#PLAYLEVEL13.TextSpans", Message.raw("LEVEL 13"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL13", EventData.of("OnButtonClicked", PLAY_LEVEL_THIRTEEN_BUTTON_ID), false);
        }
        if (roundData.getUnlockedLevels() >= 14) {
            uiCommandBuilder.set("#PLAYLEVEL14.TextSpans", Message.raw("LEVEL 14"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL14", EventData.of("OnButtonClicked", PLAY_LEVEL_FOURTEEN_BUTTON_ID), false);
        }
        if (roundData.getUnlockedLevels() >= 15) {
            uiCommandBuilder.set("#PLAYLEVEL15.TextSpans", Message.raw("LEVEL 15"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL15", EventData.of("OnButtonClicked", PLAY_LEVEL_FIFTEEN_BUTTON_ID), false);
        }
        if (roundData.getUnlockedLevels() >= 16) {
            uiCommandBuilder.set("#PLAYLEVEL16.TextSpans", Message.raw("LEVEL 16"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL16", EventData.of("OnButtonClicked", PLAY_LEVEL_16_BUTTON_ID), false);
        }
        if (roundData.getUnlockedLevels() >= 17) {
            uiCommandBuilder.set("#PLAYLEVEL17.TextSpans", Message.raw("LEVEL 17"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL17", EventData.of("OnButtonClicked", PLAY_LEVEL_17_BUTTON_ID), false);
        }
        if (roundData.getUnlockedLevels() >= 18) {
            uiCommandBuilder.set("#PLAYLEVEL18.TextSpans", Message.raw("LEVEL 18"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL18", EventData.of("OnButtonClicked", PLAY_LEVEL_18_BUTTON_ID), false);
        }
        if (roundData.getUnlockedLevels() >= 19) {
            uiCommandBuilder.set("#PLAYLEVEL19.TextSpans", Message.raw("LEVEL 19"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL19", EventData.of("OnButtonClicked", PLAY_LEVEL_19_BUTTON_ID), false);
        }
        if (roundData.getUnlockedLevels() >= 20) {
            uiCommandBuilder.set("#PLAYLEVEL20.TextSpans", Message.raw("LEVEL 20"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL20", EventData.of("OnButtonClicked", PLAY_LEVEL_20_BUTTON_ID), false);
        }
        if (roundData.getUnlockedLevels() >= 21) {
            uiCommandBuilder.set("#PLAYLEVEL21.TextSpans", Message.raw("LEVEL 21"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL21", EventData.of("OnButtonClicked", PLAY_LEVEL_21_BUTTON_ID), false);
        }
        if (roundData.getUnlockedLevels() >= 22) {
            uiCommandBuilder.set("#PLAYLEVEL22.TextSpans", Message.raw("LEVEL 22"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL22", EventData.of("OnButtonClicked", PLAY_LEVEL_22_BUTTON_ID), false);
        }
        if (roundData.getUnlockedLevels() >= 23) {
            uiCommandBuilder.set("#PLAYLEVEL23.TextSpans", Message.raw("LEVEL 23"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL23", EventData.of("OnButtonClicked", PLAY_LEVEL_23_BUTTON_ID), false);
        }
        if (roundData.getUnlockedLevels() >= 24) {
            uiCommandBuilder.set("#PLAYLEVEL24.TextSpans", Message.raw("LEVEL 24"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL24", EventData.of("OnButtonClicked", PLAY_LEVEL_24_BUTTON_ID), false);
        }
        if (roundData.getUnlockedLevels() >= 25) {
            uiCommandBuilder.set("#PLAYLEVEL25.TextSpans", Message.raw("LEVEL 25"));
            uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYLEVEL25", EventData.of("OnButtonClicked", PLAY_LEVEL_25_BUTTON_ID), false);
        }
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull MenuWithButtonsData data) {
        super.handleDataEvent(ref, store, data);

        Player player = Objects.requireNonNull(store.getComponent(ref, Player.getComponentType()));
        RoundComponent roundData = Objects.requireNonNull(store.getComponent(ref, RoundComponent.getComponentType()));

        if (PLAY_LEVEL_ONE_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundStart(1, 5);
        }
        else if (PLAY_LEVEL_TWO_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundStart(2, 6);
        }
        else if (PLAY_LEVEL_THREE_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundStart(3, 8);
        }
        else if (PLAY_LEVEL_FOUR_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundStart(4, 5);
        }
        else if (PLAY_LEVEL_FIVE_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundStart(5, 10);
        }
        else if (PLAY_LEVEL_SIX_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundStart(6, 3);
        }
        else if (PLAY_LEVEL_SEVEN_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundStart(7, 10);
        }
        else if (PLAY_LEVEL_EIGHT_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundStart(8, 3);
        }
        else if (PLAY_LEVEL_NINE_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundStart(9, 7);
        }
        else if (PLAY_LEVEL_TEN_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundStart(10, 12);
        }
        else if (PLAY_LEVEL_ELEVEN_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundStart(11, 8);
        }
        else if (PLAY_LEVEL_TWELVE_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundStart(12, 8);
        }
        else if (PLAY_LEVEL_THIRTEEN_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundStart(13, 8);
        }
        else if (PLAY_LEVEL_FOURTEEN_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundStart(14, 8);
        }
        else if (PLAY_LEVEL_FIFTEEN_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundStart(15, 8);
        }
        else if (PLAY_LEVEL_16_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundStart(16, 8);
        }
        else if (PLAY_LEVEL_17_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundStart(17, 8);
        }
        else if (PLAY_LEVEL_18_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundStart(18, 8);
        }
        else if (PLAY_LEVEL_19_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundStart(19, 8);
        }
        else if (PLAY_LEVEL_20_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundStart(20, 8);
        }
        else if (PLAY_LEVEL_21_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundStart(21, 8);
        }
        else if (PLAY_LEVEL_22_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundStart(22, 8);
        }
        else if (PLAY_LEVEL_23_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundStart(23, 8);
        }
        else if (PLAY_LEVEL_24_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundStart(24, 8);
        }
        else if (PLAY_LEVEL_25_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundStart(25, 12);
        }
        else if (BACK_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundData.setRoundType("menu_start");
        }
    }

    void roundStart(int level, int enemiesToKill) {
        roundData.setRoundType("menu_class");
        roundData.setRoundTimer(70);
        roundData.setLevel(level);
        roundData.setEnemiesToKill(enemiesToKill);
        roundData.freezeRoundTimer(true);
    }

    @Override
    public void onDismiss(@NotNull Ref<EntityStore> ref, @NotNull Store<EntityStore> store) {
        super.onDismiss(ref, store);
        roundData.setRoundType("menu_start");
    }
}
