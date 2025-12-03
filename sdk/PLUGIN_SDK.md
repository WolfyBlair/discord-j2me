# Discord J2ME Plugin SDK

This document describes how to create plugins for Discord J2ME using the Plugin SDK.

## Overview

The Discord J2ME Plugin SDK allows you to extend the functionality of the Discord J2ME client by creating plugins that can:

- React to application lifecycle events (startup, pause, destroy)
- Intercept and process messages (received, sent, edited, deleted)
- Monitor channel and guild selections
- Hook into gateway events
- Modify HTTP requests and responses
- Add custom commands to various screens
- Create custom settings interfaces

## Getting Started

### Plugin Structure

A plugin is a Java class that extends the `com.gtrxac.discord.plugin.Plugin` abstract class. Here's a minimal example:

```java
package com.gtrxac.discord.plugin;

import com.gtrxac.discord.*;

public class MyPlugin extends Plugin {
    public MyPlugin() {
        super("My Plugin", "1.0.0", "Your Name", "A simple example plugin");
    }
    
    public void onLoad() {
        System.out.println("MyPlugin loaded!");
    }
    
    public void onMessageReceived(Message message, boolean isDM) {
        System.out.println("New message: " + message.content);
    }
}
```

### Registering Your Plugin

Plugins must be registered with the PluginManager during application startup. To register your plugin, add it to the static initializer in `App.java`:

```java
static {
    subscribedGuilds = new Vector();
    IconCache.init();
    NameColorCache.init();
    UnreadManager.init();
    PluginManager.initialize();
    
    // Register your plugins here
    PluginManager.registerPlugin(new MyPlugin());
}
```

## Plugin API Reference

### Constructor

```java
public Plugin(String name, String version, String author, String description)
```

Your plugin's constructor should call the parent constructor with metadata about your plugin.

### Lifecycle Hooks

#### onLoad()
Called when the plugin is registered with the PluginManager.

```java
public void onLoad() {
    // Initialize plugin resources
}
```

#### onUnload()
Called when the plugin is unregistered from the PluginManager.

```java
public void onUnload() {
    // Clean up plugin resources
}
```

#### onAppStart()
Called when the MIDlet starts.

```java
public void onAppStart() {
    // React to app startup
}
```

#### onAppPause()
Called when the MIDlet is paused.

```java
public void onAppPause() {
    // Save state or pause operations
}
```

#### onAppDestroy()
Called when the MIDlet is destroyed.

```java
public void onAppDestroy() {
    // Final cleanup
}
```

#### onLogin()
Called when a user successfully logs in.

```java
public void onLogin() {
    // Initialize user-specific features
}
```

#### onLogout()
Called when a user logs out.

```java
public void onLogout() {
    // Clean up user-specific data
}
```

### Message Events

#### onMessageReceived()
Called when a new message is received via the gateway.

```java
public void onMessageReceived(Message message, boolean isDM) {
    // Process new message
    // isDM indicates if this is a DM message
}
```

#### onMessageSent()
Called when the user sends a message.

```java
public void onMessageSent(Message message, boolean isDM) {
    // React to sent message
}
```

#### onMessageDeleted()
Called when a message is deleted.

```java
public void onMessageDeleted(String messageId, String channelId, boolean isDM) {
    // Handle message deletion
}
```

#### onMessageEdited()
Called when a message is edited.

```java
public void onMessageEdited(Message message, boolean isDM) {
    // React to message edit
}
```

### Navigation Events

#### onChannelSelected()
Called when a guild channel is selected.

```java
public void onChannelSelected(Channel channel) {
    // React to channel selection
}
```

#### onGuildSelected()
Called when a guild is selected.

```java
public void onGuildSelected(Guild guild) {
    // React to guild selection
}
```

#### onDMChannelSelected()
Called when a DM channel is selected.

```java
public void onDMChannelSelected(DMChannel channel) {
    // React to DM channel selection
}
```

#### onChannelViewOpened()
Called when the channel view (message list) is opened.

```java
public void onChannelViewOpened(ChannelView view) {
    // React to channel view opening
}
```

#### onChannelViewClosed()
Called when the channel view is closed.

```java
public void onChannelViewClosed(ChannelView view) {
    // React to channel view closing
}
```

### Gateway Events

#### onGatewayConnected()
Called when the gateway WebSocket connection is established.

```java
public void onGatewayConnected() {
    // React to gateway connection
}
```

#### onGatewayDisconnected()
Called when the gateway WebSocket connection is closed.

```java
public void onGatewayDisconnected() {
    // React to gateway disconnection
}
```

#### onGatewayMessage()
Called for every message received from the gateway (before processing).

```java
public void onGatewayMessage(JSONObject message) {
    // Inspect or process raw gateway message
}
```

### HTTP Hooks

#### onHTTPRequest()
Called before an HTTP request is sent. Return a modified JSONObject to change the request, or the original to leave it unchanged.

```java
public JSONObject onHTTPRequest(String url, JSONObject requestData) {
    // Modify request if needed
    return requestData;
}
```

