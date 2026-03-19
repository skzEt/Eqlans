package net.skzEt.eqlans.registries;

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

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Streamer.MOD_ID);

    public static final DeferredBlock<Block> TWITCH_ORE = registerBlock("twitch_ore",
            () -> new DropExperienceBlock(UniformInt.of(1, 2),BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DEEPSLATE_TWITCH_ORE = registerBlock("deepslate_twitch_ore",
            () -> new DropExperienceBlock(UniformInt.of(1, 2), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                    .strength(2f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> TWITCH_BLOCK = registerBlock("twitch_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                    .sound(SoundType.ANVIL)));
    // Ginger
    public static final DeferredBlock<Block> GINGER_BLOCK = registerBlock("ginger_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final DeferredBlock<StairBlock> GINGER_STAIRS = registerBlock("ginger_stairs",
            () -> new StairBlock(ModBlocks.GINGER_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final DeferredBlock<DoorBlock> GINGER_DOOR = registerBlock("ginger_door",
            () -> new DoorBlock(BlockSetType.CHERRY, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final DeferredBlock<SlabBlock> GINGER_SLAB = registerBlock("ginger_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final DeferredBlock<FenceBlock> GINGER_FENCE = registerBlock("ginger_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final DeferredBlock<FenceGateBlock> GINGER_FENCE_GATE = registerBlock("ginger_fence_gate",
            () -> new FenceGateBlock(WoodType.CHERRY,BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    // Sugar
    public static final DeferredBlock<Block> SUGAR_BLOCK = registerBlock("sugar_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final DeferredBlock<StairBlock> SUGAR_STAIRS = registerBlock("sugar_stairs",
            () -> new StairBlock(ModBlocks.SUGAR_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final DeferredBlock<SlabBlock> SUGAR_SLAB = registerBlock("sugar_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final DeferredBlock<FenceBlock> SUGAR_FENCE = registerBlock("sugar_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final DeferredBlock<FenceGateBlock> SUGAR_FENCE_GATE = registerBlock("sugar_fence_gate",
            () -> new FenceGateBlock(WoodType.CHERRY,BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));



    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
