package me.maxed.cspy;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Cfg {

	private CSpy plugin;
	private File cfgFile;
	private FileConfiguration cfg;
	
	public Cfg(CSpy plugin) {
		this.plugin = plugin;
		this.cfgFile = new File(this.plugin.getDataFolder() + File.separator + "config.yml");
		if(!this.cfgFile.exists()) {
			plugin.getConfig().options().copyDefaults(true);
			plugin.saveDefaultConfig();
		}
		this.cfg = YamlConfiguration.loadConfiguration(cfgFile);
		
	}
	
	public List<String> getIgnoreCommandsList() {
		List<String> ignoreCommands = cfg.getStringList("ignore-commands");
		if(ignoreCommands == null) {
			return new LinkedList<String>();
		} else return ignoreCommands;
		
	}
	
	public String getCSpyPerm() {
		return cfg.getString("cspy-perm");
	}
	
	public String getCSpyOtherPerm() {
		return cfg.getString("cspy-other-perm");
	}
	
	public String getCSpyBypassPerm() {
		return cfg.getString("cspy-bypass-perm");
	}
	
	public boolean otherFeedback() {
		return cfg.getBoolean("other-feedback");
	}
	
	public String getCSpyCheckPerm() {
		return cfg.getString("cspy-check-perm");
	}
	
	public String getCSpyCheckOtherPerm() {
		return cfg.getString("cspy-check-other-perm");
	}
	
	public String getCSpyReloadPerm() {
		return cfg.getString("cspy-reload-perm");
	}
	
	
	
}
