package me.maxed.cspy.commands.tabcompleters;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class TabCSpy implements TabCompleter {
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length == 1) {
			return Bukkit.getOnlinePlayers().stream().map(x -> x.getName()).toList();
		} 
		
		return new LinkedList<>();
	}

}
