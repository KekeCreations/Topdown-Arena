package com.kekecreations.topdownarena.common.ui;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.protocol.packets.interface_.Page;
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

public class ClassMenuUi extends InteractiveCustomUIPage<MenuWithButtonsData> {

    private static final String SELECT_ONE_BUTTON_ID = "SELECT1";
    private static final String SELECT_TWO_BUTTON_ID = "SELECT2";
    private static final String SELECT_THREE_BUTTON_ID = "SELECT3";
    private static final String SELECT_FOUR_BUTTON_ID = "SELECT4";
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
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#SELECT4", EventData.of("OnButtonClicked", SELECT_FOUR_BUTTON_ID), false);
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull MenuWithButtonsData data) {
        super.handleDataEvent(ref, store, data);

        Player player = Objects.requireNonNull(store.getComponent(ref, Player.getComponentType()));
        RoundComponent roundData = Objects.requireNonNull(store.getComponent(ref, RoundComponent.getComponentType()));
        EntityStatMap entityStat = store.getComponent(ref, EntityStatMap.getComponentType());
        int foodChance = (int)(Math.random() * 6);
        int potionChance = (int)(Math.random() * 3);
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

        if (hotbar != null && armour != null && utility != null && backpack != null && storage != null && tool != null) {

            if (SELECT_ONE_BUTTON_ID.equals(data.buttonClicked) || SELECT_TWO_BUTTON_ID.equals(data.buttonClicked) || SELECT_THREE_BUTTON_ID.equals(data.buttonClicked) || SELECT_FOUR_BUTTON_ID.equals(data.buttonClicked)) {
                hotbar.clear();
                armour.clear();
                utility.clear();
                backpack.clear();
                storage.clear();
                tool.clear();
                if (entityStat != null) {
                    entityStat.resetStatValue(DefaultEntityStatTypes.getHealth());
                    entityStat.resetStatValue(DefaultEntityStatTypes.getStamina());
                    entityStat.resetStatValue(DefaultEntityStatTypes.getMana());
                }
            }
            if (SELECT_ONE_BUTTON_ID.equals(data.buttonClicked)) {
                if (roundData.getLevel() <= 5) {
                    hotbar.setItemStackForSlot((short) 1, new ItemStack("Weapon_Sword_Copper"));
                    hotbar.setItemStackForSlot((short) 2, new ItemStack("Weapon_Battleaxe_Copper"));
                    hotbar.setItemStackForSlot((short) 3, new ItemStack("Potion_Health", 8));

                    armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Copper_Head"));
                    armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Copper_Chest"));
                    armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Copper_Hands"));
                    armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Copper_Legs"));

                    if (roundData.getLevel() >= 2) {
                        utility.setItemStackForSlot((short) 0, new ItemStack("Weapon_Shield_Copper"));
                    }
                }
            } else if (SELECT_TWO_BUTTON_ID.equals(data.buttonClicked)) {
                if (roundData.getLevel() <= 5) {
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
            } else if (SELECT_THREE_BUTTON_ID.equals(data.buttonClicked)) {
                if (roundData.getLevel() <= 5) {
                    hotbar.setItemStackForSlot((short) 1, new ItemStack("Weapon_Sword_Bone"));
                    hotbar.setItemStackForSlot((short) 2, new ItemStack("Weapon_Staff_Frost"));
                    hotbar.setItemStackForSlot((short) 3, new ItemStack("Potion_Health", 8));
                    hotbar.setItemStackForSlot((short) 5, new ItemStack("Weapon_Bomb_Potion_Poison", 2));
                    hotbar.setItemStackForSlot((short) 6, new ItemStack("Potion_Morph_Dog", 3));
                    hotbar.setItemStackForSlot((short) 7, new ItemStack("Potion_Morph_Frog", 3));
                    hotbar.setItemStackForSlot((short) 8, new ItemStack("Potion_Morph_Mouse", 3));

                    armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Copper_Head"));
                    armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Copper_Chest"));
                    armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Copper_Hands"));
                    armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Copper_Legs"));
                    utility.setItemStackForSlot((short) 0, new ItemStack("Weapon_Shield_Copper"));
                }
            } else if (SELECT_FOUR_BUTTON_ID.equals(data.buttonClicked)) {
                if (roundData.getLevel() <= 5) {
                    hotbar.setItemStackForSlot((short) 1, new ItemStack("Weapon_Spear_Copper"));
                    hotbar.setItemStackForSlot((short) 2, new ItemStack("Weapon_Mace_Copper"));
                    hotbar.setItemStackForSlot((short) 3, new ItemStack("Bandage_Crude", 10));

                    armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Copper_Head"));
                    armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Copper_Chest"));
                    armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Copper_Hands"));
                    armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Copper_Legs"));
                    if (roundData.getLevel() >= 2) {
                        utility.setItemStackForSlot((short) 0, new ItemStack("Weapon_Shield_Copper"));
                    }
                }
            }
            if (SELECT_ONE_BUTTON_ID.equals(data.buttonClicked) || SELECT_TWO_BUTTON_ID.equals(data.buttonClicked) || SELECT_THREE_BUTTON_ID.equals(data.buttonClicked) || SELECT_FOUR_BUTTON_ID.equals(data.buttonClicked)) {
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
                roundData.freezeRoundTimer(false);
                roundData.setRoundType("level");
                player.getPageManager().setPage(ref, store, Page.None);
            }
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
