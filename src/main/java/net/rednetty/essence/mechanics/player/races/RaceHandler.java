package net.rednetty.essence.mechanics.player.races;

import net.rednetty.essence.Essence;
import net.rednetty.essence.mechanics.Mechanic;
import net.rednetty.essence.mechanics.MechanicHandler;
import net.rednetty.essence.mechanics.player.eplayer.EssencePlayer;
import net.rednetty.essence.mechanics.player.races.menus.RaceMenu;
import net.rednetty.essence.utils.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class RaceHandler extends Mechanic implements Listener {

    @Override
    public void onLoad() {
        listener(this);
    }

    @Override
    public void onUnload() {
    }



    public void setRace(EssencePlayer player, Race race) {
        player.setRace(race);
    }
    public ItemStack raceSelector() {
        return new ItemBuilder(new ItemStack(Material.TOTEM_OF_UNDYING)).setName("&b&lRACE SELECTOR").setLore(Arrays.asList("")).build();
    }

    @EventHandler
    public void respawn(PlayerRespawnEvent event) {
        EssencePlayer player = MechanicHandler.getPlayerHandler().getEPlayer(event.getPlayer());
        event.setRespawnLocation(getRaceSpawn(player.getRace()));
    }


    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if(event.getPlayer().getInventory().getItemInMainHand().isSimilar(raceSelector())) {
            EssencePlayer ePlayer = MechanicHandler.getPlayerHandler().getEPlayer(event.getPlayer());
            if(!ePlayer.isRaceSet())
            ePlayer.openMenu(new RaceMenu("&b&lRACE SELECTION"));
        }
    }

    /**
     *The configs used to set Race Spawns and Stuff
     *
     */
    public Location getRaceSpawn(Race race) {
        checkConfig();
        World world = Bukkit.getWorld(Essence.getInstance().getConfig().getString("race.spawns." + race.toString() + ".world"));
        double x = Essence.getInstance().getConfig().getDouble("race.spawns." + race.toString() + ".x");
        double y = Essence.getInstance().getConfig().getDouble("race.spawns." + race.toString() + ".y");
        double z = Essence.getInstance().getConfig().getDouble("race.spawns." + race.toString() + ".z");
        return new Location(world, x,y,z);
    }

    public void setRaceSpawn(Location location, Race race) {
        World world = location.getWorld();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        Essence.getInstance().getConfig().set("race.spawns." + race.toString() + ".world", world.getName());
        Essence.getInstance().getConfig().set("race.spawns." + race.toString() + ".x", x);
        Essence.getInstance().getConfig().set("race.spawns." + race.toString() + ".y", y);
        Essence.getInstance().getConfig().set("race.spawns." + race.toString() + ".z", z);
        Essence.getInstance().saveConfig();
    }

    public void checkConfig() {
        if(!Essence.getInstance().getConfig().contains("race.spawns")) {
            for(Race race : Race.values()) {
                World world = Bukkit.getWorlds().get(0);
                Location location = world.getSpawnLocation();
                double x = location.getX();
                double y = location.getY();
                double z = location.getZ();
                Essence.getInstance().getConfig().set("race.spawns." + race.toString() + ".world", world.getName());
                Essence.getInstance().getConfig().set("race.spawns." + race.toString() + ".x", x);
                Essence.getInstance().getConfig().set("race.spawns." + race.toString() + ".y", y);
                Essence.getInstance().getConfig().set("race.spawns." + race.toString() + ".z", z);

            }
            Essence.getInstance().saveConfig();
        }
    }
    /**
     *
     *
     */

}
