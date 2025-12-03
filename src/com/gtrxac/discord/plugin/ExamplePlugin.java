package com.gtrxac.discord.plugin;

import javax.microedition.lcdui.*;
import com.gtrxac.discord.*;

public class ExamplePlugin extends Plugin {
    private Command testCommand;
    
    public ExamplePlugin() {
        super("Example Plugin", "1.0.0", "Discord J2ME", "An example plugin demonstrating the plugin API");
    }
    
    public void onLoad() {
        System.out.println("Example Plugin loaded!");
        testCommand = new Command("Plugin Test", Command.ITEM, 10);
    }
    
    public void onUnload() {
        System.out.println("Example Plugin unloaded!");
    }
    
    public void onAppStart() {
        System.out.println("App started!");
    }
    
    public void onLogin() {
        System.out.println("User logged in!");
    }
    
    public void onMessageReceived(Message message, boolean isDM) {
        System.out.println("Message received: " + message.content + " (DM: " + isDM + ")");
    }
    
    public void onChannelSelected(Channel channel) {
        System.out.println("Channel selected: " + channel.name);
    }
    
    public void onGuildSelected(Guild guild) {
        System.out.println("Guild selected: " + guild.name);
    }
    
    public Command[] getChannelViewCommands(ChannelView view) {
        return new Command[] { testCommand };
    }
    
    public void onCommandAction(Command cmd, Displayable d) {
        if (cmd == testCommand) {
            PluginContext.showError("Test command executed!");
        }
    }
}
