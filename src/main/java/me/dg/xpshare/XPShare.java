package me.dg.xpshare;

import me.dg.xpshare.commands.XPShareCommands;
import me.dg.xpshare.events.EventsListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class XPShare extends JavaPlugin {

    private static XPShare plugin;

    @Override
    public void onEnable() {
        plugin = this;

        XPShareCommands commands = new XPShareCommands();

        getCommand("storexp").setExecutor(commands);
        getCommand("getxp").setExecutor(commands);
        getCommand("storedxp").setExecutor(commands);
        getCommand("sharexp").setExecutor(commands);

        getServer().getPluginManager().registerEvents(new EventsListener(), this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[XPShare]: plugin is enabled!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[XPShare]: plugin is disabled!");
    }

    public static XPShare getPlugin(){
        return plugin;
    }
}
