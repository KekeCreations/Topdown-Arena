package com.kekecreations.topdownarena.core.registry;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.kekecreations.topdownarena.common.command.SpawnEntityForRoundCommand;

public class CommandRegistry {

    public static void registerCommands(JavaPlugin javaPlugin) {
        var registry = javaPlugin.getCommandRegistry();
        registry.registerCommand(new SpawnEntityForRoundCommand());
    }
}
