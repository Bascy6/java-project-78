package hexlet.code.schemas;

public abstract class BaseSchema<T extends BaseSchema<T>> {
    protected boolean isRequired = false;

    @SuppressWarnings("unchecked")
    public T required() {
        this.isRequired = true;
        return (T) this;
    }

    public abstract boolean isValid(Object value);
}
