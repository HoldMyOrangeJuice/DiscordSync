package HoldMyAppleJuice;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class dsTabComplete implements TabCompleter {
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        ArrayList<String> op = new ArrayList<String>();
        if ("sync".contains(strings[0]) && strings.length == 1)
        {
            op.add("sync");
        }
        if ("deattach".contains(strings[0]) && commandSender.isOp() && strings.length == 1)
        {
            op.add("deattach");
        }
        return op;
    }
}
