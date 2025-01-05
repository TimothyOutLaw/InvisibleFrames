package me.whish.invisibleFrames

import me.whish.invisibleFrames.Commands.InvisibleFramesCommand
import me.whish.invisibleFrames.Commands.InvisibleFramesTabCompleter
import me.whish.invisibleFrames.Listeners.FrameClickListener
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Level

class InvisibleFrames : JavaPlugin() {

    lateinit var configManager: ConfigManager

    override fun onEnable() {

        saveDefaultConfig()
        configManager = ConfigManager(this)
        configManager.loadConfig()

        server.pluginManager.registerEvents(FrameClickListener(this), this)

        getCommand("invisibleframes")?.setExecutor(InvisibleFramesCommand(this))
        getCommand("invisibleframes")?.tabCompleter = InvisibleFramesTabCompleter()

        logger.log(Level.INFO, "Plugin enable!")
    }

    override fun onDisable() {
        logger.log(Level.INFO, "Plugin disable!")
    }

}
