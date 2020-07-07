package HoldMyAppleJuice;

import ProtocolPackage.Protocol;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PlayerEventHandler implements Listener
{
    public static ArrayList<Player> with_postfix = new ArrayList<Player>();
    public static HashMap<UUID, PermissionAttachment> perms = new HashMap<UUID, PermissionAttachment>();
    //public static String postfix = ChatColor.WHITE + " [" + ChatColor.DARK_GRAY  + ChatColor.BOLD + "DS" + ChatColor.RESET + ChatColor.WHITE + "]";


    @EventHandler
    public void j (PlayerJoinEvent event)
    {
        //String response = MyClient.send("player_joined", event.getPlayer().getUniqueId().toString());
        DiscordSync.client.SendMessage(new ClientMessage(Protocol.PLAYER_JOINED, event.getPlayer().getUniqueId().toString()).format());
    }

    @EventHandler
    public void l (PlayerQuitEvent event)
    {
        DiscordSync.client.SendMessage(new ClientMessage(Protocol.PLAYER_LEFT, event.getPlayer().getUniqueId().toString()).format());
    }

    public static void addPerm(Player p, String perm)
    {
        PermissionAttachment a = getOrCreateAttachment(p);
        a.setPermission(perm, true);
    }
    public static void remPerm(Player p, String perm)
    {
        PermissionAttachment a = getOrCreateAttachment(p);
        a.unsetPermission(perm);
    }

    public static PermissionAttachment getOrCreateAttachment(Player p)
    {
        if (perms.containsKey(p.getUniqueId()))
        {
            return perms.get(p.getUniqueId());
        }
        else
        {
            PermissionAttachment attachment = p.addAttachment(DiscordSync.plugin);
            perms.put(p.getUniqueId(), attachment);
            return attachment;
        }
    }

}
