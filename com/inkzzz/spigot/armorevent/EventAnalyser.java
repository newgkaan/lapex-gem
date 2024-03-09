package com.inkzzz.spigot.armorevent;

import com.google.common.collect.Maps;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;
import newgkaan.gems.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class EventAnalyser implements Listener {
   private final ConcurrentMap<UUID, ItemStack[]> contents = Maps.newConcurrentMap();

   public EventAnalyser() {
      Bukkit.getOnlinePlayers().stream().forEach((player) -> {
         ItemStack[] var10000 = (ItemStack[])this.getContents().putIfAbsent(player.getUniqueId(), player.getEquipment().getArmorContents());
      });
   }

   @EventHandler
   public final void onEvent(InventoryClickEvent event) {
      if (event.getWhoClicked() instanceof Player) {
         Inventory inventory = event.getClickedInventory();
         if (inventory != null && (inventory.getType() == InventoryType.CRAFTING || inventory.getType() == InventoryType.PLAYER) && (event.getSlotType() == SlotType.ARMOR || event.isShiftClick())) {
            this.check((Player)event.getWhoClicked());
         }

      }
   }

   @EventHandler
   public final void onEvent(PlayerInteractEvent event) {
      Action action = event.getAction();
      if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
         ItemStack item = event.getItem();
         if (item == null) {
            return;
         }

         String name = item.getType().name();
         if (name.contains("_HELMET") || name.contains("_CHESTPLATE") || name.contains("_LEGGINGS") || name.contains("_BOOTS")) {
            this.check(event.getPlayer());
         }
      }

   }

   @EventHandler
   public final void onEvent(PlayerDeathEvent event) {
      this.check(event.getEntity());
   }

   @EventHandler
   public final void onEvent(PlayerJoinEvent event) {
      this.check(event.getPlayer());
   }

   @EventHandler
   public final void onEvent(PlayerQuitEvent event) {
      if (this.getContents().containsKey(event.getPlayer().getUniqueId())) {
         this.getContents().remove(event.getPlayer().getUniqueId());
      }

   }

   @EventHandler
   public final void onEvent(BlockDispenseEvent event) {
      ItemStack item = event.getItem();
      Location location = event.getBlock().getLocation();
      if (item != null) {
         location.getWorld().getNearbyEntities(location, 6.0D, 6.0D, 6.0D).stream().filter((e) -> {
            return e instanceof Player;
         }).map((e) -> {
            return (Player)e;
         }).forEach((player) -> {
            this.check(player);
         });
      }

   }

   @EventHandler
   public final void onEvent(PlayerItemBreakEvent event) {
      this.check(event.getPlayer());
   }

   private void check(final Player player) {
      (new BukkitRunnable() {
         public void run() {
            ItemStack[] now = player.getEquipment().getArmorContents();
            ItemStack[] saved = (ItemStack[])((ItemStack[])EventAnalyser.this.getContents().get(player.getUniqueId()));

            for(int i = 0; i < now.length; ++i) {
               if (now[i] == null && saved != null && saved[i] != null) {
                  Bukkit.getPluginManager().callEvent(new PlayerArmorUnequipEvent(player, saved[i]));
               } else if (now[i] == null || saved != null && saved[i] != null) {
                  if (saved != null && now[i] != null && saved[i] != null && !now[i].toString().equalsIgnoreCase(saved[i].toString())) {
                     Bukkit.getPluginManager().callEvent(new PlayerArmorUnequipEvent(player, saved[i]));
                     Bukkit.getPluginManager().callEvent(new PlayerArmorEquipEvent(player, now[i]));
                  }
               } else {
                  Bukkit.getPluginManager().callEvent(new PlayerArmorEquipEvent(player, now[i]));
               }
            }

            EventAnalyser.this.getContents().put(player.getUniqueId(), now);
         }
      }).runTaskLater(JavaPlugin.getPlugin(Main.instance.getClass()), 1L);
   }

   private ConcurrentMap<UUID, ItemStack[]> getContents() {
      return this.contents;
   }
}
