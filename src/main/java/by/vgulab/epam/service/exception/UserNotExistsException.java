package by.vgulab.epam.service.exception;

public class UserNotExistsException extends ServiceException {
    private Long id;

    public UserNotExistsException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
