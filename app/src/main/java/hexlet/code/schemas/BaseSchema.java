package hexlet.code.schemas;

public abstract class BaseSchema<T, S extends BaseSchema<T, S>> {
    protected boolean isRequired = false;

    @SuppressWarnings("unchecked")
    public S required() {
        this.isRequired = true;
        return (S) this;
    }

    public abstract boolean isValid(Object value);
}
