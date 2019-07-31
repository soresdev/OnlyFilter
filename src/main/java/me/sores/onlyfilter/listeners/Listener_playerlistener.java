package me.sores.onlyfilter.listeners;

import me.sores.onlyfilter.OnlyFilter;
import me.sores.onlyfilter.config.Config;
import me.sores.onlyfilter.config.FilteredWords;
import me.sores.onlyfilter.config.Lang;
import me.sores.onlyfilter.util.MessageUtil;
import me.sores.onlyfilter.util.filter.Filter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by sores on 7/31/2019.
 */
public class Listener_playerlistener implements Listener {

    private Filter filter = OnlyFilter.getFilter();

    @EventHandler
    public void onChatFilter(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String message = event.getMessage();

        if(!filter.isActive()) return;
        if(event.isCancelled()) return;
        if(Config.EXEMPT_USERS.contains(player.getName())) return;

        FilteredWords.FILTERED_WORDS.forEach(word -> {
            if(message.toLowerCase().contains(word)){

                if(filter.getMode().equals("REPLACE")){
                    String selectedWord = FilteredWords.REPLACEABLE_WORDS.get(OnlyFilter.RAND.nextInt(FilteredWords.REPLACEABLE_WORDS.size()));
                    event.setMessage(message.replace(word, selectedWord));

                    Bukkit.getOnlinePlayers().forEach(online -> {
                        if(online.hasPermission("onlyfilter.see")){
                            MessageUtil.message(online, Lang.FILTER_PREFIX + Lang.MESSAGE_REPLACED.replace("%player%", player.getName()).replace("%replaced%", word));
                        }
                    });

                    return;
                }

                event.setCancelled(true);

                Bukkit.getOnlinePlayers().forEach(online -> {
                    if(online.hasPermission("onlyfilter.see")){
                        MessageUtil.message(online, Lang.FILTER_PREFIX + Lang.MESSAGE_FILTERED.replace("%player%", player.getName()).replace("%filtered%", message));
                    }
                });

            }
        });

        FilteredWords.FILTERED_PHRASES.forEach(phrase -> {
            if(message.toLowerCase().contains(phrase)){

                if(filter.getMode().equals("REPLACE")){
                    String selectedPhrase = FilteredWords.REPLACEABLE_PHRASES.get(OnlyFilter.RAND.nextInt(FilteredWords.REPLACEABLE_PHRASES.size()));
                    event.setMessage(selectedPhrase);

                    Bukkit.getOnlinePlayers().forEach(online -> {
                        if(online.hasPermission("onlyfilter.see")){
                            MessageUtil.message(online, Lang.FILTER_PREFIX + Lang.MESSAGE_REPLACED.replace("%player%", player.getName()).replace("%replaced%", phrase));
                        }
                    });

                    return;
                }

                event.setCancelled(true);

                Bukkit.getOnlinePlayers().forEach(online -> {
                    if(online.hasPermission("onlyfilter.see")){
                        MessageUtil.message(online, Lang.FILTER_PREFIX + Lang.MESSAGE_FILTERED.replace("%player%", player.getName()).replace("%filtered%", phrase));
                    }
                });
            }
        });
    }


}
