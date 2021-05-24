package pl.lucyferms.lucyhelp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import pl.lucyferms.lucyhelp.essentials.ChatUtils;
import pl.lucyferms.lucyhelp.essentials.Utils;
import pl.lucyferms.lucyhelp.help.Help;
import pl.lucyferms.lucyhelp.help.Helps;
import pl.lucyferms.lucyhelp.help.Labels;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand implements CommandExecutor, TabCompleter {



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if( args.length == 0 ){
            Helps.showHelp(label, sender, 1);
            return true;
        }
        if( args.length == 1){
            if( Utils.isInteger(args[0]) ){
                int site = Integer.parseInt(args[0]);
                Helps.showHelp(label, sender, site);
                return true;
            }
            else{
                Helps.showHelp(label, sender, 1, "" + args[0]);
                return true;
            }
        }
        else if( args.length == 2){
            if( Utils.isInteger(args[1]) ){
                int site = Integer.parseInt(args[1]);
                Helps.showHelp(label, sender, site, "" + args[0]);
                return true;
            }
            else{
                Helps.showHelp(label, sender, 1, "" + args[0]);
                return true;
            }
        }
        ChatUtils.sendMessage(sender, "/" + label + " <strona>");
        ChatUtils.sendMessage(sender, "/" + label + " <kategoria>");
        ChatUtils.sendMessage(sender, "/" + label + " <kategoria> <strona>");

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 1) {
            ArrayList<String> list = new ArrayList<>();
            ArrayList<String> result = new ArrayList<>();

            for( String l : Labels.getLabels() ){
                list.addAll(Labels.getAliases(l));
            }


            for( String alias : list) {
                if (alias.toLowerCase().startsWith(args[0].toLowerCase()) ) {
                    result.add(alias);
                }
            }
            return result;
        }

        return null;
    }
}
