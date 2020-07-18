package net.jasonhk.minecraft.plugins.lscatering.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import static com.comphenix.protocol.wrappers.EnumWrappers.Hand;

import lombok.val;

import net.jasonhk.minecraft.plugins.lscatering.events.PlayerStartEatingEvent;

public final class PlayerStartEatingListener extends PacketAdapter implements Listener
{
    public PlayerStartEatingListener(final JavaPlugin plugin)
    {
        // TODO: `PacketType.Play.Client.USE_ITEM` and `PacketType.Play.Client.BLOCK_PLACE` in
        //       ProtocolLib was switched places incorrectly, waiting upstream to fix this.
        super(plugin, ListenerPriority.HIGHEST, PacketType.Play.Client.BLOCK_PLACE);
    }

    @Override
    public void onPacketReceiving(final PacketEvent event)
    {
        val player = event.getPlayer();

        ItemStack itemToBeUsed;
        {
            val packet    = event.getPacket();
            val hand      = packet.getHands().readSafely(0);
            val inventory = player.getInventory();

            itemToBeUsed = (hand == Hand.MAIN_HAND) ? inventory.getItemInMainHand()
                                                    : inventory.getItemInOffHand();
        }

        if (itemToBeUsed.getType().isEdible())
        {
            firesPlayerStartEatingEvent(player);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteract(final PlayerInteractEvent event)
    {
        val player       = event.getPlayer();
        val action       = event.getAction();
        val clickedBlock = event.getClickedBlock();

        if ((action == Action.RIGHT_CLICK_BLOCK) && (clickedBlock.getType() == Material.CAKE_BLOCK))
        {
            firesPlayerStartEatingEvent(player);
        }
    }

    private void firesPlayerStartEatingEvent(final Player player)
    {
        val event = new PlayerStartEatingEvent(player);
        Bukkit.getPluginManager().callEvent(event);
    }
}
