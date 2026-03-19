package net.skzEt.eqlans.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.skzEt.eqlans.client.ModKeyboardHelper;
import net.skzEt.eqlans.event.CommonEvents;
import net.skzEt.eqlans.event.WaitAction;
import net.skzEt.eqlans.util.RayCast;

import java.util.*;
import java.util.function.Consumer;

public class DNDBook extends Item {
    public DNDBook(Properties pProperties) {
        super(pProperties);
    }

    private int CURRENT_ITEM_ID = 0;
    private int MAX_ABILITIES = 3;

    private int CUBE;

    private List<String> DESCRIPTION = List.of(
            "eqlans.message.ability_1",
            "eqlans.message.ability_2",
            "eqlans.message.ability_3",
            "eqlans.message.ability_4"
    );

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            MinecraftServer server = level.getServer();
            ServerPlayer serverPlayer = server.getPlayerList().getPlayer(CommonEvents.playerUUID);

            if (ModKeyboardHelper.isHoldingShift()) {
                CURRENT_ITEM_ID++;

                if (CURRENT_ITEM_ID > MAX_ABILITIES)
                    CURRENT_ITEM_ID = 0;

                serverPlayer.sendSystemMessage(
                        Component.translatable(DESCRIPTION.get(CURRENT_ITEM_ID)), true);
                return InteractionResult.FAIL;
            }

