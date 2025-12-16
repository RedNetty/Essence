package net.rednetty.essence.items.equipment;

import net.rednetty.essence.effect.sound.SoundEffect;
import net.rednetty.essence.items.ItemData;

/**
 * Created by Giovanni on 3/28/2021
 */
public class EquipmentItemData extends ItemData {

    private EquipmentAttributeMap attributeMap;
    private EquipmentType equipmentType;
    private SoundEffect onEquipSound;

    public EquipmentAttributeMap getAttributeMap() {
        return attributeMap;
    }

    public void setAttributeMap(EquipmentAttributeMap attributeMap) {
        this.attributeMap = attributeMap;
    }

    public SoundEffect getOnEquipSound() {
        return onEquipSound;
    }

    public void setOnEquipSound(SoundEffect onEquipSound) {
        this.onEquipSound = onEquipSound;
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(EquipmentType equipmentType) {
        this.equipmentType = equipmentType;
    }
}
