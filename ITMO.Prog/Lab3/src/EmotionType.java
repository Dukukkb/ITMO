package src;

public enum EmotionType {
    HAPPY("счастлив"),
    UNHAPPY("недоволен"),
    WORRIED("обеспокоен");

    private final String description;
    
    EmotionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
