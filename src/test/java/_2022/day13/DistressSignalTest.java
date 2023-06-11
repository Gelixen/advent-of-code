package _2022.day13;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.gson.JsonElement;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class DistressSignalTest {

    private static Stream<Arguments> testComparison() {
        return Stream.of(
                Arguments.of(
                        "[1,1,3,1,1]",
                        "[1,1,5,1,1]",
                        true
                ),
                Arguments.of(
                        "[[1],[2,3,4]]",
                        "[[1],4]",
                        true
                ),
                Arguments.of(
                        "[9]",
                        "[[8,7,6]]",
                        false
                ),
                Arguments.of(
                        "[[4,4],4,4]",
                        "[[4,4],4,4,4]",
                        true
                ),
                Arguments.of(
                        "[7,7,7,7]",
                        "[7,7,7]",
                        false
                ),
                Arguments.of(
                        "[]",
                        "[3]",
                        true
                ),
                Arguments.of(
                        "[[[]]]",
                        "[[]]",
                        false
                ),
                Arguments.of(
                        "[1,[2,[3,[4,[5,6,7]]]],8,9]",
                        "[1,[2,[3,[4,[5,6,0]]]],8,9]",
                        false
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void testComparison(String left, String right, boolean expectedResult) {
        boolean result = new DistressSignal().compareIfInRightOrder(left, right);
        assertEquals(expectedResult, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "[]",
            "[9]",
            "[1,1,3,1,1]",
            "[[8,7,6]]",
            "[[]]",
            "[[[]]]",
            "[[1,2],4,5]"
    })
    void testLinesParsing(String line) {
        JsonElement result = new DistressSignal().parseLine(line);
        System.out.println(result);
    }

}