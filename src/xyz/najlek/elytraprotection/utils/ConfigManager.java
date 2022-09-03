package xyz.najlek.elytraprotection.utils;

import xyz.najlek.elytraprotection.Bootstrap;

public class ConfigManager {

    public static String get(String path){
        return Bootstrap.getInstance().getConfig().getString(path);
    }
}
