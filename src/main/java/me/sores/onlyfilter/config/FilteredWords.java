package me.sores.onlyfilter.config;

import me.sores.onlyfilter.util.configuration.ConfigFile;

import java.util.List;

/**
 * Created by sores on 7/31/2019.
 */
public class FilteredWords {

    private static ConfigFile wordsFile;

    public static List<String> FILTERED_WORDS;
    public static List<String> FILTERED_PHRASES;
    public static List<String> REPLACEABLE_WORDS;
    public static List<String> REPLACEABLE_PHRASES;

    public FilteredWords() {
        ConfigFile filteredWords = new ConfigFile("filtered_words.yml");

        FILTERED_WORDS = filteredWords.getStringList("filtered_words");
        FILTERED_PHRASES = filteredWords.getStringList("filtered_phrases");
        REPLACEABLE_WORDS = filteredWords.getStringList("replaceable_words");
        REPLACEABLE_PHRASES = filteredWords.getStringList("replaceable_phrases");

        wordsFile = filteredWords;
    }

    public static ConfigFile getWordsFile() {
        return wordsFile;
    }
}
