package com.slime.slimecommons;

import com.slime.slimecommons.listeners.PlayerInteractListener;
import com.slime.slimecommons.managers.YamlManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.UnknownDependencyException;
import org.bukkit.plugin.java.JavaPlugin;

public final class SlimeCommons extends JavaPlugin {
    private static SlimeCommons instance;
    private YamlManager configManager;

    public static SlimeCommons getInstance(){
        return instance;
    }
    public YamlManager getConfigManager() {
        return configManager;
    }

    public SlimeCommons(){
        instance = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        loadConfig();
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
    }

    private void loadConfig(){
        configManager = new YamlManager(this,"config.yml");
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