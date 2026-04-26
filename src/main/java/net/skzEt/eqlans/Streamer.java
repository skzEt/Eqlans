package net.skzEt.eqlans;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.skzEt.eqlans.registries.*;
import net.skzEt.eqlans.loot.ModLootModifiers;
import net.skzEt.eqlans.particle.HolyMantleParticles;
import net.skzEt.eqlans.sound.ModSounds;
import net.skzEt.eqlans.worldgen.ModStructurePlacement;

@Mod(Streamer.MOD_ID)
public class Streamer
{
    public static final String MOD_ID = "eqlans";

    public Streamer(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);

        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);

        ModLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
        ModStructures.STRUCTURES.register(modEventBus);
        ModStructurePlacement.STRUCTURE_PLACEMENT_TYPE.register(modEventBus);

        ModTriggerType.TRIGGER_TYPES.register(modEventBus);

        ModParticles.PARTICLE_TYPE.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);

        ModCreativeModTabs.CREATIVE_MODE_TABS.register(modEventBus);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {}

    private void addCreative(BuildCreativeModeTabContentsEvent event) {}

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}

    @EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void registerParticleProvider(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(ModParticles.HOLY_MANTLE_PARTICLE.get(), HolyMantleParticles.Provider::new);
        }

        @SubscribeEvent
        public static void onClientSetup(FMLCommonSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.GINGER_DOOR.get(), ChunkSectionLayer.CUTOUT);
        }
    }
}
