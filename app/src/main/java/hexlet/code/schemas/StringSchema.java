package hexlet.code.schemas;

public class StringSchema extends BaseSchema<StringSchema> {
    private Integer minLength;
    private String contains;

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        this.contains = substring;
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return !required;
        }
        if (!(value instanceof String)) {
            return false;
        }
        String strValue = (String) value;
        if (required && strValue.isEmpty()) {
            return false;
        }
        if (minLength != null && strValue.length() < minLength) {
            return false;
        }
        if (contains != null && !strValue.contains(contains)) {
            return false;
        }
        return true;
    }
}
