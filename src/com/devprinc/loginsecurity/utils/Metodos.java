package com.devprinc.loginsecurity.utils;

import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import java.util.ArrayList;

public class Metodos
{
    static ArrayList<String> arrayOrganizado;
    static ArrayList<Integer> arrayOrganizado2;
    
    static {
        Metodos.arrayOrganizado = new ArrayList<String>();
        Metodos.arrayOrganizado2 = new ArrayList<Integer>();
    }
    
    public static void ActionBar(final Player player, final String s) {
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + s + "\"}"), (byte)2));
    }
    
    public static void Titulo(final Player player, final String s, final String s2) {
        final PlayerConnection playerConnection = ((CraftPlayer)player).getHandle().playerConnection;
        final IChatBaseComponent a = IChatBaseComponent.ChatSerializer.a("{'text': '" + s + "'}");
        final IChatBaseComponent a2 = IChatBaseComponent.ChatSerializer.a("{'text': '" + s2 + "'}");
        final PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, a, 1, 20, 10);
        final PacketPlayOutTitle packetPlayOutTitle2 = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, a2);
        playerConnection.sendPacket((Packet)packetPlayOutTitle);
        playerConnection.sendPacket((Packet)packetPlayOutTitle2);
    }
}
