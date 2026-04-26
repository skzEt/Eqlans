package net.skzEt.eqlans.datagen;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.skzEt.eqlans.Streamer;
import net.skzEt.eqlans.registries.ModBlocks;
import net.skzEt.eqlans.registries.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    private static final List<ItemLike> TWITCH_SMELTABLES = List.of(
            ModBlocks.TWITCH_ORE.get(),
            ModBlocks.DEEPSLATE_TWITCH_ORE.get()
    );
    private static final List<ItemLike> STINT_SMELTABLES = List.of(
            ModItems.STINTOCOIN.get()
    );
    private static final List<ItemLike> DUMPLING_SMELTABLES = List.of(
            ModItems.DUMPLING.get()
    );

    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    public static class Runner extends RecipeProvider.Runner {

        protected Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new ModRecipeProvider(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "My Recipes";
        }
    }

    @Override
    protected void buildRecipes() {
        oreSmelting(TWITCH_SMELTABLES, RecipeCategory.MISC, ModItems.TWITCH_DIAMOND.get(), 0.1f, 200, "twitch");
        oreBlasting(TWITCH_SMELTABLES, RecipeCategory.MISC, ModItems.TWITCH_DIAMOND.get(), 0.1f, 100, "twitch");

        oreSmelting(STINT_SMELTABLES, RecipeCategory.MISC, ModItems.COIN_ALLOY.get(), 0f, 300, "stint");
        oreBlasting(STINT_SMELTABLES, RecipeCategory.MISC,ModItems.COIN_ALLOY.get(),0f, 150, "stint");

        oreSmelting(DUMPLING_SMELTABLES, RecipeCategory.MISC, ModItems.OVERCOOKED_DUMPLING.get(), 0f, 450, "dumpling");
        oreCooking(RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, DUMPLING_SMELTABLES,
                RecipeCategory.MISC, ModItems.OVERCOOKED_DUMPLING.get(), 0f, 200,
                "dumpling", "twitch");
        // Twitch Block
        shaped(RecipeCategory.MISC, ModBlocks.TWITCH_BLOCK.get())
                .pattern("TTT")
                .pattern("TTT")
                .pattern("TTT")
                .define('T', ModItems.TWITCH_DIAMOND.get())
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get())).save(output);
        // Twitch Diamond
        shapeless(RecipeCategory.MISC, ModItems.TWITCH_DIAMOND.get(), 9)
                .requires(ModBlocks.TWITCH_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.TWITCH_BLOCK.get()), has(ModBlocks.TWITCH_BLOCK.get())).save(output);
        // Dumpling
        shaped(RecipeCategory.MISC, ModItems.DUMPLING.get())
                .pattern(" W ")
                .pattern("WDW")
                .pattern(" W ")
                .define('W', Items.WHEAT)
                .define('D', ModItems.TWITCH_DIAMOND.get())
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get())).save(output);
        // School Boot
        shaped(RecipeCategory.MISC, ModItems.SCHOOL_BOOT.get())
                .pattern("KK ")
                .pattern("KB ")
                .pattern("KKC")
                .define('K', Items.DRIED_KELP)
                .define('B', Items.BLACK_DYE)
                .define('C', Items.COAL)
                .unlockedBy(getHasName(Items.COAL), has(Items.COAL)).save(output);
        // Stint-o-Coin
        shaped(RecipeCategory.MISC, ModItems.STINTOCOIN.get())
                .pattern(" G ")
                .pattern("GDG")
                .pattern(" G ")
                .define('G', Items.GOLD_NUGGET)
                .define('D', ModItems.TWITCH_DIAMOND.get())
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get())).save(output);
        // Ball of Drake
        shaped(RecipeCategory.MISC, ModItems.BALL_OF_DRAKE.get())
                .pattern(" I ")
                .pattern("IDI")
                .pattern(" I ")
                .define('I', Items.IRON_NUGGET)
                .define('D', ModItems.TWITCH_DIAMOND.get())
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get())).save(output);
        // Drake Pendant
        shaped(RecipeCategory.MISC, ModItems.DRAKE_PENDANT.get())
                .pattern("I I")
                .pattern("I I")
                .pattern(" B ")
                .define('I', Items.IRON_INGOT)
                .define('B', ModItems.BALL_OF_DRAKE.get())
                .unlockedBy(getHasName(ModItems.BALL_OF_DRAKE.get()), has(ModItems.BALL_OF_DRAKE.get())).save(output);
        // Glasses
        shaped(RecipeCategory.MISC, ModItems.GLASSES.get())
                .pattern("C C")
                .pattern("GCG")
                .define('C', Items.COAL)
                .define('G', Items.GLASS)
                .unlockedBy(getHasName(Items.COAL), has(ModItems.TWITCH_DIAMOND.get())).save(output);
        // Dumpling Sun
        shaped(RecipeCategory.MISC, ModItems.DUMPLING_SUN.get())
                .pattern("GD")
                .define('D', ModItems.DUMPLING.get())
                .define('G', ModItems.GLASSES.get())
                .unlockedBy(getHasName(ModItems.DUMPLING.get()), has(ModItems.DUMPLING.get())).save(output);
        // Stint helmet
        shaped(RecipeCategory.MISC, ModItems.STINTIK_HELMET.get())
                .pattern("CSC")
                .pattern("C C")
                .define('C', ModItems.COIN_ALLOY.get())
                .define('S', ModItems.STINTOCOIN.get())
                .unlockedBy(getHasName(ModItems.STINTOCOIN.get()), has(ModItems.STINTOCOIN.get())).save(output);
        // Mzlff Microphone
        shaped(RecipeCategory.MISC, ModItems.MAZELLOVVV_MICROPHONE.get())
                .pattern(" DB")
                .pattern(" CD")
                .pattern("C  ")
                .define('D', ModItems.TWITCH_DIAMOND.get())
                .define('B', ModBlocks.TWITCH_BLOCK.get())
                .define('C', ModItems.MAZELLOVVV_COOKIE.get())
                .unlockedBy(getHasName(ModItems.MAZELLOVVV_COOKIE.get()), has(ModItems.MAZELLOVVV_COOKIE.get())).save(output);
        // Om Nom
        shaped(RecipeCategory.MISC, ModItems.OM_NOM.get())
                .pattern("WSI")
                .pattern("GBD")
                .pattern("AAA")
                .define('W', Items.WOODEN_PICKAXE)
                .define('S', Items.STONE_PICKAXE)
                .define('I', Items.IRON_PICKAXE)
                .define('G', Items.GOLDEN_PICKAXE)
                .define('D', Items.DIAMOND_PICKAXE)
                .define('B', ModBlocks.TWITCH_BLOCK.get())
                .define('A', ModItems.BALL_OF_DRAKE.get())
                .unlockedBy(getHasName(ModItems.BALL_OF_DRAKE.get()), has(ModItems.BALL_OF_DRAKE.get())).save(output);
        // Super Om Nom
        shaped(RecipeCategory.MISC, ModItems.SUPER_OM_NOM.get())
                .pattern("RRR")
                .pattern("IOI")
                .pattern("BBB")
                .define('R', Blocks.REDSTONE_BLOCK)
                .define('I', Items.IRON_INGOT)
                .define('B', Blocks.IRON_BLOCK)
                .define('O', ModItems.OM_NOM.get())
                .unlockedBy(getHasName(ModItems.OM_NOM.get()), has(ModItems.OM_NOM.get())).save(output);
        // Holy Mantle
        shaped(RecipeCategory.MISC, ModItems.HOLY_MANTLE.get())
                .pattern("WTW")
                .pattern("TNT")
                .pattern("CTC")
                .define('W', Items.WITHER_SKELETON_SKULL)
                .define('T', ModBlocks.TWITCH_BLOCK.get())
                .define('N', Items.NETHER_STAR)
                .define('C', ModItems.MAZELLOVVV_COOKIE)
                .unlockedBy(getHasName(ModItems.MAZELLOVVV_COOKIE.get()), has(ModItems.MAZELLOVVV_COOKIE.get())).save(output);
        // Twitch Sword
        shaped(RecipeCategory.MISC, ModItems.TWITCH_SWORD.get())
                .pattern("T")
                .pattern("T")
                .pattern("S")
                .define('T', ModItems.TWITCH_DIAMOND.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get())).save(output);
        // Twitch Axe
        shaped(RecipeCategory.MISC, ModItems.TWITCH_AXE.get())
                .pattern("TT")
                .pattern("TS")
                .pattern(" S")
                .define('T', ModItems.TWITCH_DIAMOND.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get())).save(output);
        // Twitch Pickaxe
        shaped(RecipeCategory.MISC, ModItems.TWITCH_PICKAXE.get())
                .pattern("TTT")
                .pattern(" S ")
                .pattern(" S ")
                .define('T', ModItems.TWITCH_DIAMOND.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get())).save(output);
        // Twitch Shovel
        shaped(RecipeCategory.MISC, ModItems.TWITCH_SHOVEL.get())
                .pattern("T")
                .pattern("S")
                .pattern("S")
                .define('T', ModItems.TWITCH_DIAMOND.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get())).save(output);
        // Twitch Hoe
        shaped(RecipeCategory.MISC, ModItems.TWITCH_HOE.get())
                .pattern("TT")
                .pattern(" S")
                .pattern(" S")
                .define('T', ModItems.TWITCH_DIAMOND.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get())).save(output);
        // Ginger Bread
        shaped(RecipeCategory.MISC, ModItems.MAZELLOVVV_COOKIE.get())
                .pattern("WSW")
                .pattern(" D ")
                .pattern("W W")
                .define('W', Items.WHEAT)
                .define('S', Items.SUGAR)
                .define('D', ModItems.TWITCH_DIAMOND.get())
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get())).save(output);
        // Ginger Block
        shaped(RecipeCategory.MISC, ModBlocks.GINGER_BLOCK.get())
                .pattern("GG")
                .pattern("GG")
                .define('G', ModItems.MAZELLOVVV_COOKIE.get())
                .unlockedBy(getHasName(ModItems.MAZELLOVVV_COOKIE.get()), has(ModItems.MAZELLOVVV_COOKIE.get())).save(output);
        // Ginger Stairs
        shaped(RecipeCategory.MISC, ModBlocks.GINGER_STAIRS.get(), 6)
                .pattern("G  ")
                .pattern("GG ")
                .pattern("GGG")
                .define('G', ModItems.MAZELLOVVV_COOKIE.get())
                .unlockedBy(getHasName(ModItems.MAZELLOVVV_COOKIE.get()), has(ModItems.MAZELLOVVV_COOKIE.get())).save(output);
        // Ginger Door
        shaped(RecipeCategory.MISC, ModBlocks.GINGER_DOOR.get(), 3)
                .pattern("GG")
                .pattern("GG")
                .pattern("GG")
                .define('G', ModItems.MAZELLOVVV_COOKIE.get())
                .unlockedBy(getHasName(ModItems.MAZELLOVVV_COOKIE.get()), has(ModItems.MAZELLOVVV_COOKIE.get())).save(output);
        // Ginger Fence
        shaped(RecipeCategory.MISC, ModBlocks.GINGER_FENCE.get(), 3)
                .pattern("GSG")
                .pattern("GSG")
                .define('S', Items.STICK)
                .define('G', ModItems.MAZELLOVVV_COOKIE.get())
                .unlockedBy(getHasName(ModItems.MAZELLOVVV_COOKIE.get()), has(ModItems.MAZELLOVVV_COOKIE.get())).save(output);
        // Ginger Fence Gate
        shaped(RecipeCategory.MISC, ModBlocks.GINGER_FENCE_GATE.get())
                .pattern("SGS")
                .pattern("SGS")
                .define('S', Items.STICK)
                .define('G', ModItems.MAZELLOVVV_COOKIE.get())
                .unlockedBy(getHasName(ModItems.MAZELLOVVV_COOKIE.get()), has(ModItems.MAZELLOVVV_COOKIE.get())).save(output);
        // Ginger Slab
        shaped(RecipeCategory.MISC, ModBlocks.GINGER_SLAB.get(), 3)
                .pattern("GGG")
                .define('G', ModItems.MAZELLOVVV_COOKIE.get())
                .unlockedBy(getHasName(ModItems.MAZELLOVVV_COOKIE.get()), has(ModItems.MAZELLOVVV_COOKIE.get())).save(output);
        // Sugar Block
        shaped(RecipeCategory.MISC, ModBlocks.SUGAR_BLOCK.get())
                .pattern("SS")
                .pattern("SS")
                .define('S', Items.SUGAR)
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR)).save(output);
        // Sugar Stair
        shaped(RecipeCategory.MISC, ModBlocks.SUGAR_STAIRS.get(), 6)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .define('S', Items.SUGAR)
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR)).save(output);
        // Sugar Block
        shaped(RecipeCategory.MISC, ModBlocks.SUGAR_SLAB.get(), 12)
                .pattern("SSS")
                .define('S', Items.SUGAR)
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR)).save(output);
        // Sugar Fence Gate
        shaped(RecipeCategory.MISC, ModBlocks.SUGAR_FENCE_GATE.get())
                .pattern("SGS")
                .pattern("SGS")
                .define('S', Items.STICK)
                .define('G', Items.SUGAR)
                .unlockedBy(getHasName(ModItems.MAZELLOVVV_COOKIE.get()), has(ModItems.MAZELLOVVV_COOKIE.get())).save(output);
        // Sugar Fence
        shaped(RecipeCategory.MISC, ModBlocks.SUGAR_FENCE.get(), 3)
                .pattern("GSG")
                .pattern("GSG")
                .define('S', Items.STICK)
                .define('G', Items.SUGAR)
                .unlockedBy(getHasName(ModItems.MAZELLOVVV_COOKIE.get()), has(ModItems.MAZELLOVVV_COOKIE.get())).save(output);
        // Closed DND book
        shaped(RecipeCategory.MISC, ModItems.CLOSED_DND_BOOK.get())
                .pattern("UPL")
                .pattern("PDC")
                .pattern("LCU")
                .define('P', Items.PAPER)
                .define('L', Items.LEATHER)
                .define('D', ModItems.TWITCH_DIAMOND.get())
                .define('C', ModItems.DICE.get())
                .define('U', ModItems.DUMPLING_SUN.get())
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get())).save(output);
        // DND book
        shaped(RecipeCategory.MISC, ModItems.DND_BOOK.get())
                .pattern("DWD")
                .pattern("WCW")
                .pattern("DWD")
                .define('D', ModItems.DICE.get())
                .define('W', Items.WITHER_SKELETON_SKULL)
                .define('C', ModItems.CLOSED_DND_BOOK.get())
                .unlockedBy(getHasName(ModItems.CLOSED_DND_BOOK.get()), has(ModItems.CLOSED_DND_BOOK.get())).save(output);
        // Dice
        shaped(RecipeCategory.MISC, ModItems.DICE.get())
                .pattern("WBW")
                .pattern("BDB")
                .pattern("WBW")
                .define('B', Items.BLACK_DYE)
                .define('W', Items.WHITE_DYE)
                .define('D', ModItems.TWITCH_DIAMOND.get())
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get())).save(output);
        // DND Chestplate
        shaped(RecipeCategory.MISC, ModItems.DND_CHESTPLATE.get())
                .pattern("D D")
                .pattern("DDD")
                .pattern("PDP")
                .define('D', ModItems.DICE.get())
                .define('P', ModItems.DUMPLING_SUN.get())
                .unlockedBy(getHasName(ModItems.DUMPLING_SUN.get()), has(ModItems.DUMPLING_SUN.get())).save(output);
    }
}
