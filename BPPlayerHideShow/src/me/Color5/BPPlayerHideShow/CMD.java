package me.Color5.BPPlayerHideShow;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class CMD implements CommandExecutor {

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (label.equalsIgnoreCase("hide")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("このコマンドはコンソール上で実行できません");
				return true;
			}
			Player p = (Player) sender;

			if (args.length == 0) {
				//全てのプレイヤーをhideする処理
				Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
				int howmany_hide_player = -1;
				for (Player all : onlinePlayers) {
					p.hidePlayer(all);
					howmany_hide_player++;
				}
				p.sendMessage(format("&6") + howmany_hide_player + "人を非表示にしました");

				return true;
			}

			if (args.length == 1) {
				//playerを指定してhideする処理
				Player target = Bukkit.getPlayerExact(args[0]);
				if (target == null) {
					//指定されたPlayerがサーバーにログインしていないときはtargetはnullになる
					sender.sendMessage(ChatColor.RED + args[0] + ChatColor.GREEN +
							"というPlayerはこのサーバーにログインしていません");
					return true;
				}
				//指定されたPlayerがサーバーにログインしている場合
				p.hidePlayer(target);
				sender.sendMessage(format("&6") + args[0] + format("&aさんを非表示にしました "));

				return true;
			}
			if (args.length > 1) {
				// /hide <Player name> [この部分] が入力された場合
				sender.sendMessage("usage: /hide <player name>");
			}
			return true;

		}
		if (label.equalsIgnoreCase("show")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("このコマンドはコンソール上で実行できません");
				return true;
			}
			Player p = (Player) sender;

			if (args.length == 0) {
				//全てのプレイヤーをshowする処理
				Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
				int howmany_show_players = -1;

				for (Player all : onlinePlayers) {
					howmany_show_players++;
					p.showPlayer(all);
				}
				p.sendMessage(format("&6") + howmany_show_players + "人を表示しました");
				return true;
			}

			if (args.length == 1) {
				//playerを指定してshowする処理

				Player target = Bukkit.getPlayerExact(args[0]);
				if (target == null) {
					//指定されたPlayerがサーバーにログインしていないときはtargetはnullになる
					sender.sendMessage(ChatColor.RED + args[0] + ChatColor.GREEN +
							"というPlayerはこのサーバーにログインしていません");
					return true;
				}
				//指定されたPlayerがサーバーにログインしている場合
				p.showPlayer(target);
				sender.sendMessage(format("&6") + args[0] + format("&aさんを表示しました"));

				return true;
			}
			if (args.length > 1) {
				// /show <Player name> [この部分] が入力された場合
				sender.sendMessage(ChatColor.RED + "usage: /show <player name>");
			}

			return false;

		}

		if (label.equalsIgnoreCase("hidetool")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("このコマンドはコンソール上で実行できません");
				return true;
			}
			Player p = (Player) sender;
			p.getInventory().setItem(1,ClickItem.togglehide());

			return true;

		}
		return false;
	}

	private String format(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
		/*
		 * Green: a      DarkGreen: 2      Shakey: k
		 * Aqua: b       DarkAqua: 3       Bold: l
		 * Red: c        DarkRed: 4        Strike: m
		 * Pink: d       DarkPurple: 5     Underline: n
		 * Yellow: e     Gold: 6           Italic: o
		 * White: f      Grey: 7
		 * Black: 0      DarkGrey: 8
		 * DarkBlue: 1   Indigo: 9
		 */
	}

}
