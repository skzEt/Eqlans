package net.skzEt.eqlans.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ToolMaterial;
import net.skzEt.eqlans.Streamer;
import net.skzEt.eqlans.util.ModTags;

public class ModToolMaterial {
    public static final ToolMaterial TWITCH = new ToolMaterial(ModTags.Blocks.NEEDS_TWITCH_TOOLS,
            1750, 4, 3f, 20, TagKey.create(
                    Registries.ITEM, Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "twitch")));
}
