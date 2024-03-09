package newgkaan.gems.gem;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Gem {
   private final ItemStack a;
   private final ItemStack b;
   private final ItemStack c;

   public Gem(ItemStack a, ItemStack b, ItemStack c) {
      this.a = a;
      this.b = b;
      this.c = c;
   }

   public boolean firstEmpty() {
      if (this.a == null) {
         return true;
      } else if (this.a.getType().equals(Material.AIR)) {
         return true;
      } else {
         return this.b == null ? true : this.b.getType().equals(Material.AIR);
      }
   }

   public boolean isGem() {
      return this.b.getType().equals(Material.GEM) || this.b.getType().equals(Material.HAMMER);
   }

   public Type getType() {
      if (this.b.getType().equals(Material.HAMMER)) {
         return Type.Cleartool;
      } else {
         if (this.b.getType().equals(Material.GEM)) {
            short data = (short)this.b.getData().getData();
            switch(data) {
            case 0:
               return Type.Ametist;
            case 1:
               return Type.Turkuaz;
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 13:
            default:
               break;
            case 4:
               return Type.Garnet;
            case 11:
               return Type.Safir;
            case 12:
               return Type.Spinel;
            case 14:
               return Type.Topaz;
            }
         }

         return Type.Anormal;
      }
   }
}
