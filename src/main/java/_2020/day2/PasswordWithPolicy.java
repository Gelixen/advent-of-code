package _2020.day2;

import lombok.Value;

@Value
public class PasswordWithPolicy {

    private final String password;
    private final PasswordPolicy passwordPolicy;

    public boolean isValid() {
        return passwordPolicy.isValid(password);
    }

}
