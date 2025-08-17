package it.italiandudes.webtrpg.dnd5e.data.enums;

// DO NOT CHANGE THE ENUM NAMES, SOME METHODS USE THE FINAL METHOD "name()" FOR THE DB
public enum DND5EProficiencyLevel {
    SEVERE_INEPTITUDE,
    INEPTITUDE,
    NONE,
    PROFICIENCY,
    MASTERY;

    // Methods
    public int getEffectiveProficiencyBonus(int characterProficiencyBonus) {
        switch (this) {
            case SEVERE_INEPTITUDE -> {
                return -2;
            }
            case INEPTITUDE -> {
                return -1;
            }
            case PROFICIENCY -> {
                return characterProficiencyBonus;
            }
            case MASTERY -> {
                return characterProficiencyBonus*2;
            }
            default -> {
                return 0;
            }
        }
    }
}
