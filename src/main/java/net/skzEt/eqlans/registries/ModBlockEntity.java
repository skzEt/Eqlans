package net.skzEt.eqlans.registries;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.skzEt.eqlans.Streamer;


public class ModBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENITY =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Streamer.MOD_ID);


}
