package src.tasks;

import src.exceptions.IncorrectInputException;

public class ValidateUtils {
    public static String validateString(String value) throws IncorrectInputException {
        if (value == null || value.isEmpty() || value.isBlank()) {
            throw new IncorrectInputException("Ошибка ввода");
        } else {
            return value;
        }
    }
}
