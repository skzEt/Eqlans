package net.skzEt.eqlans.registries;

import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.skzEt.eqlans.util.ModTags;

import java.util.EnumMap;

public class ModArmorMaterial {
    public static ResourceKey<EquipmentAsset> SCHOOL = EquipmentAssets.createId("school");
    public static ResourceKey<EquipmentAsset> STREAMER = EquipmentAssets.createId("streamer");
    public static ResourceKey<EquipmentAsset> DND = EquipmentAssets.createId("dnd");

    public static final ArmorMaterial SCHOOL_ARMOR_MATERIAL = new ArmorMaterial(600,
            Util.make(new EnumMap<>(ArmorType.class), attribute -> {
                attribute.put(ArmorType.HELMET, 1);
            }), 16, SoundEvents.ARMOR_EQUIP_DIAMOND,
            0f, 0, ModTags.Items.SCHOOL_REPAIRABLE, SCHOOL);

    public static final ArmorMaterial STREAMER_ARMOR_MATERIAL = new ArmorMaterial(3000,
            Util.make(new EnumMap<>(ArmorType.class), attribute -> {
                attribute.put(ArmorType.BOOTS, 5);
                attribute.put(ArmorType.LEGGINGS, 7);
                attribute.put(ArmorType.CHESTPLATE, 9);
                attribute.put(ArmorType.HELMET, 5);
                attribute.put(ArmorType.BODY, 11);
            }), 16, SoundEvents.ARMOR_EQUIP_DIAMOND,
            1.5f, 0.25f, ModTags.Items.STEAMER_REPAIRABLE, STREAMER);

    public static final ArmorMaterial DND_ARMOR_MATERIAL = new ArmorMaterial(1500,
            Util.make(new EnumMap<>(ArmorType.class), attribute -> {
                attribute.put(ArmorType.CHESTPLATE, 9);
            }), 16, SoundEvents.ARMOR_EQUIP_DIAMOND,
            0.1f, 0.5f, ModTags.Items.DND_REPAIRABLE, DND);
}
