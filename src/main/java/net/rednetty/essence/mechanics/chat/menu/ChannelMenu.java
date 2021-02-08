package net.rednetty.essence.mechanics.chat.menu;

import net.rednetty.essence.mechanics.chat.Channel;
import net.rednetty.essence.mechanics.inventory.Button;
import net.rednetty.essence.mechanics.inventory.Menu;
import net.rednetty.essence.mechanics.player.eplayer.EssencePlayer;
import net.rednetty.essence.utils.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ChannelMenu extends Menu {
    public ChannelMenu(EssencePlayer ePlayer) {
        super("&eChannel Selection", 9);

       newButton( new Button(this, 3, getChannelItem(ePlayer, Channel.LOCAL), true) {
            @Override
            protected void onClick(int slot, EssencePlayer player) {
                ePlayer.setChatChannel(Channel.LOCAL);
                ePlayer.closeMenu(true);
            }
        });
        newButton(new Button(this, 4, getChannelItem(ePlayer, Channel.GLOBAL), true) {
            @Override
            protected void onClick(int slot, EssencePlayer player) {
                ePlayer.setChatChannel(Channel.GLOBAL);
                ePlayer.closeMenu(true);
            }
        });
        newButton(new Button(this, 5, getChannelItem(ePlayer, Channel.RACE), true) {
            @Override
            protected void onClick(int slot, EssencePlayer player) {
                ePlayer.setChatChannel(Channel.RACE);
                ePlayer.closeMenu(true);
            }
        });
    }

    static ItemStack getChannelItem(EssencePlayer essencePlayer, Channel channel) {
        ItemStack toReturn;
        Channel currentChannel = essencePlayer.getChatChannel();
        String string = "&a&lCURRENT CHANNEL - ";
        if(channel != currentChannel) {
            string = "";
        }
        switch (channel) {
            default:
                toReturn = new ItemBuilder(Material.RED_WOOL).setName(string + "&cGlobal Chat")
                        .setLore(Arrays.asList(
                                "&eDescription: &7&oChange chat channels to global",
                                "&7&oto talk to everyone playing Essence."
                        ))
                        .build();
                break;
            case RACE:
                toReturn = new ItemBuilder(Material.CYAN_WOOL).setName(string + "&bRace Chat")
                        .setLore(Arrays.asList(
                                "&eDescription: &7&oChange chat channels to race",
                                "&7&oto talk to everyone playing Essence in the same race."
                        )).build();
                break;
            case LOCAL:
                toReturn = new ItemBuilder(Material.WHITE_WOOL).setName(string + "&fLocal Chat")
                        .setLore(Arrays.asList(
                                "&eDescription: &7&oChange chat channels to local",
                                "&7&oto talk in your local area."
                        )).build();

                break;
        }
        return toReturn;
    }

    @Override
    public void onClose(EssencePlayer player) {

    }

    @Override
    public void onOpen(EssencePlayer player) {

    }
}
