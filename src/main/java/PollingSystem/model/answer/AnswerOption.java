package PollingSystem.model.answer;

public enum AnswerOption {
    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4);

    private final Integer value;

    AnswerOption(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static AnswerOption fromValue(Integer value) {
        for (AnswerOption option : AnswerOption.values()) {
            if (option.value.equals(value)) {
                return option;
            }
        }
        throw new IllegalArgumentException("Invalid answer option value: " + value);
    }

    public static boolean isValid(Integer value) {
        if (value == null) {
            return false;
        }
        try {
            fromValue(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}