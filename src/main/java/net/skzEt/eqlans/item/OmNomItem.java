package net.skzEt.eqlans.item;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.skzEt.eqlans.client.ModKeyboardHelper;
import net.skzEt.eqlans.registries.ModItems;
import net.skzEt.eqlans.sound.ModSounds;
import net.skzEt.eqlans.util.ModTags;

import java.util.List;
import java.util.function.Consumer;

public class OmNomItem extends Item {
    int tickCount = 5*20;
    public OmNomItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide()) {
            BlockPos blockPos = pContext.getClickedPos();
            Level level = pContext.getLevel();
            BlockState state = pContext.getLevel().getBlockState(blockPos);

            if (!isValuableBlock(state)) {
                pContext.getPlayer().getCooldowns().addCooldown(ModItems.OM_NOM.toStack(), tickCount);
                pContext.getLevel().playSeededSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                        ModSounds.OM_NOM_USED.get(), SoundSource.BLOCKS, 1f, 1f, 0);
                level.destroyBlock(blockPos, false);

            } else if (isOreBlock(state)) {
                pContext.getPlayer().getCooldowns().addCooldown(ModItems.OM_NOM.toStack(), tickCount);
                pContext.getLevel().playSeededSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                        ModSounds.OM_NOM_USED.get(), SoundSource.BLOCKS, 1f, 1f, 0);
                level.destroyBlock(blockPos, true);
                pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                        pContext.getItemInHand().getEquipmentSlot());
            } else {
                pContext.getLevel().playSeededSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                        ModSounds.OM_NOM_EXPLODED.get(), SoundSource.BLOCKS, 1f, 1f, 0);
                pContext.getLevel().explode(null, pContext.getPlayer().getX(), pContext.getPlayer().getY(),
                        pContext.getPlayer().getZ(), 15, Level.ExplosionInteraction.BLOCK);
                pContext.getItemInHand().hurtAndBreak(600, pContext.getPlayer(),
                        pContext.getItemInHand().getEquipmentSlot());
            }

        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        if (ModKeyboardHelper.isHoldingShift()) {
            tooltipAdder.accept(Component.translatable("eqlans.description.om_nom")
                    .setStyle(Style.EMPTY).withColor(TextColor.fromRgb(0xFF0000).getValue()));
        }
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(ModTags.Blocks.OM_NOM_EXPLODE);
    }
    private boolean isOreBlock(BlockState state) {
        return state.is(ModTags.Blocks.OM_NOM_ORE);
    }
}
