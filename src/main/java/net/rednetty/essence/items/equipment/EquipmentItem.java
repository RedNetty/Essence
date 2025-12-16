package net.rednetty.essence.items.equipment;

import net.rednetty.essence.items.EssenceItem;

import java.util.Arrays;

/**
 * Created by Giovanni on 3/28/2021
 */
public class EquipmentItem extends EssenceItem {

    public EquipmentItem(EquipmentItemData itemData) {
        super(itemData);
        int health = getAttributes().getHealth();
        setCustomLore(Arrays.asList("&7▪ &c+" + health + " &fHealth"));
    }

    /**
     * Returns the {@link EquipmentItemData} of this equipment piece.
     */
    public EquipmentAttributeMap getAttributes() {
        return ((EquipmentItemData) getItemData()).getAttributeMap();
    }
}
