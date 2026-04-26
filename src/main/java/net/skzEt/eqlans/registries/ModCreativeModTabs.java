package net.skzEt.eqlans.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.skzEt.eqlans.Streamer;

import java.util.function.Supplier;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Streamer.MOD_ID);

    public static final Supplier<CreativeModeTab> STREAMER_TAB = CREATIVE_MODE_TABS.register("streamer_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TWITCH_DIAMOND.get()))
                    .title(Component.translatable("creativetab.streamer_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        // Twitch
                        output.accept(ModBlocks.TWITCH_BLOCK.get());
                        output.accept(ModBlocks.TWITCH_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_TWITCH_ORE.get());
                        output.accept(ModItems.TWITCH_DIAMOND.get());
                        // Streamers
                        output.accept(ModItems.STINTOCOIN.get());
                        output.accept(ModItems.DUMPLING_SUN.get());
                        output.accept(ModItems.BALL_OF_DRAKE.get());
                        output.accept(ModItems.MAZELLOVVV_COOKIE.get());
                        // Items
                        output.accept(ModItems.DUMPLING.get());
                        output.accept(ModItems.SCHOOL_BOOT.get());
                        output.accept(ModItems.COIN_ALLOY.get());
                        output.accept(ModItems.DICE.get());
                        // Custom Items
                        output.accept(ModItems.OM_NOM.get());
                        output.accept(ModItems.SUPER_OM_NOM.get());
                        output.accept(ModItems.HOLY_MANTLE.get());
                        output.accept(ModItems.CLOSED_DND_BOOK.get());
                        output.accept(ModItems.DND_BOOK.get());
                        output.accept(ModItems.MAZELLOVVV_MICROPHONE.get());
                        // Armor
                        output.accept(ModItems.GLASSES.get());
                        output.accept(ModItems.STINTIK_HELMET.get());
                        output.accept(ModItems.DND_CHESTPLATE.get());
                        output.accept(ModItems.DRAKE_PENDANT.get());
                        // Weapons
                        output.accept(ModItems.TWITCH_SWORD.get());
                        output.accept(ModItems.TWITCH_AXE.get());
                        output.accept(ModItems.TWITCH_PICKAXE.get());
                        output.accept(ModItems.TWITCH_SHOVEL.get());
                        output.accept(ModItems.TWITCH_HOE.get());
                        // Food
                        output.accept(ModItems.OVERCOOKED_DUMPLING.get());
                        // Ginger Items
                        output.accept(ModItems.MAZELLOVVV_COOKIE.get());
                        output.accept(ModBlocks.GINGER_BLOCK.get());
                        output.accept(ModBlocks.GINGER_STAIRS.get());
                        output.accept(ModBlocks.GINGER_SLAB.get());
                        output.accept(ModBlocks.GINGER_FENCE.get());
                        output.accept(ModBlocks.GINGER_FENCE_GATE.get());
                        output.accept(ModBlocks.GINGER_DOOR.get());
                        // Sugar
                        output.accept(ModBlocks.SUGAR_BLOCK.get());
                        output.accept(ModBlocks.SUGAR_STAIRS.get());
                        output.accept(ModBlocks.SUGAR_SLAB.get());
                        output.accept(ModBlocks.SUGAR_FENCE.get());
                        output.accept(ModBlocks.SUGAR_FENCE_GATE.get());
                    })
                    .build());
}


