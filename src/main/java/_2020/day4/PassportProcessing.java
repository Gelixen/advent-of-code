package _2020.day4;

import _2020.day4.Passport.PassportBuilder;
import lombok.extern.java.Log;
import util.SolvableTask;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;

@Log
public class PassportProcessing implements SolvableTask {

    private static final String KEY_VALUE_SEPARATOR = ":";

    public static void main(String[] args) {
        new PassportProcessing().solve();
    }

    @Override
    public String getPackageName() {
        return MethodHandles.lookup().lookupClass().getPackageName();
    }

    @Override
    public void solve() {
        long result = Arrays.stream(getInputLines("\n\n"))
                .map(this::mapToPassport)
                .filter(this::validateBasedOnNPE)
                .count();

        log.info(String.valueOf(result));
    }

    public PassportBuilder mapToPassport(String line) {
        PassportBuilder passportBuilder = Passport.builder();

        Arrays.stream(line.split(" |\n"))
                .map(this::getDataPair)
                .forEach(field -> buildPassport(passportBuilder, field));

        return passportBuilder;
    }

    private Field getDataPair(String keyValueLine) {
        String[] keyValue = keyValueLine.split(KEY_VALUE_SEPARATOR);

        String key = keyValue[0];
        String value = keyValue[1];

        return new Field(key, value);
    }

    private void buildPassport(PassportBuilder passportBuilder, Field field) {
        PassportField.findByFieldCode(field.getCode())
                .addValue(passportBuilder, field.getValue());
    }

    private boolean validateBasedOnNPE(PassportBuilder passportBuilder) {
        try {
            passportBuilder.build();
        } catch (NullPointerException e) {
            return false;
        }

        return true;
    }

}
