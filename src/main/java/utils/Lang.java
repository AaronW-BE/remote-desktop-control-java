package utils;

import java.util.ResourceBundle;

public class Lang {
    public static String L(String name) {
        ResourceBundle bundle = ResourceBundle.getBundle("lang.app");
        return bundle.getString(name);
    }
}
