package net.rednetty.essence.items.weapon;

import net.rednetty.essence.items.EssenceItem;

import java.util.Arrays;

/**
 * Created by Giovanni on 2/12/2021
 */
public class WeaponItem extends EssenceItem {

    public WeaponItem(WeaponItemData itemData) {
        super(itemData);
        int[] damageRange = getAttributes().getDamage();
        setCustomLore(Arrays.asList("&7▪ &c" + damageRange[0] + "-" + damageRange[1] + " &fDamage"));
    }

    /**
     * Returns the {@link WeaponAttributeMap} of this weapon.
     */
    public WeaponAttributeMap getAttributes() {
        return ((WeaponItemData) getItemData()).getAttributeMap();
    }
}
