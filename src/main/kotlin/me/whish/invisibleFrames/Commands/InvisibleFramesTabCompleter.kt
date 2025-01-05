package me.whish.invisibleFrames.Commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class InvisibleFramesTabCompleter : TabCompleter {
    override fun onTabComplete(
        p0: CommandSender,
        p1: Command,
        p2: String,
        p3: Array<out String>
    ): MutableList<String>? {

        if (p1.name.equals("invisibleframes", ignoreCase = true)) {

            val list = if (p3.size == 1) {
                mutableListOf<String>("reload").filter { it.startsWith(p3[0], ignoreCase = true) }.toMutableList()
            } else {
                emptyList<String>().toMutableList()
            }

            return list
        }

        return null
    }
}