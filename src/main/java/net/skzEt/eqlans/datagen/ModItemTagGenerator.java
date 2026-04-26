package net.skzEt.eqlans.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import net.skzEt.eqlans.Streamer;
import net.skzEt.eqlans.registries.ModItems;
import net.skzEt.eqlans.util.ModTags;

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
                        ModItems.DND_CHESTPLATE.get());

        this.tag(ModTags.Items.STEAMER_REPAIRABLE)
                .add(ModItems.TWITCH_DIAMOND.get());
        this.tag(ModTags.Items.SCHOOL_REPAIRABLE)
                .add(ModItems.SCHOOL_BOOT.get());
        this.tag(ModTags.Items.DND_REPAIRABLE)
                .add(ModItems.DICE.get());
    }
}
