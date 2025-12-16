package net.rednetty.essence;

import net.rednetty.essence.commands.Commands;
import net.rednetty.essence.mechanics.MechanicHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class Essence extends JavaPlugin {

    private static Essence essence;
    private static MechanicHandler mechanicHandler;
    private static boolean gameActive = false;

    /*
    Instance of The Main Class
     */
    public static Essence getInstance() {
        return essence;
    }

    public static boolean isGameActive() { return gameActive;}

    public static MechanicHandler getMechanicHandler() {
        return mechanicHandler;
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        essence = this;
        mechanicHandler = new MechanicHandler();

        mechanicHandler.loadMechanics();

        Commands.registerCommands();
    }

    @Override
    public void onDisable() {
        saveConfig();
        mechanicHandler.unLoadMechanics();
    }
}
