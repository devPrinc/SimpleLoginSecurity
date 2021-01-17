package com.devprinc.loginsecurity.api;

import org.bukkit.configuration.MemorySection;
import com.devprinc.loginsecurity.Main;
import org.bukkit.entity.Player;
import java.util.ArrayList;

public class SecurityAPI
{
    public static ArrayList<Player> logar;
    
    static {
        SecurityAPI.logar = new ArrayList<Player>();
    }
    
    public static String getSenha(final String s) {
        return ((MemorySection)Main.getInstance().getConfig()).getString("Ranks" + s);
    }
}
