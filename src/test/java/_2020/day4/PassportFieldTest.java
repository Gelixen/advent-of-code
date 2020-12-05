package _2020.day4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static _2020.day4.PassportField.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PassportFieldTest {

    @ParameterizedTest
    @ValueSource(strings = {"1800", "1919", "2003", "2100"})
    void isValid_birthYear_invalid(String value) {
        assertFalse(BIRTH_YEAR.isValid(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1920", "1980", "2000", "2002"})
    void isValid_birthYear_valid(String value) {
        assertTrue(BIRTH_YEAR.isValid(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1800", "2009", "2021", "2100"})
    void isValid_issueYear_invalid(String value) {
        assertFalse(ISSUE_YEAR.isValid(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2010", "2015", "2020"})
    void isValid_issueYear_valid(String value) {
        assertTrue(ISSUE_YEAR.isValid(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1900", "2019", "2031", "2200"})
    void isValid_expirationYear_invalid(String value) {
        assertFalse(EXPIRATION_YEAR.isValid(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2020", "2028", "2030"})
    void isValid_expirationYear_valid(String value) {
        assertTrue(EXPIRATION_YEAR.isValid(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"100cm", "149cm", "194cm", "210cm", "50in", "58in", "77in", "90in", "175", "65"})
    void isValid_height_invalid(String value) {
        assertFalse(HEIGHT.isValid(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"150cm", "175cm", "193cm", "59in", "65in", "76in"})
    void isValid_height_valid(String value) {
        assertTrue(HEIGHT.isValid(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"#12345", "#1234567", "123456", "#abcdefg", "#gfedcba", "#aaaaag", "#fffffz", "abcdef"})
    void isValid_hairColor_invalid(String value) {
        assertFalse(HAIR_COLOR.isValid(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"#111111", "#999999", "#123456", "#987654", "#abcdef", "#fedcba", "#aaaaaa", "#ffffff"})
    void isValid_hairColor_valid(String value) {
        assertTrue(HAIR_COLOR.isValid(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"aaa", "g", "zzz", "red", "wth"})
    void isValid_eyeColor_invalid(String value) {
        assertFalse(EYE_COLOR.isValid(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"})
    void isValid_eyeColor_valid(String value) {
        assertTrue(EYE_COLOR.isValid(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345678", "1234567890", "12345678a", "aaaaaaaa", "abcdefghi"})
    void isValid_passportId_invalid(String value) {
        assertFalse(PASSPORT_ID.isValid(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"000000000", "012345678", "123456789", "555555555", "999999999"})
    void isValid_passportId_valid(String value) {
        assertTrue(PASSPORT_ID.isValid(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "1", "1234567890", "abcdefghijklm", "-"})
    void isValid_countryId_valid(String value) {
        assertTrue(COUNTRY_ID.isValid(value));
    }

}