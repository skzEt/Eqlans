package net.skzEt.eqlans.registries;

import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.skzEt.eqlans.Streamer;

import net.skzEt.eqlans.advancement.StoneAxeTrigger;

import java.util.function.Supplier;

public class ModTriggerType {
    public static final DeferredRegister<CriterionTrigger<?>> TRIGGER_TYPES =
            DeferredRegister.create(Registries.TRIGGER_TYPE, Streamer.MOD_ID);

    public static final Supplier<StoneAxeTrigger> STONE_AXE_TRIGGER =
            TRIGGER_TYPES.register("stone_axe_trigger", StoneAxeTrigger::new);
}
