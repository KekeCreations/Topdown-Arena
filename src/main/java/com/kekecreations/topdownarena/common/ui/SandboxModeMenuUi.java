package com.kekecreations.topdownarena.common.ui;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.protocol.packets.interface_.Page;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.inventory.InventoryComponent;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.hypixel.hytale.server.core.modules.entitystats.EntityStatMap;
import com.hypixel.hytale.server.core.modules.entitystats.asset.DefaultEntityStatTypes;
import com.hypixel.hytale.server.core.ui.builder.EventData;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.topdownarena.common.component.RoundComponent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Objects;

public class SandboxModeMenuUi extends InteractiveCustomUIPage<SandboxModeMenuUi.Data> {

    private static final String CYCLE_1_BUTTON_ID = "CYCLE1";
    private static final String CYCLE_2_BUTTON_ID = "CYCLE2";
    private static final String CYCLE_3_BUTTON_ID = "CYCLE3";
    private static final String CYCLE_CLASS_BUTTON_ID = "CYCLECLASS";
    private static final String CYCLE_CLASS_LEVEL_BUTTON_ID = "CYCLECLASSLEVEL";
    private static final String BACK_BUTTON_ID = "BACKBUTTON";
    private static final String PLAY_BUTTON_ID = "PLAYBUTTON";
    private static final String WAVE_BUTTON_ID = "WAVEBUTTON";
    RoundComponent roundData;

    public SandboxModeMenuUi(@Nonnull PlayerRef playerRef, RoundComponent roundData, @Nonnull CustomPageLifetime lifetime) {
        super(playerRef, lifetime, SandboxModeMenuUi.Data.CODEC);
        this.roundData = roundData;
    }

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("Pages/sandbox_mode.ui");

        if (roundData.getSandboxRandomWaves()) {
            uiCommandBuilder.set("#WAVEBUTTON.TextSpans", Message.raw("RANDOM WAVES: ON"));
        } else {
            uiCommandBuilder.set("#WAVEBUTTON.TextSpans", Message.raw("RANDOM WAVES: OFF"));
        }

