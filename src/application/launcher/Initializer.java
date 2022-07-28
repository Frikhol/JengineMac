package launcher;

import core.Display;
import core.Engine;

import static event.processor.EventProcessor.*;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class Initializer implements Runnable{
    private static final Thread app = new Thread(new Initializer());

    public static void main(String[] args){
        app.start();
    }

    @Override
    public void run() {
        Engine.start();
        loadEvents();
        while(!glfwWindowShouldClose(Display.getWindowId())){
            processEvents();
            Engine.loop();
        }
        Engine.stop();
    }
}
