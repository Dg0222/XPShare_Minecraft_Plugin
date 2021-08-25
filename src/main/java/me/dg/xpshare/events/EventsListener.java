package me.dg.xpshare.events;

import me.dg.xpshare.XPShare;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataType;

public class EventsListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        if(!player.getPersistentDataContainer().has(new NamespacedKey(XPShare.getPlugin(), "storedXp"), PersistentDataType.INTEGER)){
            player.getPersistentDataContainer().set(new NamespacedKey(XPShare.getPlugin(),"storedXp"),PersistentDataType.INTEGER,0);
        }
    }
}
