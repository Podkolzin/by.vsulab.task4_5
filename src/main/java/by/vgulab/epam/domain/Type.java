package by.vgulab.epam.domain;

public enum Type {

    RECREATION("отдых"),
    EXCURSION("экскурсия"),
    SHOPPING("шопинг");

    private String name;

    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return Long.valueOf(ordinal());
    }

}
