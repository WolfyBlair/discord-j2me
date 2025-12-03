# Example Plugin for Discord J2ME

This directory contains an example plugin demonstrating the Discord J2ME Plugin API.

## What This Plugin Does

The ExamplePlugin demonstrates:

- **Event Logging**: Logs application lifecycle events, message events, and navigation events to System.out
- **Message Tracking**: Counts received messages and can detect specific message patterns
- **Custom Commands**: Adds custom commands to the channel view and main menu
- **Context Access**: Shows how to access app state through PluginContext
- **Settings UI**: Demonstrates creating a settings form for plugin configuration

## How to Use This Plugin

### Option 1: Include in Main Build

1. Copy `ExamplePlugin.java` to `src/com/gtrxac/discord/plugin/`
2. Edit `src/com/gtrxac/discord/App.java` and add to the static initializer:
   ```java
   PluginManager.registerPlugin(new ExamplePlugin());
   ```
3. Build the application normally with `build.sh` or `build.bat`

### Option 2: Use as a Template

1. Copy this plugin as a starting point for your own plugin
2. Rename the class and modify the functionality
3. Follow the steps in Option 1 to include it in the build

## Testing the Plugin

Once built and installed:

1. Start the application and watch the console output for plugin logs
2. Log in to see the "User logged in" message with your user ID
3. Open a channel and look for the "Plugin Demo" command in the menu
4. Select the "Plugin Demo" command to see statistics
5. Check the main menu for "Plugin Info"

## Plugin Output

You'll see console output like:

```
[ExamplePlugin] Plugin loaded!
[ExamplePlugin] Application started
[ExamplePlugin] User logged in
[ExamplePlugin] User ID: 123456789012345678
[ExamplePlugin] Guild selected: My Server
[ExamplePlugin] Channel selected: general
[ExamplePlugin] Channel view opened
[ExamplePlugin] Gateway connected!
[ExamplePlugin] Message #1 received from User123 (DM: false): Hello!
```

## Extending This Example

Ideas for extending this plugin:

1. **Message Filtering**: Filter messages by keywords or authors
2. **Auto-Reply**: Automatically respond to specific messages
3. **Statistics**: Track detailed statistics about usage
4. **Custom Notifications**: Add custom notification rules
5. **Data Export**: Export message logs to files
6. **Shortcuts**: Add quick actions for common tasks

## API Reference

See `sdk/PLUGIN_SDK.md` for complete API documentation.

## Support

- Discord server: https://discord.gg/2GKuJjQagp
- GitHub: https://github.com/gtrxAC/discord-j2me