        switch (roundData.getSandboxEnemyChoice()) {
            case 0 -> {
                uiCommandBuilder.set("#ENEMYTITLE1.TextSpans", Message.raw("SKELETON"));
                uiCommandBuilder.set("#ENEMYIMAGE1.AssetPath", "UI/Custom/Pages/Memories/npcs/Skeleton_Fighter.png");
            }
            case 1 -> {
                uiCommandBuilder.set("#ENEMYTITLE1.TextSpans", Message.raw("SKELETON ARCHER"));
                uiCommandBuilder.set("#ENEMYIMAGE1.AssetPath", "UI/Custom/Pages/Memories/npcs/Skeleton_Archer.png");
            }
            case 2 -> {
                uiCommandBuilder.set("#ENEMYTITLE1.TextSpans", Message.raw("SKELETON ARCHMAGE"));
                uiCommandBuilder.set("#ENEMYIMAGE1.AssetPath", "UI/Custom/Pages/Memories/npcs/Skeleton_Archmage.png");
            }
            case 3 -> {
                uiCommandBuilder.set("#ENEMYTITLE1.TextSpans", Message.raw("SKELETON ALCHEMIST"));
                uiCommandBuilder.set("#ENEMYIMAGE1.AssetPath", "UI/Custom/Pages/Memories/npcs/Skeleton_Burnt_Alchemist.png");
            }
            case 4 -> {
                uiCommandBuilder.set("#ENEMYTITLE1.TextSpans", Message.raw("SKELETON BURNT GUNNER"));
                uiCommandBuilder.set("#ENEMYIMAGE1.AssetPath", "UI/Custom/Pages/Memories/npcs/Skeleton_Burnt_Gunner.png");
            }
            case 5 -> {
                uiCommandBuilder.set("#ENEMYTITLE1.TextSpans", Message.raw("ZOMBIE"));
                uiCommandBuilder.set("#ENEMYIMAGE1.AssetPath", "UI/Custom/Pages/Memories/npcs/Zombie.png");
            }
            case 6 -> {
                uiCommandBuilder.set("#ENEMYTITLE1.TextSpans", Message.raw("ZOMBIE ABERRANT"));
                uiCommandBuilder.set("#ENEMYIMAGE1.AssetPath", "UI/Custom/Pages/Memories/npcs/Zombie_Aberrant.png");
            }
            case 7 -> {
                uiCommandBuilder.set("#ENEMYTITLE1.TextSpans", Message.raw("WHITE WOLF"));
                uiCommandBuilder.set("#ENEMYIMAGE1.AssetPath", "UI/Custom/Pages/Memories/npcs/Wolf_White.png");
            }
            case 8 -> {
                uiCommandBuilder.set("#ENEMYTITLE1.TextSpans", Message.raw("BLACK WOLF"));
                uiCommandBuilder.set("#ENEMYIMAGE1.AssetPath", "UI/Custom/Pages/Memories/npcs/Wolf_Black.png");
            }
            case 9 -> {
                uiCommandBuilder.set("#ENEMYTITLE1.TextSpans", Message.raw("WEREWOLF"));
                uiCommandBuilder.set("#ENEMYIMAGE1.AssetPath", "UI/Custom/Pages/Memories/npcs/Werewolf.png");
            }
            case 10 -> {
                uiCommandBuilder.set("#ENEMYTITLE1.TextSpans", Message.raw("YETI"));
                uiCommandBuilder.set("#ENEMYIMAGE1.AssetPath", "UI/Custom/Pages/Memories/npcs/Yeti.png");
            }
            case 11 -> {
                uiCommandBuilder.set("#ENEMYTITLE1.TextSpans", Message.raw("SPIDER"));
                uiCommandBuilder.set("#ENEMYIMAGE1.AssetPath", "UI/Custom/Pages/Memories/npcs/Spider.png");
            }
            case 12 -> {
                uiCommandBuilder.set("#ENEMYTITLE1.TextSpans", Message.raw("CAVE SPIDER"));
                uiCommandBuilder.set("#ENEMYIMAGE1.AssetPath", "UI/Custom/Pages/Memories/npcs/Spider_Cave.png");
            }
        }
        switch (roundData.getSandboxEnemyChoice2()) {
            case 0 -> {
                uiCommandBuilder.set("#ENEMYTITLE2.TextSpans", Message.raw("SKELETON"));
                uiCommandBuilder.set("#ENEMYIMAGE2.AssetPath", "UI/Custom/Pages/Memories/npcs/Skeleton_Fighter.png");
            }
            case 1 -> {
                uiCommandBuilder.set("#ENEMYTITLE2.TextSpans", Message.raw("SKELETON ARCHER"));
                uiCommandBuilder.set("#ENEMYIMAGE2.AssetPath", "UI/Custom/Pages/Memories/npcs/Skeleton_Archer.png");
            }
            case 2 -> {
                uiCommandBuilder.set("#ENEMYTITLE2.TextSpans", Message.raw("SKELETON ARCHMAGE"));
                uiCommandBuilder.set("#ENEMYIMAGE2.AssetPath", "UI/Custom/Pages/Memories/npcs/Skeleton_Archmage.png");
            }
            case 3 -> {
                uiCommandBuilder.set("#ENEMYTITLE2.TextSpans", Message.raw("SKELETON ALCHEMIST"));
                uiCommandBuilder.set("#ENEMYIMAGE2.AssetPath", "UI/Custom/Pages/Memories/npcs/Skeleton_Burnt_Alchemist.png");
            }
            case 4 -> {
                uiCommandBuilder.set("#ENEMYTITLE2.TextSpans", Message.raw("SKELETON BURNT GUNNER"));
                uiCommandBuilder.set("#ENEMYIMAGE2.AssetPath", "UI/Custom/Pages/Memories/npcs/Skeleton_Burnt_Gunner.png");
            }
            case 5 -> {
                uiCommandBuilder.set("#ENEMYTITLE2.TextSpans", Message.raw("ZOMBIE"));
                uiCommandBuilder.set("#ENEMYIMAGE2.AssetPath", "UI/Custom/Pages/Memories/npcs/Zombie.png");
            }
            case 6 -> {
                uiCommandBuilder.set("#ENEMYTITLE2.TextSpans", Message.raw("ZOMBIE ABERRANT"));
                uiCommandBuilder.set("#ENEMYIMAGE2.AssetPath", "UI/Custom/Pages/Memories/npcs/Zombie_Aberrant.png");
            }
            case 7 -> {
                uiCommandBuilder.set("#ENEMYTITLE2.TextSpans", Message.raw("WHITE WOLF"));
                uiCommandBuilder.set("#ENEMYIMAGE2.AssetPath", "UI/Custom/Pages/Memories/npcs/Wolf_White.png");
            }
            case 8 -> {
                uiCommandBuilder.set("#ENEMYTITLE2.TextSpans", Message.raw("BLACK WOLF"));
                uiCommandBuilder.set("#ENEMYIMAGE2.AssetPath", "UI/Custom/Pages/Memories/npcs/Wolf_Black.png");
            }
            case 9 -> {
                uiCommandBuilder.set("#ENEMYTITLE2.TextSpans", Message.raw("WEREWOLF"));
                uiCommandBuilder.set("#ENEMYIMAGE2.AssetPath", "UI/Custom/Pages/Memories/npcs/Werewolf.png");
            }
            case 10 -> {
                uiCommandBuilder.set("#ENEMYTITLE2.TextSpans", Message.raw("YETI"));
                uiCommandBuilder.set("#ENEMYIMAGE2.AssetPath", "UI/Custom/Pages/Memories/npcs/Yeti.png");
            }
            case 11 -> {
                uiCommandBuilder.set("#ENEMYTITLE2.TextSpans", Message.raw("SPIDER"));
                uiCommandBuilder.set("#ENEMYIMAGE2.AssetPath", "UI/Custom/Pages/Memories/npcs/Spider.png");
            }
            case 12 -> {
                uiCommandBuilder.set("#ENEMYTITLE2.TextSpans", Message.raw("CAVE SPIDER"));
                uiCommandBuilder.set("#ENEMYIMAGE2.AssetPath", "UI/Custom/Pages/Memories/npcs/Spider_Cave.png");
            }
        }
        switch (roundData.getSandboxEnemyChoice3()) {
            case 0 -> {
                uiCommandBuilder.set("#ENEMYTITLE3.TextSpans", Message.raw("SKELETON"));
                uiCommandBuilder.set("#ENEMYIMAGE3.AssetPath", "UI/Custom/Pages/Memories/npcs/Skeleton_Fighter.png");
            }
            case 1 -> {
                uiCommandBuilder.set("#ENEMYTITLE3.TextSpans", Message.raw("SKELETON ARCHER"));
                uiCommandBuilder.set("#ENEMYIMAGE3.AssetPath", "UI/Custom/Pages/Memories/npcs/Skeleton_Archer.png");
            }
            case 2 -> {
                uiCommandBuilder.set("#ENEMYTITLE3.TextSpans", Message.raw("SKELETON ARCHMAGE"));
                uiCommandBuilder.set("#ENEMYIMAGE3.AssetPath", "UI/Custom/Pages/Memories/npcs/Skeleton_Archmage.png");
            }
            case 3 -> {
                uiCommandBuilder.set("#ENEMYTITLE3.TextSpans", Message.raw("SKELETON ALCHEMIST"));
                uiCommandBuilder.set("#ENEMYIMAGE3.AssetPath", "UI/Custom/Pages/Memories/npcs/Skeleton_Burnt_Alchemist.png");
            }
            case 4 -> {
                uiCommandBuilder.set("#ENEMYTITLE3.TextSpans", Message.raw("SKELETON BURNT GUNNER"));
                uiCommandBuilder.set("#ENEMYIMAGE3.AssetPath", "UI/Custom/Pages/Memories/npcs/Skeleton_Burnt_Gunner.png");
            }
            case 5 -> {
                uiCommandBuilder.set("#ENEMYTITLE3.TextSpans", Message.raw("ZOMBIE"));
                uiCommandBuilder.set("#ENEMYIMAGE3.AssetPath", "UI/Custom/Pages/Memories/npcs/Zombie.png");
            }
            case 6 -> {
                uiCommandBuilder.set("#ENEMYTITLE3.TextSpans", Message.raw("ZOMBIE ABERRANT"));
                uiCommandBuilder.set("#ENEMYIMAGE3.AssetPath", "UI/Custom/Pages/Memories/npcs/Zombie_Aberrant.png");
            }
            case 7 -> {
                uiCommandBuilder.set("#ENEMYTITLE3.TextSpans", Message.raw("WHITE WOLF"));
                uiCommandBuilder.set("#ENEMYIMAGE3.AssetPath", "UI/Custom/Pages/Memories/npcs/Wolf_White.png");
            }
            case 8 -> {
                uiCommandBuilder.set("#ENEMYTITLE3.TextSpans", Message.raw("BLACK WOLF"));
                uiCommandBuilder.set("#ENEMYIMAGE3.AssetPath", "UI/Custom/Pages/Memories/npcs/Wolf_Black.png");
            }
            case 9 -> {
                uiCommandBuilder.set("#ENEMYTITLE3.TextSpans", Message.raw("WEREWOLF"));
                uiCommandBuilder.set("#ENEMYIMAGE3.AssetPath", "UI/Custom/Pages/Memories/npcs/Werewolf.png");
            }
            case 10 -> {
                uiCommandBuilder.set("#ENEMYTITLE3.TextSpans", Message.raw("YETI"));
                uiCommandBuilder.set("#ENEMYIMAGE3.AssetPath", "UI/Custom/Pages/Memories/npcs/Yeti.png");
            }
            case 11 -> {
                uiCommandBuilder.set("#ENEMYTITLE3.TextSpans", Message.raw("SPIDER"));
                uiCommandBuilder.set("#ENEMYIMAGE3.AssetPath", "UI/Custom/Pages/Memories/npcs/Spider.png");
            }
            case 12 -> {
                uiCommandBuilder.set("#ENEMYTITLE3.TextSpans", Message.raw("CAVE SPIDER"));
                uiCommandBuilder.set("#ENEMYIMAGE3.AssetPath", "UI/Custom/Pages/Memories/npcs/Spider_Cave.png");
            }
        }
        switch (roundData.getSandboxClassChoice()) {
            case 0 -> {
                uiCommandBuilder.set("#CLASSTITLE.TextSpans", Message.raw("KNIGHT"));
                uiCommandBuilder.set("#DESC.TextSpans", Message.raw("CHARGE INTO BATTLE"));
                uiCommandBuilder.set("#CLASSIMAGE1.AssetPath", "Icons/ItemsGenerated/Weapon_Sword_Iron.png");
            }
            case 1 -> {
                uiCommandBuilder.set("#CLASSTITLE.TextSpans", Message.raw("ARCHER"));
                uiCommandBuilder.set("#DESC.TextSpans", Message.raw("AIM CAREFULLY"));
                uiCommandBuilder.set("#CLASSIMAGE1.AssetPath", "Icons/ItemsGenerated/Weapon_Bomb_Fire_Goblin.png");
            }
            case 2 -> {
                uiCommandBuilder.set("#CLASSTITLE.TextSpans", Message.raw("WARRIOR"));
                uiCommandBuilder.set("#DESC.TextSpans", Message.raw("SPEAR THOSE WHO DARE"));
                uiCommandBuilder.set("#CLASSIMAGE1.AssetPath", "Icons/ItemsGenerated/Weapon_Mace_Copper.png");
            }
            case 3 -> {
                uiCommandBuilder.set("#CLASSTITLE.TextSpans", Message.raw("SHIFTER"));
                uiCommandBuilder.set("#DESC.TextSpans", Message.raw("SHROUD THE TRUTH"));
                uiCommandBuilder.set("#CLASSIMAGE1.AssetPath", "Icons/ItemsGenerated/Weapon_Spellbook_Fire.png");
            }
        }
        switch (roundData.getSandboxClassLevelChoice()) {
            case 0 -> {
                uiCommandBuilder.set("#CLASSLEVELTITLE.TextSpans", Message.raw("LEVEL 1"));
                uiCommandBuilder.set("#DESCLEVEL.TextSpans", Message.raw("COPPER EQUIPMENT"));
                uiCommandBuilder.set("#CLASSLEVELIMAGE.AssetPath", "Icons/ItemsGenerated/Armor_Copper_Chest.png");
            }
            case 1 -> {
                uiCommandBuilder.set("#CLASSLEVELTITLE.TextSpans", Message.raw("LEVEL 2"));
                uiCommandBuilder.set("#DESCLEVEL.TextSpans", Message.raw("IRON EQUIPMENT"));
                uiCommandBuilder.set("#CLASSLEVELIMAGE.AssetPath", "Icons/ItemsGenerated/Armor_Iron_Chest.png");
            }
            case 2 -> {
                uiCommandBuilder.set("#CLASSLEVELTITLE.TextSpans", Message.raw("LEVEL 3"));
                uiCommandBuilder.set("#DESCLEVEL.TextSpans", Message.raw("COPPER EQUIPMENT"));
                uiCommandBuilder.set("#CLASSLEVELIMAGE.AssetPath", "Icons/ItemsGenerated/Armor_Copper_Chest.png");
            }
            case 3 -> {
                uiCommandBuilder.set("#CLASSLEVELTITLE.TextSpans", Message.raw("LEVEL 4"));
                uiCommandBuilder.set("#DESCLEVEL.TextSpans", Message.raw("COPPER EQUIPMENT"));
                uiCommandBuilder.set("#CLASSLEVELIMAGE.AssetPath", "Icons/ItemsGenerated/Armor_Copper_Chest.png");
            }
            case 4 -> {
                uiCommandBuilder.set("#CLASSLEVELTITLE.TextSpans", Message.raw("LEVEL 5"));
                uiCommandBuilder.set("#DESCLEVEL.TextSpans", Message.raw("COPPER EQUIPMENT"));
                uiCommandBuilder.set("#CLASSLEVELIMAGE.AssetPath", "Icons/ItemsGenerated/Armor_Copper_Chest.png");
            }
        }
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#CYCLE1", EventData.of("OnButtonClicked", CYCLE_1_BUTTON_ID), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#CYCLE2", EventData.of("OnButtonClicked", CYCLE_2_BUTTON_ID), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#CYCLE3", EventData.of("OnButtonClicked", CYCLE_3_BUTTON_ID), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#CYCLECLASS", EventData.of("OnButtonClicked", CYCLE_CLASS_BUTTON_ID), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#CYCLECLASSLEVEL", EventData.of("OnButtonClicked", CYCLE_CLASS_LEVEL_BUTTON_ID), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#BACKBUTTON", EventData.of("OnButtonClicked", BACK_BUTTON_ID), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYBUTTON", EventData.of("OnButtonClicked", PLAY_BUTTON_ID), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#WAVEBUTTON", EventData.of("OnButtonClicked", WAVE_BUTTON_ID), false);

