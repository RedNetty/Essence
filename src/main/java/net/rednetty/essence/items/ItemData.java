package net.rednetty.essence.items;


import net.rednetty.essence.utils.string.StringUtil;
import org.bukkit.Material;

/**
 * Created by Giovanni on 1/30/2021
 */
public class ItemData {

    private String name;
    private Material material;
    private ItemType itemType;
    private boolean droppable;
    private int amount = 1;

    /**
     * Loads {@link ItemData} from a JSON string and keeps type integrity.
     */
    public static <T extends ItemData> T loadFromString(String data, ItemType itemType) {
        return (T) StringUtil.GSON.fromJson(data, itemType.getDataClass());
    }

    public boolean isDroppable() {
        return droppable;
    }

    public void setDroppable(boolean droppable) {
        this.droppable = droppable;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}