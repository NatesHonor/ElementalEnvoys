package com.nate.elemental.commands;

import com.nate.elemental.Envoys;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Create implements CommandExecutor {

    private final Envoys plugin;

    public Create() {
        this.plugin = Envoys.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be executed by players.");
            return true;
        }

        Player player = (Player) sender;
        if (args.length != 1 || !args[0].equalsIgnoreCase("axe")) {
            player.sendMessage(ChatColor.RED + "Usage: /create axe");
            return true;
        }

        ItemStack axe = createUniqueAxe();
        player.getInventory().addItem(axe);
        player.sendMessage(ChatColor.GREEN + "You've received a unique wooden axe!");

        return true;
    }

    private ItemStack createUniqueAxe() {
        ItemStack axe = new ItemStack(Material.WOODEN_AXE);
        ItemMeta meta = axe.getItemMeta();

        meta.setDisplayName(ChatColor.YELLOW + "Unique Axe");
        meta.setLore(Arrays.asList(ChatColor.GRAY + "A special axe with unique properties."));

        axe.setItemMeta(meta);
        return axe;
    }
}
