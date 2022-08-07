package me.metumortis.xraynotify;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class XrayNotify extends JavaPlugin {

    public static FileConfiguration config;
    public static String ColorCode(String message){ return ChatColor.translateAlternateColorCodes('&', message);}

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new EventHandlers(this), this);
        config = this.getConfig();
        getCommand("xraynotify").setExecutor(new CommandHandler(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.saveConfig();
    }
}
