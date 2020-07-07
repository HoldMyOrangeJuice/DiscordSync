package HoldMyAppleJuice;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class DiscordSync extends JavaPlugin
{

    public static DiscordSync plugin;
    public static Integer port;
    public static String ip;
    public static FileConfiguration config;
    public static HashMap<Player, String> names = new HashMap<Player, String>();
    public static HashMap<String, String> prefs = new HashMap<String, String>();
    public static HashMap<String, String> sufs = new HashMap<String, String>();

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
        PlayerEventHandler ph = new PlayerEventHandler();
        Bukkit.getPluginManager().registerEvents(ph, this);
        //Client.send("random", "random_data");

        getCommand("discord").setExecutor(new CommandSync());
        getCommand("discord").setTabCompleter(new dsTabComplete());

    }
}
