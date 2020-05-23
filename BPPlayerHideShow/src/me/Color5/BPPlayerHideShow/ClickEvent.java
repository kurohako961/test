package me.Color5.BPPlayerHideShow;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import net.md_5.bungee.api.ChatColor;

public class ClickEvent implements Listener {

	Map<String, Long> cooldowns = new HashMap<String, Long>();

	public ClickEvent(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClicked(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack item = p.getInventory().getItemInMainHand();

		if ((e.getAction() == Action.RIGHT_CLICK_AIR) || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			//cooldown
			if (cooldowns.containsKey(p.getName())) {
				if (cooldowns.get(p.getName()) > System.currentTimeMillis()) {
					return;
				}
			}

			cooldowns.put(p.getName(), System.currentTimeMillis() + (1 * 10));

			//hide切り替え
			if (item.getType() == Material.GOLD_NUGGET &&
					item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Toggle hide")) {
				Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
				int howmany_hide = -1;
				for (Player all : onlinePlayers) {
					howmany_hide++;
					p.hidePlayer(all);
					//インベントリの金塊を鉄塊に変える
					PlayerInventory inv = p.getInventory();
					inv.removeItem(ClickItem.togglehide());
					inv.setItem(1, ClickItem.toggleshow());
				}
				String moji = Integer.toString(howmany_hide);
				p.sendMessage(ChatColor.GOLD + moji + "人を非表示にしました");
			}

			//show切り替え
			else if (item.getType() == Material.IRON_NUGGET &&
					item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Toggle show")) {
				Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
				int howmany_show = -1;
				for (Player all : onlinePlayers) {
					howmany_show++;
					p.showPlayer(all);
					//インベントリの鉄塊を金塊に変える
					PlayerInventory inv = p.getInventory();
					inv.removeItem(ClickItem.toggleshow());
					inv.setItem(1, ClickItem.togglehide());
				}
				String moji2 = Integer.toString(howmany_show);
				p.sendMessage(ChatColor.GOLD + moji2 + "人を表示しました");
			}

		}
	}
}
