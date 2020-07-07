package HoldMyAppleJuice;

import ProtocolPackage.Protocol;
import ProtocolPackage.communication.ServerMessage;
import com.mitch528.sockets.Sockets.Client;
import com.mitch528.sockets.events.*;
import jdk.internal.instrumentation.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class DiscordSync extends JavaPlugin
{

    public static DiscordSync plugin;
    public static Integer port;
    public static String ip;

    public static Client client = new Client("127.0.0.1", 1337);

    public DiscordSync()
    {
        plugin = this;
        FileConfiguration data = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "connection_data.yml"));
        //config = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "tab.yml"));

//        for (String s :  config.getConfigurationSection("tab").getKeys(false))
//        {
//            String role = config.getString("tab." + s);
//            String pref =  config.getString("tab." + s + ".pref");
//            String suf =  config.getString("tab." + s + ".suf");
//            names.put(Bukkit.getPlayer(UUID.fromString(s)), role);
//            prefs.put(role, pref);
//            sufs.put(role, suf);
//        }

        port = data.getInt("port");
        ip = data.getString("backend_address");


    }
    @Override
    public void onEnable()
    {

        client.getHandler().getConnected().addSocketConnectedEventListener(new SocketConnectedEventListener()
        {

            @Override
            public void socketConnected(SocketConnectedEvent evt)
            {
                // handshake
                client.SendMessage(new ClientMessage(Protocol.HANDSHAKE.toString(), "PLUGIN").format());
            }

        });

        client.getHandler().getMessage().addMessageReceivedEventListener(new MessageReceivedEventListener()
        {

            @Override
            public void messageReceived(MessageReceivedEvent evt)
            {
                ServerMessage message = new ServerMessage(evt.getMessage());
                if (message.header.equals(Protocol.USER_JOINED_VOICE))
                {

                }
                if (message.header.equals(Protocol.USER_LEFT_VOICE))
                {

                }
                if (message.header.equals(Protocol.USER_END_STREAM))
                {

                }
                if (message.header.equals(Protocol.USER_START_STREAM))
                {

                }

                if (message.header.equals(Protocol.USER_MUTED_HEADPHONES))
                {

                }
                if (message.header.equals(Protocol.USER_UNMUTED_HEADPHONES))
                {

                }
                if (message.header.equals(Protocol.USER_MUTED_MIC))
                {

                }
                if (message.header.equals(Protocol.USER_UNMUTED_MIC))
                {

                }
//                if (message.header.equals(Protocol.ALREADY_SYNCED))
//                {
//                    player.sendMessage("Этот аккаунт уже привязан к Discord!");
//                }
//                if (resp.equals("removed"))
//                {
//                    player.sendMessage("Успешно выполнено.");
//                }
//                if (resp .equals("not_in_list"))
//                {
//                    player.sendMessage("UUID не в списке.");
//                }
            }

        });

        client.getHandler().getDisconnected().addSocketDisconnectedEventListener(new SocketDisconnectedEventListener()
        {

            @Override
            public void socketDisconnected(SocketDisconnectedEvent evt)
            {

            }

        });





        PlayerEventHandler ph = new PlayerEventHandler();
        Bukkit.getPluginManager().registerEvents(ph, this);
        //Client.send("random", "random_data");

        getCommand("discord").setExecutor(new CommandSync());
        getCommand("discord").setTabCompleter(new dsTabComplete());

    }

    public static void handle()
    {

    }
}
