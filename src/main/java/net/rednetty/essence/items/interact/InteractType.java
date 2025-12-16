package net.rednetty.essence.items.interact;

/**
 * Created by Giovanni on 1/30/2021
 */
public enum InteractType {

    LEFT_CLICK("&8[Left-click to use]"),
    RIGHT_CLICK("&8[Right-click to use]");

    private final String text;

    InteractType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
