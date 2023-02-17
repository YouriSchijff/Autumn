package Project;

import Autumn.*;

public class Main {

    public static void main(String[] args) {
        Autumn autumn = new Autumn();

        autumn.createWindow(500, 500, "Hello");

        autumn.loop();
        
        autumn.destroy();
    }

}
