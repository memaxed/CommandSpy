## Summary
This is a simple and free plugin that adds a command tracking system to your Spigot server. This feature is mainly intended to server-staff: helpers, moderators and administrators. Granting access to regular players or donators is not recommended. To use the plugin correctly, you need to have permission management plugin such as LuckPerms and etc.

## Commands
The plugin introduces the following commands:
- `/cspy` — Enables or disables your own command tracking mode (similar to `/spy` in server-core plugins).
- `/cspy <player>` — Enables or disables tracking mode for another player.
- `/cspycheck [player]` — Checks whether you or another player is currently in tracking mode.
- `/cspyreload` — Reloads the plugin's configuration (admin-only).

## Configuration
The plugin provides two standard configuration files:

- `config.yml` — Mainly used to define permission-related options and customize command behavior.
- `lang.yml` — Contains all plugin messages, allowing full customization of the language and formatting.

Both files are generated automatically on first launch and can be edited to better fit your server's setup.

## Usage
Players can toggle their own tracking mode using `/cspy`, provided they have the required permission.  
They can also toggle tracking mode for other players using `/cspy <player>`, if they have the appropriate permission.

## Hooks
The plugin supports PlaceholderAPI in `lang.yml` file.
