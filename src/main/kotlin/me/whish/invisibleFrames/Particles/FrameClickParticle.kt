package me.whish.invisibleFrames.Particles

import me.whish.invisibleFrames.ConfigManager
import me.whish.invisibleFrames.InvisibleFrames
import org.bukkit.Bukkit
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEntityEvent

class FrameClickParticle(private val configManager: ConfigManager) {
    fun frameClickParticle(event: PlayerInteractEntityEvent) {

        val entity = event.rightClicked
        val world = event.rightClicked.world

        val facing = entity.facing
        val location = entity.location.clone()

        when (facing) {
            BlockFace.UP -> location.add(0.0, 0.2, 0.0) // Рамка на полу
            BlockFace.DOWN -> location.add(0.0, -0.2, 0.0) // Рамка на потолке
            BlockFace.NORTH -> location.add(0.0, 0.0, -0.2) // Рамка на северной стене
            BlockFace.SOUTH -> location.add(0.0, 0.0, 0.2) // Рамка на южной стене
            BlockFace.EAST -> location.add(0.2, 0.0, 0.0) // Рамка на восточной стене
            BlockFace.WEST -> location.add(-0.2, 0.0, 0.0) // Рамка на западной стене
            else -> location.add(0.0, 0.0, 0.0) // На случай неизвестных положений
        }

        val radius = configManager.getRadius()
        val countParticles = configManager.getCountParticles()
        val volume = configManager.getVolume()

        world.spawnParticle(Particle.POOF, location, countParticles, 0.1, 0.1, 0.1, 0.02)

        val nearbyPlayers = entity.getNearbyEntities(radius, radius, radius).filterIsInstance<Player>()

        for (i in nearbyPlayers) {
            i.playSound(location, Sound.BLOCK_FIRE_EXTINGUISH, volume, 1.0f)
        }

    }
}