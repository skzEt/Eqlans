package net.skzEt.eqlans.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

public class ModKeyboardHelper {
    public static final Window WINDOW = Minecraft.getInstance().getWindow();

    public static boolean isHoldingShift() {
        return InputConstants.isKeyDown(WINDOW, GLFW.GLFW_KEY_LEFT_SHIFT) || InputConstants.isKeyDown(WINDOW, GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

    public static boolean isHoldingControl() {
        return InputConstants.isKeyDown(WINDOW, GLFW.GLFW_KEY_LEFT_CONTROL) || InputConstants.isKeyDown(WINDOW, GLFW.GLFW_KEY_RIGHT_CONTROL);
    }

    public static boolean isHoldingSpace() {
        return InputConstants.isKeyDown(WINDOW, GLFW.GLFW_KEY_SPACE);
    }
}
