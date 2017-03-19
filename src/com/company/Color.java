package com.company;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Class representing colors.
 *
 * @author i_did_iit team
 */
public enum Color {

    /**
     * Enumeration representing the different colors.
     */
    GREY, GREEN, BLUE, RED, YELLOW, WHITE;


    /**
     * Creating a list of possible colors,
     * assigning the number of colors to the SIZE variable.
     */
    private static final List<Color> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     * Function that returns a random color.
     * @return: returns a random color.
     */
    public static Color getRandomColor()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}

