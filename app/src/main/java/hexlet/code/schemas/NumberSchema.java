package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<NumberSchema> {
    private boolean isPositive = false;
    private int minRange = Integer.MIN_VALUE;
    private int maxRange = Integer.MAX_VALUE;

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
        if (!super.isValid(value)) {
            return false;
        }
        if (value == null) {
            return true;
        }
        if (!(value instanceof Integer)) {
            return false;
        }
        int number = (int) value;
        if (isPositive && number <= 0) {
            return false;
        }
        if (number < minRange || number > maxRange) {
            return false;
        }
        return true;
    }
}
