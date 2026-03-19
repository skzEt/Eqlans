package net.skzEt.eqlans.registries;


import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.skzEt.eqlans.Streamer;

import java.util.function.Supplier;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPE =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, Streamer.MOD_ID);

    public static final Supplier<SimpleParticleType> HOLY_MANTLE_PARTICLE = PARTICLE_TYPE.register("holy_mantle_particle",
            () -> new SimpleParticleType(true));
}
