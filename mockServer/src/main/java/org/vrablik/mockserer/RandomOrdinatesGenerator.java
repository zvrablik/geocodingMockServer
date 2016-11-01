/* --------------------------------------------------------------*\
| Copyright (C) e-Spatial Solutions Limited, All rights reserved. |
\* --------------------------------------------------------------*/
package org.vrablik.mockserer;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by zvrablik on 11/1/16.
 */
public class RandomOrdinatesGenerator {

    public static final double X_MIN = -180D;
    public static final int X_MAX = 180;
    public static final double X_RANGE = X_MAX - X_MIN;

    public static final double Y_MIN = -87D;
    public static final double Y_MAX = 87D;
    public static final double Y_RANGE = Y_MAX - Y_MIN;

    private final Random random;

    public RandomOrdinatesGenerator(){
        random = new Random(System.currentTimeMillis());
    }

    /**
     * Get random latitude.
     *
     * @return
     */
    public double getRandomY(){
        double value = random.nextDouble() * Y_RANGE;

        return value + Y_MIN;
    }

    /**
     * Get random longitude.
     *
     * @return
     */
    public double getRandomX(){

        double value = random.nextDouble() * X_RANGE;
        return value + X_MIN;
    }

    public String getRandomPlaceId(){
        return "randomServerGeneratedId_" + new BigInteger(80, random).toString(32);
    }
}
