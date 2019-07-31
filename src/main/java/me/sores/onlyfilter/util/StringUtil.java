package me.sores.onlyfilter.util;

import org.bukkit.ChatColor;

/**
 * Created by sores on 7/31/2019.
 */
public class StringUtil {

    public static String color(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
