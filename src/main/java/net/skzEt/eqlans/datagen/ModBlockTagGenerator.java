package net.skzEt.eqlans.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.skzEt.eqlans.Streamer;
import net.skzEt.eqlans.registries.ModBlocks;
import net.skzEt.eqlans.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Streamer.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Blocks.OM_NOM_EXPLODE)
                .add(Blocks.BEDROCK).addTag(Tags.Blocks.ORES)
                .add(Blocks.OBSIDIAN)
                .add(Blocks.CRYING_OBSIDIAN);
        this.tag(ModTags.Blocks.OM_NOM_ORE)
                .add(Blocks.DIAMOND_ORE)
                .add(Blocks.DEEPSLATE_DIAMOND_ORE)
                .add(Blocks.IRON_ORE)
                .add(Blocks.DEEPSLATE_IRON_ORE)
                .add(Blocks.GOLD_ORE)
                .add(Blocks.DEEPSLATE_GOLD_ORE)
                .add(Blocks.COPPER_ORE)
                .add(Blocks.DEEPSLATE_COPPER_ORE)
                .add(Blocks.COAL_ORE)
                .add(Blocks.DEEPSLATE_COAL_ORE).addTag(Tags.Blocks.ORES);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.TWITCH_ORE.get())
                .add(ModBlocks.TWITCH_BLOCK.get())
                .add(ModBlocks.DEEPSLATE_TWITCH_ORE.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.TWITCH_ORE.get())
                .add(ModBlocks.TWITCH_BLOCK.get())
                .add(ModBlocks.DEEPSLATE_TWITCH_ORE.get());

        this.tag(BlockTags.FENCES)
                .add(ModBlocks.GINGER_FENCE.get())
                .add(ModBlocks.SUGAR_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.GINGER_FENCE_GATE.get())
                .add(ModBlocks.SUGAR_FENCE_GATE.get());


    }
}
