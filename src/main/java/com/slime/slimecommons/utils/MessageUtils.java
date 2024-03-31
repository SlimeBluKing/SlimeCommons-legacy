package com.slime.slimecommons.utils;

import org.bukkit.ChatColor;

public class MessageUtils {
    public static String Colorize(String value){
        return ChatColor.translateAlternateColorCodes('&', value);
    }
}
