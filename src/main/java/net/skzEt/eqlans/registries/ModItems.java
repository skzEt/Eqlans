package net.skzEt.eqlans.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.skzEt.eqlans.Streamer;
import net.skzEt.eqlans.client.ModKeyboardHelper;
import net.skzEt.eqlans.item.*;

import java.util.function.Consumer;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(Streamer.MOD_ID);
    // Custom Items
    public static final DeferredItem<Item> OM_NOM = ITEMS.registerItem("default_om_nom",
            (properties) -> new OmNomItem(new Item.Properties().durability(600)
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "default_om_nom")))));
    public static final DeferredItem<Item> SUPER_OM_NOM = ITEMS.registerItem("default_super_om_nom",
            (properties) -> new SuperOmNomItem(new Item.Properties().durability(5)
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "default_super_om_nom")))));
    public static final DeferredItem<Item> HOLY_MANTLE = ITEMS.registerItem("holy_mantle",
            (properties) ->  new Item(new Item.Properties().stacksTo(1).fireResistant()
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "holy_mantle"))))
            {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
                    int hexColor = rgbToHex(16, 231, 255);

                    if (ModKeyboardHelper.isHoldingShift()) {
                        tooltipAdder.accept(Component.translatable("eqlans.description.holy_mantle")
                                .setStyle(Style.EMPTY).withColor(TextColor.fromRgb(hexColor).getValue()));
                    }
                    super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
                }
            });
    public static final DeferredItem<Item> CLOSED_DND_BOOK = ITEMS.registerItem("closed_dnd_book",
            (properties) -> new Item(new Item.Properties().stacksTo(1)
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "closed_dnd_book")))));
    public static final DeferredItem<Item> DND_BOOK = ITEMS.registerItem("dnd_book",
            (properties) -> new DNDBook(new Item.Properties().stacksTo(1)
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "dnd_book")))));
    public static final DeferredItem<Item> MAZELLOVVV_MICROPHONE = ITEMS.registerItem("mazellovvv_microphone",
            (properties) -> new MazellovvvMircophoneItem(new Item.Properties().stacksTo(1)
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "mazellovvv_microphone")))));
    // Streamers
    public static final DeferredItem<Item> STINTOCOIN = ITEMS.registerItem("stintocoin",
            (properties) -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                    Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "stintocoin")))));
    public static final DeferredItem<Item> BALL_OF_DRAKE = ITEMS.registerItem("ball_of_drake",
            (properties) -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                    Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "ball_of_drake")))));
    public static final DeferredItem<Item> DUMPLING = ITEMS.registerItem("dumpling",
            (properties) -> new Item(new Item.Properties().food(ModFoods.DUMPLING)
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "dumpling")))));
    public static final DeferredItem<Item> DUMPLING_SUN = ITEMS.registerItem("dumpling_sun",
            (properties) -> new Item(new Item.Properties().food(ModFoods.DUMPLING_SUN)
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "dumpling_sun")))));
    public static final DeferredItem<Item> MAZELLOVVV_COOKIE = ITEMS.registerItem("mazellovvv_cookie",
            (properties) -> new Item(new Item.Properties().food(ModFoods.GINGER_BREAD)
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "mazellovvv_cookie")))));

    // Default Items
    public static final DeferredItem<Item> TWITCH_DIAMOND = ITEMS.registerItem("twitch_diamond",
            (properties) -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                    Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "twitch_diamond")))));
    public static final DeferredItem<Item> SCHOOL_BOOT = ITEMS.registerItem("school_boot",
            (properties) -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                    Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "school_boot")))));
    public static final DeferredItem<Item> COIN_ALLOY = ITEMS.registerItem("coin_alloy",
            (properties) -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                    Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "coin_alloy")))));
    public static final DeferredItem<Item> DICE = ITEMS.registerItem("dice",
            (properties) -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                    Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "dice")))));
    // Armor
    public static final DeferredItem<Item> GLASSES = ITEMS.registerItem("glasses",
            (properties) -> new Item(properties.humanoidArmor(ModArmorMaterial.SCHOOL_ARMOR_MATERIAL,
                    ArmorType.HELMET)
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "glasses")))));

    public static final DeferredItem<Item> DRAKE_PENDANT = ITEMS.registerItem("drake_pendant",
            (properties) -> new Item(properties.humanoidArmor(ModArmorMaterial.STREAMER_ARMOR_MATERIAL,
                    ArmorType.CHESTPLATE)
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "drake_pendant")))));

    public static final DeferredItem<Item> STINTIK_HELMET = ITEMS.registerItem("stint_helmet",
            (properties) -> new Item(properties.humanoidArmor(ModArmorMaterial.STREAMER_ARMOR_MATERIAL,
                    ArmorType.HELMET)
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "stint_helmet")))));

    public static final DeferredItem<Item> DND_CHESTPLATE = ITEMS.registerItem("dnd_chestplate",
            (properties) -> new Item(properties.humanoidArmor(ModArmorMaterial.DND_ARMOR_MATERIAL,
                    ArmorType.CHESTPLATE)
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "dnd_chestplate")))));
    // Weapons
    public static final DeferredItem<Item> TWITCH_AXE = ITEMS.registerItem("twitch_axe",
            (properties) -> new AxeItem(ModToolMaterial.TWITCH, 15, 3.5f, new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "twitch_axe")))));
    public static final DeferredItem<Item> TWITCH_SWORD = ITEMS.registerItem("twitch_sword",
            (properties) -> new Item(new Item.Properties().sword(ModToolMaterial.TWITCH, 8, 0.8f)
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "twitch_sword")))));
    public static final DeferredItem<Item> TWITCH_SHOVEL = ITEMS.registerItem("twitch_shovel",
            (properties) -> new ShovelItem(ModToolMaterial.TWITCH, 3, 0.5f, new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "twitch_shovel")))));
    public static final DeferredItem<Item> TWITCH_PICKAXE = ITEMS.registerItem("twitch_pickaxe",
            (properties) -> new Item(new Item.Properties().pickaxe(ModToolMaterial.TWITCH, 5, 0.5f)
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "twitch_pickaxe")))));
    public static final DeferredItem<Item> TWITCH_HOE = ITEMS.registerItem("twitch_hoe",
            (properties) -> new HoeItem(ModToolMaterial.TWITCH, 3, 0.5f, new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "twitch_hoe")))));
    // Food
    public static final DeferredItem<Item> OVERCOOKED_DUMPLING = ITEMS.registerItem("overcooked_dumpling",
            (properties) -> new Item(new Item.Properties().food(ModFoods.OVERCOOKED_DUMPLING)
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "overcooked_dumpling")))));

    public static int rgbToHex(int r, int g, int b) {
        return (r << 16) | (g << 8) | b;
    }
}
