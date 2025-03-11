package hexlet.code.schemas;

public class StringSchema extends BaseSchema<StringSchema> {
    public StringSchema minLength(int length) {
        addCondition(value -> ((String) value).length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        addCondition(value -> ((String) value).contains(substring));
        return this;
    }

    @Override
    public StringSchema required() {
        super.required();
        addCondition(value -> !((String) value).isEmpty());
        return this;
    }
}
