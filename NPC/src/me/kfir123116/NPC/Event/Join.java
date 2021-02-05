package me.kfir123116.NPC.Event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.kfir123116.NPC.NPC;
import me.kfir123116.NPC.PacketReader;

public class Join implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		if(NPC.getNPCs() == null)
			return;
		if (NPC.getNPCs().isEmpty())
			return;
		NPC.addJoinPacket(event.getPlayer());
		
		PacketReader reader = new PacketReader();
		reader.inject(event.getPlayer());
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		PacketReader reader = new PacketReader();
		reader.uninject(event.getPlayer());
	}
	
}
