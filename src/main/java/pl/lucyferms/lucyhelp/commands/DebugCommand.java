package pl.lucyferms.lucyhelp.commands;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import pl.lucyferms.lucyhelp.data.LangPL;
import pl.lucyferms.lucyhelp.essentials.ChatUtils;
import pl.lucyferms.lucyhelp.essentials.Utils;
import pl.lucyferms.lucyhelp.help.Help;
import pl.lucyferms.lucyhelp.help.Helps;
import pl.lucyferms.lucyhelp.help.Labels;

import java.util.Objects;

public class DebugCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if( !(sender.hasPermission("LucyHelp.command.reload"))){
            ChatUtils.sendMessage(sender, "&4Brak uprawnien");
            return true;
        }
//        if( args.length == 1 && args[0].equalsIgnoreCase("book")) {
//            Player player = (Player) sender;
//
//            String materialName = "ENCHANTED_BOOK";
//            String enchantName = "unbreaking";
//            ItemStack itemStack = new ItemStack(Material.valueOf(materialName), 1);
//            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) itemStack.getItemMeta();
//            assert meta != null;
//            meta.addStoredEnchant(Objects.requireNonNull(Utils.getEnchantmentByCommonName(enchantName)), 3, true);
//            itemStack.setItemMeta(meta);
//            player.getInventory().addItem(itemStack);
//            return true;
//        }
        if( args.length == 1 && args[0].equalsIgnoreCase("reload")){
            LangPL.load();
            Helps.load();
            ChatUtils.sendMessage(sender, "&aPrzeladowano");
            return true;
        }
        ChatUtils.sendMessage(sender, "/" + label + " reload");


        return true;
    }
}
