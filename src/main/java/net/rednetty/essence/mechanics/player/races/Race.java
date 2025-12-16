package net.rednetty.essence.mechanics.player.races;

public enum Race {
    ORC("http://textures.minecraft.net/texture/3660e8fc97cba56616e9e14454f68bacd32dc451fb9b22f3fbf777d774edd900"),
    ELF("http://textures.minecraft.net/texture/a3ff189aa87a7381cb295cd9e2c41493db44ba1589d60ee987844bd8672ecf7"),
    DWARF("http://textures.minecraft.net/texture/99735b9eaddc24f612e8f74b76e744f183da6c8e36739ad4490c667aca42c"),
    HUMAN("http://textures.minecraft.net/texture/c094f161044f7b2623d743e1dd69797eaaead9c2847a7778f69e819e0ba6aa28");

    public String skin;

    Race(String skin) {
        this.skin = skin;
    }

    public String getSkin() {
        return skin;
    }
}
