package core;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Display {

    private static long windowId;

    public static long getWindowId() {
        return windowId;
    }

    public static void create(){
        GLFWErrorCallback.createPrint(System.err).set();
        if(!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
        windowId = glfwCreateWindow(400, 300, "Hello World!", NULL, NULL);
        if ( windowId == NULL )
            throw new RuntimeException("Failed to create the GLFW window");
        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(windowId,(vidMode.width() - 400) / 2,(vidMode.height() - 300) / 2);
        glfwMakeContextCurrent(windowId);
        // Enable v-sync
        glfwSwapInterval(1);
        // Make the window visible
        glfwShowWindow(windowId);
        GL.createCapabilities();
    }

    public static void close(){
        glfwFreeCallbacks(windowId);
        glfwDestroyWindow(windowId);
    }
}
