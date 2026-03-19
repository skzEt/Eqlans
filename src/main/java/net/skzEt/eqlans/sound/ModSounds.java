package net.skzEt.eqlans.sound;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.skzEt.eqlans.Streamer;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Streamer.MOD_ID);
    public static final Supplier<SoundEvent> OM_NOM_USED = registerSoundEvent("om_nom_used");
    public static final Supplier<SoundEvent> OM_NOM_EXPLODED = registerSoundEvent("om_nom_exploded");
    public static final Supplier<SoundEvent> HOLY_MANTLE = registerSoundEvent("holy_mantle_used");
    public static final Supplier<SoundEvent> HOLY_SOUND = registerSoundEvent("holy_sound");

    public static final Supplier<SoundEvent> BIRD = registerSoundEvent("bird");
    public static final Supplier<SoundEvent> INVALID = registerSoundEvent("invalid");
    public static final Supplier<SoundEvent> OKROHKA = registerSoundEvent("okrohka");

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(Streamer.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }
}
