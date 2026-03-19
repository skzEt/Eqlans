package net.skzEt.eqlans.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import net.skzEt.eqlans.Streamer;
import net.skzEt.eqlans.registries.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {

    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_) {
        super(p_275343_, p_275729_, Streamer.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.GLASSES.get(),
                        ModItems.DRAKE_PENDANT.get(),
                        ModItems.STINTIK_HELMET.get(),
                        ModItems.BOXERS.get(),
                        ModItems.DND.get());
    }
}
