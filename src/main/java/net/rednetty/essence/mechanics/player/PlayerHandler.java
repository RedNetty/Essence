package net.rednetty.essence.mechanics.player;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.rednetty.essence.Essence;
import net.rednetty.essence.items.weapon.WeaponAttributeMap;
import net.rednetty.essence.items.weapon.WeaponItem;
import net.rednetty.essence.items.weapon.WeaponItemData;
import net.rednetty.essence.items.weapon.WeaponType;
import net.rednetty.essence.mechanics.Mechanic;
import net.rednetty.essence.mechanics.MechanicHandler;
import net.rednetty.essence.mechanics.gear.WeaponBuilder;
import net.rednetty.essence.mechanics.gear.enums.WeaponStats;
import net.rednetty.essence.mechanics.player.eplayer.EssencePlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerHandler extends Mechanic implements Listener {

    private static ArrayList<EssencePlayer> essencePlayers = new ArrayList<>();
    private static ArrayList<String> serializedPlayers = new ArrayList<>();

    public static ArrayList<EssencePlayer> getEssencePlayers() {
        return essencePlayers;
    }

    public void onLoad() {
        listener(this);
        loadData();
    }

    @Override
    public void onUnload() {
        serialize();
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        EssencePlayer ePlayer = getEPlayer(event.getPlayer());
        if (event.getFrom().getBlock() != event.getTo().getBlock()) {
            if (!ePlayer.isRaceSet()) {
                event.setCancelled(true);
                ePlayer.centeredMessage("");
                ePlayer.centeredMessage("&c>> &7Please select your race before playing!");
            }
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        EssencePlayer ePlayer = getEPlayer(event.getPlayer());
        if (!ePlayer.isRaceSet()) {
            event.getPlayer().getInventory().addItem(MechanicHandler.getRaceHandler().raceSelector());
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {


        EssencePlayer ePlayer = getEPlayer(event.getPlayer());
        ePlayer.setPlayer(event.getPlayer());
        System.out.println(ePlayer);
        ePlayer.centeredMessage("&d&nEssence MMORPG v1.0");
        ePlayer.centeredMessage("");
        ePlayer.centeredMessage("&7&oJoin our community by getting involved in our");
        ePlayer.centeredMessage("&7&owebsite at EssenceRPG.net");
        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&7[&a+&7] " + event.getPlayer().getName()));
        WeaponItemData weaponItemData = new WeaponItemData();
        weaponItemData.setWeaponType(WeaponType.SWORD);
        weaponItemData.setName("&eLong Dong Battle-Sword");
        weaponItemData.setAmount(1);
        weaponItemData.setDroppable(true);
        weaponItemData.setMaterial(Material.GOLDEN_SWORD);
        weaponItemData.setAttributeMap(new WeaponAttributeMap());
        weaponItemData.getAttributeMap().setDamage(new int[]{120, 230});
        ePlayer.getPlayer().getInventory().addItem(new WeaponItem(weaponItemData).getItemStack());
        if (!ePlayer.isRaceSet()) {
            ePlayer.getPlayer().getInventory().addItem(MechanicHandler.getRaceHandler().raceSelector().clone());
            ePlayer.centeredMessage("");
            ePlayer.centeredMessage("&c>> &7Please select your race before playing!");
        }
    }

    /**
     * Handles Menus
     *
     * @param
     */


    @EventHandler
    public void onInteract(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        EssencePlayer gamePlayer = getEPlayer(player);
        if (!gamePlayer.viewingMenu()) return;
        gamePlayer.getOpenMenu().handleClick(event.getRawSlot(), gamePlayer, event);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        EssencePlayer gamePlayer = getEPlayer(player);
        if (gamePlayer == null) return;
        if (!gamePlayer.viewingMenu()) return;
        gamePlayer.closeMenu(false);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', "&7[&c-&7] " + event.getPlayer().getName()));
    }


    public EssencePlayer getEPlayer(Player player) {
        for (EssencePlayer ePlayer : essencePlayers) {
            if (ePlayer.getUuid().equalsIgnoreCase(player.getUniqueId().toString()) || ePlayer.getPlayer() == player) {
                System.out.println(ePlayer);
                return ePlayer;
            }
        }
        EssencePlayer essencePlayer = new EssencePlayer(player);
        essencePlayers.add(essencePlayer);
        return essencePlayer;

    }

    public static void checkFile() {
        File file = new File(Essence.getInstance().getDataFolder(), "players.json");
        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("file created.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serialize() {
        Gson gson = new Gson();
        essencePlayers.forEach(b -> serializedPlayers.add(gson.toJson(b)));
        String s = gson.toJson(serializedPlayers);
        checkFile();
        File file = new File(Essence.getInstance().getDataFolder(), "players.json");
        FileWriter writer;
        try {
            writer = new FileWriter(file);
            writer.write(s);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadData() {
        Gson gson = new Gson();
        ArrayList<EssencePlayer> essencePlayers = new ArrayList<>();
        try {
            checkFile();
            File file = new File(Essence.getInstance().getDataFolder(), "players.json");
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            ArrayList<String> strings = gson.fromJson(bufferedReader, new TypeToken<List<String>>() {
            }.getType());
            if (strings != null) {
                strings.forEach(s -> {
                    EssencePlayer player = gson.fromJson(s, EssencePlayer.class);
                    essencePlayers.add(player);
                });
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.essencePlayers = essencePlayers;
    }

}