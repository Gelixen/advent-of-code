package _2022.day13;

import static java.lang.Boolean.TRUE;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import lombok.extern.java.Log;
import util.SolvableTask;

@Log
public class DistressSignal implements SolvableTask {

    public static void main(String[] args) {
        new DistressSignal().solve();
    }

    @Override
    public void solve() {
        String[] inputLines = getInputLines();
        int rightOrderPairsSum = 0;

        for (int i = 0; i < inputLines.length; i += 3) {
            String left = inputLines[i];
            String right = inputLines[i + 1];

            if (compareIfInRightOrder(left, right)) {
                int pairNumber = i / 3 + 1;
                rightOrderPairsSum += pairNumber;
            }
        }

        log.info(String.valueOf(rightOrderPairsSum));

    }

    boolean compareIfInRightOrder(String left, String right) {
        JsonElement leftPacket = parseLine(left);
        JsonElement rightPacket = parseLine(right);

        return TRUE.equals(compareIfInRightOrder(leftPacket, rightPacket));
    }

    JsonElement parseLine(String line) {
        return new Gson().fromJson(line, JsonElement.class);
    }

    private Boolean compareIfInRightOrder(JsonElement leftPacket, JsonElement rightPacket) {
        if (isBothIntegers(leftPacket, rightPacket)) {
            int left = leftPacket.getAsInt();
            int right = rightPacket.getAsInt();

            if (left < right) return true;
            if (left > right) return false;
            return null;
        }

        if (isBothArrays(leftPacket, rightPacket)) {
            JsonArray leftArray = leftPacket.getAsJsonArray();
            JsonArray rightArray = rightPacket.getAsJsonArray();

            for (int i = 0; i < Math.max(leftArray.size(), rightArray.size()); i++) {

                JsonElement leftElement;
                try {
                    leftElement = leftArray.get(i);
                } catch (IndexOutOfBoundsException ex) {
                    return true;
                }

                JsonElement rightElement;
                try {
                    rightElement = rightArray.get(i);
                } catch (IndexOutOfBoundsException ex) {
                    return false;
                }

                Boolean result = compareIfInRightOrder(leftElement, rightElement);

                if (result != null) {
                    return result;
                }
            }
            return null;
        }

        if (mixedTypes(leftPacket, rightPacket)) {
            JsonArray wrappedElement = wrapElementInArray(leftPacket);
            return compareIfInRightOrder(wrappedElement, rightPacket);
        }
        if (mixedTypes(rightPacket, leftPacket)) {
            JsonArray wrappedElement = wrapElementInArray(rightPacket);
            return compareIfInRightOrder(leftPacket, wrappedElement);
        }

        return false;
    }

    private static boolean isBothIntegers(JsonElement leftPacket, JsonElement rightPacket) {
        return leftPacket.isJsonPrimitive() && rightPacket.isJsonPrimitive();
    }
    
    private static boolean isBothArrays(JsonElement leftPacket, JsonElement rightPacket) {
        return leftPacket.isJsonArray() && rightPacket.isJsonArray();
    }

    private static boolean mixedTypes(JsonElement firstPacket, JsonElement secondPacket) {
        return firstPacket.isJsonPrimitive() && secondPacket.isJsonArray();
    }

    private static JsonArray wrapElementInArray(JsonElement element) {
        JsonArray jsonArray = new JsonArray().getAsJsonArray();
        jsonArray.add(element);
        return jsonArray;
    }

}