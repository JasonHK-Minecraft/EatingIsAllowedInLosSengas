package net.jasonhk.minecraft.plugins.lscatering.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import lombok.NonNull;

public class PlayerStartEatingEvent extends PlayerEvent
{
    private static final HandlerList HANDLERS = new HandlerList();

    public PlayerStartEatingEvent(@NonNull final Player player)
    {
        super(player);
    }

    @SuppressWarnings("unused")
    @NonNull
    public static HandlerList getHandlerList()
    {
        return HANDLERS;
    }

    @Override
    @NonNull
    public HandlerList getHandlers()
    {
        return HANDLERS;
    }
}
