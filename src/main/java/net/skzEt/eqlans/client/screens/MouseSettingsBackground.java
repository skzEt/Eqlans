package net.skzEt.eqlans.client.screens;

import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.MouseSettingsScreen;

public class MouseSettingsBackground extends MouseSettingsScreen {
    public MouseSettingsBackground(Screen pLastScreen, Options pOptions) {
        super(pLastScreen, pOptions);
    }

    @Override
    public void renderBackground(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        pGuiGraphics.blit(TitleBackground.TEXTURES[TitleBackground.random], 0, 0,  0, 0, this.width, this.height,
                this.width, this.height);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }
}
