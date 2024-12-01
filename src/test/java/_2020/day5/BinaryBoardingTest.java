package _2020.day5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BinaryBoardingTest {

    @ParameterizedTest(name = "{index} given \"{0}\" code expect {1} row {2} column {3} id")
    @CsvSource(value = {
            "FFFFFFFLLL:0:0:0",
            "BBBBBBBRRR:127:7:1023",
            "FBFBBFFRLR:44:5:357",
            "BFFFBBFRRR:70:7:567",
            "FFFBBBFRRR:14:7:119",
            "BBFFBBFRLL:102:4:820",
    }, delimiter = ':')
    void findSeat(String code, int expectedRow, int expectedColumn, int expectedId) {
        Seat seat = BinaryBoarding.findSeat(code);

        assertEquals(expectedRow, seat.row());
        assertEquals(expectedColumn, seat.column());
        assertEquals(expectedId, seat.getId());
    }

    @ParameterizedTest(name = "{index} given \"{0}\" code with \"{1}\" char and {2}-{3} range expect {4}")
    @CsvSource(value = {
            "F:F:0:1:0",
            "U:U:0:1:0",
            "U:N:0:1:1",
            "UU:U:0:3:0",
            "UN:U:0:3:1",
            "NU:U:0:3:2",
            "NN:U:0:3:3",
            "UU:U:4:7:4",
            "UN:U:4:7:5",
            "NU:U:4:7:6",
            "NN:U:4:7:7",
    }, delimiter = ':')
    void findPlace(String code, char upperLimitChar, int minValue, int maxValue, int expectedPlace) {
        CharSequence codeCharSequence = code.subSequence(0, code.length());

        int place = BinaryBoarding.findPlace(codeCharSequence, upperLimitChar, minValue, maxValue);

        assertEquals(expectedPlace, place);
    }

}