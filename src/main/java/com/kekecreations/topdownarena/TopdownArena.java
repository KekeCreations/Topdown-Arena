package com.kekecreations.topdownarena;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.kekecreations.topdownarena.core.registry.CommandRegistry;
import com.kekecreations.topdownarena.core.registry.ComponentAndSystemRegistry;
import com.kekecreations.topdownarena.core.registry.EventRegistry;

public class TopdownArena extends JavaPlugin {
    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();



    public TopdownArena(JavaPluginInit init) {
        super(init);
        LOGGER.atInfo().log("Hello from %s version %s", this.getName(), this.getManifest().getVersion().toString());
    }

    @Override
    protected void setup() {
        CommandRegistry.registerCommands(this);
        EventRegistry.registerEvents(this);
        ComponentAndSystemRegistry.registerComponents(this);
    }
}
