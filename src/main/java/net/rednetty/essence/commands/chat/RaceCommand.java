package net.rednetty.essence.commands.chat;

import net.rednetty.essence.mechanics.MechanicHandler;
import net.rednetty.essence.mechanics.chat.Channel;
import net.rednetty.essence.mechanics.player.eplayer.EssencePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RaceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("This must be run by a player..");
            return false;
        }
        EssencePlayer viewer = MechanicHandler.getPlayerHandler().getEPlayer((Player) commandSender);
        viewer.setChatChannel(Channel.RACE);
        return true;
    }
}