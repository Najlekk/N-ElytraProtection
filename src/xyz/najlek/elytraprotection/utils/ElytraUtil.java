package xyz.najlek.elytraprotection.utils;

import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ElytraUtil {

    public static ItemMeta transfer(String nick, ItemMeta itemMeta){
        itemMeta.setLore(List.of(nick + "'s Elytra"));
        return itemMeta;
    }

}
