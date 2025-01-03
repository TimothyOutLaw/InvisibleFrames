package me.whish.invisibleFrames.Listeners

import me.whish.invisibleFrames.InvisibleFrames
import me.whish.invisibleFrames.Particles.FrameClickParticle
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.ItemFrame
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent

class FrameClickListener(private val plugin: InvisibleFrames) : Listener {

    @EventHandler
    fun frameClickListener(event: PlayerInteractEntityEvent) {

        val player = event.player
        val entity = event.rightClicked

        if (entity !is ItemFrame) {
            return
        }

        if (!(player.isSneaking)) {
            return
        }

        val itemInMainHand = player.inventory.itemInMainHand

        val invisibleError = TextComponent(ChatColor.translateAlternateColorCodes('&', "&cРамка уже невидимая!"))
        val visibleError = TextComponent(ChatColor.translateAlternateColorCodes('&', "&cРамка уже видимая!"))

        val visibility = entity.isVisible

        if (itemInMainHand.type == Material.AMETHYST_SHARD) {

            event.isCancelled = true

            if (visibility) {
                FrameClickParticle(plugin.configManager).frameClickParticle(event)
                entity.isVisible = false
            } else {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, invisibleError)
            }

        }
        else if (itemInMainHand.type == Material.WHITE_WOOL) {

            event.isCancelled = true

            if (!visibility) {
                FrameClickParticle(plugin.configManager).frameClickParticle(event)
                entity.isVisible = true
            } else {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, visibleError)
            }

        }
        return
    }
}