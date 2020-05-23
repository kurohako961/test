package me.Color5.BPPlayerHideShow;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class ClickItem {
	public static ItemStack togglehide() {
		ItemStack hideitem = new ItemStack(Material.GOLD_NUGGET);

		ItemMeta hidemeta = hideitem.getItemMeta();
		//name
		hidemeta.setDisplayName(ChatColor.GREEN + "Toggle hide");
		//lore
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("右クリックでプレイヤー全体を非表示にします");
		hidemeta.setLore(lore);
		hideitem.setItemMeta(hidemeta);

		return hideitem;

	}

	public static ItemStack toggleshow() {
		ItemStack showitem = new ItemStack(Material.IRON_NUGGET);

		ItemMeta hidemeta = showitem.getItemMeta();
		//name
		hidemeta.setDisplayName(ChatColor.GREEN + "Toggle show");
		//lore
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("右クリックでプレイヤー全体を表示にします");
		hidemeta.setLore(lore);
		showitem.setItemMeta(hidemeta);

		return showitem;

	}
}
