package me.maxed.cspy;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
import me.maxed.cspy.util.Tools;

public class Lang {
	
	private CSpy plugin;
	private File langFile;
	private FileConfiguration lang;
	private boolean papi;
	
	private String noPerms;
	private String WWP;
	private String PIO;
	private String CSpyEnabled;
	private String CSpyDisabled;
	private String CSpyEnabledOther;
	private String CSpyDisabledOther;
	private String CSpyCheckEnabled;
	private String CSpyCheckDisabled;
	private String CSpyCheckOtherEnabled;
	private String CSpyCheckOtherDisabled;
	private String CSpyFeedbackEnabled;
	private String CSpyFeedbackDisabled;
	private String msgStyle;
	
	Lang(CSpy plugin) {
		this.plugin = plugin;
		this.papi = plugin.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null; 
		this.langFile = new File(this.plugin.getDataFolder() + File.separator + "lang.yml");
		if(!this.langFile.exists()) {
			plugin.saveResource("lang.yml", false);
		}
		this.lang = YamlConfiguration.loadConfiguration(langFile);
		this.noPerms = get("no-perms");
		this.WWP = get("work-with-player");
		this.PIO = get("player-is-offline");
		this.CSpyEnabled = get("cspy-enabled");
		this.CSpyDisabled = get("cspy-disabled");
		this.CSpyEnabledOther = get("cspy-enabled-other");
		this.CSpyDisabledOther = get("cspy-disabled-other");
		this.CSpyCheckEnabled = get("cspy-check-enabled");
		this.CSpyCheckDisabled = get("cspy-check-disabled");
		this.CSpyCheckOtherEnabled = get("cspy-check-other-enabled");
		this.CSpyCheckOtherDisabled = get("cspy-check-other-disabled");
		this.CSpyFeedbackEnabled = get("cspy-feedback-enabled");
		this.CSpyFeedbackDisabled = get("cspy-feedback-disabled");
		this.msgStyle = get("message-style");
	}
	
	private String get(String index) {
		return Tools.color(lang.getString(index));
	}
	
	private String papi(String str, Player player) {
		if(papi) {
			return PlaceholderAPI.setPlaceholders(player, str);
		}
		return str;
	}
	
	public String getNoPermissionsMessage(Player player) {
		if(player == null) {
			return noPerms;
		}
		return papi(noPerms, player);
	}
	
	public String getWorkWithPlayerMessage() {
		return this.WWP;
	}
	
	public String getPlayerIsOfflineMessage(Player player) {
		return papi(PIO, player);
	}
	
	public String getCSpyEnabledMessage(Player player) {
		return papi(CSpyEnabled, player);
	}
	
	public String getCSpyDisabledMessage(Player player) {
		return papi(CSpyDisabled, player);
	}
	
	public String getCSpyEnabledOtherMessage(Player player) {
		return papi(CSpyEnabledOther, player);
	}
	
	public String getCSpyDisabledOtherMessage(Player player) {
		return papi(CSpyDisabledOther, player);
	}
	
	public String getCSpyCheckEnabledMessage(Player player) {
		return papi(CSpyCheckEnabled, player);
	}
	
	public String getCSpyCheckDisabledMessage(Player player) {
		return papi(CSpyCheckDisabled, player);
	}
	
	public String getCSpyCheckOtherEnabledMessage(Player player) {
		return papi(CSpyCheckOtherEnabled, player);
	}
	
	public String getCSpyCheckOtherDisabledMessage(Player player) {
		return papi(CSpyCheckOtherDisabled, player);
	}
	
	public String getMessageStyle(Player player, String command) {
		String result = this.msgStyle.replace("{COMMAND}", command).replace("{PLAYER}", player.getName());
		return papi(result, player);
	}
	
	public String getFeedbackEnabled(Player player) {
		return papi(CSpyFeedbackEnabled, player);
	}
	
	public String getFeedbackDisabled(Player player) {
		return papi(CSpyFeedbackDisabled, player);
	}
	
	public String getReloaded() {
		return get("cspy-plugin-reloaded");
	}
}
