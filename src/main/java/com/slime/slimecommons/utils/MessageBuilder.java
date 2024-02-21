package com.slime.slimecommons.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import me.clip.placeholderapi.PlaceholderAPI;

public class MessageBuilder {
    private String msg;

    public MessageBuilder(String message) {
        msg = translateColors(message);
    }

    public void parsePapi(Player player) {
        msg = PlaceholderAPI.setPlaceholders(player, msg);
    }

    private String translateColors(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public void replacePlaceholder(String placeholder, String value) {
        msg = msg.replace(placeholder, value);
    }

    public void send(Player player) {
        player.sendMessage(msg);
    }

    public String getMsg(){
        return msg;
    }
}