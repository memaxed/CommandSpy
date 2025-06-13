package me.maxed.cspy.commands.executors;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.maxed.cspy.CSpy;
import me.maxed.cspy.Cfg;
import me.maxed.cspy.Lang;
import me.maxed.cspy.logger.CommandLogger;
import me.maxed.cspy.logger.CommandLogger.Item;

public class CmdCHistory implements CommandExecutor {

	private CSpy plugin;
	
	public CmdCHistory(CSpy plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Lang lang = plugin.getLang();
		Cfg cfg = plugin.getCfg();
		CommandLogger logger = plugin.getCommandLogger();
		
		if(!sender.hasPermission(cfg.getCHistoryPerm())) {
			sender.sendMessage(lang.getNoPermissionsMessage());
			return true;
		}
		
		if(!cfg.isCommandLoggerEnabled()) {
			sender.sendMessage(lang.getCHistoryOff());
			return true;
		}
		
		if(args.length < 1) {
			sender.sendMessage(lang.getCHistoryUsage());
			return true;
		}
		
		String request = args[0];
		
		List<Item> list = logger.getCommandHistory(request, cfg.getCommandLoggerLimit());
		
		if(list.isEmpty()) {
			sender.sendMessage(lang.getCHistoryEmpty(request));
			return true;
		}
		
		String msg = prettyPrint(list, request);
		sender.sendMessage(msg);
		
		return true;
	}
	
	private String prettyPrint(List<Item> list, String name) {
		Lang lang = plugin.getLang();
		Cfg cfg = plugin.getCfg();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(lang.getCHistoryHeader(name, cfg.getCommandLoggerLimit()) + '\n');
		for(Item item : list) {
			sb.append(lang.getCHistoryItem(name, item.getTimestamp(), item.getCommand()) + '\n');
		}
		
		return sb.toString();
	}
	
}
