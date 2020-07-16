package net.jasonhk.minecraft.plugin.eiails;

import java.text.MessageFormat;

import org.bukkit.plugin.java.JavaPlugin;

import kr.entree.spigradle.annotations.PluginMain;

import lombok.val;

import com.comphenix.protocol.ProtocolLibrary;

import net.jasonhk.minecraft.plugin.eiails.listener.MainListener;
import net.jasonhk.minecraft.plugin.eiails.listener.PlayerStartEatingListener;

@PluginMain
@SuppressWarnings("unused")
public final class EatingIsAllowedInLosSengas extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        {
            getServer().getPluginManager().registerEvents(new MainListener(), this);
            getLogger().info(MessageFormat.format("Registered events listener {0}.",
                                                  MainListener.class.getSimpleName()));
        }

        {
            val manager = ProtocolLibrary.getProtocolManager();

            manager.addPacketListener(new PlayerStartEatingListener(this));
            getLogger().info(MessageFormat.format("Added packets listener {0}.",
                                                  PlayerStartEatingListener.class.getSimpleName()));
        }
    }

    @Override
    public void onDisable()
    {
        {
            val manager = ProtocolLibrary.getProtocolManager();
            manager.removePacketListeners(this);

            getLogger().info(MessageFormat.format("Removed packets listener {0}.",
                                                  PlayerStartEatingListener.class.getSimpleName()));
        }
    }
}
