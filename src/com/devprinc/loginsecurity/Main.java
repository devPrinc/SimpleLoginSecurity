package com.devprinc.loginsecurity;

import org.bukkit.Bukkit;
import com.devprinc.loginsecurity.Listener.DebugListener;
import com.devprinc.loginsecurity.login.Login;
import com.devprinc.loginsecurity.login.Password;
import com.devprinc.loginsecurity.utils.Config;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
    private static Main instance;
    
    public void onEnable() {
        setInstance(this);
        this.registerListeners();
        Config.createConfig("config");
    }
    
    public void onDisable() {
    }
    
    void registerListeners() {
        new Login(this);
        new DebugListener(this);
        new Password(this);
    }
    
    public static void debug(final String s) {
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§b§lLOGIN SECURITY");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§aChecking plugin..");
        Bukkit.getConsoleSender().sendMessage("§bEnabled!");
        Bukkit.getConsoleSender().sendMessage("");
    }
    
    public static Main getInstance() {
        return Main.instance;
    }
    
    public static void setInstance(final Main instance) {
        Main.instance = instance;
    }
}
