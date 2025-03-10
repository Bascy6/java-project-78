package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<NumberSchema> {
    private boolean isRequired = false;
    private boolean isPositive = false;
    private Integer minRange = null;
    private Integer maxRange = null;

    @Override
    protected NumberSchema getThis() {
        return this;
    }

    @Override
    public NumberSchema required() {
        this.isRequired = true;
        return super.required();
    }

    @Override
    public boolean isRequired() {
        return isRequired;
    }

    public NumberSchema positive() {
        this.isPositive = true;
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.minRange = min;
        this.maxRange = max;
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return !isRequired;
        }

        if (!(value instanceof Number)) {
            return false;
        }

        Number number = (Number) value;
        double numValue = number.doubleValue();

        if (isPositive && numValue <= 0) {
            return false;
        }

        if (minRange != null && numValue < minRange) {
            return false;
        }

        if (maxRange != null && numValue > maxRange) {
            return false;
        }

        return true;
    }
}
