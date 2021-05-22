package net.rednetty.essence.items.weapon;

/**
 * Created by Giovanni on 2/12/2021
 */
public enum WeaponType {

    SWORD("Sword"),
    BATTLE_AXE("Battle Axe");

    private String name;

    WeaponType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
