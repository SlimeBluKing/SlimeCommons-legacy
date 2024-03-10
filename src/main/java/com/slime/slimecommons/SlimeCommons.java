package com.slime.slimecommons;

import com.slime.slimecommons.commands.AdminCommands;
import com.slime.slimecommons.commands.tabcompleter.AdminCommandsCompleter;
import com.slime.slimecommons.listeners.PlayerInteractListener;
import com.slime.slimecommons.managers.YamlManager;
import jdk.jfr.consumer.RecordedThread;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.UnknownDependencyException;
import org.bukkit.plugin.java.JavaPlugin;

import javax.print.DocFlavor;
import java.util.Objects;

public final class SlimeCommons extends JavaPlugin {
    private static SlimeCommons instance;
    private YamlManager configManager;
    private YamlManager messageManager;
    private Economy economy;

    public static SlimeCommons getInstance(){
        return instance;
    }
    public YamlManager getConfigManager() {
        return configManager;
    }
    public YamlManager getMessageManager() {
        return messageManager;
    }
    public Economy getEconomy(){
        return economy;
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
        getCommands();
        printLogo();
    }

    private void getEvents(){
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
    }

    private void getCommands() {
        Objects.requireNonNull(getCommand("slimecommons")).setExecutor(new AdminCommands());
        Objects.requireNonNull(getCommand("slimecommons")).setTabCompleter(new AdminCommandsCompleter());
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

        if (Bukkit.getPluginManager().getPlugin("Vault") == null){
            getPluginLoader().disablePlugin(this);
            throw new UnknownDependencyException("Vault does not found");
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if(rsp == null){
            getPluginLoader().disablePlugin(this);
            throw new UnknownDependencyException("Economy with Vault does not found");
        }
        economy = rsp.getProvider();
    }

    private void loadConfig(){
        configManager = new YamlManager(this,"config.yml");
        messageManager = new YamlManager(this, "messages.yml");
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