package net.rednetty.essence.mechanics.player.eplayer;

import net.rednetty.essence.mechanics.MechanicHandler;
import net.rednetty.essence.mechanics.chat.Channel;
import net.rednetty.essence.mechanics.inventory.Menu;
import net.rednetty.essence.mechanics.moderation.rank.Rank;
import net.rednetty.essence.mechanics.player.races.Race;
import net.rednetty.essence.utils.string.DefaultFontInfo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.io.Serializable;

public class EssencePlayer implements Serializable {
    private transient Player player;
    private transient Menu openMenu;
    private String uuid;
    private Channel chatChannel = Channel.LOCAL;
    private Race race;
    private Rank rank = Rank.DEFAULT;

    public EssencePlayer(Player newPlayer) {
        uuid = newPlayer.getUniqueId().toString();
        player = newPlayer;
    }

    public String getUuid() {
        return uuid;
    }

    public Player getPlayer() {
        if(player == null) {
            return Bukkit.getPlayer(uuid);
        }
        return player;
    }
    public boolean isRaceSet() {
        return race != null;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Channel getChatChannel() {
        return chatChannel;
    }

    public void setChatChannel(Channel chatChannel) {
        this.chatChannel = chatChannel;
        centeredMessage("&e>> &7&oChat Channel switched to " + chatChannel.getName());
        player.playSound(player.getLocation(), Sound.BLOCK_BAMBOO_PLACE, 1F, 1F);
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
        centeredMessage("&3&oRace set as " + race.toString());
        player.teleport(MechanicHandler.getRaceHandler().getRaceSpawn(race));
        player.getInventory().setItemInMainHand(null);
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
        centeredMessage("&7&oYour rank was changed to " + rank.getName());
    }

    public void centeredMessage(String message) {
        if (message == null || message.equals("")) player.sendMessage("");
        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for (char c : message.toCharArray()) {
            if (c == '§') {
                previousCode = true;
            } else if (previousCode) {
                previousCode = false;
                isBold = c == 'l' || c == 'L';
            } else {
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = 154 - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while (compensated < toCompensate) {
            sb.append(" ");
            compensated += spaceLength;
        }
        player.sendMessage(sb.toString() + message);
    }

    /**
     * Open a menu for the player
     *
     * @param openMenu The menu to open
     */
    public void openMenu(Menu openMenu) {

        if (viewingMenu())
            closeMenu(true);

        this.openMenu = openMenu;
        this.openMenu.getViewers().add(this);
        this.openMenu.onOpen(this);
        getPlayer().openInventory(openMenu.getInventory());
    }

    public boolean viewingMenu() {
        return openMenu != null;
    }
    /**
     * Close the currently opened menu of the player
     */
    public void closeMenu(boolean closeBukkitInv) {
        if (!viewingMenu()) return;
        openMenu.getViewers().remove(this);
        openMenu.onClose(this);
        if (closeBukkitInv)
            getPlayer().closeInventory();
        openMenu = null;
    }

    /**
     * Get the currently opened menu of the player
     *
     * @return Menu
     */
    public Menu getOpenMenu() {
        return openMenu;
    }
}
