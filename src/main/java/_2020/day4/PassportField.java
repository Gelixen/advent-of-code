package _2020.day4;

import _2020.day4.Passport.PassportBuilder;
import lombok.extern.java.Log;

@Log
enum PassportField {
    BIRTH_YEAR("byr") {
        @Override
        void addValue(PassportBuilder builder, String value) {
            builder.birthYear(value);
        }
    },
    ISSUE_YEAR("iyr") {
        @Override
        void addValue(PassportBuilder builder, String value) {
            builder.issueYear(value);
        }
    },
    EXPIRATION_YEAR("eyr") {
        @Override
        void addValue(PassportBuilder builder, String value) {
            builder.expirationYear(value);
        }
    },
    HEIGHT("hgt") {
        @Override
        void addValue(PassportBuilder builder, String value) {
            builder.height(value);
        }
    },
    HAIR_COLOR("hcl") {
        @Override
        void addValue(PassportBuilder builder, String value) {
            builder.hairColor(value);
        }
    },
    EYE_COLOR("ecl") {
        @Override
        void addValue(PassportBuilder builder, String value) {
            builder.eyeColor(value);
        }
    },
    PASSPORT_ID("pid") {
        @Override
        void addValue(PassportBuilder builder, String value) {
            builder.id(value);
        }
    },
    COUNTRY_ID("cid") {
        @Override
        void addValue(PassportBuilder builder, String value) {
            builder.countryId(value);
        }
    };

    private final String fieldCode;

    PassportField(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    public static PassportField findByFieldCode(String fieldCode) {
        for (PassportField passportField : values()) {
            if (passportField.fieldCode.equals(fieldCode)) {
                return passportField;
            }
        }
        throw new FieldCodeNotFoundException(
                String.format("Field with code %s not found!", fieldCode)
        );
    }

    abstract void addValue(PassportBuilder builder, String value);
}