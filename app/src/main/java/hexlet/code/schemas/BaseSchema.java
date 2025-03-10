package hexlet.code.schemas;

public abstract class BaseSchema<T extends BaseSchema<T>> {
    protected abstract T getThis();

    public T required() {
        return getThis();
    }

    public abstract boolean isValid(Object value);
}
