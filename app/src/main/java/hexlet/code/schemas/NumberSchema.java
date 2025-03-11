package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<NumberSchema> {
    private boolean positive;
    private Integer minRange;
    private Integer maxRange;

    public NumberSchema positive() {
        this.positive = true;
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
            return !required;
        }
        if (!(value instanceof Integer)) {
            return false;
        }
        int numValue = (Integer) value;
        if (positive && numValue <= 0) {
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
