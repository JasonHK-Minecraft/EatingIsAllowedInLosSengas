package net.jasonhk.minecraft.plugin.eiails.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import lombok.val;

import net.jasonhk.minecraft.plugin.eiails.event.PlayerStartEatingEvent;

public final class MainListener implements Listener
{
    @EventHandler()
    public void onPlayerStartEating(PlayerStartEatingEvent event)
    {
        val player = event.getPlayer();
        if (player.getFoodLevel() == 20)
        {
            player.setFoodLevel(19);
        }
    }
}
