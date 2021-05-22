package net.rednetty.essence.items;

import net.minecraft.server.v1_16_R3.NBTTagCompound;
import net.rednetty.essence.utils.string.StringUtil;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * Created by Giovanni on 1/14/2021
 */
public class EssenceItemStack extends ItemStack {

    private final NBTTagCompound tagCompound;

    public EssenceItemStack(Material material) {
        super(material);
        this.tagCompound = new NBTTagCompound();
    }

    public EssenceItemStack(@Nonnull ItemStack itemStack) {
        super(itemStack);
        net.minecraft.server.v1_16_R3.ItemStack nms = CraftItemStack.asNMSCopy(this);
        if (nms.hasTag())
            this.tagCompound = nms.getTag();
        else this.tagCompound = new NBTTagCompound();
    }

    /**
     * Returns the {@link EssenceItem} version of this EssenceItemStack.
     */
    public EssenceItem toEssenceItem() {
        if (!hasNBTKey(ItemKey.ITEM_TYPE.getKey())) return null;
        ItemType itemType = ItemType.valueOf(getNBTString(ItemKey.ITEM_TYPE.getKey()));
        ItemData itemData = StringUtil.GSON_RAW.fromJson(getNBTString(ItemKey.ITEM_DATA.getKey()), itemType.getDataClass());
        return itemType.createNew(itemData);
    }

    /**
     * Sets an int in this item's {@link NBTTagCompound}.
     */
    public EssenceItemStack setNBTInt(String key, int value) {
        tagCompound.setInt(key, value);
        return this;
    }

    /**
     * Sets a string in this item's {@link NBTTagCompound}.
     */
    public EssenceItemStack setNBTString(String key, String value) {
        tagCompound.setString(key, value);
        return this;
    }

    /**
     * Returns the a built version of this item with NBT data applied.
     */
    public EssenceItemStack buildNBT() {
        net.minecraft.server.v1_16_R3.ItemStack nms = CraftItemStack.asNMSCopy(this);
        nms.setTag(tagCompound);
        return new EssenceItemStack(CraftItemStack.asBukkitCopy(nms));
    }

    /**
     * Gets an int from this item's {@link NBTTagCompound}.
     */
    public int getNBTInt(String key) {
        return tagCompound.getInt(key);
    }

    /**
     * Gets a string from this item's {@link NBTTagCompound}.
     */
    public String getNBTString(String key) {
        return tagCompound.getString(key);
    }

    /**
     * Returns whether this item's {@link NBTTagCompound} has a specified key or not.
     */
    public boolean hasNBTKey(String key) {
        return tagCompound.hasKey(key);
    }
}