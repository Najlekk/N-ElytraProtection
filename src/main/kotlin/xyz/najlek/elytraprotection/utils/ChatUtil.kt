package xyz.najlek.elytraprotection.utils

import org.bukkit.ChatColor

object ChatUtil {
    fun fixColors(text: String): String {
        return ChatColor.translateAlternateColorCodes('&', text)
    }
}