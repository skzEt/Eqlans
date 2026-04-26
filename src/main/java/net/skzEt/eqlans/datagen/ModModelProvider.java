package net.skzEt.eqlans.datagen;


import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.skzEt.eqlans.Streamer;
import net.skzEt.eqlans.registries.ModArmorMaterial;
import net.skzEt.eqlans.registries.ModBlocks;
import net.skzEt.eqlans.registries.ModItems;

import java.util.Set;
import java.util.stream.Stream;

public class ModModelProvider extends ModelProvider {

    private static Set<DeferredItem<Item>> flatItems = Set.of(
            ModItems.DUMPLING,
            ModItems.DUMPLING_SUN,
            ModItems.BALL_OF_DRAKE,
            ModItems.MAZELLOVVV_COOKIE,
            ModItems.STINTOCOIN,

            ModItems.TWITCH_DIAMOND,
            ModItems.SCHOOL_BOOT,
            ModItems.COIN_ALLOY,
            ModItems.DICE,

            ModItems.OVERCOOKED_DUMPLING
    );

    private static Set<DeferredItem<Item>> flatHandledItems = Set.of(
            ModItems.TWITCH_AXE,
            ModItems.TWITCH_HOE,
            ModItems.TWITCH_PICKAXE,
            ModItems.TWITCH_SHOVEL,
            ModItems.TWITCH_SWORD
    );

    public ModModelProvider(PackOutput output) {
        super(output, Streamer.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        for (DeferredItem<Item> item : flatItems) {
            itemModels.generateFlatItem(item.get(), ModelTemplates.FLAT_ITEM);
        }

        for (DeferredItem<Item> item : flatHandledItems) {
            itemModels.generateFlatItem(item.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        }

        itemModels.generateTrimmableItem(ModItems.GLASSES.get(), ModArmorMaterial.SCHOOL,
                ItemModelGenerators.TRIM_PREFIX_HELMET, false);

        itemModels.generateTrimmableItem(ModItems.STINTIK_HELMET.get(), ModArmorMaterial.STREAMER,
                ItemModelGenerators.TRIM_PREFIX_HELMET, false);

        itemModels.generateTrimmableItem(ModItems.DND_CHESTPLATE.get(), ModArmorMaterial.DND,
                ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);

        itemModels.generateTrimmableItem(ModItems.DRAKE_PENDANT.get(), ModArmorMaterial.STREAMER,
                ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);

        blockModels.createTrivialCube(ModBlocks.TWITCH_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.TWITCH_ORE.get());
        blockModels.createTrivialCube(ModBlocks.DEEPSLATE_TWITCH_ORE.get());

        blockModels.family(ModBlocks.GINGER_BLOCK.get())
                .stairs(ModBlocks.GINGER_STAIRS.get())
                .slab(ModBlocks.GINGER_SLAB.get())
                .fence(ModBlocks.GINGER_FENCE.get())
                .fenceGate(ModBlocks.GINGER_FENCE_GATE.get())
                .door(ModBlocks.GINGER_DOOR.get());

        blockModels.family(ModBlocks.SUGAR_BLOCK.get())
                .stairs(ModBlocks.SUGAR_STAIRS.get())
                .slab(ModBlocks.SUGAR_SLAB.get())
                .fence(ModBlocks.SUGAR_FENCE.get())
                .fenceGate(ModBlocks.SUGAR_FENCE_GATE.get());
    }

    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        return ModItems.ITEMS.getEntries().stream().filter(x -> !x.is(ModItems.OM_NOM) && !x.is(ModItems.SUPER_OM_NOM)
                && !x.is(ModItems.HOLY_MANTLE) && !x.is(ModItems.CLOSED_DND_BOOK) && !x.is(ModItems.DND_BOOK)
                && !x.is(ModItems.MAZELLOVVV_MICROPHONE));
    }
}