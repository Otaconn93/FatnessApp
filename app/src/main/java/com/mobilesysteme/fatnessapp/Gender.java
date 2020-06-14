package com.mobilesysteme.fatnessapp;

public enum Gender {

    MALE(0, "Männlich"),
    FEMALE(1, "Weiblich");

    int id;
    String text;

    Gender(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    /**
     * @return all defined id values
     */
    public static String[] getIds() {

        Gender[] values = Gender.values();

        String[] texts = new String[values.length];
        for(int i = 0; i < values.length; i++) {
            texts[i] = String.valueOf(values[i].getId());
        }

        return texts;
    }

    /**
     * @return all defined text values
     */
    public static String[] getTexts() {

        Gender[] values = Gender.values();

        String[] texts = new String[values.length];
        for(int i = 0; i < values.length; i++) {
            texts[i] = values[i].getText();
        }

        return texts;
    }

    /**
     * searches for the Gender matching the id
     * @param id the id of the to be found Gender
     * @return the Gender with the given id or null if not found
     */
    public static Gender findGenderById(int id) {

        for (Gender gender : Gender.values()) {

            if (id == gender.getId()) {
                return gender;
            }
        }

        return null;
    }
}
