package com.kekecreations.topdownarena.core.registry;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.kekecreations.topdownarena.common.command.DeleteItemsCommand;
import com.kekecreations.topdownarena.common.command.ResetPosCommand;
import com.kekecreations.topdownarena.common.command.SpawnEntityForRoundCommand;
import com.kekecreations.topdownarena.common.command.SpawnEntitySandboxCommand;

public class CommandRegistry {

    public static void registerCommands(JavaPlugin javaPlugin) {
        var registry = javaPlugin.getCommandRegistry();
        registry.registerCommand(new SpawnEntityForRoundCommand());
        registry.registerCommand(new ResetPosCommand());
        registry.registerCommand(new DeleteItemsCommand());
        registry.registerCommand(new SpawnEntitySandboxCommand());
    }
}
