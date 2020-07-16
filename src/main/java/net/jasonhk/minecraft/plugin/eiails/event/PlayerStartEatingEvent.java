package net.jasonhk.minecraft.plugin.eiails.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerStartEatingEvent extends PlayerEvent
{
    private static final HandlerList HANDLERS = new HandlerList();

    public PlayerStartEatingEvent(final Player player)
    {
        super(player);
    }

    @SuppressWarnings("unused")
    public static HandlerList getHandlerList()
    {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers()
    {
        return HANDLERS;
    }
}
