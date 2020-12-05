package _2020.day4;

import _2020.day4.Passport.PassportBuilder;
import lombok.extern.java.Log;

import java.util.Set;

@Log
enum PassportField {
    BIRTH_YEAR("byr") {
        @Override
        void addValue(PassportBuilder builder, String value) {
            if (isValid(value)) {
                builder.birthYear(value);
            }
        }

        @Override
        boolean isValid(String value) {
            int date = Integer.parseInt(value);
            return date >= 1920 && date <= 2002;
        }
    },
    ISSUE_YEAR("iyr") {
        @Override
        void addValue(PassportBuilder builder, String value) {
            if (isValid(value)) {
                builder.issueYear(value);
            }
        }

        @Override
        boolean isValid(String value) {
            int date = Integer.parseInt(value);
            return date >= 2010 && date <= 2020;
        }
    },
    EXPIRATION_YEAR("eyr") {
        @Override
        void addValue(PassportBuilder builder, String value) {
            if (isValid(value)) {
                builder.expirationYear(value);
            }
        }

        @Override
        boolean isValid(String value) {
            int date = Integer.parseInt(value);
            return date >= 2020 && date <= 2030;
        }
    },
    HEIGHT("hgt") {
        @Override
        void addValue(PassportBuilder builder, String value) {
            if (isValid(value)) {
                builder.height(value);
            }
        }

        @Override
        boolean isValid(String value) {
            if (isHeightWithinRange(value, "cm", 150, 193))
                return true;

            if (isHeightWithinRange(value, "in", 59, 76))
                return true;

            return false;
        }

        private boolean isHeightWithinRange(String value, String units, int lowerLimit, int upperLimit) {
            if (value.endsWith(units)) {
                int height = Integer.parseInt(value.replace(units, ""));

                return height >= lowerLimit && height <= upperLimit;
            }
            return false;
        }
    },
    HAIR_COLOR("hcl") {
        @Override
        void addValue(PassportBuilder builder, String value) {
            if (isValid(value)) {
                builder.hairColor(value);
            }
        }

        @Override
        boolean isValid(String value) {
            return value.matches("#[0-9a-f]{6}");
        }
    },
    EYE_COLOR("ecl") {

        private final Set<String> ALLOWED_EYE_COLORS = Set.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");

        @Override
        void addValue(PassportBuilder builder, String value) {
            if (isValid(value)) {
                builder.eyeColor(value);
            }
        }

        @Override
        boolean isValid(String value) {
            return ALLOWED_EYE_COLORS.contains(value);
        }
    },
    PASSPORT_ID("pid") {
        @Override
        void addValue(PassportBuilder builder, String value) {
            if (isValid(value)) {
                builder.id(value);
            }
        }

        @Override
        boolean isValid(String value) {
            return value.matches("[0-9]{9}");
        }
    },
    COUNTRY_ID("cid") {
        @Override
        void addValue(PassportBuilder builder, String value) {
            builder.countryId(value);
        }

        @Override
        boolean isValid(String value) {
            return true;
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

    abstract boolean isValid(String value);

}