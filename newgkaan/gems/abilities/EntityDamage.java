package newgkaan.gems.abilities;

import java.util.Iterator;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class EntityDamage implements Listener {
   @EventHandler(
      priority = EventPriority.HIGH,
      ignoreCancelled = true
   )
   public void on(EntityDamageByEntityEvent e) {
      if (e.getDamager() instanceof Player) {
         if (e.getEntity() instanceof Player) {
            if (e.getDamager().getVelocity().getY() >= 0.0D && !e.getDamager().isOnGround()) {
               ItemStack hand = ((Player)e.getDamager()).getItemInHand();
               if (hand != null) {
                  if (hand.hasItemMeta()) {
                     if (hand.getItemMeta().getLore() != null) {
                        List<String> lore = hand.getItemMeta().getLore();
                        if (!lore.isEmpty()) {
                           boolean critical = false;
                           Iterator var5 = lore.iterator();

                           while(var5.hasNext()) {
                              String var = (String)var5.next();
                              if (var.contains("<Item#438#0#")) {
                                 critical = true;
                                 break;
                              }
                           }

                           if (critical) {
                              double damage = e.getDamage();
                              damage += damage / 100.0D * 25.0D;
                              e.setDamage(damage);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }
}
