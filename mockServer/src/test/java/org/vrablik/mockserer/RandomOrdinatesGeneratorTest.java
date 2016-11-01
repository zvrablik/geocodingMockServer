package org.vrablik.mockserer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zvrablik on 11/1/16.
 */
public class RandomOrdinatesGeneratorTest {

    public RandomOrdinatesGenerator ordinatesGenerator;

    @Before
    public void setUp() throws Exception {
        ordinatesGenerator = new RandomOrdinatesGenerator();
    }

    @Test
    public void isGetXInRange() throws Exception {
        for (int count = 0; count < 10; count++) {
            double randomX = ordinatesGenerator.getRandomX();
            System.out.println("x\t" + randomX);
            assertTrue("Value should be in range -180 and 180 value " + randomX, randomX > -180D && randomX < 180D);
        }
    }

    @Test
    public void isGetYInRange() throws Exception {
        for (int count = 0; count < 10; count++) {
            double randomY = ordinatesGenerator.getRandomY();
            System.out.println("y\t" + randomY);
            assertTrue("Value should be in range -87 and 87 value " + randomY, randomY > -87D && randomY < 87D);
        }
    }

    @Test
    public void testRandomPlaceIdGenerator() throws Exception {
        for (int count = 0; count < 10; count++) {
            String placeId = ordinatesGenerator.getRandomPlaceId();
            System.out.println("placeId\t" + placeId);
            assertTrue("Value should be unique " + placeId, placeId.length() > 30);
        }
    }
}