package xyz.najlek.elytraprotection.utils

import org.bukkit.inventory.meta.ItemMeta

object ElytraUtil {
    fun transfer(nick: String, itemMeta: ItemMeta): ItemMeta {
        itemMeta.lore = listOf("$nick's Elytra")
        return itemMeta
    }
}