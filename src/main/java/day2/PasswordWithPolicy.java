package day2;

public class PasswordWithPolicy {

    private final String password;
    private final PasswordPolicy passwordPolicy;

    public PasswordWithPolicy(String password, PasswordPolicy passwordPolicy) {
        this.password = password;
        this.passwordPolicy = passwordPolicy;
    }

    public boolean isValid() {
        return passwordPolicy.isValid(password);
    }

    public String getPassword() {
        return password;
    }

    public PasswordPolicy getPasswordPolicy() {
        return passwordPolicy;
    }
}
