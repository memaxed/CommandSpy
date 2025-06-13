package me.maxed.cspy;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
import me.maxed.cspy.util.Hex;

public class Lang {
	
	private CSpy plugin;
	private File langFile;
	private FileConfiguration lang;
	private boolean papi;
	private String title;
	
	Lang(CSpy plugin) {
		this.plugin = plugin;
		this.papi = plugin.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null; 
		this.langFile = new File(this.plugin.getDataFolder() + File.separator + "lang.yml");
		if(!this.langFile.exists()) {
			plugin.saveResource("lang.yml", false);
		}
		this.lang = YamlConfiguration.loadConfiguration(langFile);
		this.title = get("title");
	}
	
	private String get(String index) {
		String str = lang.getString(index);
		if(title != null) {
			str = str.replace("{TITLE}", title);
		}
		return Hex.toChatColorString(str);
	}
	
	private String papi(String str, Player player) {
		if(papi) {
			str = PlaceholderAPI.setPlaceholders(player, str);
		}
		str = str.replace("{PLAYER}", player.getName());
		return str;
	}
	
	public String getNoPermissionsMessage(Player player) {
		return papi(get("no-perms"), player);
	}
	
	public String getNoPermissionsMessage() {
		return get("no-perms");
	}
	
	public String getWorkWithPlayerMessage() {
		return get("work-with-player");
	}
	
	public String getPlayerIsOfflineMessage(Player player, String offline) {
		return papi(get("player-is-offline"), player);
	}
	
	public String getCSpyEnabledMessage(Player player) {
		return papi(get("cspy-enabled"), player);
	}
	
	public String getCSpyDisabledMessage(Player player) {
		return papi(get("cspy-disabled"), player);
	}
	
	public String getCSpyEnabledOtherMessage(Player player) {
		return papi(get("cspy-enabled-other"), player);
	}
	
	public String getCSpyDisabledOtherMessage(Player player) {
		return papi(get("cspy-disabled-other"), player);
	}
	
	public String getCSpyCheckEnabledMessage(Player player) {
		return papi(get("cspy-check-enabled"), player);
	}
	
	public String getCSpyCheckDisabledMessage(Player player) {
		return papi(get("cspy-check-disabled"), player);
	}
	
	public String getCSpyCheckOtherEnabledMessage(Player player) {
		return papi(get("cspy-check-other-enabled"), player);
	}
	
	public String getCSpyCheckOtherDisabledMessage(Player player) {
		return papi(get("cspy-check-other-disabled"), player);
	}
	
	public String getMessageStyle(Player player, String command) {
		String result = get("message-style").replace("{COMMAND}", command);
		return papi(result, player);
	}
	
	public String getFeedbackEnabled(Player player) {
		return papi(get("cspy-feedback-enabled"), player);
	}
	
	public String getFeedbackDisabled(Player player) {
		return papi(get("cspy-feedback-disabled"), player);
	}
	
	public String getReloaded() {
		return get("cspy-plugin-reloaded");
	}
	
	public String getCHistoryOff() {
		return get("chistory-off");
	}
	
	public String getCHistoryUsage() {
		return get("chistory-usage");
	}
	
	public String getCHistoryEmpty(String name) {
		return get("chistory-empty").replace("{NAME}", name);
	}
	
	public String getCHistoryHeader(String name, int limit) {
		return get("chistory-header").replace("{NAME}", name).replace("{LIMIT}", String.valueOf(limit));
	}
	
	public String getCHistoryItem(String name, String timestamp, String command) {
		return get("chistory-style").replace("{NAME}", name).replace("{TIMESTAMP}", timestamp).replace("{COMMAND}", command);
	}
	
	boolean isPapiEnabled() {
		return papi;
	}
	
}
