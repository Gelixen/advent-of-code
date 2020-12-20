package _2020.day11;

import java.util.Arrays;

public class ConversionUtil {

    public static char[][] stringToCharMatrix(String textMatrix) {
        return Arrays.stream(textMatrix.split("\n"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }
}