package me.whish.invisibleFrames

import org.bukkit.configuration.file.FileConfiguration

class ConfigManager(private val plugin: InvisibleFrames) {

    private lateinit var config: FileConfiguration

    fun loadConfig() {
        config = plugin.config
    }

    fun getRadius() : Double {
        val value = config.get("radius", 5.0)
        if (value is Number) {
            val radius = value.toDouble()
            return radius
        } else {
            return 5.0
        }
    }

    fun getCountParticles() : Int {
        val value = config.get("countParticles", 10)
        if (value is Number) {
            val countParticles = value.toInt()
            return countParticles
        } else {
            return 10
        }
    }

    fun getVolume() : Float {
        val value = config.get("volume", 0.2f)
        if (value is Number) {
            val volume = value.toFloat()
            return volume
        } else {
            return 0.2f
        }
    }

    fun reloadConfig() {
        plugin.reloadConfig()
        loadConfig()
    }

}