package me.sores.onlyfilter;

import me.sores.onlyfilter.commands.Command_filter;
import me.sores.onlyfilter.commands.Command_ofreload;
import me.sores.onlyfilter.config.Config;
import me.sores.onlyfilter.config.FilteredWords;
import me.sores.onlyfilter.config.Lang;
import me.sores.onlyfilter.listeners.Listener_playerlistener;
import me.sores.onlyfilter.util.MessageUtil;
import me.sores.onlyfilter.util.filter.Filter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

/**
 * Created by sores on 7/31/2019.
 */
public class OnlyFilter extends JavaPlugin {

    public static Random RAND = new Random();

    private static OnlyFilter instance;
    private static Filter filter;

    @Override
    public void onEnable(){
        instance = this;

        initClasses();
        registerListeners();
        registerCommands();

        startupMessage();
    }

    @Override
    public void onDisable(){
        instance = null;
    }

    private void initClasses(){
        new Config();
        new Lang();
        new FilteredWords();

        filter = new Filter();
    }


    private void registerCommands(){
        getCommand("ofreload").setExecutor(new Command_ofreload());
        getCommand("filter").setExecutor(new Command_filter());
    }

    private void registerListeners(){
        PluginManager manager = Bukkit.getPluginManager();

        manager.registerEvents(new Listener_playerlistener(), this);
    }

    private void startupMessage(){
        MessageUtil.log(" ");
        MessageUtil.log("&e&lOnlyFilter &r&eby sores has been enabled!");
        MessageUtil.log(" ");
    }

    public static Filter getFilter() {
        return filter;
    }

    public static OnlyFilter getInstance() {
        return instance;
    }
}
