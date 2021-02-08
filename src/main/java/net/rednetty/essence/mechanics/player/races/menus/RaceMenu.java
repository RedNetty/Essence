package net.rednetty.essence.mechanics.player.races.menus;

import net.rednetty.essence.mechanics.inventory.Button;
import net.rednetty.essence.mechanics.inventory.Menu;
import net.rednetty.essence.mechanics.player.eplayer.EssencePlayer;
import net.rednetty.essence.mechanics.player.races.Race;
import net.rednetty.essence.utils.items.ItemBuilder;
import net.rednetty.essence.utils.items.skulls.Skull;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class RaceMenu extends Menu {

    public RaceMenu(String name) {
        super(InventoryType.HOPPER, "&b&lRACE SELECTION");
        newButton(new Button(this, this.emptySlot(), getHuman(), true) {
            @Override
            protected void onClick(int slot, EssencePlayer player) {
                player.setRace(Race.HUMAN);
                player.closeMenu(true);
            }
        });

        newButton(new Button(this, this.emptySlot(), getOrc(), true) {
            @Override
            protected void onClick(int slot, EssencePlayer player) {
                player.setRace(Race.ORC);
                player.closeMenu(true);
            }
        });

        newButton(new Button(this, this.emptySlot(), getElf(), true) {
            @Override
            protected void onClick(int slot, EssencePlayer player) {
                player.setRace(Race.ELF);
                player.closeMenu(true);
            }
        });

        newButton(new Button(this, this.emptySlot(), getDwarf(), true) {
            @Override
            protected void onClick(int slot, EssencePlayer player) {
                player.setRace(Race.DWARF);
                player.closeMenu(true);

            }
        });
    }

    private ItemStack getHuman() {
        return new ItemBuilder(Skull.getCustomSkull(Race.HUMAN.getSkin())).setName("&e&nHuman").build();
    }

    private ItemStack getOrc() {
        return new ItemBuilder(Skull.getCustomSkull(Race.ORC.getSkin())).setName("&a&nOrc").build();
    }
    private ItemStack getElf() {
        return new ItemBuilder(Skull.getCustomSkull(Race.ELF.getSkin())).setName("&b&nElf").build();
    }
    private ItemStack getDwarf() {
        return new ItemBuilder(Skull.getCustomSkull(Race.DWARF.getSkin())).setName("&c&nDwarf").build();
    }


    @Override
    public void onOpen(EssencePlayer player) {

    }

    @Override
    public void onClose(EssencePlayer player) {

    }
}
