package net.jasonhk.minecraft.plugin.eiails.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.val;

import net.jasonhk.minecraft.plugin.eiails.EatingIsAllowedInLosSengas;
import net.jasonhk.minecraft.plugin.eiails.event.PlayerStartEatingEvent;

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
                    JavaPlugin.getPlugin(EatingIsAllowedInLosSengas.class),
                    () -> { player.setFoodLevel(20); },
                    2L);
        }
    }
}
