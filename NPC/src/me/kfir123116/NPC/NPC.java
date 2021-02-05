package me.kfir123116.NPC;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import net.minecraft.server.v1_8_R3.WorldServer;

public class NPC {
	
	private static List<EntityPlayer> NPC = new ArrayList<EntityPlayer>();
	
	public static void createNPC(Player player, String skin) {
		MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer world = ((CraftWorld) Bukkit.getWorld(player.getWorld().getName())).getHandle();
		GameProfile gameProfile = new GameProfile(UUID.randomUUID(), ChatColor.RED + "HIIII");
		EntityPlayer npc = new EntityPlayer(server, world, gameProfile, new PlayerInteractManager(world));
		npc.setLocation(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), 0, 0);
		
		gameProfile.getProperties().put("textures", new Property("textures", "ewogICJ0aW1lc3RhbXAiIDogMTYxMjQ2MTAyNzQwMCwKICAicHJvZmlsZUlkIiA6ICJhNDlkMTJiYTRhNzk0M2Y2ODk3M2ZlOTcxMWMxMjVlNiIsCiAgInByb2ZpbGVOYW1lIiA6ICJBYmxhemVzZSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9mODgyYmE2ZWE4YmJmM2I5OWRmYjU5MWUwY2IzMDlmNDM3ZTc4Yzg0OGVlYzFlNzEyZjUyMzI2M2U5N2Y2ZmIiCiAgICB9CiAgfQp9", "Ebru+mflKHNSPzZ5uS54MAQw6JqQ12JGlZ8fMvpX6JIzwBKV5V2NWlhJvOtr7jzhU6/O2u6cy8O8UF+5PEeNRg2vfkFXU5rlSuqHUYfhgzIn8FtehdMBzoYdoJcqa3SUYqpnCX07mQ2SjhoxRwFJ/d88hCnlQl5+K/dQj6nRcWbpTueJwBdA8IAGQSptPG020ZzpqB77RXITp4b92smyZIWegR/+nsIMzuTVlJYIfj3n4Z7TwCCbcy6w8muygXDUa5ut0bIHuSnEEesXvzERlVExCQ5u1KwgQXubY7SoR0s3a82ra+UucfTnGpmkHRis2JP/W918eNQDwAwIWv2dzZ9WJecsxEjN5RtGNyWSkc3JlzZwXnxGA56wg+Ouu5EsQyqKNE03IjFE94/1SoJvqxjd1SNcKnRsKRH895alFul3UdOpHa53lqoJLfxkm0boAXan5oxzej/ipwDuC5KO+W7p4BExLdLCScXi69idB9fhqZDLw+zSm4yUauxb4wTq4zAY4Bqp+FkMh7YKu6jSebRbUM74JObuAK/6tJfKJpxiHDExjPFfZaKlEJbwpU/C46ut0E4Z0zO/xMrZ80WfD07MUCNQDzUg8WMksWmIBxGxRJhE+FNyXBhEwy+gNlzXywH1YBVzo0394YiFrrob1A9ZhH58QBr7KTjOC0VKFcM="));
		
		addNPCPacket(npc);
		NPC.add(npc);
	}
	
	
	public static void addNPCPacket(EntityPlayer npc) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
			connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
			connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
		}
	}
	
	public static void addJoinPacket(Player player) {
		for (EntityPlayer npc : NPC) {
			PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
			connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
			connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
		}
	}
	
	public static List<EntityPlayer> getNPCs() {
		return NPC;
	}


	public static void setHealth(int i) {
		setHealth(0);
	}
}
