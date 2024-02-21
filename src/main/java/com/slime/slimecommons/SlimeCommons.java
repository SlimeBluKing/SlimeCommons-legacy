package com.slime.slimecommons;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.slime.slimecommons.listeners.PlayerInteractListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.UnknownDependencyException;
import org.bukkit.plugin.java.JavaPlugin;

public final class SlimeCommons extends JavaPlugin {
    private static WorldGuardPlugin worldGuard;
    private static SlimeCommons instance;

    public static WorldGuardPlugin getWorldGuard(){
        return worldGuard;
    }

    public static SlimeCommons getInstance(){
        return instance;
    }

    public SlimeCommons(){
        instance = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        loadConfig();
        saveConfig();
        start();
    }

    private void start(){
        getDependencies();
        getEvents();
        printLogo();
    }

    private void getEvents(){
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
    }

    private void getDependencies(){
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            getPluginLoader().disablePlugin(this);
            throw new UnknownDependencyException("PlaceholderAPI does not found");
        }

        if(Bukkit.getPluginManager().getPlugin("WorldGuard") == null) {
            getPluginLoader().disablePlugin(this);
            throw new UnknownDependencyException("WorldGuard does not found");
        }

        worldGuard = (WorldGuardPlugin)Bukkit.getPluginManager().getPlugin("WorldGuard");
    }

    private void loadConfig(){
        getConfig().options().parseComments(true);
        getConfig().options().copyDefaults(true);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void printLogo(){
        getLogger().info("   _____ _ _                 _____                                          ");
        getLogger().info("  / ____| (_)               / ____|                                         ");
        getLogger().info(" | (___ | |_ _ __ ___   ___| |     ___  _ __ ___  _ __ ___   ___  _ __  ___ ");
        getLogger().info("  \\___ \\| | | '_ ` _ \\ / _ \\ |    / _ \\| '_ ` _ \\| '_ ` _ \\ / _ \\| '_ \\/ __|");
        getLogger().info("  ____) | | | | | | | |  __/ |___| (_) | | | | | | | | | | | (_) | | | \\__ \\");
        getLogger().info(" |_____/|_|_|_| |_| |_|\\___|\\_____\\___/|_| |_| |_|_| |_| |_|\\___/|_| |_|___/");
        getLogger().info("                                                                            ");
    }
}