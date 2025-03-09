package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    protected boolean isRequired = false;

    public T required() {
        this.isRequired = true;
        return (T) this;
    }

    public boolean isValid(Object value) {
        if (isRequired && value == null) {
            return false;
        }
        return true;
    }
}
