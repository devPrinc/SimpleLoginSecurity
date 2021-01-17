package com.devprinc.loginsecurity.login;

import org.bukkit.configuration.MemorySection;
import ru.tehkode.permissions.PermissionEntity;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import java.util.Iterator;
import org.bukkit.plugin.Plugin;
import com.devprinc.loginsecurity.utils.Metodos;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import com.devprinc.loginsecurity.api.SecurityAPI;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import org.bukkit.event.player.PlayerJoinEvent;
import com.devprinc.loginsecurity.Main;
import org.bukkit.event.Listener;

public class Login implements Listener
{
    private Main plugin;
    
    @EventHandler
    public static void onJoin(final PlayerJoinEvent playerJoinEvent) {
        final Player player = ((PlayerEvent)playerJoinEvent).getPlayer();
        final String name = ((PermissionEntity)PermissionsEx.getUser(player).getGroups()[0]).getName();
        final Iterator<String> iterator = ((MemorySection)Main.getInstance().getConfig()).getConfigurationSection("Ranks.").getKeys(false).iterator();
        while (iterator.hasNext()) {
            if (iterator.next().contains(name) && !SecurityAPI.logar.contains(player)) {
                SecurityAPI.logar.add(player);
                player.sendMessage("");
                player.sendMessage("§a§lGREEN - SECURITY");
                player.sendMessage("§7Indentificamos que você pertence");
                player.sendMessage("§7a nossa equipe, efetue o login");
                player.sendMessage("");
                player.sendMessage("§f* §e/stafflogin (senha)");
                player.sendMessage("");
                player.playSound(player.getLocation(), Sound.ANVIL_USE, 6.0f, 9.0f);
                new BukkitRunnable() {
                    public int numero = ((MemorySection)Main.getInstance().getConfig()).getInt("Times.TimeLogin");
                    
                    public void run() {
                        if (this.numero > 0) {
                            if (SecurityAPI.logar.contains(player)) {
                                --this.numero;
                                Metodos.ActionBar(player, "§a§lGREEN SECURITY §cRestam " + this.numero + "s para efetuar o login");
                            }
                            if (this.numero == 1) {
                                this.numero = 0;
                                SecurityAPI.logar.remove(player);
                                player.kickPlayer("§a§lGREEN SECURITY");
                                player.kickPlayer("§cVocê foi kickado do servidor.");
                            }
                        }
                    }
                }.runTaskTimer((Plugin)Main.getInstance(), 0L, 20L);
            }
        }
    }
    
    public Main getPlugin() {
        return this.plugin;
    }
    
    public void setPlugin(final Main plugin) {
        this.plugin = plugin;
    }
    
    public Login(final Main plugin) {
        this.setPlugin(plugin);
        Bukkit.getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
}
