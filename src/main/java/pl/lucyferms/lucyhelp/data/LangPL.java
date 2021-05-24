package pl.lucyferms.lucyhelp.data;

import org.bukkit.configuration.file.YamlConfiguration;
import pl.lucyferms.lucyhelp.essentials.ConfigUtils;
import pl.lucyferms.lucyhelp.essentials.Debug;

public class LangPL {

    public static String helpHeader = "========Help========";
    public static String helpFooter = "&a%previousPage% &2Strona %page% z %maxpage% &a%nextPage%";
    public static String previousPage = "<<";
    public static String nextPage = ">>";
    public static String helpPageHoverMessage = "Przejdź do strony %page%";
    public static String helpFormat = "&a/%label% %arg%&f: &3%description%";
    public static String helpHoverDataFormat = "&c(%permission%)";
    public static String noData = "Brak danych, zglos ten fakt administracji";
    public static String noDataForThisLabel = "Brak danych natemat %label%, sprawdz pomoc ogolna lub zadaj pytanie administracji tutaj, bądź na naszym discordzie discord.gg/trollcraft";


    public static void load(){
        YamlConfiguration langPL = ConfigUtils.load("langPL.yml");

        if( langPL == null ){
            Debug.sendError("Blad pliku langPL.yml");
            return;
        }
        if( !langPL.contains("helpHeader") ){
            Debug.sendError("Brak wymaganych danych w langPL.yml");
            return;
        }
        helpHeader = langPL.getString("helpHeader");
        helpFooter = langPL.getString("helpFooter");
        previousPage = langPL.getString("previousPage");
        nextPage = langPL.getString("nextPage");
        helpPageHoverMessage = langPL.getString("helpPageHoverMessage");
        helpFormat = langPL.getString("helpFormat");
        helpHoverDataFormat = langPL.getString("helpHoverDataFormat");
        noData = langPL.getString("noData");
        noDataForThisLabel = langPL.getString("noDataForThisLabel");

    }

}
