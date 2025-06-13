<p align="center"><img src="./assets/logo.png" alt="Logo"></p>

## About
This is a simple and free plugin that adds a command tracking system to your Spigot server. This feature is mainly intended for server-staff: helpers, moderators and administrators. Granting access to regular players or donators is not recommended. To use the plugin correctly, you need to have permission management plugin such as LuckPerms.

## Commands
The plugin introduces the following commands:
- `/cspy [player]` — Enables or disables your/other command tracking mode (similar to `/spy` in server-core plugins).
- `/cspycheck [player]` — Checks whether you or another player is currently in tracking mode.
- `/chistory <player>` — Displays the recent commands of the specified player (only if command logging is enabled).
- `/cspyreload` — Reloads the plugin's configuration (admin-only).

## Configuration
Plugin provides two standard configuration files:

- `config.yml` — Mainly used to define permission-related options and customize command behavior.
- `lang.yml` — Contains all plugin messages, allowing full customization of the language and formatting.

Both files are generated automatically on first launch and can be edited to better fit your server's setup.

## Usage
When a player without the bypass permission executes a command, all players with command spying enabled will see a message showing the command and the player who used it.  
This works exactly like the server console’s command logging, but inside the game.

## Command logging
This plugin includes a feature called command logging (turned off by default), which records commands entered by players who do not have the bypass permission.
All logged commands are stored in a special SQLite database.
To view a player's recent command history, use the command: `/chistory <player>`.

## Hooks
Plugin supports PlaceholderAPI in `lang.yml` file.