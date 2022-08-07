package me.metumortis.xraynotify;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

import static me.metumortis.xraynotify.XrayNotify.*;

public class CommandHandler implements CommandExecutor {
    private Plugin plugin;
    public CommandHandler(Plugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            sender.sendMessage(ColorCode("&cGeçersiz komut."));
        }
        else if(sender instanceof Player){
            Player player = (Player) sender;
            if(!player.hasPermission("xraynotify."+args[0].toLowerCase())){
                sender.sendMessage(ColorCode("&cBunu yapmaya yetkin yok :("));
                return false;
            }
            if(args[0].equalsIgnoreCase("add")){
                if(!player.getInventory().getItemInMainHand().getType().toString().equals("AIR")){
                    List<String> banned_blocks = config.getStringList("banned_blocks");
                    if(!banned_blocks.contains(player.getInventory().getItemInMainHand().getType().toString())){
                        banned_blocks.add(player.getInventory().getItemInMainHand().getType().toString());
                        config.set("banned_blocks", banned_blocks);
                        sender.sendMessage(ColorCode(String.format("&a%s, listeye eklendi.", player.getInventory().getItemInMainHand().getType().toString())));
                    }
                    else{
                        sender.sendMessage(ColorCode("&cBu eşya zaten listede."));
                    }
                }
                else{
                    sender.sendMessage(ColorCode("&cListeye eklemek istediğiniz eşyayı elinizde tutun."));
                }
            }

            if(args[0].equalsIgnoreCase(("remove"))){
                if(!player.getInventory().getItemInMainHand().getType().toString().equals("AIR")){
                    List<String> banned_blocks = config.getStringList("banned_blocks");
                    if(banned_blocks.contains(player.getInventory().getItemInMainHand().getType().toString())){
                        banned_blocks.remove(player.getInventory().getItemInMainHand().getType().toString());
                        config.set("banned_blocks", banned_blocks);
                        sender.sendMessage(ColorCode(String.format("&a%s, listeden çıkarıldı.", player.getInventory().getItemInMainHand().getType().toString())));
                    }
                    else{
                        sender.sendMessage(ColorCode("&cBu eşya zaten listede yok."));
                    }
                }
                else{
                    player.sendMessage(ColorCode("&cListeden çıkarmak istediğiniz eşyayı elinizde tutun."));
                }
            }

            if(args[0].equalsIgnoreCase("reload")){
                plugin.reloadConfig();
                config = plugin.getConfig();
                sender.sendMessage(ColorCode("&aPlugin başarıyla yeniden yüklendi."));
            }

            if(args[0].equalsIgnoreCase("list")){
                String message = ColorCode("&9Listedeki bloklar:");
                for(String esya : config.getStringList("banned_blocks")){
                    message += ColorCode("\n&9- &3"+esya);
                }
                sender.sendMessage(message);
            }

        }
        else if(args[0].equalsIgnoreCase("reload")){
            plugin.reloadConfig();
            config = plugin.getConfig();
            sender.sendMessage(ColorCode("&aPlugin başarıyla yeniden yüklendi."));
        }
        else{
            sender.sendMessage(ColorCode("&cBu komudu sadece oyuncular kullanabilir."));
        }







        return false;
    }

}
