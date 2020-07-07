package HoldMyAppleJuice;

import com.nametagedit.plugin.NametagEdit;
import com.nametagedit.plugin.NametagManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.data.type.Bell;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import sun.awt.HKSCS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PlayerEventHandler implements Listener
{
    public static ArrayList<Player> with_postfix = new ArrayList<Player>();
    public static HashMap<UUID, PermissionAttachment> perms = new HashMap<UUID, PermissionAttachment>();
    //public static String postfix = ChatColor.WHITE + " [" + ChatColor.DARK_GRAY  + ChatColor.BOLD + "DS" + ChatColor.RESET + ChatColor.WHITE + "]";

    public PlayerEventHandler()
    {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(DiscordSync.plugin, new Runnable() {
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers())
                {
                    try
                    {
                        String response = Client.send("is_player_on_discord", player.getUniqueId().toString());
                        String bool = response.split(":")[1];
                        if (bool.equals("true"))
                        {
                            addPostfix(player);
                        }
                        else
                        {
                            removePostfix(player);
                        }
                    }
                    catch (Exception e){e.printStackTrace();}
                }

            }
        }, 100, 100);
    }

    @EventHandler
    public void j (PlayerJoinEvent event)
    {
        String response = Client.send("player_joined", event.getPlayer().getUniqueId().toString());
    }

    @EventHandler
    public void l (PlayerQuitEvent event)
    {
        Client.send("player_left", event.getPlayer().getUniqueId().toString());
    }

    public static void addPostfix(Player player)
    {
        //player.sendMessage("trying to add prefix. " + player.getEffectivePermissions().size() + " perms found");
        for (PermissionAttachmentInfo perm : player.getEffectivePermissions())
        {
            String p = perm.getPermission();
            //player.sendMessage("iter " + p);
            if (p.contains("nte.") && !p.contains("_ds"))
            {
                if (!with_postfix.contains(player)) {
                    player.sendMessage("You just joined discord voice channel!");
                    with_postfix.add(player);
                }

                if (!perms.containsKey(player.getUniqueId())) {
                    PermissionAttachment attachment = player.addAttachment(DiscordSync.plugin);
                    attachment.setPermission(p + "_ds", true);
                    perms.put(player.getUniqueId(), attachment);
                }
                else
                {
                    PermissionAttachment attachment =perms.get(player.getUniqueId());
                    attachment.setPermission(p + "_ds", true);
                }
                player.sendMessage("has ds perm: " + player.hasPermission(p + "_ds"));

                //player.sendMessage("add " + p + "_ds" + player.hasPermission(p + "_ds"));
                //player.sendMessage("You just joined discord voice channel!");
                break;
            }
        }
        //team.addEntry(player.getName());
    }
    public static void removePostfix(Player player)
    {
        for (PermissionAttachmentInfo perm : player.getEffectivePermissions())
        {
            String p = perm.getPermission();

            if (p.contains("nte.") && p.contains("_ds"))
            {
                if (with_postfix.contains(player)) {
                    player.sendMessage("You just left discord voice channel!");
                    with_postfix.remove(player);
                }


               // player.sendMessage("found perm to remove " + p);
                if (!perms.containsKey(player.getUniqueId()))
                {
                    return;
                }
                else
                {
                    PermissionAttachment attachment = perms.get(player.getUniqueId());
                    attachment.setPermission(p, false);
                }
                player.sendMessage("has ds perm: " + player.hasPermission(p));
                break;
            }
        }




    }



}
