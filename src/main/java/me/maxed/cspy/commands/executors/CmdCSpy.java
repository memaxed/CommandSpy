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

public class CmdCSpy implements CommandExecutor {

	private CSpy plugin;
	
	public CmdCSpy(CSpy plugin) {
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
		if(!player.hasPermission(cfg.getCSpyPerm())) {
			player.sendMessage(lang.getNoPermissionsMessage(player));
			return true;
		}
		
		int len = args.length;
		
		if(len == 0) {
			boolean state = data.turnPlayerCSpy(player);
			if(state) player.sendMessage(lang.getCSpyEnabledMessage(player));
			else player.sendMessage(lang.getCSpyDisabledMessage(player));
			return true;
		} else {
			if(!(player.hasPermission(cfg.getCSpyOtherPerm()))) {
				player.sendMessage(lang.getNoPermissionsMessage(player));
				return true;
			}
			
			String targetName = args[0];
			if(Bukkit.getPlayer(targetName) == null) {
				player.sendMessage(lang.getPlayerIsOfflineMessage(player, targetName));
				return true;
			}
			
			Player target = Bukkit.getPlayer(targetName);
			boolean state = data.turnPlayerCSpy(target);
			
			if(state) player.sendMessage(lang.getCSpyEnabledOtherMessage(target));
			else player.sendMessage(lang.getCSpyDisabledOtherMessage(target));
			
			if(cfg.otherFeedback()) {
				if(state) target.sendMessage(lang.getFeedbackEnabled(player));
				else target.sendMessage(lang.getFeedbackDisabled(player));
			}
			
			return true;
		} 
	
	}

}
