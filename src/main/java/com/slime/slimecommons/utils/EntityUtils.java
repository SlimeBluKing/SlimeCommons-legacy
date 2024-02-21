package com.slime.slimecommons.utils;

import org.bukkit.Location;

import java.util.Objects;

public class EntityUtils {
    public static void strikeLightning(Location location) {
        Objects.requireNonNull(location.getWorld()).strikeLightning(location);
    }
}
