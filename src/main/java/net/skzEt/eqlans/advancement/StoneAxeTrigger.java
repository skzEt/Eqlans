package net.skzEt.eqlans.advancement;

import com.mojang.serialization.Codec;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.skzEt.eqlans.registries.ModTriggerType;

import java.util.Optional;
import java.util.function.Predicate;

public class StoneAxeTrigger extends SimpleCriterionTrigger<StoneAxeInstance> {

    @Override
    public Codec<StoneAxeInstance> codec() {
        return StoneAxeInstance.CODEC;
    }

    @Override
    public Criterion<StoneAxeInstance> createCriterion(StoneAxeInstance triggerInstance) {
        return super.createCriterion(triggerInstance);
    }

    public void trigger(ServerPlayer player, ItemStack stack) {
        this.trigger(player,
                triggerInstance -> triggerInstance.matches(stack)
        );
    }
}
