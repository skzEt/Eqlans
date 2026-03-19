package net.skzEt.eqlans.registries;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.Equippable;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.skzEt.eqlans.Streamer;
import net.skzEt.eqlans.client.ModKeyboardHelper;
import net.skzEt.eqlans.item.*;

import java.util.function.Consumer;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(Streamer.MOD_ID);
    public static final ResourceKey<EquipmentAsset> TWITCH_ASSET =
            ResourceKey.create(EquipmentAssets.ROOT_ID,
                    Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "twitch_asset"));
    // Custom Items
    public static final DeferredItem<Item> OM_NOM = ITEMS.register("om_nom",
            () -> new OmNomItem(new Item.Properties().durability(600)));
    public static final DeferredItem<Item> SUPER_OM_NOM = ITEMS.register("super_om_nom",
            () -> new SuperOmNomItem(new Item.Properties().durability(5)));
    public static final DeferredItem<Item> HOLY_MANTLE = ITEMS.register("holy_mantle",
            () ->  new Item(new Item.Properties().stacksTo(1).fireResistant()) {
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
    public static final DeferredItem<Item> CLOSED_DND_BOOK = ITEMS.register("closed_dnd_book",
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> DND_BOOK = ITEMS.register("dnd_book",
            () -> new DNDBook(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> MAZELLOVVV_MICROPHONE = ITEMS.register("mazellovvv_microphone",
            () -> new MazellovvvMircophoneItem(new Item.Properties().stacksTo(1)));
    // Streamers
    public static final DeferredItem<Item> STINTOCOIN = ITEMS.register("stintocoin",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BALL_OF_DRAKE = ITEMS.register("ball_of_drake",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DUMPLING = ITEMS.register("dumpling",
            () -> new Item(new Item.Properties().food(ModFoods.DUMPLING)));
    public static final DeferredItem<Item> DUMPLING_SUN = ITEMS.register("dumpling_sun",
            () -> new Item(new Item.Properties().food(ModFoods.DUMPLING_SUN)));
    public static final DeferredItem<Item> MAZELLOVVV_COOKIE = ITEMS.register("mazellovvv_cookie",
            () -> new Item(new Item.Properties().food(ModFoods.GINGER_BREAD)));

    // Default Items
    public static final DeferredItem<Item> TWITCH_DIAMOND = ITEMS.register("twitch_diamond",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SCHOOL_BOOT = ITEMS.register("school_boot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COIN_ALLOY = ITEMS.register("coin_alloy",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DICE = ITEMS.register("dice",
            () -> new Item(new Item.Properties()));
    // Armor
    // Armor
    public static final DeferredItem<Item> GLASSES = registerArmor("glasses",
            EquipmentSlot.HEAD, 1, EquipmentSlotGroup.HEAD, 0, 250, SCHOOL_BOOT);

    public static final DeferredItem<Item> DRAKE_PENDANT = registerArmor("drake_pendant",
            EquipmentSlot.CHEST, 6, EquipmentSlotGroup.CHEST, 0, 1000, TWITCH_DIAMOND,
            ItemAttributeModifiers.builder()
                    .add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(Identifier.fromNamespaceAndPath(
                                    Streamer.MOD_ID, "twitch_armor_toughness"),
                                    1f, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.CHEST)  // ← было HEAD, должно быть CHEST
                    .build());

    public static final DeferredItem<Item> STINTIK_HELMET = registerArmor("stint_helmet",
            EquipmentSlot.HEAD, 8, EquipmentSlotGroup.HEAD, 15, 2000, COIN_ALLOY,
            ItemAttributeModifiers.builder()
                    .add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(Identifier.fromNamespaceAndPath(
                                    Streamer.MOD_ID, "twitch_armor_toughness"),
                                    0.5f, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.HEAD)
                    .build());

    public static final DeferredItem<Item> BOXERS = registerArmor("boxers",
            EquipmentSlot.LEGS, 4, EquipmentSlotGroup.LEGS, 15, 500, SCHOOL_BOOT,
            ItemAttributeModifiers.builder()
                    .add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(Identifier.fromNamespaceAndPath(
                                    Streamer.MOD_ID, "twitch_armor_toughness"),
                                    0.1f, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.LEGS)
                    .add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(Identifier.fromNamespaceAndPath(
                                    Streamer.MOD_ID, "twitch_armor_knockback_resistance"),
                                    0.2f, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.LEGS)
                    .build());

    public static final DeferredItem<Item> DND = registerArmor("dnd",
            EquipmentSlot.CHEST, 6, EquipmentSlotGroup.CHEST, 15, 1500, DUMPLING,
            ItemAttributeModifiers.builder()
                    .add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(Identifier.fromNamespaceAndPath(
                                    Streamer.MOD_ID, "twitch_armor_toughness"),
                                    0.1f, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.CHEST)
                    .add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(Identifier.fromNamespaceAndPath(
                                    Streamer.MOD_ID, "twitch_armor_knockback_resistance"),
                                    0.5f, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.CHEST)
                    .build());
    // Weapons
    public static final DeferredItem<Item> TWITCH_AXE = ITEMS.register("twitch_axe",
            () -> new AxeItem(ModToolMaterial.TWITCH, 15, 3.5f, new Item.Properties()));
    public static final DeferredItem<Item> TWITCH_SWORD = ITEMS.register("twitch_sword",
            () -> new Item(new Item.Properties().sword(ModToolMaterial.TWITCH, 8, 0.8f)));
    public static final DeferredItem<Item> TWITCH_SHOVEL = ITEMS.register("twitch_shovel",
            () -> new ShovelItem(ModToolMaterial.TWITCH, 3, 0.5f, new Item.Properties()));
    public static final DeferredItem<Item> TWITCH_PICKAXE = ITEMS.register("twitch_pickaxe",
            () -> new Item(new Item.Properties().pickaxe(ModToolMaterial.TWITCH, 5, 0.5f)));
    public static final DeferredItem<Item> TWITCH_HOE = ITEMS.register("twitch_hoe",
            () -> new HoeItem(ModToolMaterial.TWITCH, 3, 0.5f, new Item.Properties()));
    // Food
    public static final DeferredItem<Item> OVERCOOKED_DUMPLING = ITEMS.register("overcooked_dumpling",
            () -> new Item(new Item.Properties().food(ModFoods.OVERCOOKED_DUMPLING)));

    public static DeferredItem<Item> registerArmor(String name, EquipmentSlot slot, int protection, EquipmentSlotGroup group, int enchantable, int durability, DeferredItem<Item> repairable) {
        return ITEMS.registerItem(name,
                properties -> new Item(
                        properties
                                .component(DataComponents.EQUIPPABLE, Equippable.builder(slot)
                                        .setEquipSound(SoundEvents.ARMOR_EQUIP_NETHERITE)
                                        .setAsset(ResourceKey.create(EquipmentAssets.ROOT_ID,
                                                Identifier.fromNamespaceAndPath(Streamer.MOD_ID, name))).build())
                                .attributes(ItemAttributeModifiers.builder()
                                        .add(Attributes.ARMOR, new AttributeModifier(Identifier.fromNamespaceAndPath(
                                                        Streamer.MOD_ID, "twitch_armor_toughness"),
                                                        protection, AttributeModifier.Operation.ADD_VALUE),
                                                group).build())
                                .enchantable(enchantable)
                                .durability(durability)
//                                .repairable(repairable))
                ));
    }

    // Версия с дополнительными атрибутами
    public static DeferredItem<Item> registerArmor(String name, EquipmentSlot slot, int protection, EquipmentSlotGroup group, int enchantable, int durability, DeferredItem<Item> repairable, ItemAttributeModifiers attributes) {
        return ITEMS.registerItem(name,
                properties -> new Item(
                        properties
                                .component(DataComponents.EQUIPPABLE, Equippable.builder(slot)
                                        .setEquipSound(SoundEvents.ARMOR_EQUIP_NETHERITE)
                                        .setAsset(ResourceKey.create(EquipmentAssets.ROOT_ID,
                                                Identifier.fromNamespaceAndPath(Streamer.MOD_ID, name))).build())
                                .attributes(ItemAttributeModifiers.builder()
                                        .add(Attributes.ARMOR, new AttributeModifier(Identifier.fromNamespaceAndPath(
                                                        Streamer.MOD_ID, "twitch_armor_toughness"),
                                                        protection, AttributeModifier.Operation.ADD_VALUE),
                                                group).build())
                                .attributes(attributes)  // ← ВНИМАНИЕ: это полностью заменяет предыдущий attributes!
                                .enchantable(enchantable)
                                .durability(durability)
//                                .repairable(repairable.get())
                ));
    }

    public static int rgbToHex(int r, int g, int b) {
        return (r << 16) | (g << 8) | b;
    }
}
