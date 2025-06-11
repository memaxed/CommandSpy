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
		this.CSpyCheckDisabled = get("cspy-feedback-disabled");
		this.msgStyle = get("message-style");
	}
	
	private String get(String index) {
		return Tools.color(lang.getString(index));
	}
	
	public String getNoPermissionsMessage(Player player) {
		if(player == null) {
			return this.noPerms;
		}
		return PlaceholderAPI.setPlaceholders(player, this.noPerms);
	}
	
	public String getWorkWithPlayerMessage() {
		return this.WWP;
	}
	
	public String getPlayerIsOfflineMessage(Player player) {
		return PlaceholderAPI.setPlaceholders(player, this.PIO);
	}
	
	public String getCSpyEnabledMessage(Player player) {
		return PlaceholderAPI.setPlaceholders(player, this.CSpyEnabled);
	}
	
	public String getCSpyDisabledMessage(Player player) {
		return PlaceholderAPI.setPlaceholders(player, this.CSpyDisabled);
	}
	
	public String getCSpyEnabledOtherMessage(Player player) {
		return PlaceholderAPI.setPlaceholders(player, this.CSpyEnabledOther);
	}
	
	public String getCSpyDisabledOtherMessage(Player player) {
		return PlaceholderAPI.setPlaceholders(player, this.CSpyDisabledOther);
	}
	
	public String getCSpyCheckEnabledMessage(Player player) {
		return PlaceholderAPI.setPlaceholders(player, this.CSpyCheckEnabled);
	}
	
	public String getCSpyCheckDisabledMessage(Player player) {
		return PlaceholderAPI.setPlaceholders(player, this.CSpyCheckDisabled);
	}
	
	public String getCSpyCheckOtherEnabledMessage(Player player) {
		return PlaceholderAPI.setPlaceholders(player, this.CSpyCheckOtherEnabled);
	}
	
	public String getCSpyCheckOtherDisabledMessage(Player player) {
		return PlaceholderAPI.setPlaceholders(player, this.CSpyCheckOtherDisabled);
	}
	
	public String getMessageStyle(Player player, String command) {
		String result = this.msgStyle.replace("{COMMAND}", command);
		return PlaceholderAPI.setPlaceholders(player, result);
	}
	
	public String getFeedbackEnabled(Player player) {
		return PlaceholderAPI.setPlaceholders(player, this.CSpyFeedbackEnabled);
	}
	
	public String getFeedbackDisabled(Player player) {
		return PlaceholderAPI.setPlaceholders(player, this.CSpyFeedbackDisabled);
	}
	
	public String getReloaded() {
		return get("cspy-plugin-reloaded");
	}
}
