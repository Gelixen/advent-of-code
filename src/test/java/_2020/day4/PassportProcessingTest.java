package _2020.day4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PassportProcessingTest {

    @Test
    void mapToPassport_allFieldsFilled_passes() {
        Passport passport = new PassportProcessing().mapToPassport(
                "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n" +
                        "byr:1937 iyr:2017 cid:147 hgt:183cm"
        ).build();

        assertEquals("1937", passport.getBirthYear());
        assertEquals("2017", passport.getIssueYear());
        assertEquals("2020", passport.getExpirationYear());
        assertEquals("183cm", passport.getHeight());
        assertEquals("#fffffd", passport.getHairColor());
        assertEquals("gry", passport.getEyeColor());
        assertEquals("860033327", passport.getId());
        assertEquals("147", passport.getCountryId());
    }

    @Test
    void mapToPassport_byrAnsdCidFieldsMissing_nonNullValidationFailForByr() {
        assertThrows(NullPointerException.class, () ->
                new PassportProcessing().mapToPassport(
                        "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n" +
                                "hcl:#cfa07d byr:1929"
                ).build()
        );
    }

    @Test
    void mapToPassport_cidFieldMissingButNotRequired_passes() {
        Passport passport = new PassportProcessing().mapToPassport(
                "hcl:#ae17e1 iyr:2013\n" +
                        "eyr:2024\n" +
                        "ecl:brn pid:760753108 byr:1931\n" +
                        "hgt:179cm"
        ).build();

        assertEquals("1931", passport.getBirthYear());
        assertEquals("2013", passport.getIssueYear());
        assertEquals("2024", passport.getExpirationYear());
        assertEquals("179cm", passport.getHeight());
        assertEquals("#ae17e1", passport.getHairColor());
        assertEquals("brn", passport.getEyeColor());
        assertEquals("760753108", passport.getId());
        assertNull(passport.getCountryId());
    }

    @Test
    void mapToPassport_byrAndCidFieldsMissing_nonNullValidationFailForByr() {
        assertThrows(NullPointerException.class, () ->
                new PassportProcessing().mapToPassport(
                        "hcl:#cfa07d eyr:2025 pid:166559648\n" +
                                "iyr:2011 ecl:brn hgt:59in"
                ).build()
        );
    }

    @Test
    void mapToPassport_nonExistingFieldCode_throwsFieldCodeNotFoundException() {
        assertThrows(FieldCodeNotFoundException.class, () ->
                new PassportProcessing().mapToPassport("aaa:2013")
        );
    }
}