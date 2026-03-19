package net.skzEt.eqlans.item;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.skzEt.eqlans.registries.ModItems;
import net.skzEt.eqlans.sound.ModSounds;

import java.util.Random;

public class MazellovvvMircophoneItem extends Item {
    public MazellovvvMircophoneItem(Properties pProperties) {
        super(pProperties);
    }

    public static ItemLike[] items = new ItemLike[]{
            Items.POTATO,
            Items.COOKED_BEEF,
            Items.COOKED_CHICKEN,
            Items.GOLDEN_APPLE
    };

    @Override
    public InteractionResult use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        int tickCount = 1*60*20;
        if (!pPlayer.getCooldowns().isOnCooldown(ModItems.MAZELLOVVV_MICROPHONE.toStack())) {
            pPlayer.getCooldowns().addCooldown(ModItems.MAZELLOVVV_MICROPHONE.toStack(), tickCount);
            int Random = new Random().nextInt(3);
            if (!pLevel.isClientSide()) {
                if (Random == 0) {
                    pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                            ModSounds.BIRD.get(), SoundSource.PLAYERS, 1f, 1f);
                    pPlayer.getPersistentData().putBoolean("used", true);
                } else if (Random == 1) {
                    int soundTick = 31 * 20;
                    pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                            ModSounds.INVALID.get(), SoundSource.PLAYERS, 1f, 1f);
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, soundTick, 10));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, soundTick, 10));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DARKNESS, soundTick, 10));
                } else if (Random == 2) {
                    pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                            ModSounds.OKROHKA.get(), SoundSource.PLAYERS, 1f, 1f);
                    for (int i = 0; i <= new Random().nextInt(5, 20); i++) {
                        ItemEntity itemEntity = new ItemEntity(pLevel, pPlayer.getX() + new Random().nextFloat(-3, 3), pPlayer.getY() + 1, pPlayer.getZ() + new Random().nextFloat(-3, 3),
                                new ItemStack(items[new Random().nextInt(items.length)]));
                        itemEntity.setDefaultPickUpDelay();
                        pLevel.addFreshEntity(itemEntity);
                    }
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

}
