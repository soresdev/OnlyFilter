package me.sores.onlyfilter.util.configuration;

import me.sores.onlyfilter.OnlyFilter;
import me.sores.onlyfilter.util.StringUtil;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sores on 7/31/2019.
 */
public class ConfigFile extends YamlConfiguration {

    private File file;

    public ConfigFile(String name) {
        this.file = new File(OnlyFilter.getInstance().getDataFolder(), name);

        if(!this.file.exists()) {
            OnlyFilter.getInstance().saveResource(name, false);
        }

        try {
            this.load(this.file);
        } catch(IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            this.save(this.file);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getInt(String path) {
        return super.getInt(path, 0);
    }

    @Override
    public double getDouble(String path) {
        return super.getDouble(path, 0.0);
    }

    @Override
    public boolean getBoolean(String path) {
        return super.getBoolean(path, false);
    }

    @Override
    public String getString(String path) {
        return StringUtil.color(super.getString(path, ""));
    }

    @Override
    public List<String> getStringList(String path) {
        return super.getStringList(path).stream().map(StringUtil::color).collect(Collectors.toList());
    }

    public File getFile() {
        return file;
    }

}
