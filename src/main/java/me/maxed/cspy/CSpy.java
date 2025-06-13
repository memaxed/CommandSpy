package me.maxed.cspy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.maxed.cspy.commands.executors.CmdCSpy;
import me.maxed.cspy.commands.executors.CmdCSpyCheck;
import me.maxed.cspy.commands.executors.CmdCSpyReload;
import me.maxed.cspy.commands.executors.CmdCHistory;
import me.maxed.cspy.commands.tabcompleters.TabCSpy;
import me.maxed.cspy.listeners.*;
import me.maxed.cspy.logger.CommandLogger;

public class CSpy extends JavaPlugin {
	
	private static CSpy instance;
	private Lang lang;
	private Playerdata data;
	private Cfg cfg;
	private CommandLogger logger;
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new Joins(this), this);
		Bukkit.getPluginManager().registerEvents(new CommandSend(this), this);

		getCommand("cspy").setExecutor(new CmdCSpy(this));
		getCommand("cspy").setTabCompleter(new TabCSpy());
		getCommand("cspycheck").setExecutor(new CmdCSpyCheck(this));
		getCommand("cspycheck").setTabCompleter(new TabCSpy());
		getCommand("cspyreload").setExecutor(new CmdCSpyReload(this));
		getCommand("chistory").setExecutor(new CmdCHistory(this));
		getCommand("chistory").setTabCompleter(new TabCSpy());;
		
		cfg = new Cfg(this);
		lang = new Lang(this);
		data = new Playerdata(this);
		if(cfg.isCommandLoggerEnabled()) {
			logger = new CommandLogger(this);
		}
		
		if(lang.isPapiEnabled()) {
			getLogger().info("§aPlaceholderAPI hooked.");
		}
		
		getLogger().info("Enabled CommandSpy for " + Bukkit.getVersion() + ".");
	}
	
	@Override
	public void onDisable() {
		logger.close();
	}
	
	@Override
	public void onLoad() {
		(instance = this).saveDefaultConfig();
	}
	
	public static CSpy getInstance() {
		return instance;
	}
	
	public Playerdata getPlayerdata() {
		return this.data;
	}
	
	public Lang getLang() {
		return this.lang;
	}
	
	public Cfg getCfg() {
		return this.cfg;
	}
	
	public CommandLogger getCommandLogger() {
		return this.logger;
	}
	
	public void reload() {
		this.cfg = new Cfg(this);
		this.lang = new Lang(this);
		this.data = new Playerdata(this);
		if(cfg.isCommandLoggerEnabled()) {
			this.logger = new CommandLogger(this);
		}	
	}
	
}
