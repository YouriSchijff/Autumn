package Autumn;

import Autumn.Graphics.Raw.Texture;
import Autumn.Graphics.Window;
import Autumn.Mathf.Vector2;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Autumn {

    public long window;

    public Autumn() {
        System.out.println("Autumn Enabled");

        glfwInit();
    }

    public void createWindow(int width, int height, String name) {
        window = new Window(width, height, name).getLong();
    }

    public void loop() {
        Texture t = new Texture("Afbeelding1.png");

        t.setPosition(100,100);

        while (!glfwWindowShouldClose(window)) {
            glfwPollEvents();

            glClear(GL_COLOR_BUFFER_BIT);
            glClearColor(0.1f, 0.4f, 0.5f, 1.0f);

            t.render();

            glfwSwapBuffers(window);
        }
    }

    public void destroy() {
        glfwDestroyWindow(window);
        glfwTerminate();
    }

}

