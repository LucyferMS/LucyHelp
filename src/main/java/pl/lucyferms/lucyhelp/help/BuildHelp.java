package pl.lucyferms.lucyhelp.help;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import pl.lucyferms.lucyhelp.data.LangPL;
import pl.lucyferms.lucyhelp.essentials.ChatUtils;
import pl.lucyferms.lucyhelp.essentials.Utils;

public class BuildHelp {


    public static TextComponent build(Help h, CommandSender sender){
        String label = h.getLabel();
        String arg = h.getArg();
        String permission = h.getPermission();
        String description = h.getDescription();


        TextComponent permissionComponent = new TextComponent(Utils.color("" + LangPL.helpHoverDataFormat)
                .replace("%permission%", "" + permission)
        );
        String format = LangPL.helpFormat;
        if( arg.equalsIgnoreCase("noneArg") ){
            format = format.replace(" %arg%", "");
        }
        else{
            format = format.replace("%arg%", arg);
        }

        TextComponent help = new TextComponent( Utils.color("" + format)
        .replace("%label%", "" + label)
        .replace("%permission%", "" + permission)
        .replace("%description%", "" + description)
        );
        if( sender.hasPermission("LucyHelp.see.permission")) {
            help.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(permissionComponent).create()));
        }
        return help;

    }
}
