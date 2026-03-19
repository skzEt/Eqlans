package net.skzEt.eqlans.client.screens;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.skzEt.eqlans.Streamer;

@EventBusSubscriber(modid = Streamer.MOD_ID, value = Dist.CLIENT)
public class LevelLoadingBackground {
    @SubscribeEvent
    public static void onRenderScreenBackground(ScreenEvent.Render.Post event) {
        if (event.getScreen() instanceof LevelLoadingScreen levelScreen) {
            GuiGraphics graphics = event.getGuiGraphics();

            graphics.blit(TitleBackground.TEXTURES[TitleBackground.random],
                    0, 0, 0, 0,
                    Minecraft.getInstance().getWindow().getGuiScaledWidth(),
                    Minecraft.getInstance().getWindow().getGuiScaledHeight(),
                    Minecraft.getInstance().getWindow().getGuiScaledWidth(),
                    Minecraft.getInstance().getWindow().getGuiScaledHeight());
        }
    }

}
