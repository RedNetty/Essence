package net.rednetty.essence.mechanics;

import net.rednetty.essence.mechanics.chat.ChatHandler;
import net.rednetty.essence.mechanics.gear.GearHandler;
import net.rednetty.essence.mechanics.moderation.RankHandler;
import net.rednetty.essence.mechanics.player.PlayerHandler;
import net.rednetty.essence.mechanics.player.hud.HUDHandler;
import net.rednetty.essence.mechanics.player.races.RaceHandler;

import java.util.stream.Stream;

public class MechanicHandler {


    private static PlayerHandler playerHandler;
    private static RaceHandler raceHandler;
    private static ChatHandler chatHandler;
    private static RankHandler rankHandler;
    private static HUDHandler hudHandler;
    private static GearHandler gearHandler;

    public void loadMechanics() {
        Stream.of(
                gearHandler = new GearHandler(),
                playerHandler = new PlayerHandler(),
                raceHandler = new RaceHandler(),
                chatHandler = new ChatHandler(),
                hudHandler = new HUDHandler()
        ).forEach(manager -> manager.onLoad());

    }

    public void unLoadMechanics() {
        Stream.of(
                gearHandler,
                playerHandler,
                raceHandler,
                chatHandler,
                hudHandler
        ).forEach(manager -> manager.onUnload());
    }

    public static PlayerHandler getPlayerHandler() {
        return playerHandler;
    }

    public static RaceHandler getRaceHandler() {
        return raceHandler;
    }

    public static HUDHandler getHudHandler() {
        return hudHandler;
    }
}
