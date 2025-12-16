package net.rednetty.essence.items.interact;

import com.google.common.collect.Lists;
import net.rednetty.essence.items.EssenceItem;
import net.rednetty.essence.items.EssenceItemStack;
import net.rednetty.essence.items.ItemKey;
import net.rednetty.essence.items.ItemType;
import net.rednetty.essence.mechanics.player.eplayer.EssencePlayer;
import net.rednetty.essence.utils.string.StringUtil;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Giovanni on 1/30/2021
 */
public abstract class InteractableItem extends EssenceItem {

    private InteractType interactType;

    public InteractableItem(InteractType interactType, String name, Material material, ItemType type) {
        super(name, material, type);
        this.interactType = interactType;

        InteractItemData interactItemData = new InteractItemData();
        interactItemData.setInteractType(interactType);
        interactItemData.setName(name);
        interactItemData.setMaterial(material);
        interactItemData.setItemType(type);
        setItemData(interactItemData);
    }

    public InteractableItem(InteractItemData interactItemData) {
        super(interactItemData);
        this.interactType = interactItemData.getInteractType();
    }

    /**
     * Returns a {@link EssenceItemStack} version of this item.
     */
    @Override
    public EssenceItemStack getItemStack() {
        if (getMaterial() == null || getName() == null || getType() == null) return null;

        EssenceItemStack itemStack = new EssenceItemStack(getMaterial())
                .setNBTString(ItemKey.ITEM_TYPE.getKey(), getType().name())
                .setNBTString(ItemKey.ITEM_DATA.getKey(), StringUtil.GSON_RAW.toJson(getItemData()))
                .buildNBT();

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getName()));

        List<String> lore = Lists.newArrayList();

        if (getCustomLore() != null && !getCustomLore().isEmpty())
            lore.addAll(getCustomLore());

        lore.add(interactType.getText());
        lore.add("");
        lore.add("&8&o" + getType().getName());
        itemMeta.setLore(lore.stream().map(StringUtil::applyColors).collect(Collectors.toList()));

        if (isGlowing())
            itemStack.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);

        itemMeta.addItemFlags(ItemFlag.values());
        itemStack.setNBTString(ItemKey.ITEM_TYPE.getKey(), getType().name());
        itemStack.setNBTString(ItemKey.ITEM_DATA.getKey(), StringUtil.GSON_RAW.toJson(getItemData()));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    /**
     * Returns the {@link InteractType} of this item.
     */
    public InteractType getInteractType() {
        return interactType;
    }

    /**
     * Called when this item is used.
     */
    public abstract void onItemUse(EssencePlayer user);
}
