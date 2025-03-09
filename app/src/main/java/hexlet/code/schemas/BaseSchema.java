package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    protected boolean isRequired = false;

    public BaseSchema<T> required() {
        this.isRequired = true;
        return this;
    }

    public abstract boolean isValid(Object value);
}
