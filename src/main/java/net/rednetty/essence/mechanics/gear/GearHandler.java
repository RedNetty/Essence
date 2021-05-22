package net.rednetty.essence.mechanics.gear;

import de.tr7zw.nbtapi.NBTItem;
import net.rednetty.essence.mechanics.Mechanic;
import net.rednetty.essence.mechanics.MechanicHandler;
import net.rednetty.essence.mechanics.player.eplayer.EssencePlayer;
import org.bukkit.Effect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

public class GearHandler extends Mechanic {

    @Override
    public void onLoad() {
        listener(this);
    }

    @Override
    public void onUnload() {

    }

    /**
     * Calculates Weapon Damages and Weapon Stats
     */
    @EventHandler
    public void weaponCalculation(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) event.getDamager();
            Entity damaged = event.getEntity();
            ItemStack itemStack = entity.getEquipment().getItemInMainHand();
            if(itemStack.getType() == null || itemStack.getType().isAir()) return;
            NBTItem nbtItem = new NBTItem(itemStack);
            int totalDamage = 0;
            if(nbtItem.hasKey("min") && nbtItem.hasKey("max")) {
                totalDamage += ThreadLocalRandom.current().nextInt(nbtItem.getInteger("min"), nbtItem.getInteger("max"));
                System.out.println(totalDamage);
            }
            if(nbtItem.hasKey("fire")) {
                damaged.setFireTicks(40);
                damaged.getWorld().playEffect(damaged.getLocation().add(0, 1, 0), Effect.POTION_BREAK, 12);
                totalDamage += nbtItem.getInteger("fire");
            }
            if(nbtItem.hasKey("ice")) {
                damaged.getWorld().playEffect(damaged.getLocation().add(0, 1, 0), Effect.POTION_BREAK, 1);
                totalDamage += nbtItem.getInteger("ice");
            }
            if(nbtItem.hasKey("poison")) {
                damaged.getWorld().playEffect(damaged.getLocation().add(0, 1, 0), Effect.POTION_BREAK, 19);
                totalDamage += nbtItem.getInteger("poison");
            }
            if(nbtItem.hasKey("crit")) {
                int crit = nbtItem.getInteger("crit");
                int critRoll = ThreadLocalRandom.current().nextInt(100);
                if(critRoll <= crit)  {
                    totalDamage *= 1.5;
                }
            }
            event.setDamage(totalDamage);
        }
    }
}
