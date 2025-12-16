package net.rednetty.essence.items;

import com.google.common.collect.Lists;
import net.rednetty.essence.utils.string.StringUtil;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Giovanni on 1/6/2021
 */
public class EssenceItem {

    private String name;
    private Material material;
    private ItemType type;
    private boolean glowing = false;

    private ItemData itemData;

    /* These will be managed by Minecraft itself, we don't have to
       worry about adding these as NBT tag values to the ItemStack.
     */
    private transient List<String> customLore;

    public EssenceItem(String name, Material material, ItemType type) {
        this.name = name;
        this.material = material;
        this.type = type;
        this.itemData = new ItemData();
        this.itemData.setName(name);
        this.itemData.setMaterial(material);
        this.itemData.setItemType(type);
        this.itemData.setDroppable(true);
    }

    public EssenceItem(ItemData itemData) {
        this.name = itemData.getName();
        this.material = itemData.getMaterial();
        this.type = itemData.getItemType();
        this.itemData = itemData;
    }

    /**
     * Returns whether an {@link ItemStack} is a EssenceItem or not.
     */
    public static boolean isEssenceItem(ItemStack itemStack) {
        EssenceItemStack EssenceItemStack = new EssenceItemStack(itemStack);
        return EssenceItemStack.hasNBTKey(ItemKey.ITEM_TYPE.getKey());
    }

    /**
     * Converts an {@link ItemStack} to a usable EssenceItem, if
     * the given ItemStack is a EssenceItem.
     *
     * @param itemTypeClass The {@link EssenceItem} class.
     */
    @SuppressWarnings("unchecked")
    @Nullable
    public static <T extends EssenceItem> T toEssenceItem(Class<T> itemTypeClass, ItemStack bukkitItem) {
        if (bukkitItem.getType() == Material.AIR) return null;
        if (!isEssenceItem(bukkitItem)) return null;
        EssenceItemStack itemStack = new EssenceItemStack(bukkitItem);
        EssenceItem essenceItem = itemStack.toEssenceItem();
        if (essenceItem == null) return null;
        if (essenceItem.getClass() == itemTypeClass) {
            return (T) essenceItem;
        }
        return null;
    }

    /**
     * Returns a {@link EssenceItemStack} version of this item.
     */
    public EssenceItemStack getItemStack() {
        if (material == null || name == null || type == null) return null;

        EssenceItemStack itemStack = new EssenceItemStack(material)
                .setNBTString(ItemKey.ITEM_TYPE.getKey(), type.name())
                .setNBTString(ItemKey.ITEM_DATA.getKey(), StringUtil.GSON_RAW.toJson(itemData))
                .buildNBT();

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.applyColors(getName()));

        List<String> lore = Lists.newArrayList();

        if (customLore != null && !customLore.isEmpty())
            lore.addAll(customLore);

        lore.add("");
        lore.add("&8&o" + type.getName());
        itemMeta.setLore(lore.stream().map(StringUtil::applyColors).collect(Collectors.toList()));

        if (glowing)
            itemStack.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);

        itemMeta.setUnbreakable(true);
        itemMeta.addItemFlags(ItemFlag.values());
        itemStack.setItemMeta(itemMeta);
        itemStack.setAmount(itemData.getAmount());
        return itemStack;
    }

    /**
     * Returns whether this item can be dropped or not.
     */
    public boolean isDroppable() {
        return itemData.isDroppable();
    }

    /**
     * Sets whether this item can be dropped or not.
     */
    public void setDroppable(boolean b) {
        this.itemData.setDroppable(b);
    }

    /**
     * Returns the name of this item.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the {@link Material} of this item.
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Returns the {@link ItemType} of this item.
     */
    public ItemType getType() {
        return type;
    }

    /**
     * Returns the custom lore of this item.
     */
    public List<String> getCustomLore() {
        return customLore;
    }

    /**
     * Sets the custom lore of this item.
     */
    public void setCustomLore(List<String> customLore) {
        this.customLore = customLore;
    }

    /**
     * Returns the {@link ItemData} of this item.
     */
    public ItemData getItemData() {
        return itemData;
    }

    /**
     * Sets the {@link ItemData} of this item.
     */
    public void setItemData(ItemData itemData) {
        this.itemData = itemData;
    }

    /**
     * Returns whether this item has the glowing enchantment effect or not.
     */
    public boolean isGlowing() {
        return glowing;
    }

    /**
     * Sets whether this item should have the enchanted glow effect or not.
     */
    public EssenceItem setGlowing(boolean glowing) {
        this.glowing = glowing;
        return this;
    }
}