package newgkaan.gems.abilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDamage implements Listener {
   @EventHandler(
      priority = EventPriority.HIGH,
      ignoreCancelled = true
   )
   public void on(EntityDamageByEntityEvent e) {
      if (e.getDamager() instanceof Player) {
         if (e.getEntity() instanceof Player) {
            List<ItemStack> list = new ArrayList(Arrays.asList(((Player)e.getEntity()).getInventory().getArmorContents()));
            int damage = 0;
            Iterator var4 = list.iterator();

            while(true) {
               while(true) {
                  ArrayList lore;
                  do {
                     ItemStack item;
                     do {
                        if (!var4.hasNext()) {
                           if (damage == 0) {
                              return;
                           }

                           ItemStack hand = ((Player)e.getDamager()).getItemInHand();
                           if (hand == null) {
                              return;
                           }

                           if (hand.getType().equals(Material.AIR)) {
                              return;
                           }

                           hand.setDurability((short)(hand.getDurability() + damage));
                           return;
                        }

                        item = (ItemStack)var4.next();
                     } while(!item.hasItemMeta());

                     if (item.getItemMeta().getLore() != null) {
                        lore = new ArrayList(item.getItemMeta().getLore());
                     } else {
                        lore = new ArrayList();
                     }
                  } while(lore.isEmpty());

                  Iterator var7 = lore.iterator();

                  while(var7.hasNext()) {
                     String var = (String)var7.next();
                     if (var.contains("<Item#438#4#")) {
                        ++damage;
                        break;
                     }
                  }
               }
            }
         }
      }
   }
}
