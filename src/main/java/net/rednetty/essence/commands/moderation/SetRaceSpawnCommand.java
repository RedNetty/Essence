package net.rednetty.essence.commands.moderation;

import net.rednetty.essence.Essence;
import net.rednetty.essence.mechanics.MechanicHandler;
import net.rednetty.essence.mechanics.player.eplayer.EssencePlayer;
import net.rednetty.essence.mechanics.player.races.Race;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetRaceSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender.isOp() && commandSender instanceof Player) {
            EssencePlayer ePlayer = MechanicHandler.getPlayerHandler().getEPlayer((Player) commandSender);
            if(strings.length == 1) {
                Race race = Race.valueOf(strings[0].toUpperCase());
                MechanicHandler.getRaceHandler().setRaceSpawn(ePlayer.getPlayer().getLocation(), race);
                ePlayer.centeredMessage("&7&oYou have succesfully set " + race.toString() + "'s spawn to your location");
                return true;
            }
            ePlayer.centeredMessage("&cCouldn't find race..");
            return false;

        }
        return false;
    }
}
