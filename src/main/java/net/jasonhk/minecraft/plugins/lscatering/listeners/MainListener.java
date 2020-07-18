package net.jasonhk.minecraft.plugins.lscatering.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.val;

import net.jasonhk.minecraft.plugins.lscatering.LSCatering;
import net.jasonhk.minecraft.plugins.lscatering.events.PlayerStartEatingEvent;

public final class MainListener implements Listener
{
    @EventHandler()
    public void onPlayerStartEating(final PlayerStartEatingEvent event)
    {
        val player = event.getPlayer();
        if (player.getFoodLevel() == 20)
        {
            player.setFoodLevel(19);

            val scheduler = Bukkit.getScheduler();
            scheduler.scheduleSyncDelayedTask(
                    JavaPlugin.getPlugin(LSCatering.class),
                    () -> { player.setFoodLevel(20); },
                    2L);
        }
    }
}
