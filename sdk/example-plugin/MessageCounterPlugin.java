package com.gtrxac.discord.plugin;

import javax.microedition.lcdui.*;
import javax.microedition.rms.*;
import com.gtrxac.discord.*;
import java.util.*;

/**
 * A simple plugin that counts messages per channel and displays statistics.
 * Demonstrates RMS (Record Management System) usage for persistent storage.
 */
public class MessageCounterPlugin extends Plugin {
    private Command statsCommand;
    private Hashtable channelCounts; // channelId -> Integer count
    private RecordStore store;
    private static final String RMS_NAME = "msgcounter";
    
    public MessageCounterPlugin() {
        super(
            "Message Counter",
            "1.0.0",
            "Example",
            "Counts messages per channel"
        );
    }
    
    public void onLoad() {
        System.out.println("[MessageCounter] Loading plugin...");
        channelCounts = new Hashtable();
        statsCommand = new Command("Message Stats", Command.ITEM, 10);
        loadFromRMS();
    }
    
    public void onUnload() {
        System.out.println("[MessageCounter] Unloading plugin...");
        saveToRMS();
        closeRMS();
    }
    
    private void loadFromRMS() {
        try {
            store = RecordStore.openRecordStore(RMS_NAME, true);
            
            if (store.getNumRecords() > 0) {
                byte[] data = store.getRecord(1);
                String str = new String(data, "UTF-8");
                
                // Parse format: "channelId1:count1,channelId2:count2,..."
                int pos = 0;
                while (pos < str.length()) {
                    int colonPos = str.indexOf(':', pos);
                    if (colonPos == -1) break;
                    
                    int commaPos = str.indexOf(',', colonPos);
                    if (commaPos == -1) commaPos = str.length();
                    
                    String channelId = str.substring(pos, colonPos);
                    String countStr = str.substring(colonPos + 1, commaPos);
                    
                    try {
                        int count = Integer.parseInt(countStr);
                        channelCounts.put(channelId, new Integer(count));
                    } catch (NumberFormatException e) {
                        // Skip invalid entry
                    }
                    
                    pos = commaPos + 1;
                }
                
                System.out.println("[MessageCounter] Loaded " + channelCounts.size() + " channel stats");
            }
        } catch (Exception e) {
            System.out.println("[MessageCounter] Error loading from RMS: " + e);
        }
    }
    
    private void saveToRMS() {
        try {
            if (store == null) {
                store = RecordStore.openRecordStore(RMS_NAME, true);
            }
            
            // Build string format: "channelId1:count1,channelId2:count2,..."
            StringBuffer sb = new StringBuffer();
            Enumeration keys = channelCounts.keys();
            boolean first = true;
            
            while (keys.hasMoreElements()) {
                if (!first) sb.append(',');
                first = false;
                
                String channelId = (String) keys.nextElement();
                Integer count = (Integer) channelCounts.get(channelId);
                
                sb.append(channelId);
                sb.append(':');
                sb.append(count.toString());
            }
            
            byte[] data = sb.toString().getBytes("UTF-8");
            
            if (store.getNumRecords() == 0) {
                store.addRecord(data, 0, data.length);
            } else {
                store.setRecord(1, data, 0, data.length);
            }
            
            System.out.println("[MessageCounter] Saved " + channelCounts.size() + " channel stats");
        } catch (Exception e) {
            System.out.println("[MessageCounter] Error saving to RMS: " + e);
        }
    }
    
    private void closeRMS() {
        try {
            if (store != null) {
                store.closeRecordStore();
                store = null;
            }
        } catch (Exception e) {
            // Ignore
        }
    }
    
    public void onMessageReceived(Message message, boolean isDM) {
        String channelId;
        if (isDM) {
            DMChannel dm = PluginContext.getSelectedDMChannel();
            if (dm == null) return;
            channelId = dm.id;
        } else {
            Channel ch = PluginContext.getSelectedChannel();
            if (ch == null) return;
            channelId = ch.id;
        }
        
        incrementCount(channelId);
    }
    
    private void incrementCount(String channelId) {
        Integer current = (Integer) channelCounts.get(channelId);
        int count = (current == null) ? 1 : current.intValue() + 1;
        channelCounts.put(channelId, new Integer(count));
    }
    
    public Command[] getChannelViewCommands(ChannelView view) {
        return new Command[] { statsCommand };
    }
    
    public void onCommandAction(Command cmd, Displayable d) {
        if (cmd == statsCommand) {
            showStats();
        }
    }
    
    private void showStats() {
        Channel ch = PluginContext.getSelectedChannel();
        DMChannel dmCh = PluginContext.getSelectedDMChannel();
        boolean isDM = PluginContext.isDM();
        
        String channelId;
        String channelName;
        
        if (isDM && dmCh != null) {
            channelId = dmCh.id;
            channelName = dmCh.name;
        } else if (!isDM && ch != null) {
            channelId = ch.id;
            channelName = ch.name;
        } else {
            PluginContext.showError("No channel selected");
            return;
        }
        
        Integer count = (Integer) channelCounts.get(channelId);
        int messageCount = (count == null) ? 0 : count.intValue();
        
        String stats = "Message Statistics\n\n" +
            "Channel: " + channelName + "\n" +
            "Messages counted: " + messageCount + "\n\n" +
            "Total channels tracked: " + channelCounts.size();
        
        PluginContext.showError(stats);
    }
    
    public void onAppDestroy() {
        // Save before app closes
        saveToRMS();
        closeRMS();
    }
}
