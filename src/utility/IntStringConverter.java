package utility;

import javafx.util.StringConverter;

public class IntStringConverter extends StringConverter<Number> {
    private final int emptyValue;

    public IntStringConverter(int emptyValue) {
        this.emptyValue = emptyValue;
    }

    @Override
    public String toString(Number n) {
        if (n == null || n.intValue() == emptyValue) {
            return "";
        }
        return n.toString();
    }

    @Override
    public Number fromString(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return emptyValue;
        }
    }
}