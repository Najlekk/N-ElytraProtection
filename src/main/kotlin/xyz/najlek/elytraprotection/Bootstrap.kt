package xyz.najlek.elytraprotection

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import xyz.najlek.elytraprotection.commands.ElytraCMD
import xyz.najlek.elytraprotection.listeners.PlayerPickupListener

class Bootstrap : JavaPlugin() {

    override fun onEnable() {
        instance = this
        if (!config.getBoolean("settings.enabled")) {
            logger.info("Plugin is disabled!")
            Bukkit.getPluginManager().disablePlugin(this)
        }
        saveDefaultConfig()
        saveConfig()
        logger.info("Starting plugin...")
        Bukkit.getPluginManager().registerEvents(PlayerPickupListener(), this)
        getCommand("elytra")?.setExecutor(ElytraCMD())
        logger.info("Started!")
    }

    companion object {
        lateinit var instance: Bootstrap
    }
}