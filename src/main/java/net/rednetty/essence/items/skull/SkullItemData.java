package net.rednetty.essence.items.skull;

import net.rednetty.essence.items.ItemData;
import net.rednetty.essence.items.ItemType;
import org.bukkit.Material;

/**
 * Created by Giovanni on 2/4/2021
 */
public class SkullItemData extends ItemData {

    private String skullURL;

    public SkullItemData() {
        setName("SI_Skull");
        setMaterial(Material.PLAYER_HEAD);
        setItemType(ItemType.SKULL);
    }

    public String getSkullURL() {
        return skullURL;
    }

    public void setSkullURL(String skullURL) {
        this.skullURL = skullURL;
    }
}
