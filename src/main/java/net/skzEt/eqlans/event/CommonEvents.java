package net.skzEt.eqlans.event;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.pig.Pig;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.EntityLeaveLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import net.skzEt.eqlans.Streamer;

import java.util.*;

@EventBusSubscriber(modid = Streamer.MOD_ID)
public class CommonEvents {
    public static UUID playerUUID;

    public static Vec3 playerPosition;

    public static List<WaitAction> queue = new LinkedList<>();

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            try {
                playerUUID = player.getUUID();
            } catch (IllegalArgumentException e) {
                System.out.println("Player UUID in null");
            }
            player.getPersistentData().putBoolean("used", false);
            setDefault();
        }
    }

    @SubscribeEvent
    public static void onEntityLeaveLevel(EntityLeaveLevelEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            player.getPersistentData().putBoolean("used", false);
            setDefault();
        }
    }

    private static void spawnTnt(Player pPlayer, Level pLevel) {
        Vec3 playerPos = pPlayer.position();
        PrimedTnt tnt = EntityType.TNT.create(pLevel, EntitySpawnReason.EVENT);
        Pig pig = EntityType.PIG.create(pLevel, EntitySpawnReason.EVENT);

        tnt.setPos(playerPos.x, playerPos.y + 15, playerPos.z);
        pig.setPos(playerPos.x, playerPos.y + 10, playerPos.z);

        pLevel.addFreshEntity(tnt);
        pLevel.addFreshEntity(pig);

    }
    private static int ticks = 0;
    private static int spawnedTNT = 0;
    private static boolean wait = true;
    private static int timer = 5;

    private static void setDefault() {
        timer = 5;
        wait = true;
        spawnedTNT = 0;
    }

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Pre event) {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();


        if (server == null || playerUUID == null) return;

        ServerPlayer player = server.getPlayerList().getPlayer(playerUUID);
        if (player == null) return;

        playerPosition = player.position();

        for (WaitAction waitAction : queue) {
            if (waitAction.ticks > 0) {
                waitAction.ticks--;
            }
            if (waitAction.ticks == 0) {
                waitAction.action.run();
                queue.remove(waitAction);
            }
        }

        if (player.getPersistentData().getBoolean("used").get()) {
            ticks++;
            if (ticks >= 20) {
                ticks = 0;
                if (wait) {
                    if (timer <= 0) {
                        wait = false;
                        return;
                    }
                    player.sendSystemMessage(Component.translatable("dastreamers.message.tnt1", timer), true);
                    timer--;
                    return;
                }
                spawnTnt(player, player.level());
                spawnedTNT++;
                player.sendSystemMessage(Component.translatable("dastreamers.message.tnt", 25 - spawnedTNT), true);
            }
            if (spawnedTNT == 25) {
                player.getPersistentData().putBoolean("used", false);
                setDefault();
            }
        }

    }

    @SubscribeEvent
    public static void onLivingHurtEvent(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            if (player.getPersistentData().getBoolean("abilityUsed_3").get()) {
                if (player.isFallFlying()) {
                    event.setCanceled(true);
                    player.getPersistentData().putBoolean("abilityUsed_3", false);
                }
            }
        }
    }
}
