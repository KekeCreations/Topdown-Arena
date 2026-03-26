package com.kekecreations.topdownarena.common.ui;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.player.hud.CustomUIHud;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.kekecreations.topdownarena.common.component.RoundComponent;
import org.jspecify.annotations.NonNull;

public class RoundStatsHud extends CustomUIHud {

    RoundComponent roundData;

    public RoundStatsHud(@NonNull PlayerRef playerRef, RoundComponent roundData) {
        super(playerRef);
        this.roundData = roundData;
    }

    @Override
    protected void build(@NonNull UICommandBuilder uiCommandBuilder) {
        uiCommandBuilder.append("Hud/round_stats.ui");

        //Show and hide UI
        if (!roundData.isTimerFrozen()) {
            uiCommandBuilder.set("#KILLCOUNT.TextSpans", Message.raw("Enemies Left:" + roundData.getEnemiesLeftToKill()));
            uiCommandBuilder.set("#BONUSKILLCOUNT.TextSpans", Message.raw("Bonus Kills:" + roundData.getBonusEnemiesKilled()));
            uiCommandBuilder.set("#ROUNDTIMER.TextSpans", Message.raw(String.valueOf(roundData.getRoundTimer())));
            if (roundData.getRoundTimer() <= 24 && roundData.getRoundTimer() >= 20) {
                uiCommandBuilder.set("#WAVE.TextSpans", Message.raw("INCOMING WAVE!"));
            } else if (roundData.getRoundTimer() <= 70 && roundData.getRoundTimer() >= 60) {
                uiCommandBuilder.set("#WAVE.TextSpans", Message.raw("PREPARE!"));
            } else {
                uiCommandBuilder.set("#WAVE.TextSpans", Message.raw(""));
            }
        } else {
            uiCommandBuilder.set("#KILLCOUNT.TextSpans", Message.raw(""));
            uiCommandBuilder.set("#ROUNDTIMER.TextSpans", Message.raw(""));
            uiCommandBuilder.set("#BONUSKILLCOUNT.TextSpans", Message.raw(""));
            uiCommandBuilder.set("#WAVE.TextSpans", Message.raw(""));
        }
    }
}

