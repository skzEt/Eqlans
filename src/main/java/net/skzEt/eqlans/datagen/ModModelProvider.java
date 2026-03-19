package net.skzEt.eqlans.datagen;


import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.StairBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.skzEt.eqlans.Streamer;
import net.skzEt.eqlans.registries.ModBlocks;
import net.skzEt.eqlans.registries.ModItems;

import java.util.Set;

public class ModModelProvider extends ModelProvider {

    private static Set<DeferredItem<Item>> flatItems = Set.of(
            ModItems.DUMPLING,
            ModItems.DUMPLING_SUN,
            ModItems.BALL_OF_DRAKE,
            ModItems.MAZELLOVVV_COOKIE,

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

        itemModels.generateTrimmableItem(ModItems.GLASSES.get(), ModItems.TWITCH_ASSET,
                ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModels.generateTrimmableItem(ModItems.STINTIK_HELMET.get(), ModItems.TWITCH_ASSET,
                ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModels.generateTrimmableItem(ModItems.BOXERS.get(), ModItems.TWITCH_ASSET,
                ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModels.generateTrimmableItem(ModItems.DND.get(), ModItems.TWITCH_ASSET,
                ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModels.generateTrimmableItem(ModItems.DRAKE_PENDANT.get(), ModItems.TWITCH_ASSET,
                ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);

        blockModels.createTrivialCube(ModBlocks.TWITCH_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.TWITCH_ORE.get());
        blockModels.createTrivialCube(ModBlocks.DEEPSLATE_TWITCH_ORE.get());
        blockModels.createGenericCube(ModBlocks.GINGER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.SUGAR_BLOCK.get());

        blockModels.createTrivialCube(ModBlocks.GINGER_STAIRS.get());
        blockModels.createTrivialCube(ModBlocks.SUGAR_STAIRS.get());
        blockModels.createTrivialCube(ModBlocks.SUGAR_SLAB.get());
        blockModels.createTrivialCube(ModBlocks.GINGER_SLAB.get());
        blockModels.createTrivialCube(ModBlocks.GINGER_FENCE_GATE.get());
        blockModels.createTrivialCube(ModBlocks.SUGAR_FENCE_GATE.get());
        blockModels.createTrivialCube(ModBlocks.GINGER_FENCE.get());
        blockModels.createTrivialCube(ModBlocks.SUGAR_FENCE.get());

        blockModels.createDoor(ModBlocks.GINGER_DOOR.get());
    }
}