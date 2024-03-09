package newgkaan.gems;

import com.inkzzz.spigot.armorevent.EventAnalyser;
import newgkaan.gems.abilities.Armor;
import newgkaan.gems.abilities.Effect;
import newgkaan.gems.abilities.EntityDamage;
import newgkaan.gems.abilities.ItemDamage;
import newgkaan.gems.abilities.PlayerDamage;
import newgkaan.gems.abilities.PlayerDeath;
import newgkaan.gems.cmds.GemMaster;
import newgkaan.gems.gui.Click;
import newgkaan.gems.gui.Close;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main extends JavaPlugin {
   public static Main instance;

   public void onEnable() {


      instance = this;
      this.getServer().getPluginManager().registerEvents(new Click(), this);
      this.getServer().getPluginManager().registerEvents(new Close(), this);
      this.getServer().getPluginManager().registerEvents(new EventAnalyser(), this);
      this.getServer().getPluginManager().registerEvents(new Armor(), this);
      this.getServer().getPluginManager().registerEvents(new EntityDamage(), this);
      this.getServer().getPluginManager().registerEvents(new ItemDamage(), this);
      this.getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
      this.getServer().getPluginManager().registerEvents(new PlayerDamage(), this);
      this.getServer().getPluginManager().registerEvents(new Effect(), this);
      this.getCommand("gemmaster").setExecutor(new GemMaster());
   }




}
