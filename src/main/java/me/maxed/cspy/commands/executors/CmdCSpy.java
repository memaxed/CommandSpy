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
			if(state) player.sendMessage(lang.getCSpyCheckEnabledMessage(player));
			else player.sendMessage(lang.getCSpyCheckDisabledMessage(player));
			return true;
		} else {
			if(!(player.hasPermission(cfg.getCSpyOtherPerm()))) {
				player.sendMessage(lang.getNoPermissionsMessage(player));
				return true;
			}
			String targetName = args[0];
			if(Bukkit.getPlayer(targetName) == null) {
				player.sendMessage(lang.getPlayerIsOfflineMessage(player));
				return true;
			}
			Player target = Bukkit.getPlayer(targetName);
			boolean state = data.turnPlayerCSpy(target);
			if(state) player.sendMessage(lang.getCSpyCheckOtherEnabledMessage(target));
			else player.sendMessage(lang.getCSpyCheckDisabledMessage(target));
			if(cfg.otherFeedback()) {
				if(state) target.sendMessage(lang.getFeedbackEnabled(player));
				else target.sendMessage(lang.getFeedbackDisabled(player));
			}
			return true;
		} 
		
//		if(!sender.hasPermission("cspy.use")) {
//			if(!sender.hasPermission("cspy.*")) {
//				if(!sender.hasPermission("*")) {
//					if(!sender.isOp()) {
//						sender.sendMessage(lang.getNoPermissionsMessage());
//						return true;
//					}
//				}
//			}
//		}
//		
//		if(args.length == 0) {
//			if(Tools.isConsole(sender)) {
//				sender.sendMessage(lang.getWorkWithPlayerMessage());
//				return true;
//			}
//			Player player = Tools.castPlayer(sender);
//			player.getUniqueId();
//			if(plugin.getCSpyPlayer(player)) {
//				plugin.setCSpyPlayer(player, false);
//				sender.sendMessage(lang.getCSpyDisabledMessage());
//				return true;
//			} else {
//				plugin.setCSpyPlayer(player, true);
//				sender.sendMessage(lang.getCSpyEnabledMessage());
//				return true;
//			}
//		}
//		
//		if(args.length == 1) {
//			if(Tools.isConsole(sender)) {
//				sender.sendMessage(Tools.color(lang.getWorkWithPlayerMessage()));
//				return true;
//			}
//			Player player = Tools.castPlayer(sender);
//			player.getUniqueId();
//			
//			String arg = args[0];
//			boolean enable = false;
//			if(arg.equalsIgnoreCase("enable")) {
//				enable = true;
//			} else if (arg.equalsIgnoreCase("disable")) {
//				enable = false;
//			} else if (arg.equalsIgnoreCase("get")) {
//				boolean enabled = plugin.getCSpyPlayer(player);
//				if(enabled) {
//					sender.sendMessage(lang.getCSpyCheckEnabledMessage());
//					return true;
//				} else {
//					sender.sendMessage(lang.getCSpyCheckDisabledMessage());
//					return true;
//				}
//				
//				
//			} else {
//				sender.sendMessage(Tools.color("&c�������������: " + cmd.getUsage()));
//				return true;
//			}
//			
//			plugin.setCSpyPlayer(player, enable);
//			if(enable) {
//				sender.sendMessage(lang.getCSpyEnabledMessage());
//				return true;
//			} else {
//				sender.sendMessage(lang.getCSpyDisabledMessage());
//				return true;
//			}
//			
//		}
//		
//		if(args.length == 2) {
//			Player player = Bukkit.getPlayer(args[1]);
//			if(player == null) {
//				sender.sendMessage(lang.getPlayerIsOfflineMessage());
//				return true;
//			}
//			
//			player.getUniqueId();
//			String arg = args[0];
//			if(!player.hasPermission("cspy.other")) {
//				if(!player.hasPermission("cspy.*")) {
//					if(!sender.hasPermission("*")) {
//						if(!sender.isOp()) {
//							sender.sendMessage(lang.getNoPermissionsMessage());
//							return true;
//						}
//					}
//				}
//			}
//			boolean enable;
//			if(arg.equalsIgnoreCase("enable")) {
//				enable = true;
//			} else if (arg.equalsIgnoreCase("disable")) {
//				enable = false;
//			} else if (arg.equalsIgnoreCase("get")) {
//				boolean enabled = plugin.getCSpyPlayer(player);
//				if(enabled) {
//					sender.sendMessage(lang.getCSpyCheckEnabledMessage());
//					return true;
//				} else {
//					sender.sendMessage(lang.getCSpyCheckDisabledMessage());
//					return true;
//				}
//				
//				
//			} else {
//				sender.sendMessage(Tools.color("&c�������������: " + cmd.getUsage()));
//				return true;
//			}
//			
//			if(enable) {
//				plugin.setCSpyPlayer(player, enable);
//				sender.sendMessage(lang.getCSpyEnabledOtherMessage());
//				return true;
//			} else {
//				plugin.setCSpyPlayer(player, enable);
//				sender.sendMessage(lang.getCSpyDisabledOtherMessage());
//				return true;
//			}
//			
//		}
//		
//		
//		return true;
	}

}
