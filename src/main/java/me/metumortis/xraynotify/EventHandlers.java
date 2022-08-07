package me.metumortis.xraynotify;
import static me.metumortis.xraynotify.XrayNotify.*;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

public class EventHandlers implements Listener {
    private Plugin plugin;
    public EventHandlers(Plugin plugin){
        this.plugin = plugin;
    }


    @EventHandler
    public void onBreak(BlockBreakEvent event){
        if(config.getStringList("banned_blocks").stream().anyMatch(el -> Material.getMaterial(el.toUpperCase()).equals(event.getBlock().getType()))){
            Bukkit.broadcast(ColorCode("&e" + event.getPlayer().getName() + " adllı oyuncu " + event.getBlock().getType().toString() + " buldu!"), "xraynotify.notify");
        }
    }
}
