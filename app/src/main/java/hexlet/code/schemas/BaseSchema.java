package hexlet.code.schemas;

import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected boolean required;
    protected Predicate<Object> validator = value -> true;

    public T required() {
        this.required = true;
        this.validator = this.validator.and(value -> value != null);
        return (T) this;
    }

    public boolean isValid(Object value) {
        if (!required && value == null) {
            return true;
        }
        return validator.test(value);
    }

    protected void addCondition(Predicate<Object> condition) {
        this.validator = this.validator.and(condition);
    }
}
