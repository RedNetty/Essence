package net.rednetty.essence.items.weapon;

/**
 * Created by Giovanni on 2/12/2021
 */
public class WeaponAttributeMap {

    // An int range, e.g 10 - 20
    private int[] damage;

    // Takes 1 - 10
    private double contactForce;

    public int[] getDamage() {
        return damage;
    }

    public void setDamage(int[] damage) {
        this.damage = damage;
    }

    public double getContactForce() {
        return (double) this.contactForce / 30 + 0.1;
    }

    public void setContactForce(double contactForce) {
        this.contactForce = Math.min(contactForce, 10);
    }
}
