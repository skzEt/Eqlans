package net.skzEt.eqlans.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import net.skzEt.eqlans.Streamer;
import net.skzEt.eqlans.registries.ModItems;
import net.skzEt.eqlans.loot.AddItemModifier;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {


    public ModGlobalLootModifiersProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Streamer.MOD_ID);
    }

    @Override
    protected void start() {
        add("boxers_from_ruined_portal",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("chests/ruined_portal")).build(),
                        LootItemRandomChanceCondition.randomChance(0.4f).build()}, ModItems.BOXERS.get()));

        add("stintocoin_from_desert_pyramid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.3f).build()}, ModItems.STINTOCOIN.get()));
        add("ball_of_drake_from_desert_pyramid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()}, ModItems.DRAKE_PENDANT.get()));
        add("dumpling_sun_from_desert_pyramid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.3f).build()}, ModItems.DUMPLING_SUN.get()));
        add("mazellovvv_cookie_from_desert_pyramid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.3f).build()}, ModItems.MAZELLOVVV_COOKIE.get()));
    }
}
