package me.Color5.BPPlayerHideShow;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	/*========TODO========
	@[commands]
	 ・


	  =====================
	*/

	@Override
	public void onEnable() {
		//command関連
		 this.getCommand("hide").setExecutor(new CMD());
		 this.getCommand("show").setExecutor(new CMD());
		 this.getCommand("hidetool").setExecutor(new CMD());
		 //Listener関連
		 new ClickEvent(this);
	}

	@Override
	public void onDisable() {

	}

}
