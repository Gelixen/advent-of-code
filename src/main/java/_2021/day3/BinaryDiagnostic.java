package _2021.day3;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.Arrays;

@Log
public class BinaryDiagnostic implements SolvableTask {

    private int[] onesRepetitions;

    public BinaryDiagnostic() {}

    public static void main(String[] args) {
        new BinaryDiagnostic().solve();
    }

    @Override
    public void solve() {
        String[] binaryNumbers = getInputLines();

        int digitsCount = binaryNumbers[0].length();
        onesRepetitions = new int[digitsCount];

        Arrays.stream(binaryNumbers)
                .forEach(this::countOnesForEachDigitLine);

        int gammaRate = calculateGammaRate(binaryNumbers.length);
        int epsilonRate = calculateEpsilonFromGamma(digitsCount, gammaRate);

        log.info(String.valueOf(gammaRate * epsilonRate));
    }

    private int calculateGammaRate(int numbersCount) {
        StringBuilder binaryGammaRate = new StringBuilder();

        for (int repetition : onesRepetitions) {
            int halfCount = numbersCount / 2;
            int finalBitValue = (repetition > halfCount) ? 1 : 0;
            binaryGammaRate.append(finalBitValue);
        }

        return Integer.parseInt(binaryGammaRate.toString(), 2);
    }

    private int calculateEpsilonFromGamma(int digitsCount, int gammaRate) {
        int maxDecimalAvailable = (int) (Math.pow(2, digitsCount) - 1);

        return maxDecimalAvailable - gammaRate;
    }

    public void countOnesForEachDigitLine(String binaryNumber) {
        for (int i = 0; i < binaryNumber.length(); i++) {
            if (binaryNumber.charAt(i) == '1') {
                onesRepetitions[i]++;
            }
        }
    }
}
