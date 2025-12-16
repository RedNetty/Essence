package net.rednetty.essence.mechanics.chat;

import net.rednetty.essence.Essence;
import net.rednetty.essence.mechanics.Mechanic;
import net.rednetty.essence.mechanics.MechanicHandler;
import net.rednetty.essence.mechanics.moderation.rank.Rank;
import net.rednetty.essence.mechanics.player.eplayer.EssencePlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class ChatHandler extends Mechanic implements Listener {

    @Override
    public void onLoad() {
        listener(this);
    }

    @Override
    public void onUnload() {

    }


    public List<EssencePlayer> getChatViewers(EssencePlayer essencePlayer) {
        List<EssencePlayer> viewerList = new ArrayList<>();
        switch (essencePlayer.getChatChannel()) {
            case LOCAL:
                essencePlayer.getPlayer().getNearbyEntities(25, 25, 25).forEach(entity -> {
                    if (entity instanceof Player) {
                        EssencePlayer viewer = MechanicHandler.getPlayerHandler().getEPlayer((Player) entity);
                        viewerList.add(viewer);
                    }
                });
                viewerList.add(essencePlayer);
                break;
            case RACE:
                Bukkit.getOnlinePlayers().forEach(player -> {
                    EssencePlayer viewer = MechanicHandler.getPlayerHandler().getEPlayer(player);
                    if (viewer.getRace() == essencePlayer.getRace()) viewerList.add(viewer);
                });
                break;
            case GLOBAL:
                Bukkit.getOnlinePlayers().forEach(player -> {
                    EssencePlayer viewer = MechanicHandler.getPlayerHandler().getEPlayer(player);
                    viewerList.add(viewer);
                });
                break;
        }
        return viewerList;
    }

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        EssencePlayer player = MechanicHandler.getPlayerHandler().getEPlayer(event.getPlayer());
        String rankPrefix = player.getRank().getPrefix() + " ";
        if(player.getRank() == Rank.DEFAULT) rankPrefix = "";
        event.getRecipients().clear();
        getChatViewers(player).forEach(eplayer -> event.getRecipients().add(eplayer.getPlayer()));

        event.setFormat(ChatColor.translateAlternateColorCodes('&', "&7<" +
                player.getChatChannel().getPrefix() + "&7> " + rankPrefix  + player.getPlayer().getName() + "&7: " + event.getMessage()));

        new BukkitRunnable() {
            @Override
            public void run() {
                if(event.getRecipients().size() <= 1) {
                    player.centeredMessage("&7&oYour voice echoes as no-one hears you..");
                }
            }
        }.runTaskLaterAsynchronously(Essence.getInstance(), 2L);

    }
}
