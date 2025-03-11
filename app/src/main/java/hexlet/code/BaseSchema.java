package hexlet.code;

public abstract class BaseSchema<T> {
    protected boolean required;

    @SuppressWarnings("unchecked")
    public T required() {
        this.required = true;
        return (T) this;
    }

    public abstract boolean isValid(Object value);
}
