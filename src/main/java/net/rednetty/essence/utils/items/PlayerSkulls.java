package net.rednetty.essence.utils.items;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.org.apache.commons.codec.binary.Base64;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

/**
 * Created by Giovanni on 9/30/2020
 */
public enum PlayerSkulls {

    /**
     * Takes a base 64 string of the skull, can be found here; http://heads.freshcoal.com/ or via the players texture page on Minecraft.
     */
    CHERRY("d525707696bcd15a173056fa39296e80ff41168bb0add552f4523e2558a3119"),
    APPLE("cbb311f3ba1c07c3d1147cd210d81fe11fd8ae9e3db212a0fa748946c3633"),
    BREAD("f3487d457f9062d787a3e6ce1c4664bf7402ec67dd111256f19b38ce4f670"),
    PUG("456de2b44c66a6b039b1a2e46561af76e829faa27626aaf8b948a5065c81262"),
    BEAGLE("aa266a9acd19cec3cb7e956e335774944f154633ce89993c566f766fcb0cd3d"),
    CORGI("725ed61167dd023babd33a391e9e26ecaafb29875e9ad605dc6d9fae99c30"),
    CHEWBACCA("91703822898b51ac1266a4d3837bc69294defe1c2bb17b03e2087fd5acf"),
    SLOTH("8e78fde0624d888a6e226be91706f9d18f1a4726c1292a356587de3874e8ad9"),
    TROLL("bcae421956c9469ff2558843392a13e5d7beb9ca4ac6cac7eeb18f4dd4b383"),
    TRICERATOPS("6bc473afa6be7dc18a79b495fa10e8ac48b14a3efe92c8f2f11e8b1016d0"),
    BB8("a867acfd786b666de87578fc1e632e6bed4d1bd7699c270ba9e13c55211"),
    KNIGHT("4fd85a968334ffa874d63e8068c4d1835e29b8da17cf58d8de0a71338ecbf60"),
    ARROW_RIGHT("1b6f1a25b6bc199946472aedb370522584ff6f4e83221e5946bd2e41b5ca13b"),
    GOLD_BEVELED_BLOCK("b13fff0d283a041befc337165b6839a5a9b32d3429eab3895bbd9292def11"),
    GLOBE("ade457933e0137c973964789cf15878c5bdb671ccbb850608bbec19c61088"),
    CORRUPTED_GLOBE("d83571ff589f1a59bb02b80800fc736116e27c3dcf9efebede8cf1fdde"),
    GOLD_POT("aa976f6dfcf188995a327e55ce34c60e6dcd1ff63e68dd1fc3ad75d2d1bf"),


    KNIGHT_A("1b4bf7a7a45d8ddeb470c961737ce9621148fce2cfe81459c311775cdab9"),
    KNIGHT_B("b5ec1aebc677e17a9724c7d6ee4040afde7357b0d03c659d159ce75010ee073"),
    KNIGHT_C("7d46793bdbce5cad5f37b124eaf1e3689bba18d59a8068567c74f4ff1a18"),

    MAILBOX("b4bd9dd128c94c10c945eadaa342fc6d9765f37b3df2e38f7b056dc7c927ed"),
    MAILBOX_BLUE("eb2815b99c13bfc55b4c5c2959d157a6233ab06186459233bc1e4d4f78792c69");


    private final String b64String;

    PlayerSkulls(String base64String) {
        this.b64String = base64String;
    }

    /**
     * Creates a textured skull via a mojang texture URL.
     */
    public static ItemStack createSkullFromURL(String par1) {
        String url = "http://textures.minecraft.net/texture/" + par1;
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        assert profileField != null;
        profileField.setAccessible(true);
        try {
            profileField.set(skullMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        skull.setItemMeta(skullMeta);
        return skull;
    }

    public ItemStack getSkullByURL() {
        String url = getURL();
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        assert profileField != null;
        profileField.setAccessible(true);
        try {
            profileField.set(skullMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        skull.setItemMeta(skullMeta);
        return skull;
    }

    public String getURL() {
        return "http://textures.minecraft.net/texture/" + this.b64String;
    }
}
