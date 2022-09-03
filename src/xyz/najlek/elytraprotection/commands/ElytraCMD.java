package xyz.najlek.elytraprotection.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.najlek.elytraprotection.utils.ChatUtil;
import xyz.najlek.elytraprotection.utils.ConfigManager;
import xyz.najlek.elytraprotection.utils.ElytraUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ElytraCMD implements TabExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(!(commandSender instanceof Player p)){
            commandSender.sendMessage(ChatUtil.fixColors(ConfigManager.get("msgs.only_players")));
            return false;
        }
        ItemStack itemStack = p.getInventory().getItemInMainHand();
        if(itemStack == null){
            p.sendMessage(ChatUtil.fixColors(ConfigManager.get("msgs.no_item_in_hand")));
            return false;
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        if(itemStack.getType() != Material.ELYTRA){
            p.sendMessage(ChatUtil.fixColors(ConfigManager.get("msgs.item_is_not_elytra")));
            return false;
        }
        if(args.length == 0){
            p.sendMessage(ChatUtil.fixColors(ConfigManager.get("msgs.invalid_usage")));
            return false;
        }
        if(args.length == 2 && args[0].equalsIgnoreCase("transfer")){
            if(args[1].isBlank()){
                p.sendMessage(ChatUtil.fixColors(ConfigManager.get("msgs.invalid_usage")));
                return false;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if(target == null){
                p.sendMessage(ChatUtil.fixColors(ConfigManager.get("msgs.player_is_offline")));
                return false;
            }
            p.sendMessage(ChatUtil.fixColors(ConfigManager.get("msgs.successful_transfer").replace("{TARGET}", target.getName()).replace("{NICK}", p.getName())));
            target.sendMessage(ChatUtil.fixColors(ConfigManager.get("msgs.successful_transfer_online_player").replace("{TARGET}", target.getName()).replace("{NICK}", p.getName())));
            ItemMeta itemMeta1 = ElytraUtil.transfer(target.getName(), itemMeta);
            itemStack.setItemMeta(itemMeta1);
            p.dropItem(true);
            p.getInventory().removeItem(itemStack);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1){
            List<String> arguments = new ArrayList<>();
            arguments.add("transfer");
            return arguments;
        }else if (args[0].equalsIgnoreCase("transfer") && args.length == 2){
            return Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
        }
        return null;
    }
}