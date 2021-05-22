package net.rednetty.essence.mechanics.gear;

import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.plugin.NBTAPI;
import net.rednetty.essence.mechanics.gear.enums.WeaponStats;
import net.rednetty.essence.utils.string.StringUtil;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class WeaponBuilder {
    private ItemStack weapon;
    private int minDamage;
    private int maxDamage;
    private NBTItem nbtItem;
    private ItemMeta itemMeta;
    private ArrayList<String> lore = new ArrayList<>();


    public WeaponBuilder(ItemStack itemStack, int minDamage, int maxDamage) {
        this.maxDamage = maxDamage;
        this.minDamage = minDamage;
        this.weapon = itemStack;
        this.itemMeta = weapon.getItemMeta();
        this.nbtItem = new NBTItem(weapon);
        nbtItem.clearCustomNBT();


        nbtItem.setInteger("min", minDamage);
        nbtItem.setInteger("max", maxDamage);
        nbtItem.applyNBT(weapon);


    }

    public WeaponBuilder addStat(WeaponStats stats, int amount) {
        switch (stats) {
            case ICE:
                nbtItem.setInteger("ice", amount);
                break;
            case CRIT:
                nbtItem.setInteger("crit", amount);
                break;
            case FIRE:
                nbtItem.setInteger("fire", amount);
                break;
            case POISON:
                nbtItem.setInteger("poison", amount);
                break;
        }
        return this;
    }

    private void applyLore() {
//        lore.add(StringUtil.color("&6• &cDamage: &f" + minDamage + " - " + maxDamage));
//        if(nbtItem.hasKey("ice")) {
//            lore.add(StringUtil.color("&6• &cIce &cDamage: &f" + nbtItem.getInteger("ice")));
//        }
//        if(nbtItem.hasKey("fire")) {
//            lore.add(StringUtil.color("&6• &cFire &cDamage: &f" + nbtItem.getInteger("fire")));
//        }
//        if(nbtItem.hasKey("crit")) {
//            lore.add(StringUtil.color("&6• &cCritical Chance: &f " + nbtItem.getInteger("crit") + "%"));
//        }
//        if(nbtItem.hasKey("poison")) {
//            lore.add(StringUtil.color("&6• &cPoison &cDamage: &f" + nbtItem.getInteger("poison")));
//        }
//
//        itemMeta.setLore(lore);
//    }
//
//    public WeaponBuilder setName(String string) {
//    itemMeta.setDisplayName(StringUtil.color(string));
//    return this;
//    }
//    public ItemStack build() {
//        applyLore();
//        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//
//        weapon.setItemMeta(itemMeta);
//        return weapon;
//    }
//}
    }
}