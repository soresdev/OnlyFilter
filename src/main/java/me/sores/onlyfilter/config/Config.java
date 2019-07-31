package me.sores.onlyfilter.config;

import me.sores.onlyfilter.util.configuration.ConfigFile;

import java.util.List;

/**
 * Created by sores on 7/31/2019.
 */
public class Config {

    private static ConfigFile configFile;

    public static boolean FILTER_ENABLED;
    public static String FILTER_MODE;

    public static List<String> EXEMPT_USERS;

    public Config() {
        ConfigFile config = new ConfigFile("config.yml");

        FILTER_ENABLED = config.getBoolean("options.filter.enabled");
        FILTER_MODE = config.getString("options.filter.mode");
        EXEMPT_USERS = config.getStringList("options.exempt.users");

        configFile = config;
    }

    public static ConfigFile getConfigFile() {
        return configFile;
    }
}
