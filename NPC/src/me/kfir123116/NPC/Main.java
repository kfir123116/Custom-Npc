package me.kfir123116.NPC;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.kfir123116.NPC.Event.ClickNPC;
import me.kfir123116.NPC.Event.Join;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {
	
	@Override
    public void onEnable() {
        this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n--------------------------------------------------\n" + "\n\n\n           NPC Plugin has been Enabled           \n\n\n" + "\n--------------------------------------------------\n");
        this.getServer().getPluginManager().registerEvents(new Join(), this);
        this.getServer().getPluginManager().registerEvents(new ClickNPC(), this);

        
       if (!Bukkit.getOnlinePlayers().isEmpty())
    	   for (Player player :Bukkit.getOnlinePlayers()) {
    		   PacketReader reader = new PacketReader();
    			reader.inject(player);
    	   }
    }
   
	@Override
	public void onDisable() {
        this.getServer().getConsoleSender().sendMessage(ChatColor.RED + "\n--------------------------------------------------\n" + "\n\n\n           NPC Plugin has been Enabled           \n\n\n" + "\n--------------------------------------------------\n");
        for (Player player :Bukkit.getOnlinePlayers()) {
 		   PacketReader reader = new PacketReader();
 			reader.uninject(player);
 	   }
    }
	
	public boolean onCommand(CommandSender sender, Command cmd, String labal, String[] args) {
		if (labal.equalsIgnoreCase("createnpc")) {
			if (!(sender instanceof Player)) {
				NPC.setHealth(10);
				return true;
			}
			Player player = (Player) sender;
			if (args.length == 0) {
				NPC.createNPC(player, player.getName());
				player.sendMessage("NPC CREATED");
				return true;
			}
			NPC.createNPC(player, args[0]);
			player.sendMessage("NPC CREATED");
			return true;
		}
		return false;
	}
}
