package me.kfir123116.NPC.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ClickNPC implements Listener {
	
	@EventHandler
	public void onClick(RightClickNPC event) {
		Player player = event.getPlayer();
		player.sendMessage("DID IT WORK?");
	}

}
