package me.maxed.cspy.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.maxed.cspy.CSpy;
import me.maxed.cspy.Lang;
import me.maxed.cspy.Playerdata;

public class CommandSend implements Listener {
	
	private CSpy plugin;
	
	public CommandSend(CSpy plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onCommandSend(PlayerCommandPreprocessEvent event) {
		
		Player player = event.getPlayer();
		
		if(player.hasPermission(plugin.getCfg().getCSpyBypassPerm())) return;
		
		Playerdata data = plugin.getPlayerdata();
		
		Lang lang = plugin.getLang();
		for(Player target : Bukkit.getOnlinePlayers()) {
			if(data.isEnabledCSpy(target)) {
				target.sendMessage(lang.getMessageStyle(player, event.getMessage()));
			}
		}
		
	}
	
}
