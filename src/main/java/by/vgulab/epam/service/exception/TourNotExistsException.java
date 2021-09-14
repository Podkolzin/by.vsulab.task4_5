package by.vgulab.epam.service.exception;

public class TourNotExistsException  extends ServiceException{
    private Long id;

    public TourNotExistsException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
