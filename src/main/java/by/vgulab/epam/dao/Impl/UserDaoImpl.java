package by.vgulab.epam.dao.Impl;

import by.vgulab.epam.dao.DaoException;
import by.vgulab.epam.dao.UserDao;
import by.vgulab.epam.domain.Role;
import by.vgulab.epam.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    @Override
    public List<User> readAll() throws DaoException {
        final String findAllQuery = "SELECT * FROM user ORDER BY id";
        Statement statement = null;
        ResultSet resultSet = null;
        try {

            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(findAllQuery);
            List<User> usersAll = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
                usersAll.add(user);
            }
            return usersAll;

        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try {
                resultSet.close();
            } catch (Exception e) {
            }
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public boolean isUserInitiatesTransfers(Long id) throws DaoException {
        return false;
    }

    @Override
    public User readByLogin(String login) throws DaoException {
        return null;
    }

    @Override
    public User readByLoginAndPassword(String login, String password) throws DaoException {
        return null;
    }

    @Override
    public User read(Long id) throws DaoException {
        final String read = "SELECT  * FROM user WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            statement = getConnection().prepareStatement(read);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(id);
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
            }
            return user;

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public Long create(User user) throws DaoException {
        final String create = "INSERT INTO user (login, password, name, surname, email, role) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = getConnection().prepareStatement(create, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setString(5, user.getEmail());
            statement.setInt(6, user.getRole().ordinal());
            statement.executeUpdate();
            Long id = null;
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;

        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try {
                resultSet.close();
            } catch (Exception e) {
            }
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void update(User user) throws DaoException {
        final String updateQuery = "UPDATE user SET login = ?, password = ?, name = ?, surname = ?, email = ?, role = ? WHERE id = ?";

        PreparedStatement statement = null;

        try {
            statement = getConnection().prepareStatement(updateQuery);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setString(5, user.getEmail());
            statement.setInt(6, user.getRole().ordinal());
            statement.setLong(7, user.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        final String delete = "DELETE FROM user WHERE id = ?";

        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(delete);
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {

            }
        }
    }
}
