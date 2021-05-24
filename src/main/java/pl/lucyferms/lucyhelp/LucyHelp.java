package pl.lucyferms.lucyhelp;

import org.bukkit.plugin.java.JavaPlugin;
import pl.lucyferms.lucyhelp.commands.DebugCommand;
import pl.lucyferms.lucyhelp.commands.HelpCommand;
import pl.lucyferms.lucyhelp.data.LangPL;
import pl.lucyferms.lucyhelp.help.Helps;

public final class LucyHelp extends JavaPlugin {

    private static LucyHelp instance;

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic

        LangPL.load();
        Helps.load();

        getCommand("ahelp").setExecutor(new DebugCommand());
        getCommand("hhelp").setExecutor(new HelpCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static LucyHelp getInstance(){
        return instance;
    }
}
