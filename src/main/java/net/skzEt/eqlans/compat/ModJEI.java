package net.skzEt.eqlans.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import net.minecraft.resources.Identifier;
import net.skzEt.eqlans.Streamer;

@JeiPlugin
public class ModJEI implements IModPlugin {
    @Override
    public Identifier getPluginUid() {
        return Identifier.fromNamespaceAndPath(Streamer.MOD_ID, "jei_plugin");
    }
}
