package net.rednetty.essence.items.interact;

import net.rednetty.essence.effect.sound.SoundEffect;
import net.rednetty.essence.items.ItemData;


/**
 * Created by Giovanni on 1/30/2021
 */
public class InteractItemData extends ItemData {

    private InteractType interactType;
    private SoundEffect onInteractSound;

    public SoundEffect getOnInteractSound() {
        return onInteractSound;
    }

    public void setOnInteractSound(SoundEffect onInteractSound) {
        this.onInteractSound = onInteractSound;
    }

    public InteractType getInteractType() {
        return interactType;
    }

    public void setInteractType(InteractType interactType) {
        this.interactType = interactType;
    }
}
