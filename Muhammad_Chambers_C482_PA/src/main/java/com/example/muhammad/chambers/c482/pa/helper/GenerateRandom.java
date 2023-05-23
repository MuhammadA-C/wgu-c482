package com.example.muhammad.chambers.c482.pa.helper;

import java.util.Random;

/** This class holds the method for generating random numbers.*/
public class GenerateRandom {

    /** This is the generateRandomInt method.
     This method generates a random integer up to but excluding 100.
     @return Returns an integer*/
    public static int generateRandomInt() {
        Random random = new Random();
        return random.nextInt(100);
    }
}
