package com.gtrxac.discord.plugin;

import javax.microedition.lcdui.*;
import java.util.*;
import com.gtrxac.discord.*;
import cc.nnproject.json.*;

public class PluginManager {
    private static Vector plugins = new Vector();
    private static boolean initialized = false;
    
    public static void initialize() {
        if (initialized) return;
        initialized = true;
    }
    
    public static void registerPlugin(Plugin plugin) {
        if (plugin == null) return;
        
        try {
            plugin.onLoad();
            plugins.addElement(plugin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void unregisterPlugin(Plugin plugin) {
        if (plugin == null) return;
        
        try {
            plugin.onUnload();
            plugins.removeElement(plugin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Vector getPlugins() {
        return plugins;
    }
    
    public static Plugin getPlugin(String name) {
        for (int i = 0; i < plugins.size(); i++) {
            Plugin p = (Plugin) plugins.elementAt(i);
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }
    
    public static void fireAppStart() {
        for (int i = 0; i < plugins.size(); i++) {
            try {
                ((Plugin) plugins.elementAt(i)).onAppStart();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void fireAppPause() {
        for (int i = 0; i < plugins.size(); i++) {
            try {
                ((Plugin) plugins.elementAt(i)).onAppPause();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void fireAppDestroy() {
        for (int i = 0; i < plugins.size(); i++) {
            try {
                ((Plugin) plugins.elementAt(i)).onAppDestroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void fireLogin() {
        for (int i = 0; i < plugins.size(); i++) {
            try {
                ((Plugin) plugins.elementAt(i)).onLogin();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void fireLogout() {
        for (int i = 0; i < plugins.size(); i++) {
            try {
                ((Plugin) plugins.elementAt(i)).onLogout();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void fireMessageReceived(Message message, boolean isDM) {
        for (int i = 0; i < plugins.size(); i++) {
            try {
                ((Plugin) plugins.elementAt(i)).onMessageReceived(message, isDM);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void fireMessageSent(Message message, boolean isDM) {
        for (int i = 0; i < plugins.size(); i++) {
            try {
                ((Plugin) plugins.elementAt(i)).onMessageSent(message, isDM);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void fireMessageDeleted(String messageId, String channelId, boolean isDM) {
        for (int i = 0; i < plugins.size(); i++) {
            try {
                ((Plugin) plugins.elementAt(i)).onMessageDeleted(messageId, channelId, isDM);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void fireMessageEdited(Message message, boolean isDM) {
        for (int i = 0; i < plugins.size(); i++) {
            try {
                ((Plugin) plugins.elementAt(i)).onMessageEdited(message, isDM);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void fireChannelSelected(Channel channel) {
        for (int i = 0; i < plugins.size(); i++) {
            try {
                ((Plugin) plugins.elementAt(i)).onChannelSelected(channel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void fireGuildSelected(Guild guild) {
        for (int i = 0; i < plugins.size(); i++) {
            try {
                ((Plugin) plugins.elementAt(i)).onGuildSelected(guild);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void fireDMChannelSelected(DMChannel channel) {
        for (int i = 0; i < plugins.size(); i++) {
            try {
                ((Plugin) plugins.elementAt(i)).onDMChannelSelected(channel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void fireGatewayConnected() {
        for (int i = 0; i < plugins.size(); i++) {
            try {
                ((Plugin) plugins.elementAt(i)).onGatewayConnected();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void fireGatewayDisconnected() {
        for (int i = 0; i < plugins.size(); i++) {
            try {
                ((Plugin) plugins.elementAt(i)).onGatewayDisconnected();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void fireGatewayMessage(JSONObject message) {
        for (int i = 0; i < plugins.size(); i++) {
            try {
                ((Plugin) plugins.elementAt(i)).onGatewayMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static JSONObject filterHTTPRequest(String url, JSONObject requestData) {
        JSONObject data = requestData;
        for (int i = 0; i < plugins.size(); i++) {
            try {
                JSONObject result = ((Plugin) plugins.elementAt(i)).onHTTPRequest(url, data);
                if (result != null) {
                    data = result;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }
    
    public static JSONObject filterHTTPResponse(String url, JSONObject responseData) {
        JSONObject data = responseData;
        for (int i = 0; i < plugins.size(); i++) {
            try {
                JSONObject result = ((Plugin) plugins.elementAt(i)).onHTTPResponse(url, data);
                if (result != null) {
                    data = result;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }
    
    public static void fireChannelViewOpened(ChannelView view) {
        for (int i = 0; i < plugins.size(); i++) {
            try {
                ((Plugin) plugins.elementAt(i)).onChannelViewOpened(view);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void fireChannelViewClosed(ChannelView view) {
        for (int i = 0; i < plugins.size(); i++) {
            try {
                ((Plugin) plugins.elementAt(i)).onChannelViewClosed(view);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void fireSettingsChanged() {
        for (int i = 0; i < plugins.size(); i++) {
            try {
                ((Plugin) plugins.elementAt(i)).onSettingsChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static Vector getChannelViewCommands(ChannelView view) {
        Vector commands = new Vector();
        for (int i = 0; i < plugins.size(); i++) {
            try {
                Command[] cmds = ((Plugin) plugins.elementAt(i)).getChannelViewCommands(view);
                if (cmds != null) {
                    for (int j = 0; j < cmds.length; j++) {
                        commands.addElement(cmds[j]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return commands;
    }
    
    public static Vector getMainMenuCommands() {
        Vector commands = new Vector();
        for (int i = 0; i < plugins.size(); i++) {
            try {
                Command[] cmds = ((Plugin) plugins.elementAt(i)).getMainMenuCommands();
                if (cmds != null) {
                    for (int j = 0; j < cmds.length; j++) {
                        commands.addElement(cmds[j]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return commands;
    }
    
    public static Vector getGuildSelectorCommands(GuildSelector selector) {
        Vector commands = new Vector();
        for (int i = 0; i < plugins.size(); i++) {
            try {
                Command[] cmds = ((Plugin) plugins.elementAt(i)).getGuildSelectorCommands(selector);
                if (cmds != null) {
                    for (int j = 0; j < cmds.length; j++) {
                        commands.addElement(cmds[j]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return commands;
    }
    
    public static Vector getChannelSelectorCommands(ChannelSelector selector) {
        Vector commands = new Vector();
        for (int i = 0; i < plugins.size(); i++) {
            try {
                Command[] cmds = ((Plugin) plugins.elementAt(i)).getChannelSelectorCommands(selector);
                if (cmds != null) {
                    for (int j = 0; j < cmds.length; j++) {
                        commands.addElement(cmds[j]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return commands;
    }
    
    public static void fireCommandAction(Command cmd, Displayable d) {
        for (int i = 0; i < plugins.size(); i++) {
            try {
                ((Plugin) plugins.elementAt(i)).onCommandAction(cmd, d);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static Vector getPluginSettingsForms() {
        Vector forms = new Vector();
        for (int i = 0; i < plugins.size(); i++) {
            try {
                Form form = ((Plugin) plugins.elementAt(i)).createSettingsForm();
                if (form != null) {
                    forms.addElement(form);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return forms;
    }
}
