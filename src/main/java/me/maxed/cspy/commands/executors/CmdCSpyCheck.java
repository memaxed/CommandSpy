package me.maxed.cspy.commands.executors;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.maxed.cspy.CSpy;
import me.maxed.cspy.Cfg;
import me.maxed.cspy.Lang;
import me.maxed.cspy.Playerdata;

public class CmdCSpyCheck implements CommandExecutor {

	private CSpy plugin;
	
	public CmdCSpyCheck(CSpy plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Lang lang = plugin.getLang();
		Cfg cfg = plugin.getCfg();
		Playerdata data = plugin.getPlayerdata();
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(lang.getWorkWithPlayerMessage());
			return true;
		}
		
		Player player = ((Player) sender).getPlayer();
		if(!player.hasPermission(cfg.getCSpyCheckPerm())) {
			player.sendMessage(lang.getNoPermissionsMessage(player));
			return true;
		}
		
		int len = args.length;
		
		if(len == 0) {
			boolean check = data.isEnabledCSpy(player);
			if(check) {
				player.sendMessage(lang.getCSpyCheckEnabledMessage(player));
				return true;
			}
			player.sendMessage(lang.getCSpyCheckDisabledMessage(player));
			return true;
		} else {
			if(!(player.hasPermission(cfg.getCSpyCheckOtherPerm()))) {
				player.sendMessage(lang.getNoPermissionsMessage(player));
				return true;
			}
			String targetName = args[0];
			if(Bukkit.getPlayer(targetName) == null) {
				player.sendMessage(lang.getPlayerIsOfflineMessage(player));
				return true;
			}
			Player target = Bukkit.getPlayer(targetName);
			boolean check = data.isEnabledCSpy(target);
			if(check) {
				player.sendMessage(lang.getCSpyCheckEnabledMessage(target));
				return true;
			}
			player.sendMessage(lang.getCSpyCheckDisabledMessage(target));
			
			return true;
		} 
	}
	
}
