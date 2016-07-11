package main;

import mvc.Controller;
import mvc.Model;
import mvc.View;

/**
 * Created by potaychuk on 04.07.2016.
 */
public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model,view);
        controller.processUSer();
    }
}
