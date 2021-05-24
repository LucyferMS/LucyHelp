package pl.lucyferms.lucyhelp.help;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import pl.lucyferms.lucyhelp.data.LangPL;
import pl.lucyferms.lucyhelp.essentials.ChatUtils;
import pl.lucyferms.lucyhelp.essentials.ConfigUtils;
import pl.lucyferms.lucyhelp.essentials.Debug;
import pl.lucyferms.lucyhelp.essentials.Utils;

import java.util.*;

public class Helps {

    private static List<Help> helps = new ArrayList<>();

    public static List<Help> getHelps(){
        return helps;
    }
    public static void addHelp(Help help){
        helps.add(help);
    }

    public static void load(){
        helps.clear();
        YamlConfiguration commands = ConfigUtils.load("commands.yml");
        if( commands == null ){
            Debug.sendError("Blad pliku commands.yml");
            return;
        }
        if( !commands.contains("commands") ){
            Debug.sendError("Nie znalazlem zadnych komend");
            return;
        }
        for( String label : commands.getConfigurationSection("commands").getKeys(false)){
            if( commands.contains( "commands." + label + ".main")  ){
                String permission = commands.getString("commands." + label + ".main.permission");
                String description = commands.getString("commands." + label + ".main.description");
                addHelp(new Help(label, "noneArg", permission, description));
            }
            if( commands.contains( "commands." + label + ".args")  ) {
                for (String arg : commands.getConfigurationSection("commands." + label + ".args").getKeys(false)) {
                    String permission = commands.getString("commands." + label + ".args." + arg + ".permission");
                    String description = commands.getString("commands." + label + ".args." + arg + ".description");
                    addHelp(new Help(label, arg, permission, description));
                }
            }
            List<String> aliases = new ArrayList<>(Collections.singletonList("" + label));
            if( commands.contains( "commands." + label + ".aliases")  ) {
                aliases.addAll(commands.getStringList("commands." + label + ".aliases"));
            }
            Labels.addLabel(label, aliases);
        }
    }


    public static void showHelp(String label, CommandSender sender, int site, String...argument){

        TextComponent header = new TextComponent( Utils.color("" + LangPL.helpHeader) );
        String footer =  ("" + LangPL.helpFooter)
                .replace("%previousPage%", "" + LangPL.previousPage)
                .replace("%nextPage%", "" + LangPL.nextPage)
                ;

        if( helps.size() == 0 ){
            ChatUtils.sendMessage(sender, "" + LangPL.noData);
            return;
        }
        List<Help> inlineHelps = new ArrayList<>();
        if( argument.length != 0 ){
            for( String l : Labels.getLabels()){
                if( Labels.getAliases(l).contains("" + argument[0]) ){
                    for( Help help : helps ){
                        if( help.getLabel().equalsIgnoreCase(l) ){
                            inlineHelps.add(help);
                        }
                    }
                }
            }
            if( inlineHelps.size() == 0 ){
                ChatUtils.sendMessage(sender, "" + LangPL.noDataForThisLabel.replace("%label%", argument[0]));
                return;
            }
        }
        else{
            inlineHelps = helps;
        }


        int page;
        int maxpage = (int) Math.ceil(inlineHelps.size() / 18.00);
        if( maxpage == 0 ){
            maxpage = 1;
        }
        int current = 0;
        page = Math.min(maxpage, site);

        sender.spigot().sendMessage(header);

        for( Help help : inlineHelps ){
            if (current >= (page - 1) * 10 && current < page * 18) {
                sender.spigot().sendMessage( BuildHelp.build(help, sender) );

            }
            current++;
        }

        BaseComponent[] components = TextComponent.fromLegacyText(Utils.color(footer.replace("%maxpage%", maxpage + "").replace("%page%", page + "")));

        for (BaseComponent component : components) {
            if (ChatColor.stripColor(component.toLegacyText()).contains(LangPL.nextPage)) {
                if (page < maxpage) {
                    if( argument.length == 0 ){
                        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + label +  " " + (page + 1)));
                    }
                    else {
                        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + label + " " + argument[0] + " " + (page + 1)));
                    }
                    component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(LangPL.helpPageHoverMessage.replace("%page%", "" + (page + 1))).create()));
                }

            } else if (ChatColor.stripColor(component.toLegacyText()).contains(LangPL.previousPage)) {
                if (page > 1) {
                    if( argument.length == 0 ) {
                        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + label + " " + (page - 1)));
                    }
                    else {
                        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + label + " " + argument[0] + " " + (page - 1)));
                    }
                    component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(LangPL.helpPageHoverMessage.replace("%page%", "" + (page - 1))).create()));
                }
            }
        }
        sender.spigot().sendMessage(components);
































    }


}
