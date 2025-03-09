package hexlet.code;

public class StringSchema extends BaseSchema<StringSchema> {
    private int minLength = 0;
    private String containsSubstring = null;

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        this.containsSubstring = substring;
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        if (!super.isValid(value)) {
            return false;
        }
        if (value == null) {
            return !isRequired;
        }
        if (!(value instanceof String)) {
            return false;
        }
        String str = (String) value;
        if (isRequired && str.isEmpty()) {
            return false;
        }
        if (str.length() < minLength) {
            return false;
        }
        if (containsSubstring != null && !str.contains(containsSubstring)) {
            return false;
        }
        return true;
    }
}
