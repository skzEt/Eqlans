package net.skzEt.eqlans.event;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import net.skzEt.eqlans.Streamer;
import net.skzEt.eqlans.registries.ModItems;
import net.skzEt.eqlans.registries.ModParticles;
import net.skzEt.eqlans.sound.ModSounds;

import java.util.Random;
import java.util.UUID;

@EventBusSubscriber(modid = Streamer.MOD_ID)
public class HolyMantleHurtEvent {

    public static UUID playerUUID;

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            playerUUID = player.getUUID();
        }
    }

    @SubscribeEvent
    public static void holyMantle(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            ServerLevel level = player.level();

            if (!level.isClientSide()) {
                int effectTime = 30 * 20;
                int tickCount = 60 * 20;
                if (player.getOffhandItem().getItem() == ModItems.HOLY_MANTLE.get() || player.getMainHandItem().getItem() == ModItems.HOLY_MANTLE.get()) {
                    if (player.isInLava() && !isCooldown()) {
                        Cooldown(tickCount);
                        event.setCanceled(true);
                        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, effectTime));
                        playSoundParticleAnimation();
                    } else if (player.fallDistance >= 4f && !isCooldown()) {
                        Cooldown(tickCount);
                        event.setCanceled(true);
                        playSoundParticleAnimation();
                    } else if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() == Items.ELYTRA && player.isFallFlying() && !isCooldown()) {
                        Cooldown(tickCount);
                        event.setCanceled(true);
                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, effectTime, 2));
                        playSoundParticleAnimation();
                    } else if (player.isInWall() && !isCooldown()) {
                        Cooldown(tickCount);
                        level.explode(player, player.getX(),  player.getY(),
                                player.getZ(), new Random().nextInt(3, 5), Level.ExplosionInteraction.BLOCK);
                        event.setCanceled(true);
                        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, effectTime));
                        player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, effectTime));
                        playSoundParticleAnimation();
                    } else if (player.isAttackable() && !isCooldown() && !player.isInWater()) {
                        Cooldown(tickCount);
                        event.setCanceled(true);
                        player.removeAllEffects();
                        player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, effectTime, 50));
                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, effectTime, 2));
                        player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, effectTime, 2));
                        playSoundParticleAnimation();
                    } else if (player.isInWater() && !isCooldown()) {
                        Cooldown(tickCount);
                        event.setCanceled(true);
                        player.removeAllEffects();
                        player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, effectTime));
                        player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, effectTime, 4));
                        playSoundParticleAnimation();
                    }
                }

            }
        }
    }

    private static void Cooldown(int pTick) {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        ServerPlayer player = server.getPlayerList().getPlayer(playerUUID);
        player.getCooldowns().addCooldown(ModItems.HOLY_MANTLE.toStack(), pTick);
    }
    private static boolean isCooldown() {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        ServerPlayer player = server.getPlayerList().getPlayer(playerUUID);
        return player.getCooldowns().isOnCooldown(ModItems.HOLY_MANTLE.toStack());
    }
    private static void spawnParticle(ServerLevel level, ServerPlayer pos) {
        level.sendParticles(ModParticles.HOLY_MANTLE_PARTICLE.get(),
                pos.getX(), pos.getY(), pos.getZ(), 300,
                0, 0,0, 1.4);
    }
    private static void playSoundParticleAnimation() {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        ServerPlayer player = server.getPlayerList().getPlayer(playerUUID);
        ServerLevel level = player.level();

        player.level().playSound(null,
                player.getX(), player.getY(), player.getZ(),
                ModSounds.HOLY_MANTLE.get(), SoundSource.PLAYERS, 1f, 1f);
        spawnParticle(level, player);
        Minecraft.getInstance().gameRenderer.displayItemActivation(new ItemStack(ModItems.HOLY_MANTLE.get()));
    }
}
