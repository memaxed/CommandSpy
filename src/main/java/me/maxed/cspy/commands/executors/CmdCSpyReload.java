package me.maxed.cspy.commands.executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.maxed.cspy.CSpy;
import me.maxed.cspy.Cfg;
import me.maxed.cspy.Lang;

public class CmdCSpyReload implements CommandExecutor {

	private CSpy plugin;
	
	public CmdCSpyReload(CSpy plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Cfg cfg = plugin.getCfg();
		Lang lang = plugin.getLang();
		if(!(sender.hasPermission(cfg.getCSpyReloadPerm()))) {
			sender.sendMessage(lang.getNoPermissionsMessage());
			return true;
		}
		
		plugin.reload();
		sender.sendMessage(lang.getReloaded());
		
		return true;
	}
	
}
