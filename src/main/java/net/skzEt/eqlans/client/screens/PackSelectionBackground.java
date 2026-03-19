package net.skzEt.eqlans.client.screens;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.packs.PackSelectionScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.repository.PackRepository;

import java.nio.file.Path;
import java.util.function.Consumer;

public class PackSelectionBackground extends PackSelectionScreen {
    public PackSelectionBackground(PackRepository pRepository, Consumer<PackRepository> pOutput, Path pPackDir, Component pTitle) {
        super(pRepository, pOutput, pPackDir, pTitle);
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
