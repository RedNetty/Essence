package net.rednetty.essence.items;

/**
 * Created by Giovanni on 1/30/2021
 */
public enum ItemKey {

    ITEM_TYPE("si_ItemType"),
    ITEM_DATA("si_ItemData");

    private final String key;

    ItemKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}