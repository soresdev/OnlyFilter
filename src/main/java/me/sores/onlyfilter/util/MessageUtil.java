package me.sores.onlyfilter.util;

import me.sores.onlyfilter.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by sores on 7/31/2019.
 */
public class MessageUtil {

    public static void noPermission(Player player){
        message(player, StringUtil.color(Lang.NO_PERMISSION));
    }

    public static void message(Player player, String string){
        player.sendMessage(StringUtil.color(string));
    }

    public static void sendList(Player player, String[] args){
        player.sendMessage(args);
    }

    public static void log(String message){
        Bukkit.getConsoleSender().sendMessage(StringUtil.color(message));
    }

}
