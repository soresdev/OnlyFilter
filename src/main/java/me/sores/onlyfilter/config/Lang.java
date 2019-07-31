package me.sores.onlyfilter.config;

import me.sores.onlyfilter.util.configuration.ConfigFile;

/**
 * Created by sores on 7/31/2019.
 */
public class Lang {

    private static ConfigFile langFile;

    public static String NO_PERMISSION;
    public static String FILTER_PREFIX;
    public static String RELOAD;
    public static String MESSAGE_REPLACED;
    public static String MESSAGE_FILTERED;

    public Lang() {
        ConfigFile lang = new ConfigFile("lang.yml");

        NO_PERMISSION = lang.getString("no_permission");
        FILTER_PREFIX = lang.getString("prefix");
        RELOAD = lang.getString("reload");
        MESSAGE_FILTERED = lang.getString("message_filtered");
        MESSAGE_REPLACED = lang.getString("message_replaced");

        langFile = lang;
    }

    public static ConfigFile getLangFile() {
        return langFile;
    }
}
