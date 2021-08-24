package me.dg.xpshare;

import me.dg.xpshare.commands.XPShareCommands;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class XPShare extends JavaPlugin {

    @Override
    public void onEnable() {
        XPShareCommands commands = new XPShareCommands();
        getCommand("XPStore").setExecutor(commands);
        getCommand("XPGet").setExecutor(commands);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[XPShare]: plugin is enabled!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[XPShare]: plugin is disabled!");
    }
}
