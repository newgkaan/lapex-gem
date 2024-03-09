package newgkaan.gems.abilities;

import java.util.*;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionEffectAddEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Effect implements Listener {
   private final Map<PotionEffectType, Set<UUID>> handled = new HashMap<>();


   @EventHandler
   public void onPotionEffectAdd(PotionEffectAddEvent event) {
      PotionEffect effect = event.getEffect();
      PotionEffectType effectType = effect.getType();

      if (event.getCause() == PotionEffectAddEvent.EffectCause.PLUGIN || !(event.getEntity() instanceof Player))
         return;

      Player player = (Player) event.getEntity();
      if (effectType.equals(PotionEffectType.SLOW) || effectType.equals(PotionEffectType.WEAKNESS) || effectType.equals(PotionEffectType.POISON)) {
         int multiplier = calculateMultiplier(player);
         if (multiplier > 0) {
            handleEffect(event, effect, multiplier);
         }
      }
   }

   private int calculateMultiplier(Player player) {
      int multiplier = 0;
      for (ItemStack item : player.getInventory().getArmorContents()) {
         if (item != null && item.hasItemMeta() && item.getItemMeta().getLore() != null) {
            for (String loreLine : item.getItemMeta().getLore()) {
               if (loreLine.contains("<Item#438#12#")) {
                  multiplier += 10;
                  break;
               }
            }
         }
      }
      return multiplier;
   }

   private void handleEffect(PotionEffectAddEvent event, PotionEffect effect, int multiplier) {
      int duration = effect.getDuration() - (effect.getDuration() / 100 * multiplier);
      if (duration <= 0) {
         event.setCancelled(true);
         return;
      }

      Set<UUID> handledList = handled.computeIfAbsent(effect.getType(), k -> new HashSet<>());
      handledList.add(event.getEntity().getUniqueId());
      event.setCancelled(true);
      PotionEffect newEffect = new PotionEffect(effect.getType(), duration, effect.getAmplifier());
      event.getEntity().addPotionEffect(newEffect);
   }

}
