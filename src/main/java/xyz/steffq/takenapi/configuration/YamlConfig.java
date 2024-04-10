package xyz.steffq.takenapi.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class YamlConfig {

    private final Plugin plugin;
    private final String fileName;

    private File file;
    private FileConfiguration fileConfiguration;

    public YamlConfig(@NotNull final Plugin plugin, @NotNull String fileName) {
        this.plugin = plugin;

        if (!fileName.endsWith(".yml")) fileName += ".yml";
        this.fileName = fileName;

        save();
        reload();
    }

    private @NotNull File file() {
        return new File(plugin.getDataFolder(), fileName);
    }

    public FileConfiguration options() { return fileConfiguration; }

    public void saveFile() {
        if (fileConfiguration == null || file == null) return;
        try { fileConfiguration.save(file); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public void save() {
        if (file == null) file = file();
        if (file.exists()) return;
        plugin.saveResource(fileName, false);
    }

    public void reload() {
        fileConfiguration = YamlConfiguration.loadConfiguration(file());
    }

}
