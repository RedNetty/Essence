package net.rednetty.essence.commands.moderation;

import net.rednetty.essence.Essence;
import net.rednetty.essence.mechanics.MechanicHandler;
import net.rednetty.essence.mechanics.moderation.rank.Rank;
import net.rednetty.essence.mechanics.player.eplayer.EssencePlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetRankCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender.hasPermission("essence.setrank") || commandSender.isOp()) {
            if(strings.length != 2) return false;
            String string = strings[1];
            Player player = Bukkit.getPlayer(strings[0]);
            if(player == null) {
                commandSender.sendMessage("Couldn't find player..");
                return false;
            }
            EssencePlayer ePlayer = MechanicHandler.getPlayerHandler().getEPlayer(player);
            Rank rank = null;
            if(Rank.valueOf(string.toUpperCase()) != null) rank = Rank.valueOf(string.toUpperCase());
            if(rank == null) {
                commandSender.sendMessage("Couldn't find Rank..");
                return false;
            }
            ePlayer.setRank(rank);
            commandSender.sendMessage("Succesfully set rank.");
            return true;
        }
        return false;
    }
}
