package net.skzEt.eqlans.client.screens;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.resources.Identifier;
import net.skzEt.eqlans.Streamer;

import java.util.Random;


public class TitleBackground extends TitleScreen {


    public static Identifier[] TEXTURES = new Identifier[] {
            Identifier.fromNamespaceAndPath(Streamer.MOD_ID,
            "textures/gui/background_1.png"),
            Identifier.fromNamespaceAndPath(Streamer.MOD_ID,
                    "textures/gui/background_2.png"),
            Identifier.fromNamespaceAndPath(Streamer.MOD_ID,
                    "textures/gui/background_3.png"),
            Identifier.fromNamespaceAndPath(Streamer.MOD_ID,
                    "textures/gui/background_4.png"),
            Identifier.fromNamespaceAndPath(Streamer.MOD_ID,
                    "textures/gui/background_5.png"),
            Identifier.fromNamespaceAndPath(Streamer.MOD_ID,
                    "textures/gui/background_6.png")
    };

    public static int random = new Random().nextInt(TEXTURES.length);

    @Override
    public void renderBackground(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        pGuiGraphics.blit(TEXTURES[random], 0, 0,  0, 0, this.width, this.height,
                this.width, this.height);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }
}