        uiEventBuilder.addEventBinding(CustomUIEventBindingType.ValueChanged, "#COUNT1", EventData.of("@COUNT1", "#COUNT1.Value"), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.ValueChanged, "#COUNT2", EventData.of("@COUNT2", "#COUNT2.Value"), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.ValueChanged, "#COUNT3", EventData.of("@COUNT3", "#COUNT3.Value"), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.ValueChanged, "#REQUIREDKILLS", EventData.of("@REQUIREDKILLS", "#REQUIREDKILLS.Value"), false);
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull SandboxModeMenuUi.Data data) {
        super.handleDataEvent(ref, store, data);

        Player player = Objects.requireNonNull(store.getComponent(ref, Player.getComponentType()));
        RoundComponent roundData = Objects.requireNonNull(store.getComponent(ref, RoundComponent.getComponentType()));
        EntityStatMap entityStat = store.getComponent(ref, EntityStatMap.getComponentType());
        int foodChance = (int) (Math.random() * 6);
        int potionChance = (int) (Math.random() * 3);
        boolean changed = false;
        InventoryComponent hotbarComponent = store.getComponent(ref, InventoryComponent.getComponentTypeById(-1));
        InventoryComponent armourComponent = store.getComponent(ref, InventoryComponent.getComponentTypeById(-3));
        InventoryComponent utilityComponent = store.getComponent(ref, InventoryComponent.getComponentTypeById(-5));
        InventoryComponent backpackComponent = store.getComponent(ref, InventoryComponent.getComponentTypeById(-9));
        InventoryComponent storageComponent = store.getComponent(ref, InventoryComponent.getComponentTypeById(-2));
        InventoryComponent toolComponent = store.getComponent(ref, InventoryComponent.getComponentTypeById(-8));

        ItemContainer hotbar = hotbarComponent.getInventory();
        ItemContainer armour = armourComponent.getInventory();
        ItemContainer utility = utilityComponent.getInventory();
        ItemContainer backpack = backpackComponent.getInventory();
        ItemContainer storage = storageComponent.getInventory();
        ItemContainer tool = toolComponent.getInventory();

        if (CYCLE_1_BUTTON_ID.equals(data.buttonClicked)) {
            if (roundData.getSandboxEnemyChoice() >= 12) {
                roundData.setEnemyChoice(0);
            } else {
                roundData.setEnemyChoice(roundData.getSandboxEnemyChoice() + 1);
            }
            player.getPageManager().openCustomPage(ref, store, new SandboxModeMenuUi(playerRef, roundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
        } else if (CYCLE_2_BUTTON_ID.equals(data.buttonClicked)) {
            if (roundData.getSandboxEnemyChoice2() >= 12) {
                roundData.setEnemyChoice2(0);
            } else {
                roundData.setEnemyChoice2(roundData.getSandboxEnemyChoice2() + 1);
            }
            player.getPageManager().openCustomPage(ref, store, new SandboxModeMenuUi(playerRef, roundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
        } else if (CYCLE_3_BUTTON_ID.equals(data.buttonClicked)) {
            if (roundData.getSandboxEnemyChoice3() >= 12) {
                roundData.setEnemyChoice3(0);
            } else {
                roundData.setEnemyChoice3(roundData.getSandboxEnemyChoice3() + 1);
            }
            player.getPageManager().openCustomPage(ref, store, new SandboxModeMenuUi(playerRef, roundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
        } else if (CYCLE_CLASS_BUTTON_ID.equals(data.buttonClicked)) {
            if (roundData.getSandboxClassChoice() >= 3) {
                roundData.setClassChoice(0);
            } else {
                roundData.setClassChoice(roundData.getSandboxClassChoice() + 1);
            }
            player.getPageManager().openCustomPage(ref, store, new SandboxModeMenuUi(playerRef, roundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
        } else if (CYCLE_CLASS_LEVEL_BUTTON_ID.equals(data.buttonClicked)) {
            if (roundData.getSandboxClassLevelChoice() >= 4) {
                roundData.setClassLevelChoice(0);
            } else {
                roundData.setClassLevelChoice(roundData.getSandboxClassLevelChoice() + 1);
            }
            player.getPageManager().openCustomPage(ref, store, new SandboxModeMenuUi(playerRef, roundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
        }
        if (BACK_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundData.setRoundType("menu_start");
            roundData.setLevel(0);
        }
        if (PLAY_BUTTON_ID.equals(data.buttonClicked)) {
            hotbar.clear();
            armour.clear();
            utility.clear();
            backpack.clear();
            storage.clear();
            tool.clear();
            if (entityStat != null) {
                entityStat.resetStatValue(DefaultEntityStatTypes.getHealth());
                entityStat.resetStatValue(DefaultEntityStatTypes.getStamina());
            }
            player.getPageManager().setPage(ref, store, Page.None);
            roundData.setRoundTimer(70);
            roundData.freezeRoundTimer(false);
            roundData.setRoundType("sandbox");
            roundData.setEnemiesToKill(roundData.getSandboxRequiredKills());
            roundData.setSandboxRoundsPlayedStat(roundData.getSandboxRoundsPlayedStat() + 1);
            switch (roundData.getSandboxClassChoice()) {
                case 0 -> {
                    switch (roundData.getSandboxClassLevelChoice()) {
                        case 0 -> {
                            hotbar.setItemStackForSlot((short) 1, new ItemStack("Weapon_Sword_Copper"));
                            hotbar.setItemStackForSlot((short) 2, new ItemStack("Weapon_Battleaxe_Copper"));
                            hotbar.setItemStackForSlot((short) 3, new ItemStack("Potion_Health", 8));
                            armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Copper_Head"));
                            armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Copper_Chest"));
                            armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Copper_Hands"));
                            armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Copper_Legs"));
                            utility.setItemStackForSlot((short) 0, new ItemStack("Weapon_Shield_Copper"));
                        }
                        case 1 -> {
                            hotbar.setItemStackForSlot((short) 1, new ItemStack("Weapon_Sword_Iron"));
                            hotbar.setItemStackForSlot((short) 2, new ItemStack("Weapon_Battleaxe_Iron"));
                            hotbar.setItemStackForSlot((short) 3, new ItemStack("Potion_Health", 8));
                            armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Iron_Head"));
                            armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Iron_Chest"));
                            armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Iron_Hands"));
                            armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Iron_Legs"));
                            utility.setItemStackForSlot((short) 0, new ItemStack("Weapon_Shield_Iron"));
                        }
                    }
                }
                case 1 -> {
                    switch (roundData.getSandboxClassLevelChoice()) {
                        case 0 -> {
                            hotbar.setItemStackForSlot((short) 1, new ItemStack("Weapon_Shortbow_Mithril"));
                            hotbar.setItemStackForSlot((short) 2, new ItemStack("Weapon_Crossbow_Ancient_Steel"));
                            hotbar.setItemStackForSlot((short) 3, new ItemStack("Potion_Health", 8));
                            hotbar.setItemStackForSlot((short) 5, new ItemStack("Weapon_Bomb_Potion_Poison", 3));
                            hotbar.setItemStackForSlot((short) 6, new ItemStack("Weapon_Club_Doomed"));
                            hotbar.setItemStackForSlot((short) 7, new ItemStack("Weapon_Arrow_Crude", 128));
                            armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Copper_Head"));
                            armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Copper_Chest"));
                            armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Copper_Hands"));
                            armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Copper_Legs"));
                        }
                        case 1 -> {
                            hotbar.setItemStackForSlot((short) 1, new ItemStack("Weapon_Shortbow_Mithril"));
                            hotbar.setItemStackForSlot((short) 2, new ItemStack("Weapon_Crossbow_Ancient_Steel"));
                            hotbar.setItemStackForSlot((short) 3, new ItemStack("Potion_Health", 8));
                            hotbar.setItemStackForSlot((short) 5, new ItemStack("Weapon_Bomb_Potion_Poison", 3));
                            hotbar.setItemStackForSlot((short) 6, new ItemStack("Weapon_Club_Doomed"));
                            hotbar.setItemStackForSlot((short) 7, new ItemStack("Weapon_Arrow_Crude", 128));
                            armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Iron_Head"));
                            armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Iron_Chest"));
                            armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Iron_Hands"));
                            armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Iron_Legs"));
                        }
                    }
                }
                case 3 -> {
                    switch (roundData.getSandboxClassLevelChoice()) {
                        case 0 -> {
                            hotbar.setItemStackForSlot((short) 1, new ItemStack("Weapon_Sword_Bone"));
                            hotbar.setItemStackForSlot((short) 2, new ItemStack("Weapon_Staff_Frost"));
                            hotbar.setItemStackForSlot((short) 3, new ItemStack("Potion_Health", 8));
                            hotbar.setItemStackForSlot((short) 5, new ItemStack("Potion_Morph_Pigeon", 3));
                            hotbar.setItemStackForSlot((short) 6, new ItemStack("Potion_Morph_Dog", 3));
                            hotbar.setItemStackForSlot((short) 7, new ItemStack("Potion_Morph_Frog", 3));
                            hotbar.setItemStackForSlot((short) 8, new ItemStack("Potion_Morph_Mouse", 3));
                            armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Copper_Head"));
                            armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Copper_Chest"));
                            armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Copper_Hands"));
                            armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Copper_Legs"));
                            utility.setItemStackForSlot((short) 0, new ItemStack("Weapon_Shield_Copper"));
                        }
                        case 1 -> {
                            hotbar.setItemStackForSlot((short) 1, new ItemStack("Weapon_Sword_Bone"));
                            hotbar.setItemStackForSlot((short) 2, new ItemStack("Weapon_Staff_Frost"));
                            hotbar.setItemStackForSlot((short) 3, new ItemStack("Potion_Health", 8));
                            hotbar.setItemStackForSlot((short) 5, new ItemStack("Potion_Morph_Pigeon", 3));
                            hotbar.setItemStackForSlot((short) 6, new ItemStack("Potion_Morph_Dog", 3));
                            hotbar.setItemStackForSlot((short) 7, new ItemStack("Potion_Morph_Frog", 3));
                            hotbar.setItemStackForSlot((short) 8, new ItemStack("Potion_Morph_Mouse", 3));
                            armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Iron_Head"));
                            armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Irom_Chest"));
                            armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Iron_Hands"));
                            armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Iron_Legs"));
                            utility.setItemStackForSlot((short) 0, new ItemStack("Weapon_Shield_Iron"));
                        }
                    }
                }
                case 2 -> {
                    switch (roundData.getSandboxClassLevelChoice()) {
                        case 0 -> {
                            hotbar.setItemStackForSlot((short) 1, new ItemStack("Weapon_Spear_Copper"));
                            hotbar.setItemStackForSlot((short) 2, new ItemStack("Weapon_Mace_Copper"));
                            hotbar.setItemStackForSlot((short) 3, new ItemStack("Bandage_Crude", 10));
                            armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Copper_Head"));
                            armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Copper_Chest"));
                            armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Copper_Hands"));
                            armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Copper_Legs"));
                        }
                        case 1 -> {
                            hotbar.setItemStackForSlot((short) 1, new ItemStack("Weapon_Spear_Iron"));
                            hotbar.setItemStackForSlot((short) 2, new ItemStack("Weapon_Mace_Iron"));
                            hotbar.setItemStackForSlot((short) 3, new ItemStack("Bandage_Crude", 10));
                            armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Iron_Head"));
                            armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Iron_Chest"));
                            armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Iron_Hands"));
                            armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Iron_Legs"));
                        }
                    }
                }
            }
            switch (foodChance) {
                case 0 -> hotbar.setItemStackForSlot((short) 0, new ItemStack("Food_Cheese", 16));
                case 1 -> hotbar.setItemStackForSlot((short) 0, new ItemStack("Food_Fish_Grilled", 8));
                case 2 -> hotbar.setItemStackForSlot((short) 0, new ItemStack("Food_Pie_Apple", 4));
                case 3 -> hotbar.setItemStackForSlot((short) 0, new ItemStack("Food_Wildmeat_Cooked", 16));
                case 4 -> hotbar.setItemStackForSlot((short) 0, new ItemStack("Food_Salad_Berry", 8));
                case 5 -> hotbar.setItemStackForSlot((short) 0, new ItemStack("Food_Kebab_Meat", 8));
            }
            switch (potionChance) {
                case 0 -> hotbar.setItemStackForSlot((short) 4, new ItemStack("Potion_Health_Greater", 4));
                case 1 -> hotbar.setItemStackForSlot((short) 4, new ItemStack("Potion_Health_Small", 8));
                case 2 -> hotbar.setItemStackForSlot((short) 4, new ItemStack("Potion_Health", 6));
            }
        }
        if (WAVE_BUTTON_ID.equals(data.buttonClicked)) {
            roundData.setSandboxRandomWaves(!roundData.getSandboxRandomWaves());
            player.getPageManager().openCustomPage(ref, store, new SandboxModeMenuUi(playerRef, roundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
        }


        if (data.COUNT1 > 0) {
            this.playerRef.sendMessage(Message.raw("@OUNT1 updated to: " + data.COUNT1));
            roundData.setEnemyCount((int) data.COUNT1);
            changed = true;
        }
        if (data.COUNT2 > 0) {
            this.playerRef.sendMessage(Message.raw("COUNT2 updated to: " + data.COUNT2));
            roundData.setEnemyCount2((int) data.COUNT2);
            changed = true;
        }
        if (data.COUNT3 > 0) {
            this.playerRef.sendMessage(Message.raw("COUNT3 updated to: " + data.COUNT3));
            roundData.setEnemyCount3((int) data.COUNT3);
            changed = true;
        }
        if (data.REQUIREDKILLS > 1) {
            this.playerRef.sendMessage(Message.raw("REQUIREDKILLS updated to: " + data.REQUIREDKILLS));
            roundData.setRequiredKills((int) data.REQUIREDKILLS);
            changed = true;
        }

        if (changed) {
            this.playerRef.sendMessage(Message.raw("Changes processed."));
        }
    }

    @Override
    public void onDismiss(@NotNull Ref<EntityStore> ref, @NotNull Store<EntityStore> store) {
        super.onDismiss(ref, store);
        if (roundData.getRoundType() == "null") {
            roundData.setRoundType("menu_start");
            roundData.setLevel(0);
        }
    }


    public static class Data {

        public String buttonClicked;

        public float COUNT1;
        public float COUNT2;
        public float COUNT3;
        public float REQUIREDKILLS;

        public static final BuilderCodec<Data> CODEC = BuilderCodec.builder(SandboxModeMenuUi.Data.class, SandboxModeMenuUi.Data::new)
                .append(new KeyedCodec<>("OnButtonClicked", Codec.STRING), (menuData, s) -> menuData.buttonClicked = s, choicePageEventData -> choicePageEventData.buttonClicked)
                .add()
                .append(new KeyedCodec<>("@COUNT1", Codec.FLOAT), (menuData, s) -> menuData.COUNT1 = s, menuData -> menuData.COUNT1)
                .add()
                .append(new KeyedCodec<>("@COUNT2", Codec.FLOAT), (menuData, s) -> menuData.COUNT2 = s, menuData -> menuData.COUNT2)
                .add()
                .append(new KeyedCodec<>("@COUNT3", Codec.FLOAT), (menuData, s) -> menuData.COUNT3 = s, menuData -> menuData.COUNT3)
                .add()
                .append(new KeyedCodec<>("@REQUIREDKILLS", Codec.FLOAT), (menuData, s) -> menuData.REQUIREDKILLS = s, menuData -> menuData.REQUIREDKILLS)
                .add()
                .build();
    }
}

