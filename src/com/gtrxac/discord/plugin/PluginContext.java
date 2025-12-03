package com.gtrxac.discord.plugin;

import javax.microedition.lcdui.*;
import java.util.*;
import com.gtrxac.discord.*;

public class PluginContext {
    public static Display getDisplay() {
        return App.disp;
    }
    
    public static App getApp() {
        return App.instance;
    }
    
    public static Vector getGuilds() {
        return App.guilds;
    }
    
    public static Guild getSelectedGuild() {
        return App.selectedGuild;
    }
    
    public static Vector getChannels() {
        return App.channels;
    }
    
    public static Channel getSelectedChannel() {
        return App.selectedChannel;
    }
    
    public static Vector getMessages() {
        return App.messages;
    }
    
    public static ChannelView getChannelView() {
        return App.channelView;
    }
    
    public static Vector getDMChannels() {
        return App.dmChannels;
    }
    
    public static DMChannel getSelectedDMChannel() {
        return App.selectedDmChannel;
    }
    
    public static boolean isDM() {
        return App.isDM;
    }
    
    public static String getMyUserId() {
        return App.myUserId;
    }
    
    public static String getToken() {
        return Settings.token;
    }
    
    public static boolean isGatewayActive() {
        return App.gatewayActive();
    }
    
    public static GatewayThread getGateway() {
        return App.gateway;
    }
    
    public static void showError(String message) {
        App.error(message);
    }
    
    public static void showError(String message, Displayable next) {
        App.error(message, next);
    }
    
    public static void showScreen(Displayable screen) {
        App.disp.setCurrent(screen);
    }
    
    public static void openGuildSelector(boolean reload, boolean forceReload) {
        App.openGuildSelector(reload, forceReload);
    }
    
    public static void openChannelSelector(boolean reload, boolean forceReload) {
        App.openChannelSelector(reload, forceReload);
    }
    
    public static void openDMSelector(boolean reload, boolean forceReload) {
        App.openDMSelector(reload, forceReload);
    }
    
    public static void openChannelView(boolean reload) {
        App.openChannelView(reload);
    }
    
    public static Font getAuthorFont() {
        return App.authorFont;
    }
    
    public static Font getTimestampFont() {
        return App.timestampFont;
    }
    
    public static Font getMessageFont() {
        return App.messageFont;
    }
    
    public static Font getTitleFont() {
        return App.titleFont;
    }
    
    public static Icons getIcons() {
        return App.ic;
    }
}
