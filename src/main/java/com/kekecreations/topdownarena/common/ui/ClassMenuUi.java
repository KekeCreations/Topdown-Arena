package com.kekecreations.topdownarena.common.ui;

import com.hypixel.hytale.component.ComponentAccessor;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.EntityStatEffects;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.protocol.packets.interface_.Page;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandManager;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.inventory.Inventory;
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

public class ClassMenuUi extends InteractiveCustomUIPage<MenuWithButtonsData> {

    private static final String SELECT_ONE_BUTTON_ID = "SELECT1";
    private static final String SELECT_TWO_BUTTON_ID = "SELECT2";
    private static final String SELECT_THREE_BUTTON_ID = "SELECT3";
    RoundComponent roundData;

    public ClassMenuUi(@Nonnull PlayerRef playerRef, RoundComponent roundData, @Nonnull CustomPageLifetime lifetime) {
        super(playerRef, lifetime, MenuWithButtonsData.CODEC);
        this.roundData = roundData;
    }

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("Pages/class_menu.ui");
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#SELECT1", EventData.of("OnButtonClicked", SELECT_ONE_BUTTON_ID), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#SELECT2", EventData.of("OnButtonClicked", SELECT_TWO_BUTTON_ID), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#SELECT3", EventData.of("OnButtonClicked", SELECT_THREE_BUTTON_ID), false);
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull MenuWithButtonsData data) {
        super.handleDataEvent(ref, store, data);

        Player player = Objects.requireNonNull(store.getComponent(ref, Player.getComponentType()));
        RoundComponent roundData = Objects.requireNonNull(store.getComponent(ref, RoundComponent.getComponentType()));
        EntityStatMap entityStat = store.getComponent(ref, EntityStatMap.getComponentType());
        int foodChance = (int)(Math.random() * 6);
        int potionChance = (int)(Math.random() * 6);
        Inventory inventory = player.getInventory();

        if (SELECT_ONE_BUTTON_ID.equals(data.buttonClicked) || SELECT_TWO_BUTTON_ID.equals(data.buttonClicked) || SELECT_THREE_BUTTON_ID.equals(data.buttonClicked)) {
            inventory.clear();
            if (entityStat != null) {
                entityStat.setStatValue(DefaultEntityStatTypes.getHealth(), 150.0F);
            }
        }
        if (SELECT_ONE_BUTTON_ID.equals(data.buttonClicked)) {
            if (roundData.getLevel() <= 3) {
                inventory.getHotbar().setItemStackForSlot((short) 1, new ItemStack("Weapon_Sword_Copper"));
                inventory.getHotbar().setItemStackForSlot((short) 2, new ItemStack("Weapon_Battleaxe_Copper"));
                inventory.getHotbar().setItemStackForSlot((short) 3, new ItemStack("Potion_Health", 8));

                inventory.getArmor().setItemStackForSlot((short) 0, new ItemStack("Armor_Copper_Head"));
                inventory.getArmor().setItemStackForSlot((short) 1, new ItemStack("Armor_Copper_Chest"));
                inventory.getArmor().setItemStackForSlot((short) 2, new ItemStack("Armor_Copper_Hands"));
                inventory.getArmor().setItemStackForSlot((short) 3, new ItemStack("Armor_Copper_Legs"));

                if (roundData.getLevel() >= 2) {
                    inventory.getUtility().setItemStackForSlot((short) 0, new ItemStack("Weapon_Shield_Copper"));
                }
            }
        } else if (SELECT_TWO_BUTTON_ID.equals(data.buttonClicked)) {
            if (roundData.getLevel() <= 3) {
                inventory.getHotbar().setItemStackForSlot((short) 1, new ItemStack("Weapon_Shortbow_Cobalt"));
                inventory.getHotbar().setItemStackForSlot((short) 2, new ItemStack("Weapon_Crossbow_Ancient_Steel"));
                inventory.getHotbar().setItemStackForSlot((short) 3, new ItemStack("Potion_Health", 8));
                inventory.getHotbar().setItemStackForSlot((short) 5, new ItemStack("Weapon_Bomb_Potion_Poison", 2));
                inventory.getHotbar().setItemStackForSlot((short) 6, new ItemStack("Weapon_Arrow_Crude", 128));

                inventory.getArmor().setItemStackForSlot((short) 0, new ItemStack("Armor_Copper_Head"));
                inventory.getArmor().setItemStackForSlot((short) 1, new ItemStack("Armor_Copper_Chest"));
                inventory.getArmor().setItemStackForSlot((short) 2, new ItemStack("Armor_Copper_Hands"));
                inventory.getArmor().setItemStackForSlot((short) 3, new ItemStack("Armor_Copper_Legs"));

                if (roundData.getLevel() >= 2) {
                    inventory.getUtility().setItemStackForSlot((short) 0, new ItemStack("Weapon_Shield_Copper"));
                }
            }
        }
        if (SELECT_ONE_BUTTON_ID.equals(data.buttonClicked) || SELECT_TWO_BUTTON_ID.equals(data.buttonClicked) || SELECT_THREE_BUTTON_ID.equals(data.buttonClicked)) {
            switch(foodChance) {
                case 0 -> inventory.getHotbar().setItemStackForSlot((short) 0, new ItemStack("Food_Cheese", 16));
                case 1 -> inventory.getHotbar().setItemStackForSlot((short) 0, new ItemStack("Food_Fish_Grilled", 8));
                case 2 -> inventory.getHotbar().setItemStackForSlot((short) 0, new ItemStack("Food_Pie_Apple", 4));
                case 3 -> inventory.getHotbar().setItemStackForSlot((short) 0, new ItemStack("Food_Wildmeat_Cooked", 16));
                case 4 -> inventory.getHotbar().setItemStackForSlot((short) 0, new ItemStack("Food_Salad_Berry", 8));
                case 5 -> inventory.getHotbar().setItemStackForSlot((short) 0, new ItemStack("Food_Kebab_Meat", 8));
            }
            switch(potionChance) {
                case 0 -> inventory.getHotbar().setItemStackForSlot((short) 4, new ItemStack("Potion_Health_Greater", 4));
                case 1 -> inventory.getHotbar().setItemStackForSlot((short) 4, new ItemStack("Potion_Health_Small", 8));
                case 2 -> inventory.getHotbar().setItemStackForSlot((short) 4, new ItemStack("Potion_Regen_Stamina", 2));
                case 3 -> inventory.getHotbar().setItemStackForSlot((short) 4, new ItemStack("Potion_Regen_Health", 2));
                case 4 -> inventory.getHotbar().setItemStackForSlot((short) 4, new ItemStack("Potion_Regen_Health_Large", 1));
                case 5 -> inventory.getHotbar().setItemStackForSlot((short) 4, new ItemStack("Potion_Health", 6));
            }
            roundData.freezeRoundTimer(false);
            roundData.setRoundType("level");
            player.getPageManager().setPage(ref, store, Page.None);
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
}
