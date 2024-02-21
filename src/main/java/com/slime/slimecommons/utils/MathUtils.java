package com.slime.slimecommons.utils;

import java.util.Random;

public class MathUtils {
    public static boolean tryPercentage(double percentage){
        return new Random().nextDouble() <= percentage;
    }
}
