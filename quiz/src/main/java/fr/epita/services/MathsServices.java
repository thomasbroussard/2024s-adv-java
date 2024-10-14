package fr.epita.services;

public class MathsServices {


    public static Long factorial(int num) {
        long result = 1l;
        for (int i = 1; i < num; i++) {
            result = result * i;
        }
        return result;
    }
}
