package it.italiandudes.webtrpg.core.misc;

import java.util.Random;

@SuppressWarnings("unused")
public final class RandomNumberGenerator {

    // RANDOM
    public static final Random RANDOM = new Random();

    // Methods

    /**
     * Randomize a number between two values
     * @param min included in range
     * @param max excluded from range
     * @return random integer in form of [min, max[
     */
    public static int randomBetween(int min, int max) {
        return RANDOM.nextInt(max - min) + min;
    }
}
