package com.devprinc.loginsecurity.Listener;

import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import com.devprinc.loginsecurity.api.SecurityAPI;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.Sound;

import com.devprinc.loginsecurity.Main;
import org.bukkit.event.Listener;

public class DebugListener implements Listener
{
    private Main plugin;
    
    public DebugListener(final Main plugin) {
        this.setPlugin(plugin);
        Bukkit.getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    @EventHandler
    public void CommandEx(final PlayerCommandPreprocessEvent playerCommandPreprocessEvent) {
        final Player player = ((PlayerEvent)playerCommandPreprocessEvent).getPlayer();
        if (SecurityAPI.logar.contains(player)) {
            playerCommandPreprocessEvent.setCancelled(true);
            if (!playerCommandPreprocessEvent.getMessage().startsWith("/stafflogin") && !playerCommandPreprocessEvent.getMessage().startsWith("/log") && !playerCommandPreprocessEvent.getMessage().startsWith("/regist")) {
                player.sendMessage("");
                player.sendMessage("§a§lGREEN - SECURITY");
                player.sendMessage("§7Você está bloqueado");
                player.sendMessage("§7efetue login para ficar desbloqueado.");
                player.sendMessage("");
                player.sendMessage("§f* §e/stafflogin (senha)");
                player.sendMessage("");
                player.playSound(player.getLocation(), Sound.ANVIL_USE, 6.0f, 9.0f);
            }
        }
    }
    
    @EventHandler
    public void mexer(final PlayerMoveEvent playerMoveEvent) {
        if (SecurityAPI.logar.contains(((PlayerEvent)playerMoveEvent).getPlayer())) {
            playerMoveEvent.setCancelled(true);
        }
    }
    
    @EventHandler
    public void AoFalar(final AsyncPlayerChatEvent asyncPlayerChatEvent) {
        final Player player = ((PlayerEvent)asyncPlayerChatEvent).getPlayer();
        if (SecurityAPI.logar.contains(player)) {
            asyncPlayerChatEvent.setCancelled(true);
            player.sendMessage("");
            player.sendMessage("§a§lGREEN - SECURITY");
            player.sendMessage("§7Você está bloqueado");
            player.sendMessage("§7efetue login para ficar desbloqueado.");
            player.sendMessage("");
            player.sendMessage("§f* §e/stafflogin (senha)");
            player.sendMessage("");
            player.playSound(player.getLocation(), Sound.ANVIL_USE, 6.0f, 9.0f);
        }
    }
    
    public Main getPlugin() {
        return this.plugin;
    }
    
    public void setPlugin(final Main plugin) {
        this.plugin = plugin;
    }
}
