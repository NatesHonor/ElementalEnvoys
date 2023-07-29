package com.nate.elemental;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.nate.elemental.commands.Create;

public class Envoys extends JavaPlugin implements Listener, CommandExecutor {
    private static Envoys instance;
    boolean usePackets = false;

    public static Envoys getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        getCommand("envoy").setExecutor(this);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("envoy")) {
            if (!sender.hasPermission("envoy.admin") || args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Envoy starts in:");
            }
            return true;
        } else if (args.length >= 1) {
            String subCommand = args[0].toLowerCase();
            switch (subCommand) {
                case "invite":
                    Create create = new Create();
                    create.onCommand(sender, command, label, args);
                    break;
                default:
                    sender.sendMessage("&cUnknown command: " + subCommand);
                    break;
            }
            return true;
        }
        return false;
    }

    @Override
    public void onDisable() {

    }

}
