package by.vgulab.epam.domain;

public enum Food {


    RO("Без питания"),

    BB("Завтрак"),

    HB("Полупансион"),

    FB("Полный пансион"),

    AI("Все включено");

    private String name;


    Food(String name) {
        this.name = name;
    }

    public Long getId() {
        return Long.valueOf(ordinal());
    }

    public String getName() {
        return name;
    }
}
