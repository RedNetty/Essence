package net.rednetty.essence.mechanics.chat;

public enum Channel {
    LOCAL("&fLocal", "&fL"),
    RACE("&bRace", "&bR"),
    GLOBAL("&cGlobal", "&cG");

    private String prefix;
    private String name;

    Channel(String name, String prefix) {
        this.name = name;
        this.prefix = prefix;

    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }
}
