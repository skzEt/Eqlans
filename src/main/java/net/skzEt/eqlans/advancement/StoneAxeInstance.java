package net.skzEt.eqlans.advancement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import net.minecraft.world.item.ItemStack;
import net.skzEt.eqlans.registries.ModTriggerType;

import java.util.Optional;

public record StoneAxeInstance(Optional<ContextAwarePredicate> player, ItemPredicate predicate)
        implements SimpleCriterionTrigger.SimpleInstance {

    public static final Codec<StoneAxeInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(StoneAxeInstance::player),
        ItemPredicate.CODEC.fieldOf("item").forGetter(StoneAxeInstance::predicate))
            .apply(instance, StoneAxeInstance::new));

    public static Criterion<StoneAxeInstance> instance(ContextAwarePredicate player, ItemPredicate item) {
        return ModTriggerType.STONE_AXE_TRIGGER.get().createCriterion(new StoneAxeInstance(Optional.of(player), item));
    }

    public boolean matches(ItemStack stack) {
        return this.predicate.test(stack);
    }
}
