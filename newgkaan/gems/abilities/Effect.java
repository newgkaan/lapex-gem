package newgkaan.gems.abilities;

import java.util.*;

import newgkaan.gems.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Effect implements Listener {
   private final Map<PotionEffectType, List<UUID>> handled = new HashMap();



   private final Map<UUID, Integer> cooldowns = new HashMap<>();

   public void init(Main main) {
      new BukkitRunnable() {
         public void run() {
            for (Player player : Bukkit.getOnlinePlayers()) {
               for (PotionEffect effect : player.getActivePotionEffects()) {
                  UUID playerUUID = player.getUniqueId();
                  int duration = effect.getDuration();

                  if (cooldowns.containsKey(playerUUID) && cooldowns.get(playerUUID) > 0) {
                     cooldowns.put(playerUUID, cooldowns.get(playerUUID) - 1);
                     continue;
                  }

                  player.addPotionEffect(new PotionEffect(effect.getType(), duration - 20, effect.getAmplifier()));

                  cooldowns.put(playerUUID, duration);
               }
            }
         }
      }.runTaskTimer(main, 20L, 20L);
   }

}
