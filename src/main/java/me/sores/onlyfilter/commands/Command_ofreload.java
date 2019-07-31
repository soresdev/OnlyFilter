package me.sores.onlyfilter.commands;

import me.sores.onlyfilter.config.Config;
import me.sores.onlyfilter.config.FilteredWords;
import me.sores.onlyfilter.config.Lang;
import me.sores.onlyfilter.util.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by sores on 7/31/2019.
 */
public class Command_ofreload implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            String usage = "&cUsage: /ofreload <lang:config:words>";

            if(!player.hasPermission("onlyfilter.reload")){
                MessageUtil.noPermission(player);
                return true;
            }

            if(args.length == 0){
                MessageUtil.message(player, usage);
                return true;
            }

            String input = args[0];

            switch (input.toLowerCase()){
                case "lang":{
                    new Lang();
                    MessageUtil.message(player, Lang.RELOAD.replace("%file%", "Language"));
                    break;
                }

                case "config":{
                    new Config();
                    MessageUtil.message(player, Lang.RELOAD.replace("%file%", "Config"));
                    break;
                }

                case "words":{
                    new FilteredWords();
                    MessageUtil.message(player, Lang.RELOAD.replace("%file%", "Words"));
                    break;
                }

                default:{
                    MessageUtil.message(player, usage);
                    break;
                }
            }
        }
        return false;
    }
}
