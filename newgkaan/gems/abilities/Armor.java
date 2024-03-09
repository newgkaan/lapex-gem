package newgkaan.gems.abilities;

import com.inkzzz.spigot.armorevent.PlayerArmorEquipEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class Armor implements Listener {
   @EventHandler
   public void on(PlayerArmorEquipEvent e) {
      List<ItemStack> stacks = new ArrayList(Arrays.asList(e.getPlayer().getInventory().getArmorContents()));
      int health = 0;
      Iterator var4 = stacks.iterator();

      while(true) {
         while(true) {
            ArrayList lore;
            do {
               ItemStack item;
               do {
                  if (!var4.hasNext()) {
                     health *= 2;
                     health += 20;
                     if (e.getPlayer().getMaxHealth() != (double)health) {
                        e.getPlayer().setMaxHealth((double)health);
                     }

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
               if (var.contains("<Item#438#12#")) {
                  ++health;
                  break;
               }
            }
         }
      }
   }
}
