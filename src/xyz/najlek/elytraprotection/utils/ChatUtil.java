package xyz.najlek.elytraprotection.utils;

import org.bukkit.ChatColor;

public class ChatUtil {

    public static String fixColors(String text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
