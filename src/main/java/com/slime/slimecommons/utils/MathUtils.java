package com.slime.slimecommons.utils;

import java.util.Random;

public class MathUtils {
    public static boolean tryPercentage(double percentage){
        return new Random().nextDouble() <= percentage;
    }

    public static double round(double value, int precision) {
        double scale = Math.pow(10, precision);
        return Math.round(value * scale) / scale;
    }

    public static int randomInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public static long randomLong(long min, long max){
        return new Random().nextLong((max - min) + 1) + min;
    }

    // TODO: Add more useful math things...
}
