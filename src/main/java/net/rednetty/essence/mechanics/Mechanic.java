package net.rednetty.essence.mechanics;

import net.rednetty.essence.Essence;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class Mechanic implements Listener {

    public void onUnload() {}

    public void onLoad() {}

    public void listener(Listener listener) {
        Bukkit.getServer().getPluginManager().registerEvents(listener, Essence.getInstance());
    }
}
