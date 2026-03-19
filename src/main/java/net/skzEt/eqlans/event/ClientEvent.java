package net.skzEt.eqlans.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.options.*;
import net.minecraft.client.gui.screens.options.controls.ControlsScreen;
import net.minecraft.client.gui.screens.options.controls.KeyBindsScreen;
import net.minecraft.client.gui.screens.packs.PackSelectionScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.repository.PackRepository;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.skzEt.eqlans.Streamer;
import net.skzEt.eqlans.client.screens.*;

import java.util.function.Consumer;

@EventBusSubscriber(modid = Streamer.MOD_ID, value = Dist.CLIENT)
public class ClientEvent {

    public class ScreenTracker {
        public static Screen lastTitleScreen = null;
        public static Screen lastOptionsScreen = null;
        public static Screen lastControlsScreen = null;
        public static Screen mainMenuScreen = null;
    }

    private static Screen lastScreen = null;

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Pre event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.screen instanceof TitleScreen) {
            lastScreen = null;
            minecraft.setScreen(new TitleBackground());
        }
    }


    private static Screen getSafeParentScreen(Screen fallback) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.screen != null && !(mc.screen instanceof LevelLoadingScreen)) {
            return mc.screen;
        }
        return fallback != null ? fallback : new TitleBackground();
    }

    @SubscribeEvent
    public static void openOn(ScreenEvent.Opening event) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.level != null && minecraft.player != null) {
            return;
        }

        Screen newScreen = event.getScreen();

        if (lastScreen == null && !(newScreen instanceof TitleBackground)) {
            lastScreen = minecraft.screen;
        }
        if (event.getScreen() instanceof TitleScreen) {
            lastScreen = null;
            ScreenTracker.mainMenuScreen = new TitleBackground();
            event.setNewScreen(ScreenTracker.mainMenuScreen);
        } else if (event.getScreen() instanceof OptionsScreen) {
            event.setNewScreen(new OptionsBackground(lastScreen, minecraft.options));
        } else if (event.getScreen() instanceof VideoSettingsScreen) {
            event.setNewScreen(new VideoSettingsBackground(event.getCurrentScreen(), minecraft, minecraft.options));
        } else if (event.getScreen() instanceof SoundOptionsScreen) {
            event.setNewScreen(new SoundOptionsBackground(event.getCurrentScreen(), minecraft.options));
        } else if (event.getScreen() instanceof ControlsScreen) {
            Screen safeParent = getSafeParentScreen(ScreenTracker.lastOptionsScreen);
            ScreenTracker.lastControlsScreen = safeParent;
            event.setNewScreen(new ControlsBackground(safeParent, minecraft.options));
        } else if (event.getScreen() instanceof MouseSettingsScreen) {
            event.setNewScreen(new MouseSettingsBackground(ScreenTracker.lastControlsScreen, minecraft.options));
        } else if (event.getScreen() instanceof LanguageSelectScreen) {
            event.setNewScreen(new LanguageSelectBackground(event.getCurrentScreen(), minecraft.options, minecraft.getLanguageManager()));
        } else if (event.getScreen() instanceof PackSelectionScreen) {
            Consumer<PackRepository> onClose = (packs) -> {
                minecraft.options.resourcePacks = packs.getSelectedIds().stream().toList();
                minecraft.options.save();
                minecraft.reloadResourcePacks();
                minecraft.setScreen(event.getCurrentScreen());
            };
            event.setNewScreen(new PackSelectionBackground(minecraft.getResourcePackRepository(), onClose,
                    minecraft.gameDirectory.toPath().resolve("resourcepacks"),Component.translatable("resourcePack.title")));
        } else if (event.getScreen() instanceof ChatOptionsScreen) {
            event.setNewScreen(new ChatOptionsBackground(event.getCurrentScreen(), minecraft.options));
        } else if (event.getScreen() instanceof KeyBindsScreen) {
            event.setNewScreen(new KeyBindsBackground(ScreenTracker.lastControlsScreen, minecraft.options));
        } else if (event.getScreen() instanceof FontOptionsScreen) {
            event.setNewScreen(new FontOptionsBackground(event.getCurrentScreen(), minecraft.options));
        } else if (event.getScreen() instanceof OnlineOptionsScreen) {
            event.setNewScreen(new OnlineOptionsBackground(event.getCurrentScreen(), minecraft.options));
        } else if (event.getScreen() instanceof AccessibilityOptionsScreen) {
            event.setNewScreen(new AccessibilityOptionsBackground(event.getCurrentScreen(), minecraft.options));
        } else if (event.getScreen() instanceof SkinCustomizationScreen) {
            event.setNewScreen(new SkinCustomizationBackground(event.getCurrentScreen(), minecraft.options));
        } else if (event.getScreen() instanceof SelectWorldScreen) {
            Screen parent = ScreenTracker.mainMenuScreen != null ? ScreenTracker.mainMenuScreen : event.getCurrentScreen();
            event.setNewScreen(new SelectWorldBackground(parent));
        } else if (event.getScreen() instanceof JoinMultiplayerScreen) {
            Screen parent = ScreenTracker.mainMenuScreen != null ? ScreenTracker.mainMenuScreen : event.getCurrentScreen();
            event.setNewScreen(new JoinMultiplayerBackground(parent));
        } else {
            Screen screen = event.getScreen();
            try {
                var parentField = screen.getClass().getDeclaredField("parent");
                parentField.setAccessible(true);
                Object parent = parentField.get(screen);

                if (parent != null && parent.getClass().getName().contains("StreamerMod")) {
                    parentField.set(screen, new TitleScreen());
                }

            } catch (NoSuchFieldException | IllegalAccessException ignored) {
            }
        }
    }
}
