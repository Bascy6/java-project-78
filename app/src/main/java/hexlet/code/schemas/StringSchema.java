package hexlet.code.schemas;

public class StringSchema extends BaseSchema<StringSchema> {
    private boolean isRequired = false;
    private int minLength = 0;
    private String contains = null;

    @Override
    protected StringSchema getThis() {
        return this;
    }

    @Override
    public StringSchema required() {
        this.isRequired = true;
        return this;
    }

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
            return !isRequired;
        }
        if (!(value instanceof String)) {
            return false;
        }
        String strValue = (String) value;
        if (isRequired && strValue.isEmpty()) {
            return false;
        }
        if (strValue.length() < minLength) {
            return false;
        }
        if (contains != null && !strValue.contains(contains)) {
            return false;
        }
        return true;
    }
}
