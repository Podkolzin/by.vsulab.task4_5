package by.vgulab.epam.domain;

public enum Foot {


    RO("Без питания"),

    BB("Завтрак"),

    HB("Полупансион"),

    FB("Полный пансион"),

    AI("Все включено");

    private String name;


    Foot(String name) {
        this.name = name;
    }

    public Long getId() {
        return Long.valueOf(ordinal());
    }

}
