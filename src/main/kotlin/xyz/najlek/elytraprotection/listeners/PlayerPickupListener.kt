package xyz.najlek.elytraprotection.listeners

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerPickupItemEvent
import xyz.najlek.elytraprotection.utils.ChatUtil.fixColors
import xyz.najlek.elytraprotection.utils.ConfigManager.get
import xyz.najlek.elytraprotection.utils.ElytraUtil.transfer

class PlayerPickupListener : Listener {

    @EventHandler
    fun onPlayerPickup(event: PlayerPickupItemEvent) {
        val p = event.player
        val item = event.item
        val itemStack = item.itemStack
        val itemMeta = itemStack.itemMeta
        if (itemStack.type == Material.ELYTRA) {
            if (itemMeta!!.lore == null) {
                val itemMeta1 = transfer(p.name, itemMeta)
                itemStack.itemMeta = itemMeta1
                p.sendMessage(fixColors(get("msgs.elytra_first_pickup")))
            } else if (itemMeta.lore?.get(0)?.split("'s")!![0].equals(p.name, ignoreCase = true)) {
                //ignore
            } else {
                val owner_name = itemMeta.lore?.get(0)?.split("'s")!![0]
                p.sendMessage(fixColors(get("msgs.elytra_pickup_not_owner").replace("{OWNERNAME}", owner_name)))
                event.isCancelled = true
            }
        }
    }
}