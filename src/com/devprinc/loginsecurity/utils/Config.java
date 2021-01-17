package com.devprinc.loginsecurity.utils;

import java.util.List;
import org.bukkit.configuration.InvalidConfigurationException;
import java.io.IOException;
import com.devprinc.loginsecurity.Main;
import org.bukkit.plugin.Plugin;
import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config extends YamlConfiguration
{
    private File bruteFile;
    private Plugin plugin;
    
    public static void createConfig(final String s) {
        if (!new File(Main.getInstance().getDataFolder(), String.valueOf(s) + ".yml").exists()) {
            Main.getInstance().saveResource(String.valueOf(s) + ".yml", false);
        }
    }
    
    public Config(final String s, final Plugin plugin) {
        this.plugin = plugin;
        this.bruteFile = new File(plugin.getDataFolder(), s.matches(".*(?i).yml$") ? s : s.concat(".yml"));
        try {
            if (!plugin.getDataFolder().exists()) {
                plugin.getDataFolder().mkdir();
            }
            if (!this.bruteFile.exists()) {
                this.bruteFile.createNewFile();
            }
            this.load(this.bruteFile);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (InvalidConfigurationException ex2) {
            ((Throwable)ex2).printStackTrace();
        }
    }
    
    public void saveDefault() {
        if (this.plugin.getResource(this.bruteFile.getName()) == null) {
            System.err.println("[" + this.plugin.getName() + "] Nao foi possivel salvar o arquivo");
            System.err.println("[" + this.plugin.getName() + "] default da config " + this.bruteFile.getName() + " pois o jar nao");
            System.err.println("[" + this.plugin.getName() + "] contem um arquivo com teste nome.");
        }
        else {
            this.plugin.saveResource(this.bruteFile.getName(), true);
        }
    }
    
    public void set(final String s) {
        super.set(s, (Object)"");
        this.save();
    }
    
    public Object get(final String s) {
        return super.get(s);
    }
    
    public String getString(final String s) {
        return super.getString(s);
    }
    
    public int getInt(final String s) {
        return super.getInt(s);
    }
    
    public boolean getBoolean(final String s) {
        return super.getBoolean(s);
    }
    
    public List<String> getStringList(final String s) {
        return (List<String>)super.getStringList(s);
    }
    
    public void save() {
        try {
            super.save(this.bruteFile);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void reload() {
        try {
            this.load(this.bruteFile);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (InvalidConfigurationException ex2) {
            ((Throwable)ex2).printStackTrace();
        }
    }
}
