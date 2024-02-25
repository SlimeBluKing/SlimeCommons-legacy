package com.slime.slimecommons.utils;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class BlockUtils {
    public static Block getTargetBlock(Player player, int maxDistance){
        return player.getTargetBlock(null, maxDistance);
    }

    public static boolean isBlockInRegion(Block block, String regionName){
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        ApplicableRegionSet regionSet = container.createQuery().getApplicableRegions(BukkitAdapter.adapt(block.getLocation()));

        for(ProtectedRegion region : regionSet){
            if(region.getId().equalsIgnoreCase(regionName)){
                return true;
            }
        }
        return false;
    }

    public static boolean isBlockInRegionLike(Block block, String regionName){
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        ApplicableRegionSet regionSet = container.createQuery().getApplicableRegions(BukkitAdapter.adapt(block.getLocation()));

        for(ProtectedRegion region : regionSet){
            if(region.getId().startsWith(regionName)){
                return true;
            }
        }
        return false;
    }

    public static List<Block> getBlocksInRadius(Location targetBlock, int radius, List<String> blacklistedMaterials){
        List<Block> cubeBlocks = new ArrayList<>();

        for(int x = -radius; x <= radius; x++){
            for(int y = -radius; y <= radius; y++){
                for(int z = -radius; z <= radius; z++){
                    Block blockToSave = targetBlock.getWorld().getBlockAt(targetBlock.clone().add(x, y, z));

                    if(checkMaterial(blockToSave, blacklistedMaterials)){
                        continue;
                    }

                    cubeBlocks.add(blockToSave);
                }
            }
        }

        return cubeBlocks;
    }

    public static List<Block> getBlocksInRadius(Location targetBlock, int radius){
        return getBlocksInRadius(targetBlock, radius, new ArrayList<>());
    }

    public static List<Block> getSphereInRadius(Location targetBlock, int radius, List<String> blacklistedMaterials){
        List<Block> sphereBlocks = new ArrayList<>();

        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius ; z++) {
                    if(Math.sqrt((x * x) + (y * y) + (z * z)) <= radius){
                        Block blockToSave = targetBlock.getWorld().getBlockAt(x + targetBlock.getBlockX(), y + targetBlock.getBlockY(), z + targetBlock.getBlockZ());

                        if(checkMaterial(blockToSave, blacklistedMaterials)){
                            continue;
                        }

                        sphereBlocks.add(blockToSave);
                    }
                }
            }
        }

        return sphereBlocks;
    }

    public static List<Block> getSphereInRadius(Location targetBlock, int radius){
        return getSphereInRadius(targetBlock, radius, new ArrayList<>());
    }

    public static boolean checkMaterial(Block block, List<String> blacklistedMaterials){
        for(String materialName : blacklistedMaterials){
            try{
                Material material = Material.valueOf(materialName.toUpperCase());

                if(block.getType() == material){
                    return true;
                }
            } catch (IllegalArgumentException e){
                Bukkit.getLogger().log(Level.SEVERE, e.toString());
            }
        }
        return false;
    }
}
