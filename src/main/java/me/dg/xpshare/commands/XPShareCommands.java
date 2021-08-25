package me.dg.xpshare.commands;

import me.dg.xpshare.XPShare;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class XPShareCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        try {

            Player player = (Player) sender;
            PersistentDataContainer data = player.getPersistentDataContainer();
            NamespacedKey nameSpacedKey = new NamespacedKey(XPShare.getPlugin(), "storedXp");
            int storedXp = data.get(nameSpacedKey, PersistentDataType.INTEGER);
            int currentXp = player.getTotalExperience();


            // command to store player's xp /XPStore
            if (command.getName().equalsIgnoreCase("storexp")) {

                try {
                    int inputtedXpValue = Integer.parseInt(args[0]);

                    if (inputtedXpValue > currentXp) {
                        player.sendMessage(ChatColor.RED + "The number you have entered is greater than your current exp level!");
                    } else {
                        storedXp += inputtedXpValue;
                        data.set(nameSpacedKey, PersistentDataType.INTEGER, storedXp);
                        player.setTotalExperience(currentXp - inputtedXpValue);

                        player.sendMessage(ChatColor.GREEN + "Your XP has been stored successfully!");
                    }

                } catch (NumberFormatException ex) {
                    player.sendMessage(ChatColor.RED + "You must enter a valid amount to store");
                } catch (NullPointerException ep) {
                    player.sendMessage(ChatColor.RED + "An error has occurred!");
                }
            }

            // command to share player's xp with another player /XPShare
            if (command.getName().equalsIgnoreCase("sharexp")) {

                try {

                    String inputtedPlayerName = args[0];
                    int inputtedXpValue = Integer.parseInt(args[1]);
                    Player targetPlayer = Bukkit.getServer().getPlayer(inputtedPlayerName);

                    if (inputtedXpValue > currentXp) {
                        player.sendMessage(ChatColor.RED + "The number you have entered is greater than your current exp level!");
                    } else {
                        if (!(targetPlayer == null)) {
                            int targetPlayerXp = targetPlayer.getTotalExperience();
                            player.setTotalExperience(currentXp - inputtedXpValue);
                            targetPlayer.setTotalExperience(targetPlayerXp + inputtedXpValue);
                            player.sendMessage(ChatColor.GREEN + "XP successfully transferred!");
                        } else {
                            player.sendMessage(ChatColor.RED + "Player was not found!");
                        }
                    }
                } catch (NumberFormatException ex) {
                    player.sendMessage(ChatColor.RED + "You must enter a valid integer to share XP!");
                } catch (NullPointerException ep) {
                    player.sendMessage(ChatColor.RED + "An error has occurred!");
                }
            }

            // command to get player's currently stored xp /XPGet
            if (command.getName().equalsIgnoreCase("getxp")) {

                try {
                    int inputtedXpValue = Integer.parseInt(args[0]);

                    if (storedXp <= 0) {
                        player.sendMessage(ChatColor.RED + "There is no more XP to get!");
                        return true;
                    }

                    if (inputtedXpValue <= storedXp) {
                        storedXp -= inputtedXpValue;
                        player.setTotalExperience(currentXp + inputtedXpValue);
                        player.sendMessage(ChatColor.GREEN + "Your XP has been retrieved successfully!");
                    } else {
                        player.sendMessage(ChatColor.RED + "Inputted value is greater than the amount of XP that is stored!");
                    }

                }
                catch (NumberFormatException ex) {
                    player.sendMessage(ChatColor.RED + "You must enter a valid amount to get");
                }
                catch (NullPointerException ep) {
                    player.sendMessage(ChatColor.RED + "An error has occurred!");
                }
            }

            //command to show player's currently stored xp /XPStored
            if (command.getName().equalsIgnoreCase("storedxp")) {
                player.sendMessage(ChatColor.RED + "This is your current amount of XP stored!" + storedXp);
            }
        }
        catch(NullPointerException ep){
            sender.sendMessage(ChatColor.RED + "An error has occurred!");
        }
        return true;
    }
}
