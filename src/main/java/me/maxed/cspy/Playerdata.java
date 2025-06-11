package me.maxed.cspy;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Playerdata {

	private CSpy plugin;
	private File playerData;
	private FileConfiguration fc;
	
	public Playerdata(CSpy plugin) {
		this.plugin = plugin;
		File pData = new File(this.plugin.getDataFolder() + File.separator + "playerdata.yml");
		if(!pData.exists()) {
			try {
				pData.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.playerData = pData;
		this.fc = YamlConfiguration.loadConfiguration(playerData);
	}
	
	public boolean containsPlayer(Player player) {
		UUID uuid = player.getUniqueId();
		String id = uuid.toString();
		if(fc.contains(id)) {
			return true;
		}
		return false;
	}
	
	public void createPlayer(Player player) {
		UUID uuid = player.getUniqueId();
		String id = uuid.toString();
		fc.set(id + ".name", player.getName());
		fc.set(id + ".cspy-enabled", false);
		try {
			fc.save(playerData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setPlayerCSpy(Player player, boolean value) {
		UUID uuid = player.getUniqueId();
		String id = uuid.toString();
		fc.set(id + ".cspy-enabled", value);
		try {
			fc.save(playerData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	public boolean isEnabledCSpy(Player player) {
		UUID uuid = player.getUniqueId();
		String id = uuid.toString();
		return fc.getBoolean(id + ".cspy-enabled");
	} 
	
	public boolean turnPlayerCSpy(Player player) {
		if(isEnabledCSpy(player)) {
			setPlayerCSpy(player, false);
			return false;
		} else {
			setPlayerCSpy(player, true);
			return true;
		}
	}
	
	
	
	
	
	
}
