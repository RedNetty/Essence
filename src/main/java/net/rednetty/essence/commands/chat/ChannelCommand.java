package net.rednetty.essence.commands.chat;

import net.rednetty.essence.mechanics.MechanicHandler;
import net.rednetty.essence.mechanics.chat.menu.ChannelMenu;
import net.rednetty.essence.mechanics.player.eplayer.EssencePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChannelCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            EssencePlayer ePlayer = MechanicHandler.getPlayerHandler().getEPlayer((Player) commandSender);
            ePlayer.openMenu(new ChannelMenu(ePlayer));
        }
        return false;
    }
}
