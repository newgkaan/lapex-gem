package newgkaan.gems.abilities;

import java.util.*;

import newgkaan.gems.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Effect implements Listener {
  @EventHandler
public void onPotionEffectAdd(PotionEffectAddEvent event) {
    PotionEffect effect = event.getEffect();
    if (isEffectHandled(effect)) {
        handleEffect(event, effect);
    }
}

private boolean isEffectHandled(PotionEffect effect) {
    PotionEffectType type = effect.getType();
    return !effect.getCause().equals(EffectCause.PLUGIN) &&
           (type.equals(PotionEffectType.SLOW) || 
            type.equals(PotionEffectType.WEAKNESS) || 
            type.equals(PotionEffectType.POISON));
}

private void handleEffect(PotionEffectAddEvent event, PotionEffect effect) {
    Player player = (Player) event.getEntity();
    int multiplier = calculateMultiplier(player);
    if (multiplier == 0) {
        return;
    }

    event.setCancelled(true);
    List<UUID> handledList = handled.computeIfAbsent(effect.getType(), k -> new ArrayList<>());
    handledList.add(player.getUniqueId());

    int duration = effect.getDuration();
    duration -= duration / 100 * multiplier;
    PotionEffect newEffect = new PotionEffect(effect.getType(), duration, effect.getAmplifier());
    player.addPotionEffect(newEffect);
}

private int calculateMultiplier(Player player) {
    List<ItemStack> armorContents = new ArrayList<>(Arrays.asList(player.getInventory().getArmorContents()));
    int multiplier = 0;
    for (ItemStack item : armorContents) {
        if (item != null && item.hasItemMeta()) {
            List<String> lore = item.getItemMeta().getLore();
            if (lore != null && !lore.isEmpty()) {
                for (String line : lore) {
                    if (line.contains("<Item#438#12#")) {
                        multiplier += 10;
                        break;
                    }
                }
            }
        }
    }
    return multiplier;
}

}
