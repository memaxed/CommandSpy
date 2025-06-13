<p align="center"><img src="./assets/logo.png" alt="Logo"></p>

## About
This is a simple and free plugin that adds a command tracking system to your Spigot server. This feature is mainly intended for server-staff: helpers, moderators and administrators. Granting access to regular players or donators is not recommended. To use the plugin correctly, you need to have permission management plugin such as LuckPerms.

## Commands
The plugin introduces the following commands:
- `/cspy [player]` — Enables or disables your/other command tracking mode (similar to `/spy` in server-core plugins).
- `/cspycheck [player]` — Checks whether you or another player is currently in tracking mode.
- `/cspyreload` — Reloads the plugin's configuration (admin-only).

## Configuration
Plugin provides two standard configuration files:

- `config.yml` — Mainly used to define permission-related options and customize command behavior.
- `lang.yml` — Contains all plugin messages, allowing full customization of the language and formatting.

Both files are generated automatically on first launch and can be edited to better fit your server's setup.

## Usage
When a player without the bypass permission executes a command, all players with command spying enabled will see a message showing the command and the player who used it.  
This works exactly like the server console’s command logging, but inside the game.

## Hooks
Plugin supports PlaceholderAPI in `lang.yml` file.