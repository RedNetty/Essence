package net.rednetty.essence.mechanics.player.hud;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.rednetty.essence.mechanics.Mechanic;
import net.rednetty.essence.mechanics.MechanicHandler;
import net.rednetty.essence.mechanics.player.PlayerHandler;
import net.rednetty.essence.mechanics.player.eplayer.EssencePlayer;
import net.rednetty.essence.utils.string.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;


public class HUDHandler extends Mechanic {

    @Override
    public void onLoad() {
        listener(this);
    }

    @Override
    public void onUnload() {
        super.onUnload();
    }

    private boolean getLookingAt(Player player, LivingEntity livingEntity)
    {
        return player.getTargetEntity(25) == livingEntity;
    }


    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        EssencePlayer ePlayer = MechanicHandler.getPlayerHandler().getEPlayer(player);
        Entity entity = player.getTargetEntity(25);
        if(entity == null) return;

            if(!(entity instanceof LivingEntity)) return;
            LivingEntity livingEntity = (LivingEntity) entity;
            if(!getLookingAt(player, livingEntity)) return;

            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.
                    fromLegacyText(ChatColor.translateAlternateColorCodes('&', getHealthString(livingEntity) + " &b✦ &f" + livingEntity.getName())));
    }


    public String getHealthString(LivingEntity livingEntity){
        int maxHealth = (int)livingEntity.getMaxHealth();
        int currentHealth = (int)livingEntity.getHealth();

        int yellowWarning = maxHealth / 2;
        int redWarning = (int)(maxHealth * 0.25);

        if(currentHealth > yellowWarning) {
            return "&a" + currentHealth + " &b/ " +
                    "&a" + maxHealth;
        }
        if(currentHealth <= yellowWarning && currentHealth > redWarning) {
            return "&e" + currentHealth + " &b/ " +
                    "&a" + maxHealth;

        }else{
            return "&c" + currentHealth + " &b/ " +
                    "&a" + maxHealth;
        }

    }
}
