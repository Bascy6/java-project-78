package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Integer> {
    private final String numberIsPositive = "positive";
    private final String numberInRange = "range";

    public NumberSchema required() {
        Predicate<Integer> requiredPredicate = e -> !Objects.isNull(e);
        this.addCheck(isRequired, requiredPredicate);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Integer> positive = e -> {
            if (e == 0) {
                return false;
            } else {
                return e > 0;
            }
        };
        addCheck(numberIsPositive, positive);
        return this;
    }

    public NumberSchema range(Integer a, Integer b) {
        Predicate<Integer> rangePredicate = e -> e >= a && e <= b;
        addCheck(numberInRange, rangePredicate);
        return this;
    }
}
