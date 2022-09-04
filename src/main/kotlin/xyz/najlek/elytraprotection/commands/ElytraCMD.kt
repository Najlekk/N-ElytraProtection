package xyz.najlek.elytraprotection.commands

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import xyz.najlek.elytraprotection.utils.ChatUtil
import xyz.najlek.elytraprotection.utils.ConfigManager
import xyz.najlek.elytraprotection.utils.ElytraUtil
import java.util.stream.Collectors


class ElytraCMD : TabExecutor{

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>): MutableList<String>? {
        if (args.size == 1) {
            val arguments: MutableList<String> = ArrayList()
            arguments.add("transfer")
            return arguments
        } else if (args[0].equals("transfer", ignoreCase = true) && args.size == 2) {
            return Bukkit.getOnlinePlayers().stream().map { obj: Player -> obj.name }.collect(Collectors.toList())
        }
        return null
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender !is Player){
            sender.sendMessage(ChatUtil.fixColors(ConfigManager.get("msgs.only_players")))
            return false
        }
        val itemStack = sender.inventory.itemInMainHand
        val itemMeta = itemStack.itemMeta
        if(itemStack.type != Material.ELYTRA) {
            sender.sendMessage(ChatUtil.fixColors(ConfigManager.get("msgs.item_is_not_elytra")))
            return false
        }
        if(args.isEmpty()) {
            sender.sendMessage(ChatUtil.fixColors(ConfigManager.get("msgs.invalid_usage")))
            return false
        }
        if(args.size == 2 && args[0].equals("transfer", ignoreCase = true)) {
            if (args[1].isBlank() || args[1].isEmpty()) {
                sender.sendMessage(ChatUtil.fixColors(ConfigManager.get("msgs.invalid_usage")))
                return false
            }
            val target = Bukkit.getPlayerExact(args[1])
            if (target == null) {
                sender.sendMessage(ChatUtil.fixColors(ConfigManager.get("msgs.player_is_offline")))
                return false
            }
            sender.sendMessage(
                ChatUtil.fixColors(
                    ConfigManager.get("msgs.successful_transfer").replace("{TARGET}", target.name).replace("{NICK}", sender.getName())))
            target.sendMessage(ChatUtil.fixColors(ConfigManager.get("msgs.successful_transfer_online_player").replace("{TARGET}", target.name).replace("{NICK}", sender.getName())))
            val itemMeta1 = itemMeta?.let { ElytraUtil.transfer(target.name, it) }
            itemStack.itemMeta = itemMeta1
            sender.dropItem(true)
            sender.inventory.removeItem(itemStack)
        }
        return true
    }

}