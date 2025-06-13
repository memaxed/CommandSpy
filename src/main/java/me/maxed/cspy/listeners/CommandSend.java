package me.maxed.cspy.listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.maxed.cspy.CSpy;
import me.maxed.cspy.Cfg;
import me.maxed.cspy.Lang;
import me.maxed.cspy.Playerdata;
import me.maxed.cspy.logger.CommandLogger;

public class CommandSend implements Listener {
	
	private CSpy plugin;
	
	public CommandSend(CSpy plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onCommandSend(PlayerCommandPreprocessEvent event) {
		
		Cfg cfg = plugin.getCfg();
		Playerdata data = plugin.getPlayerdata();
		Lang lang = plugin.getLang();
		
		Player player = event.getPlayer();
		
		if(player.hasPermission(cfg.getCSpyBypassPerm())) return;
		
		List<String> ignore = cfg.getIgnoreCommands();
		String msg = event.getMessage().split(" ")[0].substring(1);
		
		if(ignore.contains(msg)) return;
		
		for(Player target : Bukkit.getOnlinePlayers()) {
			if(target.getUniqueId().equals(player.getUniqueId())) continue;
			
			if(data.isEnabledCSpy(target)) {
				target.sendMessage(lang.getMessageStyle(player, event.getMessage()));
			}
		}
		
	}
	
	@EventHandler
	public void log(PlayerCommandPreprocessEvent event) {
		Cfg cfg = plugin.getCfg();
		
		if(event.getPlayer().hasPermission(cfg.getCommandLoggerBypassPerm())) return;
		
		List<String> ignore = cfg.getIgnoreCommandsLogger();
		String msg = event.getMessage().split(" ")[0].substring(1);
		
		if(ignore.contains(msg)) return;
		
		CommandLogger logger = plugin.getCommandLogger();
		logger.logCommand(event.getPlayer(), event.getMessage());
	}
	
}
