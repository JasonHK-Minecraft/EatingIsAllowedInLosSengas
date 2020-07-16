package net.jasonhk.minecraft.plugin.eiails.listener;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.val;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import static com.comphenix.protocol.wrappers.EnumWrappers.Hand;

import net.jasonhk.minecraft.plugin.eiails.event.PlayerStartEatingEvent;

public final class PlayerStartEatingListener extends PacketAdapter
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

        val packet = event.getPacket();
        val hand   = packet.getHands().readSafely(0);

        val inventory = player.getInventory();
        val itemToBeUsed = (hand == Hand.MAIN_HAND) ? inventory.getItemInMainHand()
                                                    : inventory.getItemInOffHand();

        if (itemToBeUsed.getType().isEdible())
        {
            val eventToBeFired = new PlayerStartEatingEvent(player);
            Bukkit.getPluginManager().callEvent(eventToBeFired);
        }
    }
}
