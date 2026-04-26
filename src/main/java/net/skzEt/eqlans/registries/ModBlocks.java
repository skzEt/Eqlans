package net.skzEt.eqlans.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.skzEt.eqlans.Streamer;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Streamer.MOD_ID);

    public static final DeferredBlock<Block> TWITCH_ORE = registerBlock("twitch_ore",
            (properties) -> new DropExperienceBlock(UniformInt.of(1, 2),BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f).requiresCorrectToolForDrops()
                    .setId(ResourceKey.create(Registries.BLOCK,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "twitch_ore")))));
    public static final DeferredBlock<Block> DEEPSLATE_TWITCH_ORE = registerBlock("deepslate_twitch_ore",
            (properties) -> new DropExperienceBlock(UniformInt.of(1, 2), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                    .strength(2f).requiresCorrectToolForDrops()
                    .setId(ResourceKey.create(Registries.BLOCK,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "deepslate_twitch_ore")))));
    public static final DeferredBlock<Block> TWITCH_BLOCK = registerBlock("twitch_block",
            (properties) -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                    .sound(SoundType.ANVIL)
                    .setId(ResourceKey.create(Registries.BLOCK,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "twitch_block")))));
    // Ginger
    public static final DeferredBlock<Block> GINGER_BLOCK = registerBlock("ginger_block",
            (properties) -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)
                    .setId(ResourceKey.create(Registries.BLOCK,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "ginger_block")))));
    public static final DeferredBlock<StairBlock> GINGER_STAIRS = registerBlock("ginger_stairs",
            (properties) -> new StairBlock(ModBlocks.GINGER_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)
                            .setId(ResourceKey.create(Registries.BLOCK,
                                    Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "ginger_stairs")))));
    public static final DeferredBlock<DoorBlock> GINGER_DOOR = registerBlock("ginger_door",
            (properties) -> new DoorBlock(BlockSetType.CHERRY, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)
                    .setId(ResourceKey.create(Registries.BLOCK,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "ginger_door")))));
    public static final DeferredBlock<SlabBlock> GINGER_SLAB = registerBlock("ginger_slab",
            (properties) -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)
                    .setId(ResourceKey.create(Registries.BLOCK,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "ginger_slab")))));
    public static final DeferredBlock<FenceBlock> GINGER_FENCE = registerBlock("ginger_fence",
            (properties) -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)
                    .setId(ResourceKey.create(Registries.BLOCK,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "ginger_fence")))));
    public static final DeferredBlock<FenceGateBlock> GINGER_FENCE_GATE = registerBlock("ginger_fence_gate",
            (properties) -> new FenceGateBlock(WoodType.CHERRY,BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)
                    .setId(ResourceKey.create(Registries.BLOCK,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "ginger_fence_gate")))));
    // Sugar
    public static final DeferredBlock<Block> SUGAR_BLOCK = registerBlock("sugar_block",
            (properties) -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)
                    .setId(ResourceKey.create(Registries.BLOCK,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "sugar_block")))));
    public static final DeferredBlock<StairBlock> SUGAR_STAIRS = registerBlock("sugar_stairs",
            (properties) -> new StairBlock(ModBlocks.SUGAR_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)
                            .setId(ResourceKey.create(Registries.BLOCK,
                                    Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "sugar_stairs")))));
    public static final DeferredBlock<SlabBlock> SUGAR_SLAB = registerBlock("sugar_slab",
            (properties) -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)
                    .setId(ResourceKey.create(Registries.BLOCK,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "sugar_slab")))));
    public static final DeferredBlock<FenceBlock> SUGAR_FENCE = registerBlock("sugar_fence",
            (properties) -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)
                    .setId(ResourceKey.create(Registries.BLOCK,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "sugar_fence")))));
    public static final DeferredBlock<FenceGateBlock> SUGAR_FENCE_GATE = registerBlock("sugar_fence_gate",
            (properties) -> new FenceGateBlock(WoodType.CHERRY,BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)
                    .setId(ResourceKey.create(Registries.BLOCK,
                            Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "sugar_fence_gate")))));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(name, (properties) -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }
}
