package com.company;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/***
 * Color enumeration, representing one of the three colors used by the program.
 */
public enum Color {

    // enum a színekhez

    GREEN, YELLOW, RED;


    /**
     * List of colors.
     */
    private static final List<Color> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    /**
     * Size of list of colors.
     */
    private static final int SIZE = VALUES.size();
    /**
     * Randomness.
     */
    private static final Random RANDOM = new Random();

    /**
     * Function that can return a random color.
     * @return Returns one of the three colors.
     */
    public static Color getRandomColor()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}

