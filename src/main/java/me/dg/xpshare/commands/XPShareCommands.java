package me.dg.xpshare.commands;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class XPShareCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        PersistentDataContainer data = player.getPersistentDataContainer();
        NamespacedKey nameSpacedKey = new NamespacedKey((Plugin) this, "storedXp");
        int storedXp = data.get(nameSpacedKey, PersistentDataType.INTEGER);
        int currentXp = player.getTotalExperience();
        int GetXp = data.get(nameSpacedKey);


        // command to store player's xp /XPStore
        if(command.getName().equalsIgnoreCase("XPStore")){

            try {
                int inputtedXpValue = Integer.parseInt(args[1]);

                if(inputtedXpValue > currentXp){
                    player.sendMessage(ChatColor.RED + "The number you have entered is greater than your current exp level!");
                }
                else{
                    storedXp += inputtedXpValue;
                    data.set(nameSpacedKey, PersistentDataType.INTEGER, storedXp);
                    player.setTotalExperience(currentXp - inputtedXpValue);

                    player.sendMessage(ChatColor.GREEN + "Your XP has been stored successfully!");
                }

            }
            catch(NumberFormatException ex) {
                player.sendMessage(ChatColor.RED + "You must enter a valid amount to store");
            }
        }


        // command to share player's xp with another player /XPShare
        if(command.getName().equalsIgnoreCase("XPShare")){

        }

        // command to get player's currently stored xp /XPGet
        if(command.getName().equalsIgnoreCase("XPGet")){


            try {
                int inputtedXpValue = Integer.parseInt(args[1]);

                if(storedXp <= 0){
                    player.sendMessage(ChatColor.RED + "There is no more xp to get!");
                    return true;
                }

                if (inputtedXpValue <= storedXp){
                    storedXp-=inputtedXpValue;
                    player.setTotalExperience(currentXp + inputtedXpValue);
                    player.sendMessage(ChatColor.GREEN + "Your XP has been retrieved successfully!");
                }

                else {
                    player.sendMessage(ChatColor.RED + "Inputted value is greater than the amount of XP that is stored!");
                }

            }
                catch(NumberFormatException ex) {
                player.sendMessage(ChatColor.RED + "You must enter a valid amount to get");
            }
            {
        return true;
    }
    }