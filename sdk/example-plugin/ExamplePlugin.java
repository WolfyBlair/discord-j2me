package com.gtrxac.discord.plugin;

import javax.microedition.lcdui.*;
import com.gtrxac.discord.*;

/**
 * Example plugin demonstrating the Discord J2ME Plugin API.
 * 
 * This plugin shows how to:
 * - React to various events
 * - Add custom commands to the UI
 * - Access application state
 * - Log plugin activity
 */
public class ExamplePlugin extends Plugin {
    private Command testCommand;
    private int messagesReceived;
    
    public ExamplePlugin() {
        super(
            "Example Plugin",
            "1.0.0",
            "Discord J2ME Team",
            "Demonstrates plugin capabilities"
        );
    }
    
    public void onLoad() {
        System.out.println("[ExamplePlugin] Plugin loaded!");
        testCommand = new Command("Plugin Demo", Command.ITEM, 10);
        messagesReceived = 0;
    }
    
    public void onUnload() {
        System.out.println("[ExamplePlugin] Plugin unloaded!");
    }
    
    public void onAppStart() {
        System.out.println("[ExamplePlugin] Application started");
    }
    
    public void onLogin() {
        System.out.println("[ExamplePlugin] User logged in");
        String userId = PluginContext.getMyUserId();
        if (userId != null) {
            System.out.println("[ExamplePlugin] User ID: " + userId);
        }
    }
    
    public void onMessageReceived(Message message, boolean isDM) {
        messagesReceived++;
        System.out.println("[ExamplePlugin] Message #" + messagesReceived + 
            " received from " + message.author + 
            " (DM: " + isDM + "): " + message.content);
        
        // Example: React to specific message content
        if (message.content != null && message.content.indexOf("!ping") != -1) {
            System.out.println("[ExamplePlugin] Ping command detected!");
            // You could trigger an action here
        }
    }
    
    public void onMessageSent(Message message, boolean isDM) {
        System.out.println("[ExamplePlugin] Message sent: " + message.content);
    }
    
    public void onChannelSelected(Channel channel) {
        System.out.println("[ExamplePlugin] Channel selected: " + channel.name);
    }
    
    public void onGuildSelected(Guild guild) {
        System.out.println("[ExamplePlugin] Guild selected: " + guild.name);
    }
    
    public void onDMChannelSelected(DMChannel channel) {
        System.out.println("[ExamplePlugin] DM channel selected: " + channel.name);
    }
    
    public void onGatewayConnected() {
        System.out.println("[ExamplePlugin] Gateway connected!");
    }
    
    public void onGatewayDisconnected() {
        System.out.println("[ExamplePlugin] Gateway disconnected!");
    }
    
    public void onChannelViewOpened(ChannelView view) {
        System.out.println("[ExamplePlugin] Channel view opened");
    }
    
    public void onChannelViewClosed(ChannelView view) {
        System.out.println("[ExamplePlugin] Channel view closed");
    }
    
    public Command[] getChannelViewCommands(ChannelView view) {
        // Add our custom command to the channel view
        return new Command[] { testCommand };
    }
    
    public Command[] getMainMenuCommands() {
        // We could also add commands to the main menu
        Command infoCommand = new Command("Plugin Info", Command.ITEM, 11);
        return new Command[] { infoCommand };
    }
    
    public void onCommandAction(Command cmd, Displayable d) {
        if (cmd == testCommand) {
            // Show a simple info dialog when our command is activated
            String info = "Plugin Demo\n\n" +
                "Messages received: " + messagesReceived + "\n" +
                "Gateway active: " + PluginContext.isGatewayActive();
            
            PluginContext.showError(info);
        }
        else if (cmd.getLabel().equals("Plugin Info")) {
            // Handle the main menu command
            String info = getName() + " v" + getVersion() + "\n" +
                "by " + getAuthor() + "\n\n" +
                getDescription();
            
            PluginContext.showError(info);
        }
    }
    
    public Form createSettingsForm() {
        // Example: Create a settings form for the plugin
        Form form = new Form("Example Plugin Settings");
        
        StringItem info = new StringItem("Info", 
            "This is an example settings form. " +
            "You can add TextFields, ChoiceGroups, etc.");
        form.append(info);
        
        TextField textField = new TextField("Option", "", 32, TextField.ANY);
        form.append(textField);
        
        ChoiceGroup choice = new ChoiceGroup("Enable Feature", Choice.MULTIPLE);
        choice.append("Feature 1", null);
        choice.append("Feature 2", null);
        form.append(choice);
        
        return form;
    }
}
