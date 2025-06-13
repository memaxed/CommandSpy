package me.maxed.cspy.logger;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bukkit.entity.Player;

import me.maxed.cspy.CSpy;

public class CommandLogger {

	private CSpy plugin;
	private File db;
	private Connection con;

	public CommandLogger(CSpy plugin) {
		this.plugin = plugin;
		db = new File(this.plugin.getDataFolder() + File.separator + "command_history.db");
		connect();
	}

	private void connect() {
		try {
			con = DriverManager.getConnection("jdbc:sqlite:" + db.getAbsolutePath());
			try (Statement stmt = con.createStatement()) {
				stmt.executeUpdate(
						"CREATE TABLE IF NOT EXISTS command_history (" +
								"id INTEGER PRIMARY KEY AUTOINCREMENT," +
								"player_name TEXT NOT NULL," +
								"uuid TEXT NOT NULL," +
								"command TEXT NOT NULL," +
								"timestamp TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP)"
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean logCommand(Player player, String commandText) {
		if (con == null) return false;

		try (PreparedStatement stmt = con.prepareStatement(
				"INSERT INTO command_history (player_name, uuid, command) VALUES (?, ?, ?)")) {
			stmt.setString(1, player.getName());
			stmt.setString(2, player.getUniqueId().toString());
			stmt.setString(3, commandText);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public List<Item> getCommandHistory(String playerName, int limit) {
		List<Item> history = new ArrayList<>();
		if (con == null) return history;

		try (PreparedStatement stmt = con.prepareStatement(
				"SELECT timestamp, command FROM command_history WHERE player_name = ? ORDER BY timestamp DESC LIMIT ?")) {
			stmt.setString(1, playerName);
			stmt.setInt(2, limit);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String timestamp = rs.getString("timestamp");
				String command = rs.getString("command");
				Item item = new Item(timestamp, command);
				history.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return history;
	}

	public void close() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static class Item {
		private String timestamp;
		private String command;
		
		public Item(String timestamp, String command) {
			this.command = command;
			this.timestamp = timestamp;
		}
		
		public String getTimestamp() {
			return timestamp;
		}
		
		public String getCommand() {
			return command;
		}

		@Override
		public int hashCode() {
			return Objects.hash(command, timestamp);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Item other = (Item) obj;
			return Objects.equals(command, other.command) && Objects.equals(timestamp, other.timestamp);
		}
	}
}
