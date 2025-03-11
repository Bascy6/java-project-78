package hexlet.code.schemas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {

    @Test
    public void testRequired() {
        NumberSchema numberSchema = new NumberSchema().required();

        assertTrue(numberSchema.isValid(10));
        assertFalse(numberSchema.isValid(null));
    }

    @Test
    public void testPositive() {
        NumberSchema numberSchema = new NumberSchema().positive();

        assertTrue(numberSchema.isValid(5));
        assertFalse(numberSchema.isValid(-5));
        assertFalse(numberSchema.isValid(0));
    }

    @Test
    public void testRange() {
        NumberSchema numberSchema = new NumberSchema().range(10, 20);

        assertTrue(numberSchema.isValid(15));
        assertFalse(numberSchema.isValid(5));
        assertFalse(numberSchema.isValid(25));
    }

    @Test
    public void testCombination() {
        NumberSchema numberSchema = new NumberSchema()
                .required()
                .positive()
                .range(1, 10);

        assertTrue(numberSchema.isValid(5));
        assertFalse(numberSchema.isValid(null));
        assertFalse(numberSchema.isValid(-5));
        assertFalse(numberSchema.isValid(15));
    }
}
