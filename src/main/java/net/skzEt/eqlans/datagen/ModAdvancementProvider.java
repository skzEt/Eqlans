package net.skzEt.eqlans.datagen;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.skzEt.eqlans.Streamer;
import net.skzEt.eqlans.advancement.StoneAxeInstance;
import net.skzEt.eqlans.registries.ModItems;
import net.skzEt.eqlans.registries.ModTriggerType;

import java.util.Optional;
import java.util.function.Consumer;

public class ModAdvancementProvider implements AdvancementSubProvider {
    @Override
    public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer) {
        var itemLookup = provider.lookupOrThrow(Registries.ITEM);

        AdvancementHolder root = Advancement.Builder.advancement()
                .display(
                        ModItems.TWITCH_DIAMOND,
                        Component.translatable("eqlans.advancement.eqlans"),
                        Component.translatable("eqlans.advancement.eqlan_desc"),
                        Identifier.fromNamespaceAndPath(Streamer.MOD_ID,"gui/advancements/backgrounds/eqlans"),
                        AdvancementType.TASK,
                        true, true, false
                )
                .addCriterion("has_twitch_diamond",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TWITCH_DIAMOND)
                )
                .save(consumer, "eqlans:root");

        AdvancementHolder craftAxe = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        Items.COBBLESTONE,
                        Component.translatable("eqlans.advancement.stone_axe_craft"),
                        Component.translatable("eqlans.advancement.stone_axe_craft_desc"),
                        null,
                        AdvancementType.TASK,
                        true, true, false
                )
                .addCriterion("has_stone_axe",
                        InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE_AXE)
                )
                .save(consumer, "eqlans:stone_axe");

        AdvancementHolder killDragon = Advancement.Builder.advancement()
                .parent(craftAxe)
                .display(
                        Items.STONE_AXE,
                        Component.translatable("eqlans.advancement.stone_axe"),
                        Component.translatable("eqlans.advancement.stone_axe_desc"),
                        null,
                        AdvancementType.CHALLENGE,
                        true, true, false
                )
                .addCriterion("stone_axe_kill",
                        ModTriggerType.STONE_AXE_TRIGGER.get().createCriterion(
                                new StoneAxeInstance(
                                        Optional.empty(),
                                        ItemPredicate.Builder.item().of(itemLookup, Items.STONE_AXE)
                                                .build()
                                )
                        )
                )
                .save(consumer, "eqlans:stone_axe_dragon");
    }
}
