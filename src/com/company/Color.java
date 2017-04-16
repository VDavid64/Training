package com.company;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Color {

    // enum a színekhez

    GREEN, YELLOW, RED;


    private static final List<Color> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Color getRandomColor()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}

