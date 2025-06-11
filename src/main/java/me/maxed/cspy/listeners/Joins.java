package me.maxed.cspy.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.maxed.cspy.CSpy;
import me.maxed.cspy.Playerdata;

public class Joins implements Listener {
	
	private CSpy plugin;
	public Joins(CSpy plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		Playerdata data = plugin.getPlayerdata();
		Player player = event.getPlayer();
		if(data.containsPlayer(player)) {
			data.createPlayer(player);
		}
		
	}
	
}
