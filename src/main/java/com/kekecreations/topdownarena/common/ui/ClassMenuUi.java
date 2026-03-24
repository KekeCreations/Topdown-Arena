package com.kekecreations.topdownarena.common.ui;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.protocol.packets.interface_.Page;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.inventory.Inventory;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.ui.builder.EventData;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.topdownarena.common.component.RoundComponent;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Random;
import java.util.random.RandomGenerator;

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

        int foodChance = (int)(Math.random() * 6);
        int weaponChance = (int)(Math.random() * 6);
        int weapon2Chance = (int)(Math.random() * 4);
        Inventory inventory = player.getInventory();

        if (SELECT_ONE_BUTTON_ID.equals(data.buttonClicked) || SELECT_TWO_BUTTON_ID.equals(data.buttonClicked) || SELECT_THREE_BUTTON_ID.equals(data.buttonClicked)) {
            inventory.clear();
        }
        player.sendMessage(Message.raw(weaponChance + ""));
        player.sendMessage(Message.raw(weapon2Chance + ""));
        if (SELECT_ONE_BUTTON_ID.equals(data.buttonClicked)) {
            switch(weaponChance) {
                case 0 -> inventory.getHotbar().setItemStackForSlot((short) 1, new ItemStack("Weapon_Sword_Iron"));
                case 1 -> inventory.getHotbar().setItemStackForSlot((short) 1, new ItemStack("Weapon_Sword_Bronze"));
                case 2 -> inventory.getHotbar().setItemStackForSlot((short) 1, new ItemStack("Weapon_Sword_Steel_Rusty"));
                case 3 -> inventory.getHotbar().setItemStackForSlot((short) 1, new ItemStack("Weapon_Sword_Copper"));
                case 4 -> inventory.getHotbar().setItemStackForSlot((short) 1, new ItemStack("Weapon_Sword_Mithril"));
                case 5 -> inventory.getHotbar().setItemStackForSlot((short) 1, new ItemStack("Weapon_Daggers_Iron"));
            }
            switch(weapon2Chance) {
                case 0 -> inventory.getHotbar().setItemStackForSlot((short) 2, new ItemStack("Weapon_Battleaxe_Iron"));
                case 1 -> inventory.getHotbar().setItemStackForSlot((short) 2, new ItemStack("Weapon_Battleaxe_Cobalt"));
                case 2 -> inventory.getHotbar().setItemStackForSlot((short) 2, new ItemStack("Weapon_Battleaxe_Iron_Rusty"));
                case 3 -> inventory.getHotbar().setItemStackForSlot((short) 2, new ItemStack("Weapon_Battleaxe_Copper"));
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
            player.getPageManager().setPage(ref, store, Page.None);
            roundData.freezeRoundTimer(false);
        }
    }
}
