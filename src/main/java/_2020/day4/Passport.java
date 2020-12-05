package _2020.day4;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class Passport {

    @NonNull
    String birthYear;
    @NonNull
    String issueYear;
    @NonNull
    String expirationYear;
    @NonNull
    String height;
    @NonNull
    String hairColor;
    @NonNull
    String eyeColor;
    @NonNull
    String id;

    String countryId;

}