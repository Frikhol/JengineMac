package core;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Engine {

    public static void start(){
        Display.create();
    }

    public static void loop(){
        glfwPollEvents();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glfwSwapBuffers(Display.getWindowId());
    }

    public static void stop(){
        Display.close();
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
}
