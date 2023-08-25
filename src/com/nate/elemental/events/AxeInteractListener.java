package com.nate.elemental.events;

import com.nate.elemental.Envoys;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class AxeInteractListener implements Listener {

    private final Envoys plugin;

    public AxeInteractListener(Envoys plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onAxeInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item == null || item.getType() != Material.WOODEN_AXE || !item.hasItemMeta()) {
            return;
        }

        if (!item.getItemMeta().hasLore()
                || !item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Unique Axe")) {
            return;
        }

        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            Location pos1 = event.getClickedBlock().getLocation();
            saveLocation("pos1", pos1, player);
            player.sendMessage(ChatColor.GREEN + "Position 1 set!");
        } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Location pos2 = event.getClickedBlock().getLocation();
            saveLocation("pos2", pos2, player);
            player.sendMessage(ChatColor.GREEN + "Position 2 set!");
        }
    }

    private void saveLocation(String positionName, Location location, Player player) {
        File file = new File(plugin.getDataFolder(), "envoylocation.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        plugin.getConfig().set("locations." + positionName + ".world", location.getWorld().getName());
        plugin.getConfig().set("locations." + positionName + ".x", location.getX());
        plugin.getConfig().set("locations." + positionName + ".y", location.getY());
        plugin.getConfig().set("locations." + positionName + ".z", location.getZ());
        plugin.getConfig().set("locations." + positionName + ".yaw", location.getYaw());
        plugin.getConfig().set("locations." + positionName + ".pitch", location.getPitch());
        plugin.saveConfig();
    }
}
