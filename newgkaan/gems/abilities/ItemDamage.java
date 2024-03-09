package newgkaan.gems.abilities;

import java.util.Iterator;
import java.util.List;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public class ItemDamage implements Listener {
   @EventHandler
   public void on(PlayerItemDamageEvent e) {
      ItemStack item = e.getItem();
      if (item.hasItemMeta()) {
         if (item.getItemMeta().getLore() != null) {
            List<String> lore = item.getItemMeta().getLore();
            if (!lore.isEmpty()) {
               boolean has = false;
               Iterator var5 = lore.iterator();

               while(var5.hasNext()) {
                  String var = (String)var5.next();
                  if (var.contains("<Item#438#1#")) {
                     has = true;
                     break;
                  }
               }

               if (has) {
                  if (Math.random() < 0.2D) {
                     e.setCancelled(true);
                  }

               }
            }
         }
      }
   }
}
