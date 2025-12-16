package net.rednetty.essence.utils.string;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.rednetty.essence.utils.string.DefaultFontInfo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final Gson GSON_RAW = new Gson();

    public static final String[] FILTERED_WORDS = new String[]{
            "gay", "g4y", "homo", "h0mo", "h0m0", "hom0",
            "fag", "f4g", "faggot", "f4ggot", "fagg0t", "f4gg0t",
            "fuck", "f4ck", "cancer", "c4ncer", "canc3r", "c4nc3r",
            "aids", "a1ds", "penis", "p3nis", "p3n1s", "pen1s", "cock", "c0ck",
            "bitch", "b1tch", "nigger", "n1gger", "n1gg3r", "nigg3r", "nigga",
            "n1gga", "nigg4", "n1gg4", "cunt"};


    public static final Pattern SPEC_CHAR_PATTERN = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
    public static final Pattern HEX_PATTERN = Pattern.compile("#([A-Fa-f0-9]{6})");
    public static final char COLOR_CHAR = '\u00A7';


    private final static int CENTER_PX = 154;

    public static String applyColors(String message) {
        return hexify(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static String hexify(String message) {
        Matcher matcher = HEX_PATTERN.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
        while (matcher.find()) {
            String group = matcher.group(1);
            matcher.appendReplacement(buffer, COLOR_CHAR + "x"
                    + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
                    + COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
                    + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5)
            );
        }
        return matcher.appendTail(buffer).toString();
    }

    public static boolean containFilteredWords(String string) {
        for (String filter : FILTERED_WORDS) {
            if (string.toLowerCase().contains(filter))
                return true;
        }
        return false;
    }

    public static boolean containsSpecialChars(String string) {
        String first = ChatColor.stripColor(string);
        Matcher matcher = SPEC_CHAR_PATTERN.matcher(first);
        return matcher.find();
    }


    public static String getEraString(int n) {
        int hunRem = n % 100;
        int tenRem = n % 10;
        if (hunRem - tenRem == 10) {
            return "th";
        }
        switch (tenRem) {
            case 1:
                return n + "st";
            case 2:
                return n + "nd";
            case 3:
                return n + "rd";
            default:
                return n + "th";
        }
    }

    public static void sendCenteredMessage(Player livingEntity, String message) {
        if (message == null || message.equals("")) livingEntity.sendMessage("");
        assert (message) != null;
        message = applyColors(message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for (char c : message.toCharArray()) {
            if (c == '§') {
                previousCode = true;
            } else if (previousCode) {
                previousCode = false;
                isBold = c == 'l' || c == 'L';
            } else {
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while (compensated < toCompensate) {
            sb.append(" ");
            compensated += spaceLength;
        }
        livingEntity.sendMessage(sb.toString() + message);
    }

    public static String getStringLocation(final Location l) {
        if (l == null) {
            return "";
        }
        return l.getWorld().getName() + ":" + l.getBlockX() + ":" + l.getBlockY() + ":" + l.getBlockZ();
    }

    public static Location getLocationString(final String s) {
        if (s == null || s.trim().equals("")) {
            return null;
        }
        final String[] parts = s.split(":");
        if (parts.length == 4) {
            final World w = Bukkit.getServer().getWorld(parts[0]);
            final int x = Integer.parseInt(parts[1]);
            final int y = Integer.parseInt(parts[2]);
            final int z = Integer.parseInt(parts[3]);
            return new Location(w, x, y, z);
        }
        return null;
    }

    public static String getFullMessage(String[] args, int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < args.length; i++) {
            sb.append(args[i]).append(" ");
        }

        return sb.toString().trim();
    }

    public static String parseConfigString(String toParse, String toReplace, String string) {
        return string.replace(toParse, toReplace);
    }

    public static String twoDigitString(int number) {
        if (number == 0) {
            return "00";
        }

        if (number / 10 == 0) {
            return "0" + number;
        }

        return String.valueOf(number);
    }

    public static String getDurationString(int seconds) {
        String toReturn = "";
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;
        if (seconds > 0) {
            toReturn = seconds + "s ";
        }
        if (minutes > 0) {
            toReturn = minutes + "m " + toReturn;
        }
        if (hours > 0) {
            toReturn = hours + "h " + toReturn;
        }

        return toReturn;
    }

    public static void main(String[] args) {
        System.out.println(levelProgress(150, 1000));
    }

    public static String levelProgress(int currentXP, int requiredXP) {
        if (currentXP <= 0) return "&8❙❙❙❙❙❙❙❙❙❙&7";
        int slice = requiredXP / 10;
        int hits = 1;
        for (int i = 0; i < currentXP; i++) {
            if (currentXP >= hits * slice)
                hits++;
        }
        hits--;
        int gray = 10 - hits;

        StringBuilder stringBuilder = new StringBuilder("&2");
        for (int i = 0; i < hits; i++)
            stringBuilder.append("❙");

        if (gray > 0)
            stringBuilder.append("&8");
        for (int i = 0; i < gray; i++)
            stringBuilder.append("❙");
        return stringBuilder.toString();
    }
}