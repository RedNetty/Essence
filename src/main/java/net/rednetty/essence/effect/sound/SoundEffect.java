package net.rednetty.essence.effect.sound;

import org.bukkit.Sound;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Giovanni on 1/4/2021
 */
public class SoundEffect {

    private Sound sound;
    private float volume, pitch, pitchMax;
    private int playTime;

    private boolean randomizedPitch;

    public SoundEffect(Sound sound, float volume, float pitch, int playTime) {
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
        this.playTime = playTime;
    }

    /**
     * The amount of seconds this sound gets played.
     * This does not affect the time the sound gets played in any way,
     * instead it is used to determined when the sound ends.
     */
    public int getPlayTime() {
        return playTime;
    }

    public Sound getSound() {
        return sound;
    }

    public float getVolume() {
        return volume;
    }

    public float getPitch() {
        return pitch;
    }

    public boolean hasRandomizedPitch() {
        return randomizedPitch;
    }

    public SoundEffect setRandomizedPitch(float min, float max) {
        this.pitch = min;
        this.pitchMax = max;
        this.randomizedPitch = true;
        return this;
    }

    public float randomizePitch() {
        return (float) ThreadLocalRandom.current().nextDouble(pitch, pitchMax);
    }
}
