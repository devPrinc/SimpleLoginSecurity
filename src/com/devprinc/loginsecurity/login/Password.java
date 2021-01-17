package com.devprinc.loginsecurity.login;

import org.bukkit.configuration.MemorySection;
import ru.tehkode.permissions.PermissionEntity;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import com.devprinc.loginsecurity.api.SecurityAPI;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import com.devprinc.loginsecurity.Main;
import org.bukkit.event.Listener;

public class Password implements Listener
{
    private Main plugin;
    
    public Password(final Main plugin) {
        this.setPlugin(plugin);
        Bukkit.getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    @EventHandler
    public static void write(final PlayerCommandPreprocessEvent playerCommandPreprocessEvent) {
        final Player player = ((PlayerEvent)playerCommandPreprocessEvent).getPlayer();
        final String name = ((PermissionEntity)PermissionsEx.getUser(player).getGroups()[0]).getName();
        if (SecurityAPI.logar.contains(player) && playerCommandPreprocessEvent.getMessage().startsWith("/stafflogin")) {
            if (!playerCommandPreprocessEvent.getMessage().equalsIgnoreCase("/stafflogin " + SecurityAPI.getSenha(name))) {
                player.sendMessage("");
                player.sendMessage("§a§lGREEN - SECURITY");
                player.sendMessage("§cSua senha está errada");
                player.sendMessage("§ctente novamente.");
                player.sendMessage("");
                player.sendMessage("§f* §e/stafflogin (senha)");
                player.sendMessage("");
            }
            else {
                SecurityAPI.logar.remove(player);
                player.sendMessage("");
                player.sendMessage(" §a§lGREEN - SECURITY");
                player.sendMessage("");
                player.sendMessage("&f* §eLogin feito com sucesso, bem-vindo!");
                player.sendMessage("");
            }
        }
    }
    
    public Main getPlugin() {
        return this.plugin;
    }
    
    public void setPlugin(final Main plugin) {
        this.plugin = plugin;
    }
}
