package newgkaan.gems.abilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerDeath implements Listener {
   @EventHandler
   public void on(PlayerDeathEvent e) {
      Player killer = e.getEntity().getKiller();
      if (killer != null) {
         ItemStack hand = killer.getItemInHand();
         if (hand != null) {
            if (hand.hasItemMeta()) {
               if (hand.getItemMeta().getLore() != null) {
                  List<String> lore = hand.getItemMeta().getLore();
                  if (!lore.isEmpty()) {
                     List<String> lore1 = new ArrayList();
                     Iterator var6 = lore.iterator();

                     while(var6.hasNext()) {
                        String var = (String)var6.next();
                        if (!var.contains("<Item#438#14#")) {
                           lore1.add(var);
                        } else {
                           int a = Integer.parseInt(var.replace("<Item#438#14#§eTopaz§r#Öldürülen: ", "").replace(">", ""));
                           ++a;
                           lore1.add("<Item#438#14#§eTopaz§r#Öldürülen: " + a + ">");
                        }
                     }

                     ItemMeta meta = hand.getItemMeta();
                     meta.setLore(lore1);
                     hand.setItemMeta(meta);
                  }
               }
            }
         }
      }
   }
}
