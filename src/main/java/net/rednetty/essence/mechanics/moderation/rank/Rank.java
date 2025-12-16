package net.rednetty.essence.mechanics.moderation.rank;

public enum Rank {
    DEFAULT("", "Default"),
    GUIDE("&a❖", "&aGuide"),
    JUSTICAR("&9❖", "&9Justicar"),
    KEEPER("&4❖", "&4Keeper"),
    CUSTODIAN("&6❖", "&6Custodian");
    private String prefix;
    private String name;
    Rank(String prefix, String name) {
        this.prefix = prefix;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }
}
