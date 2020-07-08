package HoldMyAppleJuice;

import ProtocolPackage.Protocol;
import ProtocolPackage.communication.ClientMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSync implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player))return false;

        Player player = (Player)commandSender;

        if (strings.length == 1 && strings[0].equals("sync"))
        {
            String[] st = new String[]{"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m"};
            String out = "";
            for (int i = 0; i < 8; i++)
            {
                out+=st[(int) Math.floor(st.length*Math.random())];
            }

            DiscordSync.send(new ClientMessage(Protocol.PLUGIN_GENERATED_CODE, player.getUniqueId().toString(), out).format());
            player.sendMessage("Ваш код:\n" + ChatColor.UNDERLINE + out );

        }
        if (strings.length == 2 && strings[0].equals("deattach"))
        {
            if (!player.isOp())return false;
            Player p = Bukkit.getPlayer(strings[1]);
            if (p==null)
            {
                player.sendMessage("Такого игрока не существует " + strings[1]);
                return false;
            }
            DiscordSync.send(new ClientMessage( Protocol.UNATTACH, p.getUniqueId().toString()).format());

        }
        return true;


    }
}
