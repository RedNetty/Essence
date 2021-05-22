package net.rednetty.essence.items.skull;

import com.google.common.collect.Lists;
import net.rednetty.essence.items.EssenceItem;
import net.rednetty.essence.items.EssenceItemStack;
import net.rednetty.essence.items.ItemKey;
import net.rednetty.essence.utils.items.PlayerSkulls;
import net.rednetty.essence.utils.string.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Giovanni on 2/4/2021
 */
public class SkullItem extends EssenceItem {

    private String skullURL;

    public SkullItem(SkullItemData skullItemData) {
        super(skullItemData);
        this.skullURL = skullItemData.getSkullURL();
    }

    /**
     * Returns a {@link EssenceItemStack} version of this item.
     */
    public EssenceItemStack getItemStack() {
        if (getMaterial() == null || getName() == null || getType() == null || this.skullURL == null || this.skullURL.length() < 1)
            return null;

        EssenceItemStack itemStack = new EssenceItemStack(PlayerSkulls.createSkullFromURL(this.skullURL))
                .setNBTString(ItemKey.ITEM_TYPE.getKey(), getType().name())
                .setNBTString(ItemKey.ITEM_DATA.getKey(), StringUtil.GSON_RAW.toJson(getItemData()))
                .buildNBT();

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getName()));

        List<String> lore = Lists.newArrayList();

        if (getCustomLore() != null && !getCustomLore().isEmpty())
            lore.addAll(getCustomLore());

        lore.add("");
        lore.add("&8&o" + getType().getName());
        itemMeta.setLore(lore.stream().map(StringUtil::applyColors).collect(Collectors.toList()));

        if (isGlowing())
            itemStack.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);

        itemMeta.addItemFlags(ItemFlag.values());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
