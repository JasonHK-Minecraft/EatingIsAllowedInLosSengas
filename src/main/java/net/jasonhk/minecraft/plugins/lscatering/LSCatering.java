package net.jasonhk.minecraft.plugins.lscatering;

import java.text.MessageFormat;

import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.ProtocolLibrary;

import kr.entree.spigradle.annotations.PluginMain;

import lombok.val;

import net.jasonhk.minecraft.plugins.lscatering.listeners.MainListener;
import net.jasonhk.minecraft.plugins.lscatering.listeners.PlayerStartEatingListener;

@SuppressWarnings("unused")
@PluginMain
public final class LSCatering extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        val logger = getLogger();

        val pluginManager   = getServer().getPluginManager();
        val protocolManager = ProtocolLibrary.getProtocolManager();

        pluginManager.registerEvents(new MainListener(), this);
        logger.info(MessageFormat.format("Registered events listener {0}.",
                                         MainListener.class.getSimpleName()));

        {
            val listener = new PlayerStartEatingListener(this);

            pluginManager.registerEvents(listener, this);
            logger.info(MessageFormat.format("Registered events listener {0}.",
                                             PlayerStartEatingListener.class.getSimpleName()));

            protocolManager.addPacketListener(listener);
            logger.info(MessageFormat.format("Added packets listener {0}.",
                                             PlayerStartEatingListener.class.getSimpleName()));
        }
    }

    @Override
    public void onDisable()
    {
        val logger = getLogger();

        val protocolManager = ProtocolLibrary.getProtocolManager();

        protocolManager.removePacketListeners(this);
        logger.info("Removed packets listeners.");
    }
}