            if (!player.getCooldowns().isOnCooldown(player.getItemInHand(hand))) {
                switch (CURRENT_ITEM_ID) {
                    case 0:
                        ExplodeRaycast(getD6(), serverPlayer, player, level, hand);
                        break;
                    case 1:
                        Teleport(getD10(), serverPlayer, player, level, hand);
                        break;
                    case 2:
                        Levitation(getD4(), serverPlayer, player, hand);
                        break;
                    case 3:
                        DeathRay(getD10(), serverPlayer, player, level, hand);
                        break;
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    private void DeathRay(int i, ServerPlayer player, Player pPlayer, Level pLevel, InteractionHand pUsedHand) {
        CUBE = i;
        float damage = 0;
        Entity entity = RayCast.rayEntity(pPlayer, pLevel, 150);
        if (CUBE <= 3) {
            player.sendSystemMessage(Component.translatable("eqlans.message.cube", CUBE), true);
        } else if (CUBE > 3 && CUBE < 10) {
            player.sendSystemMessage(Component.translatable("eqlans.message.cube", CUBE), true);
            damage = CUBE * 2;

        } else if (CUBE == 10) {
            player.sendSystemMessage(Component.translatable("eqlans.message.max_cube"), true);
            damage = 50;
        }

        if (entity != null) {
            pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand), 5*20);
            Holder<DamageType> type = Holder.direct(pLevel.registryAccess().lookupOrThrow(
                    Registries.DAMAGE_TYPE).get(DamageTypes.MAGIC).orElseThrow().value());
            entity.hurt(new DamageSource(type), damage);
        } else {
            pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand), 10);
        }
    }

    private void Levitation(int i, ServerPlayer player, Player pPlayer, InteractionHand pUsedHand) {
        CUBE = i;
        if (CUBE > 1 && CUBE < 4) {
            pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand), 10*20);
            player.sendSystemMessage(Component.translatable("eqlans.message.cube", CUBE), true);
            pPlayer.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 5*20, 2));
        } else if (CUBE == 4) {
            pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand), 20*20);
            player.sendSystemMessage(Component.translatable("eqlans.message.max_cube"), true);
            if (!pPlayer.isCreative()) {
                player.getAbilities().flying = true;
                player.getAbilities().mayfly = true;
                player.onUpdateAbilities();

                new WaitAction(15*20, () -> {
                    player.getAbilities().flying = false;
                    player.getAbilities().mayfly = false;
                    player.onUpdateAbilities();
                });
            }
            else
                player.sendSystemMessage(Component.translatable("eqlans.message.inCreative"));
        }
    }

    private void Teleport(int i, ServerPlayer player, Player pPlayer, Level pLevel, InteractionHand pUsedHand) {
        CUBE = i;
        pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand), 3*20);
        if (CUBE > 4) {
            player.sendSystemMessage(Component.translatable("eqlans.message.cube", CUBE), true);
            BlockPos blockPos = RayCast.rayBlock(pPlayer, pLevel, 250);
            BlockPos safePos = findSafeTeleportPos(pLevel, blockPos);
            pPlayer.setPos(Vec3.atCenterOf(safePos));
        } else if (CUBE >= 2 && CUBE < 4) {
            player.sendSystemMessage(Component.translatable("eqlans.message.cube", CUBE), true);
            BlockPos blockPos = RayCast.rayBlock(pPlayer, pLevel, 250);
            BlockPos safePos = findSafeTeleportPos(pLevel, blockPos);
            Vec3i newPos = new Vec3i(safePos.getX() + new Random().nextInt(-10, 10), safePos.getY() + new Random().nextInt(-3, 3), safePos.getZ() + new Random().nextInt(-10, 10));
            pPlayer.setPos(Vec3.atCenterOf(newPos));
        } else if (CUBE == 1) {
            player.sendSystemMessage(Component.translatable("eqlans.message.failed_cube", CUBE), true);
            BlockPos blockPos = RayCast.rayBlock(pPlayer, pLevel, 250);
            BlockPos safePos = findSafeTeleportPos(pLevel, blockPos);
            Vec3i newPos = new Vec3i(safePos.getX() + new Random().nextInt(-100, 100), safePos.getY() + new Random().nextInt(-5, 10), safePos.getZ() + new Random().nextInt(-100, 100));
            pPlayer.setPos(Vec3.atCenterOf(newPos));
        }
    }

    private void ExplodeRaycast(int i, ServerPlayer player, Player pPlayer, Level pLevel, InteractionHand pUsedHand) {
        CUBE = i;
        BlockPos blockPos = RayCast.rayBlock(pPlayer, pLevel, 200);
        pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand), 5*20);
        if (CUBE > 2 && CUBE < 6) {
            // Small explode
            player.sendSystemMessage(Component.translatable("eqlans.message.cube", CUBE), true);

            pLevel.explode(pPlayer, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                    15, Level.ExplosionInteraction.BLOCK);
        } else if (CUBE == 6) {
            // Big explode
            player.sendSystemMessage(Component.translatable("eqlans.message.max_cube"), true);

            pLevel.explode(pPlayer, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                    35, Level.ExplosionInteraction.BLOCK);
        } else if (CUBE == 2) {
            // No damage player explode
            player.sendSystemMessage(Component.translatable("eqlans.message.cube", CUBE), true);

            pLevel.explode(pPlayer, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                    5, Level.ExplosionInteraction.BLOCK);
        } else if (CUBE == 1) {
            // Player explode
            player.sendSystemMessage(Component.translatable("eqlans.message.failed_cube", CUBE), true);

            pLevel.explode(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                    5, Level.ExplosionInteraction.BLOCK);
        }
    }

    private BlockPos findSafeTeleportPos(Level level, BlockPos pos) {
        int maxY = level.getMaxY();
        for (int y = pos.getY(); y < maxY - 1; y++) {
            BlockPos below = new BlockPos(pos.getX(), y, pos.getZ());
            BlockPos above = below.above();

            if (level.getBlockState(above).isAir()) {
                return above; // Возвращаем позицию, где блок пуст
            }
        }
        return pos; // Если ничего не нашли — вернем исходную
    }

    private static int getD6() {
        return new Random().nextInt(1, 7);
    }
    private static int getD10() {
        return new Random().nextInt(1, 11);
    }
    private static int getD4() {
        return new Random().nextInt(1, 5);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        if (ModKeyboardHelper.isHoldingShift()) {
            tooltipAdder.accept(
                    Component.translatable(DESCRIPTION.get(CURRENT_ITEM_ID))
                            .setStyle(Style.EMPTY));
        }
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
    }
}
