package fr.epita.services;

public class MathsServices {


    public static Long factorial(int num) {
        long result = 1l;
        if (num < 0){
            throw new IllegalArgumentException("the number should be a positive integer, got: " + num);
        }
        for (int i = 1; i <= num; i++) {
            result = result * i;
        }
        return result;
    }
}
