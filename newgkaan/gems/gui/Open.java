package newgkaan.gems.gui;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Open {
   public Open(Player var) {
      Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, "Gem Ustası");
      ItemStack book = new ItemStack(Material.BOOK);
      ItemMeta meta = book.getItemMeta();
      meta.setDisplayName("§eBilgilendirme!");
      meta.setLore(Arrays.asList("", "§7Gördüğün ilk boşluğa", "§7kılıcını, ikinci boşluğa", "§7gemi yerleştirerek", "§7birleştirebilirsin!", ""));
      book.setItemMeta(meta);

      for(int x = 0; x < 27; ++x) {
         ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
         ItemMeta glassMeta = glass.getItemMeta();
         glassMeta.setDisplayName(" ");
         glass.setItemMeta(glassMeta);
         inv.setItem(x, glass);
      }

      inv.setItem(0, book);
      inv.setItem(10, new ItemStack(Material.AIR));
      inv.setItem(13, new ItemStack(Material.AIR));
      inv.setItem(16, new ItemStack(Material.AIR));
      var.openInventory(inv);
   }
}
