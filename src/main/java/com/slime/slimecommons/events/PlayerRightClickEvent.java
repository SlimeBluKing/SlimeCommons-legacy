package com.slime.slimecommons.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PlayerRightClickEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private boolean cancelled = false;
    private final Player player;
    private final ItemStack item;

    public PlayerRightClickEvent(Player player, ItemStack item){
        this.player = player;
        this.item = item;
    }

    public ItemStack getItem(){
        return item;
    }

    public Player getPlayer(){
        return player;
    }

    @Override
    public @NotNull HandlerList getHandlers(){
        return handlers;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }

    @Override
    public void setCancelled(boolean cancelled){
        this.cancelled = cancelled;
    }

    @Override
    public boolean isCancelled(){
        return cancelled;
    }
}
