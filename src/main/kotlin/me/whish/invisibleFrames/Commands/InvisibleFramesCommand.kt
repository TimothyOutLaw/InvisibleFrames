package me.whish.invisibleFrames.Commands

import me.whish.invisibleFrames.InvisibleFrames
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class InvisibleFramesCommand(private val plugin: InvisibleFrames) : CommandExecutor {

    override fun onCommand(p0: CommandSender, p1: Command, p2: String, args: Array<out String>): Boolean {

        if (args.isNotEmpty() && args[0].equals("reload", ignoreCase = true) && args.size == 1) {

            if (!p0.hasPermission("invisibleframes.admin")) {
                p0.sendMessage("§cУ вас недостаточно прав для выполнения этой команды!")
                return true
            }

            plugin.configManager.reloadConfig()
            p0.sendMessage("§aКонфигурация успешно перезагружена!")
            return true
        }
        return false
    }
}