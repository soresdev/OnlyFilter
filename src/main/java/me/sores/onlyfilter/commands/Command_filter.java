package me.sores.onlyfilter.commands;

import me.sores.onlyfilter.OnlyFilter;
import me.sores.onlyfilter.config.Config;
import me.sores.onlyfilter.config.FilteredWords;
import me.sores.onlyfilter.util.MessageUtil;
import me.sores.onlyfilter.util.StringUtil;
import me.sores.onlyfilter.util.configuration.ConfigFile;
import me.sores.onlyfilter.util.filter.Filter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by sores on 7/31/2019.
 */
public class Command_filter implements CommandExecutor {

    private Filter filter;
    private ConfigFile config;
    private String[] usage;

    public Command_filter() {
        filter = OnlyFilter.getFilter();
        config = Config.getConfigFile();

        usage = new String[]{
                StringUtil.color("&8&m------------------------------------------------"),
                StringUtil.color("&a&lFilter Usage:"),
                StringUtil.color("&e/filter toggle <on/off> &f- Toggle the chat filter on/off."),
                StringUtil.color("&e/filter add <word> &f- Add a word to the filtered words list."),
                StringUtil.color("&e/filter remove <word> &f- Remove a word from the words list."),
                StringUtil.color("&e/filter exempt <name> &f- Add/Remove a player on exempt list."),
                StringUtil.color("&e/filter mode <cancel/replace> &f- Set the mode of the Chat Filter."),
                StringUtil.color("&e/filter status &f- View the current status of the Chat Filter."),
                StringUtil.color("&e/filter list &f- List the filtered words."),
                StringUtil.color("&8&m------------------------------------------------")
        };
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            if(args.length == 0){
                MessageUtil.sendList(player, usage);
                return true;
            }

            String base = args[0].toLowerCase();

            switch (base){
                case "toggle":{
                    if(args.length < 2){
                        MessageUtil.message(player, "&cUsage: /filter toggle <on/off>");
                        return true;
                    }

                    String input = args[1].toLowerCase();

                    if(input.equals("off")){
                        if(!filter.isActive()){
                            MessageUtil.message(player, "&cThe chat filter is currently disabled.");
                            return true;
                        }else{
                            filter.setActive(false);
                            config.set("options.filter.enabled", false);
                            config.save();

                            new Config();
                            MessageUtil.message(player, "&7You have toggled the chat filter &coff&7.");
                            return true;
                        }
                    }

                    if(input.equals("on")){
                        if(filter.isActive()){
                            MessageUtil.message(player, "&cThe chat filter is currently enabled.");
                            return true;
                        }else{
                            filter.setActive(true);
                            config.set("options.filter.enabled", true);
                            config.save();

                            new Config();
                            MessageUtil.message(player, "&7You have toggled the chat filter &aon&7.");
                            return true;
                        }
                    }

                    break;
                }

                case "add":{
                    if(args.length < 2){
                        MessageUtil.message(player, "&cUsage: /filter add <word>");
                        return true;
                    }

                    String input = args[1];
                    List<String> list = FilteredWords.getWordsFile().getStringList("filtered_words");

                    if(list.contains(input)){
                        MessageUtil.message(player, "&cThat word is already on the list.");
                        return true;
                    }

                    list.add(input);
                    FilteredWords.getWordsFile().set("filtered_words", list);
                    FilteredWords.getWordsFile().save();

                    new FilteredWords();
                    MessageUtil.message(player, "&aYou have added &e" + input + " &ato the filtered words list.");
                    break;
                }

                case "remove":{
                    if(args.length < 2){
                        MessageUtil.message(player, "&cUsage: /filter remove <word>");
                        return true;
                    }

                    String input = args[1];
                    List<String> list = FilteredWords.getWordsFile().getStringList("filtered_words");

                    if(!list.contains(input)){
                        MessageUtil.message(player, "&cThat word is not on the filtered words list.");
                        return true;
                    }

                    list.remove(input);
                    FilteredWords.getWordsFile().set("filtered_words", list);
                    FilteredWords.getWordsFile().save();

                    new FilteredWords();
                    MessageUtil.message(player, "&aYou have removed &e" + input + " &afrom the filtered words list.");
                    break;
                }

                case "exempt":{
                    if(args.length < 2){
                        MessageUtil.message(player, "&cUsage: /filter exempt <username>");
                        return true;
                    }

                    String name = args[1];
                    List<String> exempt = Config.getConfigFile().getStringList("options.exempt.users");

                    if(exempt.contains(name)){
                        exempt.remove(name);
                        Config.getConfigFile().getStringList("options.exempt.users").remove(name);
                        config.set("options.exempt.users", exempt);
                        config.save();

                        new Config();
                        MessageUtil.message(player, "&aYou have removed the user &e" + name + " &afrom the exempt users list.");
                        return true;
                    }

                    exempt.add(name);
                    config.set("options.exempt.users", exempt);
                    config.save();

                    new Config();
                    MessageUtil.message(player, "&aYou have added the user &e" + name + " &ato the exempt users list.");
                    break;
                }

                case "mode":{
                    if(args.length < 2){
                        MessageUtil.message(player, "&cUsage: /filter mode <cancel/replace>");
                        return true;
                    }

                    String input = args[1].toLowerCase();

                    if(input.equals("cancel")){
                        if(filter.getMode().toLowerCase().equals(input)){
                            MessageUtil.message(player, "&cThe chat filter is already in cancel mode.");
                            return true;
                        }else{
                            filter.setMode(input.toUpperCase());
                            config.set("options.filter.mode", input.toUpperCase());
                            config.save();

                            new Config();
                            MessageUtil.message(player, "&aYou have set the filter mode to " + filter.getMode() + ".");
                            return true;
                        }
                    }

                    if(input.equals("replace")){
                        if(filter.getMode().toLowerCase().equals(input)){
                            MessageUtil.message(player, "&cThe chat filter is already in replace mode.");
                            return true;
                        }else{
                            filter.setMode(input.toUpperCase());
                            config.set("options.filter.mode", input.toUpperCase());
                            config.save();

                            new Config();
                            MessageUtil.message(player, "&aYou have set the filter mode to " + filter.getMode() + ".");
                            return true;
                        }
                    }

                    if(!input.equals("replace") || !input.equals("cancel")){
                        MessageUtil.message(player, "&cUsage: /filter mode <cancel/replace>");
                        return true;
                    }

                    break;
                }

                case "status":{

                    String[] status = {
                            StringUtil.color("&8&m------------------------------------------------"),
                            StringUtil.color("&a&lFilter Status:"),
                            StringUtil.color("&eEnabled: &f" + Config.FILTER_ENABLED),
                            StringUtil.color("&eCurrent Mode: &f" + Config.FILTER_MODE),
                            StringUtil.color("&8&m------------------------------------------------")
                    };

                    MessageUtil.sendList(player, status);
                    break;
                }

                case "list":{

                    String listWords = "";
                    String listPhrases = "";
                    for(int i = 0; i < FilteredWords.FILTERED_WORDS.size(); i++){
                        if(i == FilteredWords.FILTERED_WORDS.size()-1){
                            listWords += StringUtil.color("&e" + FilteredWords.FILTERED_WORDS.get(i));
                        }else{
                            listWords += StringUtil.color("&e" + FilteredWords.FILTERED_WORDS.get(i) + "&7, ");
                        }
                    }

                    for(int i = 0; i < FilteredWords.FILTERED_PHRASES.size(); i++){
                        if(i == FilteredWords.FILTERED_PHRASES.size()-1){
                            listPhrases += StringUtil.color("&e" + FilteredWords.FILTERED_PHRASES.get(i));
                        }else{
                            listPhrases += StringUtil.color("&e" + FilteredWords.FILTERED_PHRASES.get(i) + "&7, ");
                        }
                    }

                    MessageUtil.message(player,"&8&m---------------------------------------------------");
                    MessageUtil.message(player, "&a&lFiltered Words: " + listWords);
                    MessageUtil.message(player,"&8&m---------------------------------------------------");
                    MessageUtil.message(player, "&a&lFiltered Phrases: " + listPhrases);
                    MessageUtil.message(player,"&8&m---------------------------------------------------");

                    break;
                }

                default:{
                    MessageUtil.sendList(player, usage);
                    break;
                }
            }
        }
        return false;
    }
}
