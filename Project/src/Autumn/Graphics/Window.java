package Autumn.Graphics;

import Autumn.Mathf.Vector2;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
public class Window {

    private long window;

    public static Vector2 size;


    public Window(int width, int height, String name) {

        size = new Vector2(width, height);

        window = glfwCreateWindow(width, height, name, 0l, 0l);
        glfwShowWindow(window);
        glfwMakeContextCurrent(window);

        GL.createCapabilities();

    }

    public long getLong() { return window; }

}
