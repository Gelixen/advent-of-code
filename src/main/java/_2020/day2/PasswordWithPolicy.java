package _2020.day2;

public record PasswordWithPolicy(String password, PasswordPolicy passwordPolicy) {

    public boolean isValid() {
        return passwordPolicy.isValid(password);
    }

}