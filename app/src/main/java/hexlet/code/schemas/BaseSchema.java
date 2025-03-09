package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    protected boolean isRequired = false;

    public T required() {
        this.isRequired = true;
        return (T) this;
    }

    public abstract boolean isValid(Object value);
}
