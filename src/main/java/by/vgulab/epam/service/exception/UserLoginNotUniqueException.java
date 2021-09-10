package by.vgulab.epam.service.exception;

public class UserLoginNotUniqueException extends ServiceException{

    private String login;

    public UserLoginNotUniqueException(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}
