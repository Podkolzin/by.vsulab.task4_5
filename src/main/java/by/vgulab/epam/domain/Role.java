package by.vgulab.epam.domain;

public enum Role {
    CUSTOMER("покупатель"),
    MANAGER("менеджер"),
    ADMINISTRATOR("администратор");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return Long.valueOf(ordinal());
    }
}
