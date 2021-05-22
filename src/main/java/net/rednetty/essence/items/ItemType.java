package net.rednetty.essence.items;

import net.rednetty.essence.items.book.BookItem;
import net.rednetty.essence.items.book.BookItemData;
import net.rednetty.essence.items.equipment.EquipmentItem;
import net.rednetty.essence.items.equipment.EquipmentItemData;
import net.rednetty.essence.items.skull.SkullItem;
import net.rednetty.essence.items.skull.SkullItemData;
import net.rednetty.essence.items.weapon.WeaponItem;
import net.rednetty.essence.items.weapon.WeaponItemData;

/**
 * Created by Giovanni on 1/6/2021
 */
public enum ItemType {
    WEAPON("Weapon", WeaponItem.class, WeaponItemData.class),
    EQUIPMENT("Equipment", EquipmentItem.class, EquipmentItemData.class),
    BOOK("Book", BookItem.class, BookItemData.class),
    SKULL("Skull", SkullItem.class, SkullItemData.class),
    QUEST_ITEM("Quest Item", null, null);

    private final String name;
    private final Class<? extends EssenceItem> itemClass;
    private final Class<? extends ItemData> dataClass;

    ItemType(String name, Class<? extends EssenceItem> itemClass, Class<? extends ItemData> dataClass) {
        this.name = name;
        this.itemClass = itemClass;
        this.dataClass = dataClass;
    }

    /**
     * Returns a new {@link EssenceItem} from the {@link #itemClass}.
     */
    public EssenceItem createNew(ItemData itemData) {
        EssenceItem EssenceItem = null;
        try {
            EssenceItem = itemClass.getConstructor(dataClass).newInstance(itemData);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(name + "@" + itemData.getClass() + " -> " + dataClass);

        }
        return EssenceItem;
    }

    /**
     * Returns the class of the {@link ItemData} of this type.
     */
    public Class<? extends ItemData> getDataClass() {
        return dataClass;
    }

    /**
     * Returns the name of the ItemType.
     */
    public String getName() {
        return name;
    }
}