package net.rednetty.essence.mechanics;

import net.rednetty.essence.mechanics.chat.ChatHandler;
import net.rednetty.essence.mechanics.moderation.RankHandler;
import net.rednetty.essence.mechanics.player.PlayerHandler;
import net.rednetty.essence.mechanics.player.races.RaceHandler;

import java.util.stream.Stream;

public class MechanicHandler {


    private static PlayerHandler playerHandler;
    private static RaceHandler raceHandler;
    private static ChatHandler chatHandler;
    private static RankHandler rankHandler;

    public void loadMechanics() {
        Stream.of(
                playerHandler = new PlayerHandler(),
                raceHandler = new RaceHandler(),
                chatHandler = new ChatHandler()
        ).forEach(manager -> manager.onLoad());

    }

    public void unLoadMechanics() {
        Stream.of(
                playerHandler,
                raceHandler,
                chatHandler
        ).forEach(manager -> manager.onUnload());
    }

    public static PlayerHandler getPlayerHandler() {
        return playerHandler;
    }

    public static RaceHandler getRaceHandler() {
        return raceHandler;
    }

}
