package xyz.najlek.elytraprotection.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.najlek.elytraprotection.utils.ChatUtil;
import xyz.najlek.elytraprotection.utils.ConfigManager;
import xyz.najlek.elytraprotection.utils.ElytraUtil;

public class PlayerPickupListener implements Listener {

    @EventHandler
    public void onPlayerPickup(PlayerPickupItemEvent event){
        Player p = event.getPlayer();
        Item item =  event.getItem();
        ItemStack itemStack = item.getItemStack();
        ItemMeta itemMeta = itemStack.getItemMeta();
        if(itemStack.getType() == Material.ELYTRA){
            if(itemMeta.getLore() == null){
                ItemMeta itemMeta1 = ElytraUtil.transfer(p.getName(), itemMeta);
                itemStack.setItemMeta(itemMeta1);
                p.sendMessage(ChatUtil.fixColors(ConfigManager.get("msgs.elytra_first_pickup")));
            } else if(itemMeta.getLore().get(0).split("'s")[0].equalsIgnoreCase(p.getName())){
                //ignore
            } else {
                String owner_name = itemMeta.getLore().get(0).split("'s")[0];
                p.sendMessage(ChatUtil.fixColors(ConfigManager.get("msgs.elytra_pickup_not_owner").replace("{OWNERNAME}", owner_name)));
                event.setCancelled(true);
            }
        }
    }

}
