package net.skzEt.eqlans.event;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;
import net.skzEt.eqlans.Streamer;
import net.skzEt.eqlans.sound.ModSounds;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

@EventBusSubscriber(modid = Streamer.MOD_ID)
public class AxeStoneEvent {
    public static List<Map.Entry<String, Supplier<SoundEvent>>> TEXTS = List.of(
            Map.entry("eqlans.stone_axe_0", ModSounds.JOPA_0),
            Map.entry("eqlans.stone_axe_1", ModSounds.JOPA_1),
            Map.entry("eqlans.stone_axe_2", ModSounds.JOPA_2),
            Map.entry("eqlans.stone_axe_3", ModSounds.JOPA_3),
            Map.entry("eqlans.stone_axe_4", ModSounds.JOPA_4),
            Map.entry("eqlans.stone_axe_5", ModSounds.JOPA_4),
            Map.entry("eqlans.stone_axe_6", ModSounds.JOPA_3)
    );
    @SubscribeEvent
    public static void onAttackEntity(CriticalHitEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (event.isCriticalHit() && player.getMainHandItem().is(Items.STONE_AXE)) {
            player.sendSystemMessage(GetText(player));
        }
    }

    private static Component GetText(ServerPlayer player) {
        var entry = TEXTS.get(player.getRandom().nextInt(TEXTS.size()));

        String key = player.getRandom().nextBoolean()
                ? entry.getKey() : entry.getKey() + "_v";
        Component text = Component.translatable(key, player.getName());

        player.level().playSound(null, player.blockPosition(),
                entry.getValue().get(), player.getSoundSource(), 1, 1);
        return text;
    }
}
