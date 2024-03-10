package com.slime.slimecommons.commands;

import com.slime.slimecommons.Logging;
import com.slime.slimecommons.SlimeCommons;
import com.slime.slimecommons.utils.MessageBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AdminCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(command.getName().equalsIgnoreCase("slimecommons")){
            if(!commandSender.hasPermission("slimecommons.admin")){
                MessageBuilder messageBuilder = new MessageBuilder((String) SlimeCommons.getInstance().getMessageManager().getValue("errors.no_pex"));
                messageBuilder.replacePlaceholder("{prefix}", (String) SlimeCommons.getInstance().getMessageManager().getValue("prefix"));
                messageBuilder.send((Player) commandSender);
                return true;
            }

            if(strings[0].equalsIgnoreCase("reload")){
                cmdReload(commandSender);
            }

            if(strings[0].equalsIgnoreCase("debug")){
                cmdDebug(commandSender);
            }
        }

        return false;
    }

    private void cmdDebug(CommandSender commandSender) {
        SlimeCommons.getInstance().getConfigManager().setValue("debug", !(boolean) SlimeCommons.getInstance().getConfigManager().getValue("debug"));
        boolean value = (boolean) SlimeCommons.getInstance().getConfigManager().getValue("debug");

        if(commandSender instanceof Player){
            MessageBuilder messageBuilder = new MessageBuilder((String) SlimeCommons.getInstance().getMessageManager().getValue("admin.debug"));
            messageBuilder.replacePlaceholder("{prefix}", (String) SlimeCommons.getInstance().getMessageManager().getValue("prefix"));
            messageBuilder.replacePlaceholder("{value}", String.valueOf(value));

            messageBuilder.send((Player) commandSender);
        }

        Logging.log(SlimeCommons.getInstance(), "Debug: " + value);
    }

    private void cmdReload(CommandSender commandSender){
        if(commandSender instanceof Player){
            SlimeCommons.getInstance().getConfigManager().loadYamlFile();
            SlimeCommons.getInstance().getMessageManager().loadYamlFile();

            MessageBuilder messageBuilder = new MessageBuilder((String) SlimeCommons.getInstance().getMessageManager().getValue("admin.reload"));
            messageBuilder.replacePlaceholder("{prefix}", (String) SlimeCommons.getInstance().getMessageManager().getValue("prefix"));
            messageBuilder.send((Player) commandSender);
        }

        Logging.log(SlimeCommons.getInstance(), "Plugin ricaricato");
    }
}
