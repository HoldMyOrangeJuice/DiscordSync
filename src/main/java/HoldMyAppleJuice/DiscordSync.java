package HoldMyAppleJuice;

import ProtocolPackage.Protocol;
import ProtocolPackage.communication.ClientMessage;
import ProtocolPackage.communication.ServerMessage;
import com.mitch528.sockets.Sockets.Client;
import com.mitch528.sockets.events.*;
import jdk.internal.instrumentation.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.OutputStreamWriter;

public class DiscordSync extends JavaPlugin
{

    public static DiscordSync plugin;
    public static Integer port;
    public static String ip;
    public static ClientThread client;


    public DiscordSync()
    {
        plugin = this;
        FileConfiguration data = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "connection_data.yml"));

        port = data.getInt("port");
        ip = data.getString("backend_address");

        client = new ClientThread();
        client.start();

        System.out.println("\n\n\nConnected\n\n\n");


    }
    @Override
    public void onEnable() {
        send(new ClientMessage(Protocol.HANDSHAKE, "pl").format());
        PlayerEventHandler ph = new PlayerEventHandler();
        Bukkit.getPluginManager().registerEvents(ph, this);
        //Client.send("random", "random_data");

        getCommand("discord").setExecutor(new CommandSync());
        getCommand("discord").setTabCompleter(new dsTabComplete());
    }

    public static void send(String msg)
    {
        try {
            System.out.println("sending " + msg + " to server");
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.s.getOutputStream()));
            out.write(msg);
            out.flush();
        }
        catch (Exception e){e.printStackTrace();}
    }

    public static void handle(String raw)
    {
        System.out.println("got message from server: " + raw);
        ServerMessage message = new ServerMessage(raw);
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

    }

}
