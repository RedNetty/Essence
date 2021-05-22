package net.rednetty.essence.items.weapon;

import net.rednetty.essence.effect.sound.SoundEffect;
import net.rednetty.essence.items.ItemData;

/**
 * Created by Giovanni on 2/12/2021
 */
public class WeaponItemData extends ItemData {

    private WeaponType weaponType;
    private WeaponAttributeMap attributeMap;
    private SoundEffect onEquipSound;
    private SoundEffect onSwingSound;

    public SoundEffect getOnSwingSound() {
        return onSwingSound;
    }

    public void setOnSwingSound(SoundEffect onSwingSound) {
        this.onSwingSound = onSwingSound;
    }

    public SoundEffect getOnEquipSound() {
        return onEquipSound;
    }

    public void setOnEquipSound(SoundEffect onEquipSound) {
        this.onEquipSound = onEquipSound;
    }

    public WeaponAttributeMap getAttributeMap() {
        return attributeMap;
    }

    public void setAttributeMap(WeaponAttributeMap attributeMap) {
        this.attributeMap = attributeMap;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }
}
