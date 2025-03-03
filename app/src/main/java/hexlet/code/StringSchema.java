package hexlet.code;

public class StringSchema {
    private boolean isRequired = false;
    private int minLength = 0;
    private String containsSubstring = null;

    public StringSchema required() {
        this.isRequired = true;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        this.containsSubstring = substring;
        return this;
    }

    public boolean isValid(String value) {
        if (value == null) {
            return !isRequired;
        }
        if (isRequired && value.isEmpty()) {
            return false;
        }
        if (value.length() < minLength) {
            return false;
        }
        if (containsSubstring != null && !value.contains(containsSubstring)) {
            return false;
        }
        return true;
    }
}
