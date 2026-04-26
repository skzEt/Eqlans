package net.skzEt.eqlans.registries;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.skzEt.eqlans.Streamer;
import net.skzEt.eqlans.worldgen.structures.EarthStructures;

public class ModStructures {
    public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, Streamer.MOD_ID);

    public static final DeferredHolder<StructureType<?>, StructureType<EarthStructures>> EARTH_STRUCTURES = STRUCTURES.register("earth_structures", () -> explicitStructureTypeTyping(EarthStructures.CODEC));

    private static <T extends Structure> StructureType<T> explicitStructureTypeTyping(MapCodec<T> structureCodec) {
        return () -> structureCodec;
    }
}
