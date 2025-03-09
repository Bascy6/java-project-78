package hexlet.code.schemas;

public abstract class BaseSchema<T extends BaseSchema<T>> {
    protected boolean isRequired = false;

    public T required() {
        this.isRequired = true;
        return (T) this;
    }

    protected boolean checkRequired(Object value) {
        return !isRequired || value != null;
    }

    public abstract boolean isValid(Object value);
}