#### onHTTPResponse()
Called after an HTTP response is received. Return a modified JSONObject to change the response, or the original to leave it unchanged.

```java
public JSONObject onHTTPResponse(String url, JSONObject responseData) {
    // Modify response if needed
    return responseData;
}
```

### UI Hooks

#### getChannelViewCommands()
Return an array of custom commands to add to the channel view.

```java
public Command[] getChannelViewCommands(ChannelView view) {
    Command myCommand = new Command("My Action", Command.ITEM, 10);
    return new Command[] { myCommand };
}
```

#### getMainMenuCommands()
Return an array of custom commands to add to the main menu.

```java
public Command[] getMainMenuCommands() {
    Command myCommand = new Command("Plugin Settings", Command.ITEM, 10);
    return new Command[] { myCommand };
}
```

#### getGuildSelectorCommands()
Return an array of custom commands to add to the guild selector.

```java
public Command[] getGuildSelectorCommands(GuildSelector selector) {
    // Return custom commands
    return null;
}
```

#### getChannelSelectorCommands()
Return an array of custom commands to add to the channel selector.

```java
public Command[] getChannelSelectorCommands(ChannelSelector selector) {
    // Return custom commands
    return null;
}
```

#### onCommandAction()
Called when any command is activated (including your custom commands).

```java
private Command myCommand;

public void onLoad() {
    myCommand = new Command("My Action", Command.ITEM, 10);
}

public void onCommandAction(Command cmd, Displayable d) {
    if (cmd == myCommand) {
        // Handle your command
        PluginContext.showError("Command executed!");
    }
}
```

### Settings Hooks

#### onSettingsChanged()
Called when application settings are changed.

```java
public void onSettingsChanged() {
    // React to settings changes
}
```

#### createSettingsForm()
Return a Form for plugin-specific settings.

```java
public Form createSettingsForm() {
    Form form = new Form("My Plugin Settings");
    // Add form items
    return form;
}
```

## PluginContext API

The `PluginContext` class provides plugins with safe access to application state and functionality:

### Display Management
- `Display getDisplay()` - Get the Display instance
- `App getApp()` - Get the App instance
- `void showScreen(Displayable screen)` - Show a screen
- `void showError(String message)` - Show an error dialog

### State Access
- `Vector getGuilds()` - Get list of guilds
- `Guild getSelectedGuild()` - Get currently selected guild
- `Vector getChannels()` - Get list of channels
- `Channel getSelectedChannel()` - Get currently selected channel
- `Vector getMessages()` - Get current messages
- `ChannelView getChannelView()` - Get channel view instance
- `Vector getDMChannels()` - Get list of DM channels
- `DMChannel getSelectedDMChannel()` - Get currently selected DM channel
- `boolean isDM()` - Check if current channel is a DM
- `String getMyUserId()` - Get logged-in user's ID
- `String getToken()` - Get auth token

### Gateway Access
- `boolean isGatewayActive()` - Check if gateway is connected
- `GatewayThread getGateway()` - Get gateway thread instance

### Navigation
- `void openGuildSelector(boolean reload, boolean forceReload)` - Open guild selector
- `void openChannelSelector(boolean reload, boolean forceReload)` - Open channel selector
- `void openDMSelector(boolean reload, boolean forceReload)` - Open DM selector
- `void openChannelView(boolean reload)` - Open channel view

### UI Resources
- `Font getAuthorFont()` - Get author font
- `Font getTimestampFont()` - Get timestamp font
- `Font getMessageFont()` - Get message font
- `Font getTitleFont()` - Get title font
- `Icons getIcons()` - Get icons instance

## Best Practices

1. **Error Handling**: Always wrap plugin code in try-catch blocks to prevent crashes
2. **Performance**: J2ME has limited resources; keep operations lightweight
3. **Memory**: Be mindful of memory usage; clean up resources in `onUnload()`
4. **Null Checks**: Always check for null before accessing app state
5. **Thread Safety**: Most plugin methods are called on the main thread; be careful with threads
6. **Compatibility**: Use conditional compilation flags when needed for device compatibility

## Example: Message Logger Plugin

Here's a complete example of a plugin that logs all messages to RMS:

```java
package com.gtrxac.discord.plugin;

import javax.microedition.rms.*;
import com.gtrxac.discord.*;
import java.util.*;

public class MessageLoggerPlugin extends Plugin {
    private RecordStore store;
    
    public MessageLoggerPlugin() {
        super("Message Logger", "1.0.0", "Example", "Logs all messages to RMS");
    }
    
    public void onLoad() {
        try {
            store = RecordStore.openRecordStore("msglog", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void onUnload() {
        try {
            if (store != null) store.closeRecordStore();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void onMessageReceived(Message message, boolean isDM) {
        try {
            String log = message.author + ": " + message.content;
            store.addRecord(log.getBytes("UTF-8"), 0, log.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

## Building Your Plugin

1. Place your plugin source file in `src/com/gtrxac/discord/plugin/`
2. Register it in `App.java`'s static initializer
3. Build the application normally using `build.sh` or `build.bat`

## Support

For questions and support, join the Discord server at https://discord.gg/2GKuJjQagp
