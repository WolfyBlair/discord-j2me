package com.gtrxac.discord.plugin;

import javax.microedition.lcdui.*;
import com.gtrxac.discord.*;
import cc.nnproject.json.*;

public abstract class Plugin {
    protected String name;
    protected String version;
    protected String author;
    protected String description;
    
    public Plugin(String name, String version, String author, String description) {
        this.name = name;
        this.version = version;
        this.author = author;
        this.description = description;
    }
    
    public String getName() {
        return name;
    }
    
    public String getVersion() {
        return version;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void onLoad() {}
    
    public void onUnload() {}
    
    public void onAppStart() {}
    
    public void onAppPause() {}
    
    public void onAppDestroy() {}
    
    public void onLogin() {}
    
    public void onLogout() {}
    
    public void onMessageReceived(Message message, boolean isDM) {}
    
    public void onMessageSent(Message message, boolean isDM) {}
    
    public void onMessageDeleted(String messageId, String channelId, boolean isDM) {}
    
    public void onMessageEdited(Message message, boolean isDM) {}
    
    public void onChannelSelected(Channel channel) {}
    
    public void onGuildSelected(Guild guild) {}
    
    public void onDMChannelSelected(DMChannel channel) {}
    
    public void onGatewayConnected() {}
    
    public void onGatewayDisconnected() {}
    
    public void onGatewayMessage(JSONObject message) {}
    
    public JSONObject onHTTPRequest(String url, JSONObject requestData) {
        return requestData;
    }
    
    public JSONObject onHTTPResponse(String url, JSONObject responseData) {
        return responseData;
    }
    
    public void onChannelViewOpened(ChannelView view) {}
    
    public void onChannelViewClosed(ChannelView view) {}
    
    public Command[] getChannelViewCommands(ChannelView view) {
        return null;
    }
    
    public Command[] getMainMenuCommands() {
        return null;
    }
    
    public Command[] getGuildSelectorCommands(GuildSelector selector) {
        return null;
    }
    
    public Command[] getChannelSelectorCommands(ChannelSelector selector) {
        return null;
    }
    
    public void onCommandAction(Command cmd, Displayable d) {}
    
    public void onSettingsChanged() {}
    
    public Form createSettingsForm() {
        return null;
    }
}
