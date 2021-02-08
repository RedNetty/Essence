package net.rednetty.essence.commands;

import net.rednetty.essence.Essence;
import net.rednetty.essence.commands.chat.ChannelCommand;
import net.rednetty.essence.commands.chat.GlobalCommand;
import net.rednetty.essence.commands.chat.LocalCommand;
import net.rednetty.essence.commands.chat.RaceCommand;
import net.rednetty.essence.commands.moderation.SetRaceSpawnCommand;
import net.rednetty.essence.commands.moderation.SetRankCommand;
import org.bukkit.Bukkit;

public class Commands {

    public static void registerCommands() {
        Essence.getInstance().getCommand("global").setExecutor(new GlobalCommand());
        Essence.getInstance().getCommand("race").setExecutor(new RaceCommand());
        Essence.getInstance().getCommand("local").setExecutor(new LocalCommand());
        Essence.getInstance().getCommand("setrank").setExecutor(new SetRankCommand());
        Essence.getInstance().getCommand("channel").setExecutor(new ChannelCommand());
        Essence.getInstance().getCommand("setRaceSpawn").setExecutor(new SetRaceSpawnCommand());
    }
}
