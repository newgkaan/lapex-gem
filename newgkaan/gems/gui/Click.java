package newgkaan.gems.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import newgkaan.gems.Main;
import newgkaan.gems.gem.Gem;
import newgkaan.gems.gem.Type;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class Click implements Listener {
   @EventHandler
   public void on(final InventoryClickEvent e) {
      final InventoryView inv = e.getWhoClicked().getOpenInventory();
      if (inv != null) {
         if (inv.getTitle() != null) {
            if (inv.getTitle().equals("Gem Ustası")) {
               if (e.getClickedInventory() == inv.getTopInventory() && e.getSlot() != 10 && e.getSlot() != 13 && e.getSlot() != 16) {
                  e.setCancelled(true);
               } else if (e.getClickedInventory() == inv.getTopInventory() && e.getSlot() == 16) {
                  if (!e.getAction().equals(InventoryAction.PICKUP_ALL)) {
                     e.setCancelled(true);
                  } else if (e.getClickedInventory().getItem(10) != null) {
                     if (e.getClickedInventory().getItem(13) != null) {
                        ItemStack stack = e.getCurrentItem();
                        if (stack != null && !stack.getType().equals(Material.AIR)) {
                           inv.setItem(10, new ItemStack(Material.AIR));
                           if (inv.getItem(13).getAmount() > 1) {
                              ItemStack f = inv.getItem(13).clone();
                              f.setAmount(f.getAmount() - 1);
                              inv.setItem(13, f);
                           } else {
                              inv.setItem(13, new ItemStack(Material.AIR));
                           }

                           (new BukkitRunnable() {
                              public void run() {
                                 ((Player)e.getWhoClicked()).updateInventory();
                              }
                           }).runTaskLater(Main.instance, 3L);
                           e.getWhoClicked().getWorld().playSound(e.getWhoClicked().getLocation(), Sound.ANVIL_USE, 1.0F, 1.0F);
                        }

                     }
                  }
               } else {
                  (new BukkitRunnable() {
                     public void run() {
                        Gem gem = new Gem(inv.getItem(10), inv.getItem(13), inv.getItem(16));
                        if (gem.firstEmpty()) {
                           inv.setItem(16, new ItemStack(Material.AIR));
                        } else if (!gem.isGem()) {
                           inv.setItem(16, new ItemStack(Material.AIR));
                        } else {
                           Type type = gem.getType();
                           if (type == Type.Anormal) {
                              inv.setItem(16, new ItemStack(Material.AIR));
                           } else {
                              ItemStack a;
                              ArrayList aLorex;
                              if (type == Type.Cleartool) {
                                 a = inv.getItem(10).clone();
                                 ItemMeta aMetax = a.getItemMeta();
                                 List<String> aLore = aMetax.getLore();
                                 aLorex = new ArrayList();
                                 Iterator var7 = aLore.iterator();

                                 while(var7.hasNext()) {
                                    String var = (String)var7.next();
                                    if (var.contains("<Item#")) {
                                       aLorex.add(var);
                                    }
                                 }

                                 aLore.removeAll(aLorex);
                                 aMetax.setLore(aLore);
                                 a.setItemMeta(aMetax);
                                 inv.setItem(16, a);
                              }

                              String varx;
                              String varxx;
                              boolean is;
                              ItemMeta aMeta;
                              ArrayList gems;
                              Iterator var14;
                              boolean first;
                              Iterator var16;
                              if (type == Type.Spinel) {
                                 a = inv.getItem(10).clone();
                                 is = false;
                                 switch(a.getType()) {
                                 case TITANIUM_BOOTS:
                                 case TITANIUM_CHESTPLATE:
                                 case TITANIUM_LEGGINGS:
                                 case TITANIUM_HELMET:
                                    is = true;
                                 }

                                 if (!is) {
                                    inv.setItem(16, new ItemStack(Material.AIR));
                                    return;
                                 }

                                 aMeta = a.getItemMeta();
                                 aLorex = new ArrayList();
                                 if (aMeta.getLore() != null) {
                                    aLorex.addAll(aMeta.getLore());
                                 }

                                 gems = new ArrayList();
                                 if (!aLorex.isEmpty()) {
                                    var14 = aLorex.iterator();

                                    while(var14.hasNext()) {
                                       varx = (String)var14.next();
                                       if (varx.contains("<Item#")) {
                                          if (varx.contains("<Item#438#12#")) {
                                             inv.setItem(16, new ItemStack(Material.AIR));
                                             return;
                                          }

                                          gems.add(varx);
                                       }
                                    }

                                    aLorex.removeAll(gems);
                                 }

                                 first = false;
                                 if (gems.isEmpty()) {
                                    first = true;
                                    gems.add("<Item#0#0#Boş Gem Yuvası#Buraya gem yerleştirilebilir>");
                                    gems.add("<Item#0#0#Boş Gem Yuvası#Buraya gem yerleştirilebilir>");
                                    gems.add("<Item#0#0#Boş Gem Yuvası#Buraya gem yerleştirilebilir>");
                                 }

                                 if (!first) {
                                    Collections.reverse(gems);
                                 }

                                 var16 = gems.iterator();

                                 while(var16.hasNext()) {
                                    varxx = (String)var16.next();
                                    if (varxx.contains("<Item#0#0#Boş Gem Yuvası#")) {
                                       gems.remove(varxx);
                                       gems.add("<Item#438#12#§cSpinel§r#+1 Kalp>");
                                       break;
                                    }
                                 }

                                 Collections.reverse(gems);
                                 aLorex.addAll(gems);
                                 aMeta.setLore(aLorex);
                                 a.setItemMeta(aMeta);
                                 inv.setItem(16, a);
                              }

                              if (type == Type.Ametist) {
                                 a = inv.getItem(10).clone();
                                 is = false;
                                 switch(a.getType()) {
                                 case DIAMOND_SWORD:
                                 case TITANIUM_WARAXE:
                                 case SWORD_BUZPERISI:
                                 case SWORD_KANKILICI:
                                 case SWORD_MAVIGOKYUZUKILICI:
                                 case SWORD_ZEHIRLIKESKINLIK:
                                    is = true;
                                 }

                                 if (!is) {
                                    inv.setItem(16, new ItemStack(Material.AIR));
                                    return;
                                 }

                                 aMeta = a.getItemMeta();
                                 aLorex = new ArrayList();
                                 if (aMeta.getLore() != null) {
                                    aLorex.addAll(aMeta.getLore());
                                 }

                                 gems = new ArrayList();
                                 if (!aLorex.isEmpty()) {
                                    var14 = aLorex.iterator();

                                    while(var14.hasNext()) {
                                       varx = (String)var14.next();
                                       if (varx.contains("<Item#")) {
                                          if (varx.contains("<Item#438#0#")) {
                                             inv.setItem(16, new ItemStack(Material.AIR));
                                             return;
                                          }

                                          gems.add(varx);
                                       }
                                    }

                                    aLorex.removeAll(gems);
                                 }

                                 first = false;
                                 if (gems.isEmpty()) {
                                    first = true;
                                    gems.add("<Item#0#0#Boş Gem Yuvası#Buraya gem yerleştirilebilir>");
                                    gems.add("<Item#0#0#Boş Gem Yuvası#Buraya gem yerleştirilebilir>");
                                    gems.add("<Item#0#0#Boş Gem Yuvası#Buraya gem yerleştirilebilir>");
                                 }

                                 if (!first) {
                                    Collections.reverse(gems);
                                 }

                                 var16 = gems.iterator();

                                 while(var16.hasNext()) {
                                    varxx = (String)var16.next();
                                    if (varxx.contains("<Item#0#0#Boş Gem Yuvası#")) {
                                       gems.remove(varxx);
                                       gems.add("<Item#438#0#§5Ametist§r#Kritik hasarına %25>");
                                       break;
                                    }
                                 }

                                 Collections.reverse(gems);
                                 aLorex.addAll(gems);
                                 aMeta.setLore(aLorex);
                                 a.setItemMeta(aMeta);
                                 inv.setItem(16, a);
                              }

                              if (type == Type.Turkuaz) {
                                 a = inv.getItem(10).clone();
                                 is = false;
                                 switch(a.getType()) {
                                 case TITANIUM_BOOTS:
                                 case TITANIUM_CHESTPLATE:
                                 case TITANIUM_LEGGINGS:
                                 case TITANIUM_HELMET:
                                 case DIAMOND_HELMET:
                                 case DIAMOND_CHESTPLATE:
                                 case DIAMOND_LEGGINGS:
                                 case DIAMOND_BOOTS:
                                    is = true;
                                 case DIAMOND_SWORD:
                                 case TITANIUM_WARAXE:
                                 case SWORD_BUZPERISI:
                                 case SWORD_KANKILICI:
                                 case SWORD_MAVIGOKYUZUKILICI:
                                 case SWORD_ZEHIRLIKESKINLIK:
                                 }

                                 if (!is) {
                                    inv.setItem(16, new ItemStack(Material.AIR));
                                    return;
                                 }

                                 aMeta = a.getItemMeta();
                                 aLorex = new ArrayList();
                                 if (aMeta.getLore() != null) {
                                    aLorex.addAll(aMeta.getLore());
                                 }

                                 gems = new ArrayList();
                                 if (!aLorex.isEmpty()) {
                                    var14 = aLorex.iterator();

                                    while(var14.hasNext()) {
                                       varx = (String)var14.next();
                                       if (varx.contains("<Item#")) {
                                          if (varx.contains("<Item#438#1#")) {
                                             inv.setItem(16, new ItemStack(Material.AIR));
                                             return;
                                          }

                                          gems.add(varx);
                                       }
                                    }

                                    aLorex.removeAll(gems);
                                 }

                                 first = false;
                                 if (gems.isEmpty()) {
                                    first = true;
                                    gems.add("<Item#0#0#Boş Gem Yuvası#Buraya gem yerleştirilebilir>");
                                    gems.add("<Item#0#0#Boş Gem Yuvası#Buraya gem yerleştirilebilir>");
                                    gems.add("<Item#0#0#Boş Gem Yuvası#Buraya gem yerleştirilebilir>");
                                 }

                                 if (!first) {
                                    Collections.reverse(gems);
                                 }

                                 var16 = gems.iterator();

                                 while(var16.hasNext()) {
                                    varxx = (String)var16.next();
                                    if (varxx.contains("<Item#0#0#Boş Gem Yuvası#")) {
                                       gems.remove(varxx);
                                       gems.add("<Item#438#1#§bTurkuaz§r#Kırılmazlık %10>");
                                       break;
                                    }
                                 }

                                 Collections.reverse(gems);
                                 aLorex.addAll(gems);
                                 aMeta.setLore(aLorex);
                                 a.setItemMeta(aMeta);
                                 inv.setItem(16, a);
                              }

                              if (type == Type.Topaz) {
                                 a = inv.getItem(10).clone();
                                 is = false;
                                 switch(a.getType()) {
                                 case DIAMOND_SWORD:
                                 case TITANIUM_WARAXE:
                                 case SWORD_BUZPERISI:
                                 case SWORD_KANKILICI:
                                 case SWORD_MAVIGOKYUZUKILICI:
                                 case SWORD_ZEHIRLIKESKINLIK:
                                    is = true;
                                 }

                                 if (!is) {
                                    inv.setItem(16, new ItemStack(Material.AIR));
                                    return;
                                 }

                                 aMeta = a.getItemMeta();
                                 aLorex = new ArrayList();
                                 if (aMeta.getLore() != null) {
                                    aLorex.addAll(aMeta.getLore());
                                 }

                                 gems = new ArrayList();
                                 if (!aLorex.isEmpty()) {
                                    var14 = aLorex.iterator();

                                    while(var14.hasNext()) {
                                       varx = (String)var14.next();
                                       if (varx.contains("<Item#")) {
                                          if (varx.contains("<Item#438#14#")) {
                                             inv.setItem(16, new ItemStack(Material.AIR));
                                             return;
                                          }

                                          gems.add(varx);
                                       }
                                    }

                                    aLorex.removeAll(gems);
                                 }

                                 first = false;
                                 if (gems.isEmpty()) {
                                    first = true;
                                    gems.add("<Item#0#0#Boş Gem Yuvası#Buraya gem yerleştirilebilir>");
                                    gems.add("<Item#0#0#Boş Gem Yuvası#Buraya gem yerleştirilebilir>");
                                    gems.add("<Item#0#0#Boş Gem Yuvası#Buraya gem yerleştirilebilir>");
                                 }

                                 if (!first) {
                                    Collections.reverse(gems);
                                 }

                                 var16 = gems.iterator();

                                 while(var16.hasNext()) {
                                    varxx = (String)var16.next();
                                    if (varxx.contains("<Item#0#0#Boş Gem Yuvası#")) {
                                       gems.remove(varxx);
                                       gems.add("<Item#438#14#§eTopaz§r#Öldürülen: 0>");
                                       break;
                                    }
                                 }

                                 Collections.reverse(gems);
                                 aLorex.addAll(gems);
                                 aMeta.setLore(aLorex);
                                 a.setItemMeta(aMeta);
                                 inv.setItem(16, a);
                              }

                              if (type == Type.Safir) {
                                 a = inv.getItem(10).clone();
                                 is = false;
                                 switch(a.getType()) {
                                 case TITANIUM_BOOTS:
                                 case TITANIUM_CHESTPLATE:
                                 case TITANIUM_LEGGINGS:
                                 case TITANIUM_HELMET:
                                 case DIAMOND_HELMET:
                                 case DIAMOND_CHESTPLATE:
                                 case DIAMOND_LEGGINGS:
                                 case DIAMOND_BOOTS:
                                    is = true;
                                 case DIAMOND_SWORD:
                                 case TITANIUM_WARAXE:
                                 case SWORD_BUZPERISI:
                                 case SWORD_KANKILICI:
                                 case SWORD_MAVIGOKYUZUKILICI:
                                 case SWORD_ZEHIRLIKESKINLIK:
                                 }

                                 if (!is) {
                                    inv.setItem(16, new ItemStack(Material.AIR));
                                    return;
                                 }

                                 aMeta = a.getItemMeta();
                                 aLorex = new ArrayList();
                                 if (aMeta.getLore() != null) {
                                    aLorex.addAll(aMeta.getLore());
                                 }

                                 gems = new ArrayList();
                                 if (!aLorex.isEmpty()) {
                                    var14 = aLorex.iterator();

                                    while(var14.hasNext()) {
                                       varx = (String)var14.next();
                                       if (varx.contains("<Item#")) {
                                          if (varx.contains("<Item#438#11#")) {
                                             inv.setItem(16, new ItemStack(Material.AIR));
                                             return;
                                          }

                                          gems.add(varx);
                                       }
                                    }

                                    aLorex.removeAll(gems);
                                 }

                                 first = false;
                                 if (gems.isEmpty()) {
                                    first = true;
                                    gems.add("<Item#0#0#Boş Gem Yuvası#Buraya gem yerleştirilebilir>");
                                    gems.add("<Item#0#0#Boş Gem Yuvası#Buraya gem yerleştirilebilir>");
                                    gems.add("<Item#0#0#Boş Gem Yuvası#Buraya gem yerleştirilebilir>");
                                 }

                                 if (!first) {
                                    Collections.reverse(gems);
                                 }

                                 var16 = gems.iterator();

                                 while(var16.hasNext()) {
                                    varxx = (String)var16.next();
                                    if (varxx.contains("<Item#0#0#Boş Gem Yuvası#")) {
                                       gems.remove(varxx);
                                       gems.add("<Item#438#11#§9Safir§r#Kötü etkiler -%10>");
                                       break;
                                    }
                                 }

                                 Collections.reverse(gems);
                                 aLorex.addAll(gems);
                                 aMeta.setLore(aLorex);
                                 a.setItemMeta(aMeta);
                                 inv.setItem(16, a);
                              }

                              if (type == Type.Garnet) {
                                 a = inv.getItem(10).clone();
                                 is = false;
                                 switch(a.getType()) {
                                 case TITANIUM_BOOTS:
                                 case TITANIUM_CHESTPLATE:
                                 case TITANIUM_LEGGINGS:
                                 case TITANIUM_HELMET:
                                 case DIAMOND_HELMET:
                                 case DIAMOND_CHESTPLATE:
                                 case DIAMOND_LEGGINGS:
                                 case DIAMOND_BOOTS:
                                    is = true;
                                 case DIAMOND_SWORD:
                                 case TITANIUM_WARAXE:
                                 case SWORD_BUZPERISI:
                                 case SWORD_KANKILICI:
                                 case SWORD_MAVIGOKYUZUKILICI:
                                 case SWORD_ZEHIRLIKESKINLIK:
                                 }

                                 if (!is) {
                                    inv.setItem(16, new ItemStack(Material.AIR));
                                    return;
                                 }

                                 aMeta = a.getItemMeta();
                                 aLorex = new ArrayList();
                                 if (aMeta.getLore() != null) {
                                    aLorex.addAll(aMeta.getLore());
                                 }

                                 gems = new ArrayList();
                                 if (!aLorex.isEmpty()) {
                                    var14 = aLorex.iterator();

                                    while(var14.hasNext()) {
                                       varx = (String)var14.next();
                                       if (varx.contains("<Item#")) {
                                          if (varx.contains("<Item#438#4#")) {
                                             inv.setItem(16, new ItemStack(Material.AIR));
                                             return;
                                          }

                                          gems.add(varx);
                                       }
                                    }

                                    aLorex.removeAll(gems);
                                 }

                                 first = false;
                                 if (gems.isEmpty()) {
                                    first = true;
                                    gems.add("<Item#0#0#Boş Gem Yuvası#Buraya gem yerleştirilebilir>");
                                    gems.add("<Item#0#0#Boş Gem Yuvası#Buraya gem yerleştirilebilir>");
                                    gems.add("<Item#0#0#Boş Gem Yuvası#Buraya gem yerleştirilebilir>");
                                 }

                                 if (!first) {
                                    Collections.reverse(gems);
                                 }

                                 var16 = gems.iterator();

                                 while(var16.hasNext()) {
                                    varxx = (String)var16.next();
                                    if (varxx.contains("<Item#0#0#Boş Gem Yuvası#")) {
                                       gems.remove(varxx);
                                       gems.add("<Item#438#4#§4Garnet§r#Rakip kılıcına hasar>");
                                       break;
                                    }
                                 }

                                 Collections.reverse(gems);
                                 aLorex.addAll(gems);
                                 aMeta.setLore(aLorex);
                                 a.setItemMeta(aMeta);
                                 inv.setItem(16, a);
                              }

                           }
                        }
                     }
                  }).runTaskLater(Main.instance, 1L);
               }
            }
         }
      }
   }
}
