package net.jasonhk.minecraft.plugin.eiails;

import org.bukkit.plugin.java.JavaPlugin;

import kr.entree.spigradle.annotations.PluginMain;

import lombok.val;

import com.comphenix.protocol.ProtocolLibrary;

import net.jasonhk.minecraft.plugin.eiails.listener.MainListener;
import net.jasonhk.minecraft.plugin.eiails.listener.PlayerStartEatingListener;

@PluginMain
@SuppressWarnings("unused")
public class EatingIsAllowedInLosSengas extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        {
            getServer().getPluginManager().registerEvents(new MainListener(), this);
        }

        {
            val manager = ProtocolLibrary.getProtocolManager();
            manager.addPacketListener(new PlayerStartEatingListener(this));
        }
    }

    @Override
    public void onDisable()
    {
        {
            val manager = ProtocolLibrary.getProtocolManager();
            manager.removePacketListeners(this);
        }
    }
}
