package xyz.najlek.elytraprotection;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.najlek.elytraprotection.commands.ElytraCMD;
import xyz.najlek.elytraprotection.listeners.PlayerPickupListener;

import java.util.Objects;

public class Bootstrap extends JavaPlugin {

    static Bootstrap instance;


    @Override
    public void onEnable() {
        instance = this;
        if(!getConfig().getBoolean("settings.enabled")){
            getLogger().info("Plugin is disabled!");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        saveDefaultConfig();
        saveConfig();
        getLogger().info("Starting plugin...");
        Bukkit.getPluginManager().registerEvents(new PlayerPickupListener(), this);
        Objects.requireNonNull(getCommand("elytra")).setExecutor(new ElytraCMD());
        getLogger().info("Started!");
    }

    public static Bootstrap getInstance(){
        return instance;
    }
}
