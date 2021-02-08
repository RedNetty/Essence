package net.rednetty.essence.mechanics.moderation;

import net.rednetty.essence.mechanics.Mechanic;
import org.bukkit.event.Listener;

public class RankHandler extends Mechanic implements Listener {

    @Override
    public void onLoad() {
        listener(this);
    }

    @Override
    public void onUnload() {

    }


}
