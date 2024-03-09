package newgkaan.gems.gui;

import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Close implements Listener {
   @EventHandler
   public void on(InventoryCloseEvent e) {
      Inventory inv = e.getInventory();
      if (inv != null) {
         if (inv.getTitle() != null) {
            if (inv.getTitle().equals("Gem Ustası")) {
               ItemStack a = inv.getItem(10);
               ItemStack b = inv.getItem(13);
               if (a != null) {
                  e.getPlayer().getInventory().addItem(new ItemStack[]{a});
               }

               if (b != null) {
                  e.getPlayer().getInventory().addItem(new ItemStack[]{b});
               }

               e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.ITEM_PICKUP, 1.0F, 1.0F);
            }
         }
      }
   }
}
