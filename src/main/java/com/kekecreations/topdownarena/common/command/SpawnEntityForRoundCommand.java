package com.kekecreations.topdownarena.common.command;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.math.vector.Vector3f;
import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractTargetPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.npc.NPCPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SpawnEntityForRoundCommand extends AbstractTargetPlayerCommand {

    private final RequiredArg<String> npcId;
    private final RequiredArg<String> addOrSubtract;
    private final RequiredArg<Double> x;
    private final RequiredArg<Double> y;
    private final RequiredArg<Double> z;

    public SpawnEntityForRoundCommand() {
        super("round_npc", "Spawning NPC Command for Topdown Arena!");
        this.npcId = this.withRequiredArg("npcId", "Entity ID", ArgTypes.STRING);
        this.addOrSubtract = this.withRequiredArg("addOrSubtract", "Add or subtract coordinates from player coordinates", ArgTypes.STRING);
        this.x = this.withRequiredArg("x", "x", ArgTypes.DOUBLE);
        this.y = this.withRequiredArg("y", "y", ArgTypes.DOUBLE);
        this.z = this.withRequiredArg("z", "z", ArgTypes.DOUBLE);
        this.setPermissionGroup(GameMode.Adventure);
    }

    @Override
    protected void execute(@NotNull CommandContext commandContext, @Nullable Ref<EntityStore> ref, @NotNull Ref<EntityStore> ref1, @NotNull PlayerRef playerRef, @NotNull World world, @NotNull Store<EntityStore> store) {
        Player player = store.getComponent(ref, Player.getComponentType());
        if (player != null) {
            TransformComponent transformComponent = store.getComponent(ref, TransformComponent.getComponentType());
            Vector3d newVector;
            if (transformComponent != null) {
                if (Objects.equals(this.addOrSubtract.get(commandContext), "Add")) {
                    newVector = transformComponent.getPosition().add(this.x.get(commandContext), this.y.get(commandContext), this.z.get(commandContext));
                } else {
                    newVector = transformComponent.getPosition().subtract(this.x.get(commandContext), this.y.get(commandContext), this.z.get(commandContext));
                }
                NPCPlugin.get().spawnNPC(store, this.npcId.get(commandContext), null,
                        newVector,
                        new Vector3f()
                );
            }
        }
    }
}